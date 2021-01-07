package taskManagement;
import java.sql.*;
import java.util.Scanner;

public class Task {
	// Scanner for getting input
	Scanner sc = new Scanner(System.in);
	
	// Variables for Tasks
	String TaskId;
	String TaskName;
	String StartDate;
	String EndDate;
	String Priority;
	String Status;
	
	
	void getTaskDetails() {
		System.out.print("\nEnter task name: ");
		TaskName = sc.nextLine();
		
		System.out.println();
		System.out.print("Enter task start date(DD/MM/YYYY): ");
		StartDate = sc.nextLine();
		
		System.out.println();
		System.out.print("Enter task end date(DD/MM/YYYY): ");
		EndDate = sc.nextLine();
		
		System.out.println();
		System.out.print("Enter task priority(low/medium/high): ");
		Priority = sc.nextLine();
		
		System.out.println();
		System.out.print("Enter task status(open/closed): ");
		Status = sc.nextLine();
	}
	
	void getTask()
	{
		System.out.print("\nEnter task name: ");
		TaskName = sc.nextLine();
	}
	
	void editTask(Connection connect) throws SQLException
	{
		getTask();
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) As rowcount FROM tasks WHERE TaskName = '"+TaskName+"'");
		rs.next();
		int count = rs.getInt("rowcount");
		rs.close();
		
		if(count > 0)
		{
			SQLquery.getTask(connect, TaskName);
			editMenu(connect, TaskName);
		}
		else
		{
			System.out.println("'"+TaskName+"'" + " task is not available. Please try again.");
			editTask(connect);
		}
	}
	
	void editMenu(Connection connect, String TaskName) throws SQLException
	{
		System.out.println("\nEdit task...\n");
		System.out.println("1. Edit task name");
		System.out.println("2. Edit task start date");
		System.out.println("3. Edit task end date");
		System.out.println("4. Edit task priority");
		System.out.println("5. Edit task status");
		System.out.println("6. Exit");
		
		int option;
		System.out.print("\nSelect any number from 1 to 6: ");
		option = sc.nextInt();
		sc.nextLine();
		
		switch(option)
		{
			case 1:
			{
				String NewTaskName;
				System.out.print("\nEnter task name: ");
				NewTaskName = sc.nextLine();
				SQLquery.updateName(connect, TaskName, NewTaskName);
				editMenu(connect, TaskName);
				break;
			}
			case 2:
			{
				String NewStartDate;
				System.out.print("\nEnter task start date(DD/MM/YYYY): ");
				NewStartDate = sc.nextLine();
				SQLquery.updateStartDate(connect, TaskName, NewStartDate);
				editMenu(connect, TaskName);
				break;
			}
			case 3:
			{
				String NewEndDate;
				System.out.print("\nEnter task end date(DD/MM/YYYY): ");
				NewEndDate = sc.nextLine();
				SQLquery.updateEndDate(connect, TaskName, NewEndDate);
				editMenu(connect, TaskName);
				break;
			}
			case 4:
			{
				String NewPriority;
				System.out.print("\nEnter task priority(low/medium/high): ");
				NewPriority = sc.nextLine();
				SQLquery.updatePriority(connect, TaskName, NewPriority);
				editMenu(connect, TaskName);
				break;
			}
			case 5:
			{
				String NewStatus;
				System.out.print("\nEnter task status(open/closed): ");
				NewStatus = sc.nextLine();
				SQLquery.updateStatus(connect, TaskName, NewStatus);
				editMenu(connect, TaskName);
				break;
			}
			case 6:
			{
				return;
			}
			default:
			{
				System.out.println("Invalid option. please select from 1 to 6.");
				editMenu(connect, TaskName);
				break;
			}
		}
	}
	
	void updateStatus(Connection connect) throws SQLException
	{
		getTask();
		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) As rowcount FROM tasks WHERE TaskName = '"+TaskName+"'");
		rs.next();
		int count = rs.getInt("rowcount");
		rs.close();
		
		if(count > 0)
		{
			SQLquery.getTask(connect, TaskName);
			
			String NewStatus;
			System.out.print("\nEnter task status(open/closed): ");
			NewStatus = sc.nextLine();
			SQLquery.updateStatus(connect, TaskName, NewStatus);
		}
		else
		{
			System.out.println("'"+TaskName+"'" + " task is not available. Please try again.");
			updateStatus(connect);
		}
	}

	void getTaskByStatus(Connection connect) throws SQLException {
		
		System.out.println("\nChoose status...\n");
		System.out.println("1. Open");
		System.out.println("2. Closed");
		System.out.println("3. Exit");
		
		int option;
		System.out.print("\nSelect any number from 1 to 3: ");
		option = sc.nextInt();
		sc.nextLine();
		
		switch(option)
		{
			case 1:
			{
				SQLquery.getOpenStatus(connect);
				getTaskByStatus(connect);
				break;
			}
			case 2:
			{
				SQLquery.getClosedStatus(connect);
				getTaskByStatus(connect);
				break;
			}
			case 3:
			{
				return;
			}
			default:
			{
				System.out.println("Invalid option. please select from 1 to 3.\n");
				getTaskByStatus(connect);
				break;
			}
		}
	}
	
void getTaskByPriority(Connection connect) throws SQLException {
		
		System.out.println("\nChoose priority...\n");
		System.out.println("1. Low");
		System.out.println("2. Medium");
		System.out.println("3. High");
		System.out.println("4. Exit");
		
		int option;
		System.out.print("\nSelect any number from 1 to 4: ");
		option = sc.nextInt();
		sc.nextLine();
		
		switch(option)
		{
			case 1:
			{
				SQLquery.getLowPriority(connect);
				getTaskByPriority(connect);
				break;
			}
			case 2:
			{
				SQLquery.getMediumPriority(connect);
				getTaskByPriority(connect);
				break;
			}
			case 3:
			{
				SQLquery.getHighPriority(connect);
				getTaskByPriority(connect);
				break;
			}
			case 4:
			{
				return;
			}
			default:
			{
				System.out.println("Invalid option. please select from 1 to 4.\n");
				getTaskByPriority(connect);
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
