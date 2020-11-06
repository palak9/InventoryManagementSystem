/*
 * This DAO class has the following functionalities : 
 * 1. Place Order
 * 2. Total Monthly Income
 * 3. Customers with highest number of orders.
 * 4. Total no. of orders placed in the specified duration.
 * 5. Total no. of orders placed on monthly basis.
 * 
 * */


package system;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;


public class PurchaseOrderDao 
{
	private boolean isShipped=false;
	Connection con = ConnectionManager.createConnection();
	CustomerService c; 
	StockItemService s;
	PurchaseOrderService p;
	long cost;
	Statement stmt;
	ResultSet rs=null;
	int quantity;
	Date orderDate;
	Date shipDate;
	int days; 
	String message="";

	public PurchaseOrderDao()
	{
		
	}	
	
	public PurchaseOrderDao(CustomerService c, StockItemService s, int quantity) 
	{
		long currentDate=System.currentTimeMillis();
		this.c = c;
		this.s = s;
		this.quantity = quantity;
		this.orderDate = new java.sql.Date(currentDate);
		Random random = new Random();
		days = random.nextInt(6);
		if(days > 4) {
			message="Your order is delayed by : "+(days-4)+" days";
		}
		this.shipDate = Date.valueOf(orderDate.toLocalDate().plusDays(days));
	}
	
	void isShipped()
	{
		isShipped=true;
	}
	
	//Insert the order placed into PurchaseOrder table and update the purchase count of customer.
	public int placeOrder() {
		long cost=0;
		int orderCount=0;
		try 
		{
			PreparedStatement preparedStmt = con.prepareStatement("INSERT INTO PurchaseOrder values(?, ?, ?, ?, ?, ?)");
			preparedStmt.setInt(1, this.c.getCustomerId());
			preparedStmt.setInt(2, this.s.getItemNumber());
			preparedStmt.setInt(3, this.quantity);			
			ResultSet rs = preparedStmt.executeQuery("select * from StockItems");  
			while(rs.next())  {
				if(rs.getInt(1) == this.s.getItemNumber()) {
					cost = rs.getLong(3)*this.quantity;
					break;
				}
			}
			preparedStmt.setLong(4, cost);
			preparedStmt.setDate(5,this.orderDate);
			preparedStmt.setDate(6, this.shipDate);
			int count=preparedStmt.executeUpdate();
			if(count>0) 
			{
				String query1 = "SELECT * FROM customers WHERE customerId=?";
				PreparedStatement stmt = con.prepareStatement(query1);
				stmt.setInt(1, this.c.getCustomerId());
				ResultSet result=stmt.executeQuery();
				while(result.next()) {
					orderCount = result.getInt(10);	
					System.out.println(orderCount);
				}
				String query = "UPDATE customers SET PurchaseCount=? WHERE customerId=?";
				PreparedStatement preparedStmt2 = con.prepareStatement(query);
				preparedStmt2.setInt(1, orderCount+1);
				preparedStmt2.setInt(2, this.c.getCustomerId());
				preparedStmt2.executeUpdate();
				preparedStmt2.close();
				stmt.close();
				
				String filename="resource/note/customer.txt";
				try (FileWriter out = new FileWriter(filename);){
					out.write(String.valueOf(" CustomerID :"+this.c.getCustomerId())+" \n");
					out.write(String.valueOf(" Order Item :"+this.s.getItemNumber())+"\n ");
					out.write(String.valueOf("Quantity :"+this.quantity)+" \n");
					out.write(String.valueOf(" Cost : "+cost));
					System.out.println("Bill generated!..");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(this.message != "") {
					System.out.println(this.message);
					System.out.println("Your order is placed succesfully!.... Thank you, See you next time again!");
					isShipped();
					System.out.println("Your order will be shipped on "+this.shipDate);
				}
				else {
					System.out.println("Your order is placed succesfully!.... Thank you, See you next time again!");
					isShipped();
					System.out.println("Your order will be shipped on "+this.shipDate);
				}		
									
			}	
			return 1;
			
		} 
		catch (SQLException e) 
		{
			e.getStackTrace();
		}
		return 0;
	}
	
	
	//To calculate the total Income on monthly basis.
	public ResultSet amountCollection() 
	{
		try 
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT YEAR(orderDate), MONTH(orderDate), SUM(cost) FROM PurchaseOrder group by  Year(orderDate), MONTH(orderDate);");  
		} 
		catch (SQLException e) 
		{
			System.out.println("Could not retrieve the data to calculate the total monthly Income");
		}	
		return rs;
	}
	
	
	//Customer with highest number of orders placed.
	public ResultSet maxOrdersCustomer() 
	{
		try 
		{
		   stmt = con.createStatement();
		   rs = stmt.executeQuery("SELECT MAX(PurchaseCount), customerId FROM customers");  
		} 
		catch (SQLException e) 
		{
		   System.out.println("Sorry, could not display the count of Customer with highest no of orders...");
		}
		return rs;
	}    	
	
	
	//Orders placed within the specified duration Date1 to Date2.
	public ResultSet ordersBetween(Date d1, Date d2) 
	{	
		try 
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM PurchaseOrder WHERE orderDate BETWEEN ? and ?");
			ps.setDate(1, d1);
			ps.setDate(2, d2);
			rs = ps.executeQuery();
		} 
		catch (SQLException e)
		{
			System.out.println("Could not retrieve the data!...");
		}	
		return rs;
	}	
	
	
	//To fetch the total no of orders placed on monthly basis.
	public ResultSet monthlyOrders() 
	{	
		try 
		{
			PreparedStatement ps = con.prepareStatement("SELECT count(customerId) AS TOTAL_COUNT, MONTH(orderDate) FROM PurchaseOrder GROUP BY MONTH(orderDate)");
			rs = ps.executeQuery();
		} 
		catch (SQLException e)
		{
			System.out.println("Could not retrieve the data of total no. of orders placed on monthly basis!...");
		}	
		return rs;
	}	
	
}