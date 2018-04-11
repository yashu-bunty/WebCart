<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		background-color:#353535;
		position:absolute;
		top:30%;
		left:35%;
		border-radius:8px;
		width:30%;
		padding:10px 10px 10px 10px;
	}
	.form-control{
		width:90%;
		margin-top:10px;
	}
</style>

</head>
<body>
<div id="divMain">
<a>Please Select New Password</a>
	<form name="form" action="paswordReset.html">
		<div class="form-group" align='center'>
			<input type="password" name="npwd" placeholder="New Password" class="form-control" id="npwd"/>
			<input type="password" name="cpwd" placeholder="Confirm Password" class="form-control" id="cpwd"/>
			<button type="submit" class="btn btn-primary" style='margin-top:10px;width:90%'>Reset Password</button>
		</div>
	</form>
	<div id="msg" style="color: red" align="center">${message}</div>
</div>
</body>
</html>