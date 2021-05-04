<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.io.File"%>
<%@page import="it.befloral.model.Cart"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.befloral.beans.CartProductBean"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Product- RedStore</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>

	<table style="width: 100%">
		<tr>
			<th>Nome</th>
			<th>Quantita'</th>
			<th>Prezzo</th>
			<th>Prezzo Totale</th>
			<th>Azioni</th>
		</tr>




		<%
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			Collection<CartProductBean> products = cart.getProducts();
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
			CartProductBean bean = (CartProductBean) it.next();
		%>
		<tr>
			<td><%=bean.getProduct().getName()%></td>
			<td><%=bean.getQuantity()%></td>
			<td><%=bean.getProduct().getPriceToString()%></td>
			<td><%=bean.getTotalPriceToString()%></td>
			<td>
				<form action="Cart" method="post">
					<input type="hidden" id="id" name="id" value="<%=bean.getId()%>">
					<input type="hidden" id="action" name="action" value="remove">
					<button class="btn">
						<span class="buy">Remove</span>
					</button>
				</form>
				<form action="Cart" method="post">
					<input type="hidden" id="id" name="id" value="<%=bean.getId()%>">
					<input type="hidden" id="action" name="action" value="update">
					 <input type="number" id="quantity" name="quantity" min="1" value="<%=bean.getQuantity()%>">
					<button class="btn">
						<span class="buy">Update</span>
					</button>
				</form>
			</td>
	
		</tr>
		<%
		}

		}
		}
		%>
	</table>
	<form action="Cart" method="post">
		<input type="hidden" id="action" name="action" value="removeAll">
		<button class="btn">
			<span class="buy">Remove All</span>
		</button>
	</form>
















	<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
</body>


</html>