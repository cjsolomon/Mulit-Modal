package core;

import java.util.ArrayList;

import Routing.WeightedMetric;
import core.Vehicle;
import Routing.BestFirstFind;
import Routing.NodeCrawler;
import java.io.*;
public class Test extends BaseClass 
{
	public Test()
	{
	}
	
	public Test Load(){return null;}
	public boolean Update(){return true;};
	public boolean Delete(){return true;};
	public static double  distanceCalculator(Location l1, Location l2)
	{
		double R = 6371;
		double theta1 = Math.toDegrees(l1.getLatitude());
		double theta2 = Math.toDegrees(l2.getLatitude());
		double deltaTheta = Math.toDegrees(l1.getLatitude() - l2.getLatitude());
		double deltaPhi = Math.toDegrees(l1.getLongitude() - l2.getLongitude());
		
		double a = 	Math.sin(deltaTheta/2) *  Math.sin(deltaTheta/2) +
					Math.cos(theta1) *  Math.cos(theta2) *
					Math.sin(deltaPhi /2) * Math.sin(deltaPhi/2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return R * c;
					
	}
	
	public static void vehicleGenerator(ArrayList<Location> LocationsToUse, ArrayList<Carrier> carriersToUse){
	
		
		//Grab a random Carrier
		int carrierID = carriersToUse.get((int)(Math.random() * carriersToUse.size())).getId();
		
		//Generate how many segments this vehicle will travel along
		int numberOfSegments = (int)(Math.random() * 10) + 1;
		
		//Generate a random number to determine the vehicle type
		int rand = (int)(Math.random() * 4);
		//default travelType id
		int travelTypeID  = 10000;
		Vehicle.TravelModes mode = Vehicle.TravelModes.TRUCK;
				
		//This is the travel Speed
		double speed = 5;
		
		//This is the shipping rate ID
		int shippingRateID = 1;
		
		//This is the name of the vehicle
		String name = "";

		
		//This is the new Vehicle
		Vehicle newVehicle = new Truck();
		
		switch(rand){
				
		case 0:
			mode = Vehicle.TravelModes.TRUCK;
			//Grab a random Truck Type
			travelTypeID = (int)(Math.random() * 6 + 10000);
			speed = 5;
			//Now depending on the type of travel type and the carrier we
			//may have to create a new Shipping Rate.
			//as a general rule of thumb, the more spacious, ie the higher the 
			//id number, the more expensive it will be
			shippingRateID = generateShippingRate(carrierID, (double)((travelTypeID -10000) * 5));
			name = "Truck";
			Truck newTruck = new Truck();
			newTruck.setTravelMode(Vehicle.TravelModes.TRUCK);
			newVehicle = newTruck;
			break;
		case 1:
			mode = Vehicle.TravelModes.CARGO;
			//Grab a random Cargo Type
			travelTypeID = (int)(Math.random() * 6 + 10006);
			speed = 1;
			//Now depending on the type of travel type and the carrier we
			//may have to create a new Shipping Rate.
			//as a general rule of thumb, the more spacious, ie the higher the 
			//id number, the more expensive it will be
			shippingRateID = generateShippingRate(carrierID, (double)((travelTypeID -10000) * 1));
			name = "Cargo";
			Cargo newCargo = new Cargo();
			newCargo.setTravelMode(Vehicle.TravelModes.CARGO);
			newVehicle = newCargo;
			break;
		case 2:
			mode = Vehicle.TravelModes.PLANE;
			//Grab a random Plane Type
			travelTypeID = (int)(Math.random() * 4 + 10012);
			speed = 40;
			//Now depending on the type of travel type and the carrier we
			//may have to create a new Shipping Rate.
			//as a general rule of thumb, the more spacious, ie the higher the 
			//id number, the more expensive it will be
			shippingRateID = generateShippingRate(carrierID, (double)((travelTypeID -10000) * 15));
			name = "Plane";
			Plane newPlane = new Plane();
			newPlane.setTravelMode(Vehicle.TravelModes.PLANE);
			newVehicle = newPlane;
			break;
		case 3:
			mode = Vehicle.TravelModes.RAIL;
			//Grab a random Rail Type
			travelTypeID = (int)(Math.random() * 5 + 10016);
			speed= 3;
			//Now depending on the type of travel type and the carrier we
			//may have to create a new Shipping Rate.
			//as a general rule of thumb, the more spacious, ie the higher the 
			//id number, the more expensive it will be
			shippingRateID = generateShippingRate(carrierID, (double)((travelTypeID -10000) * 3));
			name = "Rail";
			Rail newRail = new Rail();
			newRail.setTravelMode(Vehicle.TravelModes.RAIL);
			newVehicle = newRail;
			break;
		} 
		
		newVehicle.setCarrier(Carrier.Load(carrierID));
		newVehicle.setStatus("RUNNING");
		newVehicle.setVehicleName(name + (int)(Math.random() * 100000));
		newVehicle.Update();
		
		
		
		//Times off the start are 0
		int timeOfDeparture = 0;
		int timeOfArrival = 10000;
		
		//Unchanging times
		int earliestDeparture = 0;
		int latestDeparture = 0;
		int latestArrival = 10000;
		int earliestArrival = 10000;
		
		//The current distance
		double distance = 0;
		
		Location startLocation = LocationsToUse.get((int)(Math.random() * LocationsToUse.size()));;
		//Make sure this is a valid starting location
		
		while(!startLocation.getTravelModes().contains(mode))
			startLocation = LocationsToUse.get((int)(Math.random() * LocationsToUse.size()));
		Location endLocation = startLocation;
		int iteration = 0;
		Location currentLocation = startLocation;
		
		//Generate each segment
		while(iteration < numberOfSegments){
		
		//Choose an endLocation that is not the startLocation
		while(endLocation.getID() == startLocation.getID() || !endLocation.getTravelModes().contains(mode)){
			endLocation = LocationsToUse.get((int)(Math.random() * LocationsToUse.size()));
		}
		
		//Get the distance between the start and end location
		distance = distanceCalculator(startLocation, endLocation)/10;
		
		//Calculate the time using the speed
		timeOfArrival = (int)(distance/speed) + timeOfDeparture;
		
		//Generic times
		earliestArrival = timeOfArrival;
		latestArrival = timeOfArrival;
		
		//push the new segment into the database
		Segment newSegment = new Segment();
		newSegment.setActualCapacity(0);
		newSegment.setDistance(distance);
		newSegment.setEarliestDepartureTime(timeOfDeparture);
		newSegment.setEstimatedDepartureTime(timeOfDeparture);
		newSegment.setEarliestArrivalTime(timeOfDeparture);
		newSegment.setEndLocation(endLocation);
		newSegment.setEarliestArrivalTime(timeOfArrival);
		newSegment.setEstimatedArrivalTime(timeOfArrival);
		newSegment.setLatestArrivalTime(timeOfArrival);
		newSegment.setLane("ANY");
		newSegment.setMode(mode.toString());
		newSegment.setShippingRate(ShippingRate.Load(shippingRateID));
		newSegment.setStartLocation(startLocation);
		newSegment.setTravelType(TravelType.Load(travelTypeID));
		newSegment.setVehicle(newVehicle);
		newSegment.Update();
		
		
		//Update the time of departure
		timeOfDeparture = timeOfArrival + 5;
		earliestDeparture = timeOfDeparture;
		latestDeparture = timeOfDeparture;
		
		//Update the startLocation
		startLocation = endLocation;
		iteration++;
		
		}//End of segment generation
	}
	
	public static int generateShippingRate(int carrierID, double flatRate){
		//Check to see if the shipping rate exists, if it does, use it, if it doesnt
		//create it
		ArrayList<ShippingRate> shippingRateExists = ShippingRate.LoadAll("where CarrierID = '" +carrierID + "' AND FlatRate ='" +flatRate+ "'");
		if(shippingRateExists.size() > 0){
			return shippingRateExists.get(0).getId();
		}else{
			ShippingRate SR = new ShippingRate();
			SR.setCarrier(Carrier.Load(carrierID));
			SR.setFlatRate(flatRate);
			SR.setMileRate(2);
			SR.setRank(1);
			SR.setRate1(10);
			SR.setRate2(20);
			SR.setRate3(30);
			SR.setWeight1(10);
			SR.setWeight2(20);
			SR.setWeight3(30);
			SR.Update();
			return SR.getId();
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		
		//Grab all the real Locations
		//ArrayList<Location> allLocations = new ArrayList<Location>();
		//allLocations = Location.LoadAll("where LocationID < 272");
				
		//Grab all the real Carriers
		//ArrayList<Carrier> allCarriers = new ArrayList<Carrier>();
		//allCarriers = Carrier.LoadAll("where CarrierID < 709");
		
		//for(int i = 0; i < 100; i++){
		//	vehicleGenerator(allLocations, allCarriers);
		//}
		
		//BaseClass.purgeDeleted();
		//
		//ArrayList<Shipment> shipments = Shipment.LoadAll("where ShipmentID > 7999 AND ShipmentID < 9015");
		//System.out.println("The number of Shipments loaded are : " + shipments.size());
	
		
		
	//	for(int i = 0; i< shipments.size(); i++)	
	//	{
		//	System.out.println("Starting " + (i+1));
			/*System.out.println("Doing A Star Search");
			Routing.AStarAlg alg = new Routing.AStarAlg(shipments.get(i), new WeightedMetric(1,0,0));
			ArrayList<Segment> route = alg.getPath();
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route);*/
			
			/*System.out.println("Doing NodeCrawler");
			Routing.NodeCrawler nodeCrawler = new Routing.NodeCrawler(shipments.get(i), 50, 10);
			ArrayList<Segment> route2 =  nodeCrawler.getPath();
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route2);*/
			
			/*Routing.BestFirstFind bestFind = new Routing.BestFirstFind(new WeightedMetric(1,1,1), shipments.get(i));
			ArrayList<Segment> route3 =  bestFind.getPath();
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route3);*/
			
			/*Routing.TravelByType travelBT =  new Routing.TravelByType(Vehicle.TravelModes.TRUCK, new WeightedMetric(1,1,1), shipments.get(i));
			ArrayList<Segment> route4 =  travelBT.getPath();
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route4);*/
			
			/*Routing.NextAvailableVehicle NAV =  new Routing.NextAvailableVehicle(Vehicle.TravelModes.TRUCK, new WeightedMetric(1,1,1), shipments.get(i));
			ArrayList<Segment> route5 =  NAV.getPath();
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route5);*/
			
		//	System.out.println("Done with " + (i+1));
	//	}
	//	printSolution(shipments);
		//printShipmentInfoToFile();
		//printVehicleInfo();
		//printVehicleRoute();
		//File f = new File(".././Output/Vehicles.txt");
		//File f2 = new File(".././Output/Shipments.txt");
		//File f2 = new File("../.Output/ShipmentInfo.txt");
		// w=null;
		//Writer w2=null;
		//try
		//{
		
		//	if(!f.exists()) {
			//	f.createNewFile();
			//}
			//if(!f2.exists()) {
			////	f2.createNewFile();
			//}
		//	w=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			//w2=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f2)));
		//}
		//catch(Exception ex)
		//{
		//	System.out.println("Error " + ex);
		//}
		
		
		

	//}

		/*public static void printVehicleRoute() throws IOException
		{
			File f = new File("Vehicles.txt");
			Writer w = null;
			try
			{
				if(!f.exists())
					f.createNewFile();
				w=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			}
			catch(Exception ex)
			{
				System.out.println("Error "+ex);
			}
			
			w.write("\t\t\t\t\tTRUCKS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Truck> veh = Truck.LoadAll("");
			for(int i = 0; i<veh.size();i++)
			{
				Truck t = veh.get(i);
				w.write("\n"+t.getVehicleName()+"\n\n");
				ArrayList<Segment> segs =t.getSchedule();
				ArrayList<Shipment> old=new ArrayList<Shipment>();
				ArrayList<Shipment> newShips=new ArrayList<Shipment>();
				for(int j=0;j<segs.size();j++)
				{
					Segment s = segs.get(j);
					ArrayList<Shipment> temp = newShips;
					old=temp;
					newShips=s.onBoard;
					
					Location start = s.getStartLocation();
					Location end = s.getEndLocation();
					
					w.write("\tFrom " +s.getStartLocation().getName());// + " to " + s.getEndLocation().getName()+ "\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity()+"\n");
					if(start.getState()==null || start.getState()=="")
					{
						w.write(","+start.getCountry());
					}
					else
					{
						w.write(","+start.getState());
					}
					w.write("   To   " + end.getName());
					
					if(end.getState()==null || end.getState()=="")
					{
						w.write(","+end.getCountry());
					}
					else
					{
						w.write(","+end.getState());
					}
					w.write("\n\t\tCapacity: " + s.getTravelType().getActCap()+"\tActual: " + s.estimateCapacity());
					//getPickedUp shipments
					for(int n = 0; n<newShips.size();n++)
					{
						boolean exists=false;
						for(int o =0; o<old.size();o++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tPicked Up Shipment " + newShips.get(n).getId());
					}
					//get drop off
					for(int o = 0; o<old.size();o++)
					{
						boolean exists=false;
						for(int n =0; n<newShips.size();n++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tDropped off Shipment " + old.get(o).getId());
					}
					w.write("\n\n");
				}
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tPLANES\n----------------------------------------------------------------\n\n\n");
			ArrayList<Plane> veh1 = Plane.LoadAll("");
			for(int i = 0; i<veh1.size();i++)
			{
				Plane t = veh1.get(i);
				w.write("\n"+t.getVehicleName()+"\n\n");
				ArrayList<Segment> segs =t.getSchedule();
				ArrayList<Shipment> old=new ArrayList<Shipment>();
				ArrayList<Shipment> newShips=new ArrayList<Shipment>();
				for(int j=0;j<segs.size();j++)
				{
					Segment s = segs.get(j);
					ArrayList<Shipment> temp = newShips;
					old=temp;
					newShips=s.onBoard;
					Location start = s.getStartLocation();
					Location end = s.getEndLocation();
					
					w.write("\tFrom " +s.getStartLocation().getName());// + " to " + s.getEndLocation().getName()+ "\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity()+"\n");
					if(start.getState()==null || start.getState()=="")
					{
						w.write(","+start.getCountry());
					}
					else
					{
						w.write(","+start.getState());
					}
					w.write("   To   " + end.getName());
					
					if(end.getState()==null || end.getState()=="")
					{
						w.write(","+end.getCountry());
					}
					else
					{
						w.write(","+end.getState());
					}
					w.write("\n\t\tCapacity: " + s.getTravelType().getActCap()+"\tActual: " + s.estimateCapacity());
					//getPickedUp shipments
					for(int n = 0; n<newShips.size();n++)
					{
						boolean exists=false;
						for(int o =0; o<old.size();o++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tPicked Up Shipment " + newShips.get(n).getId());
					}
					//get drop off
					for(int o = 0; o<old.size();o++)
					{
						boolean exists=false;
						for(int n =0; n<newShips.size();n++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tDropped off Shipment " + old.get(o).getId());
					}
					w.write("\n\n");
				}
			}
			w.write("\n\n\n\n\n\n");
			w.flush();
			w.write("\t\t\t\t\tTRAINS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Rail> veh2 = Rail.LoadAll("");
			for(int i = 0; i<veh2.size();i++)
			{
				Rail t = veh2.get(i);
				w.write("\n"+t.getVehicleName()+"\n\n");
				ArrayList<Segment> segs =t.getSchedule();
				
				ArrayList<Shipment> old=new ArrayList<Shipment>();
				ArrayList<Shipment> newShips=new ArrayList<Shipment>();
				for(int j=0;j<segs.size();j++)
				{
					Segment s = segs.get(j);
					ArrayList<Shipment> temp = newShips;
					old=temp;
					newShips=s.onBoard;
					Location start = s.getStartLocation();
					Location end = s.getEndLocation();
					
					w.write("\tFrom " +s.getStartLocation().getName());// + " to " + s.getEndLocation().getName()+ "\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity()+"\n");
					if(start.getState()==null || start.getState()=="")
					{
						w.write(","+start.getCountry());
					}
					else
					{
						w.write(","+start.getState());
					}
					w.write("   To   " + end.getName());
					
					if(end.getState()==null || end.getState()=="")
					{
						w.write(","+end.getCountry());
					}
					else
					{
						w.write(","+end.getState());
					}
					w.write("\n\t\tCapacity: " + s.getTravelType().getActCap()+"\tActual: " + s.estimateCapacity());
					//getPickedUp shipments
					for(int n = 0; n<newShips.size();n++)
					{
						boolean exists=false;
						for(int o =0; o<old.size();o++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tPicked Up Shipment " + newShips.get(n).getId());
					}
					//get drop off
					for(int o = 0; o<old.size();o++)
					{
						boolean exists=false;
						for(int n =0; n<newShips.size();n++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tDropped off Shipment " + old.get(o).getId());
					}
					w.write("\n\n");
				}
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tCARGOSHIPS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Cargo> veh3 = Cargo.LoadAll("");
			for(int i = 0; i<veh3.size();i++)
			{
				Cargo t = veh3.get(i);
				w.write("\n"+t.getVehicleName()+"\n\n");
				ArrayList<Segment> segs =t.getSchedule();
				ArrayList<Shipment> old=new ArrayList<Shipment>();
				ArrayList<Shipment> newShips=new ArrayList<Shipment>();
				for(int j=0;j<segs.size();j++)
				{
					Segment s = segs.get(j);
					ArrayList<Shipment> temp = newShips;
					old=temp;
					newShips=s.onBoard;
					Location start = s.getStartLocation();
					Location end = s.getEndLocation();
					
					w.write("\tFrom " +s.getStartLocation().getName());// + " to " + s.getEndLocation().getName()+ "\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity()+"\n");
					if(start.getState()==null || start.getState()=="")
					{
						w.write(","+start.getCountry());
					}
					else
					{
						w.write(","+start.getState());
					}
					w.write("   To   " + end.getName());
					
					if(end.getState()==null || end.getState()=="")
					{
						w.write(","+end.getCountry());
					}
					else
					{
						w.write(","+end.getState());
					}
					w.write("\n\t\tCapacity: " + s.getTravelType().getActCap()+"\tActual: " + s.estimateCapacity());
					//getPickedUp shipments
					for(int n = 0; n<newShips.size();n++)
					{
						boolean exists=false;
						for(int o =0; o<old.size();o++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tPicked Up Shipment " + newShips.get(n).getId());
					}
					//get drop off
					for(int o = 0; o<old.size();o++)
					{
						boolean exists=false;
						for(int n =0; n<newShips.size();n++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tDropped off Shipment " + old.get(o).getId());
					}
					w.write("\n\n");
				}
			}
			w.flush();
		}*/
	}
		public static void printSolution(ArrayList<Shipment> shipments)throws IOException
		{
			File f = new File("ShipmentsNAVWDist.txt");
			Writer w2=null;
			try
			{
				if(!f.exists())
					f.createNewFile();
				w2=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			}
			catch(Exception ex)
			{
				System.out.println("Error " +ex);
			}
			
			double lowestTotalTime = 100000;
			double lowestTotalCost = 100000;
			double lowestTotalDistance = 100000;
			double highestTotalTime = 0;
			double highestTotalCost = 0;
			double highestTotalDistance = 0;
			double sumTime = 0;
			double sumCost = 0;
			double sumDistance = 0;
			for(int i = 0; i< shipments.size();i++)
			{
				String execute = "Shipment " + (i+1)+ "    From: " +Location.Load(shipments.get(i).getFromLocationID()).getName() + "    To: "+Location.Load(shipments.get(i).getToLocationID()).getName()+"\n";
				w2.write(execute); 
				//Routing.AStarAlg alg = new Routing.AStarAlg(shipments.get(i));
				
				//ArrayList<Segment> segs = alg.getPath();
				//shipments.get(i).setHistoryFromSegments(segs);
				//w2.write("Shipment " + shipments.get(i).getId()+"\t Earliest: " + shipments.get(i).getEarliestTime()+"\t Latest: "+ shipments.get(i).getLatestTime()+"\n");
				//w2.write("\t Start:" + Location.Load(shipments.get(i).getFromLocationID()).getName()+" End: "+Location.Load(shipments.get(i).getToLocationID()).getName()+"\n");
				ArrayList<ShipmentHistory>hist=shipments.get(i).getHistory();
				double totalTime = 0;
				double totalCost = 0;
				double totalDistance = 0;
				for(int j = 0; j<hist.size();j++)
				{
					Segment s = hist.get(j).getSegment();
					totalTime += (s.getEstimatedArrivalTime() - s.getEstimatedDepartureTime());
					totalCost += s.getShippingRate().getFlatRate();
					totalDistance += s.getDistance();
					Vehicle v =s.getVehicle();
					w2.write("\n\t" + s.getStartLocation().getName() + " to " + s.getEndLocation().getName() + " via " + v.getVehicleName());
					
				}
				
				sumTime += totalTime;
				sumCost += totalCost;
				sumDistance += totalDistance;
				if(hist.size() != 0){
					w2.write("\n\n\tArrival: " + hist.get(hist.size()-1).getSegment().getEstimatedArrivalTime());
					w2.write("\n\t Total Time : " + totalTime );
					w2.write("\n\t Total Cost : " + totalCost );
					w2.write("\n\t Total Distance : " + totalDistance );
					w2.write("\n\n\n\n");
				}
				else
					w2.write("No Route found.\n\n");
				
				if(totalTime < lowestTotalTime)
					lowestTotalTime =totalTime;
				if(totalCost < lowestTotalCost)
					lowestTotalCost = totalCost;
				if(totalDistance < lowestTotalDistance)
					lowestTotalDistance =totalDistance;
				if(totalTime > highestTotalTime)
					highestTotalTime =totalTime;
				if(totalCost > highestTotalCost)
					highestTotalCost = totalCost;
				if(totalDistance > highestTotalDistance)
					highestTotalDistance =totalDistance;
				//if(segs.size()!=0)
				//{
				//for(int j = 0; j<segs.size();j++)
					//w2.write("\t\tSegment id " + segs.get(j).getID()+ "\n\t\t\t"+segs.get(j).getStartLocation().getName()+"\t"+segs.get(j).getEndLocation().getName()+"\n");
				//w2.write("\tArrival: "+ segs.get(segs.size()-1).getArrivalTime()+"\n");
				//}
				System.out.println("Done with " + i);
			}
			
			
			w2.write("\n\n\n Average Time : " + (sumTime / shipments.size()));
			w2.write("\n Average Cost : " + (sumCost / shipments.size()));
			w2.write("\n Average Distance : " + (sumDistance / shipments.size()));
			
			w2.write("\n\n\n Best Time : " + lowestTotalTime);
			w2.write("\n Best Cost : " + lowestTotalCost);
			w2.write("\n Best Distance : " + lowestTotalDistance);
			w2.write("\n\n Worst Time : " + highestTotalTime);
			w2.write("\n Worst Cost : " + highestTotalCost);
			w2.write("\n Worst Distance : " + highestTotalDistance + "\n\n\n");
			
			w2.flush();
			
		}
		public static void printShipmentInfoToFile()throws IOException
		{
			File f = new File("ShipmentInfo.txt");
			Writer w=null;
			try
			{
				if(!f.exists())
					f.createNewFile();
				w=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			}
			catch(Exception ex)
			{
				System.out.println("Error " +ex);
			}
			
			w.write("\t\t\t\t\tShipment Information");
			w.write("\n\n");
			//w.write("ID\tFrom\tTo\tSize\tPriority\tDeparture\tEarliest\tLatest");
			w.write("\n-----------------------------------------------------------------");
			ArrayList<Shipment> s = Shipment.LoadAll("");
			for(int i = 0;i<s.size();i++)
			{
				Shipment temp = s.get(i);
				Location start = Location.Load(temp.getFromLocationID());
				Location end = Location.Load(temp.getToLocationID());
				w.write("\nShipmentID: "+temp.getId());//+"\t"+start.getName());
				w.write("\n\tFrom: " + start.getName());
				if(start.getState()==null || start.getState()=="")
				{
					w.write(","+start.getCountry());
				}
				else
				{
					w.write(","+start.getState());
				}
				w.write("\n\tTo: " + end.getName());
				
				if(end.getState()==null || end.getState()=="")
				{
					w.write(","+end.getCountry());
				}
				else
				{
					w.write(","+end.getState());
				}
				w.write("\n\tSize: "+temp.getSize()+"\n\tPriority: "+temp.getPriority()+"\n\tDeparture: "+temp.getEarliestDepartureTime()+ "\n\tEarliest "+temp.getEarliestArrivalTime()+"\n\tLatest: "+temp.getLatestArrivalTime());
				w.write("\n");
			}
			w.flush();
			
		}
		public static void printVehicleInfo()throws IOException
		{
			File f = new File("VehicleInfo.txt");
			Writer w=null;
			try
			{
				if(!f.exists())
					f.createNewFile();
				w=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			}
			catch(Exception ex)
			{
				System.out.println("Error " +ex);
			}
			
			w.write("\t\t\t\t\tVehicle Information");
			w.write("\n\n");
			//w.write("ID\tFrom\tTo\tSize\tPriority\tDeparture\tEarliest\tLatest");
			w.write("\n-----------------------------------------------------------------");
			w.write("\n\n\n\t\t\t\t\t\tTRUCKS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Truck> veh = Truck.LoadAll("");
			for(int i = 0; i<veh.size();i++)
			{
				Truck t = veh.get(i);
				w.write("\nTruckID: "+t.getId());
				w.write("\n\tTruck Name: "+t.getVehicleName());
				w.write("\n\tContractor: " + t.getCarrier().getCarrierName());
				w.write("\n\tStatus: "+t.getStatus());
				w.write("\n");
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tPLANES\n----------------------------------------------------------------\n\n\n");
			ArrayList<Plane> veh1 = Plane.LoadAll("");
			for(int i = 0; i<veh1.size();i++)
			{
				Plane t = veh1.get(i);
				w.write("\nPlaneID: "+t.getId());
				w.write("\n\tPlane Name: "+t.getVehicleName());
				w.write("\n\tContractor: " + t.getCarrier().getCarrierName());
				w.write("\n\tStatus: "+t.getStatus());
				w.write("\n");
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tTRAINS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Rail> veh2 = Rail.LoadAll("");
			for(int i = 0; i<veh2.size();i++)
			{
				Rail t = veh2.get(i);
				w.write("\nRailID: "+t.getId());
				w.write("\n\tRail Name: "+t.getVehicleName());
				w.write("\n\tContractor: " + t.getCarrier().getCarrierName());
				w.write("\n\tStatus: "+t.getStatus());
				w.write("\n");
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tCARGOSHIPS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Cargo> veh3 = Cargo.LoadAll("");
			for(int i = 0; i<veh3.size();i++)
			{
				Cargo t = veh3.get(i);
				w.write("\nCargoShipID: "+t.getId());
				w.write("\n\tCargo Ship Name: "+t.getVehicleName());
				w.write("\n\tContractor: " + t.getCarrier().getCarrierName());
				w.write("\n\tStatus: "+t.getStatus());
				w.write("\n");
			}
			w.flush();
			
		}
	}


