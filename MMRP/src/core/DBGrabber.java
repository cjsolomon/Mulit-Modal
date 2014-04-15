package core;
import java.sql.*;
import java.util.ArrayList;
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
			String rateDate;
			if (rs != null) 
				while ( rs.next() )
				{
					areaCode = rs.getString(6);
					rateDate = rs.getString("SafetyRateDate");
					if(rateDate == null)
						rateDate = "January 1 1970";
					if(areaCode == null)
						areaCode = "0";
					e.execute("Insert INTO Carriers (CarrierCode, CarrierName, AreaCode, FaxNumber, SafetyRating, " +
							"SafetyRateDate, Authorize, ContractDate, InsEndDate, EmailAddress, SendByFax," +
							"SendByEmail, costModifierTruck, costModifierShip, costModifierBike, costModifierCargoShip"+
							", costModifierRail, costModifierPlane)" +
							"VALUES ('"+rs.getString(2)+"','"+rs.getString(3)+"','"+areaCode+"','"+
							rs.getString(7)+"','"+rs.getInt(9)+"','"+rateDate+"','"+rs.getInt(11)+
							"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+rs.getString(8)+"','"+
							rs.getShort(4)+"','"+rs.getShort(5)+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"')");
				}
			con.close();
			excal.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public static void populateShips()
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
			s.execute("Select * from Ships ");
			ResultSet rs = s.getResultSet(); 
			String carrierName;
			int carrierIndex;
			if (rs != null) 
				while ( rs.next() )
				{
					carrierName = rs.getString(3);
					e.execute("Select CarrierID from Carriers where CarrierName = '" + carrierName + "'");
					ResultSet es = e.getResultSet();
					if(es != null && es.next())
					{
						//es.next();
						carrierIndex = es.getInt("CarrierID");
						System.out.println("In database already");
					}
					else
					{
						System.out.println("Not in database");
						/*Carrier instanceCarrier = new Carrier();
						instanceCarrier.setCarrierName(carrierName);
						instanceCarrier.Update();*/
						e.execute("Insert INTO Carriers (CarrierCode, CarrierName, AreaCode, FaxNumber, SafetyRating, " +
								"SafetyRateDate, Authorize, ContractDate, InsEndDate, EmailAddress, SendByFax," +
								"SendByEmail, costModifierTruck, costModifierShip, costModifierBike, costModifierCargoShip"+
								", costModifierRail, costModifierPlane)" +
								"VALUES ('"+"defaultCarrierCode"+"','"+carrierName+"','"+"0"+"','"+
								"123-456-7890"+"','1','January 1 1970','0','January 1 1970','January 1 1970','default@default.com','"+
								"0"+"','0','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"')");
						e.execute("Select CarrierID from Carriers where CarrierName = '" + carrierName + "'");
						es = e.getResultSet();
						carrierIndex = es.getInt("CarrierID");
					}
					e.execute("Insert INTO Truck (TruckName, Carrier, Status)"+
							"VALUES ('"+rs.getInt(4)+"','"+carrierIndex+"','RUNNING')");
					System.out.println(rs.getInt(1));
				}
			con.close();
			excal.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public static void populateTruck()
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
			s.execute("Select * from Trucks ");
			ResultSet rs = s.getResultSet(); 
			String carrierName;
			int carrierIndex;
			if (rs != null) 
				while ( rs.next() )
				{
					carrierName = rs.getString(3);
					e.execute("Select CarrierID from Carriers where CarrierName = '" + carrierName + "'");
					ResultSet es = e.getResultSet();
					if(es != null && es.next())
					{
						//es.next();
						carrierIndex = es.getInt("CarrierID");
						System.out.println("In database already");
					}
					else
					{
						System.out.println("Not in database");
						/*Carrier instanceCarrier = new Carrier();
						instanceCarrier.setCarrierName(carrierName);
						instanceCarrier.Update();*/
						e.execute("Insert INTO Carriers (CarrierCode, CarrierName, AreaCode, FaxNumber, SafetyRating, " +
								"SafetyRateDate, Authorize, ContractDate, InsEndDate, EmailAddress, SendByFax," +
								"SendByEmail, costModifierTruck, costModifierShip, costModifierBike, costModifierCargoShip"+
								", costModifierRail, costModifierPlane)" +
								"VALUES ('"+"defaultCarrierCode"+"','"+carrierName+"','"+"0"+"','"+
								"123-456-7890"+"','1','January 1 1970','0','January 1 1970','January 1 1970','default@default.com','"+
								"0"+"','0','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"')");
						e.execute("Select CarrierID from Carriers where CarrierName = '" + carrierName + "'");
						es = e.getResultSet();
						carrierIndex = es.getInt("CarrierID");
					}
					e.execute("Insert INTO Truck (TruckName, Carrier, Status)"+
							"VALUES ('"+rs.getInt(4)+"','"+carrierIndex+"','RUNNING')");
					System.out.println(rs.getInt(1));
				}
			con.close();
			excal.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public static void populateShipRate()
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
			s.execute("Select * from Freight ");
			ResultSet rs = s.getResultSet(); 
			if (rs != null) 
				while ( rs.next() )
				{
					System.out.println(rs.getString("State"));
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
		//populateTruck();
		//populateShipRate();
		//populateShips();
	}
}
