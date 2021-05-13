<!-- Navbar -->
<%@page import="it.befloral.model.Cart"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link href="resources/css/layout.css" rel="stylesheet" type="text/css">
<title>${pageTitle}</title>
</head>

<body>
	<!-- Navbar -->
	
	<link rel="shortcut icon" type="image/x-icon" href="resources/images/logoHorizzontal-removebg.png">

	<script src="https://kit.fontawesome.com/442bbb4090.js"crossorigin="anonymous"></script>

	<div class="navbar">
		<div class="nav-left">
			<div id="nav-logo">
				<a href="Home"><img style="height: 80px; max-width: 100%"
					alt="LOGO"
					src="resources/images/logoHorizzontal-removebg.png"></a>
			</div>
		</div>
		<div class="nav-fill">
			<div id="nav-search">
				<form action="servo" method="get">
					<input type="text" placeholder="Search.." name="search" size="120">
					<button style="padding: 10px;" type="submit">
						<i class="fas fa-search"></i>
					</button>
				</form>
			</div>
		</div>
		<div class="nav-right">
			<div id="nav-tools">
				<a class="ml-2" href="Products">
					<i class="fas fa-store fa-2x"></i>
				</a>
				<a class="ml-2" href="Cart">
					<i class="fas fa-shopping-cart fa-2x"></i>(${cart == null ? 0 : cart.getTotalProductsQuantity()})	
				</a>
				<a class="ml-2" href="Login">
					<i class="fas fa-sign-in-alt fa-2x"></i>
				</a>
			</div>
		</div>
	</div>
	<!-- Navbar -->

	<div class="container">
