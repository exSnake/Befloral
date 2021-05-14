
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/product.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Product View">
	<!-- Page to view details of a product -->
	<h1>Dati del prodotto</h1>
	<form action="Products method="get">
		<input type="hidden" name="id" id="id" value="${bean.getId()}"
			required> <input type="hidden" name="action" value="read">
	</form>
	<div id="product-container">

		<!-- Start	Product details -->
		<div class="product-details">
			<!-- 	Product Name -->
			<h1>${bean.getName()}</h1>
			<!-- 		<span class="hint new">New</span> -->
			<!-- 		<span class="hint free-shipping">Free Shipping</span> -->

			<!-- The most important information about the product -->
			<p class="information">"${bean.getDescription()}"</p>
			<div class="">
				<!-- Start Button buying -->
				<form action="Cart" method="post">
					<input type="hidden" id="id" name="id" value="${bean.getId()}">
					<input type="hidden" id="action" name="action" value="add">
					<button class="product-btn">
						<span class="price">${bean.getPriceToString()}" &euro;</span> <span
							class="shopping-cart"><i class="fa fa-shopping-cart"
							aria-hidden="true"></i></span> <span class="buy">Add to Cart</span>
					</button>
					<div class="product-image">
					<p> ${bean.getImagePath()}<p>
						<img alt="alt-product" src="${bean.getImagePath()}" />">
						
						<!-- 	product Information-->
						<div class="info">
							<h2>The Description</h2>
							<ul>
								<li><strong>Sun Needs: </strong>Full Sun</li>
								<li><strong>Soil Needs: </strong>Damp</li>
								<li><strong>Zones: </strong>9 - 11</li>
								<li><strong>Height: </strong>2 - 3 feet</li>
								<li><strong>Blooms in: </strong>MidSummer - Mid�Fall</li>
								<li><strong>Features: </strong>Tolerates heat</li>
							</ul>
						</div>
					</div>
				</form>
</z:layout>