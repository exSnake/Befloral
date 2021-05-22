<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<z:layout pageTitle="Admin Order View">

  
  <p>ID ORDER         :<b>${bean.getId()}</b> <p> 
  <p>User  ID         :<b>${bean.getUser()}</b> </p>
  <p>Destination      :<b>${bean.getDestination()}</b> </p>
  <p>Total Products   :<b>${bean.getTotalProducts()}</b> </p>
  <p>Total Paid       :<b>${bean.getTotalPaid()}</b> </p>
  <p>Track Number	  :<b>${bean.getTrackNumber()}</b> </p>
  <p>Gift			  :<b>${bean.getGiftMessage()}</b> </p>
<table style="width:100%">
  <tr>
    <th>Name</th>
    <th>Description</th>
    <th>Price</th> 
    <th>Weight</th>
    <th>Discount</th>
    <th>Quantity</th>
    <th>Id</th>
    <th>OrderId</th>
  </tr>
  <c:forEach items="${bean.getItems()}" var="item">
  <tr>
    <td>${item.getName()}</td>
    <td>${item.getDescription()}</td>
    <td>${item.getPrice()}<td>
    <td>${item.getWeight()}</td>
    <td>${item.getDiscount()}</td>
    <td>${item.getQuantity()}</td>
    <td>${item.getId()}</td>
    <td>${item.getOid()}</td>
  </tr>
  </c:forEach>		
</table>
 <a href="Admin/Orders?action=edit&id=${bean.getId()}">
					<button type="submit" class="btn btn-primary">Edit Order</button>
  </a>
</z:layout>
