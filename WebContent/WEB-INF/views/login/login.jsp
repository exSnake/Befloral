<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/product.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Login">
	<form action="Login" method="post" enctype="application/x-www-form-urlencoded">
		<div class="form-group">
			<label for="email">Email</label>
			<input type="email" class="form-control" name="email" id="email" aria-describedby="nameHelp"
				placeholder="Enter your email" required>
			<label for="password">Password</label>
			<input type="password" class="form-control" name="password" id="password" aria-describedby="nameHelp"
				placeholder="Enter your password" min="8" required>
			<input type="hidden" name="action" value="login">
		</div>
		<button type="submit" class="btn btn-success mt-2">Login</button>
	</form>

	<form action="Login" method="get">
		<div class="form-group">
			<input type="hidden" name="action" value="register">
		</div>
		<div>
			<p>Are you new to BeFloral?</p>
			<button type="submit" class="btn btn-success mt-2">Register Now!</button>
		</div>
	</form>
</z:layout>