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

import it.befloral.beans.CustomerBean;
import it.befloral.beans.UserBean;
import it.befloral.model.CustomerDAO;
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		UserBean user = null;
		// If session have user
		if(request.getSession().getAttribute("user") != null)
			user = (UserBean) request.getSession().getAttribute("user");
		// Try to Register
		String action = request.getParameter("action");
		if (user == null && action != null && action.equals("register")) {
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/views/login/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if (user == null) {
			// Login page if not logged in
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
			dispatcher.forward(request, response);
		} else {
			// Profile page
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/views/users/index.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			// Try Registering
			if (action.equals("register")) {
				registerUser(request,response);
			}


			// Try login by login.jsp
			if (action.equals("login")) {
				UserBean bean = null;
				UserDAO customer = new UserDAO();
				try {
					bean = customer.doRetriveByUsername(request.getParameter("email"));
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (bean == null || bean.getPassword() == request.getParameter("password")) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login/login.jsp");
					dispatcher.forward(request, response);
				} else {
					request.getSession().setAttribute("user", bean);
				}

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/WEB-INF/views/index.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// control same password
		if(validateForm(request)) {
			CustomerBean bean = new CustomerBean();
			CustomerDAO dao = new CustomerDAO();
			bean.setBirthday(LocalDate.parse(request.getParameter("birthday")));
			bean.setFirstName(request.getParameter("firstName"));
			bean.setLastName(request.getParameter("lastName"));
			bean.setSubscription(request.getParameter("subscribe") != null);
			UserBean user = new UserBean();
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRole("customer");
			user.setActive(true);
			bean.setUser(user);
			try {
				dao.doSave(bean);
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
	 * @param request
	 * @return if form is valid
	 */
	private boolean validateForm(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors", null);
		if(request.getParameter("firstName") == null || request.getParameter("firstName").length() <= 2) {
			errors.add("Enter a valid firstname");
		}
		if(request.getParameter("lastName") == null || request.getParameter("lastName").length()  <= 2) {
			errors.add("Enter a valid lastname");
		}
		
		if(request.getParameter("birthday") != null) {
			String arr[] = request.getParameter("birthday").split("-");
			LocalDate date = null;
			try {
				date = LocalDate.parse(request.getParameter("birthday"));
			} catch (DateTimeParseException e) {
				errors.add("Enter a valid birthday");
			}
			if(date.getYear() < 1920 || date.getYear() > (LocalDate.now().getYear() - 14))
				errors.add("Enter a valid birthday");
		} else {
			errors.add("Enter the birthday");
		}
		
		if(request.getParameter("password") == null || request.getParameter("password") != request.getParameter("confirmpassword")) {
			errors.add("Password doesn't match");
		}
		if(errors.size() > 0)
			request.setAttribute("errors", errors);
		return errors.size() > 0;
	}

}
