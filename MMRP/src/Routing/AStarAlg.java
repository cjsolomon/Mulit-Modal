package Routing;
import java.util.ArrayList;

import core.*;
public class AStarAlg extends RoutingAlgorithm {
	
	private ArrayList<AStarNode> leafs;
	
	final double DISTTOGO=.2;
	final double DISTTRAVLED=.2;
	
	AStarNode best=null;
	public AStarAlg(Shipment s, WeightedMetric newMetric)
	{
		shipment=s;
		metric = newMetric;
		leafs=new ArrayList<AStarNode>();
		leafs.add(new AStarNode(shipment.getFromLocationID(),null,-1,0.0));
	}
	@Override
	public ArrayList<Segment> getPath()
	{
		ArrayList<Segment> returnPath = new ArrayList<Segment>();
		
		while(leafs.size()>0)
		{
			AStarNode currentLeaf = leafs.get(0);
			for(int i =1; i<leafs.size();i++)
				currentLeaf = (currentLeaf.getCost()>leafs.get(i).getCost())?leafs.get(i):currentLeaf;
				
			leafs.remove(currentLeaf);
			if(currentLeaf.getLocationID()==shipment.getToLocationID())
			{
				if(Segment.Load(currentLeaf.getSegmentID()).getEstimatedArrivalTime() > shipment.getLatestArrivalTime() || Segment.Load(currentLeaf.getSegmentID()).getEstimatedArrivalTime() < shipment.getEarliestArrivalTime() )
				{
					int currentDif;
					if(best!=null)
					{
						if(Segment.Load(best.getSegmentID()).getEstimatedArrivalTime() > shipment.getLatestArrivalTime())
							currentDif=Math.abs(Segment.Load(best.getSegmentID()).getEstimatedArrivalTime() - shipment.getLatestArrivalTime());
						else
							currentDif = Math.abs(Segment.Load(best.getSegmentID()).getEstimatedArrivalTime() - shipment.getEarliestArrivalTime());
					}
					else
						currentDif=0;
					int thisDif;
					if(Segment.Load(currentLeaf.getSegmentID()).getEstimatedArrivalTime() > shipment.getLatestArrivalTime())// Segment.Load(currentLeaf.getSegmentID()).getArrivalTime() < shipment.getEarliestTime() )
						thisDif=Math.abs(Segment.Load(currentLeaf.getSegmentID()).getEstimatedArrivalTime() - shipment.getLatestArrivalTime());
					else
						thisDif = Math.abs(Segment.Load(currentLeaf.getSegmentID()).getEstimatedArrivalTime() - shipment.getEarliestArrivalTime());
					
					if(best==null || thisDif<currentDif)
					{
						best=currentLeaf;
					}
					if(leafs.size()>0)
					{
						currentLeaf=leafs.get(0);
						for(int i =1; i<leafs.size();i++)
							currentLeaf = (currentLeaf.getCost()>leafs.get(i).getCost())?leafs.get(i):currentLeaf;
					}
				}
				else
				{
					while(currentLeaf!=null)
					{
						if(currentLeaf.getSegmentID()!=-1)
							returnPath.add(0,Segment.Load(currentLeaf.getSegmentID()));
						currentLeaf=currentLeaf.getPrevious();
					}
					return returnPath;
				}
			}
			
			
				int startTime = (currentLeaf.getSegmentID()==-1)?shipment.getEarliestDepartureTime():Segment.Load(currentLeaf.getSegmentID()).getEstimatedArrivalTime();
				ArrayList<Segment> possibleDestinations = Segment.LoadAllAtLocation(currentLeaf.getLocationID(), startTime);
				
				//Check for previously visitedLocations
				removePreviousVisited(possibleDestinations,currentLeaf);
				//Check if shipment will fit on vehicle
				removeSegmentsWithFullVehicle(possibleDestinations);
				//Check if lowerCost leaf to location already exists if not add/replace new leaf
				checkCurrentLeafs(possibleDestinations,currentLeaf);
				
			
		}
		while(best!=null)
		{
			if(best.getSegmentID()!=-1)
				returnPath.add(0,Segment.Load(best.getSegmentID()));
			best=best.getPrevious();
		}
		return returnPath;
		
	}
	
	private void removePreviousVisited(ArrayList<Segment> possible, AStarNode current)
	{
		ArrayList<Segment> toRemove = new ArrayList<Segment>();
		
		for(int i = 0; i< possible.size();i++)
		{
			Segment test = possible.get(i);
			AStarNode compareNode=current.getPrevious();
			while(compareNode!=null)
			{
				if(test.getEndLocationID()==compareNode.getLocationID())
				{
					toRemove.add(test);
					break;
				}
				compareNode=compareNode.getPrevious();
			}
		}
		//remove previously visited
		for(int i = 0; i<toRemove.size();i++)
		{
			possible.remove(toRemove.get(i));
		}
	}

	private void removeSegmentsWithFullVehicle(ArrayList<Segment> possible)
	{
		ArrayList<Segment> toRemove=new ArrayList<Segment>();
		for(int i = 0; i<possible.size();i++)
		{
			Segment test = possible.get(i);
			if(this.shipment.getId()==21)
				{
				System.out.println("TroubleArea");
				
				}
			if(Math.abs(possible.get(i).getActualCapacity()- possible.get(i).getTravelType().getMaxCap())<(test.estimateCapacity()+shipment.getSize()))
				toRemove.add(test);
		}
		
		//remove segments where vehicle is full
		for(int i = 0; i<toRemove.size();i++)
		{
			possible.remove(toRemove.get(i));
		}
	}
	private void checkCurrentLeafs(ArrayList<Segment> possible,AStarNode currentLeaf)
	{
		for(int i =0;i<possible.size();i++)
		{
			Segment test = possible.get(i);
			
			double cost=currentLeaf.getCost();
			double distanceToDestination = getDistance(test.getEndLocationID(),shipment.getToLocationID());
			double distanceTravel = test.getDistance();
			double time = test.getEstimatedDepartureTime()-test.getEstimatedArrivalTime();
			double segmentCost = possible.get(i).getShippingRate().getFlatRate();
			
			cost+= (distanceToDestination*this.DISTTOGO)+(distanceTravel*this.DISTTRAVLED)+(time*this.metric.getWeightedTime())+(segmentCost * this.metric.getWeightedCost());
			
			AStarNode temp = new AStarNode(test.getEndLocationID(),currentLeaf,test.getID(),cost);
			AStarNode replace=null;
			if(leafs.size()==0)
				leafs.add(temp);
			else
			{
				for(int j = 0; j<leafs.size();j++)
				{
					if(temp.getLocationID()==leafs.get(j).getLocationID())
					{
						if(temp.getCost()<leafs.get(j).getCost())
						{
							replace=leafs.get(j);
							leafs.add(temp);
							
						}
						break;
					}
				}
			}
			if(replace==null)
				leafs.add(temp);
			if(replace!=null)
				leafs.remove(replace);
			
		}
	}
	public static double getDistance(int one , int two)
	{
		Location s = Location.Load(one);
		Location e = Location.Load(two);
		double distance;
		distance = Math.sqrt(Math.pow(s.getLatitude()-e.getLatitude(),2) + Math.pow(s.getLongitude() - e.getLongitude(), 2));
		return distance;
	}

	@Override
	public ArrayList<Segment> validPaths(ArrayList<Segment> path) {
		// TODO Auto-generated method stub
		return null;
	}

}
