package it.befloral.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.model.Cart;
import it.befloral.model.ProductModelDS;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/Cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static ProductModelDS model = new ProductModelDS();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//show cart page
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Check action
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		//If action == add
		try {
			if(action != null) {
				if(action.equals("add")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.addProduct(model.doRetriveByKey(id));
					response.sendRedirect("Products");
					return;
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//else if action == update
			//edit item in cart
		//else if action == delete
			//delete item from cart
		//else if action = removeAll
			//delete every items from cart
	}

}
