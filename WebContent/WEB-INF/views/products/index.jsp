<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/product.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<z:layout pageTitle="Products List">
	<div class="product-list">
		<c:forEach items="${products}" var="bean">
			<div class="product-wrapper">
			  <div class="product-container">
			    <div class="product-top">
			    	<img src="https://source.unsplash.com/350x420/?sig=${bean.getId()}&flower,${bean.getName().replace(' ', '+')}">
			    </div>
			    <div class="product-bottom">
			      <div class="product-left">
			        <div class="product-details">
			          <h1 class="title">${bean.getName()}</h1>
			          <p class="subtitle">&euro; ${bean.getPrice()}</p>
			        </div>
			        <div id="productadd_${bean.getId()}" class="product-buy"><i class="material-icons">add_shopping_cart</i></div>
			      </div>
			      <div class="product-right">
			        <div class="product-done"><i class="material-icons">done</i></div>
			        <div class="product-details">
			          <h1 class="title">${bean.getName()}</h1>
			          <p class="subtitle">Added to your cart</p>
			        </div>
			        <div id="productremove_${bean.getId()}" class="product-remove"><i class="material-icons">clear</i></div>
			      </div>
			    </div>
			  </div>
			  <div class="inside">
			    <div class="icon"><i class="material-icons">info_outline</i></div>
			    <div class="contents">
			      <table>
			        <tr>
			          <th>Width</th>
			          <th>Height</th>
			        </tr>
			        <tr>
			          <td>3000mm</td>
			          <td>4000mm</td>
			        </tr>
			        <tr>
			          <th>Something</th>
			          <th>Something</th>
			        </tr>
			        <tr>
			          <td>200mm</td>
			          <td>200mm</td>
			        </tr>
			        <tr>
			          <th>Something</th>
			          <th>Something</th>
			        </tr>
			        <tr>
			          <td>200mm</td>
			          <td>200mm</td>
			        </tr>
			        <tr>
			          <th>Something</th>
			          <th>Something</th>
			        </tr>
			        <tr>
			          <td>200mm</td>
			          <td>200mm</td>
			        </tr>
			      </table>
			    </div>
			  </div>
			</div>
		</c:forEach>
	</div>
	
	<script>
	
	
	$(".product-buy").on("click", function() {
		var pid = this.id.split("_")[1];
		var time;
		var timerStarted = false;
		$.post("Api/Cart", { action:"add", id:pid }, function(msg){
			parseInt($("#cart-quantity").text(parseInt($("#cart-quantity").text())+1));
			$("#productadd_"+pid).parent().parent().addClass("clicked");
			$("#productadd_"+pid).parent().parent().one("mouseout", function(){
				time = setTimeout(() => {
					$("#productadd_"+pid).parent().parent().removeClass("clicked");
					timerStarted = true;
				}, 3000);
			});
			$("#productadd_"+pid).parent().parent().on("mouseover", function(){
				if(timerStarted) {
					$("#productadd_"+pid).parent().parent().off("mouseover")
				} else {
					clearTimeout(time);
					$("#productadd_"+pid).parent().parent().one("mouseout", function(){
						time = setTimeout(() => {
							$("#productadd_"+pid).parent().parent().removeClass("clicked");
							console.log("out");
							timerStarted = true;
						}, 3000);
					});
				}
			});
		});
	});
	
	$(".product-remove").on("click", function() {
		var pid = this.id.split("_")[1];
		$.post("Api/Cart", { action:"remove", id:pid }, function(msg){
			parseInt($("#cart-quantity").text(parseInt($("#cart-quantity").text())-1));
			$("#productadd_"+pid).parent().parent().removeClass("clicked");
		});
	});
	</script>
</z:layout>