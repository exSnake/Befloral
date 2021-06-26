<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/user-profile.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Wishlist">
<h1 class="is-size-1 has-text-weight-semibold">Hi! ${user.getFirstName()} </h1>
	<div class="user-profile">
		<div class="user-profile-menu">
			<ul>
				<li>
					<h2 class="title">My Account</h2>
				</li>
				<li>
					<a href="User?action=viewOrders" class="active">
						<p>My Orders</p>
					</a>
				</li>
				<li>
					<a href="User?action=viewAddresses" class="active">
						<p>My Addresses</p>
					</a>
				</li>
				<li>
					<a href="User?action=wishlist">
						<p>My Wish List</p>
					</a>
				</li>
				<li>
					<a href="User?action=viewData">
						<p>My Data</p>
					</a>
				</li>
				<li>
					<a href="User?action=viewSubscription">
						<p>My Subscription</p>
					</a>
				</li>
			</ul>
			<a class="user-logout" href="Login?action=logout">Logout</a>
		</div>

		<div class="right">
			<h3 class="title-left is-size-4 has-text-weight-semibold">My Wishs</h3>
			<div class="user-previous-orders">
				<c:if test="${wishs.size() == 0 }">
					<p>No wish yet...!</p>
				</c:if>
				
			
			</div>
		</div>
	</div>
</z:layout>