package it.befloral.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.Order;
import it.befloral.model.GenericDAO;
import it.befloral.model.OrderDAO;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = { "/Admin/Orders" })
public class AdminOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GenericDAO<Order> model = new OrderDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminOrderServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var action = request.getParameter("action");
		if (action == null) {
			index(request, response);
		} else {
			if (action.equals("view")){
				doView(request, response);
			} else if (action.equals("edit")) {
				doEdit(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var action = request.getAttribute("action");
		if (action == null) {

		} else if (action.equals("put")) {
			doPut(request, response);
		} else if (action.equals("delete")) {
			doDelete(request, response);
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO view all orders 10 per page, filtered eventually by date or customer
		var page = request.getAttribute("page") == null ? 1 : request.getAttribute("page");
		var dateFrom = request.getAttribute("dateFrom");
		var dateTo = request.getAttribute("dateTo");
		var uid = request.getAttribute("userId");
		// http://localhost/befloral/admin/orders?page=1 <- primi 10 ordini
		// http://localhost/befloral/admin/orders?page=2 <- dal 10 al 20 esimo ordine...
		// etc
		// http://localhost/befloral/admin/orders?page=1&dateFrom=2021-01-01&dateTo=2021-05-01
		// 1
	}

	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO view single order detail page
		// http://localhost/beflral/admin/orders?action=view&id=1
		try {
			if (request.getParameter("action").equals("view") && request.getParameter("id") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				var order = model.doRetriveByKey(id);
				request.setAttribute("bean", order);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/admin/orders/view.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

	}

	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO view single order edit page
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO delete order from db (only id needed)
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO update order (attribute will be the same as db columns)
	}
}
