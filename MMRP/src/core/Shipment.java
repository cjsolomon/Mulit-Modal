package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

//Shipment class
//Lets try this again
//YEAH!!!! ITS WORKING!!!!!
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
	
	/**
	 * This is the default constructor for the Shipment object
	 */
	public Shipment()
	{
		MarkNew();
	}//End of the default Shipment constructor
	
	/**
	 * This is the argumented Shipment constructor
	 * @param id This is the id of the new Shipment
	 */
	public Shipment(int id)
	{
		this.id=id;
		MarkOld();
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
		if(this.priority != priority)
		{
			this.priority = priority;
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
		//NEEDS ERROR CHECKING
		if(this.size!=size)
		{
			this.size = size;
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
		//NEED ERROR CHECKING
		if(this.timeToLoad != t)
		{
			this.timeToLoad=t;
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
		//NEEDS ERROR CHECKING
		if(this.timeToUnload!=t)
		{
			this.timeToUnload=t;
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
		//NEED TO ADD ERROR CHECKING
		if(this.maxStops!=maxStop)
		{
			maxStops=maxStop;
			MarkDirty();
		}
	}//End of setMaxStops(int s)
	
	
	public int getMaxStops()
	{
		return maxStops;
	}
	
	public int getLoadingRate()
	{
		return this.loadingRate;
	}
	public void setLoadingRate(int r)
	{
		if(this.loadingRate!=r)
		{
			this.loadingRate=r;
			MarkDirty();
		}
	}
	
	public String getUnloadType()
	{
		return this.unloadingType;
	}
	public void setUnloadType(String t)
	{
		if(this.unloadingType==null||!unloadingType.equals(t))
		{
			this.unloadingType=t;
			MarkDirty();
		}
	}
	public void setLoadingType(String t)
	{
		if(this.loadingType==null || !this.loadingType.equals(t))
		{
			this.loadingType=t;
			MarkDirty();
		}
	}
	public void setTrailerType(String t)
	{
		if(this.trailerType==null || !this.trailerType.equals(t))
		{
			this.trailerType=t;
			MarkDirty();
		}
	}
	public String getTrailerType()
	{
		return this.trailerType;
	}
	
	public void setPrefCarrier(String s)
	{
		if(this.prefCarriers==null || !this.prefCarriers.equals(s))
		{
			this.prefCarriers=s;
			MarkDirty();
		}
	}
	public String getPrefCarriers()
	{
		return this.prefCarriers;
	}
	public void setHistory(ArrayList<ShipmentHistory> hist)
	{
		history=hist;
		for(int i = 0; i<hist.size();i++)
		{
			
		}
	}
	public void setWeight(int w)
	{
		if(this.weight!=w)
		{
			this.weight=w;
			MarkDirty();
		}
	}
	public int getWeight()
	{
		return this.weight;
	}
	public void setHistoryFromSegments(ArrayList<Segment> hist)
	{
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
	}
	public ArrayList<ShipmentHistory> getHistory()
	{
		return history;
	}
	public Location loadStartLocation()
	{
		return Location.Load(fromLocationID);
	}
	public Location loadEndLocation()
	{
		return Location.Load(toLocationID);
	}
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
		}
 		return null;
	}
	
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
		}
		return returnList;
	}
	
	public static ArrayList<Shipment> LoadAllForSegment(int segID)
	{
		ArrayList<Shipment> returnList = new ArrayList<Shipment>();
		try 
		{
			/*
			 * SELECT * FROM `multi-modal`.shipment where shipmentID in
(
	Select shipmentID from `multi-modal`.shipmentHistory where segmentID=104

);
			 */
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
		}
		return returnList;
	}
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
		s.setTollRoads((Boolean)data.get("takeTollRoads"));
		s.setCongestionByPass((Boolean)data.get("localCongestionByPass"));
		s.setTrailerType((String)data.get("trailerType"));
		s.setLoadingType((String)data.get("loadingType"));
		s.setUnloadType((String)data.get("unloadingType"));
		s.setHazmat((String)data.get("hazmatConstraints"));
		s.setPrefCarrier((String)data.get("prefCarriers"));
		if((Integer)data.get("maxStops")!=null)
			s.setMaxStops((Integer)data.get("maxStops"));
		s.MarkClean();
		return s;
		
	}
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
							" where ShipmentID = '" + this.id +"'");
							
					MarkClean();
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);													//Print out the error
			return false;
		}//End of catch block

	}

	@Override
	boolean Delete() {
		try
		{
			executeCommand("Delete from Shipment Where ShipmentID = " + this.id);					//Delete the plane
			return true;
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);	
			return false;
		}

	}
	public void DeleteAllHistory()
	{
		for(int i=0;i<history.size();i++)
		{
			history.get(i).Delete();
		}
	}

}
