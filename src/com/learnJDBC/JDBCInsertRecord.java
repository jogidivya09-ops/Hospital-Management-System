package com.learnJDBC;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCInsertRecord {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		//ResultSet rs=null;
		String URL="jdbc:mysql://localhost:3306/JDBCMySQL";
		String Username="root";
		String Password="@divya@123!";
		try {
			Class.forName("com.mysql.jdbc.driver");
		}catch(ClassNotFoundException e) {
			System.out.println("In catch "+e.getMessage());
		}
		try {
		    con = DriverManager.getConnection(URL, Username, Password);
		    stmt = con.createStatement();

		    try {
		        stmt.execute("insert into Students values (3,'Sangeetha','IT')");
		    } catch (SQLException e) {
		        System.out.println("Insert failed for ID 3: " + e.getMessage());
		    }

		    try {
		        stmt.execute("insert into Students values (4,'Saina Nehwal','ECE')");
		    } catch (SQLException e) {
		        System.out.println("Insert failed for ID 4: " + e.getMessage());
		    }

		    try {
		        stmt.execute("insert into Students values (5,'Sania Mirza','EEE')");
		    } catch (SQLException e) {
		        System.out.println("Insert failed for ID 5: " + e.getMessage());
		    }

		    try {
		        stmt.execute("insert into Students values (6,'Sindhu','Mech')");
		    } catch (SQLException e) {
		        System.out.println("Insert failed for ID 6: " + e.getMessage());
		    }

		    System.out.println("Insert operations completed.");
		    stmt.close();
		    con.close();
		} catch (SQLException e) {
		    System.out.println("Connection or statement failed: " + e.getMessage());
		}

		finally {
			if(con!=null) {
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


