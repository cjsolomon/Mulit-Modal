/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 */

package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Bike extends Vehicle {
	
	/**
	 * Default Constructor for Bike Class
	 * <p>
	 * Creates a new Vehicle of type Bike that is set to new
	 */
	public Bike()
	{
		super.setTravelMode(Vehicle.TravelModes.Bike);	//Set the TravelMode to Bike
		MarkNew();										//Mark this as a new object
	}//End of Bike()
	
	//This is the arguemented Bike constructor to set the id of the Bike
	/**
	 * Constructor for Bike Class when object is loaded from Database
	 * @param id BikeId in database table
	 */
	public Bike(int id)
	{
		super.setTravelMode(Vehicle.TravelModes.Bike);
		this.id=id;										//Set the bike id
						
	}//End of Bike(int id)
	
	/**
	 * Sets the name of the Bike
	 * @param s Name of the bike
	 */
	public void setBikeName(String s)
	{
		if(name==null || !this.name.equals(s))
		{
			super.setVehicleName(s);					//Set the name of the Bike (as a vehicle)
		}//End of valid name if
	
	}//End of setBikeName(String s)
	
	/**
	 * Returns the name of the Bike
	 * @return String name of the Bike
	 */
	public String getBikeName()
	{
		return super.getVehicleName();					//Return the name of the Bike (from vehicle)
	}//End of getBikeName()
	
	
	/**
	 * Updates the database entry for this object.
	 * 
	 * If the object is new it will be inserted into the database.
	 * If the object is dirty, the database entry will be updated
	 */
	@Override
	public void Update() 
	{
		try
		{
		//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the bike is new insert it into the database by executing the following
				executeCommand("Insert into Bike (BikeName,Carrier,Status) Values ('"+
				this.getBikeName() + "','" + this.getCarrier().getId() + "','"+this.getStatus()+"')");
				
				//Grab this bike from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select BikeID from Bike where BikeName = '" + this.getBikeName() + "' AND Carrier = '"+this.getCarrier().getId() +
				"' AND Status = '" + this.getStatus()+"'");
				//If this bike exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("BikeID");				//Set this Bike's id
					MarkClean();												//Set the bike to clean
					MarkOld();													//Set the bike to old
				}//End of entry found if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Bike is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Bike Set BikeName = '" + this.getBikeName() + "' , Carrier = '"+this.getCarrier().getId() +
					"' , Status = '" + this.getStatus() + "' Where BikeID = " +this.id);
					MarkClean();												//Now mark the bike as clean
				}//End of isDirty if
			}//End of isOld else
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);									//Print out the error
		}//End of catch block
	
	}//End of overridden Update()
	
	/**
	 * Deletes the bike from the database
	 */
	@Override
	public  void Delete() 
	{
		try
		{
			executeCommand("Delete from Bike Where BikeID = " + this.id);		//Delete this Bike from the database
		}//End of try block
		catch(Exception ex)
		{	
			System.out.println("Error " + ex);									//Print out the error
		}//End of catch block
	
	}//End of overridden Delete()
	/**
	 * Static method that loads a bike from the databse
	 * @param id BikeID
	 * @return Instance of Bike where BikedID=id
	 */
	public static Bike Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Bike where BikeID = " + id);
			if(temp.size()>0)
			{
				Bike b = BuildFromDataRow(temp.get(0));
				b.getSchedule();
				return b;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
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
			
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Bike " +  where);
			for(int i = 0; i<temp.size();i++)
			{
				Bike  b = BuildFromDataRow(temp.get(i));
				b.getSchedule();
				returnList.add(b);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}
	/**
	 * Builds a Bike object from the results obtained from a database query
	 * @param data Mapping from database query
	 * @return
	 * @throws SQLException
	 */
	public static Bike BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
	
		//This code grabs each element that will be found in the database on the Bikes table and set the appropriate values for a new Bike
		Bike b = new Bike((Integer)data.get("BikeID"));//rs.getInt("BikeID"));
		b.setBikeName((String)data.get("BikeName"));//rs.getString("BikeName"));
		b.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
		b.setStatus((String)data.get("Status"));//rs.getString("Status"));		
		b.MarkClean();
		return b;
		
	}//End of BuildFromDataRow(Map<String,Object> data)
	
	//This function overrides the toString function and returns the name of the Bike
	@Override
	public String toString()
	{
		return getBikeName();								//Return the name of the Bike
	}//End of the overridden toString()


}
