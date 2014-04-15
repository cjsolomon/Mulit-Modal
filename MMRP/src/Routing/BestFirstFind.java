package Routing;
import java.util.ArrayList;
import core.Segment;
import core.Shipment;
import core.Location;

public class BestFirstFind{

    /* How bestFirstFind Works
         * 1) Grab the segments connected to the start and end nodes
         * 2) While we arent connected to the end node do the following
         * 3) Pick the best segment, using the metric to go down
         * 4) Check to see if we can now connect to the end node
         * 5) Add this segment to the path
         * 6) If we have reached the end node we are done, else repeat from 3
         */
	ArrayList<Segment> route;
	WeightedMetric metric;
	int currentTime;
	Shipment shipment;
	boolean pathFound;
	int maxTries;
	
	public BestFirstFind(){
		route = new ArrayList<Segment>();
		metric = new WeightedMetric();
		currentTime = 0;
		pathFound = false;
		maxTries = 10;
	}//End of BestFirstFind() default constructor
	
	public BestFirstFind(WeightedMetric metric, Shipment shipment){
		route = new ArrayList<Segment>();
		this.metric = metric;
		this.shipment = shipment;
		pathFound = false;
		maxTries = 10;
	}//End of BestFirstFind() 3-argument constructor
	
	public BestFirstFind(WeightedMetric metric, Shipment shipment, int maximumTries){
		route = new ArrayList<Segment>();
		this.metric = metric;
		this.shipment = shipment;
		pathFound = false;
		maxTries = maximumTries;
	}//End of BestFirstFind() 4-argument constructor
	
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
				//Attempt to find a direct path from the currentLocation to the end using the best path possible
				paths = validPaths(grabSegmentsBetween(Location.Load(currentLocationID), shipment.loadEndLocation()));
				if(paths.size() > 0){
					//We have a direct path from the start to the finish so choose the best one and return it
					route.add(metric.getLowestWeightedCostSegment(paths));
					//Set the currentLocationID to the end of the path
					currentLocationID = route.get(route.size()-1).getEndLocationID();
					pathFound = true;
				}//End of direct path if
				else{
					//Grab all the Segments starting at this location
					paths = validPaths(grabSegmentsStartingAt(currentLocationID));
					if(paths.size()  > 0){
						//We have valid paths we can use
						Segment nextSegment;
						//Choose the best route to travel along
						nextSegment = metric.getLowestWeightedCostSegment(paths);
						route.add(nextSegment);
						currentLocationID = nextSegment.getEndLocationID();
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
	
	public ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck){
		//We need to check to see if the vehicle is available at the location
		//and if it has any capacity left to carry this shipment and if it is running and if it is the correct vehicle type
		for(int i = 0; i < segmentsToCheck.size(); i++){
			//NEED MORE REAL DATA TO CHECK THE PATH BETTER
			//if(segmentsToCheck.get(i).getEstimatedDepartureTime() < currentTime || segmentsToCheck.get(i).getTravelType().getActCap() < shipment.getSize() || 
			//	segmentsToCheck.get(i).getVehicle().getStatus() != "RUNNING"){
				//We cannot use this segment so remove it from the list
				//segmentsToCheck.remove(i);
			//}//End of time, size and status restraint if
		}//End of time and capacity checking for loop
			
	return segmentsToCheck;
	}//End of ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck)
	
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
	
}//End of BestFirstFind class
