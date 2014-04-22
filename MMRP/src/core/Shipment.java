package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import GUI.Log;

//Shipment class
public class Shipment extends BaseClass {

	private int fromLocationID,toLocationID;
	private int priority;
	private int size;
	private int weight;
	private int earliestArrival, latestArrival;
	private int id;
	private int earliestDeparture,latestDeparture;
	private ArrayList<ShipmentHistory> history;
	private int currentLocation;
	private int timeToLoad,timeToUnload;
	private int shipperID;
	private Boolean tollRoads;
	private Boolean congestionByPass;
	private int maxStops;
	//TODO:Need to added hazmat constraints to vehicles as well
	private String hazmatConstraints;
	
	//TODO: Might be better suited as a property of vehicles
	private int loadingRate;
	//TODO: I Have no clue where these should go just yet
	private String trailerType,loadingType,unloadingType;
	//TODO: prefCarriers should probably be a property of a shipper not a shipment
	private String prefCarriers;
	
	//Default Variables
	private static final int DEFAULT_START_LOCATION_ID = 1;
	private static final int DEFAULT_END_LOCATION_ID = 2;
	private static final int DEFAULT_PRIORITY = 1;
	private static final int DEFAULT_SIZE = 50;
	private static final int DEFAULT_WEIGHT = 100;
	private static final int DEFAULT_EARLIEST_ARRIVAL = 50;
	private static final int DEFAULT_LATEST_ARRIVAL = 50;
	private static final int DEFAULT_EARLIEST_DEPARTURE = 0;
	private static final int DEFAULT_LATEST_DEPARTURE = 0;
	private static final int DEFAULT_TIME_TO_LOAD = 0;
	private static final int DEFAULT_TIME_TO_UNLOAD = 0;
	private static final int DEFAULT_SHIPPER_ID = 1;
	private static final int DEFAULT_MAX_STOPS = 100;
	private static final String DEFAULT_HAZMAT_CONSTRAINTS = "defaultHazmatConstraints";
	private static final int DEFAULT_LOADING_RATE = 1;
	private static final String DEFAULT_TRAILER_TYPE = "defaultTrailerType";
	private static final String DEFAULT_LOADING_TYPE = "defaultLoadingType";
	private static final String DEFAULT_UNLOADING_TYPE = "defaultUnloadingType";
	private static final String DEFAULT_PREFERRED_CARRIERS = "defaultPreferredCarriers";
 
	private static final int MIN_SIZE = 0;
	private static final int MAX_SIZE = 100;
	private static final int MIN_PRIORITY = 1;
	private static final int MAX_PRIORITY = 10;
	private static final int MIN_WEIGHT = 1;
	private static final int MAX_WEIGHT = 500;
	private static final int MIN_LOAD_TIME = 0;
	private static final int MAX_LOAD_TIME = 100;
	private static final int MIN_LOAD_RATE = 1;
	private static final int MAX_LOAD_RATE = 100;
	private static final int MIN_STOPS = 1;
	private static final int MAX_STOPS = 500;
	
	/**
	 * This is the default constructor for the Shipment object
	 */
	public Shipment()
	{
		this.congestionByPass = false;
		this.earliestArrival = DEFAULT_EARLIEST_ARRIVAL;
		this.earliestDeparture = DEFAULT_EARLIEST_DEPARTURE;
		this.fromLocationID = DEFAULT_START_LOCATION_ID;
		this.hazmatConstraints = DEFAULT_HAZMAT_CONSTRAINTS;
		this.latestArrival = DEFAULT_LATEST_ARRIVAL;
		this.latestDeparture = DEFAULT_LATEST_DEPARTURE;
		this.loadingRate = DEFAULT_LOADING_RATE;
		this.loadingType = DEFAULT_LOADING_TYPE;
		this.maxStops = DEFAULT_MAX_STOPS;
		this.prefCarriers = DEFAULT_PREFERRED_CARRIERS;
		this.priority = DEFAULT_PRIORITY;
		this.shipperID = DEFAULT_SHIPPER_ID;
		this.size = DEFAULT_SIZE;
		this.timeToLoad = DEFAULT_TIME_TO_LOAD;
		this.timeToUnload = DEFAULT_TIME_TO_UNLOAD;
		this.tollRoads = false;
		this.toLocationID = DEFAULT_END_LOCATION_ID;
		this.trailerType = DEFAULT_TRAILER_TYPE;
		this.unloadingType = DEFAULT_UNLOADING_TYPE;
		this.weight = DEFAULT_WEIGHT;
		this.currentLocation = DEFAULT_START_LOCATION_ID;
		this.MarkNew();
		this.MarkClean();
		this.MarkUndeleted();
	}//End of the default Shipment constructor
	
