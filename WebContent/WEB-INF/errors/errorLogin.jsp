<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isErrorPage="true" %>

<link href="resources/css/product.css" rel="stylesheet" type="text/css">

<z:layout pageTitle="ErrorPage">



	<link rel="404photo" type="image/x-icon" href="/resources/images/errorecut.jpg">
		<div class="divImage" style="text-align: center; ">
			<img class="cropped" alt="Error 404" 
			src="${pageContext.request.contextPath}/resources/images/errorecut.jpg" >
		</div>
	
		
		<form method="get" action="Home" >
			<button  class="btn btn-success mt-2" >Go Back</button>
		</form>
	

		
	






</z:layout>