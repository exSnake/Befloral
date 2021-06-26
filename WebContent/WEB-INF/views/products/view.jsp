<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<z:layout pageTitle="Product View">
<link href="<c:url value="/resources/css/product-view.css"/>" rel="stylesheet" type="text/css">
	<!-- Page to view details of a product -->
	<div id="notification" class="notification is-hidden"><button class="delete"></button>Test</div>
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
					<h1 class="is-size-2 has-text-weight-bold">${prod.getName()}</h1>
				</div>
				<div class="product-description mt-4">
					<p>${prod.getDescription()}</p>
				</div>
	
				<div class="product-action mt-4">
					<form action="Cart" method="post">
						<input type="hidden" name="action" value="add">
						<input type="hidden" name="id" value="${prod.getId()}">
						<button type="submit" class="btn btn-success" style="width:100%">Add to cart</button>
					</form>
					<button class="button is-primary mt-2" onclick="openModal()">Review</button>
					<div class="wishs">
				<form action="Products" method="post">
						<input type="hidden" name="action" value="addWishlist">
						<input type="hidden" name="pid" value="${prod.getId()}">
						<input type="hidden" name="price" value="${prod.getPrice()}">
						<button type="submit" class="btn btn-success" style="width:100%">Add to Wishlist</button>
				</form>
				</div>
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
						<h3 class="has-text-weight-bold">${review.getTitle()}</h3>
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
		<div class="modal">
		  <div class="modal-background"></div>
		  <div class="modal-card">
		    <header class="modal-card-head">
		      <p class="modal-card-title">New Review</p>
		      <button class="delete" aria-label="close" onclick="closeModal()"></button>
		    </header>
		    <section class="modal-card-body">
		      <form action="Products" method="post" id="reviewForm">
		      	<input type="hidden" name="pid" id="pid" value="${prod.getId()}">
		      	<input type="hidden" name="action" id="action" value="review">
		      	<input type="hidden" name="score" id="score" value="4">
		      	<label class="label">Overall Rating</label>
		      	<i class="fa fa-star fa-2x is-clickable has-text-warning" id="1-star" onclick="starClicked(this)"></i>
		      	<i class="fa fa-star fa-2x is-clickable has-text-warning" id="2-star" onclick="starClicked(this)"></i>
		      	<i class="fa fa-star fa-2x is-clickable has-text-warning" id="3-star" onclick="starClicked(this)"></i>
		      	<i class="fa fa-star fa-2x is-clickable has-text-warning" id="4-star" onclick="starClicked(this)"></i>
		      	<i class="fa fa-star fa-2x is-clickable has-text-white-ter" id="5-star" onclick="starClicked(this)"></i>
		      	<label class="label">Add a title</label>
		      	<input class="input" type="text" name="title" id="title" placeHolder="Important things to know">
		      	<label class="label">Add a review</label>
		      	<textarea class="textarea" name="body" id="body" placeHolder="What you liked and you didn't like..."></textarea>
		      </form>
		    </section>
		    <footer class="modal-card-foot">
		      <button class="button is-success" id="reviewSubmit">Submit</button>
		      <button class="button" onclick="closeModal()">Cancel</button>
		    </footer>
		  </div>
		</div>
	</div>
	<script>
		function submitForm(){
			$("#reviewForm").submit();
			$(".modal").removeClass("is-active");
		}
		function openModal(){
			$(".modal").addClass("is-active");
		}
		
		function closeModal(){
			$(".modal").removeClass("is-active");
		}
		
		function starClicked(star){
			var score = parseInt(star.id.split("-")[0]);
			$("#score").val(score);
			for(i = 1; i <= score; i++){
				$("#"+i+"-star").removeClass("has-text-white-ter")
				$("#"+i+"-star").addClass("has-text-warning")
			}
			for(i = score + 1; i <=5; i++) {
				$("#"+i+"-star").removeClass("has-text-warning")
				$("#"+i+"-star").addClass("has-text-white-ter")
			}
		}
		
		$(document).ready(function() {
			$("#reviewSubmit").click(function() {
				var pid = $("#pid").val();
				var title = $("#title").val();
				var body = $("#body").val();
				var score = $("#score").val();
				var action = $("#action").val();
				$.post('Products', {"action":action,"title":title,"body":body,"score":score,"pid":pid},
					function(resp) {
						alert(resp);
					})
					.fail(function(resp){
						alert(resp);
					});
				});
		});
	</script>
</z:layout>