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
   td {
    	padding: 8px;
    	color:black;
		margin-top:30px;
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
<body>
<div  id="table2" align="center" style="overflow: auto;">
	<table>
				<tr>
					<td align="center">
					<a style="font-weight:bold;color:blue;font-size: 25px;text-decoration: none;">Items Ordered to Vendor</a>
					
					<table id="nestedTable" width="100%" align="center" cellpadding="20px">
					<thead>
					<tr align="center">
					<th>Item Name</th>
					<th>Vendor Name</th>
					<th>Order Status</th>
					</tr>
					</thead>
					<c:forEach var="item" items="${VendorOrderList}">
					<tr align="center">
						<td>${item.itemName}</td>
						<td>${item.vendorName}</td>
						<td><a style="font-weight:bold;color: maroon">${item.orderStatus}</a></td>
					</tr>
					</c:forEach>
					</table>
					
					</td>
				</tr>
	</table>
</div>
</body>
</html>