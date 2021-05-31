<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="<c:url value="/resources/css/admin.css"/>" rel="stylesheet" type="text/css">
<z:layout pageTitle="Admin Product View">
	<a href="<c:url value="/Admin/Products?action=create"/>">
		<button type="submit" class="btn btn-primary mt-2">Add New Product</button>
	</a>
	<c:forEach items="${products}" var="prod">
		<div class="admin-order">
		<div class="body">
		 	<p><b>ID ${prod.getId() }</b></p>
		 	<p>Name ${prod.getName() }</p>
		 	<p>Short Description: ${prod.getShortDescription() }</p>
		 	<p>Price: ${prod.getPrice() }</p>
		 	<p>Weight: ${prod.getWeight() }</p>
		 	<p>Available: ${prod.isAvailable() ? "Yes": "No"}</p>
		 	<p>Quantity: ${prod.getQuantity() }</p>
	 	</div>
	 	<div class="actions">
	 		<a href="<c:url value="/Admin/Products?action=view&id=${prod.getId()}"/>" class="btn btn-success">View</a>
    		<a href="<c:url value="/Admin/Products?action=edit&id=${prod.getId()}"/>" class="btn btn-primary">Edit</a>
    		<form action="<c:url value="/Admin/Products"/>" method="post">
    			<input type="hidden" name="action" id="action" value="delete">
    			<input type="hidden" name="id" id="id" value="${prod.getId()}">
    			<button class="btn btn-danger" style="width: 100%">Delete</button>
    		</form>
    	</div>
	</div>
	</c:forEach>
</z:layout>

