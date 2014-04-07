

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
	
	//This is the plane default contructor
	public Plane()
	{
		super.setTravelMode(Vehicle.TravelModes.Plane);						//Set the TravelMode to Plane
		MarkNew();															//Mark the Plane as new
	}//End of Plane default constructor
	
	//This is the Plane arugmented constructor that takes an id value
	public Plane(int id)
	{
		super.setTravelMode(Vehicle.TravelModes.Plane);						//Set the TravelMode to Plane
		this.id=id;															//Set the Plane's id
																			
	}//End of the Plane(int id) constructor
	
	//This function will set the plane's name
	public void setPlaneName(String s)
	{
		if(name==null || !this.name.equals(s))
		{
			super.setVehicleName(s);										//Set the plane's name (as a Vehicle)
		}//End of valid name if
		
	}//End of setPlaneName(String s)
	
	//This function returns the name of the Plane
	public String getPlaneName()
	{
		return super.getVehicleName();										//Return the name of the Plane (from Vehicle)
	}//End of getPlaneName()
	
	
	//This function overrides the parent's Update function and will handle changes made to the Plane object in the database
	@Override
	public void Update() 
	{
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the plane is new insert it into the database by executing the following
				executeCommand("Insert into Plane (PlaneName,Carrier,Status) Values ('"+
						getPlaneName() + "','" + this.getCarrier().getId()+"','"+this.getStatus()+"')");
				//Grab this plane from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select PlaneID from Plane where PlaneName = '" + this.getPlaneName() + "' AND Carrier = '"+this.getCarrier().getId()+
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
					executeCommand("Update Plane Set PlaneName = '" + this.getPlaneName() + "' , Carrier = '"+this.getCarrier().getId()+
						 "' , Status = '" + this.getStatus() + "' Where Plane = " +this.id);
					MarkClean();
				}//End of isDirty if
			}//End of isOld else
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);													//Print out the error
		}//End of catch block
		
	}//End of overridden Update

	//This is the overridden Delete function of the parent class and will remove this Plane from the database
	@Override
	public  void Delete() 
	{
		try
		{
			executeCommand("Delete from Plane Where PlaneID = " + this.id);					//Delete the plane
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);												//Print out the error
		}//End of the catch block

	}//End of the overridden Delete function
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
	}
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
	}
	//This function builds objects from returned data from SQL queries against our database
	public static Plane BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		//This code grabs each element that will be found in the database on the Plane table and set the appropriate values for a new Plane
		Plane p = new Plane((Integer)data.get("PlaneID"));
		p.setPlaneName((String)data.get("PlaneName"));
		p.setCarrier(Carrier.Load((Integer)data.get("Contractor")));
		p.setStatus((String)data.get("Status"));	
		p.MarkClean();
		return p;
		
	}//End of BuildDataFromRow(Map<String,Object> data)
	
	//This function overrides the toString function and returns the name of the Plane
	@Override
	public String toString()
	{
		return getPlaneName();													//Return the plane name
	}//End of overridden toString()

}
