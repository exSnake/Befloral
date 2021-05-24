package it.befloral.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.User;
import it.befloral.model.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(this.getClass().getSimpleName() + " get:" + request.getParameter("action"));
		request.setAttribute("active", "Login");
		User user = (User) request.getSession().getAttribute("user");
		String action = request.getParameter("action");
		// Try to Register
		if(user == null) {
			if(action != null && action.equals("register")) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login/register.jsp");
				dispatcher.forward(request, response);
				return;
			} else {
				// Login page if not logged in
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login/login.jsp");
				dispatcher.forward(request, response);
				return;
			}
		} else {
			if(action.equals("logout")) {
				request.getSession().invalidate();
				response.sendRedirect("Home");
				return;
			} else {
				response.sendRedirect("User");
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass().getSimpleName() + " post:" + request.getParameter("action"));
		String action = request.getParameter("action");
		request.setAttribute("active", "Login");
		if (action != null) {
			// Try Registering
			if (action.equals("register")) {
				registerUser(request, response);
				return;
			} else if (action.equals("login")) {
				// Try login by login.jsp
				User user = null;
				UserDAO userDAO = new UserDAO();
				try {
					user = userDAO.doRetriveByEmail(request.getParameter("email"));
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
				if (user == null || !user.getPassword().equals(request.getParameter("password"))) {
					request.getSession().removeAttribute("user");
					request.setAttribute("errors", new ArrayList<String>() {{add("User not found or password error");}});
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login/login.jsp");
					dispatcher.forward(request, response);
					return;
				} else {
					request.removeAttribute("errors");
					if(user.getRole().equals("customer")) {
						request.getSession().setAttribute("user", user);
					} else if(user.getRole().equals("administrator")) {
						request.getSession().setAttribute("admin", user);
					}
					response.sendRedirect("Home");
				}
			}
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// control same password
		if (validateForm(request)) {
			User user = new User();
			UserDAO dao = new UserDAO();
			user.setBirthday(LocalDate.parse(request.getParameter("birthday")));
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setSubscription(request.getParameter("subscribe") != null);
			user.setGender(request.getParameter("gender"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRole("customer");
			user.setActive(true);
			try {
				dao.doSave(user);
				user = dao.doRetriveByEmail(user.getEmail());
				request.getSession().setAttribute("user", user);
				response.sendRedirect("Home");
				return;
			} catch (IOException | SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/WEB-INF/views/login/register.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/views/login/register.jsp");
			dispatcher.forward(request, response);
		}

	}

	/***
	 * Check if the register form is valid
	 * 
	 * @param request
	 * @return if form is valid
	 */
	private boolean validateForm(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors", null);
		if (request.getParameter("firstName") == null || request.getParameter("firstName").length() <= 2) {
			errors.add("Enter a valid firstname");
		}
		if (request.getParameter("lastName") == null || request.getParameter("lastName").length() <= 2) {
			errors.add("Enter a valid lastname");
		}

		if (request.getParameter("birthday") != null) {
			String arr[] = request.getParameter("birthday").split("-");
			LocalDate date = null;
			try {
				date = LocalDate.parse(request.getParameter("birthday"));
			} catch (DateTimeParseException e) {
				errors.add("Enter a valid birthday");
			}
			if (date.getYear() < 1920 || date.getYear() > (LocalDate.now().getYear() - 14))
				errors.add("Enter a valid birthday");
		} else {
			errors.add("Enter the birthday");
		}

		if (request.getParameter("password") == null
				|| request.getParameter("password") != request.getParameter("confirmpassword")) {
			errors.add("Password doesn't match");
		}
		if( request.getParameter("gender") == null) 
			errors.add("Set a gender or choose undefined");
		if (errors.size() > 0)
			request.setAttribute("errors", errors);
		return errors.size() > 0;
	}

}
