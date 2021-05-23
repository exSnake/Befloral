<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<z:layout pageTitle="Admin Control Panel">
	<h1>Welcome to the Admin Control Panel</h1>
	<a href="<c:url value="/Admin/Orders"/>">Manage Orders</a>
	<br>
	<a href="<c:url value="/Admin/Products"/>">Manage Products</a>
</z:layout>