package system;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class StockItemService 
{
	private int itemNumber;
	private String itemDescription;
	private long itemPrice;
	
	StockItemDao stockDao = new StockItemDao();
	
	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public long getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(long itemPrice) {
		this.itemPrice = itemPrice;
	}

	public StockItemService() 
	{
		
	}
	
	public StockItemService(int itemNumber)
	{
		this.itemNumber=itemNumber;
	}
	
	public StockItemService(int itemNumber, String itemDescription, long itemPrice) 
	{
		super();
		this.itemNumber = itemNumber;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}
	
	
	public int addStockItem()
	{	
		StockItemService obj=new StockItemService(this.itemNumber,this.itemDescription,this.itemPrice);
		
		ArrayList<StockItemService>list=new ArrayList<>();
		list.add(obj);
		int i = stockDao.insertItem(list);
		if(i>0)
		{
			System.out.println("Stock Item added Successfully!...");	
		}
		return i;
	}
		
	public void deleteStockItem(int itemNumber) throws SQLException
	{	
		int i = stockDao.deleteItem(itemNumber);
		if(i>0)
		{
			System.out.println("Stock Item deleted Successfully!...");	
		}
		
	}
	
	@Override
	public String toString() {
		return "" + itemNumber + "\t\t" + itemDescription + "\t\t"
				+ itemPrice;
	}

	public int getStockItem() throws SQLException
	{
		ResultSet rs = stockDao.getItem();
		int rowCount=0;
		while(rs.next())
		{	
			rowCount++;
		}
		return rowCount;
	}
	
}