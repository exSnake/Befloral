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

import com.google.gson.Gson;

import it.befloral.beans.ResponseStatusMessage;
import it.befloral.beans.Product;
import it.befloral.beans.Review;
import it.befloral.beans.User;
import it.befloral.model.ProductDAO;
import it.befloral.model.ReviewDAO;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/Products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductDAO model = new ProductDAO();

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
		if(request.getParameter("active") != null) {
			request.setAttribute("active", request.getParameter("active"));
		}
		var action = request.getParameter("action");
		if (action != null) {
			if (action.equals("view")) {
				doView(request, response);
				return;
			}
			if (action.equals("search")) {
				doSearch(request, response);
				return;
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
	
	private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.removeAttribute("products");
		String searchVal = request.getParameter("searchVal");
		var imageRoot = getServletContext().getContextPath() + "/resources/images/products/";
		var realPath = getServletContext().getRealPath("/resources/images/products/");
		Collection<Product> products;
		try {
			products = model.doSearchByName(searchVal);
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
			if(request.getSession().getAttribute("user") == null) {
				if(request.getHeader("x-requested-with") == null) {
					response.sendRedirect(getServletContext().getContextPath() + "/Login");
				} else {
					response.setContentType("application/json; charset=UTF-8");
					response.setStatus(401);
					response.getWriter().print(new Gson().toJson(new ResponseStatusMessage(401,"unauthenticated")));
					response.getWriter().flush();
				}
			} else {
				doReview(request, response);
			}
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
			response.setStatus(200);
			response.getWriter().write(new Gson().toJson(new ResponseStatusMessage(200,"Review added successful")));
		} catch (SQLException e) {
			response.setStatus(500);
			response.getWriter().print(new Gson().toJson(new ResponseStatusMessage(500,e.getMessage())));
			response.getWriter().flush();
			return;
		}
	}

}
