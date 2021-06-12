package it.befloral.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.befloral.beans.Address;
import it.befloral.beans.Order;
import it.befloral.beans.User;
import it.befloral.model.AddressDAO;
import it.befloral.model.GenericDAO;
import it.befloral.model.OrderDAO;
import it.befloral.model.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static GenericDAO<Address> model = new AddressDAO();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("active", "User");
		var action = request.getParameter("action");
		OrderDAO orderDao = new OrderDAO();
		try {
			request.getSession().setAttribute("orders", orderDao.doRetriveByUser(user));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(action == null || action.equals("viewOrders")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/view.jsp");
			dispatcher.forward(request, response);
		}else if (action.equals("viewData")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/myData.jsp");
			dispatcher.forward(request, response);
		}else if (action.equals("invoiceDownload")) {
			try {
				var orderId = (int) Integer.parseInt(request.getParameter("orderId"));
				Order toShow=null;
				Collection<Order> list = (Collection<Order>) request.getSession().getAttribute("orders");
				for (Order order : list) {if(order.getId()==orderId) {toShow=order;break; }}
				if(toShow==null) {				
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/view.jsp");
					dispatcher.forward(request, response);
				}
				OrderDAO o = new OrderDAO();
				toShow = o.doRetriveByKey(toShow.getId());
				
				request.setAttribute("orderToShow", toShow);
				request.setAttribute("pdf", true);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/users/invoice.jsp");
				dispatcher.forward(request, response);
			}catch (NumberFormatException e ) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/view.jsp");
				dispatcher.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("invoiceView")) {
			try {
				var orderId = (int) Integer.parseInt(request.getParameter("orderId"));
				Order toShow=null;
				Collection<Order> list = (Collection<Order>) request.getSession().getAttribute("orders");
				for (Order order : list) {if(order.getId()==orderId) {toShow=order;break; }}
				if(toShow==null) {				
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/view.jsp");
					dispatcher.forward(request, response);
				}
				OrderDAO o = new OrderDAO();
				toShow = o.doRetriveByKey(toShow.getId());
				
				request.setAttribute("orderToShow", toShow);
				request.setAttribute("pdf", false);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/users/invoice.jsp");
				dispatcher.forward(request, response);
			}catch (NumberFormatException e ) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/view.jsp");
				dispatcher.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if (action.equals("viewAddresses")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/addresses.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("createAddress")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/createAddress.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("editAddress")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Address address;
			try {
				address = model.doRetriveByKey(id);
				System.out.println(address);
				request.setAttribute("bean",address);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/editAddress.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
			
		}	
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("active", "User");
		User user = (User) request.getSession().getAttribute("user");
		var action = request.getParameter("action");
		if(action == null) {
			response.sendError(500);
			return;
		}
		if(action.equals("createAddress")) {
			Address address = new Address();
			address.setFirstName(request.getParameter("firstName"));
			address.setLastName(request.getParameter("lastName"));
			address.setAddress(request.getParameter("address"));
			address.setPostalCode(request.getParameter("postalCode"));
			address.setCity(request.getParameter("city"));
			address.setProvince(request.getParameter("province"));
			address.setPhone(request.getParameter("phone"));
			address.setInfo(request.getParameter("info"));
			address.setAlias(request.getParameter("alias"));
			address.setPreferred(request.getParameter("preferred") != null);
			UserDAO userDAO = new UserDAO();
			try {
				userDAO.doSaveAddress(user, address);
				user=userDAO.doRetriveByEmail(user.getEmail());
				request.getSession().setAttribute("user", user);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
			response.sendRedirect("User");
		} else if (action.equals("setPreferredAddress")) {
			UserDAO userDAO = new UserDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			if(user.setPreferredAddress(id)) {
				try {
					userDAO.doSetPreferredAddress(user, id);
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
			}
			response.sendRedirect(getServletContext().getContextPath()+"/User?action=viewAddresses");
			return;
		} else if(action.equals("editAddress")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Address address = new Address();
				address.setId(id);
				address.setUid(user.getId());
				address.setFirstName(request.getParameter("firstName"));
				address.setLastName(request.getParameter("lastName"));
				address.setAddress(request.getParameter("address"));
				address.setPostalCode(request.getParameter("postalCode"));
				address.setCity(request.getParameter("city"));
				address.setProvince(request.getParameter("province"));
				address.setPhone(request.getParameter("phone"));
				address.setInfo(request.getParameter("info"));
				address.setAlias(request.getParameter("alias"));
				address.setPreferred(request.getParameter("preferred") != null);
				try {
					model.doUpdate(address);
					UserDAO u = new UserDAO();
					User userLoad = (User) u.doRetriveByKey(((User)request.getSession().getAttribute("user")).getId());
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", userLoad);
					response.sendRedirect(getServletContext().getContextPath()+"/User");
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendError(500);
				}
		}		
		
	}

}
