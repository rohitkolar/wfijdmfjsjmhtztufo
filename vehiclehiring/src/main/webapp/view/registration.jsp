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
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="../assets/font-awsome/css/font-awesome.min.css" type="text/css">
	<link rel="stylesheet" href="../assets/custom_css/common_styles.css" type="text/css">
	
	<script type="text/javascript" src="../assets/custom_js/vh-validator.js"></script>	
	<script type="text/javascript" src="../assets/jquery/jquery.min.js"></script>	
	<style type="text/css">
		
		label {
			color: #9E9E9E;
		}
		
		form > div > p {
			margin: 4px 0px !important;
		}
		
		.w3-input {
			padding: 4px !important;
		}	
	</style>
	
	<script>
		$(function() {
			$("#registrationForm").submit(function() {
				if($("#password").val() != $("#conformPassword").val()) {
					showMessage("#password", "#alertMsg", "Password doesn't match");
					$("#alertBox").fadeIn();
					return false;
				}
					
				return true;
			});
		});
	

	</script>
</head>
<body style="font-family: 'tahoma'">
	<header class="w3-container" style="padding-left: 8%;">
		<h1>Logo</h1>
	</header>
	<div class="w3-row-padding" style="padding-left: 8%; padding-right: 8%">
		<div class="w3-row-padding" style="margin-top:30px; min-height: 550px; background-color: #ff7700">
			<div class="w3-container w3-third">
				&nbsp;
			</div>
			<div class="w3-container w3-third">
				<div class="w3-container" style="min-width:290px">
					<form id="registrationForm" action="/<%=VHCaseConstants.LBL_VHCONTROLLER%>" method="post" class="w3-container w3-card-2 w3-round" style="background-color: white; margin:30px 0px;">
					<input type="hidden" name="<%=VHCaseConstants.LBL_OPERATION%>" value="<%=VHCaseConstants.REGISTRATION%>">
						<header class="w3-display-container" style="margin-bottom: 30px">
						  <h4 class="w3-display-topmiddle" style="color: #03A9F4">Sign Up</h4>
						</header>
						<div style="padding:20px;">
							<c:if test="${response != null && !response.valid}">
								<div class="w3-panel errorBox">
								  <p style="text-align: center">${response.msg}</p>
								</div>
							</c:if>
							<div class="w3-panel errorBox" style="display: none" id="alertBox">
								  <p style="text-align: center" id="alertMsg"></p>
							</div>
							
							<p style="margin: 2px 0px !important;">
								<input type="text" id="firstName" name="firstName" class="w3-input" type="text" maxlength="50" required>
								<label>First Name</label>
							</p>
							<p>
								<input type="text" id="lastName" name="lastName" class="w3-input" type="text" maxlength="50" required>
								<label>Last Name</label>
							</p>
							<p>
								<input type="text" id="email" name="email" class="w3-input" type="text" maxlength="50" required>
								<label>Email</label>
							</p>
							<p>
								<input type="text" id="mobileNo" name="mobileNo" class="w3-input" type="text" maxlength="10" required>
								<label>Mobile Number</label>
							</p>
							<p>
								<input type="password" id="password" name="password" class="w3-input" type="text" maxlength="50" required>
								<label>Password</label>
							</p>
							<p>
								<input type="password" id="conformPassword" name="conformPassword" class="w3-input" type="text" maxlength="50" required>
								<label>Conform Password</label>
							</p>
							<input type="submit" value="Sign Up" class="w3-button w3-section w3-round w3-ripple" style="width: 100%; background-color: #03A9F4; color: white">
						</div>
						<div class="w3-display-container"  style="padding-top: 20px">
							<a href="login.jsp" class="w3-display-middle" style="color: #03A9F4;">Already Registered?</a>
						</div>
						<div style="height: 50px">
						</div>
					</form>
				</div>
			</div>
			<div class="w3-container w3-third">
				&nbsp;
			</div>
		</div>	
	</div>
</body>
</html>