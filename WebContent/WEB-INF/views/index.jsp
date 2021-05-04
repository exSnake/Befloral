<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Befloral</title>
	
	</head>
	<body>
	
		<%-- Dynamic include (A static include is resolved at compile time,
		 and may thus not use a parameter value, which is only known at execution time.) --%>
		<jsp:include page="layouts/header.jsp"></jsp:include>
	
		<!-- BIG PHOTO OF STORE -->
		
		<div class="divImage" style="text-align: center; ">
			<img class="cropped" alt="BIG PHOTO OF STORE" 
			src="<%=getServletContext().getContextPath()%>/resources/images/screenlogo.jpg">
		</div>
		
		<p>Site under construction!</p>
		
		<%-- Dynamic include (A static include is resolved at compile time,
		 and may thus not use a parameter value, which is only known at execution time.) --%>
		<jsp:include page="layouts/footer.jsp"></jsp:include>
		
	</body>

</html>