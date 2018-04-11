<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yash Shop</title>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.min.css" />
	
    <script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    
<style type="text/css">
 	
  #header {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    height: 80px;
    background: rgba(241, 235, 235, 0.5);
    width: 100%;
}
#footer {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    height: 25px;
    margin:auto;
    background-color: #696969;
    background: rgba(241, 235, 235, 0.4);
}
#content {
    position: fixed;
    top: 80px;
    bottom: 25px;
    left: 0;
    right: 0;
    overflow: auto;
}
.parallax {
    /* The image used */
    background-image: url("resources/img/shp.jpg");

    /* Set a specific height */
    min-height: 500px; 
    /* Create the parallax scrolling effect */
    background-attachment: fixed;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    border-radius:8px;
	}

</style>
    
</head>
<body class="parallax">
<div id="header">
<jsp:include page="header.jsp" />
</div>
	<div align="justify" id="content">
		<p><jsp:include page="${param.page}" /></p>
								<p>&nbsp;</p>
	</div>
<div id="footer">
<jsp:include page="Footer.jsp"/></div>
</body>
</html>