	/**
	 * This is the argumented Shipment constructor
	 * @param id This is the id of the new Shipment
	 */
	public Shipment(int id)
	{
		this.id=id;
		this.congestionByPass = false;
		this.earliestArrival = DEFAULT_EARLIEST_ARRIVAL;
		this.earliestDeparture = DEFAULT_EARLIEST_DEPARTURE;
		this.fromLocationID = DEFAULT_START_LOCATION_ID;
		this.hazmatConstraints = DEFAULT_HAZMAT_CONSTRAINTS;
		this.latestArrival = DEFAULT_LATEST_ARRIVAL;
		this.latestDeparture = DEFAULT_LATEST_DEPARTURE;
		this.loadingRate = DEFAULT_LOADING_RATE;
		this.loadingType = DEFAULT_LOADING_TYPE;
		this.maxStops = DEFAULT_MAX_STOPS;
		this.prefCarriers = DEFAULT_PREFERRED_CARRIERS;
		this.priority = DEFAULT_PRIORITY;
		this.shipperID = DEFAULT_SHIPPER_ID;
		this.size = DEFAULT_SIZE;
		this.timeToLoad = DEFAULT_TIME_TO_LOAD;
		this.timeToUnload = DEFAULT_TIME_TO_UNLOAD;
		this.tollRoads = false;
		this.toLocationID = DEFAULT_END_LOCATION_ID;
		this.trailerType = DEFAULT_TRAILER_TYPE;
		this.unloadingType = DEFAULT_UNLOADING_TYPE;
		this.weight = DEFAULT_WEIGHT;
		this.currentLocation = DEFAULT_START_LOCATION_ID;
		this.MarkNew();
		this.MarkClean();
		this.MarkUndeleted();
	}//End of the Shipment(int id) constructor
	
	/**
	 * This function returns the current location id of the Shipment
	 * @return Returns the current location id of the shipment
	 */
	public int getCurrentLocationID()
	{
		return currentLocation;
	}//End of getCurrentLocationID()
	
	/**
	 * This function sets the current location id of the Shipment
	 * @param locationID This is the new current location ID
	 */
	public void setCurrentLocation(int loc)
	{
		//NEED ERROR CHECKING
		if(this.currentLocation!=loc)
		{
			currentLocation=loc;
			MarkDirty();
		}
	}//End of setCurrentLocation(int loc)
	
	/**
	 * This function returns the start location id of the shipment
	 * @return Returns the start location id of the shipment
	 */
	public int getFromLocationID() {
		return fromLocationID;
	}//End of getFromLocationID()
	
	/**
	 * This function will set the start location ID
	 * @param fromLocationID This is the new start location ID
	 */
	public void setFromLocationID(int fromLocationID) {
		//NEED SOME ERROR CHECKING
		if(this.fromLocationID!=fromLocationID)
		{
			this.fromLocationID = fromLocationID;
			MarkDirty();
		}
	}//End of setFromLocationID(int fromLocationID)
	
	/**
	 * This function returns the end location id
	 * @return Returns the end location id
	 */
	public int getToLocationID() {
		return toLocationID;
	}//End of getToLocationID()
	
	/**
	 * This function sets the end location id of the Shipment
	 * @param toLocationID This is the new end location id
	 */
	public void setToLocationID(int toLocationID) {
		//NEEDS SOME ERROR CHECKING
		if(this.toLocationID != toLocationID)
		{
			this.toLocationID = toLocationID;
			MarkDirty();
		}
	}//End of setToLocationID(int toLocationID)
	
	/**
	 * This function returns the priority of the Shipment
	 * @return Returns the priority of the Shipment
	 */
	public int getPriority() {
		return priority;
	}//End of getPriority()
	
