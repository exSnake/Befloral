package it.befloral.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.befloral.beans.Product;
import it.befloral.beans.ResponseStatusMessage;
import it.befloral.model.Cart;
import it.befloral.model.GenericDAO;
import it.befloral.model.ProductDAO;

/**
 * Servlet implementation class CartApiServlet
 */
@WebServlet("/Api/Cart")
public class CartApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static GenericDAO<Product> model = new ProductDAO();
	private static final String contentType = "application/json; charset=UTF-8";
	private String action;
	private Gson gson = new Gson();
	private Cart cart;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getHeader("x-requested-with") == null) {
			response.sendError(500);
			return;
		} 
		cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		this.action = request.getParameter("action");
		response.setContentType(contentType);		
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setStatus(200);
		response.getWriter().print(gson.toJson(cart));
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (action != null) {
				if (action.equals("add")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.addProduct(model.doRetriveByKey(id));
					response.setStatus(200);
					response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, "added")));
					response.getWriter().flush();
					return;
				} else if (action.equals("remove")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.deleteProduct(model.doRetriveByKey(id));
					response.setStatus(200);
					response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, "removed")));
					response.getWriter().flush();
					return;
				} else if (action.equals("removeAll")) {
					cart.deleteAll();
					response.setStatus(200);
					response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, "removedAll")));
					response.getWriter().flush();
					return;
				} else if (action.equals("update")) {
					int id = Integer.parseInt(request.getParameter("id"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					if (quantity > 0) {
						cart.updateProduct(model.doRetriveByKey(id), quantity);
						response.setStatus(200);
						response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, "updated")));
						response.getWriter().flush();
					} else {
						response.setStatus(400);
						response.getWriter().print(gson.toJson(new ResponseStatusMessage(400, "invalid quantity")));
						response.getWriter().flush();
					}
					return;
				} else {
					response.setStatus(400);
					response.getWriter().print(gson.toJson(new ResponseStatusMessage(400, "invalid action")));
					response.getWriter().flush();
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.getWriter().print(gson.toJson(new ResponseStatusMessage(500, "error")));
			response.getWriter().flush();
		}
	}
}
