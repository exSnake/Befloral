<%@ tag body-content="scriptless"%>
<%@ attribute name="pageTitle" required="true" type="java.lang.String"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="http://fonts.cdnfonts.com/css/playlist" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
   
	<link href="<c:url value="/resources/css/layout.css"/>" rel="stylesheet" type="text/css">
	<title>${pageTitle}</title>
</head>

<body>
	<!-- Navbar -->
	
	<div class="main-bar">
        <nav>
            <input type="checkbox" id="check">
            <label class="checkbtn" for="check">
                <span></span>
                <span></span>
                <span></span>
            </label>
            
            <div class="logo-header">
                <a href="#">BeFloral</a>
            </div>

            <ul>
                <li ${active.equals("Home") ? "class='active'": ""}><a href="Home">Home</a></li>
				<li ${active.equals("Products") ? "class='active'": ""}><a href="Products">Flowers</a></li>
                <li ${active.equals("Plants") ? "class='active'": ""}><a href="#">Plants</a></li>
                <li ${active.equals("Bouquet") ? "class='active'": ""}><a href="#">Bouquet</a></li>
                <li ${active.equals("Cart") ? "class='active'": ""}><a href="Cart">
                	<i class="fa fa-shopping-bag fa-2x"></i>
                	<span>(${cart == null ? 0:cart.getTotalProductsQuantity() })</span>
                </a>
                <c:if test="${user == null}">
                	<li ${active.equals("Login") ? "class='active'": ""}><a href="Login">Login</a></li>
                </c:if>
                <c:if test="${user != null}">
                	<li ${active.equals("User") ? "class='active'": ""}><a href="User">Profile</a></li>
                </c:if>
            </ul>
        </nav>
    </div>

	<!-- Navbar -->
	<main>
		<div class="container">
			<jsp:doBody />
		</div>
	</main>
	<!-- Footer colors #2e2e2e   and #252525  -->
	<div class="footer">
		<div class=footer-left>
			<a href="#facebook"><i class="fa fa-facebook fa-2x"></i></a>
			<a class="ml-2" href="#twitter"><i class="fa fa-twitter fa-2x"></i></a>
			<a class="ml-2" href="#instagram"><i class="fa fa-instagram fa-2x"></i></a>
		</div>
		<div class=footer-fill>

		</div>
		<!-- Copyright -->
		<div class="footer-right">
			<a class="anchorBefloral" href="/befloral">@2021 Copyright: Leanelda Group</a>
		</div>
	</div>
	<!-- Footer -->
</body>

</html>