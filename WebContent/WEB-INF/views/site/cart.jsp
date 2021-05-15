<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:layout pageTitle="Cart">
	<table style="width: 100%">
		<tr>
			<th>Name</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Total Price</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${cart.getProducts()}" var="bean">
		<tr>
			<td></td>
			<td>${bean.getQuantity()}</td>
			<td>${bean.getProduct().getPriceToString()}</td>
			<td>${bean.getTotalPriceToString()}</td>
			<td>
				<form action="Cart" method="post">
					<input type="hidden" id="id" name="id" value="${bean.getId()}">
					<input type="hidden" id="action" name="action" value="remove">
					<button class="btn btn-danger">Delete</button>
				</form>
				<form action="Cart" method="post">
					<input type="hidden" id="id" name="id" value="${bean.getId()}">
					<input type="hidden" id="action" name="action" value="update">
					 <input type="number" id="quantity" name="quantity" min="1" value="${bean.getQuantity()}">
					<button class="btn btn-info">Update</button>
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	<form action="Cart" method="post">
		<input type="hidden" id="action" name="action" value="removeAll">
		<button class="btn btn-danger">Remove All</button>
	</form>
	<form action="Cart" method="post">
		<input type="hidden" id="action" name="action" value="buy">
		<button class="btn btn-danger">Buy Now</button>
	</form>
</z:layout>