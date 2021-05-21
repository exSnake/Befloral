package it.befloral.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.Order;
import it.befloral.beans.User;
import it.befloral.model.OrderDAO;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = { "/Admin/OrderServlet" })
public class AdminOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var action = request.getAttribute("action");
		if(action == null) {
			index(request, response);
		} else {
			if(action == "view") {
				doView(request, response);
			} else if (action == "edit") {
				doEdit(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var action = request.getAttribute("action");
		if(action == null) {
			
		} else if (action.equals("put")) {
			doPut(request, response);
		} else if (action.equals("delete")) {
			doDelete(request, response);
		}
	} 
	
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		var page = request.getParameter("page") == null ? 1 : request.getParameter("page");//TAKE PAGE 
		var dateFrom = request.getAttribute("dateFrom");
		var dateTo = request.getAttribute("dateTo");
		var uid = request.getAttribute("userId");
		
		var offset = request.getParameter("page") == null ? 10 : request.getParameter("offset"); // 10 FOR PAGE
		var order = request.getParameter("order") == null ? "id" : request.getParameter("order"); // NO ORDER , DEFAULT ORDER BY ID
	
		OrderDAO dao = new OrderDAO();
		try {
			/*TAKE orders of that id*/
			Collection<Order> orders  = dao.doRetrieveOrdersBetween(order, (int) uid , ((((int)page-1)*((int)offset)+1))   , (int) offset
					, new Timestamp(((Date)dateFrom).getTime()) , new Timestamp(((Date)dateTo).getTime()));
			
			
			request.removeAttribute("order");
			request.setAttribute("order", orders);
			
			
			//Rispondi TODO
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}		
		
	
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// http://localhost/beflral/admin/orders?action=view&id=12 
		var orderid = request.getParameter("id") == null ? 0 : request.getParameter("id");//TAKE id of order
		
		OrderDAO dao = new OrderDAO(); 
		try {
			Order o = dao.doRetriveByKey((int)orderid);
			
			//Rispondi TODO
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO view single order edit page
		var id = request.getParameter("id") == null ? 0 : request.getParameter("id");//TAKE id of order
		 
		

		


		
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO delete order from db (only id needed)
		var orderid = request.getParameter("id") == null ? 0 : request.getParameter("id");//TAKE id of order
		
		OrderDAO dao = new OrderDAO(); 
		try {
			boolean risp = dao.doDelete((int)orderid);
			
			//Rispondi TODO
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO update order (attribute will be the same as db columns)
		
		var id = request.getAttribute("id");
		var uid = request.getAttribute("uid");
		var destination = request.getAttribute("destination");
		var totalProducts = request.getAttribute("totalProducts");
		var totalPaid = request.getAttribute("totalPaid");
		var trackNumber = request.getAttribute("trackNumber");
		var gif = request.getAttribute("gif");
		var gifMessage = request.getAttribute("gifMessage");
		
		Order order = (Order) request.getSession().getAttribute("order");
		
		order.setId((int)id);
		User temp = new User(); temp.setId((int) uid);
		order.setUser(temp);
		order.setDestination((String) destination);
		order.setTotalProducts((int) totalProducts);
		order.setTotalPaid((int) totalPaid);
		order.setTrackNumber((String) trackNumber);
		order.setGift((boolean) gif);
		
		try {
			OrderDAO dao =  new OrderDAO();
			dao.doUpdateOrder(order);
			
			//RISPOSTA TODO
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
