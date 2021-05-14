<%@ taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<z:layout pageTitle="Product View">
	<!-- Page to view details of a product -->
	<h1>Dati del prodotto</h1>
	<form action="Products" method="post">
	<input type="hidden" name="id" id="id" value="${bean.getId()}"required>
	<input type="hidden" name="action" value="read">
	</form>
	<table style="width: 100%">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Description<th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Total Price</th>
			
		</tr>
		<tr>
			<td><c:out value="${bean.getId()}"/></td>
			<td><c:out value="${bean.getQuantity()}"/></td>
			<td><c:out value="${bean.getDescription()}"/></td>
			<td><c:out value="${bean.getProduct().getPriceToString()}"/></td>
			<td><c:out value="${bean.getTotalPriceToString()}"/>/td>
		</tr>
	</table>
</z:layout>