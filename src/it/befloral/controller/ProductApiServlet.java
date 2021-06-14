package it.befloral.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.befloral.beans.ResponseStatusMessage;
import it.befloral.model.ProductDAO;

/**
 * Servlet implementation class ProductApiServlet
 */
@WebServlet("/Api/Products")
public class ProductApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String contentType = "application/json; charset=UTF-8";
	private String action;
	private Gson gson = new Gson();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Se è una richiesta di tipo ajax
		if(request.getHeader("x-requested-with") == null) {
			response.sendError(500);
			return;
		} 
		this.action = request.getParameter("action");
		response.setContentType(contentType);		
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(action.equals("search")) {
			ProductDAO dao = new ProductDAO();
			try {
				var u = dao.doSearchByName(request.getParameter("val"));
				response.setStatus(200);
				response.getWriter().print(gson.toJson(u));
				response.getWriter().flush();
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				response.setStatus(500);
				response.getWriter().print(gson.toJson(new ResponseStatusMessage(500, "error")));
				response.getWriter().flush();
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
