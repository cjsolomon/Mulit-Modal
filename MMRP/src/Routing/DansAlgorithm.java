package Routing;

import java.util.ArrayList;

import core.Segment;
import core.Shipment;

public class DansAlgorithm extends RoutingAlgorithm {

	static int MAXITERATIONS = 100;
	static double MINTOLERANCE = 0.01;
	Shipment shipment;
	RoutingAlgorithm startingAlgorithm;
	RoutingAlgorithm updatingAlgorithm;
	int currentLocation;
	ArrayList<Segment> bestRoute;
	ArrayList<Segment> currentRoute;
	int iterations;
	double tolerance;

	public DansAlgorithm(Shipment shipment, RoutingAlgorithm startingAlgorithm, RoutingAlgorithm updatingAlgorithm){

		this.shipment = shipment;
		this.startingAlgorithm = startingAlgorithm;
		this.updatingAlgorithm = updatingAlgorithm;

		bestRoute = new ArrayList<Segment>();
		currentRoute = new ArrayList<Segment>();
		iterations =0;
		tolerance = 1000;

	}


	public ArrayList<Segment> getPath(){

		//Grab the starting solution
		bestRoute = startingAlgorithm.getPath();
		currentRoute = new ArrayList<Segment>(bestRoute);
		int currentSegment = 0;
		while(iterations < MAXITERATIONS && tolerance > MINTOLERANCE && currentSegment < bestRoute.size()){
			
			//Get a new route starting at the next location on the best route
			//To do this we need to update the Shipments current location to the end location of the segment we
			//are starting at
			shipment.setCurrentLocation(bestRoute.get(currentSegment).getEndLocationID());
			
			//iterate the current segment to the next location on the bestRoute
			currentSegment++;
			
			//Get the new route from the new location
			currentRoute.clear();
			//Add all the bestRoute paths up to this locaiton
			for(int i =0; i < currentSegment; i++){
				currentRoute.add(bestRoute.get(i));
			}
			currentRoute.addAll(updatingAlgorithm.getPath());
			
			if(updatingAlgorithm.getTotalRouteCost(currentRoute) < updatingAlgorithm.getTotalRouteCost(bestRoute)){
				//We have found a better route so set the bestRoute to the current route
				bestRoute = new ArrayList<Segment>(currentRoute);
			}
			
		}//End of while loop
		
		
		return bestRoute;
	}//End of getPath()
	
	public ArrayList<Segment> validPaths(ArrayList<Segment> segmentsToCheck){
		//Just need to implement this because it a required by the RoutingAlgorithm parent class
		//Since this algorithm only deals with completed routes all the error checking for valid 
		//paths will already be taken care of.
		return segmentsToCheck;
	}

}
