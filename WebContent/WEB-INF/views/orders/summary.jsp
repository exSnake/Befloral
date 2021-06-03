<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/user-profile.css" rel="stylesheet" type="text/css">
<link href="resources/css/summary.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Checkout">
	<div id="notification" class="notification is-hidden"><button class="delete" onclick="closeNotification()">X</button><span id="notification-text">Test</span></div>
	<h1>Order Summary</h1>
	<div class="summary">
		<div class="left">
			<div class="address">
				<div class="num">
					<h2>1.</h2>
				</div>
				<div class="head">
					<h2>Delivery Address</h2>
				</div>
				
				<div id="checkout-address" class="body">
					<c:if test="${address != null}">
						<p>${address.getFirstName() } ${address.getLastName() }</p>
						<p>${address.getAddress()}</p>
						<p>${address.getPostalCode() } ${address.getCity()} (${address.getProvince()})</p>
						<p>${address.getInfo()}</p>
					</c:if>
					<c:if test="${address == null }">
						<p>No address, add one</p>
					</c:if>
				</div>
				<div class="actions">
					<button id="btn-addressModal" class="button is-success">Select</button>
				</div>
			</div>
			<hr>
			<div class="payment">
				<div class="num">
					<h2>2.</h2>
				</div>
				<div class="head">
					<h2>Payment</h2>
				</div>
				<div class="body">
					<p><b>Mastercard</b> termina con **62</p>
				</div>
				<div class="actions">
					<ul>
						<li><a class="links" href="#">Add</a></li>
					</ul>
				</div>
			</div>
			<hr>
			<div class="order-items">
				<div class="order-details">
					<div class="flex-spaced">
						<div class="flex">
							<img class="order-details-image"
								src="https://source.unsplash.com/120x120/?sig=1&bouquet">
							<ul>
								<li class="delivery-date">Boquet di rose rosse</li>
								<li class="bouquet-name">Boquet di rose rosse</li>
								<li class="bouquet-price mobile-hidden">
									300,00 &euro;
								</li>
								<li class="order-number">Short description</li>
								<li class="invoice-links">
									<a href="#" target="_blank" class="download-link"></a>
									<a href="#" class="generate-invoice"></a>
								</li>
							</ul>
						</div>
						<div class="flex">
							<ul>
								<li><a class="links" href="#">Edit</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>	
			<div class="row buy"></div>
		</div>
		<div class="right">
			<form action="Orders" method="post">
				<input type="hidden" name="action" value="buy"/>
				<div class="right-buy">
					<button class="links" type="submit">Buy Now</button>
				</div>
				<hr>
				<h2>Order Summary</h2>
				<div>
					<div><p>Products</p></div>
					<div><p>13.90 &euro;</p></div>
					
				</div>
				<div>
					<div><p>Shipping cost</p></div>
					<div><p>0.00 &euro;</p></div>
				</div>
				<hr>
				<div>
					<div><h2>Total order</h2></div>
					<div><h2>13.90 &euro;</h2></div>
				</div>
				<h3>Tax fees included</h3>
				<div class="form-check mb-4">
					<input class="form-check-input" type="checkbox" value="" name="isGift" id="isGift">
					<label class="form-check-label" for="isGift">
						Is a Gift
					</label>
				</div>
			
				<div class="form-group">
					<label for="giftMessage"></label>
					<input type="text" class="form-control" name="giftMessage" id="giftMessage" aria-describedby="giftMessageHelp"
						placeholder="Tanti auguri di buon compleano..." disabled>
				</div>
			</form>
		</div>
		
		
	</div>
	<div id="addressModal" class="modal">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
				  <p class="modal-card-title">Select an Address</p>
				  <button id="btn-addressModalClose" class="delete" aria-label="close"></button>
				</header>
				<section id="body-addressModal" class="modal-card-body">
					
	 			</section>
				<footer class="modal-card-foot">
					<a class="button is-success" href="<c:url value="/User?action=createAddress"/>">Create New Address</a>
					<button id="btn-addressModalCancel" class="button">Cancel</button>
				</footer>
			</div>
		</div>
	
	<script>
	function closeNotification() {
		$("#notification").addClass("is-hidden");
	}
	$(document).ready(function() {
		$('#btn-addressModal').on("click", function (){
			$.get("Api/User", { action:"getAddresses" }, function(data) {
				$('#body-addressModal').empty();
				$(data).each((e, k) => {
					var card="";
					card += "					<header class=\"card-header\">";
					card += "					    <p class=\"card-header-title\">";
					card += "					      <i class=\"fa fa-home mr-2\"> <\/i> "+ k.alias;
					card += "					    <\/p>";
					card += "					<\/header>";
					card += "					<div class=\"card-content\">";
					card += "					  <div class=\"content\">";
					card += "					  <div class=\"columns is-vcentered\">";
					card += "					  	<div class=\"column is-four-fifths\">";
					card += "						    " + k.firstName + " " + k.lastName;
					card += "						    <br>";
					card += "						    " + k.address;
					card += "						    <br>";
					card += "						    "+ k.postalCode +" "+ k.city +" ("+ k.province +")";
					card += "					    <\/div>";
					card += "					    <div class=\"column\">";
					card += "					    	<button id='address_"+k.id+"' class=\"button is-success\">Select<\/button>";
					card += "					    <\/div>";
					card += "					  <\/div>";
					card += "					  <\/div>";
					card += "					<\/div>";
					$('#body-addressModal').append(card);
					
					$('#address_'+k.id).on("click", function() {
						$.post("Api/Orders", { action: "setAddress", address:JSON.stringify(k) }, function(data) {
							var addr="";
							addr += "						<p>"+k.firstName+" "+k.lastName+"<\/p>";
							addr += "						<p>"+k.address+"<\/p>";
							addr += "						<p>"+k.postalCode+" "+k.city+" ("+k.province+")<\/p>";
							addr += "						<p><\/p>";
							$('#checkout-address').empty();
							$('#checkout-address').append(addr);
							$('#addressModal').removeClass('is-active');
						})
						.fail(function() {
							$("#notification-text").text("Error while adding address");
							$("#notification").addClass("is-danger");
							$("#notification").removeClass("is-hidden");
						})
					});
					
				});
				
			});
			$('#addressModal').addClass("is-active");
		});
		
		$('#btn-addressModalCancel, #btn-addressModalClose').on("click", function(){
			$('#addressModal').removeClass("is-active");
		});
	});
	
	$("#isGift").change(function() {
	    if(this.checked) {
	    	$('#giftMessage').removeAttr("disabled");
	    	$('#giftMessage').prop("required", this.checked);
	    } else {
	    	$('#giftMessage').prop("disabled", true);
	    	$('#giftMessage').removeAttr("required");
	    }
	});
	</script>
</z:layout>