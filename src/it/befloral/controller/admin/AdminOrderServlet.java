package it.befloral.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.Order;
import it.befloral.model.GenericDAO;
import it.befloral.beans.User;
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
			if (action.equals("view")) {
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
		var action = request.getParameter("action");
		if (action == null) {
			response.sendError(500);
		} else if (action.equals("put")) {
			doPut(request, response);
		} else if (action.equals("delete")) {
			if (request.getParameter("id") != null) {
				doDelete(request, response);
			} else {
				response.sendError(500);
			}

		}

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

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		LocalDate dateFrom = LocalDate.parse(request.getParameter("dateFrom"));
		LocalDate dateTo = LocalDate.parse(request.getParameter("dateTo"));
		int uid = Integer.parseInt(request.getParameter("userId"));

		int offset = Integer.parseInt(request.getParameter("page") == null ? "10": request.getParameter("offset")); // 10 FOR PAGE
		String sort= request.getParameter("sort") == null ? "id" : request.getParameter("sort"); // NO ORDER , DEFAULT
																									// ORDER BY ID
		OrderDAO dao = new OrderDAO();
		try {
			/* TAKE orders of that id */
			Collection<Order> orders = dao.doRetrieveOrdersBetween(sort, uid,
					(( page - 1) * (offset) + 1),offset,Timestamp.valueOf(dateFrom.atStartOfDay()),
					Timestamp.valueOf(dateTo.atStartOfDay()));

			request.removeAttribute("order");
			request.setAttribute("order", orders);

			// Rispondi TODO

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO view single order edit page

		var id = request.getParameter("id") == null ? 0 : request.getParameter("id");// TAKE id of order

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO delete order from db (only id needed)
		int orderid = Integer.parseInt(request.getParameter("id"));// TAKE id of order
		OrderDAO dao = new OrderDAO();
		
		try {
			boolean risp = dao.doDelete(orderid);

			// Rispondi TODO

		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO update order (attribute will be the same as db columns)

		int id = Integer.parseInt(request.getParameter("id"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		String destination = request.getParameter("destination");
		int totalProducts = Integer.parseInt(request.getParameter("totalProducts"));
		double totalPaid = Double.parseDouble(request.getParameter("totalPaid"));
		String trackNumber = request.getParameter("trackNumber");
		boolean gift = Boolean.parseBoolean(request.getParameter("gift"));
		String giftMessage = request.getParameter("giftMessage");

		Order order = new Order();

		order.setId((int) id);
		User temp = new User();
		temp.setId((int) uid);
		order.setUser(temp);
		order.setDestination(destination);
		order.setTotalProducts(totalProducts);
		order.setTotalPaid(totalPaid);
		order.setTrackNumber(trackNumber);
		order.setGift(gift);
		order.setGiftMessage(giftMessage);

		try {
			OrderDAO dao = new OrderDAO();
			dao.doUpdateOrder(order);

			// RISPOSTA TODO

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
