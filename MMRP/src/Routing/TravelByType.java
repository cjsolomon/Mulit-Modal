package Routing;

import java.util.ArrayList;
import core.Vehicle;
import core.Location;
import core.Segment;
import core.Shipment;

public class TravelByType extends RoutingAlgorithm{

	Vehicle.TravelModes mode;
	int percentChanceOfDirectPath;
	int percentChanceOfBestRoute;
	
	ArrayList<Segment> route;
	WeightedMetric metric;
	int currentTime;
	Shipment shipment;
	boolean pathFound;
	int maxTries;
	
	public TravelByType(){
		route = new ArrayList<Segment>();
		mode = Vehicle.TravelModes.TRUCK;
		metric = new WeightedMetric();
		currentTime = 0;
		percentChanceOfDirectPath = 30;
		percentChanceOfBestRoute =  50;
		pathFound = false;
		maxTries = 10;
	}//End of TravelByType() default constructor
	
	public TravelByType(Vehicle.TravelModes travelMode, WeightedMetric metric, Shipment shipment){
		route = new ArrayList<Segment>();
		this.mode = travelMode;
		this.metric = metric;
		this.shipment = shipment;
		percentChanceOfDirectPath = 30;
		percentChanceOfBestRoute = 50;
		pathFound = false;
		maxTries = 10;
	}//End of TravelByType() 3-argument constructor
	
	public TravelByType(Vehicle.TravelModes travelMode, WeightedMetric metric, Shipment shipment, int directPathChance, int bestRouteChance, int maximumTries){
		route = new ArrayList<Segment>();
		this.mode = travelMode;
		this.metric = metric;
		this.shipment = shipment;
		percentChanceOfDirectPath = directPathChance;
		percentChanceOfBestRoute = bestRouteChance;
		pathFound = false;
		maxTries = maximumTries;
	}//End of TravelByType() 6-argument constructor
	
	public ArrayList<Segment> getPath(){
		//First check to see if we have a direct path between the start and end point
		ArrayList<Segment> route =  new ArrayList<Segment>();
		ArrayList<Segment> paths = validPaths(grabSegmentsBetween(shipment.loadStartLocation(), shipment.loadEndLocation()));
		
		int currentLocationID = shipment.getFromLocationID();
		
		if(paths.size() > 0){
			//We have a valid path so choose the best segment to travel along
			route.add(metric.getLowestWeightedCostSegment(paths));
			pathFound = true;
		}//End of directPath if
		else{
			//We do not have a direct path so we will enter a while loop until we find a path
			int tries = 0;
			while(tries < maxTries && !pathFound){
				//Attempt to find a direct path from the currentLocation to the end giving a percent chance passed in
				if(Math.floor(Math.random() * 100) < percentChanceOfDirectPath){
					//We will attempt to find a directPath between this location and the end location
					paths = validPaths(grabSegmentsBetween(Location.Load(currentLocationID), shipment.loadEndLocation()));
					if(paths.size() > 0){
						//We have a direct path from the start to the finish so choose the best one and return it
						route.add(metric.getLowestWeightedCostSegment(paths));
						pathFound = true;
					}//End of direct path if
				}else{
					//Grab all the Segments starting at this location
					paths = validPaths(grabSegmentsStartingAt(currentLocationID));
					if(paths.size()  > 0){
						//We have valid paths we can use
						Segment nextSegment;
						//Now we check to see if we want to use the best possbile path or not
						if(Math.floor(Math.random() * 100) > percentChanceOfBestRoute){
							//Choose the best route to travel along
							nextSegment = metric.getLowestWeightedCostSegment(paths);
							route.add(nextSegment);
							currentLocationID = nextSegment.getEndLocationID();
						}else{
							//Choose a random path to travel along
							nextSegment = paths.get((int)Math.floor(Math.random() * paths.size()));
							route.add(nextSegment);
							currentLocationID = nextSegment.getEndLocationID();
							
						}//End of not-best route path
					}else{
						//We have no valid path from this point so we must rewind the path
						if(!rewindPath(route)){
							//we could not rewind the path, therefore we could not find a path
							tries = maxTries;
						}//End of unsuccessful path rewinding if
						else{
							//Set the currentLocationID to the end of the path
							currentLocationID = route.get(route.size()-1).getEndLocationID();
						}
					}//End of no valid paths else
					
					//Check to see if we are done
					if(currentLocationID == shipment.getToLocationID()){
						//We have found a path to the end and are done
						pathFound = true;
					}
				}//End of non-directPath else
				tries++;
			}//End of path finding while loop
		}//End of no directPath else
		
		//Check to see if we exited because we succeeded or because we failed
		if(!pathFound){
			//We failed
			route.clear();
		}//End of failure if
			
		return route;
		
	}//End of getPath()
	
	public ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck){
		//We need to check to see if the vehicle is available at the location
		//and if it has any capacity left to carry this shipment and if it is running and if it is the correct vehicle type
		//for(int i = 0; i < segmentsToCheck.size(); i++){
		//	if(s.getEstimatedDepartureTime() < currentTime || s.getTravelType().getActCap() < shipment.getSize() || 
		//		s.getVehicle().getStatus() != "RUNNING" || s.getMode() != mode.toString()){
				//We cannot use this segment so remove it from the list
		//		segmentsToCheck.remove(s);
		//	}//End of time, size and status restraint if
		//}//End of time and capacity checking for loop
			
	return segmentsToCheck;
	}//End of ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck)
	
}//End of TravelByType class
