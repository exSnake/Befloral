package it.befloral.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.Order;
import it.befloral.beans.Product;
import it.befloral.beans.User;
import it.befloral.model.GenericDAO;
import it.befloral.model.OrderDAO;
import it.befloral.model.ProductDAO;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/Orders")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GenericDAO<Order> model = new OrderDAO();

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
		try {
			var action = request.getAttribute("action");
			var user = request.getSession().getAttribute("user");
			if (user == null) {
				response.sendError(404);
				return;
			}

			if (action != null) {
				if (action.equals("view")) {
					// view detail order
					if (request.getParameter("action").equals("view") && (request.getParameter("id") != null)) {
						int id = Integer.parseInt(request.getParameter("id"));
						var order = model.doRetriveByKey(id);
						request.setAttribute("bean", order);
						RequestDispatcher dispatcher = request.getServletContext()
								.getRequestDispatcher("/WEB-INF/views/orders/view.jsp");
						dispatcher.forward(request, response);
					}
				}
			} else {
				response.sendRedirect("User");
				return;
			}

			if (action != null) {
				if (action.equals("view")) {
					// view detail order

				}

			} else {
				response.sendRedirect("User");
				return;

			}
		} catch (SQLException a) {
			System.out.println("Error:" + a.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
