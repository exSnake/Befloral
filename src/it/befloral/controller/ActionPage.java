package it.befloral.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActionPage
 */
@WebServlet("/ActionPage")
public class ActionPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ActionPage() {
        super();
        
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//RequestDispatcher d=getServletContext().getRequestDispatcher("/WEB-INF/views/layouts/productView.jsp");
		response.sendRedirect("/WEB-INF/views/layouts/productV.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
