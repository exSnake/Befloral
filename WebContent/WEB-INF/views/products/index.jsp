<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/product.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Products List">
	<form action="Products" method="get">
		<input type="hidden" name="action" value="create">
		<button type="submit" class="btn btn-primary mt-2">Add New
			Product</button>
	</form>
	<c:forEach items="${products}" var="bean">
		<!-- single product -->
		<div id="product-container">

			<!-- Start	Product details --> 
			<div class="product-details">
				<!-- 	Product Name -->
				<h1>${bean.getName()}</h1>
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
					</form>
					<!-- End Button buying -->
					<form action="Products" method="post">
						<input type="hidden" id="id" name="id" value="${bean.getId()}">
						<input type="hidden" id="action" name="action" value="delete">
						<button class="product-btn">
							<span class="Delete">Delete</span>
						</button>
					</form>
					<!-- Delete button -->
					<form action="Products" method="get">
						<input type="hidden" id="id" name="id" value="${bean.getId()}">
						<input type="hidden" name="action" value="update">
						<button type="submit" class="btn btn-primary mt-2">Update
							Product</button>
					</form>
					<!-- Update button -->
					<form action="Products" method="get">
						<input type="hidden" id="id" name="id" value="${bean.getId()}">
						<input type="hidden" name="action" value="show">
						<button type="submit" class="btn btn-primary mt-2">Show Single Product</button>		
					</form>
					<!-- Show Button -->
				</div>
			</div>

			<!-- 	End	Product details   -->
			<!-- 	Start product image & Information -->
			<div class="product-image">
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
			<!--  End product image  -->
		</div>
		<!-- end single product -->
	</c:forEach>
</z:layout>