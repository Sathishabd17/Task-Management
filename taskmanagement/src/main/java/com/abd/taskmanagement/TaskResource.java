package com.abd.taskmanagement;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("tasks")
public class TaskResource {
	
	TaskDb taskdb = new TaskDb();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getTasks() throws SQLException
	{
		return taskdb.getTasks();
	}
	
	@GET
	@Path("task/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Task getTask(@PathParam("id") int id) throws SQLException
	{
		return taskdb.getTask(id);
	}
	
	@POST
	@Path("task")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Task createTask(Task task) throws SQLException
	{
		taskdb.createTask(task);
		
		return task;
	}
	
	@PUT
	@Path("task")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Task updateTask(Task task) throws SQLException
	{
		if(taskdb.getTask(task.getTaskid()).getTaskid()==0)
		{
			taskdb.createTask(task);
		}
		else
		{
			taskdb.updateTask(task);
		}
		
		return task;
	}
	
	@GET
	@Path("today")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getTodayTasks() throws SQLException
	{
		return taskdb.getTodayTasks();
	}
	
	@GET
	@Path("open")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getOpenTasks() throws SQLException
	{
		return taskdb.getOpenTasks();
	}
	
	@GET
	@Path("closed")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getClosedTasks() throws SQLException
	{
		return taskdb.getClosedTasks();
	}
	
	@GET
	@Path("low")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getLowTasks() throws SQLException
	{
		return taskdb.getLowTasks();
	}
	
	@GET
	@Path("medium")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getMediumTasks() throws SQLException
	{
		return taskdb.getMediumTasks();
	}
	
	@GET
	@Path("high")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getHighTasks() throws SQLException
	{
		return taskdb.getHighTasks();
	}
}
