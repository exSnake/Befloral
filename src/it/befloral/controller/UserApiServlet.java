package it.befloral.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.befloral.beans.User;
import it.befloral.model.UserDAO;
import it.befloral.beans.ResponseStatusMessage;

/**
 * Servlet implementation class UserApiServlet
 */
@WebServlet("/Api/User")
public class UserApiServlet extends HttpServlet {
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
		if(action.equals("checkEmail")) {
			UserDAO dao = new UserDAO();
			try {
				var u = dao.doRetriveByEmail(request.getParameter("email"));
				response.setStatus(200);
				response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, u == null ? "free" : "taken")));
				response.getWriter().flush();
				return;
			} catch (SQLException e) {
				response.setStatus(500);
				response.getWriter().print(gson.toJson(new ResponseStatusMessage(500, "error")));
				response.getWriter().flush();
				return;
			}
		}
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
		
		if(action.equals("getAddresses")) {
			response.setStatus(200);
			response.getWriter().print(gson.toJson(user.getAddresses()));
			response.getWriter().flush();
			return;
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
