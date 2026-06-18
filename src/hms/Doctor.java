package hms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	
	private Connection connection;
	
	
	public Doctor(Connection connection)
	{
		this.connection = connection;
	}
public void viewDoctor()
{
	String query = "select * from Doctor";

try {
	PreparedStatement preparedstatement = connection.prepareStatement(query);
	ResultSet resultset = preparedstatement.executeQuery();
	System.out.println("Doctor: ");
	System.out.println("+-------------+------------+------------------+");
	System.out.println("|Doctor Id    | Name       | Specilization    |");
	System.out.println("+-------------+------------+------------------+");
	while(resultset.next())
	{
		int id = resultset.getInt("id");
		String name = resultset.getString("name");
		String Specilization =resultset.getString("specilization");
		System.out.printf("|%-13s|%-30s|%-15s|\n",id,name,Specilization);
		System.out.println("+------------+-------+----------+");
	}
}catch(SQLException e)
{
	e.printStackTrace();
}
}
public boolean getDoctorById(int id)
{
	String query = "select * from Doctor where ID = ?";
		try {
			
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
	         return false;
		}
	}
}