	/**
	 * This function will set the priority for the Shipment
	 * @param priority This is the new priority for the Shipment
	 */
	public void setPriority(int priority) {
		if(this.priority!=priority)
		{
			if(FormatChecker.inRange(MIN_PRIORITY, MAX_PRIORITY, priority))
			{
				this.priority = priority;
			}
			else
			{
				this.priority = DEFAULT_PRIORITY;
				Log.writeLogWarning("Invalid number given for priority of shipment. Set priority to "+
				DEFAULT_PRIORITY);
			}
			MarkDirty();
		}
	}//End of setPriority(int priority)
	
	/**
	 * This function returns the size of the Shipment
	 * @return Returns the size of the Shipment
	 */
	public int getSize() {
		return size;
	}//End of getSize()
	
	/**
	 * This function sets the size of the Shipment
	 * @param size This is the new size of the Shipment
	 */
	public void setSize(int size) {
		if(this.size!=size)
		{
			if(FormatChecker.inRange(MIN_SIZE, MAX_SIZE, size))
			{
				this.size = size;
			}
			else
			{
				this.size = DEFAULT_SIZE;
				Log.writeLogWarning("Invalid number given for size of shipment. Set size to "+DEFAULT_SIZE);
			}
			MarkDirty();
		}
	}//End of setSize(int size)
	
	/**
	 * This function returns the earliest arrival time of the Shipment
	 * @return Returns the earliest arrival time of the Shipment
	 */
	public int getEarliestArrivalTime() {
		return earliestArrival;
	}//End of getEarliestArrivalTime()
	
	/**
	 * This function sets the earliest arrival time for the Shipment
	 * @param time This is the new earliest arrival time for the Shipment
	 */
	public void setEarliestArrivalTime(int time) {
		//NEED SOME ERROR CHECKING
		if(this.earliestArrival!=time)
		{
			this.earliestArrival = time;
			MarkDirty();
		}
	}//End of setEarliestArrivalTime(int time)
	
	/**
	 * This function returns the latest arrival time of the Shipment
	 * @return Returns the latest arrival time of the Shipment
	 */
	public int getLatestArrivalTime() {
		return latestArrival;
	}//End of getLatestArrivalTime()
	
	/**
	 * This function sets the latest arrival time for the Shipment
	 * @param time
	 */
	public void setLatestArrivalTime(int time) {
		//NEED ERROR CHECKING
		if(this.latestArrival!=time)
		{
			this.latestArrival = time;
			MarkDirty();
		}
	}//End of setLatestArrivalTime(int time)
	
	/**
	 * This function returns the earliest departure time for the Shipment
	 * @return Returns the earliest departure time for the Shipment
	 */
	public int getEarliestDepartureTime() {
		return earliestDeparture;
	}//End of getEarliestDepartureTime()
	
	/**
	 * This function sets the earliest departure time for the Shipment
	 * @param departureTime This is the new earliest departure time for the Shipment
	 */
	public void setEarliestDepartureTime(int departureTime) {
		//NEED ERROR CHECKING
		if(this.earliestDeparture!=departureTime)
		{
			this.earliestDeparture = departureTime;
			MarkDirty();
		}
	}//End of setEarliestDepartureTime(int departureTime)
	
	/**
	 * This function returns the latest departure time for the Shipment
	 * @return Returns the latest departure time for the Shipment
	 */
	public int getLatestDepartureTime()
	{
		return latestDeparture;
	}//End of getLatestDepartureTime()
	
	/**
	 * This function sets the latest departure time for the Shipment
	 * @param departureTime This is the new latest departure time for the Shipment
	 */
	public void setLatestDepartureTime(int departureTime)
	{
		//NEEDS ERROR CHECKING
		if(this.latestDeparture!=departureTime)
		{
			this.latestDeparture=departureTime;
			MarkDirty();
		}
	}//End of setLatestDepartureTime(int departureTime)
	
	/**
	 * This function returns the id of the Shipment
	 * @return Returns the id of the Shipment
	 */
	public int getId() {
		return id;
	}//End of getId()
	
	/**
	 * This function will return the time it takes to load the Shipment
	 * @return Returns the time it will take to load the Shipment 
	 */
	public int getTimeToLoad()
	{
		return this.timeToLoad;
	}//End of getTimeToLoad()
	
