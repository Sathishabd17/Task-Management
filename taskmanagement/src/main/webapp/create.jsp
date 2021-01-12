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
			<h1 class="text-center mt-3">Create new task</h1>
			<div class="row justify-content-center">
        		<div class="col-8">
					<form action="createProcess.jsp" method="POST" class="rounded shadow m-3 p-3">
					  <div class="form-row">
					    <div class="form-group col-md-6">
					      <label for="taskid">Task id</label>
					      <input type="text" name="taskid" class="form-control" id="taskid" placeholder="Enter task id">
					    </div>
					    <div class="form-group col-md-6">
					      <label for="priority">Priority</label>
					      <input type="text" name="priority" class="form-control" id="priority" placeholder="Enter task priority">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="taskname">Task name</label>
					    <input type="text" name="taskname" class="form-control" id="taskname" placeholder="Enter task name">
					  </div>
					  <div class="form-row">
					    <div class="form-group col-md-4">
					      <label for="startdate">Start date</label>
					      <input type="text" name="startdate" class="form-control" id="startdate" placeholder="Enter start date">
					    </div>
					    <div class="form-group col-md-4">
					      <label for="enddate">End date</label>
					      <input type="text" name="enddate" class="form-control" id="enddate" placeholder="Enter end date">
					    </div>
					    <div class="form-group col-md-4">
					      <label for="status">Status</label>
					      <input type="text" name="status" class="form-control" id="status" placeholder="Enter task status">
					    </div>
					  </div>
					  <div class="text-center">
					  	<button type="submit" class="form-control btn btn-primary">Create task</button>
					  </div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>