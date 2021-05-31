<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<z:layout pageTitle="Admin Control Panel">
	<h1>Welcome to the Admin Control Panel</h1>
	<div>
		<a href="<c:url value="/Admin/Orders"/>">Manage Orders</a>
		<br>
		<a href="<c:url value="/Admin/Products"/>">Manage Products</a>
		<br>
		<a href="<c:url value="/Admin?action=viewLogs"/>">View Logs</a>
	</div>
</z:layout>