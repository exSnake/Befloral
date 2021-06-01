<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<link href="resources/css/user-profile.css" rel="stylesheet" type="text/css">
<link href="resources/css/invoice.css" rel="stylesheet" type="text/css">

<script src="resources/script/invoiceDow.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script>

<z:layout pageTitle="Order Details"> 

<div class="containerDetails" >  	
  	<div id="Details" style="background-image: radial-gradient(ellipse, rgba(57, 215, 156, 1) 0%, rgba(247, 25, 136, 0) 100%);">
       		<h1 class="invoice">Order Details</h1>
        	<div class="information_befloral">
        		<p class="befloral_firm">Befloral</p>
        		<span>
        			thank you very much for purchasing from us		
 				</span>
      		</div> 
       			
       			
        	<div class="information_invoice">     		
        	  <div class="time">	
        		<c:set var = "orderDate" value = "${orderToShow.getCreateDate()}" />
        		<p>Order executed on : <fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${orderDate}" /></p>
        	     <p>${orderToShow.getDestination()}</p>
        	      <p>User Details: </p>
	        	  	   <div class="pad">
	        	  	   	<p> ${user.getFirstName()}</p>
	        	 	    	<p>  ${user.getLastName()}</p>
	        	 	     		<p>  ${user.getEmail()}</p> 
	        	 	    </div>         	     
        	  </div>	
        	       
                    <div class="table-responsive">
                       <div class="div_table1">
                        <table class="table table-lg">
                            <thead>
                                <tr>
                                    <th>Description</th>
                                    <th>Weight Unit</th>
                                    <th>Quantity</th>
                                    <th>Unit price</th>
                                    <th>Discount</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                            
                            	<c:forEach items="${orderToShow.getItems() }" var="items" >
                                <tr>
                                    <td>
	                                        <p class="mb-0">${items.getName()}
	                                        				<br> ${items.getShortDescription()} </p>
                                    </td>  
                                    <td>${items.getWeight()}</td>
                                    <td>${items.getQuantity()}</td>
                                    <td>${items.getPriceString() }</td>
	                                <td>${items.getDiscountString()}</td>                             
                                    <td><span class="font-weight-semibold"> 
                                    		${items.getPrice()*items.getQuantity()} </span></td>
                                </tr> 
                               </c:forEach>   
                                   
                            </tbody>
                        </table>
                      </div>
                    </div>
                    
                  
                            <div class="div_table2">
                                <div class="table-responsive">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <th class="text-left">Subtotal:</th>
                                                <td class="text-right">${orderToShow.getTotalPaidString() }</td>                                               
                                            </tr>
                                            <tr>
                                                <th class="text-left">Tax: <span class="font-weight-normal">(22%)</span></th>
                                                <td class="text-right">TODO</td>
                                            </tr>
                                            <tr>
                                                <th class="text-left">Total:</th>
                                                <td class="text-right text-primary">
                                                    <h5 class="font-weight-semibold">${orderToShow.getTotalPaidString()}</h5>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th class="text-left">Invoice #</th>
                                                <td class="text-right text-primary">
                                                    <h5 class="font-weight-semibold" id="track">${orderToShow.getTrackNumber()}</h5>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                 </div>
             </div>       
        </div>
 	</div>
 	
 		<div>
 			<form method="get" action="User">
				<button class="links" style="margin-top: 20px" id="back"> Go Back</button>
			</form>
 		</div>
</div>

</z:layout>