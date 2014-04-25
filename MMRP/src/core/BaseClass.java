/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 * @see java.sql
 * @see java.util.ArrayList
 * @see java.util.HashMap
 * @see java.util.Map
 */
package core;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseClass {
	private Boolean dirty;																	//The dirty/clean flag for DB management
	private Boolean newObject;																//The new/old flag for DB management
	private Boolean deleted;
	private static final String DRIVER = "com.mysql.jdbc.Driver";							//Location of the driver
	private static final String URL="jdbc:mysql://Excalibur.sru.edu:3306/Multi-Modal";		//URL to connect to the database
	private static final String USER = "thangiah";											//Default user name
	private static final String PSWD="thangiah12345";										//Default password
	public static int connectionCounter=0;
	/**
	 * Return the value of Dirty.
	 * <p>
	 * If the object extending this class has been updated since it was loaded, True is returned. Else False Is returned.
	 * @return true if Dirty, otherwise false
	 */
	public boolean isDirty()
	{
		if(dirty==null)
			return false;
		return dirty;
	}//End of isDirty()
	
	/**
	 * Return the value of newObject.
	 * <p>
	 * If the object extending this class has not be inserted into Database, True is returned. Else False Is returned.
	 * @return true if New, otherwise false
	 */
	public boolean isNew()
	{
		if(newObject==null)
			return false;
		return newObject;
	}//End of isNew()
	
	//This function sets the dirty flag to true
	/**
	 * Marks the object as Dirty
	 * <p>
	 * To be called when a change is made to the extending object
	 * 
	 */
	public void MarkDirty()
	{
		dirty=true;
	}//End of MarkDirty()
	
	/**
	 * Marks the Object as Clean
	 * <p>
	 * To be called when a change to an object is pushed to the database 
	 * 
	 */
	public void MarkClean()
	{
		dirty=false;
	}//End of MarkClean()
	
	/**
	 * Marks the object as new
	 * <p>
	 * To be called when an object is created without loading it from the database as to flag it for insert  
	 * 
	 */
	public void MarkNew()
	{
		newObject=true;
	}//End of MarkNew()
	
	/**
	 * Marks the object as old
	 * <p>
	 * To be called when the a 'new' object is inserted into database
	 */
	public void MarkOld()
	{
		newObject=false;
	}//End of MarkOld
	
	/**
	 * This function will mark this object as deleted and old so that it will be updated in the database
	 */
	public void MarkDeleted(){
		if(deleted == null){
			deleted = true;
		}
		if(!deleted){
			deleted = true;
			MarkDirty();
		}
			
	}//End of MarkDeleted()
	
	/**
	 * This function will undelete an object
	 */
	public void MarkUndeleted(){
		if(deleted == null){
			deleted = false;
		}
		if(deleted){
			deleted = false;
			MarkDirty();
		}
	}//End of MarkUndeleted()
	
	/**
	 * This function will return the boolean indicating if this object is deleted or not
	 * @return
	 */
	public boolean isDeleted(){
		return deleted;
	}//End
	
	
	
	/**
	 * Returns the connection to the database
	 * @return Connection to the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static Connection getConnection() throws SQLException,ClassNotFoundException
	{
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL,USER,PSWD);
	}//End of getConnection()
	
	/**
	 * Executes and returns the results of a query of the database
	 * @param sql The query to be executed
	 * @return ArrayList<Map<String,Object>>	maps the column name in the query to the value in the field
	 * @throws SQLException
	 */
	public static ArrayList<Map<String,Object>> executeQuery(String sql) throws Exception 
	{
		Connection c=null;
		try
		{
			c = getConnection();															//Create the connection
			connectionCounter++;
			ResultSet rs = c.createStatement().executeQuery(sql);							//Execute the query
			ResultSetMetaData md = rs.getMetaData();										//Save the metadata from the results
			ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>();		//Create a new ArrayList of Maps of Strings to Objects
			while(rs.next())
			{
				Map<String,Object> temp = new HashMap<String,Object>(md.getColumnCount());	//While we still have results create a new HashMap object 
				for(int i = 1; i<=md.getColumnCount();i++)									//For each of the columns place the string and object pair
				{
					temp.put(md.getColumnName(i),rs.getObject(i));
				}//End of for loop
				data.add(temp);																//Add the temp variable to the data
				
			}//End of while loop
			return data;																	//Return the data
			
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);												//Print out the error to the screen
			System.out.println("Number of Connections " + connectionCounter);
			ex.printStackTrace();
			return null;
		}//End of the catch block
		finally
		{
			
			if(c!=null && !c.isClosed())													//Make sure the connection is closed
				c.close();		
			Thread.sleep(1);
			//connectionCounter--;
		}//End of finally clause
	}//End of executeQuery(String sql)
	
	/**
	 * Executes a command on the database
	 * @param sql Command to be executed
	 * @return True if command successfully executed, otherwise false 
	 * @throws SQLException
	 */
	public static boolean executeCommand(String sql)throws Exception
	{
		
		Connection c=null;
		try
		{
			c = getConnection();												//Create a connection to the database\
			connectionCounter++;
			c.createStatement().execute(sql);									//Execute the SQL statement
			return true;														//Return that it was successful
			
		}//End of try block	
		catch(Exception ex)
		{
			System.out.println("Error " + ex);		
			System.out.println("Number of Connections " + connectionCounter);//Print out the error
			ex.printStackTrace();
			return false;														//Return that the SQL command failed
		}//End of catch block
		finally
		{
			if(c!=null && !c.isClosed())										//Close the connection
				c.close();
			Thread.sleep(5);
			//connectionCounter--;
		}//End of finally block
	}//End of executeCommand(String sql)
	
	//Abstract Functions
	abstract boolean Update();
	abstract boolean Delete();
	
	/**
	 * This function will restore the database
	 * @return Returns a boolean indicating if the database was restored correctly
	 */
	public static boolean RestoreDatabase(){
		boolean success = false;        
        String restorePath =System.getProperty("user.dir")+"MultiModal.sql";
        String [] executeCmd = {"C:/Program Files/MySQL/MySQL Server 5.6/bin/mysql",URL,"-u "+USER,"-p "+PSWD,"-e", " source " +restorePath};
        Process runtimeProcess;
        try {
        	runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if(processComplete ==0){
            	success=true;
            }
            else{
            	success=false;
            }
       } catch (IOException e) {
           e.printStackTrace();
           return false;
       } catch (InterruptedException e) {
    	   e.printStackTrace();
    	   return false;
       }


        return success;
 	}//End of RestoreDatabase(String dbName)
	
	/**
	 * This function will back up the database
	 * @return Returns a boolean indicating if the database was backed up successfully
	 */
	public static boolean BackupDatabase(){
          boolean success = true; 

          String executeCmd = "C:/Program Files/MySQL/MySQL Server 5.6/bin/mysqldump -v -u" + USER + " -p" + PSWD + " --database " + URL + " -r " +"MultiModal.sql ";
          Process runtimeProcess;
          try {

        	  runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        	  int processComplete = runtimeProcess.waitFor();

        	  if (processComplete == 0) {
        		  success=true;
        	  } else {
        		  success = false;
        	  }
          } catch (Exception ex) {
        	  ex.printStackTrace();
        	  return false;
          }  
          return success;
	}//End of BackupDatabase(String dbName)
	
	/**
	 * This function will permanently delete all the entries in the database that are marked deleted
	 * @return Returns a boolean indicating if the purge was successful or not
	 * @throws Exception
	 */
	public static boolean purgeDeleted() throws Exception{
		try{
			
			executeCommand("Delete from Bike Where Deleted = true");
			executeCommand("Delete from Plane Where Deleted = true");
			executeCommand("Delete from Rail Where Deleted = true");
			executeCommand("Delete from CargoShip Where Deleted = true");
			executeCommand("Delete from Truck Where Deleted = true");
			executeCommand("Delete from Location Where Deleted = true");
			executeCommand("Delete from Carrier Where Deleted = true");
			executeCommand("Delete from Segment Where Deleted = true");
			executeCommand("Delete from Shipment Where Deleted = true");
			executeCommand("Delete from Shipper Where Deleted = true");
			executeCommand("Delete from ShippingRate Where Deleted = true");
			executeCommand("Delete from TravelType Where Deleted = true");
			
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
	}//End of purgeDeleted()
	
	/**
	 * This function will generate the appropriate index or indexes on the index table in the
	 * database for appropriate data connections
	 * @return Returns a boolean indicating if indexes were added correctly
	 * @throws Exception
	 */
	public static boolean AddIndex(int vehicleID, int segmentID, int travelTypeID, Vehicle.TravelModes mode ) throws Exception{
		
		try{
			
			executeCommand("Insert into IndexTable (VehicleID, SegmentID, TravelTypeID, TravelMode, InUse) Values " +
					"(" + vehicleID + ", " + segmentID + ", " + travelTypeID + ", '" + mode.toString() + "', true)");
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}

	}//End of AddIndex()
	
	/**
	 * This function will update the index table for the given segment
	 * @return Returns a boolean indicating if indexes were updated correctly
	 * @throws Exception
	 */
	public static boolean UpdateIndex(int vehicleID, int segmentID, int travelTypeID, Vehicle.TravelModes mode ) throws Exception{
		
		try{
			
			executeCommand("Update IndexTable Set VehicleID = " + vehicleID +", TravelTypeID = " + travelTypeID +", TravelMode = '" + mode.toString() + "', InUse = true where SegmentID = "+ segmentID);
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}

	}//End of AddIndex()

}//End of BaseClass
