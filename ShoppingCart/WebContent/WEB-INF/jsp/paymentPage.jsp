<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.min.css" />
	
    <script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    
   <style type="text/css">
	#divMain{
		position:static;
	}
	#topdiv{
		background-color:#353535;
		color:white;
		border-radius:8px;
		padding:10px 0px 10px 0px;
		font-weight: bolder;
		text-transform: uppercase;
		height: 100%;
	}
	td {
    	padding: 8px;
    	color:black;
		margin-top:30px;
	}
	.td{
	border:0;
	border-radius: 8px;
	background-color: #353535;
	left:20px
	}
	
	#table2{
	padding:10px 0px 10px 0px;
	background: rgba(241, 235, 235, 0.5);
	border-radius:8px;
	height: 100%;
	}
	#nestedTable{
	border-bottom: 1px solid black;
	}
	
	</style>
</head>

<body >
<div id = "divMain">
<table width="100%"  height="500" >
	<tr>
		<td>
		<c:if test="${sessionScope.Cart1.numberOfItems > 0}">
			<div align="center"  id="topdiv">

				<form id="form1" name="form1" method="post" action="orderConfirmed.html">
					<c:set var="total" value="${sessionScope.Cart1.total}" />
					<div>
					<p>Welcome ${sessionScope.userID }
					<p>Total Price : ${total} USD
					<p>Please provide your Credit/Debit Card Details
					</div>

					<table align="center" width="50%" height="100%">
						<tr>
							<td>Name on Card</td>
							<td><label for="name"></label> <input type="text" name="name" id="name" /></td>
						</tr>
						<tr>
							<td>Card Type</td>
							<td>
								<label> <input type="radio" name="CardTypeRadioGroup"
									value="master_card" id="CardTypeRadioGroup_0" /> Master Card
								</label> <label> <input type="radio" name="CardTypeRadioGroup"
									value="visa_card" id="CardTypeRadioGroup_1" /> Visa Card
								</label> <br />
							</td>
						</tr>
						<tr>
							<td>Card Number</td>
							<td><label for="cvv"></label> <input type="text" name="cnumber"
								id="cnumber" /></td>
						</tr>
						<tr>
							<td>CVV</td>
							<td><label for="cvv"></label> <input type="password" name="cvv2"
								id="cvv" /></td>
						</tr>
						<tr>
							<td>Expiry Date(mmyyyy)</td>
							<td><label for="expiry_date"></label> <input type="text"
								name="expiry_date" id="expiry_date" /></td>
						</tr>
						<tr>
							<td>
								<button type="submit" name="submit" id="submit" class="btn btn-primary" style='margin-top:10px;width:90%'>Pay</button>
							</td>
							<td>
								<button type="reset" name="clear" id="clear" class="btn btn-primary" style='margin-top:10px;width:70%'>Reset</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			</c:if>
		</td>
		
		<td>
		<div  id="table2" align="center" style="overflow: auto;">
			<table width="100%" height="100%">
				<tr>
					<td align="center">
					<a style="font-weight:bold;color:blue;font-size: 25px;text-decoration: none;">Your Previous Shopping History</a>
					<c:forEach var="item" items="${cartHistory}">
					<table id="nestedTable" width="100%">
					<tr align="center">
						<td><a style="font-weight:bold;">Order Id: </a>${item.orderId}</td>
					</tr>
					<tr>
						<td><a style="font-weight:bold;color: black">Item: </a>${item.inventory.itemName}</td>
						<td><a style="font-weight:bold;color: black">Quantity: </a>${item.quantity}</td>
						<td><a style="font-weight:bold;color: black">Order Status: </a>${item.orderStatus}</td>
					</tr>
					</table>
					</c:forEach>
					</td>
				</tr>
			</table>
		</div>
	</td>
	</tr>
</table>

</div>

</body>
</html>