//======================================================================================//
//							Software Engineering MMRP Project							//
//									Dr. Sam Thangiah									//
//					Dan Miller, Zach Petrusch, Chris Solomon, and Jordan Schiller		//
//======================================================================================//
//										Truck Class										//
//======================================================================================//
// Purpose:																				//
//																						//
//======================================================================================//

package core;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
public class Truck extends Vehicle {											
	
	//Default Variables
	private final static String DEFAULT_TRUCK_NAME = "defaultTruckName";
	
	/**
	 * The default Truck constructor
	 */
	public Truck()
	{
		super.setTravelMode(Vehicle.TravelModes.TRUCK);					//Set the TravelMode to Truck
		this.setCarrier(new Carrier());									//Set the default Truck Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Truck status to RUNNING
		this.name = DEFAULT_TRUCK_NAME;									//Set the Truck name					
		this.MarkNew();													//Mark the Truck as new
		this.MarkClean();												//Mark the Truck as clean
		this.MarkUndeleted();
	}//End of the default Truck constructor
	
	/**
	 * This is the argumented Truck constructor
	 * @param id This is the id of the new Truck 
	 */
	public Truck(int id)
	{
		this.id=id;														//Set the Truck's id
		super.setTravelMode(Vehicle.TravelModes.TRUCK);					//Set the TravelMode to Truck
		this.setCarrier(new Carrier());									//Set the default Truck Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Truck status to RUNNING
		this.name = DEFAULT_TRUCK_NAME;									//Set the Truck name					
		this.MarkOld();													//Mark the Truck as old
		this.MarkClean();												//Mark the Truck as clean
	}//End of the argumented Truck constructor
	
	/**
	 * This function overrides the parent's Update function and will handle changes made to the Truck object in the database
	 */
	@Override
	public boolean Update() 
	{
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the Truck is new insert it into the database by executing the following
				executeCommand("Insert into Truck (TruckName,Carrier,Status) Values ('"+
						getVehicleName() + "','" + this.getCarrier().getId() + "','"+this.getStatus()+"')");
				//Grab this Truck from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select TruckID from Truck where TruckName = '" + this.getVehicleName() + "' AND Carrier = '"+this.getCarrier().getId()+
						"' AND Status = '" + this.getStatus()+"'");
				//If this Truck exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(temp.size()-1).get("TruckID");									//Set the Truck id
					MarkClean();																	//Mark the Truck as clean
					MarkOld();																		//Mark the Truck as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Truck is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Truck Set TruckName = '" + this.getVehicleName() + "' , Carrier = '"+this.getCarrier().getId()+
						 "' , Status = '" + this.getStatus() + "', Deleted = " + this.isDeleted() + " Where TruckID = " +this.id);
					MarkClean();																	//Mark the Truck as clean
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);														//Print the error
			ex.printStackTrace();
			return false;
		}//End of catch block
		
	}//End of overridden Update()

	/**
	 * This is the overridden Delete function of the parent class and will mark this Truck as deleted in the database
	 */
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Update Truck Set Deleted = true Where TruckID = " + this.id);							//Delete this Truck
			//Now we need mark all the segments that use this truck as deleted
			executeQuery("Update Segment set Deleted = true Where VehicleID = " + this.id + " AND ModeType = '" +this.mode.toString() + "'");
			this.MarkDeleted();
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);														//Print the error
			ex.printStackTrace();
			return false;
		}//End of catch block

	}//End of the overridden Delete()

	/**
	 * This function will load a Truck from the database
	 * @param id This is the id of the Truck to load from the database
	 * @return Returns the Truck loaded from the database
	 */
	public static Truck Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Truck where TruckId = " + id  + " AND Deleted = false");
			if(temp.size()>0)
			{
				Truck t = BuildFromDataRow(temp.get(0));
				return t;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
 		return null;
	}//End of Load(int id)
	
	/**
	 * This function returns an ArrayList of Trucks loaded from the database, based on the given where clause
	 * @param where This is the where clause determining which Trucks to load from the database
	 * @return Returns an ArrayList of Trucks loaded from the database based on the given where clause 
	 */
	public static ArrayList<Truck> LoadAll(String where)
	{
		ArrayList<Truck> returnList = new ArrayList<Truck>();
		try 
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Truck " +where   + " AND Deleted = false");
			for(int i = 0; i<temp.size();i++)
			{
				Truck t = BuildFromDataRow(temp.get(i));
				returnList.add(t);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
		return returnList;
	}//End of LoadAll(String where)
	
	/**
	 * This function builds objects from returned data from SQL queries against our database
	 * @param data This is the data that will be used to build the Truck object
	 * @throws SQLException
	 */
	public static Truck BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		//This code grabs each element that will be found in the database on the Truck table and set the appropriate values for a new Truck
		Truck t = new Truck((Integer)data.get("TruckID"));
		t.setVehicleName((String)data.get("TruckName"));
		t.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
		t.setStatus((String)data.get("Status"));	
		if(Boolean.getBoolean(data.get("Deleted").toString()))
			t.MarkDeleted();
		else
			t.MarkUndeleted();
		t.MarkClean();																//Mark the Truck as clean
		t.MarkOld();
		return t;
			
	}//End of BuildFromDataRow(Map<String,Object> data)

	public static String getDefaultName() {
		// TODO Auto-generated method stub
		return DEFAULT_TRUCK_NAME;
	}

}//End of Truck Class