	/**
	 * This function will set the time to load the Shipment
	 * @param t This is the new time to load the Shipment
	 */
	public void setTimeToLoad(int t)
	{
		if(this.timeToLoad!=t)
		{
			if(FormatChecker.inRange(MIN_LOAD_TIME, MAX_LOAD_TIME, t))
			{
				this.timeToLoad = t;
			}
			else
			{
				this.timeToLoad = DEFAULT_TIME_TO_LOAD;
				Log.writeLogWarning("Invalid number given for time to load. Set load time to "+
						DEFAULT_TIME_TO_LOAD);
			}
			MarkDirty();
		}
	}//End of setTimeToLoad(int t)
	
	/**
	 * This function returns the time to unload the Shipment
	 * @return Returns the time to unload the Shipment
	 */
	public int getTimeToUnload()
	{
		return this.timeToUnload;
	}//End of getTimeToUnload()
	
	/**
	 * This function sets the time to unload the Shipment
	 * @param t This is the new time to unload the Shipment
	 */
	public void setTimeToUnload(int t)
	{
		if(this.timeToUnload!=t)
		{
			if(FormatChecker.inRange(MIN_LOAD_TIME, MAX_LOAD_TIME, t))
			{
				this.timeToUnload = t;
			}
			else
			{
				this.timeToUnload = DEFAULT_TIME_TO_UNLOAD;
				Log.writeLogWarning("Invalid number given for time to unload. Set unload time to "+
						DEFAULT_TIME_TO_UNLOAD);
			}
			MarkDirty();
		}
	}//End of setTimeToUnload(int t)
	
	/**
	 * This function sets the shipper id for the Shipment
	 * @param id This is the new Shipper id for the shipment
	 */
	public void setShipperID(int id)
	{
		//NEED ERROR CHECKING
		if(this.shipperID!=id)
		{
			this.shipperID=id;
			MarkDirty();
		}
	}//End of setShipperID(int id)
	
	/**
	 * This function returns the shipper id of the Shipment
	 * @return Returns the id of the shipper of the Shipment
	 */
	public int getShipperID()
	{
		return this.shipperID;
	}//End of getShipperID()
	
	/**
	 * This function allows the Shipment to take toll roads
	 */
	public void setTollRoadsTrue()
	{
		if(this.tollRoads!=true)
		{
			tollRoads=true;
			MarkDirty();
		}
	}//End of setTollRoadsTrue()
	
	/**
	 * This function disallows the Shipment to take toll roads
	 */
	public void setTollRoadsFalse()
	{
		if(this.tollRoads!=false)
		{
			tollRoads=false;
			MarkDirty();
		}
	}//End of setTollRoadsFalse()
	
	/**
	 * This function will return whether or not the Shipment can take toll roads
	 * @return Returns a boolean indicating if the Shipment can travel on toll roads or not
	 */
	public Boolean getTollRoads()
	{
		return tollRoads;
	}//End of getTollRoads()
	
	/**
	 * This function allow the Shipment to perform a congestion by pass
	 */
	public void setCongestionByPassTrue()
	{
		if(this.congestionByPass!=true)
		{
			this.congestionByPass=true;
			MarkDirty();
		}
	}//End of setCongestionByPassTrue()
	
	/**
	 * This function disallow the Shipment to perform a congestion by pass
	 */
	public void setCongestionByPassFalse()
	{
		if(this.congestionByPass!=false)
		{
			this.congestionByPass=false;
			MarkDirty();
		}
	}//End of setCongestionByPassFalse()
	
	/**
	 * This function will return whether or not the Shipment can perform a congestion by pass
	 * @return Returns a boolean indicating if the Shipment can perform a congestion by pass
	 */
	public Boolean getCongestionByPass()
	{
		return this.congestionByPass;
	}//End of getCongestionByPass()
	
	/**
	 * This function sets the hazmat status for the Shipment
	 * @param s This is the new hazmat status
	 */
	public void setHazmat(String s)
	{
		//NEEDS ERROR CHECKING
		if(this.hazmatConstraints==null || !this.hazmatConstraints.equals(s))
		{
			this.hazmatConstraints=s;
			MarkDirty();
		}
	}//End of setHazmat(String s)
	
