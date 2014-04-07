package core;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class TravelType extends BaseClass {

	private int vehicleTypeID;
	private String vehicleTypeName;
	private String vehicleMode;
	private String trailer1;
	private String trailer2;
	private double minCap;
	private double maxCap;
	private double actCap;
	private double maxWeight;
	private String serviceType;
	private boolean radiation;
	private boolean refridgeration;
	private boolean hazmat;
	private boolean explosives;
	private boolean tracking;
	
	
	//The default TravelType constructor
	public TravelType()
	{
		MarkNew();																//Mark the Truck as new
	}//End of the default TravelType constructor
	
	//This is the argumented TravelType constructor
	public TravelType(int id)
	{
		this.vehicleTypeID=id;													//Set the TravelType id

	}//End of the argumented TravelType constructor
	
	//This function will return the vehicle type's id
	public int getVehicleTypeID()
	{
		return vehicleTypeID;													//Returns the vehicle type id
	}//End of getVehicleTypeID()
	
	//This function sets the travel type's name
	public void setTravelTypeName(String s)
	{
		vehicleTypeName = s;													//Set the TravelType's name 
	}//End of setTravelTypeName(String s)
	
	//This function returns the TravelType name
	public String getTravelTypeName()
	{
		return vehicleTypeName;													//Returns the TravelType's name 
	}//End of getTravelTypeName()
	
	//This function sets the travel type's mode
	public void setTravelTypeMode(String s)
	{
		vehicleMode = s;													//Set the TravelType's mode
	}//End of setTravelTypeMode(String s)
		
	//This function returns the TravelType mode
	public String getTravelTypeMode()
	{
		return vehicleMode;													//Returns the TravelType's mode
	}//End of getTravelTypeMode()
	
	//This function sets the travel type's mode
	public void setTrailer1(String s)
	{
		trailer1 = s;														//Set the TravelType's trailer1
	}//End of setTrailer1(String s)
			
	//This function returns the TravelType mode
	public String getTrailer1()
	{
		return trailer1;													//Returns the TravelType's trailer1
	}//End of getTrailer1()
	
	//This function sets the travel type's mode
	public void setTrailer2(String s)
	{
		trailer2 = s;														//Set the TravelType's trailer2
	}//End of setTrailer2(String s)
				
	//This function returns the TravelType mode
	public String getTrailer2()
	{
		return trailer2;													//Returns the TravelType's trailer2
	}//End of getTrailer2()
	
	//This function sets the travel type's minimum capacity
	public void setMinCap(double minCapacity)
	{
		if(minCapacity < 0){
			System.out.println("Minumum Capacity was set below zero so the minimum capacity has defaulted to 0");
			minCapacity = 0;
		}
		minCap = minCapacity;												//Set the TravelType's minimum capacity
	}//End of setMinCap(double minCapacity)
	
	//This function returns the TravelType mode
	public double getMinCap()
	{
		return minCap;														//Returns the TravelType's minimum capacity
	}//End of getMinCap()
	
	//This function sets the travel type's maximum capacity
	public void setMaxCap(double maxCapacity)
	{
		if(maxCapacity < 0){
			System.out.println("Maximum Capacity was set below zero so the maximum capacity has defaulted to 0");
			maxCapacity = 0;
		}
		maxCap = maxCapacity;												//Set the TravelType's maximum capacity
	}//End of setMaxCap(double maxCapacity)
		
	//This function returns the TravelType maximum capacity
	public double getMaxCap()
	{
		return maxCap;														//Returns the TravelType's maximum capacity
	}//End of getMaxCap()
	
	//This function sets the travel type's actual capacity
	public void setActCap(double actCapacity)
	{
		if(actCapacity < 0){
			System.out.println("Actual Capacity was set below 0% so the actual capacity has defaulted to 0%");
			actCapacity = 0;
		}
		else if(actCapacity > 100){
			System.out.println("Actual Capacity was set above 100% so the actual capacity has defaulted to 100%");
			actCapacity = 100;
		}
		actCap = actCapacity;												//Set the TravelType's actual capacity
	}//End of setActCap(double actCapacity)
			
	//This function returns the TravelType actual capacity
	public double getActCap()
	{
		return actCap;														//Returns the TravelType's actual capacity
	}//End of getActCap()
	
	//This function sets the travel type's maximum weight
	public void setMaxWeight(double weight)
	{
		if(maxWeight < 0){
			System.out.println("Maximum weight was set below 0 so the maximum weight has defaulted to 0");
			maxWeight = 0;
		}
		maxWeight = weight;												//Set the TravelType's maximum weight
	}//End of setMaxWeight(double weight)
				
	//This function returns the TravelType maximum weight
	public double getMaxWeight()
	{
		return maxWeight;												//Returns the TravelType's maximum weight
	}//End of getMaxWeight()
	
	//This function sets the TravelType's Service Type
	public void setServiceType(String service)
	{
		serviceType = service;											//Set the TravelType's service type
	}//End of setServiceType(String service)
	
	//This function will return the TravelType's Service Type
	public String getServiceType()
	{
		return serviceType;												//Returns the TravelType's Service Type
	}//End of getServiceType()
	
	//This function will set the radiation field to true
	public void setRadTrue()
	{
		radiation = true;												//Sets the radiation field to true
	}//End of setRadTrue()
	
	//This function will set the radiation field to false
	public void setRadFalse()
	{
		radiation = false;												//Sets the radiation field to false
	}//End of setRadFalse()
	
	//This function will return the state of the radiation field
	public boolean getRadiation()
	{
		return radiation;												//Returns the radiation field
	}//End of getRadiation()
	
	//This function will set the refridgeration field to true
	public void setRefTrue()
	{
		this.refridgeration = true;										//Sets the refridgeration field to true
	}//End of setRefTrue()
		
	//This function will set the refridgeration field to false
	public void setRefFalse()
	{
		refridgeration = false;											//Sets the refridgeration field to false
	}//End of setRadFalse()
		
	//This function will return the state of the radiation field
	public boolean getRefridgeration()
	{
		return refridgeration;											//Returns the refridgeration field
	}//End of getRefridgeration()
	
	//This function will set the hazmat field to true
	public void setHazTrue()
	{
		this.hazmat = true;												//Sets the hazmat field to true
	}//End of setHazTrue()
			
	//This function will set the hazmat field to false
	public void setHazFalse()
	{
		hazmat = false;													//Sets the hazmat field to false
	}//End of setHazFalse()
			
	//This function will return the state of the hazmat field
	public boolean getHazmat()
	{
		return hazmat;													//Returns the hazmat field
	}//End of getHazmat()
	
	//This function will set the explosives field to true
	public void setExpTrue()
	{
		this.explosives= true;											//Sets the explosives field to true
	}//End of setExpTrue()
				
	//This function will set the explosives field to false
	public void setExpFalse()
	{
		explosives = false;												//Sets the explosives field to false
	}//End of setExpFalse()
				
	//This function will return the state of the explosives field
	public boolean getExplosives()
	{
		return explosives;												//Returns the explosives field
	}//End of getExplosives()
	
	//This function will set the tracking field to true
	public void setTrackingTrue()
	{
		this.tracking= true;											//Sets the tracking field to true
	}//End of setTrackingTrue()
					
	//This function will set the tracking field to false
	public void setTrackingFalse()
	{
		tracking = false;												//Sets the tracking field to false
	}//End of setTrackingFalse()
					
	//This function will return the state of the tracking field
	public boolean getTracking()
	{
		return tracking;												//Returns the tracking field
	}//End of getTracking()
	
	//This function overrides the parent's Update function and will handle changes made to the Truck object in the database
	@Override
	public void Update() 
	{
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the Truck is new insert it into the database by executing the following
				executeCommand("Insert into TravelTypes (VehicleTypeName,VehicleMode,Trailer1,Trailer2,MinimumCapacity,MaximumCapacity,ActualCapacity,MaxWeight,ServiceType,Radiation, Refridgeration, HazardousMaterial, ExplosiveMaterial, Tracking) Values ('"+
						this.getTravelTypeName() + "','" + this.getTravelTypeMode() + "','"+ this.getTrailer1()+"','"+this.getTrailer2() + "','" + this.getMinCap() + "','" + this.getMaxCap()+ "','"+
						this.getActCap()+"','"+this.getMaxWeight()+"','"+this.getServiceType()+"','"+this.getRadiation()+"','"+this.getRefridgeration()+"','"+this.getHazmat()+"','"+this.getExplosives()+"','"+this.getTracking()+"')");
				//Grab this Truck from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select VehicleTypeID from TravelTypes where VehicleTypeName = '" + this.getTravelTypeName() + "' AND VehicleMode = '"+this.getTravelTypeMode()+
						"' AND Trailer1 = '" + this.getTrailer1() + "' AND Trailer2 = '" + this.getTrailer2() + "' AND MinimumCapacity = '" + this.getMinCap() + 
						"' AND MaximumCapacity = '" + this.getMaxCap() + "' AND ActualCapacity = '" +this.getActCap() + "' AND MaxWeight = '" + this.getMaxWeight()+ "' AND ServiceType = '" + this.getServiceType()+
						 "' AND Radiation = '" + this.getRadiation()+ "' AND Refridgeration = '" + this.getRefridgeration()+ "' AND HazardousMaterial = '" + this.getHazmat()+ "' AND ExplosiveMaterial = '" + this.getExplosives()+
						 "' AND Tracking = '" + this.getTracking()+"'");
				//If this TravelType exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.vehicleTypeID = (Integer)temp.get(0).get("VehicleTypeID");									//Set the Truck id
					MarkClean();																	//Mark the Truck as clean
					MarkOld();																		//Mark the Truck as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the TravelType is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update TravelTypes Set VehicleTypeName = '" + this.getTravelTypeName() + "' , VehicleMode = '"+this.getTravelTypeMode()+
						"' , Trailer1 = '" + this.getTrailer1() + "' , Trailer2 = '" + this.getTrailer2() + "' , MinimumCapacity = '" + this.getMinCap() + 
						"' , MaximumCapacity = '" + this.getMaxCap() + "' , ActualCapacity = '" +this.getActCap() + "' , MaxWeight = '" + this.getMaxWeight() +
						"' , ServiceType = '" + this.getServiceType() + "' , Radiation = '" + this.getRadiation() + "' , Refridgeration = '" + this.getRefridgeration() +
						"' , HazardousMaterial = '" + this.getHazmat() + "' , ExplosiveMaterial = '" + this.getExplosives() + "' , Tracking = '" + this.getTracking() +
						"' Where VehicleTypeID = " +this.vehicleTypeID);
					MarkClean();																	//Mark the TravelType as clean
				}//End of isDirty if
			}//End of isOld else
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);														//Print the error
		}//End of catch block
		
	}//End of overridden Update()

	//This is the overridden Delete function of the parent class and will remove this Truck from the database
	@Override
	public  void Delete() 
	{
		try
		{
			executeCommand("Delete from TravelTypes Where VehicleTypeID = " + this.vehicleTypeID);							//Delete this Truck
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);														//Print the error
		}//End of catch block

	}//End of the overridden Delete()


	public static TravelType Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from TravelTypes where VehicleTypeID = " + id);
			if(temp.size()>0)
			{
				TravelType t = BuildFromDataRow(temp.get(0));
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
	public static ArrayList<TravelType> LoadAll(String where)
	{
		ArrayList<TravelType> returnList = new ArrayList<TravelType>();
		try 
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from TravelType " +where );
			for(int i = 0; i<temp.size();i++)
			{
				TravelType t = BuildFromDataRow(temp.get(i));
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
		public static TravelType BuildFromDataRow(Map<String,Object> data) throws SQLException
		{
			//This code grabs each element that will be found in the database on the TravelType table and set the appropriate values for a new TravelType
			TravelType t = new TravelType((Integer)data.get("VehicleTypeID"));
			t.setActCap(((Integer)data.get("ActualCapacity")));//data;//rs.getString("TruckName"));
			t.setMaxCap((Double.parseDouble(data.get("MaximumCapacity").toString())));//rs.getInt("Capacity"));
			t.setMinCap((Double.parseDouble(data.get("MinimumCapacity").toString())));//rs.getString("Contractor"));
			t.setMaxWeight((Double.parseDouble(data.get("MaxWeight").toString())));//,rs.getString("LocationName"));
			t.setServiceType((String)data.get("ServiceType"));//rs.getString("TruckType"));
			t.setTrailer1((String)data.get("Trailer1"));//rs.getString("Status"));		
			t.setTrailer2((String)data.get("Trailer2"));
			t.setTravelTypeMode((String)data.get("VehicleMode"));
			t.setTravelTypeName((String)data.get("VehicleTypeName"));
			if(Boolean.getBoolean((String)data.get("Radiation"))){
				t.setRadTrue();	
			}
			else
				t.setRadFalse();
			if(Boolean.getBoolean((String)data.get("HazardousMaterial"))){
				t.setHazTrue();	
			}
			else
				t.setHazFalse();
			if(Boolean.getBoolean((String)data.get("ExplosiveMaterial"))){
				t.setExpTrue();	
			}
			else
				t.setExpFalse();
			if(Boolean.getBoolean((String)data.get("Refridgeration"))){
				t.setRefTrue();	
			}
			else
				t.setRefFalse();
			if(Boolean.getBoolean((String)data.get("Tracking"))){
				t.setTrackingTrue();	
			}
			else
				t.setTrackingFalse();
			t.MarkClean();																//Mark the Truck as clean
			return t;
			
		}//End of BuildFromDataRow(Map<String,Object> data)
		
		//This function overrides the toString function and returns the name of the Truck
		@Override
		public String toString()
		{
			return this.getTravelTypeName();														//Return the name of the Truck
		}//End of overridden toString()

}//End of TravelType Class