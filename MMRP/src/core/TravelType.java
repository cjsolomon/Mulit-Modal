package core;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import GUI.Log;

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
	private boolean refrigeration;
	private boolean hazmat;
	private boolean explosives;
	private boolean tracking;
	
	
	//Default Variables
	private static final int DEFAULT_VEHICLE_TYPE_ID = 0;
	private static final String DEFAULT_VEHICLE_TYPE_NAME = "defaultVehicleTypeName";
	private static final String DEFAULT_VEHICLE_MODE = "defaultVehicleMode";
	private static final String DEFAULT_TRAILER1 = "defaultTrailer1";
	private static final String DEFAULT_TRAILER2 = "defaultTrailer2";
	private static final double DEFAULT_MINIMUM_CAPACITY = 0;
	private static final double DEFAULT_MAXIMUM_CAPACITY = 100;
	private static final double DEFAULT_MAXIMUM_WEIGHT = 500;
	private static final String DEFAULT_SERVICE_TYPE = "defaultServiceType";
	
	/**
	 * The default TravelType constructor
	 */
	public TravelType()
	{
		this.actCap = DEFAULT_MINIMUM_CAPACITY;
		this.explosives = false;
		this.hazmat = false;
		this.maxCap = DEFAULT_MAXIMUM_CAPACITY;
		this.maxWeight = DEFAULT_MAXIMUM_WEIGHT;
		this.minCap = DEFAULT_MINIMUM_CAPACITY;
		this.radiation = false;
		this.refrigeration = false;
		this.serviceType = DEFAULT_SERVICE_TYPE;
		this.tracking = false;
		this.trailer1 = DEFAULT_TRAILER1;
		this.trailer2 = DEFAULT_TRAILER2;
		this.vehicleMode = DEFAULT_VEHICLE_MODE;
		this.vehicleTypeName = DEFAULT_VEHICLE_TYPE_NAME;
		this.vehicleTypeID = DEFAULT_VEHICLE_TYPE_ID;
		MarkClean();
		MarkNew();																//Mark the Truck as new
		MarkUndeleted();
	}//End of the default TravelType constructor
	
	/**
	 * This is the argumented TravelType constructor
	 * @param id This is the id of the new TravelType
	 */
	public TravelType(int id)
	{
		this.vehicleTypeID=id;													//Set the TravelType id
		this.actCap = DEFAULT_MINIMUM_CAPACITY;
		this.explosives = false;
		this.hazmat = false;
		this.maxCap = DEFAULT_MAXIMUM_CAPACITY;
		this.maxWeight = DEFAULT_MAXIMUM_WEIGHT;
		this.minCap = DEFAULT_MINIMUM_CAPACITY;
		this.radiation = false;
		this.refrigeration = false;
		this.serviceType = DEFAULT_SERVICE_TYPE;
		this.tracking = false;
		this.trailer1 = DEFAULT_TRAILER1;
		this.trailer2 = DEFAULT_TRAILER2;
		this.vehicleMode = DEFAULT_VEHICLE_MODE;
		this.vehicleTypeName = DEFAULT_VEHICLE_TYPE_NAME;
		this.vehicleTypeID = DEFAULT_VEHICLE_TYPE_ID;
		MarkClean();									
	}//End of the argumented TravelType constructor
	
	/**
	 * This function will return the vehicle type's id
	 * @return Returns the vehicle's type id
	 */
	public int getVehicleTypeID()
	{
		return vehicleTypeID;													//Returns the vehicle type id
	}//End of getVehicleTypeID()
	
	/**
	 * This function sets the travel type's name
	 * @param newName This is the new Travel Type's name
	 */
	public void setTravelTypeName(String newName)
	{
		vehicleTypeName = newName;													//Set the TravelType's name 
	}//End of setTravelTypeName(String newName)
	
	/**
	 * This function returns the TravelType name
	 * @return Returns the TravelType's name
	 */
	public String getTravelTypeName()
	{
		return vehicleTypeName;													//Returns the TravelType's name 
	}//End of getTravelTypeName()
	
	/**
	 * This function sets the travel type's mode
	 * @param newMode This sets the vehicle's travel mode
	 */
	public void setTravelTypeMode(String newMode)
	{
		vehicleMode = newMode;													//Set the TravelType's mode
	}//End of setTravelTypeMode(String newMode)
		
	/**
	 * This function returns the TravelType mode
	 * @return Returns the TravelTypes mode
	 */
	public String getTravelTypeMode()
	{
		return vehicleMode;													//Returns the TravelType's mode
	}//End of getTravelTypeMode()
	
	/**
	 * This function sets the trailer1
	 * @param newTrailer1 This is the new trailer1
	 */
	public void setTrailer1(String newTrailer1)
	{
		trailer1 = newTrailer1;														//Set the TravelType's trailer1
	}//End of setTrailer1(String newTrailer1)
			
	/**
	 * This function returns the TravelType trailer1
	 * @return Returns the trailer1 for this TravelType
	 */
	public String getTrailer1()
	{
		return trailer1;													//Returns the TravelType's trailer1
	}//End of getTrailer1()
	
	/**
	 * This function sets the trailer1
	 * @param newTrailer2 This is the new trailer2
	 */
	public void setTrailer2(String newTrailer2)
	{
		trailer2 = newTrailer2;														//Set the TravelType's trailer2
	}//End of setTrailer2(String newTrailer2)
				
	/**
	 * This function returns the TravelType trailer2
	 * @return Returns the trailer2 for this TravelType
	 */
	public String getTrailer2()
	{
		return trailer2;													//Returns the TravelType's trailer2
	}//End of getTrailer2()
	
	/**
	 * This function sets the travel type's minimum capacity
	 * @param minCapacity This is the new minumum capacity for the TravelType
	 */
	public void setMinCap(double minCapacity)
	{
		//NEED ERROR CHECKING
		if(minCapacity < 0){
			System.out.println("Minumum Capacity was set below zero so the minimum capacity has defaulted to 0");
			minCapacity = 0;
		}
		minCap = minCapacity;												//Set the TravelType's minimum capacity
	}//End of setMinCap(double minCapacity)
	
	/**
	 * This function returns the TravelType's minimum capacity
	 * @return Returns the minimum capacity
	 */
	public double getMinCap()
	{
		return minCap;														//Returns the TravelType's minimum capacity
	}//End of getMinCap()
	
	/**
	 * This function sets the travel type's maximum capacity
	 * @param maxCapacity This is the maximum capacity of the TravelType
	 */
	public void setMaxCap(double maxCapacity)
	{
		if(FormatChecker.inRange(maxCapacity, DEFAULT_MINIMUM_CAPACITY, DEFAULT_MAXIMUM_CAPACITY))
		{
			maxCap = maxCapacity;
		}
		else
		{
			Log.writeLogWarning("Invalid entry for max capacity in Travel Type. Setting max capacity to "+ DEFAULT_MAXIMUM_CAPACITY);
			maxCap = DEFAULT_MAXIMUM_CAPACITY;
		}
	}//End of setMaxCap(double maxCapacity)
		
	/**
	 * This function returns the TravelType maximum capacity
	 * @return Returns the maximum capacity for the TravelType
	 */
	public double getMaxCap()
	{
		return maxCap;														//Returns the TravelType's maximum capacity
	}//End of getMaxCap()
	
	/**
	 * This function sets the TravelType's actual capacity
	 * @param actCapacity This is the actual capacity for the TravelType
	 */
	public void setActCap(double actCapacity)
	{
		if(FormatChecker.inRange(actCapacity, DEFAULT_MINIMUM_CAPACITY, DEFAULT_MAXIMUM_CAPACITY))
		{
			actCap = actCapacity;
		}
		else
		{
			Log.writeLogWarning("Invalid entry for actual capacity in Travel Type. Actual capacity set to " + DEFAULT_MAXIMUM_CAPACITY);
			actCap = DEFAULT_MAXIMUM_CAPACITY;
		}
	}//End of setActCap(double actCapacity)
			
	/**
	 * This function returns the TravelType actual capacity
	 * @return Returns the actual capacity of the TravelType
	 */
	public double getActCap()
	{
		return actCap;														//Returns the TravelType's actual capacity
	}//End of getActCap()
	
	/**
	 * This function sets the travel type's maximum weight
	 * @param weight This is the new maximum weight of the TravelType
	 */
	public void setMaxWeight(double weight)
	{
		if(FormatChecker.inRange(weight, 0, DEFAULT_MAXIMUM_WEIGHT))
		{
			maxWeight = weight;												//Set the TravelType's maximum weight
		}
		else 
		{
			Log.writeLogWarning("Invalid entry for weight in Truck Type. Setting weight to " + DEFAULT_MAXIMUM_WEIGHT);
			maxWeight = DEFAULT_MAXIMUM_WEIGHT;
		}
	}//End of setMaxWeight(double weight)
				
	/**
	 * This function returns the TravelType maximum weight
	 * @return Returns the maximum weight for the TravelType
	 */
	public double getMaxWeight()
	{
		return maxWeight;												//Returns the TravelType's maximum weight
	}//End of getMaxWeight()
	
	/**
	 * This function sets the TravelType's Service Type
	 * @param service This is the new service type for the TravelType
	 */
	public void setServiceType(String service)
	{
		serviceType = service;											//Set the TravelType's service type
	}//End of setServiceType(String service)
	
	/**
	 * This function will return the TravelType's Service Type
	 * @return Returns the service type for the TravelType
	 */
	public String getServiceType()
	{
		return serviceType;												//Returns the TravelType's Service Type
	}//End of getServiceType()
	
	/**
	 * This function will set the radiation field to true
	 */
	public void setRadTrue()
	{
		radiation = true;												//Sets the radiation field to true
	}//End of setRadTrue()
	
	/**
	 * This function will set the radiation field to false
	 */
	public void setRadFalse()
	{
		radiation = false;												//Sets the radiation field to false
	}//End of setRadFalse()
	
	/**
	 * This function will return the state of the radiation field
	 * @return
	 */
	public boolean getRadiation()
	{
		return radiation;												//Returns the radiation field
	}//End of getRadiation()
	
	/**
	 * This function will set the refrigeration field to true
	 */
	public void setRefTrue()
	{
		this.refrigeration = true;										//Sets the refridgeration field to true
	}//End of setRefTrue()
		
	/**
	 * This function will set the refrigeration field to false
	 */
	public void setRefFalse()
	{
		refrigeration = false;											//Sets the refridgeration field to false
	}//End of setRadFalse()
		
	/**
	 * This function will return the state of the radiation field
	 * @return Returns a boolean indicating if the TravelType can handle refrigeration
	 */
	public boolean getRefridgeration()
	{
		return refrigeration;											//Returns the refridgeration field
	}//End of getRefridgeration()
	
	/**
	 * This function will set the hazmat field to true
	 */
	public void setHazTrue()
	{
		this.hazmat = true;												//Sets the hazmat field to true
	}//End of setHazTrue()
			
	/**
	 * This function will set the hazmat field to false
	 */
	public void setHazFalse()
	{
		hazmat = false;													//Sets the hazmat field to false
	}//End of setHazFalse()
			
	/**
	 * This function will return the state of the hazmat field
	 * @return Returns a boolean indicating if the TravelType can handle hazardous material
	 */
	public boolean getHazmat()
	{
		return hazmat;													//Returns the hazmat field
	}//End of getHazmat()
	
	/**
	 * This function will set the explosives field to true
	 */
	public void setExpTrue()
	{
		this.explosives= true;											//Sets the explosives field to true
	}//End of setExpTrue()
				
	/**
	 * This function will set the explosives field to false
	 */
	public void setExpFalse()
	{
		explosives = false;												//Sets the explosives field to false
	}//End of setExpFalse()
				
	/**
	 * This function will return the state of the explosives field
	 * @return Returns a boolean indicating if the TravelType can handle explosive material
	 */
	public boolean getExplosives()
	{
		return explosives;												//Returns the explosives field
	}//End of getExplosives()
	
	/**
	 * This function will set the tracking field to true
	 */
	public void setTrackingTrue()
	{
		this.tracking= true;											//Sets the tracking field to true
	}//End of setTrackingTrue()
					
	/**
	 * This function will set the tracking field to false
	 */
	public void setTrackingFalse()
	{
		tracking = false;												//Sets the tracking field to false
	}//End of setTrackingFalse()
					
	/**
	 * This function will return the state of the tracking field
	 * @return Returns a boolean indicating if the TravelType offers tracking
	 */
	public boolean getTracking()
	{
		return tracking;												//Returns the tracking field
	}//End of getTracking()
	
	/**
	 * This function overrides the parent's Update function and will handle changes made to the Truck object in the database
	 */
	@Override
	public boolean Update() 
	{
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the Truck is new insert it into the database by executing the following
				executeCommand("Insert into TravelTypes (VehicleTypeName, VehicleMode,Trailer1,Trailer2,MinimumCapacity,MaximumCapacity,ActualCapacity,MaxWeight,ServiceType,Radiation, Refridgeration, HazardousMaterial, ExplosiveMaterial, Tracking) Values ('"+
					    this.getTravelTypeName() + "','" +this.getTravelTypeMode() + "','"+ this.getTrailer1()+"','"+this.getTrailer2() + "','" + this.getMinCap() + "','" + this.getMaxCap()+ "','"+
						this.getActCap()+"','"+this.getMaxWeight()+"','"+this.getServiceType()+"',"+this.getRadiation()+","+this.getRefridgeration()+","+this.getHazmat()+","+this.getExplosives()+","+this.getTracking()+")");
				//Grab this Truck from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select VehicleTypeID from TravelTypes where VehicleTypeName = '" + this.getTravelTypeName() + "' AND VehicleMode = '"+this.getTravelTypeMode()+
						"' AND Trailer1 = '" + this.getTrailer1() + "' AND Trailer2 = '" + this.getTrailer2() + "' AND MinimumCapacity = '" + this.getMinCap() + 
						"' AND MaximumCapacity = '" + this.getMaxCap() + "' AND ActualCapacity = '" +this.getActCap() + "' AND MaxWeight = '" + this.getMaxWeight()+ "' AND ServiceType = '" + this.getServiceType()+
						 "' AND Radiation = '" + this.getRadiation()+ "' AND Refridgeration = '" + this.getRefridgeration()+ "' AND HazardousMaterial = '" + this.getHazmat()+ "' AND ExplosiveMaterial = '" + this.getExplosives()+
						 "' AND Tracking = '" + this.getTracking()+"'");
				//If this TravelType exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.vehicleTypeID = (Integer)temp.get(temp.size()-1).get("VehicleTypeID");									//Set the Truck id
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
						"' , ServiceType = '" + this.getServiceType() + "' , Radiation = " + this.getRadiation() + " , Refridgeration = " + this.getRefridgeration() +
						" , HazardousMaterial = " + this.getHazmat() + " , ExplosiveMaterial = " + this.getExplosives() + " , Tracking = " + this.getTracking() +
						", Deleted = " +this.isDeleted() + " Where VehicleTypeID = " +this.vehicleTypeID);
					MarkClean();																	//Mark the TravelType as clean
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);														//Print the error
			ex.printStackTrace();
			return false;
		}//End of catch block
		
	}//End of overridden Update()

	/**
	 * This is the overridden Delete function of the parent class and will set this TravelType as deleted in the database
	 */
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Update TravelTypes Set Deleted = true Where VehicleTypeID = " + this.vehicleTypeID);							//Delete this Truck
			this.MarkDeleted();
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);														//Print the error
			ex.printStackTrace();
			return false;
		}//End of catch block

	}//End of the overridden Delete()

	/**
	 * This function will load a TravelType from the database
	 * @param id This is the id of the TravelType to load from the database
	 * @return Returns a TravelType loaded from the database
	 */
	public static TravelType Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from TravelTypes where VehicleTypeID = " + id  + " AND Deleted = false");
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
			ex.printStackTrace();
		}
 		return null;
	}//End of Load(int id)
	
	/**
	 * This function will load all TravelTypes for a given Vehicle
	 * @param Vehicle v, the vehicle to retrieve data for
	 * @return ArrayList<TravelTypes>
	 */
	
	public static ArrayList<TravelType> LoadForVehicle(Vehicle v)
	{
		ArrayList<TravelType> returnList = new ArrayList<TravelType>();
		try 
		{
			ArrayList<Map<String,Object>> temp =executeQuery("SELECT * FROM vehicletraveltypeIndex vti left outer join traveltypes tt on vti.TravelTypeID = tt.VehicleTypeID where vti.VehicleID = '" + v.getId() +"' AND vti.TravelMode = '" + v.getTravelMode()+"'"+ " AND Deleted = false");
			for(int i = 0; i<temp.size();i++)
			{
				TravelType t = BuildFromDataRow(temp.get(i));
				returnList.add(t);
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
	 * Overload: This function will load all TravelTypes for a given Vehicle based on vehicle id and mode
	 * @param id, int id for vehicle
	 * @param mode, string for which travel mode the vehicle is
	 * @return ArrayList<TravelTypes>
	 */
	
	public static ArrayList<TravelType> LoadForVehicle(int id, String mode)
	{
		ArrayList<TravelType> returnList = new ArrayList<TravelType>();
		try 
		{
			ArrayList<Map<String,Object>> temp =executeQuery("SELECT * FROM vehicletraveltypeIndex vti left outer join traveltypes tt on vti.TravelTypeID = tt.VehicleTypeID where vti.VehicleID = '" + id +"' AND vti.TravelMode = '" + mode +"'"+ " AND Deleted = false");
			for(int i = 0; i<temp.size();i++)
			{
				TravelType t = BuildFromDataRow(temp.get(i));
				returnList.add(t);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
		return returnList;
	}
	public static ArrayList<TravelType> LoadNotInVehilce(Vehicle v)
	{
		ArrayList<TravelType> returnList = new ArrayList<TravelType>();
		try 
		{
			ArrayList<Map<String,Object>> temp =executeQuery("SELECT * FROM `multi-modal`.traveltypes where VehicleTypeID Not In("+
					"Select TravelTypeID from vehicletraveltypeindex where vehicleID = '" + v.getId() + "' AND TravelMode = '" + v.getTravelMode() + "'" + 
					")" + " AND VehicleMode = '" + v.getTravelMode() + "' AND Deleted = false");
			for(int i = 0; i<temp.size();i++)
			{
				TravelType t = BuildFromDataRow(temp.get(i));
				returnList.add(t);
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
	 * This function returns an ArrayList of TravelTypes loaded from the database based on the given where clause
	 * @param where This is the clause that determines which TravelTypes to load from the database
	 * @return Returns an ArrayList of TravelTypes determined by the where clause
	 */
	public static ArrayList<TravelType> LoadAll(String where)
	{
		ArrayList<TravelType> returnList = new ArrayList<TravelType>();
		try 
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from TravelType " +where  + " AND Deleted = false");
			for(int i = 0; i<temp.size();i++)
			{
				TravelType t = BuildFromDataRow(temp.get(i));
				returnList.add(t);
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
	 * @param data This is the data that will be used to build the TravelType
	 * @return Returns a new TravelType built from the passed in data
	 * @throws SQLException
	 */
		public static TravelType BuildFromDataRow(Map<String,Object> data) throws SQLException
		{
			//This code grabs each element that will be found in the database on the TravelType table and set the appropriate values for a new TravelType
			TravelType t = new TravelType((Integer)data.get("VehicleTypeID"));
			t.setActCap(Double.parseDouble(data.get("ActualCapacity").toString()));
			t.setMaxCap((Double.parseDouble(data.get("MaximumCapacity").toString())));//rs.getInt("Capacity"));
			t.setMinCap((Double.parseDouble(data.get("MinimumCapacity").toString())));//rs.getString("Contractor"));
			t.setMaxWeight((Double.parseDouble(data.get("MaxWeight").toString())));//,rs.getString("LocationName"));
			t.setServiceType((String)data.get("ServiceType"));//rs.getString("TruckType"));
			t.setTrailer1((String)data.get("Trailer1"));//rs.getString("Status"));		
			t.setTrailer2((String)data.get("Trailer2"));
			t.setTravelTypeMode((String)data.get("VehicleMode"));
			t.setTravelTypeName((String)data.get("VehicleTypeName"));
			if(Boolean.getBoolean(data.get("Radiation").toString())){
				t.setRadTrue();	
			}
			else
				t.setRadFalse();
			if(Boolean.getBoolean(data.get("HazardousMaterial").toString())){
				t.setHazTrue();	
			}
			else
				t.setHazFalse();
			if(Boolean.getBoolean(data.get("ExplosiveMaterial").toString())){
				t.setExpTrue();	
			}
			else
				t.setExpFalse();
			if(Boolean.getBoolean(data.get("Refridgeration").toString())){
				t.setRefTrue();	
			}
			else
				t.setRefFalse();
			if(Boolean.getBoolean(data.get("Tracking").toString())){
				t.setTrackingTrue();	
			}
			else
				t.setTrackingFalse();
			if(Boolean.getBoolean(data.get("Deleted").toString()))
				t.MarkDeleted();
			else
				t.MarkUndeleted();
			
			t.MarkClean();																//Mark the Truck as clean
			return t;
			
		}//End of BuildFromDataRow(Map<String,Object> data)
		
		/**
		 * This function overrides the toString function and returns the name of the Truck
		 */
		@Override
		public String toString()
		{
			return this.getTravelTypeName();														//Return the name of the Truck
		}//End of overridden toString()

}//End of TravelType Class