<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.abd.taskmanagement.Task,java.net.URL,java.net.HttpURLConnection,
				java.util.Scanner,org.json.simple.parser.JSONParser,org.json.simple.JSONObject,org.json.simple.JSONArray,
				java.lang.*" %>
				
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
		<link rel="stylesheet" href="style.css">
		<%@ include file="navbar.jsp" %>
	</head>
	<body class="bg-light">
		<div class="container">
			<table class="table table-sm table-hover table-bordered text-center mt-3">
			  <thead class="table-dark">
			    <tr>
			      <th scope="col">S.No</th>
			      <th>Task Name</th>
			      <th>Start Date</th>
			      <th>End Date</th>
			      <th>Priority</th>
			      <th>Status</th>
			    </tr>
			  </thead>
			  <tbody>
			  		<tr>
				      <th scope="row">1.</th>
				      <td><%= obj.get("taskname") %></td>
				      <td><%= obj.get("startdate") %></td>
				      <td><%= obj.get("enddate") %></td>
				      <td><%= obj.get("priority") %></td>
				      <td><%= obj.get("status") %></td>
				    </tr>
			  </tbody>
			</table>
			
			<div class="row justify-content-center mt-5">
		        <div class="col-12">
		            <div class="row">
		                <div class="col-5"> 
		                    <img src="https://www.pngitem.com/pimgs/m/508-5084761_green-icon-transparent-free-task-png-png-download.png" class="image rounded mx-auto d-block">
		                    <div class="mt-4 text-success font-weight-light text-center mb-1">
		                        <h4><%= obj.get("taskname") %></h4>
		                    </div>
		                </div>
		
		                <div class="col-5"> 
		                    <div class="row">
		                        <div class="col-5 font-weight-light"> 
		                            Task name
		                        </div>
		                        <div class="col-7 text-right font-weight-light font-italic">
		                            <%= obj.get("taskname") %>
		                        </div>
		                    </div>
		                    <hr>
		                    <div class="row">
		                        <div class="col-5 font-weight-light"> 
		                            Start date
		                        </div>
		                        <div class="col-7 text-right font-weight-light font-italic">
		                            <%= obj.get("startdate") %>
		                        </div>
		                    </div>
		                    <hr>
		                    <div class="row">
		                        <div class="col-5 font-weight-light"> 
		                            End date
		                        </div>
		                        <div class="col-7 text-right font-weight-light font-italic">
		                            <%= obj.get("enddate") %>
		                        </div>
		                    </div>
		                    <hr>
		                    <div class="row">
		                        <div class="col-5 font-weight-light"> 
		                            Priority
		                        </div>
		                        <div class="col-7 text-right font-weight-light font-italic">
		                            <%= obj.get("priority") %>
		                        </div>
		                    </div>
		                    <hr>
		                    <div class="row">
		                        <div class="col-5 font-weight-light"> 
		                            Status
		                        </div>
		                        <div class="col-7 text-right font-weight-light font-italic">
		                            <%= obj.get("status") %>
		                        </div>
		                    </div>
		                    <hr>
		                    <div class="text-center col-12 mt-3">
		                    	<a href="edit.jsp?taskid=<%= obj.get("taskid") %>" class="btn btn-outline-primary">Edit</a>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</body>
</html>