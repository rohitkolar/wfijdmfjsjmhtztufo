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
	<link rel="stylesheet" href="../custom_css/common_styles.css" type="text/css">
		
	<style type="text/css">
		
		label {
			color: #9E9E9E;
		}
		
			
	</style>
</head>
<body style="font-family: 'tahoma'">
	<header class="w3-container" style="padding-left: 8%;">
		<h1>Logo</h1>
	</header>
	<div class="w3-row-padding" style="padding-left: 8%; padding-right: 8%">
		<div class="w3-row-padding" style="margin-top:30px; min-height: 550px; background-color: #ff7700">
			<div class="w3-container w3-half">
				<img class="w3-hide-small w3-hide-midium" src="../images/family_car.png">
			</div>
			<div class="w3-half">
				<div class="w3-container" style="margin: 30px 50px; min-width:290px">
					<form action="/<%=VHCaseConstants.LBL_VHCONTROLLER%>" method="post" class="w3-container w3-card-2 w3-round" style="background-color: white; margin-top:30px; padding:30px">
					<input type="hidden" name="<%=VHCaseConstants.LBL_OPERATION%>" value="<%=VHCaseConstants.LOGIN%>">
						<c:if test="${response != '' && !response.valid}">
							<div class="w3-panel errorBox">
							  <p style="text-align: center">${response.msg}</p>
							</div>
						</c:if>
						<div>
							<p>
								<input type="text" name="username" class="w3-input" maxlength="50" required>
								<label>Email/ Mobile No</label>
							</p>
							<p>
								<input type="password" name="password" class="w3-input" maxlength="50" required>
								<label>Password</label>
							</p>
							<input type="submit" value="Log In" class="w3-button w3-section w3-round w3-ripple" style="width: 100%; background-color: #03A9F4; color: white">
						</div>
						<div class="w3-display-container"  style="padding-top: 20px">
							<a href="registration.jsp" class="w3-display-middle" style="color: #03A9F4;">Not a registered member?</a>
						</div>
						<div style="height: 50px">
						</div>
					</form>
				</div>
			</div>
		</div>	
	</div>
</body>
</html>