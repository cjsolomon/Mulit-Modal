package Routing;

import java.util.ArrayList;
import core.Vehicle;
import core.Segment;
import core.Shipment;
import core.Location;

public class NextAvailableVehicle extends RoutingAlgorithm{

	Vehicle.TravelModes mode;
	int percentChanceOfDirectPath;
	
	/**
	 * This is the default NextAvailableVehicle constructor
	 * <p>The travel mode used will be set to TRUCK
	 * <p>The percent chance of trying to use a direct path from the current location to the end location will be set to 30%
	 * <p>The maximum number of attempts to find a path will be set to 10
	 * <p>The weighted metric will be balanced between the measurements
	 */
	public NextAvailableVehicle(){
		route = new ArrayList<Segment>();
		mode = Vehicle.TravelModes.TRUCK;
		metric = new WeightedMetric();
		currentTime = 0;
		pathFound = false;
		percentChanceOfDirectPath = 30;
		maxTries = 10;
	}//End of NextAvailableVehicle() default constructor
	
	/**
	 * This is the 3 argument constructor for NextAvailableVehicle
	 * @param travelMode This is the preferred travel mode to use
	 * @param metric This is the user-defined weighted metric to determine cost
	 * @param shipment This is the shipment that will be routed
	 * <p>The percent chance of trying to use a direct path from the current location to the end location will be set to 30%
	 * <p>The maximum number of attempts to find a path will be set to 10
	 */
	public NextAvailableVehicle(Vehicle.TravelModes travelMode, WeightedMetric metric, Shipment shipment){
		route = new ArrayList<Segment>();
		this.mode = travelMode;
		this.metric = metric;
		this.shipment = shipment;
		pathFound = false;
		percentChanceOfDirectPath = 30;
		maxTries = 10;
	}//End of NextAvailableVehicle() 3-argument constructor
	
	/**
	 * This is the 5 argument constructor of NextAvailableVehicle
	 * @param travelMode This is the preferred mode of travel to use
	 * @param metric This is the user-defined weighted metric to determine cost
	 * @param shipment This is the shipment that will be routed
	 * @param chanceOfDirectPath This is the percent chance that the routing will attempt a direct route from the current Location
	 * @param maximumTries This is the maximum number of attempts the algorithm has to fid a path from the starting Location of the Shipment to the end Location
	 */
	public NextAvailableVehicle(Vehicle.TravelModes travelMode, WeightedMetric metric, Shipment shipment, int chanceOfDirectPath, int maximumTries){
		route = new ArrayList<Segment>();
		this.mode = travelMode;
		this.metric = metric;
		this.shipment = shipment;
		pathFound = false;
		percentChanceOfDirectPath = chanceOfDirectPath;
		maxTries = maximumTries;
	}//End of NextAvailableVehicle() 6-argument constructor
	
