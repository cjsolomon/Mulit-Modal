package Routing;
import java.util.ArrayList;
import core.Segment;
import core.Shipment;
import core.Location;

public class BestFirstFind extends RoutingAlgorithm{

    /* How bestFirstFind Works
         * 1) Grab the segments connected to the start and end nodes
         * 2) While we arent connected to the end node do the following
         * 3) Pick the best segment, using the metric to go down
         * 4) Check to see if we can now connect to the end node
         * 5) Add this segment to the path
         * 6) If we have reached the end node we are done, else repeat from 3
         */
	/**
	 * This is the default constructor for the BestFirstFind algorithm
	 * <p>This function will use a default metric and allow 10 tries for the algorithm to return a route.
	 */
	public BestFirstFind(){
		route = new ArrayList<Segment>();
		metric = new WeightedMetric();
		currentTime = 0;
		pathFound = false;
		maxTries = 10;
	}//End of BestFirstFind() default constructor
	
	/**
	 * This is the 2 argument constructor for the BestFirstFind algorithm
	 * @param metric This is the user defined Weighted Metric 
	 * @param shipment This is the shipment that this algorithm will attempt to find a path for
	 */
	public BestFirstFind(WeightedMetric metric, Shipment shipment){
		route = new ArrayList<Segment>();
		this.metric = metric;
		this.shipment = shipment;
		pathFound = false;
		maxTries = 10;
	}//End of BestFirstFind() 3-argument constructor
	
	/**
	 * This is the 3 argument constructor for the BestFirstFind algorithm
	 * @param metric This is the user defined WeightedMetric
	 * @param shipment This is the shipment that this algorithm will attempt to `																							
	 * @param maximumTries
	 */
	public BestFirstFind(WeightedMetric metric, Shipment shipment, int maximumTries){
		route = new ArrayList<Segment>();
		this.metric = metric;
		this.shipment = shipment;
		pathFound = false;
		maxTries = maximumTries;
	}//End of BestFirstFind() 4-argument constructor
	
	/**
	 * This is the getPath function, it will return a path from the start location to the end location or
	 * a null path if it fails.
	 * <p>It will find a path based on the following steps :
	 * <p>1) Grab the segments connected to the start and end nodes
     * <p>2) While we arent connected to the end node do the following
     * <p>3) Pick the best segment, using the metric to go down
     * <p>4) Check to see if we can now connect to the end node
     * <p>5) Add this segment to the path
     * <p>6) If we have reached the end node we are done, else repeat from 3
     */
	@Override
	public ArrayList<Segment> getPath(){
		//First check to see if we have a direct path between the start and end point
		ArrayList<Segment> route =  new ArrayList<Segment>();
		ArrayList<Segment> paths = validPaths(grabSegmentsBetween(shipment.loadStartLocation(), shipment.loadEndLocation()));
		
		int currentLocationID = shipment.getCurrentLocationID();
		
		if(paths.size() > 0){
			//We have a valid path so choose the best segment to travel along
			route.add(metric.getLowestWeightedCostSegment(paths));
			pathFound = true;
		}//End of directPath if
		else{
			//We do not have a direct path so we will enter a while loop until we find a path
			int tries = 0;
			while(tries < maxTries && !pathFound){
				
				System.out.println("Best First Find - Current Attempt : " + (tries+1) + " of " + maxTries);
				//Attempt to find a direct path from the currentLocation to the end using the best path possible
				paths = validPaths(grabSegmentsBetween(Location.Load(currentLocationID), shipment.loadEndLocation()));
				if(paths.size() > 0){
					System.out.println("Best First Find - Direct Path found!");
					//We have a direct path from the start to the finish so choose the best one and return it
					route.add(metric.getLowestWeightedCostSegment(paths));
					//Set the currentLocationID to the end of the path
					currentLocationID = route.get(route.size()-1).getEndLocationID();
					pathFound = true;
				}//End of direct path if
				else{
					System.out.println("Best First Find - No Direct Path available, choosing next best");
					//Grab all the Segments starting at this location
					paths = validPaths(grabSegmentsStartingAt(currentLocationID));
					if(paths.size()  > 0){
						System.out.println("Best First Find - Next best path found");
						//We have valid paths we can use
						Segment nextSegment;
						//Choose the best route to travel along
						nextSegment = metric.getLowestWeightedCostSegment(paths);
						route.add(nextSegment);
						currentLocationID = nextSegment.getEndLocationID();
					}else{
						System.out.println("Best First Find - Could not locate next path - rewinding path");
						//We have no valid path from this point so we must rewind the path
						if(!rewindPath(route)){
							System.out.println("Best First Find - Could not rewind, could not find path!");
							//we could not rewind the path, therefore we could not find a path
							tries = maxTries;
							continue;
						}//End of unsuccessful path rewinding if
						else{
							//Set the currentLocationID to the end of the path
							currentLocationID = route.get(route.size()-1).getEndLocationID();
						}
					}//End of no valid paths else
					
					//Check to see if we are done
					if(currentLocationID == shipment.getToLocationID()){
						System.out.println("Best First Find - Arrived at destination!");
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
		System.out.println("Best First Find - Validating Segments");
		//We need to check to see if the vehicle is available at the location
		//and if it has any capacity left to carry this shipment and if it is running and if it is the correct vehicle type
		for(int i = 0; i < segmentsToCheck.size(); i++){
			System.out.println("Best First Find - Checking Segment :" + (i+1) + " of " + segmentsToCheck.size());
			if(segmentsToCheck.get(i).getEstimatedDepartureTime() < currentTime || 
				Math.abs(segmentsToCheck.get(i).getActualCapacity() - segmentsToCheck.get(i).getTravelType().getMaxCap()) < shipment.getSize() || 
			   segmentsToCheck.get(i).getVehicle().getStatus().toString() != "RUNNING" ||
			   this.route.contains(segmentsToCheck.get(i))){
				System.out.println("Best First Find - Segment NOT VALID");
				//We cannot use this segment so remove it from the list
				segmentsToCheck.remove(i);
				i--;
			}//End of time, size and status restraint if
			else{
				System.out.println("Best First Find - Segment VALID");
			}
		}//End of time and capacity checking for loop
			
	return segmentsToCheck;
	}//End of ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck)
	
	
}//End of BestFirstFind class
