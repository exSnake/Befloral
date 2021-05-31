<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/product-view.css"/>" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<z:layout pageTitle="Admin Product View">
	<!-- Page to view details of a product -->
	<div class="products">
		<div class="product">
			<div class="left">
				<div class="images-small">
					<img src="https://picsum.photos/100/100">
					<img src="https://picsum.photos/100/100">
					<img src="https://picsum.photos/100/100">
					<img src="https://picsum.photos/100/100">
					<img src="https://picsum.photos/100/100">
				</div>
				<div class="images-big">
					<img src="https://picsum.photos/600/600">
				</div>
			</div>
			<div class="right">
				<div class="product-title">
					<h1>${prod.getName()}</h1>
				</div>
				<div class="product-description mt-4">
					<p>${prod.getDescription()}</p>
				</div>
	
				<div class="product-action mt-4">
					<a href="<c:url value="/Admin/Products?action=edit&id=${prod.getId()}"/>" class="btn btn-primary mb-2">Edit</a>
					<form action="<c:url value="/Admin/Products"/>" method="post">
		    			<input type="hidden" name="action" id="action" value="delete">
		    			<input type="hidden" name="id" id="id" value="${prod.getId()}">
		    			<button class="btn btn-danger mt-2" style="width: 100%">Delete</button>
	    			</form>
				</div>
			</div>
		</div>
		<hr>
		<div class="additional-info">
			<div class="reviews">
				<c:forEach items="${prod.getReviewes()}" var="review"> 
				<div class="single-review">
					<div class="rev-title">
						<span class="rev-score"><c:forEach var="i" begin="1" end="${review.getScore()}" ><i class="fa fa-star" style="color: #56BFBA"></i></c:forEach></span>
						<h3>${review.getTitle()}</h3>
					</div>
					<div class="rev-text">
						<div id="rev-body"><p>${review.getBody() }<p></div>
						<div id="rev-user"><i class="fa fa-user ml-2 mt-2"> ${review.getUser().getEmail()}</i></div>
						<div id="rev-actions"></div>
					</div>
					<c:if test="${review.getReply() != null}">
						<hr>
						<div class="rev-reply">
							<em>BeFloral replied:</em>
							<p>${review.getReply() }</p>
						</div>
					</c:if>	
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
</z:layout>