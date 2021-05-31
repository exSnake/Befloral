package it.befloral.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.CartProduct;
import it.befloral.beans.Order;
import it.befloral.beans.OrderItem;
import it.befloral.beans.User;
import it.befloral.beans.Address;
import it.befloral.model.Cart;
import it.befloral.model.GenericDAO;
import it.befloral.model.OrderDAO;
/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/Orders")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var action = request.getParameter("action");
		User user = (User) request.getSession().getAttribute("user");
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (action != null) {
			if (action.equals("view")) {
				// view detail order
			} else if (action.equals("checkout")){
				if(cart == null || ((Cart)cart).getTotalProductsQuantity() <= 0) {
					response.sendError(500);
					return;
				}
				if(request.getAttribute("address") == null) {
					request.getSession().setAttribute("address", user.getPreferredAddress());
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/orders/summary.jsp");
				dispatcher.forward(request, response);
				return;
			} 
		} else {
			response.sendRedirect("User");
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var action = request.getParameter("action");
		if(action != null) {
			if(action.equals("create")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/orders/summary.jsp");
				dispatcher.forward(request, response);
				return;
			}else if(action.equals("buy")) {
				doBuy(request, response);
				return;
			}
			
			
		}

		
	}
	
	private void doBuy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/***
		 * TODO Redirect to a page where the user can select if is a gift, the address
		 * of the shipment, payment method and gift message
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		User user = (User) request.getSession().getAttribute("user");
		Address address = (Address) request.getSession().getAttribute("address");
		if(cart == null || address == null) {
			response.sendError(500);
			return;
		}
		
		Order order = new Order();
		order.setDestination(address.toString());
		order.setTotalPaid(cart.getTotalPrice());
		order.setTotalProducts(cart.getTotalProductsQuantity());
		order.setTrackNumber("");
		order.setUser(user);
		if(request.getParameter("isGift") != null) {
			order.setGift(true);
			order.setGiftMessage(request.getParameter("giftMessage"));
		} else {
			order.setGift(false);
		}
		for (CartProduct prod : cart.getProducts()) {
			OrderItem bean = new OrderItem();
			bean.setDescription(prod.getProduct().getDescription());
			bean.setDiscount(prod.getProduct().getDiscount());
			bean.setName(prod.getProduct().getName());
			bean.setPrice(prod.getTotalPrice());
			bean.setQuantity(prod.getQuantity());
			bean.setShortDescription(prod.getProduct().getShortDescription());
			bean.setWeight(prod.getProduct().getWeight());
			order.addItem(bean);
		}
		OrderDAO dao = new OrderDAO();
		try {
			dao.doSave(order);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
			return;
		}
		request.getSession().setAttribute("cart", new Cart());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/orders/success.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