	/**
	 * This function returns the Shipment's hazmat status
	 * @return Returns the Shipment's hazmat status
	 */
	public String getHazmat()
	{
		return this.hazmatConstraints;
	}//End of getHazmat()
	
	/**
	 * This function sets the maximum number of stop locations for the Shipment
	 * @param maxStop This is the new maximum number of stops
	 */
	public void setMaxStops(int maxStop)
	{
		if(this.maxStops!=maxStop)
		{
			if(FormatChecker.inRange(maxStop, MIN_STOPS, MAX_STOPS))
			{
				maxStops=maxStop;
			}
			else
			{
				Log.writeLogSevere("Invalid number given for max number of stops. Set max number of stops to "+
			DEFAULT_MAX_STOPS);
				maxStops = DEFAULT_MAX_STOPS;
			}
			MarkDirty();
		}
	}//End of setMaxStops(int s)
	
	/**
	 * This function will return the maximum number of stops the Shipment is allowed
	 * @return Returns the maximum number of stops the Shipment is allowed
	 */
	public int getMaxStops()
	{
		return maxStops;
	}//End of getMaxStops()
	
	/**
	 * This function will return the loading rate of the Shipment
	 * @return Returns the loading rate for the Shipment
	 */
	public int getLoadingRate()
	{
		return this.loadingRate;
	}//End of getLoadingRate()
	
	/**
	 * This function will set the loading rate for the Shipment
	 * @param newLoadingRate This is the new loading rate for teh Shipment
	 */
	public void setLoadingRate(int newLoadingRate)
	{
		if(this.loadingRate!=newLoadingRate)
		{
			if(FormatChecker.inRange(newLoadingRate, MIN_LOAD_RATE, MAX_LOAD_RATE))
			{
				this.loadingRate=newLoadingRate;
			}
			else
			{
				Log.writeLogSevere("Invalid value for loading rate. Set loading rate to "+DEFAULT_LOADING_RATE);
				this.loadingRate = DEFAULT_LOADING_RATE;
			}
			MarkDirty();
		}
	}//End of setLoadingRate(int newLoadingRate)
	
	/**
	 * This function returns the unloading type for the Shipment
	 * @return Returns the unloading type for the Shipment
	 */
	public String getUnloadType()
	{
		return this.unloadingType;
	}//End of getUnloadType()
	
	/**
	 * This function sets the unloading type for the Shipment
	 * @param newUnloadingType This is the new unloading type of the Shipment
	 */
	public void setUnloadType(String newUnloadingType)
	{
		//NEEDS ERROR CHECKING
		if(this.unloadingType==null||!unloadingType.equals(newUnloadingType))
		{
			this.unloadingType=newUnloadingType;
			MarkDirty();
		}
	}//End of setUnloadType(String newUnloadingType)
	
	/**
	 * This function will set the loading type for the Shipment
	 * @param newLoadingType This is the new loading type for the Shipment
	 */
	public void setLoadingType(String newLoadingType)
	{
		//NEEDS ERROR CHECKING
		if(this.loadingType==null || !this.loadingType.equals(newLoadingType))
		{
			this.loadingType=newLoadingType;
			MarkDirty();
		}
	}//End of setLoadingType(String t)
	
	/**
	 * This function sets the trailer type for the Shipment
	 * @param newTrailerType This is the new trailer type for the Shipment
	 */
	public void setTrailerType(String newTrailerType)
	{
		//Error checking
		if(this.trailerType==null || !this.trailerType.equals(newTrailerType))
		{
			this.trailerType=newTrailerType;
			MarkDirty();
		}
	}//End of setTrailerType(String newTrailerType)
	
	/**
	 * This function returns the trailer type for the Shipment
	 * @return Returns the trailer type of the Shipment
	 */
	public String getTrailerType()
	{
		return this.trailerType;
	}//End of getTrailerType()
	
	/**
	 * This function sets the Carrier's preference for the Shipment
	 * @param newCarrierPref This is the new Carrier's preference for the Shipment
	 */
	public void setPrefCarrier(String newCarrierPref)
	{
		//NEED ERROR CHECKING
		if(this.prefCarriers==null || !this.prefCarriers.equals(newCarrierPref))
		{
			this.prefCarriers=newCarrierPref;
			MarkDirty();
		}
	}//End of setPrefCarrier(String newCarrierPref)
	
