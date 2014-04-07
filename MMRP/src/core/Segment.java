
//======================================================================================//
//							Software Engineering MMRP Project							//
//									Dr. Sam Thangiah									//
//					Dan Miller, Zach Petrusch, Chris Solomon, and Jordan Schiller		//
//======================================================================================//
//									Segment Class										//
//======================================================================================//
// Purpose:																				//
//																						//
//======================================================================================//
package core;
import java.util.ArrayList;
import java.util.Map;
import java.sql.*;
import GUI.Log;


public class Segment extends BaseClass {

	private int id;												//The Segment id
	private int toID;											//The endpoint id
	private int fromID;											//The startpoint id
	private int VehicleID;										//The id of the vehicle on this segment
	private String mode;										//The mode of transport
	private double distance;									//The distance between the start and endpoints
	private double cost;										//The cost to travel along this segment 
	private int departureTime;									//The time the vehicle departs
	private int arrivalTime;									//The time the vehicle arrives
	private ShippingRate shippingRate;							//This is the shipping rate over the Segment
	private String lanes;
	public ArrayList<Shipment> onBoard;
	
	private static final int LOWEST_ARRIVAL_TIME = 0;			//This is the lowest possible arrival time
	private static final int LOWEST_DEPARTURE_TIME = 0;			//This is the lowest possible departure time
	
	/**
	 * This is the default constructor for the Segment
	 */
	public Segment()
	{
		MarkNew();												//Mark this Segment as new
		onBoard=new ArrayList<Shipment>();
	}//End of the default Segment constructor
	
	/**
	 * This is the argumented Segment constructor that takes an id value
	 * @param id This is the new id for the Segment
	 */
	public Segment(int id)
	{
		this.id=id;												//Set the Segment id
																//SHOULD WE ALSO SET THIS TO NEW OR DIRTY??
		onBoard=new ArrayList<Shipment>();
	}//End of the arguemented Segment constructor

	/**
	 * This function will return the Segment id
	 * @return Returns the ID of the Segment
	 */
	public int getID()
	{
		return id;												//Return the id
	}//End of getID()
	
	/**
	 * This function will set the Segment's distance
	 * @param newDistance This is the Segments new distance
	 */
	public void setDistance(double newDistance)
	{
		//Some error checking
		if(newDistance < 0){
			Log.writeLogSevere("The new distance for segment " + this.getID() + " was set below zero. The new " +
					"distance will be now set to zero.");
			newDistance = 0;
		}
		
		
		if(this.distance!=newDistance)
		{
			distance=newDistance;								//Set the distance
			MarkDirty();										//Mark this Segment as dirty
		}//End of update distance if
	}//End of setDistance(double d)
	
	/**
	 * This function returns the distance along the Segment
	 * @return Returns the distance between the start location and the end location on the Segment
	 */
	public double getDistance()
	{
		return this.distance;									//Return the distance
	}//End of getDistance()
	
	/**
	 * This function returns the ShippingRate over the Segment
	 * @return Returns the ShippingRate over the Segment
	 */
	public ShippingRate getShippingRate() {
		return shippingRate;
	}

	/**
	 * This function sets the ShippingRate over the Segment
	 * @param shippingRate This is the new ShippingRate of the Segment
	 */
	public void setShippingRate(ShippingRate shippingRate) {
		this.shippingRate = shippingRate;
	}
	
	/**
	 * This function sets the estimated arrival time of the vehicle on this Segment
	 * @param newArrivalTime This is the new arrival time for the vehicle at the end location
	 */
	public void setEstimatedArrivalTime(int newArrivalTime)
	{
		//Some error checking
		if(newArrivalTime < LOWEST_ARRIVAL_TIME){
			Log.writeLogSevere("The new estimate arrival time was set too low, so it has been corrected to " +
		LOWEST_ARRIVAL_TIME + ".");
			newArrivalTime = LOWEST_ARRIVAL_TIME;
		}else if(newArrivalTime < this.departureTime){
			Log.writeLogSevere("The new estimate arrival time was set below the departure time, so it has been corrected to " +
					this.departureTime + ".");
			newArrivalTime = this.departureTime;
		}

		if(this.arrivalTime!=newArrivalTime)
		{
			arrivalTime=newArrivalTime;							//Set the arrival time
			MarkDirty();										//Mark this Segment as dirty
		}//End of update if
	}//End of setEstimatedArrivalTime(int newArrivalTime)
	
