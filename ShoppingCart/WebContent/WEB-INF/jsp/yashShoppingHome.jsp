<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Yash Shopping</title>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.min.css" />
	
    <script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    
	
<style type="text/css">
	#divMain{
		position:static;
		color:black;
		margin:auto;
		border-radius:8px;
		width:50%;
		font-weight: bolder;
		text-transform: uppercase;
		background: rgba(241, 235, 235, 0.4);

	}
	#td{
		padding-left: 10px;
		border-left:  1px solid black;
	}
	#divFilter{
		top:30%;
		left:35%;
		border-radius:8px;
		font-size:15px;
		width:100%;
		padding:10px 0px 10px 20px;
		background: rgba(241, 235, 235, 0.4);
		font-weight: bold;
	}
	.button{
		background-color:#353535;
		color:white;
		border-radius:8px;
		text-align:center;
	}
	#price{
		background-color:#C0C0C0;
		border-radius:8px;
		padding: 10px 10px 10px 10px;
		border: medium;
		color: black;
	}
	#price1{
		background-color:#C0C0C0;
		border-radius:8px;
		padding: 10px 10px 10px 10px;
		border: medium;
		color: black;
	}
	select{
		width:15%;
		margin:auto;
		text-align:center;
	}
	#divFilter span{
		cursor:pointer;
		margin-left:10px;
		font-size:15px;
		color:#ffffff;	
		padding:0px 10px 3px 10px;
		
	}
	img{
	border-radius:8px;
	}
	
	
	
</style>
</head>
<body id='price'>

<div  style="color: white;font-size: 20px" align="center">${message}</div>
<c:url var="url" value="/loginRegister.html">
	<c:param name="LoginType" value="Admin" />
</c:url>



<div id="divFilter">
<table width="100%">
	<tr>
	<td align="left">
		
			<form action="yashShoppingHome.html">
				<a style="color:black; ">Set Filter : </a>
				<select name="filter" id="productfilter">
					<c:forEach var="cartItems" items="${setproductList.productGroup}">
						<option value="${cartItems}">${cartItems}</option>
					</c:forEach>
				</select>
				<input type="submit" name="Search" value="Search" class="button .button">
			</form>
		
	</td>
	<td align="right">
		<c:if test="${sessionScope.Cart1.numberOfItems > 0}">
			<c:url var="url" value="userCart.html">
			<c:param name="Clear" value="0" />
			<c:param name="Remove" value="0" />
		</c:url>
		<a href="${url}" style="color: black;">Show Cart(${sessionScope.Cart1.numberOfItems})</a>&nbsp;&nbsp;&nbsp;
</c:if>
	</td>
	</tr>
</table>
</div>


 <c:forEach var="setList" items="${setproductList.inventoryList}" varStatus="status">
 <br>
<div  id="divMain"  align="center" >

			<table width="95%" height="201">
				<tr>
				<!-- New Column #1-->
				<td width="355" rowspan="4">
				<img src="${setList.itemImageUrl}" width="256" height="171" alt="Yet to Be Added"></td>
				<td width="355" rowspan="4">
					<table   id='price' >
						<tr>
					<td width="335" height="33">
					<div align="center" style="font-size: 20px;color:black; ">
						
						${setList.itemName}
					</div>	
					
					</td>
				</tr>
				
				<tr>
					<td height="42"><div align="center" style="color: black;">Price:$ ${setList.itemPrice}</div></td>
				</tr>
				
				<tr>
					<td height="42">
					<div align="center" >
						<!--  Adds the specific item to the cart.-->
							<c:url var="url2" value="/addCart.html">
								<c:param name="Add" value="${setList.itemId}" />
								<c:param name="name" value="${setList.itemName}" />
							</c:url>
							<c:choose>
								<c:when test="${setList.itemQunatity > 0}">
									<a href="${url2}"  style="color:white;" >Add to Cart</a>
								</c:when>
								<c:otherwise>
									<a  style="color:red;" >Out Of Stock</a>
								</c:otherwise>
							</c:choose>
						</div></td>
				</tr>
					</table>
				</td>
				
				
				
				</tr>
				
				
				
			</table>
		</div>
</c:forEach>

</body>
</html>