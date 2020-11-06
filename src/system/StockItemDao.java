package system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public class StockItemDao {
	
	static Connection con = ConnectionManager.createConnection();
	
	int insertItem(ArrayList<StockItemService> list)
	{
		int result=0;	
		try 
		{
			PreparedStatement stmt=con.prepareStatement("INSERT into stockItems values(?,?,?)");  
			for(StockItemService l:list) {
				stmt.setInt(1,l.getItemNumber());  
				stmt.setString(2,l.getItemDescription());
				stmt.setLong(3,l.getItemPrice());
			}
			int i=stmt.executeUpdate();
			result=i;
		} 
		catch (SQLException e) 
		{
			System.out.println("Stock Item already exists ");
		}
		return result;
	}
	
	ResultSet getItem() throws SQLException
	{	
		ResultSet rs=null;
		ArrayList<StockItemService>list=new ArrayList<>();
		
		try 
		{
			Statement stmt=con.createStatement();  
			rs=stmt.executeQuery("select * from stockItems");  
			
			while(rs.next())
			{
				StockItemService obj=new StockItemService();
				obj.setItemNumber(rs.getInt(1));
				obj.setItemDescription(rs.getString(2));
				obj.setItemPrice(rs.getLong(3));
				list.add(obj);
				
			}
			for(StockItemService s:list) {
				System.out.println(s.toString());
				
			}
			 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rs;
	}
	
		
	int deleteItem(int itemNumber) throws SQLException {
		int result=0;
		try
		{	
			PreparedStatement stmt=con.prepareStatement("DELETE FROM stockItems where StockItemId=?");  
			stmt.setInt(1,itemNumber);
			int i=stmt.executeUpdate();  
			result=i;
		} 
		catch (SQLException e) 
		{
			System.out.println("Record doesn't exists.. Please check!");
		}
		return result;
	}
}