<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.befloral.beans.ProductBean"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link
	href="<%=getServletContext().getContextPath()%>/resources/css/product.css"
	rel="stylesheet" type="text/css">
<title>Products</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
	<div class="container">
		<%
		Collection<ProductBean> products = (Collection<ProductBean>) request.getAttribute("products");
		if (products != null && products.size() != 0) {
			Iterator<?> it = products.iterator();
			while (it.hasNext()) {
				ProductBean bean = (ProductBean) it.next();
		%>
		<!-- single product -->
		<div id="product-container">

			<!-- Start	Product details -->
			<div class="product-details">

				<!-- 	Product Name -->
				<h1><%=bean.getName()%></h1>
				<!-- 		<span class="hint new">New</span> -->
				<!-- 		<span class="hint free-shipping">Free Shipping</span> -->
				<!-- 		the Product rating -->
				<span class="hint-star star"> <i class="fa fa-star"
					aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i>
					<i class="fa fa-star" aria-hidden="true"></i> <i
					class="fa fa-star-half-o" aria-hidden="true"></i> <i
					class="fa fa-star-o" aria-hidden="true"></i>
				</span>

				<!-- The most important information about the product -->
				<p class="information">
					"
					<%=bean.getDescription()%>
					"
				</p>

				<!-- 		Control -->
				<div class="control">
					<!-- Start Button buying -->
					<form action="Cart" method="post">
						<input type="hidden" value="<%=bean.getId()%>">
						<button class="btn">
							<!-- 		the Price -->
							<span class="price"><%=bean.getPriceToString() %> &euro;</span>
							<!-- 		shopping cart icon-->
							<span class="shopping-cart"><i class="fa fa-shopping-cart"
								aria-hidden="true"></i></span>
							<!-- 		Buy Now / ADD to Cart-->
							<span class="buy">Buy Now</span>
						</button>
					</form>
					<!-- End Button buying -->
				</div>
			</div>
			<!-- 	End	Product details   -->

			<!-- 	Start product image & Information -->

			<div class="product-image">
				<%
				String path = getServletContext().getRealPath("/resources/images/products/" + bean.getId() + ".jpg");
				File file = new File(path);
				if (file.exists()) {
				%>
				<img alt="alt-product"
					src="<%=getServletContext().getContextPath()%>/resources/images/products/<%=bean.getId()%>.jpg">
				<%
				} else {
				%>
				<img alt="alt-product"
					src="<%=getServletContext().getContextPath()%>/resources/images/products/error.png">
				<%
				}
				%>
				<!-- 	product Information-->
				<div class="info">
					<h2>The Description</h2>
					<ul>
						<li><strong>Sun Needs: </strong>Full Sun</li>
						<li><strong>Soil Needs: </strong>Damp</li>
						<li><strong>Zones: </strong>9 - 11</li>
						<li><strong>Height: </strong>2 - 3 feet</li>
						<li><strong>Blooms in: </strong>MidâSummer - MidâFall</li>
						<li><strong>Features: </strong>Tolerates heat</li>
					</ul>
				</div>
			</div>
			<!--  End product image  -->
		</div>
		<!-- end single product -->
		<%
			}
		}
		%>
	</div>
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
</body>
</html>