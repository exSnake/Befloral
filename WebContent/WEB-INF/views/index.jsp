<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Befloral</title>

<%-- Icon on the tab --%>
<link rel="shortcut icon" type="image/x-icon" href="images/logoHorizzontal-removebg.png">


<%-- Load our icons from FontAwesome.com --%>
<script src="https://kit.fontawesome.com/442bbb4090.js" crossorigin="anonymous"></script>

<%-- Load a css file --%>
<style><%@include file="/css/navigationMenu.css"%></style>





</head>
<body>

	<%-- Dynamic include (A static include is resolved at compile time,
	 and may thus not use a parameter value, which is only known at execution time.) --%>
	<jsp:include page="layouts/header.jsp"></jsp:include>

	
	<!-- BIG PHOTO OF STORE -->
	
	<div class="divImage" style="text-align: center; ">
		<img class="cropped" alt="BIG PHOTO OF STORE" 
		src="<%=getServletContext().getContextPath()%>/images/screenlogo.jpg">
	</div>
	

	
	<p>Site under construction!</p>

	
	<%-- Dynamic include (A static include is resolved at compile time,
	 and may thus not use a parameter value, which is only known at execution time.) --%>
	<jsp:include page="layouts/footer.jsp"></jsp:include>
	
	
	
	
	
	
</body>




</html>