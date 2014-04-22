

//======================================================================================//
//							Software Engineering MMRP Project							//
//									Dr. Sam Thangiah									//
//					Dan Miller, Zach Petrusch, Chris Solomon, and Jordan Schiller		//
//======================================================================================//
//										Rail Class										//
//======================================================================================//
// Purpose:																				//
//																						//
//======================================================================================//
package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Rail extends Vehicle {
	
	//Default Variables
	private final String DEFAULT_RAIL_NAME = "defaultRailName";
	
	/**
	 * This is the default Rail constructor
	 */
	public Rail()
	{
		super.setTravelMode(Vehicle.TravelModes.RAIL);					//Set the TravelMode to Rail
		this.setCarrier(new Carrier());									//Set the default Rail Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Rail status to RUNNING
		this.name = DEFAULT_RAIL_NAME;									//Set the Rail name					
		this.MarkNew();													//Mark the Rail as new
		this.MarkClean();												//Mark the Rail as clean
		this.MarkUndeleted();											//Mark the Rail as not deleted
	}//End of default Rail constructor
	
	/**
	 * This is the argumented Rail constructor that takes an id
	 * @param id This is the new id of the Rail
	 */
	public Rail(int id)
	{
		this.id=id;														//Set the id
		super.setTravelMode(Vehicle.TravelModes.RAIL);					//Set the TravelMode to Rail
		this.setCarrier(new Carrier());									//Set the default Rail Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Rail status to RUNNING
		this.name = DEFAULT_RAIL_NAME;									//Set the Rail name					
		this.MarkClean();												//Mark the Rail as clean
	}//End of the argumented constructor Rail(int id)
	
	/**
	 * This function overrides the parent's Update function and will handle changes made to the Rail object in the database
	 */
	@Override
	public boolean Update() 
	{
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the Rail is new insert it into the database by executing the following
				executeCommand("Insert into rail (RailName,Carrier,Status) Values ('"+
						getVehicleName() + "','" + this.getCarrier().getId() +"','"+this.getStatus()+"')");
				//Grab this Rail from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select RailID from rail where RailName = '" + this.getVehicleName() + "' AND Carrier = '"+this.getCarrier().getId()+
						 "' AND Status = '" + this.getStatus()+"'");
				//If this rail exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("RailID");					//Set this Rail id to the id in the database
					MarkClean();													//Mark this Rail as clean
					MarkOld();														//Mark this Rail as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Rail is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Rail Set RailName = '" + this.getVehicleName() + "' , Carrier = '"+this.getCarrier().getId()+
						 "' , Status = '" + this.getStatus() + "', Deleted = " +this.isDeleted() + " Where RailID = " +this.id);
					MarkClean();													//Mark the Rail as clean
				}//End of isDirty else
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);										//Print out the error
			ex.printStackTrace();
			return false;
		}//End of catch block
		
	}//End of the overridden Update()

	/**
	 * This is the overridden Delete function of the parent class and will mark the Rail as deleted
	 */
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Update Rail Set Deleted = true Where RailID = " + this.id);			//Delete the Rail
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);										//Print out the error
			ex.printStackTrace();
			return false;
		}//End of catch block

	}//End of overridden Delete()

	/**
	 * This function will load a Rail from the database based on the passed in id
	 * @param id This is the id of the Rail to load from the database
	 * @return Returns the Rail object loaded from the database
	 */
	public static Rail Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Rail where RailID = " + id);
			if(temp.size()>0)
			{
				Rail r = BuildFromDataRow(temp.get(0));
				return r;
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
	 * This function will return an ArrayList of Rail objects from the database based on the where clause
	 * @param where This clause determines which Rails to load from the database
	 * @return Returns an ArrayList of Rail objects from the database based on the given where clause
	 */
	public static ArrayList<Rail> LoadAll(String where)
	{
		ArrayList<Rail> returnList = new ArrayList<Rail>();
		try 
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Rail " +  where);
			for(int i = 0; i<temp.size();i++)
			{
				Rail r = BuildFromDataRow(temp.get(i));
				returnList.add(r);
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
	 */
	public static Rail BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		//This code grabs each element that will be found in the database on the Rail table and set the appropriate values for a new Rail
		Rail r = new Rail((Integer)data.get("RailID"));
		r.setVehicleName((String)data.get("RailName"));
		r.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
		r.setStatus((String)data.get("Status"));		
		if(Boolean.getBoolean(data.get("Deleted").toString()))
			r.MarkDeleted();
		else
			r.MarkUndeleted();
		r.MarkClean();	
		r.MarkOld();
		return r;
			
	}//End of BuildFromDataRow(Map<String,Object> data)

}//End of Rail class
