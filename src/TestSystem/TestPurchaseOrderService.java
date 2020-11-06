/*
 * This test class is designed to perform Unit Testing of PurchaseOrderService class.
 * 
 * */
package TestSystem;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;
import system.ConnectionManager;
import system.PurchaseOrderDao;
import system.PurchaseOrderService;

public class TestPurchaseOrderService 
{	
	Connection con;
	PurchaseOrderService service = new PurchaseOrderService();
	
	//Test Case 1 : Pass - To test for the successful Database Connection 
	@Test
	public void setUp() 
	{
		con = ConnectionManager.createConnection();
		assertEquals(con != null, true);
	}	
	
	//Test case 2 :Pass - With valid inputs
	@Test
	public void testPlaceOrder1()
	{
		int customerId = 111;
		int stockId = 101;
		int quantity = 2;
		int actual = service.placeOrder(customerId, stockId, quantity);
		assertEquals(1, actual);
	}
	
	//Test case 3 : Fail - With invalid inputs 
	@Test
	public void testPlaceOrder2()
	{
		int customerId = 1000; //Invalid customerId
		int stockId = 101;
		int quantity = 2;	
		int actual = service.placeOrder(customerId, stockId, quantity);
		assertEquals(1, actual);
	}
	
	//Test Case 4 : Pass - Total no of records fetched == 3
	@Test
	public void testAmountCollection()
	{
	  int actual = service.printAmountCollection();
	  assertEquals(3, actual);
	}
	
	//Test case 5 : Pass - Customer ID with maximum no. of orders == 101
	@Test
	public void testPrintMaxOrderCustomerID() {
		int actual = service.printMaxOrderCustomerId();
		assertEquals(101, actual);
	}
	
	//Test Case 6 : Fail - Total no of Purchase orders in the provided duration == 3
	@Test
	public void testOrdersBetween1() 
	{
	 	 String d1 = "2020-09-20";
		 Date date1 = Date.valueOf(d1);
		 String d2 = "2020-09-25";
		 Date date2 = Date.valueOf(d2);
		 int actual = service.orderBetween(date1, date2);
		 assertEquals(5, actual);
	}
	
	//Test Case 7 : Pass - Total no of Purchase orders in the provided duration == 37
	@Test
	public void testOrdersBetween2() 
	{
	 	 String d1 = "2020-09-25";
		 Date date1 = Date.valueOf(d1);
		 String d2 = "2020-10-01";
		 Date date2 = Date.valueOf(d2);
		 int actual = service.orderBetween(date1, date2);
		 assertEquals(37, actual);
	}
	
	//Test Case 8 : Pass - Total no. of rows fetched == 3
	@Test
	public void testMonthlyOrders() 
	{
		int actual = service.printMonthlyOrdersPlaced();
		assertEquals(3, actual);
	}	
}