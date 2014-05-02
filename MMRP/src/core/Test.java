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
	public void printRandmonness()
	{

	}
	public static void main(String[] args) throws Exception
	{
		
		BaseClass.purgeDeleted();
		
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


