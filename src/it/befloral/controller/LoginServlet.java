package it.befloral.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.befloral.beans.CustomerBean;
import it.befloral.loggedControl.CryptoPw;
import it.befloral.loggedControl.User;
import it.befloral.model.CustomerDAO;
import it.befloral.model.IsDao;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		if(request.isSecure()==false) {
			RequestDispatcher dispatcher = getServletContext()
	                .getRequestDispatcher("/WEB-INF/errors/errorLogin.jsp");
	        dispatcher.forward(request, response);
		}
		*/
		
		//If session have user
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
			//Pagina di login if not loggato
			RequestDispatcher dispatcher = getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/login/login.jsp");
	        dispatcher.forward(request, response);
		}
		else {
			//Pagina di profilo
			RequestDispatcher dispatcher = getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/profile/profile.jsp");
	        dispatcher.forward(request, response);
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action!=null) {
			
			//Try Registering
			if(action.equals("register")) {
				//control same password	
				CustomerBean bean = new CustomerBean();
				bean.setActive(1);
				String dd = (request.getParameter("birthday"));
				String arr[] = dd.split("-");
				
				bean.setBirthday(new Date(Integer.parseInt(arr[0]) ,Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
				
				bean.setFristname(request.getParameter("fristName"));
				bean.setLastname(request.getParameter("lastName"));
				bean.setEmail(request.getParameter("email"));
				bean.setNewsletter(request.getParameter("newsletter"));
				
				String psw1 =  request.getParameter("password");
				String psw2 =  request.getParameter("confirmpassword");
				
				
				if(!psw1.equals(psw2)) {//Password diverse rimanda alla pagina di registrazione
					RequestDispatcher dispatcher = getServletContext()
			                .getRequestDispatcher("/WEB-INF/views/login/register.jsp");
			        dispatcher.forward(request, response);
				}
				
				bean.setPassword(CryptoPw.crypt(psw1.concat(bean.getEmail())));
				
				CustomerDAO dao = new CustomerDAO();
				
				try {
					 dao.doInsert(bean);
				}catch (SQLIntegrityConstraintViolationException e) {
					//Email gia' presente
					RequestDispatcher dispatcher = getServletContext()
			                .getRequestDispatcher("/WEB-INF/views/login/register.jsp");
			        dispatcher.forward(request, response);
				}				
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					RequestDispatcher dispatcher = getServletContext()
			                .getRequestDispatcher("/WEB-INF/views/login/register.jsp");
			        dispatcher.forward(request, response);
				}
				
					//Registrazione success
							
					
				    User utente = new User(bean.getId(), bean.getFristname(), bean.getLastname(), bean.getEmail());
				    request.getSession().setAttribute("user", utente);
				    
					RequestDispatcher dispatcher = getServletContext()
			                .getRequestDispatcher("/WEB-INF/views/index.jsp");
			        dispatcher.forward(request, response);
					
				
				
			}
			
			
			
			//Try to Register
			if(action.equals("sendToRegister")) {
				
				RequestDispatcher dispatcher = getServletContext()
		                .getRequestDispatcher("/WEB-INF/views/login/register.jsp");
		        dispatcher.forward(request, response);
			}
			
			//Try loggin by login.jsp
			if(action.equals("loggin")) {
				
				CustomerBean bean=null;
				CustomerDAO customer = new CustomerDAO();
			    try {
					 bean = customer.doRetriveByEmail(request.getParameter("email"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    if(bean==null) {
			    	//LOGGIN FALLITO UTENTE NON PRESENTE IN DATABASE
					RequestDispatcher dispatcher = getServletContext()
			                .getRequestDispatcher("/WEB-INF/views/login/login.jsp");//DA avvertire loggin fallito
			        dispatcher.forward(request, response);
			    }
			    
			    //Check password

			    if(!(CryptoPw.isEqual(request.getParameter("password").concat(request.getParameter("email")) , bean.getPassword() )) ) {
					
			    	RequestDispatcher dispatcher = getServletContext()
			                .getRequestDispatcher("/WEB-INF/views/login/login.jsp");//DA avvertire loggin fallito
			        dispatcher.forward(request, response);

			    }
			    
			    User utente = new User(bean.getId(), bean.getFristname(), bean.getLastname(), bean.getEmail());
			    request.getSession().setAttribute("user", utente);
			    
				RequestDispatcher dispatcher = getServletContext()
		                .getRequestDispatcher("/WEB-INF/views/index.jsp");
		        dispatcher.forward(request, response);
			
			}
			
			
				
			
			
			
			
		}
		
	
	}

}
