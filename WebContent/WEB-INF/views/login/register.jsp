<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<z:layout pageTitle="Register">
	<h1>Create new account</h1>
	<form action="Login" method="post"
		enctype="application/x-www-form-urlencoded"
		onsubmit="event.preventDefault(); myfunction(this)">
		<input type="hidden" name="action" value="register">
		<div class="form-group">
			<label for="firstName">First Name</label> <input type="text"
				class="form-control" name="firstName" id=""
				firstName"" aria-describedby="nameHelp"
				placeholder="Enter Your FirstName" required>
		</div>

		<div class="form-group">
			<label for="lastName">LastName</label> <input type="text"
				class="form-control" name="lastName" id="lastName"
				aria-describedby="nameHelp" placeholder="Enter Your LastName"
				required>
		</div>

		<div class="form-group">
			<label for="email">Email</label> <input type="email"
				class="form-control" name="email" id="email"
				aria-describedby="nameHelp" placeholder="Enter Your Email" required>
		</div>

		<div class="form-check">
			<input class="form-check-input" type="radio" name="gender"
				id="gender_male" value="scolastic" value="Male"> <label
				class="form-check-label" for="gender_male">Male</label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="gender"
				id="gender_female" value="Female"> <label
				class="form-check-label" for="gender_female">Female</label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="gender"
				id="gender_undefined" value="Undefined" checked> <label
				class="form-check-label" for="gender_undefined">Undefined</label>
		</div>

		<div class="form-check">
			<input class="form-check-input" type="checkbox" name="subscribe"
				id="subscribe"> <label class="form-check-label"
				for="subscribe">Subscribe to newsletter</label>
		</div>

		<div class="form-group mt-2">
			<label for="birthday">Birthday</label> <input type="date"
				class="form-control" name="birthday" id="birthday"
				aria-describedby="nameHelp" placeholder="Enter Your Birthday"
				required>
		</div>

		<div class="form-group">
			<label for="password">Password</label> <input type="password"
				class="form-control" name="password" id="password"
				aria-describedby="nameHelp" placeholder="Enter your password"
				min="8" required>
		</div>

		<div class="form-group">
			<label for="confirmpassword">Confirm Password</label> <input
				type="password" class="form-control" name="confirmpassword"
				id="confirmpassword" aria-describedby="nameHelp"
				placeholder="Enter again your password" min="8" required>
		</div>

		<button type="submit" class="btn btn-success mt-2">Register
			Now!</button>
	</form>
	<script>
	
	function myfunction(frm) {
		if(valiDate()){
			if(validateEmail()) {
				if (validatePasswordComplexity()) {
					if(validatePassword()) {
						frm.submit();
					} else {
						alert("Password don't match");
					}
				} else{
					alert("Password must be at least 8 character and must contain at least One special character,One number,One UpperCase char and One LowerCase char");
				}
			} else {
				alert("You have entered an invalid email address!");
			}
		} else {
			alert("Invalid Birthday");
		}	
	}
	
	
	function valiDate(){
		var valiDate=/^\d{4}[./-]\d{2}[./-]\d{2}$/;
		var date=document.getElementById("birthday");
		if (!date.value.match(valiDate)) {
			return false;
		}
		var foo= new Date();
		foo.setFullYear(foo.getFullYear()-15);
		var bar= new Date();
		bar.setFullYear(bar.getFullYear() - 120);
		var birthday=document.getElementById("birthday");
		if(birthday<bar || birthday.value>foo.value) {
			return false;
		} else {
			return true;
		}
	}
	
	function validateEmail() {
		var uemail=document.getElementById("email");	
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if (uemail.value.match(mailformat)) {
			return true;
		} else {
			uemail.focus;
			return false;
		}
	}
	function validatePasswordComplexity(){
		var pass=document.getElementById("password");
		var passformat=/(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;
		if (pass.value.match(passformat)) {
			return true;
		} else {
			pass.focus();
			return false;
		}
	}
	function validatePassword(){
		var pass=document.getElementById("password");
		var confirmpass=document.getElementById("confirmpassword");
			if (pass.value==confirmpass.value) {
				return true;
			} else {
			pass.focus();
			return false;
		}	
	}
	
	</script>
</z:layout>