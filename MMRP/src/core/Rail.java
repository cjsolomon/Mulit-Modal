

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
	
	//This is the default Rail constructor
	public Rail()
	{
		super.setTravelMode(Vehicle.TravelModes.Rail);				//Set the TravelMode to Rail
		MarkNew();													//Mark this Rail as new
	}//End of default Rail constructor
	
	//This is the argumented Rail constructor that takes an id
	public Rail(int id)
	{
		super.setTravelMode(Vehicle.TravelModes.Rail);				//Set the TravelMode to Rail
		this.id=id;													//Set the id
																	
	}//End of the argumented constructor Rail(int id)
	
	//This function overrides the parent's Update function and will handle changes made to the Rail object in the database
	@Override
	public void Update() 
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
						 "' , Status = '" + this.getStatus() + "' Where RailID = " +this.id);
					MarkClean();													//Mark the Rail as clean
				}//End of isDirty else
			}//End of isOld else
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);										//Print out the error
		}//End of catch block
		
	}//End of the overridden Update()

	//This is the overridden Delete function of the parent class and will remove this Rail from the database
	@Override
	public  void Delete() 
	{
		try
		{
			executeCommand("Delete from Rail Where RailID = " + this.id);			//Delete the Rail
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);										//Print out the error
		}//End of catch block

	}//End of overridden Delete()

	public static Rail Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Rail where RailID = " + id);
			if(temp.size()>0)
			{
				Rail r = BuildFromDataRow(temp.get(0));
				r.getSchedule();
				return r;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}
	public static ArrayList<Rail> LoadAll(String where)
	{
		ArrayList<Rail> returnList = new ArrayList<Rail>();
		try 
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Rail " +  where);
			for(int i = 0; i<temp.size();i++)
			{
				Rail r = BuildFromDataRow(temp.get(i));
				r.getSchedule();
				returnList.add(r);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}
	//This function builds objects from returned data from SQL queries against our database
		public static Rail BuildFromDataRow(Map<String,Object> data) throws SQLException
		{
			//This code grabs each element that will be found in the database on the Rail table and set the appropriate values for a new Rail
			Rail r = new Rail((Integer)data.get("RailID"));
			r.setVehicleName((String)data.get("RailName"));
			r.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
			r.setStatus((String)data.get("Status"));		
			r.MarkClean();															//Mark the Rail as clean
			return r;
			
		}//End of BuildFromDataRow(Map<String,Object> data)

}//End of Rail class
