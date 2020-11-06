package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

	static Connection con;
	
	public static Connection createConnection() {
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/OrderManagementSystem?autoReconnect=true&useSSL=false";
			String username = "root";
			String password = "palak@123";
			con = DriverManager.getConnection(url, username, password);
		
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
}
