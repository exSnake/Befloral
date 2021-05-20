package it.befloral.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.Product;
import it.befloral.model.GenericDAO;
import it.befloral.model.ProductDAO;

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
		// Acquire all products from db
		String sort = request.getParameter("sort");
		request.setAttribute("active", "Products");
		try {
			var action = request.getParameter("action");
			if (action != null) {
				// View page
				if (request.getParameter("action").equals("view") && (request.getParameter("id") != null)) {
					int id = Integer.parseInt(request.getParameter("id"));
					var prod = model.doRetriveByKey(id);
					request.setAttribute("bean", prod);
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/products/view.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				// index page
				request.removeAttribute("products");
				var imageRoot = getServletContext().getContextPath() + "/resources/images/products/";
				var realPath = getServletContext().getRealPath("/resources/images/products/");
				var products = model.doRetrieveAll(sort);
				for (Product p : products) {
					p.setImagePath(new File(realPath + p.getId() + ".jpg").exists() ? imageRoot + p.getId() + ".jpg"
							: imageRoot + "error.png");
				}
				request.setAttribute("products", products);
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/products/index.jsp");
				dispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
