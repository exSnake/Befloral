package it.befloral.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var action = request.getParameter("action");
		if(action == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/index.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("viewLogs")) {
			List<String> logs = new LinkedList<String>();
			logs = Files.readAllLines(Paths.get(getServletContext().getInitParameter("logFilePath")));
			request.setAttribute("logs", logs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/logs.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("clearLogs")) {
			Files.writeString(Paths.get(getServletContext().getInitParameter("logFilePath")), "", StandardOpenOption.TRUNCATE_EXISTING);
			response.sendRedirect(getServletContext().getContextPath() + "/Admin");
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
