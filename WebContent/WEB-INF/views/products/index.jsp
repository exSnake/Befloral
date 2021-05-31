<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/product.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<z:layout pageTitle="Products List">
	<div class="product-list">
		<c:forEach items="${products}" var="bean">
			<div class="wrapper">
				<div class="wrapper-container">
					<div class="wrapper-top">
						<img src="https://source.unsplash.com/300x400/?sig=${bean.getId()}&flower,${bean.getName().replace(' ', '+')}">
					</div>
					<div class="wrapper-bottom">
						<div class="wrapper-left">
							<div class="details">
								<a href="Products?action=view&id=${bean.getId()}">
									<p>${bean.getName()}</p>
								</a>
								<span>${bean.getPrice()}&euro;</span>
							</div>
							<div class="buy">
								<form action="Cart" method="post">
									<input type="hidden" id="id" name="id" value="${bean.getId()}">
									<input type="hidden" id="action" name="action" value="add">
									<button type="submit"><i class="material-icons">add_shopping_cart</i></button>
								</form>
							</div>
						</div>
						<div class="wrapper-right">
							<div class="done"><i class="material-icons">done</i></div>
							<div class="details">
								<p>${bean.getName()}</p>
								<span>Added to your cart</span>
							</div>
							<div class="remove"><i class="material-icons">clear</i></div>
						</div>
					</div>
				</div>
				<div class="inside">
					<div class="icon"><i class="material-icons">info_outline</i></div>
					<div class="contents">
						<p>${bean.getDescription()}</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</z:layout>