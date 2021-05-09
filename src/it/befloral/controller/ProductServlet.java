package it.befloral.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.ProductBean;
import it.befloral.model.ProductModelDS;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/Products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModelDS model = new ProductModelDS();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Acquire all products from db
		String sort = request.getParameter("sort");
	
		try {
			var action =request.getParameter("action"); 
			if(action != null) {
			//Create page
				if(action.equals("create")) {
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/products/create.jsp");
					dispatcher.forward(request, response);
				}
			//View page
			} else if(request.getParameter("id") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				var prod = model.doRetriveByKey(id);
				request.setAttribute("product", prod);
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/products/view.jsp");
				dispatcher.forward(request, response);
			} else {
			//index page
				request.removeAttribute("products");
				var imageRoot = getServletContext().getContextPath() + "/resources/images/products/";
				var realPath = getServletContext().getRealPath("/resources/images/products/");
				var products = model.doRetrieveAll(sort);
				for (ProductBean p : products) {
					p.setImagePath(new File(realPath + p.getId() + ".jpg").exists() ? imageRoot + p.getId() + ".jpg" : imageRoot + "error.png");
				}
				request.setAttribute("products", products);
				
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/products/index.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
