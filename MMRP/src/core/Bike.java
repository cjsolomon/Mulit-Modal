/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 * @see java.sql.SQLException
 * @see java.util.ArrayList
 * @see java.util.Map
 */

package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Bike extends Vehicle {
	
	//Default Variables
	private final String DEFAULT_BIKE_NAME = "defaultBikeName";

	/**
	 * Default Constructor for Bike Class
	 * <p>
	 * Creates a new Vehicle of type Bike that is set to new
	 */
	public Bike()
	{
		super.setTravelMode(Vehicle.TravelModes.BIKE);					//Set the TravelMode to Bike
		this.setCarrier(new Carrier());									//Set the default Bike's Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Bike's status to RUNNING
		this.name = DEFAULT_BIKE_NAME;									//Set the Bike's name					
		this.MarkNew();													//Mark the Bike as new
		this.MarkClean();												//Mark the Bike as clean
		this.MarkUndeleted();											//Mark the bike as not deleted
	}//End of Bike()

	//This is the arguemented Bike constructor to set the id of the Bike
	/**
	 * Constructor for Bike Class when object is loaded from Database
	 * @param id BikeId in database table
	 */
	public Bike(int id)
	{
		this.id=id;														//Set the bike id
		super.setTravelMode(Vehicle.TravelModes.BIKE);					//Set the TravelMode to Bike
		this.setCarrier(new Carrier());									//Set the default Bike's Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Bike's status to RUNNING
		this.name = DEFAULT_BIKE_NAME;									//Set the Bike's name					
		this.MarkClean();												//Mark the Bike as clean

	}//End of Bike(int id)

	/**
	 * Updates the database entry for this object.
	 * 
	 * If the object is new it will be inserted into the database.
	 * If the object is dirty, the database entry will be updated
	 */
	@Override
	public boolean Update() 
	{
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the bike is new insert it into the database by executing the following
				executeCommand("Insert into Bike (BikeName,Carrier,Status) Values ('"+
						this.getVehicleName() + "','" + this.getCarrier().getId() + "','"+this.getStatus()+"')");

				//Grab this bike from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select BikeID from Bike where BikeName = '" + this.getVehicleName() + "' AND Carrier = '"+this.getCarrier().getId() +
						"' AND Status = '" + this.getStatus()+"'");
				//If this bike exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(temp.size()-1).get("BikeID");				//Set this Bike's id
					MarkClean();												//Set the bike to clean
					MarkOld();													//Set the bike to old
				}//End of entry found if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Bike is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Bike Set BikeName = '" + this.getVehicleName() + "' , Carrier = '"+this.getCarrier().getId() +
							"' , Status = '" + this.getStatus() + "', Deleted = " + this.isDeleted() + " Where BikeID = " +this.id);
					MarkClean();												//Now mark the bike as clean
				}//End of isDirty if
			}//End of isOld else
			return true;	//Indicates that the update was successful
		}//End of try block
		
		catch(Exception ex)
		{
			System.out.println("Error " + ex);									//Print out the error
			ex.printStackTrace();
			return false;	//Indicates the update was not successful
		}//End of catch block

	}//End of overridden Update()

	/**
	 * Sets the Bike as deleted in the database
	 */
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Update Bike Set Deleted = true Where BikeID = " + this.id);		//Delete this Bike from the database
			//Now we need mark all the segments that use this bike as deleted
			executeQuery("Update Segment set Deleted = true Where VehicleID = " + this.id + " AND ModeType = '" +this.mode.toString() + "'");
	
			this.MarkDeleted();
			return true;
		}//End of try block
		catch(Exception ex)
		{	
			System.out.println("Error " + ex);									//Print out the error
			ex.printStackTrace();
			return false;
		}//End of catch block

	}//End of overridden Delete()
	
	/**
	 * Static method that loads a bike from the database
	 * @param id BikeID
	 * @return Instance of Bike where BikedID=id
	 */
	public static Bike Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Bike where BikeID = " + id + " AND Deleted = false");
			if(temp.size()>0)
			{
				Bike b = BuildFromDataRow(temp.get(0));
				return b;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * Loads all the bikes from a database meeting conditions
	 * @param where Conditions to be meet for Bike
	 * @return ArrayList<Bike>
	 */
	public static ArrayList<Bike> LoadAll(String where)
	{
		ArrayList<Bike> returnList = new ArrayList<Bike>();
		try 
		{
			ArrayList<Map<String,Object>> temp;
			if(where.isEmpty())
				temp =executeQuery("Select * from Bike where Deleted = false ");
			else
				temp =executeQuery("Select * from Bike " +  where  + " AND Deleted = false");
			for(int i = 0; i<temp.size();i++)
			{
				Bike  b = BuildFromDataRow(temp.get(i));
				returnList.add(b);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
		return returnList;
	}
	/**
	 * Builds a Bike object from the results obtained from a database query
	 * @param data Mapping from database query
	 * @return Returns a new Bike object that was created from the passed in data
	 * @throws SQLException
	 */
	public static Bike BuildFromDataRow(Map<String,Object> data) throws SQLException
	{

		//This code grabs each element that will be found in the database on the Bikes table and set the appropriate values for a new Bike
		Bike b = new Bike((Integer)data.get("BikeID"));
		b.setVehicleName((String)data.get("BikeName"));
		b.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
		b.setStatus((String)data.get("Status"));
		if(Boolean.getBoolean(data.get("Deleted").toString()))
			b.MarkDeleted();
		else
			b.MarkUndeleted();
		b.MarkClean();
		b.MarkOld();
		return b;

	}//End of BuildFromDataRow(Map<String,Object> data)


}//End of Bike Class
