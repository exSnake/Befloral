<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:layout pageTitle="Register">

<h1>Create new account</h1>
	
	<form action="Login" method="post" enctype="application/x-www-form-urlencoded">
	
		<div class="form-group">
		    <label for="fristName">FristName</label>
		    <input type="text" class="form-control" name="fristName" id="fristName" aria-describedby="nameHelp" placeholder="Enter Your FristName" required>
		 </div>
	
		<div class="form-group">
		    <label for="lastName">LastName</label>
		    <input type="text" class="form-control" name="lastName" id="lastName" aria-describedby="nameHelp" placeholder="Enter Your LastName" required>
		 </div>

		<div class="form-group">
		    <label for="email">Email</label>
		    <input type="email" class="form-control" name="email" id="email" aria-describedby="nameHelp" placeholder="Enter Your Email" required>
		 </div>


		<fieldset class="form-group">
		<div class="form-group">
		    <label for="genere">Select gender</label><br>
		    Male <input type="radio" class="form-control" name="gender" id="gender" aria-describedby="nameHelp" value="0">
		    Female <input type="radio" class="form-control" name="gender" id="gender" aria-describedby="nameHelp" value="1">
		    Not answer<input type="radio" class="form-control" name="gender" id="gender" aria-describedby="nameHelp" value="2">
		 </div>
	     </fieldset>
		
		<div class="form-group">
		    <label for="newsletter">Newsletter</label>
		    <input type="email" class="form-control" name="newsletter" id="newsletter" aria-describedby="nameHelp" placeholder="Enter Your Newsletter" required>
		 </div>

		<div class="form-group">
		    <label for="birthday">Birthday</label>
		    <input type="date" class="form-control" name="birthday" id="birthday" aria-describedby="nameHelp" placeholder="Enter Your Birthday" required>
		 </div>


		<div class="form-group">
		<label for="password">Password</label>
		<input type="password" class="form-control" name="password" id="password"
		 aria-describedby="nameHelp" placeholder="Enter your password" min="8" required>
		</div>
		
		<div class="form-group">
		<label for="confirmpassword">Password</label>
		<input type="password" class="form-control" name="confirmpassword" id="confirmpassword"
		 aria-describedby="nameHelp" placeholder="Enter again your password" min="8" required>
		</div>
		
		<input type="hidden" name="action" value="register">
		
		<button type="submit" class="btn btn-success mt-2">Regiser Now!</button>
	
</form>

</z:layout>