<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<z:layout pageTitle="Admin Order Edit">
<h1 class="mb-3">Update Order N. ${bean.getId()}</h1>
	<form action="Admin/Orders" method="post">
		<div class="form-group">
			<label for="destination">Destination</label>
			<input type="text" value="${bean.getDestination()}" class="form-control" name="destination" id="destination"
				aria-describedby="detinationHelp" placeholder="Enter new destination">
			<small id="destinationHelp" class="form-text text-muted">Enter the destination address</small>
		</div>
		<div class="form-group">
			<label for="trackNumber">Tracking Number</label>
			<input type="text" value="${bean.getTrackNumber()}" class="form-control" name="trackNumber" id="trackNumber"
				aria-describedby="trackNumberHelp" placeholder="Enter new tracking number">
			<small id="trackNumberHelp" class="form-text text-muted">Enter the tracking Number</small>
		</div>
		<c:if test="${bean.isGift()}">
		<div class="form-group">
			<label for="giftMessage">Gift Message</label>
			<input type="text" value="${bean.getGiftMessage()}" class="form-control" name="giftMessage" id="giftMessage"
				aria-describedby="giftMessage" placeholder="Enter giftMessage">
			<small id="giftMessage" class="form-text text-muted">Enter the gift Message</small>
		</div>
		</c:if>
		</form>
		<button type="submit"  class="btn btn-success mt-2">Update Order</button>
</z:layout>