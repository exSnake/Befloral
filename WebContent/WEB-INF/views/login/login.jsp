<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:layout pageTitle="Login">




	<form action="Login" method="post" enctype="application/x-www-form-urlencoded">
	
	<div class="form-group">
	
	<label for="email">Email</label>
	<input type="email" class="form-control" name="email" id="email" aria-describedby="nameHelp" placeholder="Enter your email" required>
	
	<label for="password">Password</label>
	<input type="password" class="form-control" name="password" id="password"
	 aria-describedby="nameHelp" placeholder="Enter your password" min="8" required>
	
	<input type="hidden" name="action" value="loggin">
	</div>
	
		<button type="submit" class="btn btn-success mt-2">Loggin</button>
	</form>

	
	
	<form action="Login" method="post">
	<div class="form-group">
	<input type="hidden" name="action" value="sendToRegister">
	</div>
	Are you new to Befloral? <button type="submit" class="btn btn-success mt-2">Join Us!</button>
	</form>
	



</z:layout>