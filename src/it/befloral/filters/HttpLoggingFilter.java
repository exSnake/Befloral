package it.befloral.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.befloral.controller.UserServlet;

/**
 * Servlet Filter implementation class HttpLoggingFilter
 */
@WebFilter("/*")
public class HttpLoggingFilter implements Filter {
	private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Default constructor. 
     */
    public HttpLoggingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		StringBuilder str = new StringBuilder();
		str.append("\"Log\":{");
		str.append("\"URL\":\"" + req.getRequestURI().substring(req.getContextPath().length()) +"\",");
		str.append("\"Method\":\"" + req.getMethod() +"\",");
		str.append("\"Action\":\"" + req.getParameter("action") == null ? "null" : req.getParameter("action") + "\"");
		
		// pass the request along the filter chain
		try {
			chain.doFilter(request, response);
			str.append("},");
			LOGGER.debug(str.toString());
		} catch (Exception e) {
			str.append("\"Error\":\"" + e.getMessage() + "\"");
			str.append("\"StackTrace\":\"" + e.getStackTrace().toString() + "\"");
			str.append("},");
			LOGGER.error(str.toString());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
