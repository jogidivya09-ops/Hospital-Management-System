package hms;

import java.sql.*;
import java.util.Scanner;

public class Patient {
		
		private Connection connection;
		private Scanner scanner;
		
		public Patient(Connection connection,Scanner scanner) 
		{
			this.connection=connection;
			this.scanner=scanner;
		}
		//method to add patients
		public void addPatient()
		{
			//collecting data from users
			System.out.println("Enter Patient's Name");
			String name = scanner.next();
			System.out.println("Enter patient's Age");
			int age = scanner.nextInt();
			System.out.println("Enter patient's Gender");
			String gender = scanner.next();
			try {
				//sending the data to th database in th form of insert
				String Query = "insert into Patients(name,age,gender) values(?,?,?)";
			    PreparedStatement preparedStatement = connection.prepareStatement(Query);
			    preparedStatement.setString(1,name);
			    preparedStatement.setInt(2,age);
			    preparedStatement.setString(3,gender);
			    //exeute method gives interger if row is successfully entered
			    int affectedRows = preparedStatement.executeUpdate();
			    //checking the data
			    if(affectedRows>0) 
			    {
			    	System.out.println("Patient Added Successfully");
			    }
			    else {
			    	System.out.print("Failed to add Patient!!");
			    }
		
	    	}catch(SQLException e)
			{
				e.printStackTrace();
		    }
		}
	//method to viewpatients
	public void viewPatients()
	{
		String query = "select * from patients";
		
		try {
			//storting in resultset
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			//value from database
			ResultSet resultset = preparedStatement.executeQuery();
			System.out.println("Patients: ");
			System.out.println("+-------------+------------+-------+----------+");
			System.out.println("|Patient Id   | Name       | Age   | Gender   |");
			System.out.println("+-------------+------------+-------+----------+");
			while(resultset.next())//run the loop as long as data exits
			{
				//connect to appointment
		     	int id = resultset.getInt("id");
				String name = resultset.getString("name");
				int age = resultset.getInt("age");
				String gender = resultset.getString("gender");
				
				
				System.out.printf("|%-13s|%-21s|%-6s|%-10s|\n",id,name,age,gender);
				System.out.println("+----------------+--------+-------+----------+");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public boolean getPatientByID(int id)
	{
		String query="select * from patients where ID = ?";
		
		try {
			//retrieve the data
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, id);
			ResultSet resultset = preparedstatement.executeQuery();
			if(resultset.next())
			{
				return true;
			}
			else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
	         
		}
		return false;
	}

}
