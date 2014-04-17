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
							"SendByEmail, costModifierTruck, costModifierBike, costModifierCargoShip"+
							", costModifierRail, costModifierPlane)" +
							"VALUES ('"+rs.getString(2)+"','"+rs.getString(3)+"','"+areaCode+"','"+
							rs.getString(7)+"','"+rs.getInt(9)+"','"+rateDate+"','"+rs.getInt(11)+
							"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+rs.getString(8)+"',"+
							rs.getShort(4)+","+rs.getShort(5)+",'"+1+"','"+1+"','"+1+"','"+1+"','"+1+"')");
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
					carrierName = carrierName.trim();
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
								"SendByEmail, costModifierTruck, costModifierBike, costModifierCargoShip"+
								", costModifierRail, costModifierPlane)" +
								"VALUES ('"+"defaultCarrierCode"+"','"+carrierName+"','"+"0"+"','"+
								"123-456-7890"+"','1','January 1 1970','0','January 1 1970','January 1 1970','default@default.com',"+
								"0"+",0,'"+1+"','"+1+"','"+1+"','"+1+"','"+1+"','"+1+"')");
						e.execute("Select CarrierID from Carriers where CarrierName = '" + carrierName + "'");
						es = e.getResultSet();
						carrierIndex = es.getInt("CarrierID");
					}
					/*e.execute("Insert INTO Truck (TruckName, Carrier, Status)"+
							"VALUES ('"+rs.getInt(4)+"','"+carrierIndex+"','RUNNING')");*/
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
					carrierName = carrierName.trim();
					e.execute("Select CarrierID from Carriers where CarrierCode = '" + carrierName + "' or CarrierName ='"+ carrierName +"'");
					ResultSet es = e.getResultSet();
					if(es != null && es.next())
					{
						es.first();
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
								"SendByEmail, costModifierTruck, costModifierBike, costModifierCargoShip"+
								", costModifierRail, costModifierPlane)" +
								"VALUES ('"+carrierName+"','"+"defaultCarrierName"+"','"+"0"+"','"+
								"123-456-7890"+"','1','January 1 1970','0','January 1 1970','January 1 1970','default@default.com',"+
								"0"+",0,'"+1+"','"+1+"','"+1+"','"+1+"','"+1+"')");
						e.execute("Select CarrierID from Carriers where CarrierCode = '" + carrierName + "' or CarrierName ='"+ carrierName +"'");
						es = e.getResultSet();
						es.first();
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
			Statement e = excal.createStatement();
			s.execute("Select * from Freight ");
			ResultSet rs = s.getResultSet(); 
			ArrayList<ShippingRate> rates = new ArrayList<ShippingRate>();
			ShippingRate temp;
			Carrier carrier;
			Location start;
			if (rs != null) 
				while ( rs.next() )
				{
					temp = new ShippingRate();
					start = new Location();
					start.setName(rs.getString("City"));
					start.setState(rs.getString("State"));
					start.setCountry("USA");
					carrier = new Carrier();
					carrier.setCarrierName(rs.getString("CarrierCode"));
					temp.setStartLocation(start);
					temp.setWeight1(rs.getInt("Weight1"));
					temp.setWeight2(rs.getInt("Weight2"));
					temp.setWeight3(rs.getInt("Weight3"));
					temp.setRate1(rs.getInt("Rate1"));
					temp.setRate2(rs.getInt("Rate2"));
					temp.setRate3(rs.getInt("Rate3"));
					temp.setMileRate(rs.getInt("MileRate"));
					temp.setFlatRate(rs.getInt("FlatRate"));
					temp.setRank(rs.getInt("Rank"));
					rates.add(temp);
				}
			for(int i = 0; i<rates.size();i++)
			{
				
			}
			con.close();
			excal.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
	}
	
	public static void populateLocations()
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
			s.execute("Select * from Freight ");
			String command;
			double numTTypes;
			ArrayList<String> inserted = new ArrayList<String>();
			ResultSet rs = s.getResultSet(); 
			String cityName;
			String stateName;
			String appended;
			if (rs != null) 
				while ( rs.next() )
				{
					cityName = rs.getString("City");
					stateName = rs.getString("State");
					appended = cityName+","+stateName;
					if(!inserted.contains(appended))
					{
						inserted.add(appended);
						command = "Insert INTO Location (Name, Latitude, Longitude, State, Country, TravelType1, TravelType2, "
								+ "TravelType3, TravelType4)"
								+ "Values ('"+cityName+"','0','0','"+stateName+"','USA',";
						numTTypes = Math.ceil(Math.random()*4);
						switch((int)numTTypes)
						{
						case 1:
							command += "'TRUCK',NULL,NULL,NULL";
							break;
						case 2:
							command += "'TRUCK','RAIL',NULL,NULL";
							break;
						case 3:
							command += "'TRUCK','RAIL','PLANE',NULL";
							break;
						case 4: 
							command += "'TRUCK','RAIL','PLANE','CARGO'";
							break;
						}
						command += ")";
						//System.out.println(command);
						e.execute(command);
					}
				}
			con.close();
			excal.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
	}
	
	public static void updatePhone()
	{
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName(DRIVER);
			Connection excal = DriverManager.getConnection(URL, USER, PSWD);
			Statement e = excal.createStatement();
			Statement x = excal.createStatement();
			x.execute("Select * from Carriers");
			ResultSet rs = x.getResultSet();
			String phone;
			String code;
			String area;
			if (rs != null) 
				while ( rs.next() )
				{
					code = rs.getString("CarrierCode");
					phone = rs.getString("FaxNumber");
					phone = phone.replace(' ', '-');
					area = rs.getString("AreaCode");
					if(phone.length() < 10 && area.length() == 3)
					{
						e.execute("UPDATE Carriers SET FaxNumber = '"+area+" "+phone+"'"+
								"Where CarrierCode = '"+code+"'");
						System.out.println("UPDATE Carriers SET FaxNumber = '"+area+" "+phone+"'"+
								" Where CarrierCode = '"+code+"'");
						phone = area+"-"+phone;
					}
					e.execute("UPDATE Carriers SET FaxNumber = '"+phone+"' WHERE CarrierCode = '"+
							code+"'");
					System.out.println("UPDATE Carriers SET FaxNumber = '"+phone+"' WHERE CarrierCode = '"+
							code+"'");
				}
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
		populateShipRate();
		//populateLocations();
		//populateShips();
		//updatePhone();  
	}
}
