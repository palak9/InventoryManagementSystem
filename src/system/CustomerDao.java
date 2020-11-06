/*
 * This DAO class has the following functionalities : 
 * 1. Insert Customer into Database (Registration details). 
 * 2. Fetch customer details from Database.
 * 
 * */

package system;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDao 
{
	
	Connection con=ConnectionManager.createConnection();
	CustomerService c1;
	
	 int insertCustomer(ArrayList<CustomerService> list) throws SQLException {
		 
		int i=0;
		try 
		{
			PreparedStatement stmt=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?,?)");   
			
			for(CustomerService l:list) {
				stmt.setInt(1,l.getCustomerId());  
				stmt.setString(2,l.getFirstName());
				stmt.setString(3,l.getLastName());
				stmt.setString(4,l.getEmailId());
				stmt.setString(5,l.getContactNo());
				stmt.setString(6,l.getStreet());
				stmt.setString(7,l.getCity());
				stmt.setString(8,l.getState());
				stmt.setLong(9,l.getPinCode());
			}
			stmt.setInt(10, 0);
			i=stmt.executeUpdate(); 
			System.out.println("Customer added successfully!..."); 
		}
		catch(MySQLIntegrityConstraintViolationException e1)
		{
			e1.printStackTrace();
		}
		return i;
	}
		
	 ResultSet selectCustomers()
		{	
			Statement stmt;
			ResultSet rs=null;
			try 
			{
				stmt = con.createStatement();
				rs=stmt.executeQuery("select * from customers"); 
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}  
			return rs;
		}

	
}
