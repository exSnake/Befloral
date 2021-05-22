package it.befloral.controller.admin;

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
import it.befloral.model.OrderDAO;
import it.befloral.model.ProductDAO;

/**
 * Servlet implementation class AdminProductServlet
 */
@WebServlet("/Admin/Products")
public class AdminProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var action = request.getParameter("action");
		if(action == null) {
			index(request,response);
		} else {
			if(action.equals("view")) {
				
			} else if (action.equals("edit")) {
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
		// TODO dispatch page to view all product information 10 per page
		var offset = request.getParameter("page") == null ? 10 : request.getParameter("offset");
		var page = request.getParameter("page") == null ? 1 : request.getParameter("page");
		var order = request.getParameter("order") == null ? "id" : request.getParameter("order");
		OrderDAO dao = new OrderDAO();
		try {
			Collection<Order> orders  = dao.doRetrieveSome(order, ((((int)page-1)*((int)offset)+1))   , (int) offset);
			
			request.removeAttribute("order");
			request.setAttribute("order", orders);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//TODO RISPONDI 
		
	}
	
	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO show page to edit product information
	}
	
	@Override
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


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO update product (attribute will be the same as db columns)
	}
}