	/**
	 * This function will return the Carrier's preference for the Shipment
	 * @return Returns the Carrier's preference for the Shipment
	 */
	public String getPrefCarriers()
	{
		return this.prefCarriers;
	}//End of getPrefCarriers()
	
	/**
	 * This function sets the history of the Shipment
	 * @param hist This is the new history for the Shipment
	 */
	public void setHistory(ArrayList<ShipmentHistory> hist)
	{
		//Need some error checking and fleshing out
		history=hist;
		for(int i = 0; i<hist.size();i++)
		{
			
		}
	}//End of setHistory(ArrayList<ShipmentHistory> hist)
	
	/**
	 * This function sets the weight of the Shipment
	 * @param newWeight This is the newWeight for the Shipment
	 */
	public void setWeight(int newWeight)
	{
		if(this.weight!=newWeight)
		{
			if(FormatChecker.inRange(newWeight, MIN_WEIGHT, MAX_WEIGHT))
			{
				this.weight=newWeight;
			}
			else
			{
				Log.writeLogWarning("Invalid weight. Set weight to "+DEFAULT_WEIGHT);
				this.weight = DEFAULT_WEIGHT;
			}
			MarkDirty();
		}
	}//End of setWeight(int newWeight)
	
	/**
	 * This function returns the weight of the Shipment
	 * @return Returns the weight of the Shipment
	 */
	public int getWeight()
	{
		return this.weight;
	}//End of getWeight()
	
	/**
	 * This function sets the history of the Shipment based on segments its visited
	 * @param hist This is an ArrayList of Segments that the shipment has/will visit and will build a history from
	 */
	public void setHistoryFromSegments(ArrayList<Segment> hist)
	{
		//Need some error checking
		history=new ArrayList<ShipmentHistory>();
		for(int i = 0; i<hist.size();i++)
		{
			ShipmentHistory temp = new ShipmentHistory();
			temp.setNodeNumber(i);
			temp.setSegmentID(hist.get(i).getID());
			temp.setShipmentID(this.id);
			temp.Update();
			history.add(temp);
		}
	}//End of setHistoryFromSegments(ArrayList<Segment> hist)
	
	/**
	 * This function will return the ShipmentHistory of the Shipment
	 * @return Returns an ArrayList of ShipmentHistory Objects for the Shipment
	 */
	public ArrayList<ShipmentHistory> getHistory()
	{
		return history;
	}//End of ArrayList<ShipmentHistory> getHistory()
	
	/**
	 * Thus function returns the start location of the Shipment
	 * @return Returns the start location object of the Shipment
	 */
	public Location loadStartLocation()
	{
		return Location.Load(fromLocationID);
	}//End of loadStartLocation()
	
	/**
	 * This function returns the end location of the Shipment
	 * @return Returns the end location object of the Shipment
	 */
	public Location loadEndLocation()
	{
		return Location.Load(toLocationID);
	}//End of loadEndLocation()
	
