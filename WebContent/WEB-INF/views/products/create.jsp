<%@ taglib prefix="z" tagdir="/WEB-INF/tags"%>
<z:layout pageTitle="Product View">
	<h1>Create new Product</h1>
	<form action="Products" method="post">
		<div class="form-group">
		    <label for="name">Name</label>
		    <input type="text" class="form-control" id="name" aria-describedby="nameHelp" placeholder="Enter Product Name">
		    <small id="nameHelp" class="form-text text-muted">Enter a descriptive small name</small>
		 </div>
	
		<div class="form-group">
			<label for="description">Description</label>
			<input type="text" class="form-control" id="description" aria-describedby="descriptionHelp" placeholder="Enter Product Description">
			<small id="descriptionHelp" class="form-text text-muted">Enter a description for the product</small>
		</div>

		<div class="form-group">
			<label for="shortDescription">Short Description</label>
			<input type="text" class="form-control" id="shortDescription" aria-describedby="shortDescriptionHelp" placeholder="Enter Product Short Description">
			<small id="shortDescriptionHelp" class="form-text text-muted">Enter a short Description</small>
		</div>

		<div class="form-group">
			<label for="metaDescription">Meta Description</label>
			<input type="text" class="form-control" id="metaDescription" aria-describedby="metaDescriptionHelp" placeholder="Enter Product meta Description">
			<small id="metaDescriptionHelp" class="form-text text-muted">Enter a meta description</small>
		</div>

		<div class="form-group">
			<label for="metaKeyword">Meta Keyword</label>
			<input type="text" class="form-control" id="metaKeyword" aria-describedby="metaKeywordHelp" placeholder="Enter Product meta keyword">
			<small id="metaKeywordHelp" class="form-text text-muted">Enter a meta keyword</small>
		</div>
	
		<label for="price">Price</label>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">&euro;</span>
			</div>
			<input type="text" class="form-control" aria-label="Amount (to the nearest dollar)" id="price">
		</div>

		<div class="form-group">
			<label for="weight">Weight</label>
			<input type="number" class="form-control" id="weight" aria-describedby="weightHelp" placeholder="1.4">
			<small id="weightHelp" class="form-text text-muted">Enter the product weight</small>
		</div>
		
		<label for="discount">Discount</label>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">%</span>
			</div>
			<input type="text" class="form-control" aria-label="Amount (in percentage)" id="discount">
		</div>
		
		<div class="form-group">
			<label for="onSale">Products On Sale</label>
			<input type="number" class="form-control" id="onSale" aria-describedby="onSaleHelp" placeholder="9">
			<small id="onSaleHelp" class="form-text text-muted">Enter the number of product on sale</small>
		</div>
		
		<div class="form-group">
			<label for="quantity">Quantity</label>
			<input type="number" class="form-control" id="quantity" aria-describedby="quantityHelp" placeholder="120">
			<small id="quantityHelp" class="form-text text-muted">Enter the number of available products</small>
		</div>
		
		<div class="form-check mb-3">
			<input class="form-check-input" type="checkbox" value="" id="available" checked>
			<label class="form-check-label" for="available">
				Is Available
			</label>
		</div>

		<button type="submit" class="btn btn-success mt-2">Create</button>
	</form>
</z:layout>