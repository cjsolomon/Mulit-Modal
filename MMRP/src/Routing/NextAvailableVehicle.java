package Routing;

import java.util.ArrayList;
import core.Vehicle;
import core.Segment;
import core.Shipment;
import core.Location;

public class NextAvailableVehicle{

	ArrayList<Segment> route;
	Vehicle.TravelModes mode;
	WeightedMetric metric;
	int currentTime;
	Shipment shipment;
	boolean pathFound;
	int percentChanceOfDirectPath;
	int maxTries;
	
	public NextAvailableVehicle(){
		route = new ArrayList<Segment>();
		mode = Vehicle.TravelModes.TRUCK;
		metric = new WeightedMetric();
		currentTime = 0;
		pathFound = false;
		percentChanceOfDirectPath = 30;
		maxTries = 10;
	}//End of NextAvailableVehicle() default constructor
	
	public NextAvailableVehicle(Vehicle.TravelModes travelMode, WeightedMetric metric, Shipment shipment){
		route = new ArrayList<Segment>();
		this.mode = travelMode;
		this.metric = metric;
		this.shipment = shipment;
		pathFound = false;
		percentChanceOfDirectPath = 30;
		maxTries = 10;
	}//End of NextAvailableVehicle() 3-argument constructor
	
	public NextAvailableVehicle(Vehicle.TravelModes travelMode, WeightedMetric metric, Shipment shipment, int chanceOfDirectPath, int maximumTries){
		route = new ArrayList<Segment>();
		this.mode = travelMode;
		this.metric = metric;
		this.shipment = shipment;
		pathFound = false;
		percentChanceOfDirectPath = chanceOfDirectPath;
		maxTries = maximumTries;
	}//End of NextAvailableVehicle() 6-argument constructor
	
	public ArrayList<Segment> getPath(){
		//First check to see if we have a direct path between the start and end point
		ArrayList<Segment> route =  new ArrayList<Segment>();
		ArrayList<Segment> paths = validPaths(grabSegmentsBetween(shipment.loadStartLocation(), shipment.loadEndLocation()));
		
		int currentLocationID = shipment.getFromLocationID();
		
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
				//Attempt to find a direct path from the currentLocation to the end giving a percent chance passed in
				if(Math.floor(Math.random() * 100) < percentChanceOfDirectPath){
					//We will attempt to find a directPath between this location and the end location
					paths = validPaths(grabSegmentsBetween(Location.Load(currentLocationID), shipment.loadEndLocation()));
					if(paths.size() > 0){
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
					//Grab all the Segments starting at this location
					paths = validPaths(grabSegmentsStartingAt(currentLocationID));
					if(paths.size()  > 0){
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
		//for(int i = 0; i < segmentsToCheck.size(); i++){
		//	if(segmentsToCheck.get(i).getEstimatedDepartureTime() < currentTime || segmentsToCheck.get(i).getTravelType().getActCap() < shipment.getSize() || 
		//		segmentsToCheck.get(i).getVehicle().getStatus() != "RUNNING" || segmentsToCheck.get(i).getTravelMode() != mode.toString()){
		//		//We cannot use this segment so remove it from the list
		//		segmentsToCheck.remove(i);
		//	}//End of time, size and status restraint if
		//}//End of time and capacity checking for loop
			
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
	
}//End of TravelByType class
