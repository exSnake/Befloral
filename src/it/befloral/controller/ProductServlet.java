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

import it.befloral.beans.ProductBean;
import it.befloral.model.GenericDAO;
import it.befloral.model.ProductDAO;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/Products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GenericDAO<ProductBean> model = new ProductDAO();

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

		try {
			var action = request.getParameter("action");
			if (action != null) {
				// Create page
				if (action.equals("create")) {
					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/WEB-INF/views/products/create.jsp");
					dispatcher.forward(request, response);
				}

				// Update page
				if (action.equals("update")) {
					var prod = model.doRetriveByKey(Integer.parseInt(request.getParameter("id")));
					request.setAttribute("bean", prod);
					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/WEB-INF/views/products/update.jsp");
					dispatcher.forward(request, response);
				}
				// View page
				if (request.getParameter("action").equals("read") && (request.getParameter("id") != null)) {
					int id = Integer.parseInt(request.getParameter("id"));
					var prod = model.doRetriveByKey(id);
					System.out.println("sdfghjklòàcdfcvgbhnjmk,l.");
					request.setAttribute("bean", prod);
					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/WEB-INF/views/products/view.jsp");
					dispatcher.forward(request, response);
					

				}
			} else {
				// index page
				request.removeAttribute("products");
				var imageRoot = getServletContext().getContextPath() + "/resources/images/products/";
				var realPath = getServletContext().getRealPath("/resources/images/products/");
				var products = model.doRetrieveAll(sort);
				for (ProductBean p : products) {
					p.setImagePath(new File(realPath + p.getId() + ".jpg").exists() ? imageRoot + p.getId() + ".jpg"
							: imageRoot + "error.png");
				}
				request.setAttribute("products", products);

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/products/index.jsp");
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
	
	
	//Double.parseDouble(request.getParameter("weight") != null? request.getParameter("weight").replace(",", ".") : "0.0" )
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("action").equals("create")) {
		ProductDAO products = new ProductDAO();
		ProductBean product = new ProductBean(request.getParameter("name"), request.getParameter("description"),
				request.getParameter("shortDescription"), request.getParameter("metaDescription"),
				request.getParameter("metaKeyword"),
				Double.parseDouble(request.getParameter("weight")),
				Double.parseDouble(request.getParameter("price")), Double.parseDouble(request.getParameter("discount")),
				Integer.parseInt(request.getParameter("quantity")), Integer.parseInt(request.getParameter("onSale")),
				(request.getParameter("available") == null ? false : true));
		
		try {
			products.doSave(product);

		} catch (SQLException e) {
			System.out.println("Error insert Product");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/products/create.jsp");
		dispatcher.forward(request, response);
		}
		if (request.getParameter("action").equals("put")) {
			doPut(request, response);
		}
		if (request.getParameter("action").equals("read")) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/products/view.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if (request.getParameter("action").equals("delete")) {
			doDelete(request, response);
			response.sendRedirect("Products");
			return;
		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = null;
		ProductDAO products = new ProductDAO();
		ProductBean product = new ProductBean(Integer.parseInt(request.getParameter("id")),
				request.getParameter("name"), request.getParameter("description"),
				request.getParameter("shortDescription"), request.getParameter("metaDescription"),
				request.getParameter("metaKeyword"),
				Double.parseDouble(request.getParameter("weight").replace(",", ".")),
				Double.parseDouble(request.getParameter("price")), Double.parseDouble(request.getParameter("discount")),
				Integer.parseInt(request.getParameter("quantity")), Integer.parseInt(request.getParameter("onSale")),
				(request.getParameter("available") == null ? false : true));
		try {
			if (products.doUpdate(product) > 0) {
				response.sendRedirect("Products");
				return;
			} else {
				error = "Update Failed";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("error", error);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/products/update.jsp");
		dispatcher.forward(request, response);

		// response.sendRedirect("/WEB-INF/views/products/update.jsp");

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO product = new ProductDAO();
		try {
			product.doDelete(Integer.parseInt(request.getParameter("id")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Error with delete product id" + request.getParameter("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/products/index.jsp");
		dispatcher.forward(request, response);
	}

}
