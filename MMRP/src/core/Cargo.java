
//======================================================================================//
//							Software Engineering MMRP Project							//
//									Dr. Sam Thangiah									//
//					Dan Miller, Zach Petrusch, Chris Solomon, and Jordan Schiller		//
//======================================================================================//
//										Cargo Class										//
//======================================================================================//
// Purpose:																				//
//																						//
//======================================================================================//
package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Cargo extends Vehicle {
	
	//This is the default Cargo constructor
	public Cargo()
	{
		super.setTravelMode(Vehicle.TravelModes.Cargo);					//Set the TravelMode to Cargo
		MarkNew();														//Mark this cargo as new
	}//End of Cargo()
	
	//This function will create a new Cargo object with the given id
	public Cargo(int id)
	{
		super.setTravelMode(Vehicle.TravelModes.Cargo);					//Set the TravelMode to Cargo
		this.id=id;														//Set the id of the Cargo to the given value
																		
	}//End of arguemented Cargo constructor
	
	//This function overrides the parent's Update function and will handle changes made to the Cargo object in the database
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
				this.id = (Integer)temp.get(0).get("ShipID");					//Set this Cargo's id
				
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
					"' , Status = '" + this.getStatus() +"' Where ShipID = " +this.id);
				MarkClean();													//Mark the cargo as clean
			}//End of isDirty if
		}//End of isOld else
		return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);									//Print out the error
			return false;
		}//End of catch block
		
	}//End of overridden Update()

	//This is the overridden Delete function of the parent class and will remove this Cargo from the database
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Delete From CargoShip where ShipID = " + id);			//Delete this Cargo from the database
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error "+ ex);									//Print out the error
			return false;
		}//End of catch block

	}//End of overridden Delete()

	public static Cargo Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from CargoShip where ShipID = " + id);
			if(temp.size()>0)
			{
				Cargo c = BuildFromDataRow(temp.get(0));
				c.getSchedule();
				return c;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}
	public static ArrayList<Cargo> LoadAll(String where)
	{
		ArrayList<Cargo> returnList = new ArrayList<Cargo>();
		try 
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from CargoShip " +  where);
			for(int i = 0 ; i<temp.size();i++)
			{
				Cargo c = BuildFromDataRow(temp.get(i));
				c.getSchedule();
				returnList.add(c);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}
	//This function builds objects from returned data from SQL queries against our database
	public static Cargo BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		//This code grabs each element that will be found in the database on the Cargo table and set the appropriate values for a new Cargo
		Cargo c = new Cargo((Integer)data.get("ShipID"));
		c.setVehicleName((String)data.get("ShipName"));
		c.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
		c.setStatus((String)data.get("Status"));
		c.MarkClean();
		return c;
		
	}//End of the BuildFromDataRow(Map<String, Object> data)
	


}