	/**
	 * This function returns the estimated arrival time of the vehicle traveling over this Segment
	 * @return Returns the estimated arrival time of the vehicle traveling over this Segment
	 */
	public int getArrivalTime()
	{
		return this.arrivalTime;								//Return the arrivalTime
	}//End of getArrivalTime()
	
	/**
	 * This function sets the estimated departure time of the vehicle on this Segment
	 * @param newDepartureTime This is the new estimated departure time for the vehicle on this Segment
	 */
	public void setDepartureTime(int newDepartureTime)
	{
		//Some error checking
		if(newDepartureTime < LOWEST_DEPARTURE_TIME){
			Log.writeLogSevere("The new estimate departure time was set too low, so it has been corrected to " +
					LOWEST_DEPARTURE_TIME + ".");
			newDepartureTime = LOWEST_DEPARTURE_TIME;
		}else if(newDepartureTime > this.arrivalTime){
			Log.writeLogSevere("The new estimate departure time was set above the arrival time, so it has been corrected to " +
					this.arrivalTime + ".");
			newDepartureTime = this.arrivalTime;
		}
		
		if(this.departureTime!=newDepartureTime)
		{
			this.departureTime=newDepartureTime;				//Set the departureTime
			MarkDirty();										//Mark this Segment as dirty
		}//End of update if
	}//End of setDepartureTime(int newDepartureTime)
	
	/**
	 * This function returns the estimated departure time of the vehicle from the start location
	 * @return Returns the estimated departure time for the vehicle from the start location
	 */
	public int getDepartureTime()
	{
		return this.departureTime;								//Return departureTime
	}//End of getDepartureTime()
	
	/**
	 * This function sets the starting location of this Segment
	 * @param startLocationID This is the ID of the new start location of the Segment
	 */
	public void setStartLocation(int startLocationID)
	{
		if(fromID!=startLocationID)
		{
			fromID=startLocationID;								//Set the fromID
			MarkDirty();										//Mark this Segment as dirty
		}//End of update if
	}//End of setStartLocation(int startLocationID)
	
	/**
	 * This function sets the starting location of this Segment using a Location object
	 * @param start This is the new starting location for the Segment
	 */
	public void setStartLocation(Location start)
	{
		if(fromID!=start.getID())
		{
			fromID=start.getID();								//Set the fromID
			MarkDirty();										//Mark the Segment as dirty
		}//End of update if
	}//End of setStartLocation(Location start)
	
	/**
	 * This function returns the starting location id
	 * @return Returns the ID of the start location
	 */
	public int getStartLocationID()
	{
		return fromID;											//Return the fromID
	}//End of getStartLocationID()
	
	/**
	 * This function returns the Location object associated with the fromID
	 * @return Returns the start location of this Segment
	 */
	public Location getStartLocation()
	{
		return Location.Load(fromID);							//Return the Location of this Segments start
	}//End of getStartLocation()
	
	/**
	 * This function sets the end location of the Segment
	 * @param endLocationID This is the ID of the new end location for the Segment
	 */
	public void setEndLocation(int endLocationID)
	{
		if(toID!=endLocationID)
		{
			toID=endLocationID;									//Set the end point id
			MarkDirty();										//Mark the Segment as dirty
		}//End of update if
	}//End of setEndLocation(int endLocationID)
	
	/**
	 * This function sets the endpoint of Segment using a Location object
	 * @param end This is the new ending location of the Segment
	 */
	public void setEndLocation(Location end)
	{
		if(toID!=end.getID())
		{
			toID=end.getID();									//Set the toID
			MarkDirty();										//Mark the Segment as dirty
		}//End of update if
	}//End of setEndLocation(Location end)
	
	/**
	 * This function returns the end location of the Segment
	 * @return Returns the ID of the end location 
	 */
	public int getEndLocationID()
	{
		return toID;											//Return toID
	}//End of getEndLocationID()
	
