<!-- Navbar -->
<%@page import="it.befloral.model.Cart"%>
<link rel="shortcut icon" type="image/x-icon" href="<%=getServletContext().getContextPath()%>/resources/images/logoHorizzontal-removebg.png">

<script src="https://kit.fontawesome.com/442bbb4090.js"	crossorigin="anonymous"></script>

<link href="<%=getServletContext().getContextPath()%>/resources/css/layout.css"	rel="stylesheet" type="text/css">

<div class="navbar">
	<div class="nav-left">
		<div id="nav-logo">
			<a href="/"><img style="height:80px;max-width: 100%" alt="LOGO"	src="<%=getServletContext().getContextPath()%>/resources/images/logoHorizzontal-removebg.png"></a>
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
			<% Cart cart = null;
			if(request.getSession().getAttribute("cart") != null)
				cart = (Cart) request.getSession().getAttribute("cart"); 
			%>
			<a class="ml-2" href="Products"><i class="fas fa-store fa-2x"></i>(<%= cart == null ? 0 : cart.getTotalProductsQuantity() %>)</a>
			<a class="ml-2" href="Cart"><i class="fas fa-shopping-cart fa-2x"></i></a> 
			<a class="ml-2" href="#login"><i class="fas fa-sign-in-alt fa-2x"></i></a>
		</div>
	</div>
</div>
<!-- Navbar -->
