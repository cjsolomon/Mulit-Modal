package Routing;

import core.Segment;
import java.util.ArrayList;

public class WeightedMetric{

	int distance;
	int time;
	int cost;
	
	double distNWeight;
	double timeNWeight;
	double costNWeight;
	
	static final int MIN_DISTANCE = 0;
	static final int MAX_DISTANCE = 100;
	static final int MIN_TIME = 0;
	static final int MAX_TIME = 100;
	static final int MIN_COST = 0;
	static final int MAX_COST = 100;
	
	public static final int DEFAULT_DISTANCE = 1;
	public static final int DEFAULT_TIME = 1;
	public static final int DEFAULT_COST = 1;
	
	public WeightedMetric(){
		distance = DEFAULT_DISTANCE;
		time = DEFAULT_TIME;
		cost = DEFAULT_COST;
		this.normalize();
	}//End of WeightedMetric default constructor
	
	public WeightedMetric(int distanceValue, int timeValue, int costValue){
		//Some error checking
		if(distanceValue < MIN_DISTANCE){
			System.out.println("The distance value passed in was too low, the distance has be set to " + MIN_DISTANCE);
			distanceValue = MIN_DISTANCE;
		} else if (distanceValue > MAX_DISTANCE){
			System.out.println("The distance value passed in was too high, the distance has be set to " + MAX_DISTANCE);
			distanceValue = MAX_DISTANCE;
		}
		if(timeValue < MIN_TIME){
			System.out.println("The time value passed in was too low, the time has be set to " + MIN_TIME);
			timeValue = MIN_TIME;
		} else if (timeValue > MAX_TIME){
			System.out.println("The time value passed in was too high, the time has be set to " + MAX_TIME);
			timeValue = MAX_TIME;
		}
		if(costValue < MIN_COST){
			System.out.println("The cost value passed in was too low, the cost has be set to " + MIN_COST);
			costValue = MIN_COST;
		} else if (costValue > MAX_COST){
			System.out.println("The cost value passed in was too high, the cost has be set to " + MAX_COST);
			costValue = MAX_COST;
		}
		
		this.distance = distanceValue;
		this.time = timeValue;
		this.cost = costValue;
		
		this.normalize();
	}//End of WeightedMetric(int distanceValue, int timeValue, int costValue)
	
	public int getDistance(){
		return this.distance;
	}//End of getDistance()
	
	public double getWeightedDistance(){
		return this.distNWeight;
	}//End of getWeightedDistance()
	
	public int getTime(){
		return this.time;
	}//End of getTime()
	
	public double getWeightedTime(){
		return this.timeNWeight;
	}//End of getWeightedTime()
	
	public int getCost(){
		return this.cost;
	}//End of getCost()
	
	public double getWeightedCost(){
		return this.costNWeight;
	}//End of getWeightedCost()
	
	public void setDistance(int distanceValue){
		//Some error checking
		if(distanceValue < MIN_DISTANCE){
			System.out.println("The distance value passed in was too low, the distance has be set to " + MIN_DISTANCE);
			distanceValue = MIN_DISTANCE;
		} else if (distanceValue > MAX_DISTANCE){
			System.out.println("The distance value passed in was too high, the distance has be set to " + MAX_DISTANCE);
			distanceValue = MAX_DISTANCE;
		}
		distance = distanceValue;
		this.normalize();
		
	}//End of setDistance(int distanceValue)
	
	public void setTime(int timeValue){
		//Some error checking
		if(timeValue < MIN_TIME){
			System.out.println("The time value passed in was too low, the time has be set to " + MIN_TIME);
			timeValue = MIN_TIME;
		} else if (timeValue > MAX_TIME){
			System.out.println("The time value passed in was too high, the time has be set to " + MAX_TIME);
			timeValue = MAX_TIME;
		}
		time = timeValue;
		this.normalize();
		
	}//End of setTime(int timeValue)
	
	public void setCost(int costValue){
		//Some error checking
		if(costValue < MIN_COST){
			System.out.println("The cost value passed in was too low, the cost has be set to " + MIN_COST);
			costValue = MIN_COST;
		} else if (costValue > MAX_COST){
			System.out.println("The cost value passed in was too high, the cost has be set to " + MAX_COST);
			costValue = MAX_COST;
		}
		cost = costValue;
		this.normalize();
		
	}//End of setCost(int costValue)
	
	private void normalize(){
		double normalLength = Math.sqrt(distance * distance + time * time + cost * cost);
		distNWeight = distance / normalLength;
		timeNWeight =  time / normalLength;
		costNWeight = cost / normalLength;
	}//End of normalize()
	
	public double getWeightedCost(Segment segment){
		return segment.getShippingRate().getFlatRate() * costNWeight + segment.getDistance() * distNWeight + (segment.getEstimatedArrivalTime() - segment.getEstimatedDepartureTime()) * timeNWeight;
	}//End of getWeightedCost(Segment segment)
	
	public Segment getLowestWeightedCostSegment(ArrayList<Segment> segments){
		double lowest = segments.get(0).getShippingRate().getFlatRate() * costNWeight + 
						segments.get(0).getDistance() * distNWeight + 
					   (segments.get(0).getEstimatedArrivalTime() - segments.get(0).getEstimatedDepartureTime()) * timeNWeight;
		int lowestIndex = 0;
		for(int i=0; i< segments.size(); i++){
			if((segments.get(i).getShippingRate().getFlatRate() * costNWeight + 
				segments.get(i).getDistance() * distNWeight + 
				(segments.get(i).getEstimatedArrivalTime() - segments.get(i).getEstimatedDepartureTime()) * timeNWeight) < lowest){
				lowestIndex = i;
				lowest = (segments.get(i).getShippingRate().getFlatRate() * costNWeight + 
						segments.get(i).getDistance() * distNWeight + 
						(segments.get(i).getEstimatedArrivalTime() - segments.get(i).getEstimatedDepartureTime()) * timeNWeight);
			}//End of new lower if
		}//End of checking for loop
				
		//Now we have the location of the the lowest cost path on the list so return it
		return segments.get(lowestIndex);
	}//End of getLowestWeightedCostSegment(ArrayList<Segment> segments)

	public static int getMinDistance() {
		return MIN_DISTANCE;
	}

	public static int getMaxDistance() {
		return MAX_DISTANCE;
	}

	public static int getMinTime() {
		return MIN_TIME;
	}

	public static int getMaxTime() {
		return MAX_TIME;
	}

	public static int getMinCost() {
		return MIN_COST;
	}

	public static int getMaxCost() {
		return MAX_COST;
	}


}//End of the WeightedMetric class
