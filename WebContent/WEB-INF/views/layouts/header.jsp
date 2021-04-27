<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
	<head>
		<%-- Load a css file --%>
		<style><%@include file="/css/navigationMenu.css"%></style>
		
	</head>
</html>

<body>


  
  <!-- Navbar -->
  
	<ul  >
	
		<li style="top: 0;left: 0;"><a><img alt="LOGO" src="images/logoHorizzontal.jpg"></a> </li>
		<li><a href="#products">Products</a></li>
		<li><a href="#cart">Cart</a></li>
		<li><a href="#login">Login</a></li>
		
	</ul>

	<br>
	
	<!-- BIG PHOTO OF STORE -->
	<p>BIG PHOTO</p>
	<img alt="BIG PHOTO OF STORE" src="images/bigLogPage.jpeg" width="1024" height="860">
	<img alt="BIG PHOTO OF STORE" src="<%=getServletContext().getContextPath()%>/images/bigLogPage.jpeg" width="1024" height="860">

	<br>
<div>	
	<ul>
		<li><a href="#contact">Contact</a></li>
		<li><a href="#news">News</a></li>		
		<li><a href="#about">About</a></li>
	</ul>


  <!-- Navbar -->
  
  
</div>

 </body>