	/**
	 * This function returns the end location of the Segment as a Location object
	 * @return Returns the end location of the Segment
	 */
	public Location getEndLocation()
	{
		return Location.Load(toID);								//Return the end point Location
	}//End of getEndLocation()
	
	/**
	 * This function sets the Segment's Vehicle
	 * @param vehicle This is the vehicle traveling over the Segment
	 */
	public void setVehicle(Vehicle vehicle)
	{
		if(this.VehicleID!=vehicle.getId() || this.mode==null || !this.mode.equals(vehicle.getTravelMode()))
		{
			this.VehicleID=vehicle.getId();						//Set the vehicle id
			mode=vehicle.getTravelMode();						//Set the travel type
			MarkDirty();										//Mark the Segment as dirty
		}//End of valid vehicle if
	}//End of setVehicle(Vehicle vehicle)
	
	/**
	 * This function sets the Segment's Vehicle using an id and travel mode
	 * @param vehicleID This is the ID of the vehicle that will travel along this Segment 
	 * @param travelMode This is the travel mode of the vehicle
	 */
	public void setVehicle(int vehicleID,String travelMode)
	{
		//todo:ADD check on travel mode: if not exist do nothing
		if(this.VehicleID!=vehicleID || this.mode==null || !this.mode.equals(travelMode))
		{
			this.VehicleID=vehicleID;							//Set the vehicle id
			mode=travelMode;									//Set the travel type
			MarkDirty();										//Mark the Segment as dirty
		}//End of valid vehicle if
	}//End of setVehicle(int vehicleID,String travelMode)
	
	/**
	 * This function returns the vehicle id
	 * @return Returns the ID of the vehicle traveling along this segment
	 */
	public int getVehicleID()
	{
		return this.VehicleID;									//Return the vehicle id
	}//End of getVehicleID()
	
	/**
	 * This function returns the Travel Mode on this Segment
	 * @return Returns the mode of travel along this Segment
	 */
	public String getTravelMode()
	{
		return this.mode;										//Return the travel type
	}//End of getTravelMode()
	
	/**
	 * This function returns the Vehicle object on this Segment
	 * @return Returns the vehicle object on this Segment
	 */
	public Vehicle getVehicle()
	{
		//Load the Vehicle from the database based on the type of the Vehicle
		switch(Vehicle.loadMode(mode))
		{
			case Truck:
				return Truck.Load(this.VehicleID);		//Return Truck
			case Rail:
				return Rail.Load(this.VehicleID);		//Return Rail 
			case Cargo:
				return Cargo.Load(this.VehicleID);		//Return Cargo
			case Plane:
				return Plane.Load(this.VehicleID);		//Return Plane
			case Bike:
				return Bike.Load(this.VehicleID);		//Return Bike
		
		}//End of switch
		return null;
	}//End of getVehicle()
	
	/**
	 * This function sets the lane of travel on the Segment
	 * @param newLane This is the new lane of travel for the Segment
	 */
	public void setLane(String newLane)
	{
		if(lanes==null||!lanes.equals(newLane))
		{
			lanes = newLane;
			MarkDirty();
		}
	}//End of setLane(String newLane)
	
	/**
	 * This function returns the lane of travel along this Segment
	 * @return Returns the lane of travel on this Segment
	 */
	public String getLane()
	{
		return lanes;
	}//End of getLane()
	
	/**
	 * This function returns all the Segments specified in the given where clause
	 * @param where This is the where clause specifying the Segments to load from the database
	 * @return Returns an ArrayList of the Segments specified in the where clause
	 */
	public static ArrayList<Segment> LoadAll(String where)
	{
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try
		{
			//Create and populate an ArrayList of Segments
			ArrayList<Map<String,Object>> temp=executeQuery("Select * from Segment " + where);
			
			//For each of the entries in our list create a Segment object
			for(int i = 0; i<temp.size();i++)
			{
				Segment s = BuildFromDataRow(temp.get(i));
				s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
			
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}//End of catch block
		return returnList;
	}//End of LoadAll(String where)
	
	/**
	 * This function builds objects from returned data from SQL queries against our database
	 * @param data This is the data that will be used to build the Segment object
	 * @return Returns a Segment object built from the passed in data
	 * @throws SQLException
	 */
	public static Segment BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		//This code grabs each element that will be found in the database on the Segment table and set the appropriate values for a new Segment
		Segment s = new Segment((Integer)data.get("SegmentID"));
		s.setStartLocation((Integer)data.get("FromLocationID"));
		s.setEndLocation((Integer)data.get("ToLocationID"));
		s.setVehicle((Integer)data.get("VehicleID"),(String)data.get("ModeType"));
		s.setDistance(Double.parseDouble(data.get("Distance").toString()));
		s.setDepartureTime((Integer)data.get("TimeOfDeparture"));
		s.setEstimatedArrivalTime((Integer)data.get("TimeOfArrival"));
		s.setLane((String)data.get("Lane"));
		s.setShippingRate(ShippingRate.Load((Integer)data.get("ShippingRateID")));
		s.MarkClean();													//Mark the Segment as clean
		return s;
	}//End of BuildFromDataRow(Map<String,Object> data)
	
