<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Yash Shop</title>

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
			console.log(value);
			if(value == 'Login'){
				$("#divRegister").slideUp();
				$("#divLogin").slideDown();
				document.getElementById("pwdreset").style.display="none";
				document.getElementById("msg").style.display="block";
			}
			else if (value == 'Sign Up') {
				$("#divRegister").slideDown();
				$("#divLogin").slideUp();
				document.getElementById("pwdreset").style.display="none";
				document.getElementById("msg").style.display="none";
			}else{
				$("#divRegister").slideDown();
				$("#divLogin").slideDown();
			}
		});
	});
	
	function forgotPassword() {
		document.getElementById("divLogin").style.display="none";
	    document.getElementById("divRegister").style.display="none";
	    document.getElementById("pwdreset").style.display="block";
	}
</script>

<style type="text/css">
	#divMain{
		background-color:#353535;
		position:absolute;
		top:30%;
		left:35%;
		border-radius:8px;
		width:30%;
		padding:10px 0px 10px 0px;
	}
	#divMain span{
		cursor:pointer;
		margin-left:10px;
		font-size:20px;
		color:#ffffff;	
		padding:0px 10px 3px 10px;
	}
	#divMain span.active{
		border-bottom:5px solid #428bca;
	}
	.form-control{
		width:90%;
		margin-top:10px;
	}
	body{
		background-image: url("resources/img/fashion-black.jpg");
    	background-repeat: no-repeat;
    	background-size: 100%;
	}
</style>
</head>

<body>

	<div id='divMain'>
	
		<span class='active'>Login</span>
		<c:if test="${adminLogin eq null}">
		<span>Sign Up</span></c:if>
		<br/>		
		<div id="msg" style="color: White" align="center"><br/>${message}<br/></div>
		<c:choose>
			<c:when test="${adminLogin eq 'Yes'}">
				<form action="loginUser.html">
					<div class="form-group" align='center' id="divLogin">
						<input type="text" name="adminName" placeholder="Admin Id" class="form-control" />
						<input type="password" name="adminPwd" placeholder="Password" class="form-control" />
						<button type="submit" class="btn btn-primary" style='margin-top:10px;width:90%'>Login</button>
						<!-- <a onclick="forgotPassword()">forgot Password ??</a> -->
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<form action="loginUser.html">
					<div class="form-group" align='center' id="divLogin">
						<input type="text" name="uname" placeholder="User Name" class="form-control" />
						<input type="password" name="upwd" placeholder="Password" class="form-control" />
						<button type="submit" class="btn btn-primary" style='margin-top:10px;width:90%'>Login</button>
						<a onclick="forgotPassword()">forgot Password ??</a>
					</div>
				</form>
			
				<form action="addUser.html">
					<div class="form-group" align='center' style="display:none;" id="divRegister">
						<input type="text"  id="userName" name="uname" class="form-control" placeholder="Select User Name"/>
						<input type="password" class="form-control" placeholder = "Select Password" id="password" name="password"/>
						<input type="text" class="form-control" placeholder = "Enter Full Name" id="fullname" name="fullName" />
						<input type="text" class="form-control" placeholder = "Enter Valid Email Id" id="emailId" name="emailId"/>
						<button type="submit" class="btn btn-primary" style='margin-top:10px;width:90%'>Sign Up</button>
					</div>
				</form>
				</c:otherwise>
				</c:choose>
				<form action="sendResetMail.html">
					<div align="center" style="display: none;" id="pwdreset">
						<input type="text" class="form-control" placeholder = "Enter Email Id" id="emailId" name="emailId"/>
						<button type="submit" class="btn btn-primary" style='margin-top:10px;width:90%'>RESET</button>
					</div>
				</form>
	</div>
	
</body>
</html>
