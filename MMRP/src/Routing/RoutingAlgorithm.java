package Routing;

import java.util.ArrayList;

import core.Location;
import core.Segment;
import core.Shipment;

public abstract class RoutingAlgorithm {

	ArrayList<Segment> route;
	WeightedMetric metric;
	int currentTime;
	Shipment shipment;
	boolean pathFound;
	int maxTries;
	
	/**
	 * This function will remove Segments that are not valid for this algorithm
	 */
	abstract public ArrayList<Segment> getPath();
	
	/**
	 * This is the getPath function, it will return a path from the start location to the end location or
	 * a null path if it fails.
	 */
	abstract public ArrayList<Segment> validPaths(ArrayList<Segment> path);
	
	/**
	 * This function returns the WeightedMetric being used by this routing algorithm
	 * @return Returns the WeightedMetric being used by this routing algorithm
	 */
	public WeightedMetric getMetric() {
		return metric;
	}//End of getMetric()
	
	/**
	 * This function returns the current time
	 * @return Returns the current time
	 */
	public int getCurrentTime() {
		return currentTime;
	}//End of getCurrentTime()
	
	/**
	 * This function returns the Shipment that is being routed
	 * @return Returns the Shipment that is beign routed
	 */
	public Shipment getShipment() {
		return shipment;
	}//End of getShipment()
	
	/**
	 * This function returns the boolean indicating if a path was found
	 * @return Returns a boolean indicating if the path was found
	 */
	public boolean isPathFound() {
		return pathFound;
	}//End of isPathFound()
	
	/**
	 * This function returns the maximum tries the algorithm gets to find a path
	 * @return Returns the maximum number of tries the algorithm gets to find a path
	 */
	public int getMaxTries() {
		return maxTries;
	}//End of getMaxTries()
	
	/**
	 * This function sets the WeightedMetric used to measure the cost to move along paths
	 * @param metric This is the new WeightedMetric used to measure costs
	 */
	public void setMetric(WeightedMetric metric) {
		this.metric = metric;
	}//End of setMetric(WeightedMetric metric)
	
	/**
	 * This function sets the currentTime for the RoutingAlgorithm
	 * @param currentTime This is the new currentTime for the algorithm
	 */
	public void setCurrentTime(int currentTime) {
		//NEED SOME ERROR CHECKING
		this.currentTime = currentTime;
	}//End of setCurrentTime(int currentTime)
	
	/**
	 * This function sets the Shipment that is to be routed by this algorithm
	 * @param shipment This is the new shipment to route
	 */
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}//End of setShipment(Shipment shipment)
	
	/**
	 * This function sets the number of maximum attempts the RoutingAlgorithm gets to find a path
	 * for its Shipment
	 * @param maxTries This is the new maximum number of attempts to find a path
	 */
	public void setMaxTries(int maxTries) {
		//NEED SOME ERROR CHECKING
		this.maxTries = maxTries;
	}//End of setMaxTries(int maxTries)
	
	/**
	 * This function will grab all the Segments that start at the given Location ID and return an ArrayList of valid Segments
	 * @param startID This is the is of the starting Location of the returned Segments
	 * @return Returns an ArrayList of valid Segments that start at the given Location
	 */
	public ArrayList<Segment> grabSegmentsStartingAt(int startID){
		ArrayList<Segment> startSegments=Segment.LoadAll("Where FromLocationID = '"+ startID + "'");
		startSegments = validPaths(startSegments);
		return startSegments;
	}//End of grabSegmentsStartingAt(Location start)
	
	/**
	 * This function will grab all the valid Segments that start and end at the given Location ids
	 * @param start This is the id of the starting Location
	 * @param end This is the id of the ending Location
	 * @return Returns an ArrayList of valid Segments between the starting and ending Locations
	 */
	public ArrayList<Segment> grabSegmentsBetween(Location start, Location end){
		ArrayList<Segment> directPath=Segment.LoadAll("Where FromLocationID = '"+ start.getID() +"' AND ToLocationID ='" + end.getID() +"';");
		directPath = validPaths(directPath);
		return directPath;
	}//End of ArrayList<Segment> grabSegmentsBetween(Location start, Location end)
	
	/**
	 * This funtion will return the path taking by a Shipment
	 * @param rewindPath This is the path that needs to be rewound
	 * @return Returns a boolean indicating if the path was able to be rewound
	 */
	public boolean rewindPath(ArrayList<Segment> rewindPath){
		if(rewindPath.size() > 1){
			//We can rewind the path
			rewindPath.remove(rewindPath.size()-1);
			return true;
		}else{
			//We cannot rewind the path
			rewindPath.clear();
			return false;
		}
	}//End of rewindPath(ArrayList<Segment> rewindPath)
	
}
