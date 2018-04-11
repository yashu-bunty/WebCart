<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#tb{
	right: 20px;
	}
	
</style>
</head>
<body>

<table width="100%" id="tb">
<tr>
<c:url var="url" value="/loginRegister.html">
	<c:param name="LoginType" value="Admin" />
</c:url>
<td align="left"><a href= "${url}"  style="color:white;font-weight: bold;">Admin Login</a></td>
<td align="right"><a style="font-weight: bold;">&copy;Yash Shop</a></td>
</tr>
</table>




</body>
</html>