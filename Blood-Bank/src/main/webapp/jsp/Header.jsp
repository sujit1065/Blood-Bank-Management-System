<%@page import="in.co.blood.bank.controller.LoginCtl"%>
<%@page import="in.co.blood.bank.bean.UserBean"%>
<%@page import="in.co.blood.bank.controller.BBMView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=BBMView.APP_CONTEXT%>/css/bootstrap.min.css" >
<script src="<%=BBMView.APP_CONTEXT%>/js/jquery-3.3.1.slim.min.js" ></script>
<script src="<%=BBMView.APP_CONTEXT%>/js/popper.min.js" ></script>
<script src="<%=BBMView.APP_CONTEXT%>/js/bootstrap.min.js" ></script>

</head>
<body>

 <%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>

<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #905358a1;">
  <a class="navbar-brand" href="#">Blood Bank</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    
   		<li class="nav-item active">
        <a class="nav-link" href="<%=BBMView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
      	</li>
      	<li class="nav-item active">
        <a class="nav-link" href="<%=BBMView.SEARCH_DONOR_CTL%>">Search Donor</a>
      	</li>
      	<%if(userLoggedIn){%>
      	<%if(userBean.getRoleId()==1){ %>
       
      
      
      
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Hospital
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BBMView.HOSPITAL_CTL%>">Add Hospital</a>
          <a class="dropdown-item" href="<%=BBMView.HOSPITAL_LIST_CTL%>">Hospital List</a>
        
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Organization
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BBMView.ORGNIZATION_CTL%>">Add Organization</a>
          <a class="dropdown-item" href="<%=BBMView.ORGNIZATION_LIST_CTL%>">Organization List</a>
        
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Blood Bank
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BBMView.BLOODBANK_CTL%>">Add BloodBank</a>
          <a class="dropdown-item" href="<%=BBMView.BLOODBANK_LIST_CTL%>">BloodBank  List</a>
        
        </div>
      </li>
      

      <%}else if(userBean.getRoleId()==2){%>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Blood Bank
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BBMView.BLOODBANK_CTL%>">Add BloodBank</a>
          <a class="dropdown-item" href="<%=BBMView.BLOODBANK_LIST_CTL%>">BloodBank  List</a>
        
        </div>
      </li>
      
      
     
      
      <%}else if(userBean.getRoleId()==3){ %>
      
    
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Blood Bank
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BBMView.BLOODBANK_CTL%>">Add BloodBank</a>
          <a class="dropdown-item" href="<%=BBMView.BLOODBANK_LIST_CTL%>">BloodBank  List</a>
        
        </div>
      </li>
      
      
      <%}else if(userBean.getRoleId()==4){%>
      	<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Blood Bank
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BBMView.BLOODBANK_CTL%>">Add BloodBank</a>
          <a class="dropdown-item" href="<%=BBMView.BLOODBANK_LIST_CTL%>">BloodBank  List</a>
        
        </div>
      </li>
      <%} %>
      
      <%} %>
    </ul>
    <form class="form-inline my-2 my-lg-0">
     <ul class="navbar-nav mr-auto">
     <%if (userLoggedIn){%>
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BBMView.MY_PROFILE_CTL%>">My Profile</a>
          <a class="dropdown-item" href="<%=BBMView.CHANGE_PASSWORD_CTL%>">Change Password</a>
          <a class="dropdown-item" href="<%=BBMView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
        </div>
      </li>
      <%}else { %>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BBMView.LOGIN_CTL%>">SignIn</a>
          <a class="dropdown-item" href="<%=BBMView.USER_REGISTRATION_CTL%>">SignUP</a>

        </div>
      </li>
      <%} %>
     </ul>
      
    </form>
  </div>
</nav>
</body>
</html>