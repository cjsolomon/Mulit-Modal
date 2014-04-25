package core;
import java.io.*;
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
					//System.out.println("Data from Customer: " + rs.getString(1) );
					e.execute("Insert INTO shipper (CompanyName, prefCarriers, ContactName, Phone, Email)" +
						"VALUES ('"+rs.getString(4)+"','"+"defaultPreferredCarriers"+"','"+
						"defaultContactName"+"','"+"123-456-7890"+"','"+ "defaultEmailAddress"+"')");
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
			//Database set up stuff
			String filename = "./Routing Data.mdb";
			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database+= filename.trim() + ";DriverID=22;READONLY=true}"; 
			Connection con = DriverManager.getConnection( database ,"",""); 
			Class.forName(DRIVER);
			Connection excal = DriverManager.getConnection(URL, USER, PSWD);
			Statement s = con.createStatement();
			Statement e = excal.createStatement();
			
			s.execute("Select * from Freight ");  //Pull freight information from the other database 
			ResultSet rs = s.getResultSet();      //Save freight stuff in RS
			ArrayList<ShippingRate> rates = new ArrayList<ShippingRate>(); 
			ArrayList<String> cities;
			ShippingRate temp;
			String command;
			Carrier carrier;
			Location start;
			if (rs != null) 
				while ( rs.next() )  //Cycle through all freight entries in other database
				{
					/* Save the information from the freight table 
					 * into a Rates item to hold the data. Save each of these into an ArrayList (rates)
					 */
					temp = new ShippingRate();
					start = new Location();
					start.setName(rs.getString("City"));
					start.setState(rs.getString("State"));
					start.setCountry("USA");
					carrier = new Carrier();
					carrier.setCarrierName(rs.getString("CarrierCode"));
					temp.setCarrier(carrier);;
					temp.setWeight1(rs.getDouble("Weight1"));
					temp.setWeight2(rs.getDouble("Weight2"));
					temp.setWeight3(rs.getDouble("Weight3"));
					temp.setRate1(rs.getDouble("Rate1"));
					temp.setRate2(rs.getDouble("Rate2"));
					temp.setRate3(rs.getDouble("Rate3"));
					temp.setMileRate(rs.getDouble("MileRate"));
					temp.setFlatRate(rs.getDouble("FlatRate"));
					temp.setRank(rs.getInt("Rank"));
					rates.add(temp);
				}
			int startID;
			int citiesDone = 0;
			int endID;
			int carrierID;
			String travelType[] = {"TRUCK","PLANE","CARGOSHIP","RAIL"};
			ResultSet es; 
			int insertCount = 0;
			PrintWriter writer = new PrintWriter("shipRate.txt", "UTF-8");
			//Cycle through every rate entry from the other table 
			//A rate entry corresponds to every possible pair of start cities and contractor for that city
			for(int i = 0; i<rates.size();i++)
			{
				/*Result cities Array List 
				 * This holds cities that have been used as destinations for the current
				 * start city */
				cities = new ArrayList<String>();  
				/* 
				 * For this start city + contractor combo go through the rest of the rates 
				 * and set a rate to any city that isn't the start city and hasn't been used yet 
				 */
				for(int j = 0; j<rates.size(); j++)
				{
					/*if(!rates.get(j).getStartLocation().getName().equals(rates.get(i).getStartLocation().getName()) &&
							!cities.contains(rates.get(j).getStartLocation().getName()))
					{
						//Find ids for Carrier and Locations 
						e.execute("Select * From Carriers Where CarrierCode = '"+rates.get(i).getCarrier().getCarrierName()+"'");
						es = e.getResultSet();
						es.next();
						carrierID = es.getInt("CarrierID");
						//Use each travel type for this city+contractor -> city relationship
						for(int k=0; k < travelType.length; k++)
						{
							command = "INSERT INTO shippingrates (StartLocation, EndLocation, CarrierID, "
								+ "Weight1, Weight2, Weight3, Rate1, Rate2, Rate3, MileRate, FlatRate, Rank, TravelType)";
							command += "VALUES ('"+startID+"','"+endID+"','"+carrierID+"','"+rates.get(i).getWeight1()+
								"','"+rates.get(i).getWeight2()+"','"+rates.get(i).getWeight3()+"','"+
								rates.get(i).getRate1()+"','"+rates.get(i).getRate2()+"','"+rates.get(i).getRate3()+
								"','"+rates.get(i).getMileRate()+"','"+rates.get(i).getFlatRate()+"','"+
								rates.get(i).getRank()+"'"+travelType[k%travelType.length]+"')";
							writer.println(command);
							//e.execute(command);
							System.out.println("Calculating rates..");
							System.out.println("Cities completed = "+ citiesDone);
							writer.println(insertCount++);
						}
						cities.add(rates.get(j).getStartLocation().getName());
					}*/
				}
				System.out.println("Completed a start city");
				citiesDone++;
			}
			con.close();
			excal.close();
			writer.close();
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
	
	public static void updateShipment() 
	{
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName(DRIVER);
			Connection excal = DriverManager.getConnection(URL, USER, PSWD);
			Statement e = excal.createStatement();
			Statement x = excal.createStatement();
			x.execute("Select * from shipment");
			ResultSet rs = x.getResultSet();
			String command;
			/*
			if(rs != null)
				while(rs.next())
				{
					command = "UPDATE shipper SET LocationID = '" + rs.getInt("FromLocationID") + "'" + 
						" WHERE shipperID = '" + rs.getInt("shipper") + "'";
					System.out.println(command);
					//e.execute(command);
				}*/
			
			x.execute("Select * from shipper");
			rs = x.getResultSet();
			if(rs != null) 
				while(rs.next())
				{
					command = "UPDATE shipper SET CompanyName = '" + "defaultCompanyName" + "'" + 
						" WHERE CompanyName = '" + null + "'";
					System.out.println(command);
					e.execute(command);
				}
			
			/*
			ArrayList <Integer> shipperIds = new ArrayList <Integer>();
			if (rs != null) 
				while ( rs.next() )
				{
					shipperIds.add(rs.getInt("shipperID"));
				}
			x.execute("Select * from shipment");
			rs = x.getResultSet();
			int randomId;
			int idSelected;
			if(rs != null) 
				while(rs.next())
				{
					randomId = (int)Math.floor(Math.random()*shipperIds.size());
					//System.out.println(randomId);
					//System.out.println(shipperIds.get(randomId));
					command = "UPDATE shipment SET shipper = '" + shipperIds.get(randomId) + "'" +
							 " WHERE ShipmentID = '" + rs.getInt("ShipmentID") + "'";
					e.execute(command);
					System.out.println(command);
				}*/
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
		//populateLocations();
		//populateShips();
		//updatePhone();  
		//updateShipment();
		//populateShipRate();
	}
}
