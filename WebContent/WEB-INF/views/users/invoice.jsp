<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" />

<link href="resources/css/user-profile.css" rel="stylesheet" type="text/css">
<z:layout pageTitle="Invoice">

<div class="container">
        
        <div class="row">
            <div>
                <button class="btn btn-primary" id="download"> Download PDF</button>
        	</div>
       		 <!-- in alto a sinistra -->
        	<div><span>Daniele Giaquinto CEO Befloral <br>
        	Mercato San Severino SA 84085 <br>
        	+39 3663997645 // 02-896340007
        	<br>
        	
        	</span></div>
      	
        	<div><!-- in alto a destra -->
        		 <p>Billing Data : </p>
        		 <c:set var="currentDate" value="<%=new java.util.Date()%>"/>
        		 <fmt:formatDate type="both" value="${currentDate}" /> <br/>
        	</div>
        	
        
        	<div>
        	
        	</div>
        
    
                    <div class="table-responsive">
                        <table class="table table-lg">
                            <thead>
                                <tr>
                                    <th>Description</th>
                                    <th>Quantity</th>
                                    <th>Unit price</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                            
                            	<c:forEach items="${orderToShow.getItems() }" var="items" >
                                <tr>
                                    <td>
	                                        <p class="mb-0">${items.getName()}
	                                        				<br> ${items.getDescription()} </p>
                                    </td>  
                                    <td>${items.getPrice()}</td>
                                    <td>${items.getQuantity()}</td>
                                   
                                    <td><span class="font-weight-semibold"> 
                                    		${items.getPrice()*items.getQuantity()} </span></td>
                                </tr> 
                               </c:forEach>   
                                   
                            </tbody>
                        </table>
                    </div>
                    
                    
                    <div class="card-body">
                        <div class="d-md-flex flex-md-wrap">
                            <div class="pt-2 mb-3 wmin-md-400 ml-auto">
                               
                                
                                <div class="table-responsive">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <th class="text-left">Subtotal:</th>
                                                <td class="text-right">$1,090</td>
                                            </tr>
                                            <tr>
                                                <th class="text-left">Tax: <span class="font-weight-normal">(25%)</span></th>
                                                <td class="text-right">$27</td>
                                            </tr>
                                            <tr>
                                                <th class="text-left">Total:</th>
                                                <td class="text-right text-primary">
                                                    <h5 class="font-weight-semibold">$1,160</h5>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="text-right mt-3"> <button type="button" class="btn btn-primary"><b><i class="fa fa-paper-plane-o mr-1"></i></b>
                                        Send invoice</button> </div>
                            </div>
                        </div>
                    </div>
                    <div class="footer_B"> <span class="text-muted">By Befloral financial service </span> </div>
                </div>
            </div>
        </div>
    </div>



</z:layout>