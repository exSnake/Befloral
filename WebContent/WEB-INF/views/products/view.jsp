<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/product-view.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Product View">
	<!-- Page to view details of a product -->	
	<div class="container">
		<div class="left">
			<div class="images-small">
				<img src="https://picsum.photos/100/100">
				<img src="https://picsum.photos/100/100">
				<img src="https://picsum.photos/100/100">
				<img src="https://picsum.photos/100/100">
				<img src="https://picsum.photos/100/100">
			</div>
			<div class="images-big">
				<img src="https://picsum.photos/600/600">
			</div>
		</div>
		<div class="right">
			<div class="product-title">
				<h1>${bean.getName()}</h1>
			</div>
			<div class="product-description">
				<p>${bean.getDescription()}</p>
			</div>
			
			<div class="product-action">
				<form action="Cart" method="post">
					<input type="hidden" name="action" value="add">
					<input type="hidden" name="id" value="${bean.getId()}">
					<button type="submit" class="btn btn-success">Add to cart</button>
				</form> 
				<form action="Products" method="post">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="id" value="${bean.getId()}">
					<button type="submit" class="btn btn-danger">Delete Product</button>
				</form> 
				<a href="Products?action=edit&id=${bean.getId()}">
					<button type="submit" class="btn btn-primary">Edit Product</button>
				</a>
			</div>
		</div>
	</div>	
</z:layout>