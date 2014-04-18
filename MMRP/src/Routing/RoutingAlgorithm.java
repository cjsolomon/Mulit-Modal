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
	
	abstract public ArrayList<Segment> getPath();
	abstract public ArrayList<Segment> validPaths(ArrayList<Segment> path);
	
	public ArrayList<Segment> getRoute() {
		return route;
	}
	public WeightedMetric getMetric() {
		return metric;
	}
	public int getCurrentTime() {
		return currentTime;
	}
	public Shipment getShipment() {
		return shipment;
	}
	public boolean isPathFound() {
		return pathFound;
	}
	public int getMaxTries() {
		return maxTries;
	}
	public void setRoute(ArrayList<Segment> route) {
		this.route = route;
	}
	public void setMetric(WeightedMetric metric) {
		this.metric = metric;
	}
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public void setPathFound(boolean pathFound) {
		this.pathFound = pathFound;
	}
	public void setMaxTries(int maxTries) {
		this.maxTries = maxTries;
	}
	
	public ArrayList<Segment> grabSegmentsStartingAt(int startID){
		ArrayList<Segment> startSegments=Segment.LoadAll("Where FromLocationID = '"+ startID + "'");
		startSegments = validPaths(startSegments);
		return startSegments;
	}//End of grabSegmentsStartingAt(Location start)
	
	public ArrayList<Segment> grabSegmentsBetween(Location start, Location end){
		ArrayList<Segment> directPath=Segment.LoadAll("Where FromLocationID = '"+ start.getID() +"' AND ToLocationID ='" + end.getID() +"';");
		directPath = validPaths(directPath);
		return directPath;
	}//End of ArrayList<Segment> grabSegmentsBetween(Location start, Location end)
	
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
