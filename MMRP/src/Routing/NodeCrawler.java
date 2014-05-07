package Routing;

import java.util.ArrayList;
import core.Segment;
import core.Location;
import core.Shipment;

public class NodeCrawler extends RoutingAlgorithm{

	/* How nodeCrawlerFirst Works
         * 1) Grab the segments connected to the start and end nodes
         * 2) While we arent connected to the end node do the following
         * 3) Pick a random segment to go down
         * 4) Check to see if we can now connect to the end node
         * 5) Add this segment to the path
         * 6) If we have reached the end node we are done, else repeat from 3
    */

	int precentChanceOfDirectPath;
	
	/**
	 * This is the default NodeCrawler constructor
	 * <p>The maximum number of attempts to find a path are set to 10
	 * <p>The percent chance of trying a direct path is set to 50
	 */
	public NodeCrawler(){
		route = new ArrayList<Segment>();
		currentTime = 0;
		pathFound = false;
		maxTries = 10;
		precentChanceOfDirectPath = 50;
		metric = new WeightedMetric(1,1,1);
	}//End of NodeCrawler() default constructor
	
	/**
	 * This is the 1 argument NodeCrawler constructor
	 * @param shipment This is the Shipment that is being routed
	 * <p>The maximum number of attempts to find a path are set to 10
	 * <p>The percent chance of trying a direct path is set to 50
	 */
	public NodeCrawler(Shipment shipment){
		route = new ArrayList<Segment>();
		this.shipment = shipment;
		pathFound = false;
		maxTries = 10;
		precentChanceOfDirectPath = 50;
		metric = new WeightedMetric(1,1,1);
	}//End of NodeCrawler() 3-argument constructor
	
	/**
	 * This is the 3 argument NodeCrawler constructor
	 * @param shipment This is the Shipment that is being routed
	 * @param chanceOfDirectPath
	 * @param maximumTries
	 */
	public NodeCrawler(Shipment shipment, int chanceOfDirectPath, int maximumTries){
		route = new ArrayList<Segment>();
		this.shipment = shipment;
		pathFound = false;
		maxTries = maximumTries;
		precentChanceOfDirectPath = chanceOfDirectPath;
		metric = new WeightedMetric(1,1,1);
	}//End of NodeCrawler() 5-argument constructor
	
	/**
	 * This is the getPath function, it will return a path from the start location to the end location or
	 * a null path if it fails.
	 * <p>It will find a path based on the following steps :
	 * <p>1) If the percent chance is greater than the chance of a direct path attempt a direct path
	 * <p>2) If a direct path exists randomly choose one
	 * <p>3) If we did not choose a direct path or one doesnt exist randomly pick a connecting Segment
	 * <p>4) Repeat from 1
     */
	@Override
	public ArrayList<Segment> getPath(){
		//First check to see if we have a direct path between the start and end point
		ArrayList<Segment> route =  new ArrayList<Segment>();
		ArrayList<Segment> paths = new ArrayList<Segment>();
		
		int currentLocationID = shipment.getCurrentLocationID();
		
		//We will enter a while loop until we find a path
		int tries = 0;
		while(tries < maxTries && !pathFound){
			System.out.println("Node Crawler - Current Attempt : " + (tries+1) + " of " + maxTries);
			//Attempt to find a direct path from the currentLocation to the end giving a percent chance passed in
			if(Math.floor(Math.random() * 100) < precentChanceOfDirectPath){
				System.out.println("Node Crawler - Attempting Direct Path");
				//We will attempt to find a directPath between this location and the end location
				paths = validPaths(grabSegmentsBetween(Location.Load(currentLocationID), shipment.loadEndLocation()));
				if(paths.size() > 0){
					System.out.println("Node Crawler - Direct Path found!");
					//Choose a random path to travel along
					Segment nextSegment = paths.get((int)Math.floor(Math.random() * paths.size()));
					route.add(nextSegment);
					currentLocationID = nextSegment.getEndLocationID();
				}//End of direct path if
			}else{
				System.out.println("Node Crawler - Grabbing random segment");
				//Grab all the Segments starting at this location
				paths = validPaths(grabSegmentsStartingAt(currentLocationID));
				if(paths.size()  > 0){
					System.out.println("Node Crawler - Adding new Segment");
					//We have valid paths we can use
					Segment nextSegment;
					//Choose a random path to travel along
					nextSegment = paths.get((int)Math.floor(Math.random() * paths.size()));
					route.add(nextSegment);
					currentLocationID = nextSegment.getEndLocationID();
					
					}//End of not-best route path
				else{
					System.out.println("Node Crawler - No Path found, rewinding");
					//We have no valid path from this point so we must rewind the path
					if(!rewindPath(route)){
						System.out.println("Node Crawler - Could not rewind, no path found" );
						//we could not rewind the path, therefore we could not find a path
						tries = maxTries;
						continue;
					}//End of unsuccessful path rewinding if
					else{
						//Set the currentLocationID to the end of the path
						currentLocationID = route.get(route.size()-1).getEndLocationID();
					}
				}//End of no valid paths else
			}//End of non-directPath else
				
			if(currentLocationID == shipment.getToLocationID()){
				System.out.println("Node Crawler - Arrived at end location");
				//We have found a working path
				pathFound = true;
			}else{
				//Increment the number of tries
				tries++;
			}
		}//End of path finding while loop
		
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
		System.out.println("Node Crawler - Validating Paths");
		//We need to check to see if the vehicle is available at the location
		//and if it has any capacity left to carry this shipment and if it is running and if it is the correct vehicle type
		for(int i = 0; i < segmentsToCheck.size(); i++){
			System.out.println("Node Crawler - Checking Segment : " + (i+1) + " of " + segmentsToCheck.size());
			if(segmentsToCheck.get(i).getEstimatedDepartureTime() < currentTime || 
				Math.abs(segmentsToCheck.get(i).getActualCapacity() - segmentsToCheck.get(i).getTravelType().getMaxCap()) < shipment.getSize() || 
				segmentsToCheck.get(i).getVehicle().getStatus().toString() != "RUNNING" ||
				this.route.contains(segmentsToCheck.get(i))){
				//We cannot use this segment so remove it from the list
				System.out.println("Node Crawler - NOT VALID");
				segmentsToCheck.remove(i);
				i--;
			}//End of time, size and status restraint if
			else{
				System.out.println("Node Crawler - VALID");
			}
		}//End of time and capacity checking for loop
			
	return segmentsToCheck;
	}//End of ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck)
	
	
}//End of NodeCrawler class
