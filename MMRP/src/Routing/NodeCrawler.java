package Routing;
import java.util.ArrayList;
import MMRP.Vehicle;

public class NodeCrawler{

	/* How nodeCrawlerFirst Works
         * 1) Grab the segments connected to the start and end nodes
         * 2) While we arent connected to the end node do the following
         * 3) Pick a random segment to go down
         * 4) Check to see if we can now connect to the end node
         * 5) Add this segment to the path
         * 6) If we have reached the end node we are done, else repeat from 3
    */

	ArrayList<Segment> route;
	int currentTime;
	Shipment shipment;
	Random rand;
	boolean pathFound;
	int maxTries;
	int precentChanceOfDirectPath;
	
	public NodeCrawler(){
		route = new ArrayList<Segment>();
		metric = new WeightedMetric();
		currentTime = 0;
		rand =  new Random();
		pathFound = false;
		maxTries = 1000;
		precentChanceOfDirectPath = 50;
	}//End of NodeCrawler() default constructor
	
	public NodeCrawler(Vehicle.TravelTypes travelMode, Shipment shipment){
		route = new ArrayList<Segment>();
		rand =  new Random();
		this.mode = travelMode;
		this.shipment = shipment;
		pathFound = false;
		maxTries = 1000;
		precentChanceOfDirectPath = 50;
	}//End of NodeCrawler() 3-argument constructor
	
	public NodeCrawler(Vehicle.TravelTypes travelMode, Shipment shipment, int chanceOfDirectPath, int maximumTries){
		route = new ArrayList<Segment>();
		rand =  new Random();
		this.mode = travelMode;
		this.shipment = shipment;
		pathFound = false;
		maxTries = maximumTries;
		precentChanceOfDirectPath = chanceOfDirectPath;
	}//End of NodeCrawler() 5-argument constructor
	
	public ArrayList<Segment> getPath(){
		//First check to see if we have a direct path between the start and end point
		ArrayList<Segment> route =  new ArrayList<Segment>();
		ArrayList<Segment> paths = new ArrayList<Segment>();
		
		int currentLocationID = shipment.getFromLocationID();
		
		//We will enter a while loop until we find a path
		int tries = 0;
		while(tries < maxTries && !pathFound){
			//Attempt to find a direct path from the currentLocation to the end giving a percent chance passed in
			if(rand.nextInt % 100 < precentChanceOfDirectPath){
				//We will attempt to find a directPath between this location and the end location
				paths = validPaths(grabSegmentsBetween(currentLocationID, shipment.getToLocationID()));
				if(paths.size() > 0){
					//Choose a random path to travel along
					nextSegment = paths.get(rand.nextInt() * paths.size());
					route.add(nextSegment);
					pathFound = true;
				}//End of direct path if
			}else{
				//Grab all the Segments starting at this location
				paths = validPaths(grabSegmentsStartingAt(currentLocationID));
				if(paths.size  > 0){
					//We have valid paths we can use
					Segment nextSegment;
					//Choose a random path to travel along
					nextSegment = paths.get(rand.nextInt() * paths.size());
					route.add(nextSegment);
					currentLocationID = nextSegment.getEndLocationID();
					
					}//End of not-best route path
				}else{
					//We have no valid path from this point so we must rewind the path
					if(!rewindPath(route)){
						//we could not rewind the path, therefore we could not find a path
						tries = maxTries;
					}//End of unsuccessful path rewinding if
				}//End of no valid paths else
				
				if(currentLocationID == shipment.getToLocationID()){
					//We have found a working path
					pathFound = true;
				}else{
					//Increment the number of tries
					tries++;
				}
			}//End of non-directPath else
		}//End of path finding while loop
		
		//Check to see if we exited because we succeeded or because we failed
		if(!pathFound){
			//We failed
			route.empty();
		}//End of failure if
			
		return route;
		
	}//End of getPath()

	public ArrayList<Segment> grabSegmentsStartingAt(int startID){
		ArrayList<Segment> startSegments=Segment.LoadAll("Where StartingLocation = '"+ startID);
		startSegments = validPaths(startSegments);
		return startSegments;
	}//End of grabSegmentsStartingAt(Location start)
	
	public ArrayList<Segment> grabSegmentsBetween(Location start, Location end){
		ArrayList<Segment> directPath=Segment.LoadAll("Where StartingLocation = '"+ snid +"' AND EndingLocation ='" + enid +"';");
		directPath = validPaths(directPath);
		return directPath;
	}//End of ArrayList<Segment> grabSegmentsBetween(Location start, Location end)
	
	public ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck){
		//We need to check to see if the vehicle is available at the location
		//and if it has any capacity left to carry this shipment and if it is running and if it is the correct vehicle type
		for(int i = 0; i < segmentsToCheck.size(); i++){
			if(segmentsToCheck.get(i).getDepartureTime() < currentTime || segmentsToCheck.get(i).getVehicle().capacity < shipment.capacity || 
				segmentsToCheck.get(i).getVehicle().getStatus() != "RUNNING"){
				//We cannot use this segment so remove it from the list
				segmentsToCheck.remove(i);
			}//End of time, size and status restraint if
		}//End of time and capacity checking for loop
			
	return segmentsToCheck;
	}//End of ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck)
	
	public boolean rewindPath(ArrayList<Segment> rewindPath){
		if(rewindPath.size > 1){
			//We can rewind the path
			rewindPath.remove(rewindPath.size()-1);
			currentLocationID = rewindPath.get(rewindPath.size()-1).getEndLocationID();
			return true;
		}else{
			//We cannot rewind the path
			rewindPath.empty();
			return false;
		}
	}//End of rewindPath(ArrayList<Segment> rewindPath)
	
}//End of NodeCrawler class
