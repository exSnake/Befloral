<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
	<head>
	
<%-- Including a stylesheet <style><%@include file="/css/navigationMenu.css"%></style> --%>
		
	</head>
</html>

<body>

  <!-- Navbar -->
  
  
  <!-- fristBar -->
<div>
	<ul>
	
		<!-- Logo -->
		<li style="float: left; position: relative;   " >
		<a "><img alt="LOGO" src="images/logoHorizzontal-removebg.png" width="60" height="60"></a> 
		</li>
		<!-- Logo -->
		
		<!-- search bar -->
		<li style=" height: 300%; "> 
		 
		 <form action="servo" method="get" >
	      	<input type="text" placeholder="Search.." name="search" size="120">
	      	<button style="padding-top: 20px;"type="submit"><i class="fas fa-search fa-2x"></i></button>
   		 </form>
		
		</li>
		<!-- search bar -->
		
		<!-- Products/Cart/Login -->
		<li><a href="#products"><i class="fas fa-store fa-2x"></i><br>Products</a></li>
		<li><a href="#cart"><i class="fas fa-shopping-cart fa-2x"></i><br>Cart</a></li>
		<li><a href="#login"><i class="fas fa-sign-in-alt fa-2x"></i><br>Login</a></li>
		<!-- Products/Cart/Login -->
		
	</ul>
</div >	
  <!-- fristBar -->
		
		
  <!-- secondBar -->				
<div >	
	<ul>
		<li><a style="mix-blend-mode: hard-light;" href="#contact"><i class="fab fa-weixin fa-2x"></i><br></i>Contact</a></li>
		<li><a href="#news"><i class="far fa-newspaper fa-2x "></i><br>News</a></li>		
		<li><a href="#about"><i class="fas fa-child fa-2x "></i><br>About Us</a></li>
	</ul>
</div>
  <!-- secondBar -->
  


   <!-- Navbar -->

 </body>