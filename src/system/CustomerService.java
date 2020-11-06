/*
 * This class (Customer Service Layer) maintains the functionalities related to customers, as follows : 
 * 
 * 1. Add new customer (Register).
 * 2. Fetch customer details.
 * 3. Display customer details.
 * 
 * Data Encapsulated and abstraction is applied.
 * Exceptions are handled.
 * 
 * */

package system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerService {
	
	private int CustomerId;
	private String firstName, lastName,street, city, state,emailId,ContactNo;
	private long pinCode;
	
	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public CustomerService() 
	{
		
	}
		
	public CustomerService(int CustomerID) 
	{
		this.CustomerId = CustomerID;
	}

	public CustomerService(int customerId,  String firstName,String lastName,  String emailId,String ContactNo,String street, String city,String state, long pinCode) {
		super();
		this.CustomerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.ContactNo =ContactNo ;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
	}
	
	CustomerDao custDao = new CustomerDao();

	
	public void addCustomer() 
	{
		ArrayList<CustomerService> cs = new ArrayList<>();

		CustomerService obj = new CustomerService(this.CustomerId, this.firstName, this.lastName, this.emailId, this.ContactNo, this.street, this.city, this.state, this.pinCode);
		cs.add(obj);
		try 
		{
			custDao.insertCustomer(cs);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean getCustomer(int customerId) throws SQLException
	{
		boolean flag=false;	
		ResultSet rs = custDao.selectCustomers();	
		while(rs.next()) 
			{
				if(rs.getInt(1) == customerId)
				{
					flag=true;
					break;
				} 
			}
		 return flag;
	 }
	
	
	@Override
	public String toString() {
		return "" + CustomerId + "\t" + firstName + "\t" + lastName
				+ "\t" + street + "\t" + city + "\t" + state + "\t" + emailId + "\t"
				+ ContactNo + "\t" + pinCode;
	}
	
	public int displayCustomer() throws SQLException 
	{
		ResultSet rs = custDao.selectCustomers();
		int rowCount=0;
		while(rs.next())
		{	
			System.out.println("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t"+rs.getString(7)+"\t\t"+rs.getString(8)+"\t\t"+rs.getString(9));  
			rowCount++;
		}
		return rowCount;		
	}

} 