package it.befloral.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.befloral.beans.Address;
import it.befloral.beans.ResponseStatusMessage;
import it.befloral.beans.User;

/**
 * Servlet implementation class OrderApiServlet
 */
@WebServlet("/Api/Orders")
public class OrderApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String contentType = "application/json; charset=UTF-8";
	private String action;
	private Gson gson = new Gson();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			response.setStatus(401);
			response.getWriter().print(gson.toJson(new ResponseStatusMessage(401,"unauthorized")));
			response.getWriter().flush();
			return;
		} else if ( action == null ) {
			response.setStatus(500);
			response.getWriter().print(gson.toJson(new ResponseStatusMessage(500,"action needed")));
			response.getWriter().flush();
			return;
		}
		if(action.equals("setAddress")) {
			Address addr = gson.fromJson(request.getParameter("address"), Address.class);
			request.getSession().setAttribute("address", addr);
			response.setStatus(200);
			response.getWriter().print(gson.toJson("success"));
			response.getWriter().flush();
			return;
		}	
	}

}
