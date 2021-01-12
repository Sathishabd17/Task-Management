<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.abd.taskmanagement.Task, org.json.simple.JSONObject, org.json.simple.JSONArray, java.lang.*" %>

<% 
	String str = "medium";
	Task task = new Task();
	JSONArray data_arr = task.getRequest(str);
%>

<%@ include file="tasksbody.jsp" %>