	/**
	 * This function returns an ArrayList of all the Segments that begin at the given Location
	 * @param start This is the start location of all the Segments to be loaded
	 * @return Returns an ArrayList of all the Segments that start at the given location
	 */
	public static ArrayList<Segment> LoadAllAtLocation(Location start)
	{
		//Create an empty list of Segments
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try
		{
			//Populate the list with Segments starting at the given Location
			ArrayList<Map<String,Object>>temp=executeQuery("Select * from Segment where FromLocationID = '" + start.getID() +"'");
			//For each returned Segment create a new Segment object 
			for(int i = 0 ; i < temp.size();i++)
			{
				Segment s = BuildFromDataRow(temp.get(i));
				s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);							//Print out the error
		}//End of catch block
		return returnList;
	}//End of LoadAllAtLocation(Location start)
	
	/**
	 * This function returns an ArrayList of Segments of all the Segments that start at the given Location and leave after the given start time
	 * @param start This is the starting location of the Segments to load
	 * @param startTime This is the earliest estimated time that the vehicle on this Segment can leave
	 * @return Returns an ArrayList of the Segments that start at the given location and leave after the given time
	 */
	public static ArrayList<Segment> LoadAllAtLocation(Location start, int startTime)
	{
		//Create a new list of Segments
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try
		{
			//Populate a list with all the Segments that match the criteria
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Segment where FromLocationID = '" + start.getID() + "' and TimeOfDeparture > " + startTime);
			//For each returned Segment create a new Segment object on our list
			for(int i = 0; i < temp.size();i++)
			{
				Segment s = BuildFromDataRow(temp.get(i));
				s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);											//Print out the error
		}//End of catch block
		return returnList;
	}//End of LoadAllAtLocation(Location start, int startTime)
	
