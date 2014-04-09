

//======================================================================================//
//							Software Engineering MMRP Project							//
//									Dr. Sam Thangiah									//
//					Dan Miller, Zach Petrusch, Chris Solomon, and Jordan Schiller		//
//======================================================================================//
//										Plane Class										//
//======================================================================================//
// Purpose:																				//
//																						//
//======================================================================================//
package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Plane extends Vehicle {
	
	//Default Variables
	private final String DEFAULT_PLANE_NAME = "defaultPlaneName";
	
	/**
	 * This is the plane default constructor
	 */
	public Plane()
	{
		super.setTravelMode(Vehicle.TravelModes.Plane);					//Set the TravelMode to Plane
		this.setCarrier(new Carrier());									//Set the default Plane Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Plane status to RUNNING
		this.name = DEFAULT_PLANE_NAME;									//Set the Plane name					
		this.MarkNew();													//Mark the Plane as new
		this.MarkClean();												//Mark the Plane as clean
	}//End of Plane default constructor
	
	/**
	 * This is the Plane argumented constructor that takes an id value
	 * @param id This is the new id of the Plane object
	 */
	public Plane(int id)
	{
		this.id=id;														//Set the Plane's id
		super.setTravelMode(Vehicle.TravelModes.Plane);					//Set the TravelMode to Plane
		this.setCarrier(new Carrier());									//Set the default Plane Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Plane status to RUNNING
		this.name = DEFAULT_PLANE_NAME;									//Set the Plane name					
		this.MarkNew();													//Mark the Plane as new
		this.MarkClean();												//Mark the Plane as clean
																			
	}//End of the Plane(int id) constructor
	
	/**
	 * This function overrides the parent's Update function and will handle changes made to the Plane object in the database
	 */
	@Override
	public boolean Update() 
	{
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the plane is new insert it into the database by executing the following
				executeCommand("Insert into Plane (PlaneName,Carrier,Status) Values ('"+
						getVehicleName() + "','" + this.getCarrier().getId()+"','"+this.getStatus()+"')");
				//Grab this plane from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select PlaneID from Plane where PlaneName = '" + this.getVehicleName() + "' AND Carrier = '"+this.getCarrier().getId()+
						"' AND Status = '" + this.getStatus()+"'");
				//If this plane exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("PlaneID");								//Set this Plane's id to the database id
					MarkClean();																//Mark the Plane as clean
					MarkOld();																	//Mark the Plane as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Plane is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Plane Set PlaneName = '" + this.getVehicleName() + "' , Carrier = '"+this.getCarrier().getId()+
						 "' , Status = '" + this.getStatus() + "' Where Plane = " +this.id);
					MarkClean();
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);													//Print out the error
			return false;
		}//End of catch block
		
	}//End of overridden Update

	/**
	 * This is the overridden Delete function of the parent class and will remove this Plane from the database
	 */
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Delete from Plane Where PlaneID = " + this.id);					//Delete the plane
			return true;
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);												//Print out the error
			return false;
		}//End of the catch block

	}//End of the overridden Delete function
	
	/**
	 * This function will load a Plane from the database based on the given id
	 * @param id This is id of the Plane to load from the database
	 * @return Returns the Plan object loaded from the database based on the given id
	 */
	public static Plane Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp= executeQuery("Select * from Plane where PlaneID = " + id);
			if(temp.size()>0)
			{
				
				Plane p = BuildFromDataRow(temp.get(0));
				p.getSchedule();
				return p;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}//End of Load(int id)
	
	/**
	 * This function will return an ArrayList of Plane objects from the database based on the given where clause
	 * @param where This is the clause that determines which Planes to laod from the database
	 * @return Returns an ArrayList of Planes from the database based on the given where clause
	 */
	public static ArrayList<Plane> LoadAll(String where)
	{
		ArrayList<Plane> returnList = new ArrayList<Plane>();
		try 
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Plane " +  where);
			for(int i = 0; i<temp.size();i++)
			{
				Plane p = BuildFromDataRow(temp.get(i));
				p.getSchedule();
				returnList.add(p);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}//End of LoadAll(String where)
	
	/**
	 * This function builds objects from returned data from SQL queries against our database
	 * @param data This is the data used to build the Plane object
	 * @return Returns the new Plane object built from the passed in data
	 * @throws SQLException
	 */
	public static Plane BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		//This code grabs each element that will be found in the database on the Plane table and set the appropriate values for a new Plane
		Plane p = new Plane((Integer)data.get("PlaneID"));
		p.setVehicleName((String)data.get("PlaneName"));
		p.setCarrier(Carrier.Load((Integer)data.get("Contractor")));
		p.setStatus((String)data.get("Status"));	
		p.MarkClean();
		return p;
		
	}//End of BuildDataFromRow(Map<String,Object> data)
	
}//End of Plane Class
