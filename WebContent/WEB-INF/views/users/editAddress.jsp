<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<z:layout pageTitle="Edit Address">
<h1>Edit Address N. ${bean.getId()}</h1>

<form class="mt-4" action="User" method="post" onsubmit="event.preventDefault(); myfunction(this)">
		<input type="hidden" name="action" value="editAddress">
		<input type="hidden" name="id" value="${bean.getId()}">
			<div class="row">
			<div class="col">
				<div class="form-group">
					<label for="firstName">First Name</label>
					<input type="text" class="form-control" name="firstName" id="firstName" 
					aria-describedby="firstNameHelp" value="${bean.getFirstName()}" required>
					<small id="firstNameHelp" class="form-text text-muted">Destination first name</small>
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for="lastName">Last Name</label>
					<input type="text" class="form-control" name="lastName" id="lastName" aria-describedby="lastNameHelp" value="${bean.getLastName()}" required>
					<small id="lastNameHelp" class="form-text text-muted">Destination last name</small>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-8">
				<div class="form-group">
					<label for="address">Address</label>
					<input type="text" class="form-control" name="address" id="address" aria-describedby="addressHelp" value="${bean.getAddress()}" required>
					<small id="lastNameHelp" class="form-text text-muted">Destination address and civic number</small>
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for=postalCode>CAP</label>
					<input type="text" class="form-control" name="postalCode" id="postalCode" aria-describedby="postalCodeHelp" value="${bean.getPostalCode()}" required>
					<small id="postalCodeHelp" class="form-text text-muted">Postal code of destination</small>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-8">
				<div class="form-group">
					<label for="city">City</label>
					<input type="text" class="form-control" name="city" id="city" aria-describedby="cityHelp" value="${bean.getCity()}"required>
					<small id="lastNameHelp" class="form-text text-muted">Destination city</small>
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for="province">Province</label>
					<input type="text" class="form-control" name="province" id="province" aria-describedby="provinceHelp" value="${bean.getProvince()}"required>
					<small id="provinceHelp" class="form-text text-muted">Destination province abbreviation</small>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="phone"></label>
			<input type="text" class="form-control" name="phone" id="phone" aria-describedby="phoneHelp"value="${bean.getPhone()}">
			<small id="phoneHelp" class="form-text text-muted">Phone number to refer</small>
		</div>
		
		<div class="form-group">
			<label for="info"></label>
			<input type="text" class="form-control" name="info" id="info" aria-describedby="infoHelp" value="${bean.getInfo()}">
			<small id="infoHelp" class="form-text text-muted">Other info for the delivery</small>
		</div>
		
		<hr>
		
		<div class="form-check mb-3">
			<input class="form-check-input" type="checkbox" value="" name="preferred" id="preferred" <c:if test="${bean.isPreferred()}">checked</c:if> >
			<label class="form-check-label" for="preferred" >
				Preferred
			</label>
		</div>
		
		<div class="form-group">
			<label for="alias"></label>
			<input type="text" class="form-control" name="alias" id="alias" aria-describedby="aliasHelp"
				placeholder="Home, Office..." value="${bean.getAlias()}"required>
			<small id="Help" class="form-text text-muted">Enter an alias for this address</small>
		</div>
		
		<hr>		
		
		<button class="btn btn-success" type="submit">Save</button>
		
	</form>
	<script>

	function myfunction(frm) {
		if(validatecap()) {
			if(phonenumber()) {
				frm.submit();
			} else {
				alert("The numeric input is not valid");
			}
		} else {
			alert("Cap is not valid");
		}
	}
	
	function validatecap() {
		var valicap=/^\d{5}$/;
		var postalCode=document.getElementById("postalCode");
		if(postalCode.value.match(valicap)) {
			return true;
		} else {
			postalCode.focus;
			return false;
		}
	}
	
	function phonenumber() {
		var input=document.getElementById("phone");
		var phoneno = /^\d{10}$/;
		if(input.value.match(phoneno)) {
			return true;
		} else {
			input.focus;
			return false;
		}
	}
</script>

</z:layout>