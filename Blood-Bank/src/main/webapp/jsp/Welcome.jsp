<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blood-Bank</title>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.mySlides {display:none;}
</style>
<script>
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  x[slideIndex-1].style.display = "block";  
}
</script>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Welcome</li>
	</ol>
	</nav>
	
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="jumbotron">
					<h1 class="display-4">Welcome To Blood Bank Management</h1>
					
				</div>
			</div>
		</div>
</div>
	
<div class="w3-content w3-display-container">
  <img class="mySlides" src="<%=BBMView.APP_CONTEXT%>/images/download(2).jpg" style="width:100%">
  <img class="mySlides" src="<%=BBMView.APP_CONTEXT%>/images/img_49e46d0f61ed9c7be6eb772597c26db5_1518093597213_original.jpg" style="width:100%">
  <img class="mySlides" src="<%=BBMView.APP_CONTEXT%>/images/organ-donation.jpg" style="width:100%">
  <img class="mySlides" src="<%=BBMView.APP_CONTEXT%>/images/download(2).jpg" style="width:100%">

  <button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)">&#10094;</button>
  <button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)">&#10095;</button>
</div>
	
<div style="margin-top: 308px">
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>