	/**
	 * This function loads a Shipment from the database based on the given id
	 * @param id This is the id of the Shipment to load from the database
	 * @return Returns the Shipment loaded from the database based on the given id
	 */
	public static Shipment Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Shipment where ShipmentID = " + id);
			if(temp.size()>0)
			{
				Shipment s = BuildFromDataRow(temp.get(0));
				s.setHistory(ShipmentHistory.LoadAllForShipment(id));
				return s;
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
	 * This function will return an ArrayList of Shipments from the database based on the given where clause
	 * @param where This is the clause that will determine which Shipments to load from the database
	 * @return Returns an ArrayList of Shipments loaded from the database based on the given where clause
	 */
	public static ArrayList<Shipment> LoadAll(String where)
	{
		ArrayList<Shipment> returnList = new ArrayList<Shipment>();
		try 
		{
			
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Shipment " +  where);
			for(int i = 0; i<temp.size();i++)
			{
				Shipment s =BuildFromDataRow(temp.get(i));
				s.setHistory(ShipmentHistory.LoadAllForShipment(s.getId()));
				returnList.add(s);
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
	 * This function will load all the Shipments at the given Segment
	 * @param segID This is the id of the Segment that will return all the Shipments on it
	 * @return Returns an ArrayList of Shipments on the given Segment
	 */
	public static ArrayList<Shipment> LoadAllForSegment(int segID)
	{
		ArrayList<Shipment> returnList = new ArrayList<Shipment>();
		try 
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Shipment where shipmentID in ( Select shipmentID from shipmentHistory where segmentID = " +  segID+")");
			for(int i = 0; i<temp.size();i++)
			{
				Shipment s =BuildFromDataRow(temp.get(i));
				s.setHistory(ShipmentHistory.LoadAllForShipment(s.getId()));
				returnList.add(s);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
		return returnList;
	}//End of LoadAllForSegment(int segID)
	
	/**
	 * This function will build a new Shipment object based on the passed in data
	 * @param data This is the data that will be used to build a new Shipment
	 * @return Returns a Shipment object built from the passed in data
	 * @throws SQLException
	 */
	public static Shipment BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		Shipment s = new Shipment((Integer)data.get("ShipmentID"));
		s.setFromLocationID((Integer)data.get("FromLocationID"));
		s.setToLocationID((Integer)data.get("ToLocationID"));
		s.setPriority((Integer)data.get("Priority"));
		s.setEarliestDepartureTime((Integer)data.get("EarliestDepartureFromStart"));
		s.setLatestDepartureTime((Integer)data.get("LatestDepartureFromStart"));
		s.setEarliestArrivalTime((Integer)data.get("EarliestArrival"));
		s.setLatestArrivalTime((Integer)data.get("LatestArrival"));
		s.setSize((Integer)data.get("Size"));
		if((Integer)data.get("weight")!=null)
			s.setWeight((Integer)data.get("weight"));
		if((Integer)data.get("CurrentLocation")!=null)
			s.setCurrentLocation((Integer)data.get("CurrentLocation"));
		if((Integer)data.get("loadingTime")!=null)
			s.setTimeToLoad((Integer)data.get("loadingTime"));
		if((Integer)data.get("unloadingTime")!=null)
			s.setTimeToUnload((Integer)data.get("unloadingTime"));
		if((Integer)data.get("shipper")!=null)
			s.setShipperID((Integer)data.get("shipper"));
		if((Boolean)data.get("takeTollRoads")){
			s.setTollRoadsTrue();
		}else{
			s.setTollRoadsFalse();
		}
		if((Boolean)data.get("localCongestionByPass")){
			s.setCongestionByPassTrue();
		}else{
			s.setCongestionByPassFalse();
		}
		s.setTrailerType((String)data.get("trailerType"));
		s.setLoadingType((String)data.get("loadingType"));
		s.setUnloadType((String)data.get("unloadingType"));
		s.setHazmat((String)data.get("hazmatConstraints"));
		s.setPrefCarrier((String)data.get("prefCarriers"));
		if((Integer)data.get("maxStops")!=null)
			s.setMaxStops((Integer)data.get("maxStops"));
		if((Boolean)data.get("Deleted"))
			s.MarkDeleted();
		else
			s.MarkUndeleted();
		s.MarkClean();
		return s;
		
	}//End of BuildFromDataRow(Map<String,Object> data)
	
	/**
	 * This function will update the Shipment on the database
	 */
	@Override
	boolean Update() {
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the plane is new insert it into the database by executing the following
				executeCommand("Insert into Shipment (FromLocationID,ToLocationID,Priority,EarliestDepartureFromStart,LatestDepartureFromStart,EarliestArrival,LatestArrival,Size,weight,CurrentLocation,loadingTime"+
				",unloadingTime,loadingRate,shipper,takeTollRoads,localCongestionByPass,trailerType,loadingType,unloadingType,hazmatConstraints,prefCarriers,maxStops) Values ('"+
						this.fromLocationID+ "','" + this.toLocationID + "','"+ this.priority+"','"+this.earliestDeparture+"','"+this.latestDeparture+"','" + this.earliestArrival + "','" + this.latestArrival+ "','"+
						this.size+"','"+this.weight+"','"+this.currentLocation+this.timeToLoad+"','"+this.timeToUnload+"','"+this.loadingRate+"','"+this.shipperID+"','"+this.tollRoads+"','"+this.congestionByPass+
						"','"+this.trailerType+"','"+this.loadingType+"','"+this.unloadingType+"','"+this.hazmatConstraints+"','"+this.prefCarriers+"','"+this.maxStops+"')");
				//Grab this plane from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select ShipmentID from Shipment where "+
						"FromLocationID = '" + this.fromLocationID +"' "+
						"AND ToLocationID = '" + this.toLocationID +"' "+
						"AND Priority = '" + this.priority +"' "+
						"AND EarliestDepartureFromStart = '" + this.earliestDeparture + "' "+
						"AND LatestDepartureFromStart = '" + this.latestDeparture +"' " +
						"AND EarliestArrival = '" + this.earliestArrival +"' "+
						"AND LatestArrival = '" + this.latestArrival +"' "+
						"AND Size = '" + this.size + "' "+
						"AND weight = '" + this.weight +"' "+
						"AND CurrentLocation = '" + this.currentLocation +"' "+
						"AND loadingTime = '"+ this.timeToLoad +"' "+
						"AND unloadingTime = '" + this.timeToUnload + "' "+
						"AND loadingRate = '" + this.loadingRate +"' "+
						"AND shipper = '" + this.shipperID +"' "+
						"AND takeTollRoads = '" + this.tollRoads +"' "+
						"AND localCongestionByPass = '" + this.congestionByPass +"' "+
						"AND trailerType = '" + this.trailerType +"' "+
						"AND loadingType = '" + this.loadingType+"' "+
						"AND unloadingType = '" + this.unloadingType+"' "+
						"AND hazmatConstraints = '" + this.hazmatConstraints + "' "+
						"AND prefCarriers = '" + this.prefCarriers +"' "+
						"AND maxStops = '"+this.maxStops+"'");
						
				//If this plane exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("ShipmentID");								//Set this Plane's id to the database id
					MarkClean();																//Mark the Plane as clean
					MarkOld();																	//Mark the Plane as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Plane is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Shipment Set "+
							"FromLocationID = '" + this.fromLocationID +"' "+
							", ToLocationID = '" + this.toLocationID +"' "+
							", Priority = '" + this.priority +"' "+
							", EarliestDepartureFromStart = '" + this.earliestDeparture + "' "+
							", LatestDepartureFromStart = '" + this.latestDeparture +"' " +
							", EarliestArrival = '" + this.earliestArrival +"' "+
							", LatestArrival = '" + this.latestArrival +"' "+
							", Size = '" + this.size + "' "+
							", weight = '" + this.weight +"' "+
							", CurrentLocation = '" + this.currentLocation +"' "+
							", loadingTime = '"+ this.timeToLoad +"' "+
							", unloadingTime = '" + this.timeToUnload + "' "+
							", loadingRate = '" + this.loadingRate +"' "+
							", shipper = '" + this.shipperID +"' "+
							", takeTollRoads = '" + this.tollRoads +"' "+
							", localCongestionByPass = '" + this.congestionByPass +"' "+
							", trailerType = '" + this.trailerType +"' "+
							", loadingType = '" + this.loadingType+"' "+
							", unloadingType = '" + this.unloadingType+"' "+
							", hazmatConstraints = '" + this.hazmatConstraints + "' "+
							", prefCarriers = '" + this.prefCarriers +"' "+
							", maxStops = '"+this.maxStops+"'"+
							", deleted = " + this.isDeleted() +
							" where ShipmentID = '" + this.id +"'");
							
					MarkClean();
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);													//Print out the error
			ex.printStackTrace();
			return false;
		}//End of catch block

	}//End of Update()

	/**
	 * This function will mark this shipment as deleted in the database
	 */
	@Override
	boolean Delete() {
		try
		{
			executeCommand("Update Shipment Set Deleted = true Where ShipmentID = " + this.id);	
			return true;
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);	
			return false;
		}

	}//End of Delete()
	
	/**
	 * This function will delete the entire history of the Shipment
	 */
	public void DeleteAllHistory()
	{
		for(int i=0;i<history.size();i++)
		{
			history.get(i).Delete();
		}
	}//End of DeleteAllHistory()

}//End of Shipment Class
