package com.learnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCDemoStatment {

	public static void main(String[] args) {
		Connection con = null;
		//Statment stmt = null;
		ResultSet rs = null;
		String URL = "jdbc:mysql://localhost:3306/JDBCMySQL";
		
		//Username and Password while installing SQL
		String Username = "root";
		String Password = "@divya@123!";
		
		String Query = "select * from students where name = ?";
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Diver Loaded !");
		} catch(ClassNotFoundException e)
		{
		   System.out.println("Drivers not loaded");
		   System.out.println("In catch1"+e.getMessage());
		}
		try {
			con = DriverManager.getConnection(URL,Username,Password);
			
			//Statement stmt = con.createStatement();
			
			PreparedStatement preparedStatement = con.prepareStatement(Query);
			preparedStatement.setString(1, "maan");
			
			rs=preparedStatement.executeQuery();
		while(rs.next())
		{
			System.out.println("__________________");
			int id=rs.getInt("id");
			String name=rs.getString("name");
			String dept=rs.getString("dept");
			
			System.out.println("Student ID :"+id);
			System.out.println("Student Name :"+name);
			System.out.println("Student Department :"+dept);
			
			System.out.println("__________________");
		}
		rs.close();
		//stmt.close();
		con.close();
	}catch(SQLException e) {
		System.out.println("In catch"+e.getMessage());
	}
		finally
		   {
		   	if(con!=null) {
		   		try {
		   			rs.close();
		   			//stmt.close();
		   			con.close();
		   			System.out.println("Terminated Successfully");
		   		}
		   		catch(Exception e){
		   			System.out.println("Oops ! some serious issue");
		   		}
		   	}
			}


	}

}
