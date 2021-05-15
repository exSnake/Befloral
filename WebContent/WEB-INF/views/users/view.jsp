<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/user-profile.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Profile">

	<h1>Hi! ${user.getEmail()} </h1>
	<div class="user-profile">	
		<div class="user-profile-menu">
			<ul>
				<li>
					<h2 class="title">My Account</h2>	
				</li>
				<li>
					<a href="User?action=viewOrders" class="active"><p>My Orders</p></a>
				</li>
				<li>
					<a href="User?action=wishList"><p>My Wish List</p></a>
				</li>
				<li>
					<a href="User?action=viewData"><p>My Data</p></a>
				</li>
				<li>
					<a href="User?action=viewSubscription"><p>My Subscription</p></a>
				</li>
			</ul>
			<a class="user-logout" href="Login?action=logout">Close the session</a>
		</div>
		
		<div class="right">
			<h3 class="title-left">My Orders</h3>
			<div class="user-previous-orders">
				<c:forEach items="${orders}" var="order">
				<div class="order-details">
					<div class="flex-spaced">
						<div class="flex">
							<img class="order-details-image" src="https://source.unsplash.com/120x120/?sig=${order.getId()}&bouquet">
							<ul>
								<li class="delivery-date">${order.getStatus()}</li>
								<li class="bouquet-name"></li>
								<li class="bouquet-price mobile-hidden">
									${order.getTotalPaid() }&euro;
									<span>, with discount</span>
								</li>
								<li class="order-number">Order no. ${order.getId()}</li>
								<li class="invoice-links">
									<a href="#" target="_blank" class="download-link">Download Invoice</a>
									<a href="#" class="generate-invoice">Generate Invoice</a>
								</li>
							</ul>
						</div>
						<div class="flex">
							<ul>
								<li><a class="links" href="Orders?id=${order.getId()}">View Details</a></li>
							</ul>
							
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
</z:layout>