<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:layout pageTitle="Register">
	<h1>Create new account</h1>
	<form action="Login" method="post" enctype="application/x-www-form-urlencoded">
		<div class="form-group">
			<label for="firstName">First Name</label>
			<input type="text" class="form-control" name="firstName" id="" firstName"" aria-describedby="nameHelp"
				placeholder="Enter Your FirstName" required>
		</div>

		<div class="form-group">
			<label for="lastName">LastName</label>
			<input type="text" class="form-control" name="lastName" id="lastName" aria-describedby="nameHelp"
				placeholder="Enter Your LastName" required>
		</div>

		<div class="form-group">
			<label for="email">Email</label>
			<input type="email" class="form-control" name="email" id="email" aria-describedby="nameHelp"
				placeholder="Enter Your Email" required>
		</div>

		<div class="form-check">
			<input class="form-check-input" type="radio" name="gender" id="gender_male" value="scolastic" value="male">
			<label class="form-check-label" for="gender_male">Male</label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="gender" id="gender_female" value="female">
			<label class="form-check-label" for="gender_female">Female</label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="gender" id="gender_undefined" value="undefined">
			<label class="form-check-label" for="gender_undefined">Undefined</label>
		</div>

		<div class="form-check">
			<input class="form-check-input" type="checkbox" name="subscribe" id="subscribe">
			<label class="form-check-label" for="subscribe">Subscribe to newsletter</label>
		</div>

		<div class="form-group mt-2">
			<label for="birthday">Birthday</label>
			<input type="date" class="form-control" name="birthday" id="birthday" aria-describedby="nameHelp"
				placeholder="Enter Your Birthday" required>
		</div>

		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" name="password" id="password" aria-describedby="nameHelp"
				placeholder="Enter your password" min="8" required>
		</div>

		<div class="form-group">
			<label for="confirmpassword">Confirm Password</label>
			<input type="password" class="form-control" name="confirmpassword" id="confirmpassword"
				aria-describedby="nameHelp" placeholder="Enter again your password" min="8" required>
		</div>

		<input type="hidden" name="action" value="register">

		<button type="submit" class="btn btn-success mt-2">Register Now!</button>

	</form>
</z:layout>