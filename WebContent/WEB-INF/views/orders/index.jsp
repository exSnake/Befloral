<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/product.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Login">
<div class="container">
	<table>
	<thead>
		<tr>
			<th>Order Date</th>
			<th>Products Quantity</th>
			<th>Total Price</th>
			<th>Action</th>
		</tr>
	</thead>
	<c:forEach items="${orders}" var="bean">
		<tr>
			<td>${bean.getCreatedAt() }</td>
			<td>${bean.getTotalProducts() }</td>
			<td>${bean.getTotalPaid() }</td>
			<td>
				<a href="Order?action=view&id=${bean.getId()}">Dettagli</a>
			</td>
		</tr>
	</c:forEach>
	</table>
</div>
</z:layout>