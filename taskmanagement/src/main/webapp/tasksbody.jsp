<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Task manager</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		<%@ include file="navbar.jsp" %>
	</head>
	<body class="bg-light">
		<div class="container">
			<h1 class="text-center mt-3">
				<% if(str=="") { %>
						Tasks
				<% } else if(str=="today") { %>
						Today tasks
				<% } else if(str=="open") { %>
						Open tasks
				<% } else if(str=="closed") { %>
						Closed tasks
				<% } else if(str=="low") { %>
						Low priority tasks
				<% } else if(str=="medium") { %>
						Medium priority tasks
				<% } else if(str=="high") { %>
						High priority tasks
				<% } %>
			</h1>
			<table class="table table-sm table-hover table-bordered text-center mt-3">
			  <thead class="table-dark">
			    <tr>
			      <th scope="col">S.No</th>
			      <th>Task Name</th>
			      <th>Start Date</th>
			      <th>End Date</th>
			      <th>Priority</th>
			      <th>Status</th>
			      <th colspan="2">Actions</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<% for(int i = 0; i < data_arr.size(); i++) { 
			  		JSONObject new_obj = (JSONObject) data_arr.get(i);
				%>
			  		<tr>
				      <th scope="row"><%= i+1 %>.</th>
				      <td><%= new_obj.get("taskname") %></td>
				      <td><%= new_obj.get("startdate") %></td>
				      <td><%= new_obj.get("enddate") %></td>
				      <td><%= new_obj.get("priority") %></td>
				      <td><%= new_obj.get("status") %></td>
				      <td><a href="show.jsp?taskid=<%= new_obj.get("taskid") %>" class="btn btn-outline-success">Show</a>
				      <td><a href="edit.jsp?taskid=<%= new_obj.get("taskid") %>" class="btn btn-outline-primary">Edit</a>
				    </tr>
			  	<% } %>
			  </tbody>
			</table>
		</div>
	</body>
</html>