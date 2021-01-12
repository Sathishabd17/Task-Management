package com.abd.taskmanagement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@XmlRootElement
public class Task {
	private int taskid;
	private String taskname;
	private String startdate;
	private String enddate;
	private String priority;
	private String status;
	
	public JSONArray getRequest(String str) throws ParseException, IOException
	{
		String inline = "";
		JSONArray data_arr;
		String strurl = "http://localhost:8080/taskmanagement/webapi/tasks/"+str;
		URL url = new URL(strurl);

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
		    data_arr = (JSONArray) parse.parse(inline);
		}
		return data_arr;
	}
	
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task [taskid=" + taskid + ", taskname=" + taskname + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", priority=" + priority + ", status=" + status + "]";
	}
}
