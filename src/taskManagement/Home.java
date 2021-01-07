package taskManagement;
import java.sql.*;
import java.util.Scanner;

public class Home {
	
	static Scanner sc = new Scanner(System.in);
	static void home(Connection connect) throws SQLException
	{
		System.out.println("\n........Welcome to task manager........\n");
		System.out.println("1. Add task");
		System.out.println("2. View all tasks");
		System.out.println("3. View today's tasks");
		System.out.println("4. View separate task");
		System.out.println("5. Edit task");
		System.out.println("6. Update task status");
		System.out.println("7. View specific tasks by status");
		System.out.println("8. View specific tasks by priority");
		System.out.println("9. Exit");
		
		int option;
		System.out.print("\nSelect any number from 1 to 8: ");
		option = sc.nextInt();
		
		switch(option)
		{
			case 1:
			{
				Task task = new Task();
				task.getTaskDetails();
				SQLquery.insert(connect, task.TaskName, task.StartDate,
								task.EndDate, task.Priority, task.Status);
				System.out.println("Task added sucessfully...");
				home(connect);
				break;
			}
			case 2:
			{
				SQLquery.tasks(connect);
				home(connect);
				break;
			}
			case 3:
			{
				SQLquery.todaytasks(connect);
				home(connect);
				break;
			}
			case 4:
			{
				SQLquery.tasks(connect);
				Task task = new Task();
				task.getTask();
				SQLquery.getTask(connect, task.TaskName);
				home(connect);
				break;
			}
			case 5:
			{
				SQLquery.tasks(connect);
				Task task = new Task();
				task.editTask(connect);
				home(connect);
				break;
			}
			case 6:
			{
				SQLquery.tasks(connect);
				Task task = new Task();
				task.updateStatus(connect);
				home(connect);
				break;
			}
			case 7:
			{
				Task task = new Task();
				task.getTaskByStatus(connect);
				home(connect);
				break;
			}
			case 8:
			{
				Task task = new Task();
				task.getTaskByPriority(connect);
				home(connect);
				break;
			}
			case 9:
			{
				connect.close();
				System.out.println("Thanks you. Hava nice day...");
				return;
			}
			default:
			{
				System.out.println("Invalid option. please select from 1 to 9.\n");
				home(connect);
				break;
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connect = null;
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanagement", "root", "");
		home(connect);
	}
}
