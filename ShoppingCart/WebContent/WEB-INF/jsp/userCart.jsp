<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Yash Shop</title>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.min.css" />
	
    <script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<style type="text/css">
	#divMain{
		background-color:gray;
		position:absolute;
		border-radius:8px;
		top:30%;
		left:30%;
		width:auto;
		margin:auto;
		padding:10px 0px 10px 0px;
	}
	#table {
    	border-collapse: collapse;
    	width: 100%;
	}
	body{
		background-image: url("resources/img/fashion-black.jpg");
    	background-repeat: no-repeat;
    	background-size: 100%;
	}
	th, td {
    	text-align: left;
    	padding: 8px;
    	border-bottom-style: groove;
	}
	a{
	}

tr:nth-child(even){background-color: #f2f2f2}



</style>
<script type="text/javascript">
function myFunction2(val) {
	var x = val;
	var regex=/^[0-9]+$/;
	if(!x.match(regex)){
		document.getElementById("chekoutId").href = "#";
		alert("Input Should be Integer value");
		
	}else{
		document.getElementById("chekoutId").href = "loginRegister.html";
	}
}
</script>
</head>
<body>
	<div id="divMain" align="center">
	<c:if test="${sessionScope.Cart1.numberOfItems < 1 }">
				<a> Shopping Cart is Empty,</a>
				<a><b>Please Continue Shopping</b></a><br><br>
	</c:if>
	
	<c:if test="${sessionScope.Cart1.numberOfItems > 0}">
	
		<table id="table">
			<tr>
				<th>Quantity</th>
				<th>Product Name</th>
				<th>Price Per Product</th>
				<th>Product Type</th>
			</tr>
			<c:forEach var="item" items="${sessionScope.Cart1.items}">
				<c:set var="items" value="${item.cartItems}" />
				<tr>
					<form action="userCart.html">
					<td><input value="${item.cartQuantity}" type="text"  id="updatedQty" name="sendQty" onchange="myFunction2(this.value)" size="8px">
					</td>
					<td>${items.itemName}</td>
					<td>${items.itemPrice}</td>
					<td>${items.itemGroup}</td>
					<td><c:url var="url" value="/userCart.html">
							<c:param name="Remove" value="${items.itemId}" />
						</c:url> <a href="${url}">Remove Item</a>
					</td>
					<td>
					<button type="submit" value="${items.itemId}" id="qtySub" name="valId" class="btn btn-primary" style='margin-top:10px;'>Update Quantity</button>
					</td>
					</form>
				</tr>
			</c:forEach>
		</table>
	
	
		<p>Total Amount:$ ${sessionScope.Cart1.total}</p>
		<c:url var="url" value="/loginRegister.html" />
			<strong><a id="chekoutId" href="${url}">Checkout</a></strong> &nbsp;&nbsp;&nbsp;</c:if>
			<c:url var="url2" value="/yashShoppingHome.html" />
			
			<strong><a href="${url2}">Continue Shopping</a></strong>
		
	</div>
</body>
</html>