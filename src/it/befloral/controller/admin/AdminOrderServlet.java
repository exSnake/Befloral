package it.befloral.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO view all orders 10 per page, filtered eventually by date or customer
		var page = request.getAttribute("page") == null ? 1 : request.getAttribute("page");
		var dateFrom = request.getAttribute("dateFrom");
		var dateTo = request.getAttribute("dateTo");
		var uid = request.getAttribute("userId");
	}
	
	private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO view single order detail page
	}
	
	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
