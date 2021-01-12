package com.abd.taskmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDb {
	Connection con = null;
	
	public TaskDb()
	{
		try
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanagement", "root", "");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	public List<Task> Helper(String sql) throws SQLException
	{
		List<Task> tasks = new ArrayList<>();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next())
		{
			Task task = new Task();
			task.setTaskid(rs.getInt(1));
			task.setTaskname(rs.getString(2));
			task.setStartdate(rs.getString(3));
			task.setEnddate(rs.getString(4));
			task.setPriority(rs.getString(5));
			task.setStatus(rs.getString(6));
			
			tasks.add(task);
		}
		
		return tasks;
	}
	
	public List<Task> getTasks() throws SQLException
	{
		String sql = "select * from tasks order by StartDate";

		return Helper(sql);
	}
	
	public Task getTask(int id) throws SQLException
	{
		Task task = new Task();
		String sql = "select * from tasks where TaskId="+id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next())
		{
			task.setTaskid(rs.getInt(1));
			task.setTaskname(rs.getString(2));
			task.setStartdate(rs.getString(3));
			task.setEnddate(rs.getString(4));
			task.setPriority(rs.getString(5));
			task.setStatus(rs.getString(6));
		}
		
		return task;
	}
	
	public void createTask(Task task) throws SQLException {
		String sql = "insert into tasks values(?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, task.getTaskid());
		st.setString(2, task.getTaskname());
		st.setString(3, task.getStartdate());
		st.setString(4, task.getEnddate());
		st.setString(5, task.getPriority());
		st.setString(6, task.getStatus());
		st.executeUpdate();
	}
	
	public void updateTask(Task task) throws SQLException
	{
		String sql = "update tasks set TaskName=?, StartDate=?, EndDate=?,"
				+ "Priority=?, Status=? where TaskId=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, task.getTaskname());
		st.setString(2, task.getStartdate());
		st.setString(3, task.getEnddate());
		st.setString(4, task.getPriority());
		st.setString(5, task.getStatus());
		st.setInt(6, task.getTaskid());
		st.executeUpdate();
	}

	public List<Task> getTodayTasks() throws SQLException {
		
		// get current date to query database
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String StartDate = formatter.format(date);
		
		String sql = "SELECT * FROM tasks WHERE StartDate = '"+StartDate+"' ORDER BY StartDate";
		
		return Helper(sql);
	}
	
	public List<Task> getOpenTasks() throws SQLException {
		
		String sql = "SELECT * FROM tasks WHERE Status = 'open' ORDER BY StartDate";
		
		return Helper(sql);
	}

	public List<Task> getClosedTasks() throws SQLException {
		
		String sql = "SELECT * FROM tasks WHERE Status = 'closed' ORDER BY StartDate";
		
		return Helper(sql);
	}

	public List<Task> getLowTasks() throws SQLException {
		
		String sql = "SELECT * FROM tasks WHERE Priority = 'low' ORDER BY StartDate";
		
		return Helper(sql);
	}
	
	public List<Task> getMediumTasks() throws SQLException {
		
		String sql = "SELECT * FROM tasks WHERE Priority = 'medium' ORDER BY StartDate";
		
		return Helper(sql);
	}

	public List<Task> getHighTasks() throws SQLException {
		
		String sql = "SELECT * FROM tasks WHERE Priority = 'high' ORDER BY StartDate";
		
		return Helper(sql);
	}
}
