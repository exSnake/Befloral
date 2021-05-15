<%@ taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<z:layout pageTitle="Product View">
	<h1>Update product </h1>
	<form action="Products" method="post">
		<input type="hidden" name="id" id="id" value="${bean.getId()}"required>
		<input type="hidden" name="action" value="put">
		<div class="form-group">
		    <label for="name">Name</label>
		    <input type="text" class="form-control" name="name" id="name" aria-describedby="nameHelp" value="${bean.getName()}">
		    <small id="nameHelp" class="form-text text-muted">Enter a descriptive small name</small>
		 </div>
	
		<div class="form-group">
			<label for="description">Description</label>
			<input type="text" class="form-control" name="description" id="description" aria-describedby="descriptionHelp" placeholder="Enter Product Description" value="${bean.getDescription()}">
			<small id="descriptionHelp" class="form-text text-muted">Enter a description for the product</small>
		</div>

		<div class="form-group">
			<label for="shortDescription">Short Description</label>
			<input type="text" class="form-control" name="shortDescription" id="shortDescription" aria-describedby="shortDescriptionHelp" placeholder="Enter Product Short Description" value="${bean.getShortDescription()}">
			<small id="shortDescriptionHelp" class="form-text text-muted">Enter a short Description</small>
		</div>

		<div class="form-group">
			<label for="metaDescription">Meta Description</label>
			<input type="text" class="form-control" name="metaDescription" id="metaDescription" aria-describedby="metaDescriptionHelp" placeholder="Enter Product meta Description" value="${bean.getMetaDescription()}">
			<small id="metaDescriptionHelp" class="form-text text-muted">Enter a meta description</small>
		</div>

		<div class="form-group">
			<label for="metaKeyword">Meta Keyword</label>
			<input type="text" class="form-control" name="metaKeyword" id="metaKeyword" aria-describedby="metaKeywordHelp" placeholder="Enter Product meta keyword" value="${bean.getMetaKeyword()}">
			<small id="metaKeywordHelp" class="form-text text-muted">Enter a meta keyword</small>
		</div>
	
		<label for="price">Price</label>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">&euro;</span>
			</div>
			<input type="text" class="form-control" aria-label="Amount (to the nearest dollar)" name="price" id="price" value="${bean.getPrice()}">
		</div>

		<div class="form-group">
			<label for="weight">Weight</label>
			<input type="number" class="form-control" name="weight" id="weight" aria-describedby="weightHelp" step="0.01" placeholder="1,4" required value="${bean.getWeight()}">
			<small id="weightHelp" class="form-text text-muted">Enter the product weight</small>
		</div>
		
		<label for="discount">Discount</label>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">%</span>
			</div>
			<input type="text" class="form-control" aria-label="Amount (in percentage)" name="discount" id="discount" required value="${bean.getDiscount()}">
		</div>
		
		<div class="form-group">
			<label for="onSale">Products On Sale</label>
			<input type="number" class="form-control" name="onSale" id="onSale" aria-describedby="onSaleHelp" placeholder="9" value="${bean.getOnSale()}">
			<small id="onSaleHelp" class="form-text text-muted">Enter the number of product on sale</small>
		</div>
		
		<div class="form-group">
			<label for="quantity">Quantity</label>
			<input type="number" class="form-control" name="quantity" id="quantity" aria-describedby="quantityHelp" placeholder="120" value="${bean.getQuantity()}">
			<small id="quantityHelp" class="form-text text-muted">Enter the number of available products</small>
		</div>
		
		<div class="form-check mb-3">
			<input class="form-check-input" type="checkbox" value="" name="available" id="available" checked>
			<label class="form-check-label" for="available">
				Is Available
			</label>
		</div>
		<button type="submit" class="btn btn-success mt-2">Update Product</button>
		<c:if test = "${error != null}">
			<div class="error">	
				<p> ${error} </p>
			</div>
		</c:if>
		</form>
</z:layout>