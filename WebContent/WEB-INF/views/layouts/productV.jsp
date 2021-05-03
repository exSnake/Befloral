<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 
 	<div class= "container">
 		<h1 class ="lg-title"> text </h1>
 		<p class="text-light"> text.</p>
 		
 		<div class="product-items">
 			<!-- single product -->
 			<div class="product-content"> 
				<div class=product-img>
					<img src="images/first_product.jpg" alt="if photo not exist we put some descrition in alt :)"  width="200" height="250">
				</div>
				<div class="product-btns">
					<button type="button" class="btn-cart">Add to Cart
						<span><i class= "fas fa-plus"></i></span>
						</button>
						<button type="button" class="btn-buy">Buy Now
							<span><i class= "fas fa-shopping-cart"></i></span>
						</button>
				</div>
				
				<div class="product info">
					<div class="product-info-top">
					<h2 class="sm-title"> lifestyle</h2>
						<div class= "rating">
						<span><i class="fas fa-star"></i></span>
						<span><i class="fas fa-star"></i></span>
						<span><i class="fas fa-star"></i></span>
						<span><i class="fas fa-star"></i></span>
						<span><i class="fas fa-star"></i></span>
						</div>
					</div>
					<a href="#" class="product-name"> Flower for the Boss,new product</a>
					<p class="product-price">10$</p>
					<p class="product-price">7.5$</p>	
				</div>
				<div class="off-info">
					<h2 class="sm-title">25% off</h2>
				</div>
			</div>
			<!-- end of single products -->
 		</div>
	</div>
</body>
</html>