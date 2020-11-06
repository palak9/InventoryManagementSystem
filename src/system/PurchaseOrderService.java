/*
 * This class contains the business logic and is the service layer. 
 * (No direct interaction with the Database)
 * 
 * Abstraction and Encapsulation is performed.
 * All necessary exceptions are handled. 
 *  
 * */
package system;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseOrderService 
{
	private Date orderDate;
	private Date shipDate;
	private boolean isShipped = false;
	private CustomerService c;
	private StockItemService s;
	private int quantity;
	int days; 
	long cost;
	String message="";
	private int orderCount;
	PurchaseOrderDao purchaseDao = new PurchaseOrderDao();
	CustomerDao custDao = new CustomerDao();

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	
	Connection con = ConnectionManager.createConnection();
	
	public int getQuantity() {
		return quantity;
	}
	
	public PurchaseOrderService() {
		
	}
	
	
	public int placeOrder(int customerId, int stockId, int quantity)
	{
		PurchaseOrderDao obj = new PurchaseOrderDao(new CustomerService(customerId), new StockItemService(stockId), quantity);
		int r = obj.placeOrder();
		return r;
	}
	
	public int printAmountCollection()
	{	
		int noOfRows = 0;
		ResultSet rs = purchaseDao.amountCollection();
		System.out.println("YEAR"+"\t|\t"+"MONTH"+"\t|\t"+"TOTAL INCOME");
		try 
		{
			while(rs.next()) 
			{
				noOfRows++;
				System.out.println(rs.getString("YEAR(OrderDate)")+"\t|\t"+rs.getString("MONTH(OrderDate)")+"\t|\t"+rs.getString("SUM(cost)") );
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return noOfRows;
	}
	
	public int printMaxOrderCustomerId()
	{	
		ResultSet rs = purchaseDao.maxOrdersCustomer();
		int result=0;
		System.out.println("MAX(PurchaseCount)"+"   "+"customerId");
		try
		{
			while(rs.next()) 
			{
				result = rs.getInt("customerId");
				System.out.println(rs.getString("MAX(PurchaseCount)")+"\t\t\t"+rs.getString("customerId"));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public int orderBetween(Date d1, Date d2) 
	{	
		int result = 0;
	    ResultSet rs = purchaseDao.ordersBetween(d1, d2);
	    System.out.println("CustomerID"+"\t"+"StockItemID"+"\t"+"Quantity"+"\t"+"Cost"+"\t\t"+"Order Date"+"\t\t"+"Ship Date");
		try
		{
			while(rs.next()) 
			{
				if(rs.isLast()) {
					result = rs.getRow();
				}
				System.out.println(rs.getInt(1)+"\t\t"+rs.getInt(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getLong(4)+"\t\t"+rs.getDate(5)+"\t\t"+rs.getDate(6));
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public int printMonthlyOrdersPlaced() 
	{	
		int result = 0;
		ResultSet rs = purchaseDao.monthlyOrders();
		try
		{
			System.out.println("TOTAL COUNT"+"\t"+"MONTH");
			while(rs.next()) 
			{
				if(rs.isLast()) {
					result = rs.getRow();
				}
				System.out.println(rs.getInt(1)+"\t\t"+rs.getInt(2));
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}	
}