	/**
	 * This function returns an ArrayList of all the Segments that begin at the given LocationID
	 * @param startLocationID This is the start location ID of all the Segments to be loaded
	 * @return Returns an ArrayList of all the Segments that start at the given locationID
	 */
	public static ArrayList<Segment> LoadAllAtLocation(int startLocationID)
	{
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try
		{
			ArrayList<Map<String,Object>>temp=executeQuery("Select * from Segment where FromLocationID = '" + startLocationID +"'");
			for(int i = 0 ; i < temp.size();i++)
			{
				Segment s = BuildFromDataRow(temp.get(i));
				s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}//End of LoadAllAtLocation(int startLocationID)
	
	/**
	 * This function returns an ArrayList of Segments of all the Segments that start at the given Location id and leave after the given start time
	 * @param locationID This is the starting location id of the Segments to load
	 * @param startTime This is the earliest estimated time that the vehicle on this Segment can leave
	 * @return Returns an ArrayList of the Segments that start at the given location id and leave after the given time
	 */
	public static ArrayList<Segment> LoadAllAtLocation(int locationID, int startTime)
	{
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Segment where FromLocationID = '" + locationID + "' and TimeOfDeparture > " + startTime);
			for(int i = 0; i < temp.size();i++)
			{
				Segment s = BuildFromDataRow(temp.get(i));
				s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}//End of LoadAllAtLocation(int locationID, int startTime)
	
	
	/**
	 * This function will load a Segment from the database based on the given ID
	 * @param id This is the ID of the Segment to load from the database
	 * @return Returns a Segment object based on the id passed in
	 */
	public static Segment Load(int id)
	{
		try
		{
			//Search the database for Segments with the given id
			ArrayList<Map<String,Object>> temp= executeQuery("Select * from Segment where SegmentID = " +id);
			//If something was returned build the Segment from it
			if(temp.size()>0)
			{
				Segment s = BuildFromDataRow(temp.get(0));
				s.onBoard= Shipment.LoadAllForSegment(s.getID());
				
				return s;
			}
			return null;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " +ex);
			return null;
		}//End of catch block
	}//End of Load(int id)
		
	/**
	 * This function overrides the parent's Update function and will handle changes made to the Segment object in the database
	 */
	@Override
	public void Update() 
	{
		//MORE COMMENTS. CATDOG FOR THE WIN!!!!
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the Segment is new insert it into the database by executing the following
				executeCommand("Insert into Segment (FromLocationID,ToLocationID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID) Values ('"+
						this.getStartLocationID()+"','"+this.getEndLocationID()+"','"+this.getVehicleID()+"','"+this.getTravelMode()+"','"+this.getDistance()+"','"+
					    this.getDepartureTime()+"','"+this.getArrivalTime()+"','"+this.getLane() +"','"+this.getShippingRate().getId() +"')");
				//Grab this Segment from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select SegmentID from Segment where FromLocationID ='"+ this.getStartLocationID()+"' "+
						"AND ToLocationID ='" + this.getEndLocationID() +"' "+
						"AND VehicleID='" + this.getVehicleID()+"' "+
						"And ModeType='" + this.getTravelMode()+"' "+
						"And Distance='"+this.getDistance()+"' "+
						"And TimeOfDeparture ='"+this.getDepartureTime()+"' "+
						"And Lane ='"+this.getLane()+"' "+
						"And ShippingRateID ='"+this.getShippingRate().getId()+"' "+
						"And TimeOfArrival = '"+this.getArrivalTime()+"'");
				//If this Segment exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("SegmentID");				//Set the Segment id to the id from the database
					MarkClean();													//Mark the Segment as clean
					MarkOld();														//Mark the Segment as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Segment is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Segment Set FromLocationID ='"+ this.getStartLocationID()+"' "+
							"AND ToLocationID ='" + this.getEndLocationID() +"' "+
							"AND VehicleID='" + this.getVehicleID()+"' "+
							"And ModeType='" + this.getTravelMode()+"' "+
							"And Distance='"+this.getDistance()+"' "+
							"And TimeOfDeparture ='"+this.getDepartureTime()+"' "+
							"And Lane ='"+this.getLane()+"' "+
							"And ShippingRateID ='"+this.getShippingRate().getId()+"' "+
							"And TimeOfArrival = '"+this.getArrivalTime()+"' Where SegmentID="+this.id);
					MarkClean();													//Mark the Segment as clean
				}//End of isDirty if
			}//End of isOld else
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);										//Print out the error
		}//End of catch block
			
	}//End of the overridden Update()

	/**
	 * This function estimates the capacity available over this Segment
	 * @return Returns the estimated capacity along this Segment
	 */
	public int estimateCapacity()
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select Sum(Size)as Capacity from Shipment where ShipmentID IN (Select Distinct ShipmentID from ShipmentHistory where SegmentID = " + this.id+")");
			if(temp.get(0).get("Capacity")!=null)
			{
				System.out.println(temp.get(0).get("Capacity"));
				return Integer.parseInt(temp.get(0).get("Capacity").toString());
			}
			else
				return 0;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			return -1;
		}
	}//End of estimateCapacity()
	
	/**
	 * This is the overridden Delete function of the parent class and will remove this Segment from the database
	 */
	@Override
	public  void Delete() 
	{
		try
		{
			executeCommand("Delete from Segment Where SegmentID = " + this.id);			//Delete the Segment
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);										//Print out the error
		}//End of catch block

	}//End of the overridden Delete()
	
	/**
	 * This is the overridden toString function for the Segment
	 * @return Returns the id of the start location and the end location
	 */
	@Override
	public String toString()
	{
		return this.fromID + "   " + this.toID;
	}//End of toString()

	
}//End of Segment Class
