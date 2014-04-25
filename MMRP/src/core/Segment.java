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

	private int id; // The Segment id
	private int toID; // The endpoint id
	private int fromID; // The startpoint id
	private Vehicle vehicle; // The vehicle on this segment
	private String mode; // The mode of transport
	private double distance; // The distance between the start and endpoints
	private int departureTime; // The estimated time the vehicle departs
	private int arrivalTime; // The estimated time the vehicle arrives
	private int earliestArrivalTime; // This is the earliest arrival time
	private int latestArrivalTime; // This is the latest arrival time
	private int earliestDepartureTime; // This is the earliest departure time
	private int latestDepartureTime; // This is the latest departure time
	private ShippingRate shippingRate; // This is the shipping rate over the
	// Segment
	private TravelType travelType; // This is the travelType used over this
	// Segment
	private String lanes;
	private ArrayList<Shipment> onBoard;
	private Location start;
	private Location end;
	private double actualCapacity;

	// Default Variables
	private static final double MIN_DISTANCE = 0;
	private static final double MIN_CAPACITY = 0;
	private static final int DEFAULT_ARRIVAL_TIME = 50;
	private static final int DEFAULT_DEPARTURE_TIME = 0;
	private static final double DEFAULT_DISTANCE = 100;
	private static final int DEFAULT_EARLIEST_ARRIVAL_TIME = 50;
	private static final int DEFAULT_EARLIEST_DEPARTURE_TIME = 0;
	private static final int DEFAULT_START_LOCATION_ID = 1;
	private static final String DEFAULT_LANES = "defaultLanes";
	private static final int DEFAULT_LATEST_ARRIVAL_TIME = 50;
	private static final int DEFAULT_LATEST_DEPARTURE_TIME = 1000;
	private static final String DEFAULT_MODE = "TRUCK";
	private static final int DEFAULT_END_LOCATION_ID = 2;

	/**
	 * This is the default constructor for the Segment
	 */
	public Segment() {
		this.arrivalTime = DEFAULT_ARRIVAL_TIME;
		this.departureTime = DEFAULT_DEPARTURE_TIME;
		this.distance = DEFAULT_DISTANCE;
		this.earliestArrivalTime = DEFAULT_EARLIEST_ARRIVAL_TIME;
		this.earliestDepartureTime = DEFAULT_EARLIEST_DEPARTURE_TIME;
		this.fromID = DEFAULT_START_LOCATION_ID;
		this.lanes = DEFAULT_LANES;
		this.latestArrivalTime = DEFAULT_LATEST_ARRIVAL_TIME;
		this.latestDepartureTime = DEFAULT_LATEST_DEPARTURE_TIME;
		this.mode = DEFAULT_MODE;
		this.shippingRate = new ShippingRate();
		this.toID = DEFAULT_END_LOCATION_ID;
		this.travelType = new TravelType();
		this.vehicle = new Truck();
		this.onBoard = new ArrayList<Shipment>();
		this.actualCapacity = 0;
		MarkNew(); // Mark this Segment as new
		MarkClean();
		MarkUndeleted();
	}// End of the default Segment constructor

	/**
	 * This is the argumented Segment constructor that takes an id value
	 * @param id This is the new id for the Segment
	 */
	public Segment(int id) {
		this.id = id; // Set the Segment id
		this.arrivalTime = DEFAULT_ARRIVAL_TIME;
		this.departureTime = DEFAULT_DEPARTURE_TIME;
		this.distance = DEFAULT_DISTANCE;
		this.earliestArrivalTime = DEFAULT_EARLIEST_ARRIVAL_TIME;
		this.earliestDepartureTime = DEFAULT_EARLIEST_DEPARTURE_TIME;
		this.fromID = DEFAULT_START_LOCATION_ID;
		this.lanes = DEFAULT_LANES;
		this.latestArrivalTime = DEFAULT_LATEST_ARRIVAL_TIME;
		this.latestDepartureTime = DEFAULT_LATEST_DEPARTURE_TIME;
		this.mode = DEFAULT_MODE;
		this.shippingRate = new ShippingRate();
		this.toID = DEFAULT_END_LOCATION_ID;
		this.travelType = new TravelType();
		this.vehicle = new Truck();
		this.onBoard = new ArrayList<Shipment>();
		this.actualCapacity = 0;
		MarkClean();
	}// End of the arguemented Segment constructor

	/**
	 * This function will return the Segment id
	 * @return Returns the ID of the Segment
	 */
	public int getID() {
		return id; // Return the id
	}// End of getID()

	/**
	 * This function will set the Segment's distance
	 * @param newDistance This is the Segments new distance
	 */
	public void setDistance(double newDistance) {
		if (!FormatChecker.checkLowerBound(newDistance, MIN_DISTANCE)) {
			/*
			 * Log.writeLogSevere("The new distance for segment " + this.getID()
			 * + " was set below zero. The new " +
			 * "distance will be now set to zero.");
			 */
			newDistance = MIN_DISTANCE;
		}
		else{
			if (this.distance != newDistance) {
				distance = newDistance; // Set the distance
				MarkDirty(); // Mark this Segment as dirty
			}// End of update distance if
		}

	}// End of setDistance(double d)

	public void setActualCapacity(double newActualCapacity){
		if(FormatChecker.checkLowerBound(newActualCapacity, MIN_CAPACITY))
		{
			this.actualCapacity = newActualCapacity;
			this.MarkDirty();
		}
		else
		{
			/*Log.writeLogWarning("Attempt to set actual capacity below minimum in segment. "
					+ "Setting actual capacity to " + MIN_CAPACITY);*/
			this.actualCapacity = MIN_CAPACITY;
		}
	}

	public double getActualCapacity(){
		return this.actualCapacity;
	}

	public ArrayList<Shipment> getOnBoard() {
		return this.onBoard;
	}


	/**
	 * This function returns the distance along the Segment
	 * @return Returns the distance between the start location and the end
	 * location on the Segment
	 */
	public double getDistance() {
		return this.distance; // Return the distance
	}// End of getDistance()

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
		this.MarkDirty();
	}

	/**
	 * This function sets the estimated arrival time of the vehicle on this
	 * Segment
	 * @param newArrivalTime This is the new arrival time for the vehicle at the end
	 * location
	 */
	public void setEstimatedArrivalTime(int newArrivalTime) {
		if(FormatChecker.inRange(newArrivalTime, earliestArrivalTime, latestArrivalTime))
		{
			this.arrivalTime = newArrivalTime; // Set the arrival time
			MarkDirty(); // Mark this Segment as dirty
		}
		else 
		{
			if(!FormatChecker.checkLowerBound(newArrivalTime, earliestArrivalTime))
			{
				/*Log.writeLogWarning("Invalid entry for estimated arrival time in segment. Attempted to "
						+ "set estimated arrival time before earliest arrival time. Setting estimated"
					+ "arrival time to earliest arrival time: " + earliestArrivalTime);*/
				this.arrivalTime = earliestArrivalTime;
			}
			else
			{
				/*Log.writeLogWarning("Invalid entry for estimated arrival time in segment. Attempted to "
					+ "set estimated arrival time after latest arrival time. Setting estimated"
					+ "arrival time to latest arrival time: " + latestArrivalTime);*/
				this.arrivalTime = latestArrivalTime;
			}
		}
	}// End of setEstimatedArrivalTime(int newArrivalTime)

	/**
	 * This function returns the estimated arrival time of the vehicle traveling
	 * over this Segment
	 * @return Returns the estimated arrival time of the vehicle traveling over
	 * this Segment
	 */
	public int getEstimatedArrivalTime() {
		return this.arrivalTime; // Return the arrivalTime
	}// End of getEstimatedArrivalTime()

	/**
	 * This function sets the estimated departure time of the vehicle on this
	 * Segment
	 * @param newDepartureTime This is the new estimated departure time for the vehicle on
	 * this Segment
	 */
	public void setEstimatedDepartureTime(int newDepartureTime) {
		if (this.departureTime != newDepartureTime) {
			if(FormatChecker.inRange(newDepartureTime, this.earliestDepartureTime, this.latestDepartureTime))
			{
				this.departureTime = newDepartureTime; // Set the departureTime
				MarkDirty(); // Mark this Segment as dirty
			}
			else 
			{
				if(!FormatChecker.checkLowerBound(newDepartureTime, this.earliestDepartureTime))
				{
					/*Log.writeLogWarning("Invalid entry for estimated departure time in segment. Attempted to "
							+ "set estimated departure time before earliest departure time. Setting estimated"
						+ "departure time to earliest departure time: " + earliestDepatureTime);*/
					this.departureTime = earliestDepartureTime;
				}
				else
				{
					/*Log.writeLogWarning("Invalid entry for estimated departure time in segment. Attempted to "
						+ "set estimated departure time after latest departure timing. Setting estimated"
						+ "departure time to latest departure time: " + latestDepatureTime);*/
					this.departureTime = latestDepartureTime;
				}
			}
		}// End of update if
	}// End of setEstimatedDepartureTime(int newDepartureTime)

	/**
	 * This function returns the estimated departure time of the vehicle from
	 * the start location
	 * @return Returns the estimated departure time for the vehicle from the
	 * start location
	 */
	public int getEstimatedDepartureTime() {
		return this.departureTime; // Return departureTime
	}// End of getEstimatedDepartureTime()

	/**
	 * This function returns the earliest arrival time of the vehicle at the end
	 * location
	 * @return Returns the earliest arrival time of the vehicle at the end
	 * location
	 */
	public int getEarliestArrivalTime() {
		return earliestArrivalTime;
	}// End of getEarliestArrivalTime()

	/**
	 * This function sets the earliest arrival time of the vehicle at the end
	 * location
	 * @param earliestArrivalTime This is the new earliest arrival time of the vehicle at the
	 * end location
	 */
	public void setEarliestArrivalTime(int earliestArrivalTime) {
		if(FormatChecker.checkUpperBound(earliestArrivalTime, this.latestArrivalTime))
		{
			this.earliestArrivalTime = earliestArrivalTime;
			this.MarkDirty();
		}
		else 
		{
			/*
			Log.writeLogWarning("Invalid entry for earliest arrival time in segment. Setting earliest"
				+ "arrival time to " + this.latestArrivalTime);*/
			this.earliestArrivalTime = this.latestArrivalTime;
		}
	}// End of setEarliestArrivalTime(int earliestArrivalTime)

	/**
	 * This function returns the latest arrival time of the vehicle at the end
	 * location
	 * @return Returns the latest arrival time of the vehicle at the end
	 * location
	 */
	public int getLatestArrivalTime() {
		return latestArrivalTime;
	}// End of getLatestArrivalTime()

	/**
	 * This function sets the latest arrival time of the vehicle at the end
	 * location
	 * @param latestArrivalTime This is the new latest arrival time of the vehicle at the end
	 * location
	 */
	public void setLatestArrivalTime(int latestArrivalTime) {
		if(FormatChecker.checkLowerBound(latestArrivalTime, this.earliestArrivalTime))
		{
			this.latestArrivalTime = latestArrivalTime;
			this.MarkDirty();
		}
		else 
		{
			/*
			Log.writeLogWarning("Invalid entry for latest arrival time in segment. Setting latest"
				+ "arrival time to " + this.earliestArrivalTime); */
			this.latestArrivalTime = this.earliestArrivalTime;
		}
	}// End of setLatestArrivalTime(int latestArrivalTime)

	/**
	 * This function returns the earliest departure time of the vehicle from the
	 * start location 
	 * @return Returns the earliest departure time of the vehicle from the start
	 * location
	 */
	public int getEarliestDepartureTime() {
		return earliestDepartureTime;
	}// End of getEarliestDepartureTime()

	/**
	 * This function sets the earliest departure time for the vehicle from the
	 * start location
	 * @param earliestDepartureTime This is the new earliest departure time for the vehicle from
	 * the start location
	 */
	public void setEarliestDepartureTime(int earliestDepartureTime) {
		if(FormatChecker.checkUpperBound(earliestDepartureTime, this.latestDepartureTime))
		{
			this.earliestDepartureTime = earliestDepartureTime;
			this.MarkDirty();
		}
		else 
		{
			/*
			Log.writeLogWarning("Invalid entry for earliest departure time in segment. Setting earliest"
				+ "departure time to " + this.latestDepartureTime); */
			this.earliestDepartureTime = this.latestDepartureTime;
		}
	}// End of setEarliestDepartureTime(int earliestDepartureTime)

	/**
	 * This function returns the latest departure time for the vehicle from the
	 * start location
	 * @return Returns the latest departure time for the vehicle from the start
	 * location
	 */
	public int getLatestDepartureTime() {
		return latestDepartureTime;
	}// End of getLatestDepartureTime()

	/**
	 * This function sets the latest departure time for the vehicle from the
	 * start location
	 * @param latestDepartureTime This is the new latest departure time for the vehicle from the
	 * start location
	 */
	public void setLatestDepartureTime(int latestDepartureTime) {
		if(FormatChecker.checkLowerBound(latestDepartureTime, this.earliestDepartureTime))
		{
			this.latestDepartureTime = latestDepartureTime;
			this.MarkDirty();
		}
		else 
		{
			/*
			Log.writeLogWarning("Invalid entry for latest departure time in segment. Setting latest"
				+ "departure time to " + this.earliestDepartureTime); */
			this.latestDepartureTime = this.earliestDepartureTime;
		}
	}// End of setLatestDepartureTime(int latestDepartureTime)

	/**
	 * This function sets the starting location of this Segment
	 * @param startLocationID This is the ID of the new start location of the Segment
	 */
	public void setStartLocation(int startLocationID) {
		if (fromID != startLocationID) {
			fromID = startLocationID; // Set the fromID
			MarkDirty(); // Mark this Segment as dirty
		}// End of update if
	}// End of setStartLocation(int startLocationID)

	/**
	 * This function sets the starting location of this Segment using a Location
	 * object
	 * @param start This is the new starting location for the Segment
	 */
	public void setStartLocation(Location start) {
		if(this.start==null)this.start=start;
		if (fromID != start.getID()) {
			this.start = start;
			fromID = start.getID(); // Set the fromID
			MarkDirty(); // Mark the Segment as dirty
		}// End of update if
	}// End of setStartLocation(Location start)

	/**
	 * This function returns the starting location id
	 * @return Returns the ID of the start location
	 */
	public int getStartLocationID() {
		return fromID; // Return the fromID
	}// End of getStartLocationID()

	/**
	 * This function returns the Location object associated with the fromID
	 * @return Returns the start location of this Segment
	 */
	public Location getStartLocation() {
		if (start == null)
			return Location.Load(fromID); // Return the Location of this
		// Segments start
		return start;
	}// End of getStartLocation()

	/**
	 * This function sets the end location of the Segment
	 * @param endLocationID This is the ID of the new end location for the Segment
	 */
	public void setEndLocation(int endLocationID) {
		if (toID != endLocationID) {
			toID = endLocationID; // Set the end point id
			MarkDirty(); // Mark the Segment as dirty
		}// End of update if
	}// End of setEndLocation(int endLocationID)

	/**
	 * This function sets the endpoint of Segment using a Location object
	 * @param end This is the new ending location of the Segment
	 */
	public void setEndLocation(Location end) {
		if(this.end==null)this.end=end;
		if (toID != end.getID()) {
			this.end = end;
			toID = end.getID(); // Set the toID
			MarkDirty(); // Mark the Segment as dirty
		}// End of update if
	}// End of setEndLocation(Location end)

	/**
	 * This function returns the end location of the Segment
	 * @return Returns the ID of the end location
	 */
	public int getEndLocationID() {
		return toID; // Return toID
	}// End of getEndLocationID()

	/**
	 * This function returns the end location of the Segment as a Location
	 * object
	 * @return Returns the end location of the Segment
	 */
	public Location getEndLocation() {
		if (end == null)
			return Location.Load(toID); // Return the end point Location
		return end;
	}// End of getEndLocation()

	/**
	 * This function sets the Segment's Vehicle
	 * @param vehicle This is the vehicle traveling over the Segment
	 */
	public void setVehicle(Vehicle vehicle) {
		if (this.vehicle != vehicle || this.mode == null
				|| !this.mode.equals(vehicle.getTravelMode())) {
			this.vehicle = vehicle; // Set the vehicle
			mode = vehicle.getTravelMode(); // Set the travel type
			MarkDirty(); // Mark the Segment as dirty
		}// End of valid vehicle if
	}// End of setVehicle(Vehicle vehicle)

	/**
	 * This function returns the Vehicle object on this Segment
	 * @return Returns the vehicle object on this Segment
	 */
	public Vehicle getVehicle() {
		// Load the Vehicle from the database based on the type of the Vehicle
		if (vehicle == null) {
			switch (Vehicle.loadMode(mode)) {
			case TRUCK:
				return Truck.Load(this.vehicle.getId()); // Return Truck
			case RAIL:
				return Rail.Load(this.vehicle.getId()); // Return Rail
			case CARGO:
				return Cargo.Load(this.vehicle.getId()); // Return Cargo
			case PLANE:
				return Plane.Load(this.vehicle.getId()); // Return Plane
			case BIKE:
				return Bike.Load(this.vehicle.getId()); // Return Bike
			case NONE:
				return null;
			case ALL:
				return null;
			}//end switch
			return null;
		}// End of if

		else {
			return this.vehicle;
		}
	}// End of getVehicle()

	/**
	 * This function sets the vehicle traveling over the Segment based on an ID
	 * from the database and the TravelMode of the vehicle
	 * @param id This is the ID of the vehicle to load from the database
	 * @param mode This is the mode of travel of the vehicle
	 */
	public void setVehicle(int id, Vehicle.TravelModes mode) {
		// Load the Vehicle from the database based on the type of the Vehicle
		switch (mode) {
		case TRUCK:
			this.vehicle = Truck.Load(id); // Return Truck
			break;
		case RAIL:
			this.vehicle = Rail.Load(id); // Return Rail
			break;
		case CARGO:
			this.vehicle = Cargo.Load(id); // Return Cargo
			break;
		case PLANE:
			this.vehicle = Plane.Load(id); // Return Plane
			break;
		case BIKE:
			this.vehicle = Bike.Load(id); // Return Bike
			break;
		case NONE:
			break;
		case ALL:
			break;

		}// End of switch
	}// End of setVehicle(int id, Vehicle.TravelModes mode)

	/**
	 * This function sets the lane of travel on the Segment
	 * @param newLane This is the new lane of travel for the Segment
	 */
	public void setLane(String newLane) {
		if (lanes == null || !lanes.equals(newLane)) {
			lanes = newLane;
			MarkDirty();
		}
	}// End of setLane(String newLane)

	/**
	 * This function returns the lane of travel along this Segment
	 * @return Returns the lane of travel on this Segment
	 */
	public String getLane() {
		return lanes;
	}// End of getLane()

	/**
	 * This function returns the TravelType of the vehicle over this Segment
	 * @return Returns the TravelType of the vehicle over the Segment
	 */
	public TravelType getTravelType() {
		return travelType;
	}// End of getTravelType()

	/**
	 * This function sets the TravelType for the vehicle over the Segment
	 * @param travelType This is the new TravelType of the vehicle over the Segment
	 */
	public void setTravelType(TravelType travelType) {

		// NEED SOME ERROR CHECKING HERE TO MAKE SURE IT IS A VALID TRAVEL TYPE
		// FOR THIS VEHICLE

		this.travelType = travelType;
		this.MarkDirty();
	}// End of setTravelType(TravelType travelType)

	public void setMode(String mode) {
		if(FormatChecker.isEnumerated(Vehicle.TravelModes.class, mode))
		{
			this.mode = mode;
			this.MarkDirty();
		}
		else
		{
			this.setMode("NONE");
			Log.writeLogWarning("Invalid travel mode in Vehicle. Not a valid mode. Setting mode to "
					+ " NONE.");
		}

	}

	public String getMode() {
		return mode;
	}

	/**
	 * This function returns all the Segments specified in the given where
	 * clause
	 * @param where This is the where clause specifying the Segments to load from
	 * the database
	 * @return Returns an ArrayList of the Segments specified in the where
	 * clause
	 */
	public static ArrayList<Segment> LoadAll(String where) {
		// System.out.println("Loading all the segments from the where clause");
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try {
			// Create and populate an ArrayList of Segments
			ArrayList<Map<String, Object>> temp;
			if(where.isEmpty())
				temp = executeQuery("SELECT SegmentID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID,EarliestArrivalTime,LatestArrivalTime,EarliestDepartureTime,LatestDepartureTime,s.Deleted,l.LocationID as StartID,"
						+ "l.Latitude as StartLat, l.Longitude as StartLon,l.Name as StartName, l.State as StartState, l.Country as StartCountry, l.TravelType1 as StartType1, l.TravelType2 as StartType2, l.TravelType3 as StartType3, l.TravelType4 as StartType4, l.TravelType5 as StartType5, l.TravelType6 as StartType6,e.LocationID as EndID,"
						+ "e.Latitude as EndLat, e.Longitude as EndLon,e.Name as EndName, e.State as EndState, e.Country as EndCountry, e.TravelType1 as EndType1, e.TravelType2 as EndType2, e.TravelType3 as EndType3, e.TravelType4 as EndType4, e.TravelType5 as EndType5, e.TravelType6 as EndType6 "
						+ "FROM segment s left outer join location l on s.FromLocationID=l.LocationID left outer join location e on s.ToLocationID = e.LocationID  where s.Deleted = false");
			else
				temp = executeQuery("SELECT SegmentID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID,EarliestArrivalTime,LatestArrivalTime,EarliestDepartureTime,LatestDepartureTime,s.Deleted,l.LocationID as StartID,"
						+ "l.Latitude as StartLat, l.Longitude as StartLon,l.Name as StartName, l.State as StartState, l.Country as StartCountry, l.TravelType1 as StartType1, l.TravelType2 as StartType2, l.TravelType3 as StartType3, l.TravelType4 as StartType4, l.TravelType5 as StartType5, l.TravelType6 as StartType6,e.LocationID as EndID,"
						+ "e.Latitude as EndLat, e.Longitude as EndLon,e.Name as EndName, e.State as EndState, e.Country as EndCountry, e.TravelType1 as EndType1, e.TravelType2 as EndType2, e.TravelType3 as EndType3, e.TravelType4 as EndType4, e.TravelType5 as EndType5, e.TravelType6 as EndType6 "
						+ "FROM segment s left outer join location l on s.FromLocationID=l.LocationID left outer join location e on s.ToLocationID = e.LocationID " + where  + " AND s.Deleted = false");
			// For each of the entries in our list create a Segment object
			for (int i = 0; i < temp.size(); i++) {
				Segment s = BuildFromDataRow(temp.get(i));
				// s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}

		}// End of try block
		catch (Exception ex) {
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}// End of catch block
		return returnList;
	}// End of LoadAll(String where)

	/**
	 * This function builds objects from returned data from SQL queries against
	 * our database
	 * @param data This is the data that will be used to build the Segment object
	 * @return Returns a Segment object built from the passed in data
	 * @throws SQLException
	 */
	public static Segment BuildFromDataRow(Map<String, Object> data)
			throws SQLException {
		/*
		 * SegmentID VehicleID ModeType Distance TimeOfDeparture TimeOfArrival
		 * Lane ShippingRateID EarliestArrivalTime LatestArrivalTime
		 * EarliestDepartureTime LatestDepartureTime l.LocationID as StartID,"
		 * l.Latitude as StartLat l.Longitude as StartLon l.TravelType1 as
		 * StartType1 l.TravelType2 as StartType2 l.TravelType3 as StartType3
		 * l.TravelType4 as StartType4 l.TravelType5 as StartType5 l.TravelType6
		 * as StartType6 e.LocationID as EndID e.Latitude as EndLat e.Longitude
		 * as EndLon e.Name as EndName, e.State as EndState, e.Country as
		 * EndCountry e.TravelType1 as EndType1 e.TravelType2 as EndType2
		 * e.TravelType3 as EndType3 e.TravelType4 as EndType4 e.TravelType5 as
		 * EndType5 e.TravelType6 as EndType6"
		 */
		// This code grabs each element that will be found in the database on
		// the Segment table and set the appropriate values for a new Segment
		Segment s = new Segment((Integer) data.get("SegmentID"));
		s.setStartLocation((Integer) data.get("StartID"));
		s.setEndLocation((Integer) data.get("EndID"));
		Vehicle.TravelModes newMode = Vehicle.TravelModes.valueOf((String) data
				.get("ModeType"));
		s.setMode((String) data.get("ModeType"));
		s.setVehicle((Integer) data.get("VehicleID"), newMode);
		s.setDistance(Double.parseDouble(data.get("Distance").toString()));
		s.setEstimatedDepartureTime((Integer) data.get("TimeOfDeparture"));
		s.setEstimatedArrivalTime((Integer) data.get("TimeOfArrival"));
		s.setEarliestArrivalTime((Integer) data.get("EarliestArrivalTime"));
		s.setEarliestDepartureTime((Integer) data.get("EarliestDepartureTime"));
		s.setLatestArrivalTime((Integer) data.get("LatestArrivalTime"));
		s.setLatestDepartureTime((Integer) data.get("LatestDepartureTime"));
		s.setLane((String) data.get("Lane"));
		if(Boolean.getBoolean(data.get("Deleted").toString()))
			s.MarkDeleted();
		else
			s.MarkUndeleted();
		// s.setShippingRate(ShippingRate.Load((Integer)data.get("ShippingRateID")));

		Location tstart = new Location((Integer) data.get("StartID"));

		tstart.setLatitude(Double.parseDouble(data.get("StartLat").toString()));
		tstart.setLongitude(Double.parseDouble(data.get("StartLon").toString()));
		tstart.setName((String) data.get("StartName"));// rs.getString("Name"));
		tstart.setCountry((String) data.get("StartCountry"));
		tstart.setState((String) data.get("StartState"));
		tstart.addTravelMode(Vehicle.loadMode((String) data.get("StartType1")));// rs.getString("TravelType1")));
		if ((String) data.get("StartType2") != null) {
			tstart.addTravelMode(Vehicle.loadMode((String) data
					.get("StartType1")));
			if ((String) data.get("StartType3") != null) {
				tstart.addTravelMode(Vehicle.loadMode((String) data
						.get("StartType3")));
				if ((String) data.get("StartType4") != null) {
					tstart.addTravelMode(Vehicle.loadMode((String) data
							.get("StartType4")));
				}
				if ((String) data.get("StartType5") != null) {
					tstart.addTravelMode(Vehicle.loadMode((String) data
							.get("StartType5")));
					if ((String) data.get("StartType6") != null) {
						tstart.addTravelMode(Vehicle.loadMode((String) data
								.get("StartType6")));
					}
				}
			}
		}
		tstart.MarkClean();
		s.setStartLocation(tstart);

		Location tend = new Location((Integer) data.get("EndID"));

		tend.setLatitude(Double.parseDouble(data.get("EndLat").toString()));
		tend.setLongitude(Double.parseDouble(data.get("EndLon").toString()));
		tend.setName((String) data.get("EndName"));// rs.getString("Name"));
		tend.setCountry((String) data.get("EndCountry"));
		tend.setState((String) data.get("EndState"));
		tend.addTravelMode(Vehicle.loadMode((String) data.get("EndType1")));// rs.getString("TravelType1")));
		if ((String) data.get("EndType2") != null) {
			tend.addTravelMode(Vehicle.loadMode((String) data.get("EndType1")));
			if ((String) data.get("EndType3") != null) {
				tend.addTravelMode(Vehicle.loadMode((String) data
						.get("EndType3")));
				if ((String) data.get("EndType4") != null) {
					tend.addTravelMode(Vehicle.loadMode((String) data
							.get("EndType4")));
				}
				if ((String) data.get("EndType5") != null) {
					tend.addTravelMode(Vehicle.loadMode((String) data
							.get("EndType5")));
					if ((String) data.get("EndType6") != null) {
						tend.addTravelMode(Vehicle.loadMode((String) data
								.get("EndType6")));
					}
				}
			}
		}
		tend.MarkClean();
		s.setEndLocation(tend);

		s.MarkClean(); // Mark the Segment as clean
		return s;
	}// End of BuildFromDataRow(Map<String,Object> data)

	/**
	 * This function returns an ArrayList of all the Segments that begin at the
	 * given Location
	 * @param start This is the start location of all the Segments to be loaded
	 * @return Returns an ArrayList of all the Segments that start at the given
	 * location
	 */
	public static ArrayList<Segment> LoadAllAtLocation(Location start) {
		// Create an empty list of Segments
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try {
			// Populate the list with Segments starting at the given Location
			ArrayList<Map<String, Object>> temp = executeQuery("SELECT SegmentID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID,EarliestArrivalTime,LatestArrivalTime,EarliestDepartureTime,LatestDepartureTime,s.Deleted,l.LocationID as StartID,"
					+ "l.Latitude as StartLat, l.Longitude as StartLon,l.Name as StartName, l.State as StartState, l.Country as StartCountry, l.TravelType1 as StartType1, l.TravelType2 as StartType2, l.TravelType3 as StartType3, l.TravelType4 as StartType4, l.TravelType5 as StartType5, l.TravelType6 as StartType6,e.LocationID as EndID,"
					+ "e.Latitude as EndLat, e.Longitude as EndLon,e.Name as EndName, e.State as EndState, e.Country as EndCountry, e.TravelType1 as EndType1, e.TravelType2 as EndType2, e.TravelType3 as EndType3, e.TravelType4 as EndType4, e.TravelType5 as EndType5, e.TravelType6 as EndType6 "
					+ "FROM segment s left outer join location l on s.FromLocationID=l.LocationID left outer join location e on s.ToLocationID = e.LocationID where FromLocationID = '"
					+ start.getID() + "'"  + " AND l.Deleted = false AND e.Deleted=false");
			// For each returned Segment create a new Segment object
			for (int i = 0; i < temp.size(); i++) {
				Segment s = BuildFromDataRow(temp.get(i));
				// s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
		}// End of try block
		catch (Exception ex) {
			System.out.println("Error " + ex); // Print out the error
			ex.printStackTrace();
		}// End of catch block
		return returnList;
	}// End of LoadAllAtLocation(Location start)

	/**
	 * This function returns an ArrayList of Segments of all the Segments that
	 * start at the given Location and leave after the given start time
	 * @param start This is the starting location of the Segments to load
	 * @param startTime This is the earliest estimated time that the vehicle on this
	 * Segment can leave
	 * @return Returns an ArrayList of the Segments that start at the given
	 * location and leave after the given time
	 */
	public static ArrayList<Segment> LoadAllAtLocation(Location start,
			int startTime) {
		// Create a new list of Segments
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try {
			// Populate a list with all the Segments that match the criteria
			ArrayList<Map<String, Object>> temp = executeQuery("SELECT SegmentID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID,EarliestArrivalTime,LatestArrivalTime,EarliestDepartureTime,LatestDepartureTime,s.Deleted,l.LocationID as StartID,"
					+ "l.Latitude as StartLat, l.Longitude as StartLon,l.Name as StartName, l.State as StartState, l.Country as StartCountry, l.TravelType1 as StartType1, l.TravelType2 as StartType2, l.TravelType3 as StartType3, l.TravelType4 as StartType4, l.TravelType5 as StartType5, l.TravelType6 as StartType6,e.LocationID as EndID,"
					+ "e.Latitude as EndLat, e.Longitude as EndLon,e.Name as EndName, e.State as EndState, e.Country as EndCountry, e.TravelType1 as EndType1, e.TravelType2 as EndType2, e.TravelType3 as EndType3, e.TravelType4 as EndType4, e.TravelType5 as EndType5, e.TravelType6 as EndType6 "
					+ "FROM segment s left outer join location l on s.FromLocationID=l.LocationID left outer join location e on s.ToLocationID = e.LocationID where FromLocationID = '"
					+ start.getID() + "' and TimeOfDeparture > " + startTime  + " AND l.Deleted = false AND e.Deleted=false");
			// For each returned Segment create a new Segment object on our list
			for (int i = 0; i < temp.size(); i++) {
				Segment s = BuildFromDataRow(temp.get(i));
				// s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
		}// End of try block
		catch (Exception ex) {
			System.out.println("Error " + ex); // Print out the error
			ex.printStackTrace();
		}// End of catch block
		return returnList;
	}// End of LoadAllAtLocation(Location start, int startTime)

	/**
	 * This function returns an ArrayList of all the Segments that begin at the
	 * given LocationID
	 * @param startLocationID This is the start location ID of all the Segments to be loaded
	 * @return Returns an ArrayList of all the Segments that start at the given
	 * locationID
	 */
	public static ArrayList<Segment> LoadAllAtLocation(int startLocationID) {
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try {
			ArrayList<Map<String, Object>> temp = executeQuery("SELECT SegmentID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID,EarliestArrivalTime,LatestArrivalTime,EarliestDepartureTime,LatestDepartureTime,s.Deleted,l.LocationID as StartID,"
					+ "l.Latitude as StartLat, l.Longitude as StartLon,l.Name as StartName, l.State as StartState, l.Country as StartCountry, l.TravelType1 as StartType1, l.TravelType2 as StartType2, l.TravelType3 as StartType3, l.TravelType4 as StartType4, l.TravelType5 as StartType5, l.TravelType6 as StartType6,e.LocationID as EndID,"
					+ "e.Latitude as EndLat, e.Longitude as EndLon,e.Name as EndName, e.State as EndState, e.Country as EndCountry, e.TravelType1 as EndType1, e.TravelType2 as EndType2, e.TravelType3 as EndType3, e.TravelType4 as EndType4, e.TravelType5 as EndType5, e.TravelType6 as EndType6 "
					+ "FROM segment s left outer join location l on s.FromLocationID=l.LocationID left outer join location e on s.ToLocationID = e.LocationID where FromLocationID = '"
					+ startLocationID + "'"  + " AND l.Deleted = false AND e.Deleted=false");
			for (int i = 0; i < temp.size(); i++) {
				Segment s = BuildFromDataRow(temp.get(i));
				// s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
		} catch (Exception ex) {
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
		return returnList;
	}// End of LoadAllAtLocation(int startLocationID)

	/**
	 * This function returns an ArrayList of Segments of all the Segments that
	 * start at the given Location id and leave after the given start time
	 * @param locationID This is the starting location id of the Segments to load
	 * @param startTime This is the earliest estimated time that the vehicle on this
	 * Segment can leave
	 * @return Returns an ArrayList of the Segments that start at the given
	 * location id and leave after the given time
	 */
	public static ArrayList<Segment> LoadAllAtLocation(int locationID,
			int startTime) {
		ArrayList<Segment> returnList = new ArrayList<Segment>();
		try {
			ArrayList<Map<String, Object>> temp = executeQuery("SELECT SegmentID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID,EarliestArrivalTime,LatestArrivalTime,EarliestDepartureTime,LatestDepartureTime,s.Deleted,l.LocationID as StartID,"
					+ "l.Latitude as StartLat, l.Longitude as StartLon,l.Name as StartName, l.State as StartState, l.Country as StartCountry, l.TravelType1 as StartType1, l.TravelType2 as StartType2, l.TravelType3 as StartType3, l.TravelType4 as StartType4, l.TravelType5 as StartType5, l.TravelType6 as StartType6,e.LocationID as EndID,"
					+ "e.Latitude as EndLat, e.Longitude as EndLon,e.Name as EndName, e.State as EndState, e.Country as EndCountry, e.TravelType1 as EndType1, e.TravelType2 as EndType2, e.TravelType3 as EndType3, e.TravelType4 as EndType4, e.TravelType5 as EndType5, e.TravelType6 as EndType6 "
					+ "FROM segment s left outer join location l on s.FromLocationID=l.LocationID left outer join location e on s.ToLocationID = e.LocationID where FromLocationID = '"
					+ locationID + "' and TimeOfDeparture > " + startTime  + " AND l.Deleted = false AND e.Deleted=false");
			for (int i = 0; i < temp.size(); i++) {
				Segment s = BuildFromDataRow(temp.get(i));
				// s.onBoard= Shipment.LoadAllForSegment(s.getID());
				returnList.add(s);
			}
		} catch (Exception ex) {
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
		return returnList;
	}// End of LoadAllAtLocation(int locationID, int startTime)

	/**
	 * This function will load a Segment from the database based on the given ID
	 * @param id This is the ID of the Segment to load from the database
	 * @return Returns a Segment object based on the id passed in
	 */
	public static Segment Load(int id) {
		try {
			// Search the database for Segments with the given id
			ArrayList<Map<String, Object>> temp = executeQuery("SELECT SegmentID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID,EarliestArrivalTime,LatestArrivalTime,EarliestDepartureTime,LatestDepartureTime,s.Deleted,l.LocationID as StartID,"
					+ "l.Latitude as StartLat, l.Longitude as StartLon,l.Name as StartName, l.State as StartState, l.Country as StartCountry, l.TravelType1 as StartType1, l.TravelType2 as StartType2, l.TravelType3 as StartType3, l.TravelType4 as StartType4, l.TravelType5 as StartType5, l.TravelType6 as StartType6,e.LocationID as EndID,"
					+ "e.Latitude as EndLat, e.Longitude as EndLon,e.Name as EndName, e.State as EndState, e.Country as EndCountry, e.TravelType1 as EndType1, e.TravelType2 as EndType2, e.TravelType3 as EndType3, e.TravelType4 as EndType4, e.TravelType5 as EndType5, e.TravelType6 as EndType6 "
					+ "FROM segment s left outer join location l on s.FromLocationID=l.LocationID left outer join location e on s.ToLocationID = e.LocationID where SegmentID = "
					+ id  + " AND l.Deleted = false AND e.Deleted=false");
			// If something was returned build the Segment from it
			if (temp.size() > 0) {
				Segment s = BuildFromDataRow(temp.get(0));
				s.onBoard = Shipment.LoadAllForSegment(s.getID());

				return s;
			}
			return null;
		}// End of try block
		catch (Exception ex) {
			System.out.println("Error " + ex);
			ex.printStackTrace();
			return null;
		}// End of catch block
	}// End of Load(int id)

	/**
	 * This function overrides the parent's Update function and will handle
	 * changes made to the Segment object in the database
	 * @return Returns a boolean indicating if the Update completed successfully or not
	 */
	@Override
	public boolean Update() {
		try {
			// toDo: set id on insert set update statement
			if (isNew()) {
				// If the Segment is new insert it into the database by
				// executing the following
				executeCommand("Insert into Segment (FromLocationID,ToLocationID,VehicleID,ModeType,Distance,TimeOfDeparture,TimeOfArrival,Lane,ShippingRateID,EarliestArrivalTime,LatestArrivalTime,EarliestDepartureTime,LatestDepartureTime) Values ('"
						+ this.getStartLocationID()
						+ "','"
						+ this.getEndLocationID()
						+ "','"
						+ this.vehicle.getId()
						+ "','"
						+ this.getMode()
						+ "','"
						+ this.getDistance()
						+ "','"
						+ this.getEstimatedDepartureTime()
						+ "','"
						+ this.getEstimatedArrivalTime()
						+ "','"
						+ this.getLane()
						+ "','"
						+ this.getShippingRate().getId()
						+ "','"
						+ this.getEarliestArrivalTime()
						+ "','"
						+ this.getLatestArrivalTime()
						+ "','"
						+ this.getEarliestDepartureTime()
						+ "','"
						+ this.getLatestDepartureTime() + "')");
				// Grab this Segment from the database
				ArrayList<Map<String, Object>> temp = executeQuery("Select SegmentID from Segment where FromLocationID ='"
						+ this.getStartLocationID()
						+ "' "
						+ "AND ToLocationID ='"
						+ this.getEndLocationID()
						+ "' "
						+ "AND VehicleID='"
						+ this.vehicle.getId()
						+ "' "
						+ "And ModeType='"
						+ this.getMode()
						+ "' "
						+ "And Distance='"
						+ this.getDistance()
						+ "' "
						+ "And TimeOfDeparture ='"
						+ this.getEstimatedDepartureTime()
						+ "' "
						+ "And Lane ='"
						+ this.getLane()
						+ "' "
						+ "And EarliestArrivalTime ='"
						+ this.getEarliestArrivalTime()
						+ "' "
						+ "And LatestArrivalTime ='"
						+ this.getLatestArrivalTime()
						+ "' "
						+ "And EarliestDepartureTime ='"
						+ this.getEarliestDepartureTime()
						+ "' "
						+ "And LatestDepartureTime ='"
						+ this.getLatestDepartureTime()
						+ "' "
						+ "And ShippingRateID ='"
						+ this.getShippingRate().getId()
						+ "' "
						+ "And TimeOfArrival = '"
						+ this.getEstimatedArrivalTime() + "'");
				// If this Segment exists on the database mark it as old and
				// clean
				if (temp.size() > 0) {
					this.id = (Integer) temp.get(temp.size()-1).get("SegmentID"); // Set the
					// Segment
					// id to
					// the
					// id
					// from
					// the
					// database

				}// End of found something if
				MarkClean(); // Mark the Segment as clean
				MarkOld(); // Mark the Segment as old
				
				//Now that the Segment has been pushed to the database we need to add a 
				//new index entry
				AddIndex(this.vehicle.getId(), this.id, this.travelType.getVehicleTypeID(), this.vehicle.mode);
				
				
			}// End of isNew if
			else {
				if (isDirty()) {
					// If the Segment is not new, but is dirty then it needs to
					// be updated by the following SQL command
					executeCommand("Update Segment Set FromLocationID ='"
							+ this.getStartLocationID() + "', "
							+ "ToLocationID ='" + this.getEndLocationID() + "', "
							+ "VehicleID='" + this.vehicle.getId() + "', "
							+ "ModeType='" + this.getMode() + "', "
							+ "Distance='" + this.getDistance() + "', "
							+ "TimeOfDeparture ='"
							+ this.getEstimatedDepartureTime() + "', "
							+ "Lane ='" + this.getLane() + "', "
							+ "ShippingRateID ='"
							+ this.getShippingRate().getId() + "', "
							+ "EarliestArrivalTime ='"
							+ this.getEarliestArrivalTime() + "', "
							+ "LatestArrivalTime ='"
							+ this.getLatestArrivalTime() + "', "
							+ "EarliestDepartureTime ='"
							+ this.getEarliestDepartureTime() + "', "
							+ "LatestDepartureTime ='"
							+ this.getLatestDepartureTime() + "', "
							+ "TimeOfArrival = '"
							+ this.getEstimatedArrivalTime() + "', "
							+ "Deleted = " + this.isDeleted() 
							+ " Where SegmentID=" + this.id);
					MarkClean(); // Mark the Segment as clean
					
					//Now that the Segment has been pushed to the database we need to add a 
					//new index entry
					UpdateIndex(this.vehicle.getId(), this.id, this.travelType.getVehicleTypeID(), this.vehicle.mode);
					
				}// End of isDirty if
			}// End of isOld else
			return true;
		}// End of try block
		catch (Exception ex) {
			System.out.println("Error " + ex); // Print out the error
			ex.printStackTrace();
			return false;
		}// End of catch block

	}// End of the overridden Update()

	/**
	 * This function estimates the capacity available over this Segment
	 * @return Returns the estimated capacity along this Segment
	 */
	public int estimateCapacity() {
		try {
			ArrayList<Map<String, Object>> temp = executeQuery("Select Sum(Size)as Capacity from Shipment where ShipmentID IN (Select Distinct ShipmentID from ShipmentHistory where SegmentID = "
					+ this.id + ")");
			if (temp.get(0).get("Capacity") != null) {
				System.out.println(temp.get(0).get("Capacity"));
				return Integer.parseInt(temp.get(0).get("Capacity").toString());
			} else
				return 0;
		} catch (Exception ex) {
			System.out.println("Error " + ex);
			ex.printStackTrace();
			return -1;
		}
	}// End of estimateCapacity()

	/**
	 * This is the overridden Delete function of the parent class and will
	 * mark this segment as deleted
	 * @return Returns a boolean indicating if the segment was successfully marked as deleted or not
	 */
	@Override
	public boolean Delete() {
		try {
			executeCommand("Update Segment Set Deleted = true Where SegmentID = " + this.id);
			this.MarkDeleted();
			return true;
		}// End of try block
		catch (Exception ex) {
			System.out.println("Error " + ex); // Print out the error
			ex.printStackTrace();
			return false;
		}// End of catch block

	}// End of the overridden Delete()

	/**
	 * This is the overridden toString function for the Segment 
	 * @return Returns the id of the start location and the end location
	 */
	@Override
	public String toString() {
		return this.fromID + "   " + this.toID;
	}// End of toString()

	public static int getDefaultArrivalTime() {
		return DEFAULT_ARRIVAL_TIME;
	}

	public static int getDefaultDepartureTime() {
		return DEFAULT_DEPARTURE_TIME;
	}

	public static double getDefaultDistance() {
		return DEFAULT_DISTANCE;
	}

	public static int getDefaultEarliestArrivalTime() {
		return DEFAULT_EARLIEST_ARRIVAL_TIME;
	}

	public static int getDefaultEarliestDepartureTime() {
		return DEFAULT_EARLIEST_DEPARTURE_TIME;
	}

	public static int getDefaultStartLocationId() {
		return DEFAULT_START_LOCATION_ID;
	}

	public static String getDefaultLanes() {
		return DEFAULT_LANES;
	}

	public static int getDefaultLatestArrivalTime() {
		return DEFAULT_LATEST_ARRIVAL_TIME;
	}

	public static int getDefaultLatestDepartureTime() {
		return DEFAULT_LATEST_DEPARTURE_TIME;
	}

	public static String getDefaultMode() {
		return DEFAULT_MODE;
	}

	public static int getDefaultEndLocationId() {
		return DEFAULT_END_LOCATION_ID;
	}

}// End of Segment Class
