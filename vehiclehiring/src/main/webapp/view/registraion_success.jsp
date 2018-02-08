<%@page import="vh.vhutils.VHResponse"%>
<%@page import="vh.vhutils.VHCaseConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="jstl/core-rt" %>
<%@ taglib prefix="fmt" uri="jstl/fmt-rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>VHIS</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../assets/w3/w3.css" type="text/css">
	<link rel="stylesheet" href="../assets/font-awsome/css/font-awesome.min.css" type="text/css">
	<link rel="stylesheet" href="../assets/custom_css/common_styles.css" type="text/css">
	
</head>
<body style="font-family: 'tahoma'">
	<header class="w3-container" style="padding-left: 8%;">
		<h1>Logo</h1>
	</header>
	<div class="w3-row-padding" style="padding-left: 8%; padding-right: 8%">
		<div class="w3-container w3-third">&nbsp;</div>
		<div class="w3-container w3-third">
			<div class="w3-card-4" style="background-color: #F5F5F5">
				<div class="w3-container" style="padding:20px" align="center">
				  	<i class="fa fa-check-circle fa-2x" style="color: #8BC34A"></i><br>
				  	<p>Registration successful! Now you can login with your credentials. </p><br>
				  	<a class="w3-button w3-round" href="login.jsp" style="background-color: #03A9F4; color: white">Go To Login</a>
				</div>
			</div>
		</div>
		<div class="w3-container w3-third">&nbsp;</div>
	</div>
</body>
</html>