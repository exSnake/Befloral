package it.befloral.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.Product;
import it.befloral.beans.Review;
import it.befloral.beans.User;
import it.befloral.model.GenericDAO;
import it.befloral.model.ProductDAO;
import it.befloral.model.ReviewDAO;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/Products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GenericDAO<Product> model = new ProductDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		request.setAttribute("active", "Products");
		var action = request.getParameter("action");
		if (action != null) {
			if (action.equals("view")) {
				doView(request, response);
			}
		} else {
			index(request, response);
		}

	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.removeAttribute("products");
		String sort = request.getParameter("sort");
		var imageRoot = getServletContext().getContextPath() + "/resources/images/products/";
		var realPath = getServletContext().getRealPath("/resources/images/products/");
		Collection<Product> products;
		try {
			products = model.doRetrieveAll(sort);
			for (Product p : products) {
				p.setImagePath(new File(realPath + p.getId() + ".jpg").exists() ? imageRoot + p.getId() + ".jpg"
						: imageRoot + "error.png");
			}
			request.setAttribute("products", products);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/products/index.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}		
	}

	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var pid = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
		try {
			Product prod = model.doRetriveByKey(pid);
			request.setAttribute("prod", prod);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/products/view.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var pid = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
		var action = request.getParameter("action");
		if(action.equals("review")) {
			if(request.getSession().getAttribute("user") == null)
				response.sendRedirect(getServletContext().getContextPath() + "/Login");
			else
				doReview(request, response);
		}
	}

	private void doReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		Review rw = new Review();
		ReviewDAO rdao = new ReviewDAO();
		rw.setTitle(request.getParameter("title"));
		rw.setBody(request.getParameter("body"));
		rw.setScore(Integer.parseInt(request.getParameter("score")));
		rw.setUser(user);
		rw.setProduct(new Product(Integer.parseInt(request.getParameter("pid"))));
		try {
			rdao.doSave(rw);
			response.getWriter().write("success");
		} catch (SQLException e) {
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
			return;
		}
	}

}
