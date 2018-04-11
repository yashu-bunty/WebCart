<%@page import="org.hibernate.Session"%>
<%@page import="org.springframework.web.context.request.SessionScope"%>
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
    
    <script type="text/javascript">
	
	$(document).ready(function(){
		$("#divMain span").click(function(){
			$('#divMain span').removeClass('active');
			$(this).addClass('active');
			var value = $(this).text();
			
			if(value == 'Update Quantity'){
				$("#divAddNew").slideUp();
				$("#divUpdate").slideDown();
			}else{
				$("#divAddNew").slideDown();
				$("#divUpdate").slideUp();
			}
		});
	});

	
</script>
    
<style type="text/css">
	#divMain{
		background-color:#ffffff;
		position:static;
		top:25%;
		left:35%;
		border-radius:8px;
		width:100%;
		padding:10px 0px 10px 0px;
	}
	#divMain span{
		cursor:pointer;
		margin-left:10px;
		font-size:20px;
		color:#353535;	
		padding:0px 10px 3px 10px;
	}
	#divMain span.active{
		border-bottom:5px solid #428bca;
	}
	.form-control{
		width:90%;
		margin-top:10px;
	}
	.hint { 
	display: none;
	font-size: 5px;
	size: 5px;
	color: red;
	font-style: italic;
	}
	input:focus + .hint { 
	display: inline;
	}
	#table2{
	padding:10px 0px 10px 0px;
	background: rgba(241, 235, 235, 0.5);
	border-radius:8px;
	height: 100%;
	}
	
	

</style>
<script type="text/javascript">
function search(val) {
	document.getElementById("searchForm").submit();
}

function validateQty(val){
	var msg = '${minReqQuantity}';
	var firstIndex = msg.indexOf(":");
	var subString = msg.substring(firstIndex+1,msg.length);
	var req = parseInt(subString);
	if(val< req){
		document.getElementById("qty").value = 0;
	}
}
</script>
</head>
<body>
<table align="center" height="500" cellspacing="10">
	<tr>
		<td>
		<div id='divMain' align="center">
	
		<span class='active'>Update Quantity</span>
		<span>Add New Item</span>
		<br/>
		<div id="msg" style="color: black" align="center"><br/>${message}<br/></div>
		<form action="updateQty.html" id="searchForm">
		<div class="form-group" align='center' id="divUpdate">
			
			<c:choose>
				<c:when test="${sValue eq 'Update Price/Quantity'}">
					<input type="text" name="itemId" onchange="search(this.value)" placeholder="Enter Item Id" class="form-control"  value="${id}"  /><br>
					<a>Current Quantity : </a>
					
					<input type="text" id="qty" name="qty" placeholder="Quantity" class="form-control" value="${qValue}" onchange="validateQty(this.value)">
					<span class="hint" style="font-size: 10px;color: red">${minReqQuantity}</span><br>
					<a>Current Price : </a>
					<input type="text" name="price" placeholder="Present Price" class="form-control" value="${pValue}"/>
				</c:when>
				<c:otherwise>
					<input type="text" name="itemId" placeholder="Enter Item Id" class="form-control" />
				</c:otherwise>
			</c:choose>
			
			<input type="submit" class="btn btn-primary" id="subId" style='margin-top:10px;width:90%' value="${sValue}"/>
			
			<a href="exportInvetory.html">Export Inventory</a>
		</div>
		</form>
			<div class="form-group"  style="display:none;" id="divAddNew" align="center">
		<!-- <form action="addItem.html"> -->
			<form action="addItem.html" method="post" enctype="multipart/form-data">
				<input type="text" class="form-control" placeholder = "Enter Vendor Id" id="vId" name="vId" value="${nVId}"/>
				<input type="text" class="form-control" placeholder = "Enter Vendor Name" id="vName" name="vName"/>
				<input type="text" class="form-control" placeholder = "Enter Vendor Email" id="vEmail" name="vEmail"/>
				
				<input type="text" class="form-control" placeholder = "Enter New Item Id" id="itemId" name="itemId" />
				<input type="text" class="form-control" placeholder = "Enter Item Name" id="itemName" name="itemName"/>
				<input type="text" class="form-control" placeholder = "Enter Item Price" id="itemPrice" name="itemPrice" />
				<input type="text" class="form-control" placeholder = "Enter Item Group" id="itemGroup" name="itemGroup"/>
				<input type="text" class="form-control" placeholder = "Enter Item Quantity" id="itemqty" name="itemqty"/>
				
				
			
		<!-- </form> -->
		
				<br>Select an Image to upload: <br><br><input type="file" name="file" size="50"/><br>
				<button type="submit" class="btn btn-primary" style='margin-top:10px;width:90%'>Upload Image</button>
			
			</form>
		</div>
		<div align="center">
		<form action="trackVendorOrders.html" method="post">
				<button type="submit" name="submit" id="submit" class="btn btn-primary" style='margin-top:10px;width:30%'>Track Orders</button>
		</form>
		</div>
		
		
		
		
</div>
</td>
		<td>
		<div  id="table2" align="right" style="overflow: auto;">
		<table>
			<tr>
				<td align="center">
					<a style="font-weight:bold;color:blue;font-size: 25px;text-decoration: none;">Items Running Out Of Stock</a>
			<table width="100%" >
					<tr>
					<th>Item Id</th>
					<th>Item Name</th>
					<th>Current Quantity</th>
					</tr>
					<tr>
					<c:forEach var="item" items="${runningOut}">
					
					<tr align="center">
						<td><a style="font-weight:bold;color: maroon">${item.itemId}</a></td>
						<td><a style="font-weight:bold;color: maroon">${item.itemName}</a></td>
						<td><a style="font-weight:bold;color: maroon">${item.itemQunatity}</a></td>
					</tr>
					</c:forEach>
					</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
</table>

</body>
</html>