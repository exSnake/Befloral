<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<z:layout pageTitle="Admin Order View">

<div class="container">
			<div class="order-id">
				<h1>${bean.getId()}</h1>
			</div>
			<div class="order-destination">
				<p>${bean.getDestination()}</p>
			</div>
	</div>
</z:layout>
