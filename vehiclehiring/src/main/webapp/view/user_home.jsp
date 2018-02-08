<%@page import="vh.vhutils.VHCaseConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %> 

<%@ taglib prefix="c" uri="jstl/core-rt" %>
<%@ taglib prefix="fmt" uri="jstl/fmt-rt" %>

<html>
<head>
<title>VHIS</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../assets/w3/w3.css" type="text/css">
	<link rel="stylesheet" href="../assets/font-awsome/css/font-awesome.min.css" type="text/css">
	<link rel="stylesheet" href="../assets/custom_css/common_styles.css" type="text/css">
	
<style>
	body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
	body {font-size:16px;}
	.w3-half img{margin-bottom:-6px;margin-top:16px;opacity:0.8;cursor:pointer}
	.w3-half img:hover{opacity:1}
</style>
</head>
<body>
	<!-- Navbar (sit on top) -->
	<div class="w3-top">
	  <div class="w3-bar w3-white w3-card" id="myNavbar">
	    <a href="#home" class="w3-bar-item w3-button w3-wide">LOGO</a>
	    <!-- Right-sided navbar links -->
	    <div class="w3-right w3-hide-small">
	    	
	      <a href="view/login.jsp" class="w3-bar-item w3-button">${userDetails.firstName}</a>
	      <form action="/<%=VHCaseConstants.LBL_VHCONTROLLER%>" method="post">
			<input type="hidden" name="<%=VHCaseConstants.LBL_OPERATION%>" value="<%=VHCaseConstants.LOGIN_OUT%>">
			<input type="submit" class="w3-bar-item w3-button" value="Logout">
			</form>
	    </div>
	    <!-- Hide right-floated links on small screens and replace them with a menu icon -->
	
	    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
	      <i class="fa fa-bars"></i>
	    </a>
	  </div>
	</div>
	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
	  <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
	  <div class="w3-container">
	    <h3 class="w3-padding-64"><b>Vehicle Hiring</h3>
	  </div>
	  <div class="w3-bar-block">
	    <a href="#" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Home</a> 
	    <a href="#showcase" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Showcase</a> 
	    <a href="#services" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Services</a> 
	    <a href="#designers" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Designers</a> 
	    <a href="#packages" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Packages</a> 
	    <a href="#contact" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Contact</a>
	    
	  </div>
	</nav>
	
	<!-- Top menu on small screens -->
	<header class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
	  <a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a>
	  <span>Company Name</span>
	</header>
	
	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>
	
	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left:340px;margin-right:40px">

  
  

<!-- End page content -->
</div>


<script>
// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

// Modal Image Gallery
function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
  var captionText = document.getElementById("caption");
  captionText.innerHTML = element.alt;
}
</script>
	
	
	
	
</body>
</html>