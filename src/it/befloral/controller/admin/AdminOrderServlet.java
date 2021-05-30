package it.befloral.controller.admin;

import java.awt.List;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.Order;
import it.befloral.model.GenericDAO;
import it.befloral.beans.User;
import it.befloral.model.OrderDAO;
import it.befloral.model.UserDAO;

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
		System.out.println(action);
		if (action == null) {
			index(request, response);
		} else {
			if (action.equals("view")) {
				doView(request, response);
				return;
			} else if (action.equals("edit")) {
				doEdit(request, response);
				return;
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
			return;
		} else if (action.equals("delete")) {
			if (request.getParameter("id") != null) {
				doDelete(request, response);
				return;
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
				request.setAttribute("items", order.getItems());
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/admin/orders/view.jsp");
				dispatcher.forward(request, response);
				return;
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page")) ;
		page = page <= 0 ? 1 : page ;
		LocalDate dateFrom = (request.getParameter("dateFrom") == null || request.getParameter("dateFrom").isEmpty()) ? LocalDate.parse("1970-01-01") : LocalDate.parse(request.getParameter("dateFrom"));
		LocalDate dateTo = (request.getParameter("dateTo") == null || request.getParameter("dateTo").isEmpty()) ? LocalDate.parse("2038-01-18") : LocalDate.parse(request.getParameter("dateTo"));
		int uid = request.getParameter("userId") == null ? 0 : Integer.parseInt(request.getParameter("userId"));
		int offset = request.getParameter("offset") == null ? 10 :  Integer.parseInt(request.getParameter("offset")); // 10																						// FOR																					// PAGE
		String sort = request.getParameter("sort") == null ? "id" : request.getParameter("sort"); // NO ORDER , DEFAULT
																								// ORDER BY ID
		OrderDAO dao = new OrderDAO();
		UserDAO udao = new UserDAO();
		try {
			/* TAKE orders of that id */
			Collection<Order> orders = dao.doRetrieveOrdersBetween(sort, uid, ((page - 1) * (offset)), offset,
					Timestamp.valueOf(dateFrom.atStartOfDay()), Timestamp.valueOf(dateTo.atStartOfDay()));
			int pageCount = (int) Math.ceil(dao.doRetrieveCount()/(double)offset);
				
			ArrayList<Entry<Integer,String>> pages = new ArrayList<Entry<Integer,String>>();
			int toShow = 7;
			int start = (int) Math.max(1, Math.min(Math.floor(page - (toShow/2) ), pageCount-toShow));
			System.out.println(start+toShow);
			System.out.println(pageCount);
			int end = (start+toShow) < pageCount ? start+toShow : pageCount;
			for (int i = start; i <= end; i++) {
				
				pages.add(new AbstractMap.SimpleEntry<>(i,String.format("page=%d&dateFrom=%s&dateTo=%s&userId=%d&offset=%d&sort=%s",i,dateFrom.toString(),dateTo.toString(),uid,offset,sort)));
				//pages.add(String.format("page=%d&dateFrom=%s&dateTo=%s&userId=%d&offset=%d&sort=%s",i,dateFrom.toString(),dateTo.toString(),uid,offset,sort));
			}
			request.removeAttribute("orders");
			request.setAttribute("orders", orders);
			request.setAttribute("pages", pages);
			request.setAttribute("currPage", page);
			request.setAttribute("users", udao.doRetrieveAll("email"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/orders/index.jsp");
			dispatcher.forward(request, response);
			return;
			// Rispondi TODO

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("id") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				var order = model.doRetriveByKey(id);
				request.setAttribute("bean", order);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/admin/orders/edit.jsp");
				dispatcher.forward(request, response);
				return;
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		// http://localhost/beflral/admin/orders?action=view&id=1

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
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO update order (attribute will be the same as db columns)

		int id = Integer.parseInt(request.getParameter("id"));
		String destination = request.getParameter("destination");
		String trackNumber = request.getParameter("trackNumber");
		String giftMessage = request.getParameter("giftMessage");

		Order order = new Order();

		order.setId(id);
		order.setDestination(destination);
		order.setTrackNumber(trackNumber);
		order.setGiftMessage(giftMessage);

		try {
			OrderDAO dao = new OrderDAO();
			dao.doUpdateOrder(order);
			response.sendRedirect(request.getServletContext().getContextPath()+"/Admin/Orders");
			// RISPOSTA TODO

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
