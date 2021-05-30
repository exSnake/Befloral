package it.befloral.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet Filter implementation class HttpLoggingFilter
 */
@WebFilter("/*")
public class HttpLoggingFilter implements Filter {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		var str = new StringBuilder();
		str.append("\"Log\":{");
		str.append("\"URL\":\"" + req.getRequestURI().substring(req.getContextPath().length()) +"\",");
		str.append("\"Method\":\"" + req.getMethod() +"\",");
		str.append("\"Action\":\"" + req.getParameter("action") == null ? "null" : req.getParameter("action") + "\"");
		
		try {
			chain.doFilter(request, response);
			str.append("},");
			LOGGER.debug("{}", str);
		} catch (Exception e) {
			str.append("\"Error\":\"" + e.getMessage() + "\"");
			str.append("\"StackTrace\":\"" + Arrays.toString(e.getStackTrace()) + "\"");
			str.append("},");
			LOGGER.error(str.toString());
		}
	}

}
