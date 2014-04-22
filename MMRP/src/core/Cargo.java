/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 * @see java.sql.SQLException
 * @see java.util.ArrayList
 * @see java.util.Map
 * @see GUI.log
 */
package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import GUI.Log;

public class Cargo extends Vehicle {
	
	//Default Variables
	private final String DEFAULT_CARGO_NAME = "defaultCargoName";
	
	/**
	 * This is the default Cargo constructor
	 */
	public Cargo()
	{
		super.setTravelMode(Vehicle.TravelModes.CARGO);					//Set the TravelMode to Cargo
		this.setCarrier(new Carrier());									//Set the default Cargo's Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Cargo's status to RUNNING
		this.name = DEFAULT_CARGO_NAME;									//Set the Cargo's name					
		this.MarkNew();													//Mark the Cargo as new
		this.MarkClean();												//Mark the Cargo as clean
		this.MarkUndeleted();											//Mark the Cargo as not deleted
	}//End of Cargo()
	
	/**
	 * This function will create a new Cargo object with the given id
	 * @param id This is the new ID of the Cargo
	 */
	public Cargo(int id)
	{
		this.id=id;														//Set the id of the Cargo to the given value
		super.setTravelMode(Vehicle.TravelModes.CARGO);					//Set the TravelMode to Cargo
		this.setCarrier(new Carrier());									//Set the default Cargo's Carrier
		this.setStatus(Vehicle.Status.Running);							//Set the Cargo's status to RUNNING
		this.name = DEFAULT_CARGO_NAME;									//Set the Cargo's name					
		this.MarkClean();												//Mark the Cargo as clean	
	}//End of arguemented Cargo constructor
	
	/**
	 * This function overrides the parent's Update function and will handle changes made to the Cargo object in the database
	 */
	@Override
	public boolean Update() 
	{
		try
		{
		if(isNew())
		{
			//If the cargo is new insert it into the database by executing the following
			executeCommand("Insert into CargoShip (ShipName,Carrier,Status) Values ('"+
					getVehicleName() + "','" +this.getCarrier().getId()+"','" +this.getStatus()+"')");
			//Grab this cargo from the database
			ArrayList<Map<String,Object>> temp =executeQuery("Select ShipID from CargoShip where ShipName = '" + this.getVehicleName() + "' AND Carrier = '"+this.getCarrier().getId()+
					 "' AND Status = '" + this.getStatus()+"'");
			//If this cargo exists on the database mark it as old and clean
			if(temp.size()>0)
			{
				this.id = (Integer)temp.get(temp.size()-1).get("ShipID");					//Set this Cargo's id
				
			}
			MarkClean();														//Mark the Cargo as clean
			MarkOld();															//Mark the Cargo as old
		}//End of isNew if
		else
		{
			if(isDirty())
			{
				//If the Cargo is not new, but is dirty then it needs to be updated by the following SQL command
				executeCommand("Update CargoShip Set ShipName = '" + this.getVehicleName() + "' , Carrier = '"+this.getCarrier().getId()+
					"' , Status = '" + this.getStatus() +"', Deleted = " + this.isDeleted() + " Where ShipID = " +this.id);
				MarkClean();													//Mark the cargo as clean
			}//End of isDirty if
		}//End of isOld else
		return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);									//Print out the error
			ex.printStackTrace();
			return false;
		}//End of catch block
		
	}//End of overridden Update()

	/**
	 * This is the overridden Delete function of the parent class and will set the cargo ship to deleted
	 */
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Update CargoShip Set Deleted = true where ShipID = " + id);			//Delete this Cargo from the database
			this.MarkDeleted();
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error "+ ex);									//Print out the error
			ex.printStackTrace();
			return false;
		}//End of catch block

	}//End of overridden Delete()

	/**
	 * This function will load a Cargo object from the database based on the given id
	 * @param id This is the ID of the Cargo object to load from the database
	 * @return Returns the Cargo object from the database
	 */
	public static Cargo Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from CargoShip where ShipID = " + id  + " AND Deleted = false");
			if(temp.size()>0)
			{
				Cargo c = BuildFromDataRow(temp.get(0));
				return c;
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
	 * This function loads an ArrayList of Cargo objects from the database based on the passed in where clause
	 * @param where This is the where clause that specifies which Cargo objects to load from the database
	 * @return Returns an ArrayList of Cargo objects from the database
	 */
	public static ArrayList<Cargo> LoadAll(String where)
	{
		ArrayList<Cargo> returnList = new ArrayList<Cargo>();
		try 
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from CargoShip " +  where  + " AND Deleted = false");
			for(int i = 0 ; i<temp.size();i++)
			{
				Cargo c = BuildFromDataRow(temp.get(i));
				returnList.add(c);
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
	 * @param data This is the data that will be used to build the Cargo object
	 * @return Returns a new cargo object built from the passed in data
	 * @throws SQLException
	 */
	public static Cargo BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		//This code grabs each element that will be found in the database on the Cargo table and set the appropriate values for a new Cargo
		Cargo c = new Cargo((Integer)data.get("ShipID"));
		c.setVehicleName((String)data.get("ShipName"));
		c.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
		c.setStatus((String)data.get("Status"));
		if(Boolean.getBoolean(data.get("Deleted").toString()))
			c.MarkDeleted();
		else
			c.MarkUndeleted();
		c.MarkClean();
		c.MarkOld();
		return c;
		
	}//End of the BuildFromDataRow(Map<String, Object> data)
	
}//End of Cargo class
