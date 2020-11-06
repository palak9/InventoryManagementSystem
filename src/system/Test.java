package system;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

public class Test 
{
	static String choice1;
	static String choice2;
	
	public static void main(String[] args) 
	{
		File dir = new File("resource/note");
		File file1 = new File(dir, "LogText.txt");
		Log my_log=null;
		try 
		{
			file1.createNewFile();
			my_log = new Log("LogText.txt");
		}
		catch (IOException e3) 
		{
			e3.printStackTrace();
		}
		catch (SecurityException e3) 
		{
			e3.printStackTrace();
		}
		
		my_log.logger.setLevel(Level.INFO);
		my_log.logger.info("Welcome to Order Management System!");
		
		Scanner sc = new Scanner(System.in);
		int select1=0;
		int select2=0;
		int select3=0;
				
		System.out.println("1. Admin \n2. Customer");
		select1 = sc.nextInt();
		switch(select1)
		{
		case 1 : 
				do
				{
					System.out.println("1. Add Stock Item \n2. Get Customer Details \n3. Get Total Monthly Income \n4. Delete Stock Item \n5. Orders Between \n6. Customer with MAX Orders \n7. Monthly Orders Placed");
					select2 = sc.nextInt();
					switch(select2) 
					{
						case 1 : System.out.println("Please enter the new Stock Id :");
					     		 int stockID = sc.nextInt();
					     		 System.out.println("Enter the stock item description (name of the product) : ");
					     		 String description = sc.next();
					     		 System.out.println("Enter the Price of the product : ");
					     		 long stockItemPrice = sc.nextLong();
					     		 StockItemService s = new StockItemService(stockID, description, stockItemPrice); 
					     		 s.addStockItem(); 
					     		 my_log.logger.warning("Stock item is added");
					     		 System.out.println("================================================================================");
					     		 break;
					     		 
					     		 
						case 2 : System.out.println("Customer Details : ");
								 CustomerService c = new CustomerService();
								 try 
								 {	 
									 c.displayCustomer();
								 } 
								 catch (SQLException e1) 
								 {
									 my_log.logger.info("Exception occurred ..."+e1.getMessage());
							         e1.printStackTrace();
						         }
								 System.out.println("================================================================================");
								 break;
								 
						case 3 : System.out.println("The Monthly Income Report : ");
								 PurchaseOrderService obj=new PurchaseOrderService();
		  						 obj.printAmountCollection();
		  						 System.out.println("================================================================================");
								 break;									
								 
						case 4 : System.out.println("Enter the Stock Item ID to be deleted  : ");
								 int stockIDNumber = sc.nextInt();
								 StockItemService s2 = new StockItemService();
								 try 
								 {
									 s2.deleteStockItem(stockIDNumber);
								 } 
								 catch (SQLException e) 
								 {
									 my_log.logger.info("Exception occurred ..."+e.getMessage());
									 e.printStackTrace();
								 }
								 System.out.println("================================================================================");
								 break;
								 
						case 5 : System.out.println("Enter Date 1 : (yyyy-mm-dd)");
								 String d1 = sc.next();
								 Date date1 = Date.valueOf(d1);
								 System.out.println("Enter Date 2 : (yyyy-mm-dd)");
								 String d2 = sc.next();
								 Date date2 = Date.valueOf(d2);
								 PurchaseOrderService pService = new PurchaseOrderService();
								 pService.orderBetween(date1, date2);
								 break;
								 
						case 6 : System.out.println("Customer with highest number of Orders : ");
								 PurchaseOrderService pService1 = new PurchaseOrderService();
								 pService1.printMaxOrderCustomerId();
								 System.out.println("================================================================================");
								 break;

						case 7 : System.out.println("Orders placed on monthly basis are : ");
								 PurchaseOrderService pService2 = new PurchaseOrderService();
								 pService2.printMonthlyOrdersPlaced();
								 System.out.println("================================================================================");
								 break;
						}
					System.out.println("Do you want to continue?(y/n)...");
					choice1 = sc.next();
					
				}while(choice1.equalsIgnoreCase("y"));
			    break;
		
		case 2 : 
				do 
				{
					System.out.println("1. Register \n2. Stock Item List \n3. Place Order");
					select3 = sc.nextInt();
					switch(select3)
					{
					case 1 :System.out.println("Please add the following details...");
					 		System.out.println("ID : ");
					 		int id = sc.nextInt();
					 		System.out.println("First Name : ");
					 		String firstName = sc.next();
					 		System.out.println("last Name : ");
					 		String lastName = sc.next();
					 		System.out.println("Email ID : ");
					 		String email = sc.next();
					 		System.out.println("Contact No : ");
					 		String contact = sc.next();
					 		System.out.println("Street : ");
					 		String street = sc.next();
					 		System.out.println("City : ");
					 		String city = sc.next();
					 		System.out.println("State : ");
					 		String state = sc.next();
					 		System.out.println("Pincode : ");
					 		long pincode = sc.nextLong();
					 		CustomerService customer = new CustomerService(id, firstName, lastName, email, contact, street, city, state, pincode);  
					 		customer.addCustomer();
					 		System.out.println("================================================================================");
					 		break;
					
					case 2 : System.out.println("Stock Items Available : ");
							 StockItemService s1 = new StockItemService();
							 try 
							 {
								 System.out.println("StockItem ID "+"\t"+"Description"+"\t"+"Stock Price");
								 s1.getStockItem();
							 }
							 catch (SQLException e1) 
							 {
								 my_log.logger.info("Exception.."+e1.getMessage());
								 e1.printStackTrace();
							 }
							 System.out.println("================================================================================");
							 break;
							 
							 
					case 3 : System.out.println("Please enter you CustomerID : ");
					 		 int customerID = sc.nextInt();
					 		 CustomerService c = new CustomerService();
					 		 try {
					 			 if(c.getCustomer(customerID)) 
					 			 {
					 				System.out.println("Here are the available items : ");
							 		 StockItemService stock = new StockItemService();
							 		 try 
							 		 {
							 			 stock.getStockItem();
							 		 }
							 		 catch (SQLException e) 
							 		 {
							 			my_log.logger.info("Exception.."+e.getMessage());
							 			 System.out.println("We could not display the stock details.. sorry for the inconvinience!...");
							 		 }
							 		 System.out.println("Enter the StockItemID you want to purchase");
							 		 int stockItemID = sc.nextInt();
							 		 System.out.println("Please enter the required qauntity : ");
							 		 int quantity = sc.nextInt();
							 		 PurchaseOrderService obj=new PurchaseOrderService();
							 		 int result = obj.placeOrder(customerID,stockItemID,quantity);							 		 
							 		 System.out.println("================================================================================");
					 		     }
					 			 else 
					 			 {
					 				 System.out.println("Invalid Customer ID.. Please Register!...");
					 			 }
					 		 } 
					 		 catch (SQLException e1)
					 		 {
					 			my_log.logger.info("Exception.."+e1.getMessage());
					 			 e1.printStackTrace();
					 		 }
					 		 break; 
					}
					System.out.println("Do you want to continue?(y/n)...");
					choice2 = sc.next();
				}while(choice2.equalsIgnoreCase("y"));
		}	
	}
}