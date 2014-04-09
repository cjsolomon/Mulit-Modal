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
	
	//The default Truck constructor
	public Truck()
	{
		super.setTravelMode(Vehicle.TravelModes.Truck);					//Set the TravelMode to Truck
		MarkNew();														//Mark the Truck as new
	}//End of the default Truck constructor
	
	//This is the argumented Truck constructor
	public Truck(int id)
	{
		super.setTravelMode(Vehicle.TravelModes.Truck);					//Set the TravelMode to Truck
		this.id=id;														//Set the Truck's id
																				
	}//End of the argumented Truck constructor
	
	//This function overrides the parent's Update function and will handle changes made to the Truck object in the database
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
					this.id = (Integer)temp.get(0).get("TruckID");									//Set the Truck id
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
						 "' , Status = '" + this.getStatus() + "' Where TruckID = " +this.id);
					MarkClean();																	//Mark the Truck as clean
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);														//Print the error
			return false;
		}//End of catch block
		
	}//End of overridden Update()

	//This is the overridden Delete function of the parent class and will remove this Truck from the database
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Delete from Truck Where TruckID = " + this.id);							//Delete this Truck
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);														//Print the error
			return false;
		}//End of catch block

	}//End of the overridden Delete()


	public static Truck Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Truck where TruckId = " + id);
			if(temp.size()>0)
			{
				Truck t = BuildFromDataRow(temp.get(0));
				t.getSchedule();
				return t;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}
	public static ArrayList<Truck> LoadAll(String where)
	{
		ArrayList<Truck> returnList = new ArrayList<Truck>();
		try 
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Truck " +where );
			for(int i = 0; i<temp.size();i++)
			{
				Truck t = BuildFromDataRow(temp.get(i));
				t.getSchedule();
				returnList.add(t);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}
	//This function builds objects from returned data from SQL queries against our database
		public static Truck BuildFromDataRow(Map<String,Object> data) throws SQLException
		{
			//This code grabs each element that will be found in the database on the Truck table and set the appropriate values for a new Truck
			Truck t = new Truck((Integer)data.get("TruckID"));
			t.setVehicleName((String)data.get("TruckName"));
			t.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
			t.setStatus((String)data.get("Status"));	
			t.MarkClean();																//Mark the Truck as clean
			return t;
			
		}//End of BuildFromDataRow(Map<String,Object> data)

}
