package core;
import java.sql.*;
public class DBGrabber {
	private static final String DRIVER = "com.mysql.jdbc.Driver";							//Location of the driver
	private static final String URL="jdbc:mysql://Excalibur.sru.edu:3306/Multi-Modal";		//URL to connect to the database
	private static final String USER = "thangiah";											//Default user name
	private static final String PSWD="thangiah12345";				
	
	public static void populateCustomer()
	{
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// set this to a MS Access DB you have on your machine
			String filename = "./Routing Data.mdb";
			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database+= filename.trim() + ";DriverID=22;READONLY=true}"; // add on to the end 
			// now we can get the connection from the DriverManager
			Connection con = DriverManager.getConnection( database ,"",""); 
			Class.forName(DRIVER);
			Connection excal = DriverManager.getConnection(URL, USER, PSWD);
			Statement s = con.createStatement();
			Statement e = excal.createStatement();
			s.execute("Select * from Customers");
			ResultSet rs = s.getResultSet(); 
			if (rs != null) 
				while ( rs.next() )
				{
					System.out.println("Data from Customer: " + rs.getString(1) );
					e.execute("Insert INTO Customer (CompanyName, CustCode, CustomerAddress, CustCodeDiv)" +
							"VALUES ('"+rs.getString(4)+"','"+rs.getString(2)+"','"+rs.getString(5)+"','"+
							rs.getInt(3)+"')");
				}
			con.close();
			excal.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
	}
	
	public static void populateCarrier()
	{
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// set this to a MS Access DB you have on your machine
			String filename = "./Routing Data.mdb";
			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database+= filename.trim() + ";DriverID=22;READONLY=true}"; // add on to the end 
			// now we can get the connection from the DriverManager
			Connection con = DriverManager.getConnection( database ,"",""); 
			Class.forName(DRIVER);
			Connection excal = DriverManager.getConnection(URL, USER, PSWD);
			Statement s = con.createStatement();
			Statement e = excal.createStatement();
			s.execute("Select * from Carriers");
			ResultSet rs = s.getResultSet(); 
			String areaCode;
			if (rs != null) 
				while ( rs.next() )
				{
					areaCode = rs.getString(6);
					if(areaCode == null)
						areaCode = "0";
					e.execute("Insert INTO Carriers (CarrierCode, CarrierName, AreaCode, FaxNumber, SafetyRating, " +
							"SafetyRateDate, Authorize, ContractDate, InsEndDate, EmailAddress, SendByFax," +
							"SendByEmail, costModifierTruck, costModifierShip, costModifierBike, costModifierCargoShip"+
							", costModifierRail, costModifierPlane)" +
							"VALUES ('"+rs.getString(2)+"','"+rs.getString(3)+"','"+areaCode+"','"+
							rs.getString(7)+"','"+rs.getInt(9)+"','"+rs.getString(10)+"','"+rs.getInt(11)+
							"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+rs.getString(8)+"','"+
							rs.getShort(4)+"','"+rs.getShort(5)+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"')");
					/*
					System.out.println("'"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(6)+"','"+
							rs.getString(7)+"','"+rs.getInt(9)+"','"+rs.getString(10)+"','"+rs.getInt(11)+
							"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+rs.getString(8)+"','"+
							rs.getShort(4)+"','"+rs.getShort(5)+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"')");
					*/
				}
			con.close();
			excal.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		//populateCustomer();
		//populateCarrier();
	}
}
