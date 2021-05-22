<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<z:layout pageTitle="Login">
	<h1>Inserisci i dati per la spedizione</h1>
	<form class="mt-4" action="User" method="post">
		<input type="hidden" name="action" value="createAddress">
		<div class="row">
			<div class="col">
				<div class="form-group">
					<label for="firstName">First Name</label>
					<input type="text" class="form-control" name="firstName" id="firstName" aria-describedby="firstNameHelp"
						placeholder="Mario..." required>
					<small id="firstNameHelp" class="form-text text-muted">Destination first name</small>
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for="lastName">Last Name</label>
					<input type="text" class="form-control" name="lastName" id="lastName" aria-describedby="lastNameHelp"
						placeholder="Rossi..." required>
					<small id="lastNameHelp" class="form-text text-muted">Destination last name</small>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-8">
				<div class="form-group">
					<label for="address">Address</label>
					<input type="text" class="form-control" name="address" id="address" aria-describedby="addressHelp"
						placeholder="Via Roma, 12/A..." required>
					<small id="lastNameHelp" class="form-text text-muted">Destination address and civic number</small>
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for=postalCode>CAP</label>
					<input type="text" class="form-control" name="postalCode" id="postalCode" aria-describedby="postalCodeHelp"
						placeholder="84100" required>
					<small id="postalCodeHelp" class="form-text text-muted">Postal code of destination</small>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-8">
				<div class="form-group">
					<label for="city">City</label>
					<input type="text" class="form-control" name="city" id="city" aria-describedby="cityHelp"
						placeholder="Milano..." required>
					<small id="lastNameHelp" class="form-text text-muted">Destination city</small>
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for="province">Province</label>
					<input type="text" class="form-control" name="province" id="province" aria-describedby="provinceHelp"
						placeholder="MI..." required>
					<small id="provinceHelp" class="form-text text-muted">Destination province abbreviation</small>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="phone"></label>
			<input type="text" class="form-control" name="phone" id="phone" aria-describedby="phoneHelp"
				placeholder="3929009...">
			<small id="phoneHelp" class="form-text text-muted">Phone number to refer</small>
		</div>
		
		<div class="form-group">
			<label for="info"></label>
			<input type="text" class="form-control" name="info" id="info" aria-describedby="infoHelp"
				placeholder="Cancello verde di fronte alla banca...">
			<small id="infoHelp" class="form-text text-muted">Other info for the delivery</small>
		</div>
		
		<div class="form-check mb-3">
			<input class="form-check-input" type="checkbox" value="" name="save" id="save">
			<label class="form-check-label" for="save">
				Save this address
			</label>
		</div>
		
		<div class="form-group">
			<label for="alias"></label>
			<input type="text" class="form-control" name="alias" id="alias" aria-describedby="aliasHelp"
				placeholder="Home, Office..." disabled>
			<small id="Help" class="form-text text-muted">Enter an alias to save this address</small>
		</div>
		
		<hr>
		
		<div class="form-check mb-3">
			<input class="form-check-input" type="checkbox" value="" name="isGift" id="isGift">
			<label class="form-check-label" for="isGift">
				Is a Gift
			</label>
		</div>
		
		<div class="form-group">
			<label for="giftMessage"></label>
			<input type="text" class="form-control" name="giftMessage" id="giftMessage" aria-describedby="giftMessageHelp"
				placeholder="Tanti auguri di buon compleano..." disabled>
			<small id="giftMessageHelp" class="form-text text-muted">Insert a gift message</small>
		</div>
		
		
		<button class="btn btn-success" type="submit">Save</button>
		
	</form>
	
	<script>
		$("#save").change(function() {
		    if(this.checked) {
		    	$('#alias').removeAttr("disabled");
		    	$('#alias').prop("required", true);
		    } else {
		    	$('#alias').prop("disabled", true);
		    	$('#alias').removeAttr("required");
		    }
		});
		
		$("#isGift").change(function() {
		    if(this.checked) {
		    	$('#giftMessage').removeAttr("disabled");
		    	$('#giftMessage').prop("required", true);
		    } else {
		    	$('#giftMessage').prop("disabled", true);
		    	$('#giftMessage').removeAttr("required");
		    }
		});
	</script>
	
</z:layout>