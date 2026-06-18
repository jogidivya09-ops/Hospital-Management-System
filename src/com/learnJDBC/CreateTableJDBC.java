package com.learnJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableJDBC {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		//ResultSet rs = null;
		String URL = "jdbc:mysql://localhost:3306/JDBCMySQL";
		//Username and Password while installing SQL
		String Username = "root";
		String Password = "@divya@123!";
		String TableName = "create table test(RollNumber INT PRIMARY KEY,Name VARCHAR(255),Marks INT Not Null, Grade VARCHAR(21))";
		try {
			Class.forName("com.mysql.jdbc.driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("In catch"+e.getMessage());
	
		}
		try {
			con = DriverManager.getConnection(URL,Username,Password);
			stmt  = con.createStatement();
			stmt.execute(TableName);
			System.out.println("Yay I Table Created");
			//rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e)
		{
			System.out.println("In catch"+e.getMessage());
		}
		finally
		{
			if(con!=null) 
			{
				try {
					//rs.close();
					stmt.close();
					con.close();
					System.out.println("Terminated Successfully");
				}
				catch(Exception e)
				{
					System.out.println("Oops ! some serious issue");
				}	
		}
		}	
	}

}
