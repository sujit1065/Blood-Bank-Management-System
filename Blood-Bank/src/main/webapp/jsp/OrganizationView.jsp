<%@page import="in.co.blood.bank.controller.OrganizationCtl"%>
<%@page import="in.co.blood.bank.util.ServletUtility"%>
<%@page import="in.co.blood.bank.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Organization</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Organization</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=BBMView.ORGNIZATION_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="in.co.blood.bank.bean.OrgnizationBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Organization</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                	
    						<div class="form-group">
								<input type="text" class="form-control"  name="name"
									placeholder="Name" value="<%=DataUtility.getStringData(bean.getName())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
        					
							<%if(bean.getId()==0){ %>
							<div class="form-group">
								<input type="text" class="form-control"  name="login"
									placeholder="Login Id" value="<%=DataUtility.getStringData(bean.getLogin())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
							</div>
							<div class="form-row">
    							<div class="form-group col-md-6">
								<input type="password" class="form-control" 
									name="password" placeholder="Password" value="<%=DataUtility.getStringData(bean.getPassword()) %>" >
						<font   color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							</div>
							
							<div class="form-group col-md-6">
								<input type="password" class="form-control" 
									name="confirmPassword" placeholder="Confirm Password" value="<%=DataUtility.getStringData(bean.getConfirmPassword()) %>" >
						<font   color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							</div>
							</div>
							<%} %>
							<div class="form-group">
								<input type="text" class="form-control"  name="mobile"
									placeholder="Contact No." value="<%=DataUtility.getStringData(bean.getContectNo())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="city"
									placeholder="City" value="<%=DataUtility.getStringData(bean.getCity())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("city", request)%></font>
							</div>
							
							<div class="form-group">
                   				 <textarea class="form-control" type="textarea" name="address" placeholder="Address"  rows="3"><%=DataUtility.getStringData(bean.getAddress())%></textarea>
                                           <font  color="red"><%=ServletUtility.getErrorMessage("address", request)%></font>
                  			  </div>
							
							
            
        					<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=OrganizationCtl.OP_SAVE%>">&nbsp;or&nbsp;
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=OrganizationCtl.OP_RESET%>">
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>

		<%@ include file="Footer.jsp"%>
</body>
</html>