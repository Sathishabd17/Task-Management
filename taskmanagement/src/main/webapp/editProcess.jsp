<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.abd.taskmanagement.Task,java.net.URL,java.net.HttpURLConnection,
				java.util.Scanner,org.json.simple.parser.JSONParser,org.json.simple.JSONObject,org.json.simple.JSONArray,
				com.google.gson.Gson, java.io.OutputStream,java.io.DataOutputStream,java.io.BufferedReader,
				java.io.InputStreamReader, java.lang.*" %>

<%
	Task task = new Task();
	
	task.setTaskid(Integer.parseInt(request.getParameter("taskid")));
	task.setTaskname(request.getParameter("taskname"));
	task.setStartdate(request.getParameter("startdate"));
	task.setEnddate(request.getParameter("enddate"));
	task.setPriority(request.getParameter("priority"));
	task.setStatus(request.getParameter("status"));
	
	Gson gson = new Gson();
	String json = gson.toJson(task);
	
	//String postJsonData = "{\"Id\":5,\"name\":\"USA\",\"scores\":8000}";
	URL obj = new URL("http://localhost:8080/taskmanagement/webapi/tasks/task");
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
	// Setting basic post request
	con.setRequestMethod("PUT");
	con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	con.setRequestProperty("Content-Type","application/json; charset=UTF-8");
	
	// Send post request
	con.setDoOutput(true);
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	wr.writeBytes(json);
	wr.flush();
	wr.close();
	            
	int responseCode = con.getResponseCode();
	
	BufferedReader in = new BufferedReader(
	        new InputStreamReader(con.getInputStream()));
	String output;
	StringBuffer responses = new StringBuffer();
	
	while ((output = in.readLine()) != null) {
	 responses.append(output);
	}
	in.close();
	
	response.sendRedirect("show.jsp?taskid="+task.getTaskid());
%>