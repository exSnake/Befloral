<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="it.befloral.loggedControl.User"%>


<link href="resources/css/product.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Login">

	<c:out value="">
		User user = null;
	if (request.getSession().getAttribute("user") != null)
		user = (User) request.getSession().getAttribute("user");
	else response.sendRedirect("/WebContent/WEB-INF/views/login/login.jsp");	
	</c:out>


	<h1>Hi! ${user.getFristname()} </h1>
	
	<div>
	
	</div>







</z:layout>