	/**
	 * This is the getPath function, it will return a path from the start location to the end location or
	 * a null path if it fails.
	 * <p>It will find a path based on the following steps :
	 * <p>1) Grab the segments connected to the start and end nodes
	 * <p>2) If a direct path exists take the next available vehicle and use it
     * <p>3) While we aren't connected to the end node do the following
     * <p>4) Take the next available vehicle and use it
     * <p>5)Add this segment to the path
     * <p>6) If we have reached the end node we are done, else repeat from 2
     */
	@Override
	public ArrayList<Segment> getPath(){
		//First check to see if we have a direct path between the start and end point
		ArrayList<Segment> route =  new ArrayList<Segment>();
		ArrayList<Segment> paths = validPaths(grabSegmentsBetween(shipment.loadStartLocation(), shipment.loadEndLocation()));
		
		int currentLocationID = shipment.getCurrentLocationID();
		
		if(paths.size() > 0){
			//Now we will grab the segment with the closest starting time to the currenttime
			Segment soonest = paths.get(0);
			//Note: I could take the Math.abs value of the departure time - the currentTime, but seeing
			//as how I eliminated any segments that had a passed departure time, the segment with the 
			//lowest departure time will be leaving first.
			int lowest = paths.get(0).getEstimatedDepartureTime();
			for(int i = 0; i < paths.size(); i++){
				if(paths.get(i).getEstimatedDepartureTime() < lowest){
					soonest = paths.get(i);
					lowest = paths.get(i).getEstimatedDepartureTime();
				}//End of new lowest if
			}//End of for loop
			
			//Add and return the segment
			route.add(soonest);
			pathFound = true;
		}//End of directPath if
		else{
			//We do not have a direct path so we will enter a while loop until we find a path
			int tries = 0;
			while(tries < maxTries && !pathFound){
				
				System.out.println("Next Available Vehicle  - Current Attempt : " + (tries+1) + " of " + maxTries);
				//Attempt to find a direct path from the currentLocation to the end giving a percent chance passed in
				if(Math.floor(Math.random() * 100) < percentChanceOfDirectPath){
					System.out.println("Next Available Vehicle  - Attempting Direct Path");
					//We will attempt to find a directPath between this location and the end location
					paths = validPaths(grabSegmentsBetween(Location.Load(currentLocationID), shipment.loadEndLocation()));
					if(paths.size() > 0){
						System.out.println("Next Available Vehicle  - Direct path found!");
						//We have a direct path from the start to the finish so choose the best one and return it
						Segment soonest = paths.get(0);
						//Note: I could take the Math.abs value of the departure time - the currentTime, but seeing
						//as how I eliminated any segments that had a passed departure time, the segment with the 
						//lowest departure time will be leaving first.
						int lowest = paths.get(0).getEstimatedDepartureTime();
						for(int i = 0; i < paths.size(); i++){
							if(paths.get(i).getEstimatedDepartureTime() < lowest){
								soonest = paths.get(i);
								lowest = paths.get(i).getEstimatedDepartureTime();
							}//End of new lowest if
						}//End of for loop
			
						//Add and return the segment
						route.add(soonest);
						pathFound = true;
					}//End of direct path if
				}else{
					System.out.println("Next Available Vehicle  - Finding non-direct path");
					//Grab all the Segments starting at this location
					paths = validPaths(grabSegmentsStartingAt(currentLocationID));
					if(paths.size()  > 0){
						System.out.println("Next Available Vehicle  - Found next segment");
						//We have valid paths we can use
						Segment soonest = paths.get(0);
						//Note: I could take the Math.abs value of the departure time - the currentTime, but seeing
						//as how I eliminated any segments that had a passed departure time, the segment with the 
						//lowest departure time will be leaving first.
						int lowest = paths.get(0).getEstimatedDepartureTime();
						for(int i = 0; i < paths.size(); i++){
							if(paths.get(i).getEstimatedDepartureTime() < lowest){
								soonest = paths.get(i);
								lowest = paths.get(i).getEstimatedDepartureTime();
							}//End of new lowest if
						}//End of for loop
			
						//Add the segment
						route.add(soonest);
						currentLocationID = soonest.getEndLocationID();
					}else{
						System.out.println("Next Available Vehicle  - Could not find next segment - rewinding path");
						//We have no valid path from this point so we must rewind the path
						if(!rewindPath(route)){
							System.out.println("Next Available Vehicle  - Could not rewind path, could not find route");
							//we could not rewind the path, therefore we could not find a path
							tries = maxTries;
							continue;
						}//End of unsuccessful path rewinding if
						else{
							//Set the currentLocationID to the end of the path
							currentLocationID = route.get(route.size()-1).getEndLocationID();
						}
					}//End of no valid paths else
					
					//Set the currentLocationID to the end of the path
					currentLocationID = route.get(route.size()-1).getEndLocationID();
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
	@Override
	public ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck){
		System.out.println("Next Available Vehicle  - Validating Segments");
		//We need to check to see if the vehicle is available at the location
		//and if it has any capacity left to carry this shipment and if it is running and if it is the correct vehicle type
		for(int i = 0; i < segmentsToCheck.size(); i++){
			System.out.println("Next Available Vehicle  - Checking Segment :" + (i+1) + " of " + segmentsToCheck.size());
			if(segmentsToCheck.get(i).getEstimatedDepartureTime() < currentTime || 
				Math.abs(segmentsToCheck.get(i).getActualCapacity() - segmentsToCheck.get(i).getTravelType().getMaxCap()) < shipment.getSize() || 
				segmentsToCheck.get(i).getVehicle().getStatus().toString() != "RUNNING" || 
				!segmentsToCheck.get(i).getMode().equalsIgnoreCase(mode.toString()) ||
				  this.route.contains(segmentsToCheck.get(i))){
				System.out.println("Next Available Vehicle  - Segment NOT VALID");
				//We cannot use this segment so remove it from the list
				segmentsToCheck.remove(i);
				i--;
			}//End of time, size and status restraint if
			else{
				System.out.println("Next Available Vehicle  - Segment VALID");
			}
		}//End of time and capacity checking for loop
			
	return segmentsToCheck;
	}//End of ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck)
	
	
}//End of TravelByType class
