<%@page import="in.co.blood.bank.controller.BloodBankListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.blood.bank.bean.BloodBankBean"%>
<%@page import="in.co.blood.bank.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Donor</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<br>

	<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">

		<li class="breadcrumb-item active" aria-current="page">Search Donor
			</li>
	</ol>
	</nav>
	<form action="<%=BBMView.SEARCH_DONOR_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">

						<h3 style="margin-bottom: 15px; text-align: left;">Search Donor</h3>
						<div class="form-row">
							
							<div class="form-group col-lg-4">
								<input type="text" class="form-control" name="city"
									placeholder="City"
									value="<%=ServletUtility.getParameter("city", request)%>">
							</div>
							
							<div class="form-group col-lg-4">
								<input type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=BloodBankListCtl.OP_SEARCH%>">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<center>
			<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
		</center>
			<% List list = ServletUtility.getList(request);  %>
			
			<% if(list!=null && list.size()>0){%>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					
					
					<th scope="col">S.No</th>
					<th scope="col">Name</th>
					<th scope="col">Blood Group</th>
					<th scope="col">Contact No</th>
					<th scope="col">City</th>
					<th scope="col">Address</th>
					<th scope="col">Upload By</th>
					<th scope="col">Status</th>
					
				
				</tr>
			</thead>
			<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					BloodBankBean bean = null;

					

					Iterator<BloodBankBean> it = list.iterator();

					while (it.hasNext()) {
						bean = it.next();
				%>
				<tr>
					
					
						
					<td><%=index++%></td>
					
					<td><%=bean.getName()%></td>
					<td><%=bean.getBloodGroup()%></td>
					<td><%=bean.getContactNo()%></td>
					<td><%=bean.getCity()%></td>
					<td><%=bean.getAddress()%></td>
					<td><%=bean.getUploadBy()%></td>
					<td><%=bean.getStatus()%></td>

		
					
				</tr>
			
				<%
					}
				%>
			</tbody>
		</table>
		<hr>
		
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
			<%} %>
	</form>
	<br>
	<div style="margin-top: 321px">
	<%@ include file="Footer.jsp"%>
	</div>
</body>
</html>