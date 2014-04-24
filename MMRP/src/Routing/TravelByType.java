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
	
	/**
	 * This is the default TravelByType 
	 * <p>This sets the travel mode to TRUCK
	 * <p>This percent chance of a direct route is attempted from the current location to the end location is set to 30%
	 * <p>The percent chance that the lowest cost path is chosen next is set to 50%
	 * <p>The maximum number of attempts to find a path is set to 10
	 */
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
	
	/**
	 * This is the 3 argument TravelByType constructor
	 * @param travelMode This is the mode of travel that the path is restricted to
	 * @param metric This is the weighted metric used to determine the cost of paths
	 * @param shipment This is the shipment that is being routed.
	 * <p>This percent chance of a direct route is attempted from the current location to the end location is set to 30%
	 * <p>The percent chance that the lowest cost path is chosen next is set to 50%
	 * <p>The maximum number of attempts to find a path is set to 10
	 */
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
	 
	/**
	 * This is the 6 argument TravelByType constructor
	 * @param travelMode This is the mode of travel that the paths must use
	 * @param metric This is the metric used to measure the costs of the paths
	 * @param shipment This is the shipment being routed
	 * @param directPathChance This is the chance that a direct path will be attempted from the current location to the end location
	 * @param bestRouteChance This is the chance the the lowest cost path will be chosen from this location
	 * @param maximumTries This is the maximum number of attempts that the algorithm has to find a path
	 */
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
	
	/**
	 * This is the getPath function, it will return a path from the start location to the end location or
	 * a null path if it fails.
	 * <p>It will find a path based on the following steps :
	 * <p>1) If a random number is greater than the percent chance of a direct path then attempt a direct path
	 * <p>2) If a random number is greater than the percent chance of the best route then choose the best path otherwise pick a random path
	 * <p>3) If we have reached the end we are done, otherwise repeat from 1
     */
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
				if(Math.floor(Math.random() * 100) > percentChanceOfDirectPath){
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
	
	/**
	 * This function will remove Segments that are not valid for this algorithm
	 */
	public ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck){
		//We need to check to see if the vehicle is available at the location
		//and if it has any capacity left to carry this shipment and if it is running and if it is the correct vehicle type
		for(int i = 0; i < segmentsToCheck.size(); i++){
			if(segmentsToCheck.get(i).getEstimatedDepartureTime() < currentTime || 
				Math.abs(segmentsToCheck.get(i).getActualCapacity() - segmentsToCheck.get(i).getTravelType().getMaxCap()) < shipment.getSize() || 
				segmentsToCheck.get(i).getVehicle().getStatus().toString() != "RUNNING" || 
				segmentsToCheck.get(i).getMode() != mode.toString()){
				//We cannot use this segment so remove it from the list
				segmentsToCheck.remove(segmentsToCheck.get(i));
			}//End of time, size and status restraint if
		}//End of time and capacity checking for loop
			
	return segmentsToCheck;
	}//End of ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck)
	
}//End of TravelByType class
