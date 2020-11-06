package TestSystem;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import system.ConnectionManager;
import system.CustomerDao;
import system.CustomerService;

public class TestCustomerService 
{
	Connection con;
	CustomerService obj=new CustomerService();
	CustomerDao obj1=new CustomerDao();
	
	    //Test Case 1 : Pass - To test for the successful Database Connection 
		@Test
		public void setUp() 
		{
			con = ConnectionManager.createConnection();
			assertEquals(con != null, true);
		}	
		
	    //Test Case 2 : Pass - To test for displaying customer 	
		@Test
		public void testDisplayCustomer() throws SQLException 
		{
			int actual = obj.displayCustomer();
			assertEquals(10, actual);
		}	

		//Test case 3 :Pass - With valid inputs
		@Test
		public void testGetCustomer1()
		{
			int customerId = 111;
			int actual =obj.getCustomerId();
			assertEquals(101, actual);
		}
		
	    //Test case 4 :Pass - With Invalid inputs
		@Test
		public void testGetCusomer2()
		{
			int customerId = -21;
			int actual =obj.getCustomerId();
			assertEquals(101, actual);
		}
}