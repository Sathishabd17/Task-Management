<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.abd.taskmanagement.Task,java.net.URL,java.net.HttpURLConnection,
				java.util.Scanner,org.json.simple.parser.JSONParser,org.json.simple.JSONObject,org.json.simple.JSONArray,
				com.google.gson.Gson, java.io.OutputStream,java.io.DataOutputStream,java.io.BufferedReader,
				java.io.InputStreamReader, java.lang.*" %>
				
<% 
	String taskid = request.getParameter("taskid");
	String inline = "";
	JSONObject obj;
	String link = "http://localhost:8080/taskmanagement/webapi/tasks/task/"+taskid;
	URL url = new URL(link);

	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.connect();

	//Getting the response code
	int responsecode = conn.getResponseCode();
	
	if (responsecode != 200) {
	    throw new RuntimeException("HttpResponseCode: " + responsecode);
	} else {
		
	    Scanner scanner = new Scanner(url.openStream());
	  
	   //Write all the JSON data into a string using a scanner
	    while (scanner.hasNext()) {
	       inline += scanner.nextLine();
	    }
	    
	    //Close the scanner
	    scanner.close();

	    //Using the JSON simple library parse the string into a json object
	    JSONParser parse = new JSONParser();
	    obj = (JSONObject) parse.parse(inline);
	}
%>  

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
			<h1 class="text-center mt-3">Edit task</h1>
			<div class="row justify-content-center">
        		<div class="col-8">
					<form action="editProcess.jsp" method="PUT" class="rounded shadow m-3 p-3">
					  <div class="form-row">
					    <div class="form-group col-md-6">
					      <label for="taskid">Task id</label>
					      <input type="text" name="taskid" class="form-control" id="taskid" value="<%= obj.get("taskid") %>" placeholder="Enter task id">
					    </div>
					    <div class="form-group col-md-6">
					      <label for="priority">Priority</label>
					      <input type="text" name="priority" class="form-control" id="priority" value="<%= obj.get("priority") %>" placeholder="Enter task priority">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="taskname">Task name</label>
					    <input type="text" name="taskname" class="form-control" id="taskname" value="<%= obj.get("taskname") %>" placeholder="Enter task name">
					  </div>
					  <div class="form-row">
					    <div class="form-group col-md-4">
					      <label for="startdate">Start date</label>
					      <input type="text" name="startdate" class="form-control" id="startdate" value="<%= obj.get("startdate") %>" placeholder="Enter start date">
					    </div>
					    <div class="form-group col-md-4">
					      <label for="enddate">End date</label>
					      <input type="text" name="enddate" class="form-control" id="enddate" value="<%= obj.get("enddate") %>" placeholder="Enter end date">
					    </div>
					    <div class="form-group col-md-4">
					      <label for="status">Status</label>
					      <input type="text" name="status" class="form-control" id="status" value="<%= obj.get("status") %>" placeholder="Enter task status">
					    </div>
					  </div>
					  <div class="text-center">
					  	<button type="submit" class="form-control btn btn-primary">Update task</button>
					  </div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>