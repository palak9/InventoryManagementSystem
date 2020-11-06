package TestSystem;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import system.ConnectionManager;
import system.StockItemService;

public class TestStockItemService
{
	Connection con;
	StockItemService obj = new StockItemService();
	
	  //Test Case 1 : Pass - To test for the successful Database Connection 
		@Test
		public void setUp() 
		{
			con = ConnectionManager.createConnection();
			assertEquals(con != null, true);
		}	
		
	  //Test Case 2: Pass - To test for successful addition of Stock Item
		@Test 
		public void testAddStockItem()
		{
			StockItemService obj=new StockItemService();
			int i=obj.addStockItem();
			assertTrue(i>0);
		}
		
	  //Test Case 3: Pass -  To test for minimum 10 records in StockItem table
		@Test 
		public void testGetStockItem() throws SQLException
		{
			int i=obj.getStockItem();
			assertEquals(10,i);
		}
}
