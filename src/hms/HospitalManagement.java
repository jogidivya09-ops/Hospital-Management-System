package hms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class HospitalManagement {

	private static final String url = "jdbc:mysql://localhost:3306/hospital";
	private static final String Username = "root";
	private static final String password = "@divya@123!";
	private static int rowsAffected;
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj/jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		try {
			Connection connection = DriverManager.getConnection(url,Username,password);
			Patient patient = new Patient(connection,scanner);
			Doctor doctor = new Doctor(connection);
			while(true)
			{
				System.out.println("Hospital Management System");
				System.out.println("1.Add Patient");
				System.out.println("2.view Patients");
				System.out.println("3.view Doctors");
				System.out.println("4.Book Appointment");
				System.out.println("5.Exit");
				System.out.println("Please Enter Your Choice");
				
				int choice = scanner.nextInt();
				switch(choice)
				{
				case 1:
					//Add Patient
					patient.addPatient();
					System.out.println();
					break;
				case 2:
					//view Patient
					patient.viewPatients();
					System.out.println();
					break;
				case 3:
					//view Doctors
					doctor.viewDoctor();
					System.out.println();
					break;
				case 4:
					//Book Appointment
					bookAppointment(patient, doctor, connection, scanner);
					System.out.println();
					break;
				case 5:
					System.out.println("THANK YOU ! FOR USING HOSPITAL MANAGEMENT SYSTEM");
					return;
				default:
					System.out.println("Please Enter Valid Input");
					break;
				}
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	} 
	public static void bookAppointment(Patient patient,Doctor doctor,Connection connection,Scanner scanner)
	{
		System.out.println("Please Enter Patient ID:");
		int PatientID = scanner.nextInt();

		System.out.println("Please Enter DoctorID:");
		int doctorID = scanner.nextInt();
		System.out.println("Please Enter Appointment Date(YYYY-MM-DD): ");
		String appointmentDate =scanner.next();
		if(patient.getPatientByID(PatientID)&& doctor.getDoctorById(doctorID))
		{
			if(checkDoctorAvailability(doctorID,appointmentDate,connection)) {
				String appointmentQuery = "INSERT INTO appointments (patients_id,doctor_id,Appointment_date) values(?,?,?)";
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
					preparedStatement.setInt(1, PatientID);
					preparedStatement.setInt(2, doctorID);
					preparedStatement.setString(3, appointmentDate);
					int rowsAffected = preparedStatement.executeUpdate();
					if(rowsAffected>0)
					{
						System.out.println("Appointment Booked");
					}
					else
					{
						System.out.println("Appointment could not be Booked");
					}
					}catch(SQLException e)
				{
						e.printStackTrace();
				}
				}
			else {
				System.out.println("Doctor not available on this date!");
			}
		}else {
			System.out.println("Either doctor or patient doesn't exit!!!");
			}
		}
	private static boolean checkDoctorAvailability(int doctorID, String appointmentDate, Connection connection) {
		//Count(*): getting the rows which matches the aprticular criteria
		String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND appointment_date = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorID);
			preparedStatement.setString(2, appointmentDate);
			
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()) {
				int count = resultset.getInt(1);
				if(count == 0) {
					return true;
				}
				else {
					return false;
				}
			}
	}catch(SQLException e)
		{
		e.printStackTrace();
		}
		return false;
	}
}
     
	


