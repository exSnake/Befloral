<%@ tag body-content="scriptless"%>
<%@ attribute name="pageTitle" required="true" type="java.lang.String"%>
<%@ tag import="it.befloral.model.Cart"%>
<%@ tag import="it.befloral.loggedControl.User"%>

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
				<a href="/"><img style="height: 80px; max-width: 100%"
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
				<%
				Cart cart = null;
				if (request.getSession().getAttribute("cart") != null)
					cart = (Cart) request.getSession().getAttribute("cart");
				%>
				<%
				User user = null;
				if (request.getSession().getAttribute("user") != null)
					user = (User) request.getSession().getAttribute("user");
				%>
				
				
				<a class="ml-2" href="Products"><i class="fas fa-store fa-2x"></i></a>
				<a class="ml-2" href="Cart"><i
					class="fas fa-shopping-cart fa-2x"></i>(<%=cart == null ? 0 : cart.getTotalProductsQuantity()%>)</a>
				<a class="ml-2" href="Login"><i
					class="fas fa-sign-in-alt fa-2x"></i><%= user == null ? "Accedi" : user.getWelcome() %></a>
			</div>
		</div>
	</div>
	<!-- Navbar -->

	<div class="container">
		<jsp:doBody />
	</div>
	
	<!-- Footer colors #2e2e2e   and #252525  -->
	<div class="footer">
		<div class=footer-left>
			<a href="#facebook"><i class="fab fa-facebook fa-2x"></i></a>
			<a class="ml-2" href="#twitter"><i class="fab fa-twitter fa-2x"></i></a>
			<a class="ml-2" href="#instagram"><i class="fab fa-instagram fa-2x"></i></a>	
		</div>
		<div class=footer-fill>
		
		</div>
	  <!-- Copyright -->
		<div class="footer-right">© 2021 Copyright:
			<a class="anchorBefloral" href="/befloral">Befloral.com</a>
		</div>	
		
	</div>
	<!-- Footer -->
</body>
</html>