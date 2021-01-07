package taskManagement;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLquery {
	
	static void insert(Connection connect, String taskname, String startdate, String enddate,
				String priority, String status) throws SQLException
	{
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) AS rowcount FROM tasks");
		rs.next();
		int count = rs.getInt("rowcount");
		count += 1;
		rs.close();
		st.executeUpdate("INSERT INTO tasks VALUES(" + count + ", '"+taskname+"', '"+startdate+"', '"+enddate+"', '"+priority+"', '"+status+"')");
	}
	
	static void tasks(Connection connect) throws SQLException
	{
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tasks ORDER BY TaskId");
		
		System.out.println("All tasks...\n");
		System.out.println("S.No.   Task Name        Start Date        End Date        Priority       Status     ");
		int id = 1;
		while(rs.next())
		{
			String taskname = rs.getString("TaskName");
			String startdate = rs.getString("StartDate");
			String enddate = rs.getString("EndDate");
			String priority = rs.getString("Priority");
			String status = rs.getString("Status");
			
			System.out.format("%-7d %-16s %-17s %-15s %-14s %-10s\n",
					id, taskname, startdate, enddate, priority, status);
			id += 1;
		}
		rs.close();
	}
	
	static void todaytasks(Connection connect) throws SQLException
	{
		// get current date to query database
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String StartDate = formatter.format(date);
		
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE StartDate = '"+StartDate+"' ORDER BY TaskId");
		
		System.out.println("\nToday's tasks...\n");
		System.out.println("S.No.   Task Name        Start Date        End Date        Priority       Status     ");
		int id = 1;
		while(rs.next())
		{
			String taskname = rs.getString("TaskName");
			String startdate = rs.getString("StartDate");
			String enddate = rs.getString("EndDate");
			String priority = rs.getString("Priority");
			String status = rs.getString("Status");
			
			System.out.format("%-7d %-16s %-17s %-15s %-14s %-10s\n",
					id, taskname, startdate, enddate, priority, status);
			id += 1;
		}
		rs.close();
	}
	
	static void getTask(Connection connect, String taskName) throws SQLException
	{
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE TaskName = '"+taskName+"'");
		
		System.out.println("\nS.No.   Task Name        Start Date        End Date        Priority       Status     ");
		int id = 1;
		while(rs.next())
		{
			String taskname = rs.getString("TaskName");
			String startdate = rs.getString("StartDate");
			String enddate = rs.getString("EndDate");
			String priority = rs.getString("Priority");
			String status = rs.getString("Status");
			
			System.out.format("%-7d %-16s %-17s %-15s %-14s %-10s\n",
					id, taskname, startdate, enddate, priority, status);
			id += 1;
		}
		rs.close();
	}
	
	static void updateName(Connection connect, String taskname, String newtaskname) throws SQLException
	{
		Statement st = connect.createStatement();
		if(st.executeUpdate("UPDATE tasks SET TaskName = '"+newtaskname+"' "
				+ "WHERE TaskName = '"+taskname+"'") > 0)
		{
			System.out.println("Taskname changed as " + newtaskname + ".");
		}
		else
		{
			System.out.println("Something went wrong.");
		}
	}
	
	static void updateStartDate(Connection connect, String taskname, String newstartdate) throws SQLException
	{
		Statement st = connect.createStatement();
		if(st.executeUpdate("UPDATE tasks SET StartDate = '"+newstartdate+"' "
				+ "WHERE TaskName = '"+taskname+"'") > 0)
		{
			System.out.println("Task start date changed as " + newstartdate + ".");
		}
		else
		{
			System.out.println("Something went wrong.");
		}
	}
	
	static void updateEndDate(Connection connect, String taskname, String newenddate) throws SQLException
	{
		Statement st = connect.createStatement();
		if(st.executeUpdate("UPDATE tasks SET EndDate = '"+newenddate+"' "
				+ "WHERE TaskName = '"+taskname+"'") > 0)
		{
			System.out.println("Task end date changed as " + newenddate + ".");
		}
		else
		{
			System.out.println("Something went wrong.");
		}
	}
	
	static void updatePriority(Connection connect, String taskname, String newpriority) throws SQLException
	{
		Statement st = connect.createStatement();
		if(st.executeUpdate("UPDATE tasks SET Priority = '"+newpriority+"' "
				+ "WHERE TaskName = '"+taskname+"'") > 0)
		{
			System.out.println("Task priority changed as " + newpriority + ".");
		}
		else
		{
			System.out.println("Something went wrong.");
		}
	}
	
	static void updateStatus(Connection connect, String taskname, String newstatus) throws SQLException
	{
		Statement st = connect.createStatement();
		if(st.executeUpdate("UPDATE tasks SET Status = '"+newstatus+"' "
				+ "WHERE TaskName = '"+taskname+"'") > 0)
		{
			System.out.println("Task status changed as " + newstatus + ".");
		}
		else
		{
			System.out.println("Something went wrong.");
		}
	}
	
	static void getOpenStatus(Connection connect) throws SQLException
	{
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE Status = 'open' ORDER BY TaskId");
		
		System.out.println("\nOpen tasks...\n");
		System.out.println("S.No.   Task Name        Start Date        End Date        Priority       Status     ");
		int id = 1;
		while(rs.next())
		{
			String taskname = rs.getString("TaskName");
			String startdate = rs.getString("StartDate");
			String enddate = rs.getString("EndDate");
			String priority = rs.getString("Priority");
			String status = rs.getString("Status");
			
			System.out.format("%-7d %-16s %-17s %-15s %-14s %-10s\n",
					id, taskname, startdate, enddate, priority, status);
			id += 1;
		}
		rs.close();
	}
	
	static void getClosedStatus(Connection connect) throws SQLException
	{
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE Status = 'closed' ORDER BY TaskId");
		
		System.out.println("\nClosed tasks...\n");
		System.out.println("S.No.   Task Name        Start Date        End Date        Priority       Status     ");
		int id = 1;
		while(rs.next())
		{
			String taskname = rs.getString("TaskName");
			String startdate = rs.getString("StartDate");
			String enddate = rs.getString("EndDate");
			String priority = rs.getString("Priority");
			String status = rs.getString("Status");
			
			System.out.format("%-7d %-16s %-17s %-15s %-14s %-10s\n",
					id, taskname, startdate, enddate, priority, status);
			id += 1;
		}
		rs.close();
	}
	
	static void getLowPriority(Connection connect) throws SQLException
	{
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE Priority = 'low' ORDER BY TaskId");
		
		System.out.println("\nLow prioriy...\n");
		System.out.println("S.No.   Task Name        Start Date        End Date        Priority       Status     ");
		int id = 1;
		while(rs.next())
		{
			String taskname = rs.getString("TaskName");
			String startdate = rs.getString("StartDate");
			String enddate = rs.getString("EndDate");
			String priority = rs.getString("Priority");
			String status = rs.getString("Status");
			
			System.out.format("%-7d %-16s %-17s %-15s %-14s %-10s\n",
					id, taskname, startdate, enddate, priority, status);
			id += 1;
		}
		rs.close();
	}
	
	static void getMediumPriority(Connection connect) throws SQLException
	{
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE Priority = 'medium' ORDER BY TaskId");
		
		System.out.println("\nMedium priority...\n");
		System.out.println("S.No.   Task Name        Start Date        End Date        Priority       Status     ");
		int id = 1;
		while(rs.next())
		{
			String taskname = rs.getString("TaskName");
			String startdate = rs.getString("StartDate");
			String enddate = rs.getString("EndDate");
			String priority = rs.getString("Priority");
			String status = rs.getString("Status");
			
			System.out.format("%-7d %-16s %-17s %-15s %-14s %-10s\n",
					id, taskname, startdate, enddate, priority, status);
			id += 1;
		}
		rs.close();
	}
	
	static void getHighPriority(Connection connect) throws SQLException
	{
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE Priority = 'high' ORDER BY TaskId");
		
		System.out.println("\nHigh priority...\n");
		System.out.println("S.No.   Task Name        Start Date        End Date        Priority       Status     ");
		int id = 1;
		while(rs.next())
		{
			String taskname = rs.getString("TaskName");
			String startdate = rs.getString("StartDate");
			String enddate = rs.getString("EndDate");
			String priority = rs.getString("Priority");
			String status = rs.getString("Status");
			
			System.out.format("%-7d %-16s %-17s %-15s %-14s %-10s\n",
					id, taskname, startdate, enddate, priority, status);
			id += 1;
		}
		rs.close();
	}
}
