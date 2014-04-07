package core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ShippingRate extends BaseClass{

	private int id;
	private Carrier carrier;
	private Location startLocation;
	private Location endLocation;
	private TravelType type;
	private double weight1;
	private double weight2;
	private double weight3;
	private double rate1;
	private double rate2;
	private double rate3;
	private double mileRate;
	private double flatRate;
	private int rank;
	
	//This is the default ShippingRate constructor
		public ShippingRate()
		{
			MarkNew();													//Mark this ShippingRate as new
		}//End of default ShippingRate constructor
		
		//This is the argumented ShippingRate constructor that takes an id
		public ShippingRate(int id)
		{
			this.id=id;													//Set the id
																		
		}//End of the argumented constructor ShippingRate(int id)
		
		public int getId(){
			return id;
		}
		
		public Location getStartLocation() {
			return startLocation;
		}

		public void setStartLocation(Location startLocation) {
			this.startLocation = startLocation;
		}

		public Location getEndLocation() {
			return endLocation;
		}

		public void setEndLocation(Location endLocation) {
			this.endLocation = endLocation;
		}

		public TravelType getType() {
			return type;
		}

		public void setType(TravelType type) {
			this.type = type;
		}

		public double getWeight1() {
			return weight1;
		}

		public void setWeight1(double weight1) {
			this.weight1 = weight1;
		}

		public double getWeight2() {
			return weight2;
		}

		public void setWeight2(double weight2) {
			this.weight2 = weight2;
		}

		public double getWeight3() {
			return weight3;
		}

		public void setWeight3(double weight3) {
			this.weight3 = weight3;
		}

		public double getRate1() {
			return rate1;
		}

		public void setRate1(double rate1) {
			this.rate1 = rate1;
		}

		public double getRate2() {
			return rate2;
		}

		public void setRate2(double rate2) {
			this.rate2 = rate2;
		}

		public double getRate3() {
			return rate3;
		}

		public void setRate3(double rate3) {
			this.rate3 = rate3;
		}

		public double getMileRate() {
			return mileRate;
		}

		public void setMileRate(double mileRate) {
			this.mileRate = mileRate;
		}

		public double getFlatRate() {
			return flatRate;
		}

		public void setFlatRate(double flatRate) {
			this.flatRate = flatRate;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setCarrier(Carrier carrier) {
			this.carrier = carrier;
		}
		
		public Carrier getCarrier() {
			return carrier;
		}
		
		//This function overrides the parent's Update function and will handle changes made to the ShippingRate object in the database
		@Override
		public void Update() 
		{
			try
			{
				//toDo: set id on insert set update statement
				if(isNew())
				{
					//If the ShippingRate is new insert it into the database by executing the following
					executeCommand("Insert into shippingrate (ShippingRateID, CarrierID, StartLocation, EndLocation, TravelType, Weight1, Rate1, Weight2, Rate2, Weight3, Rate3, MileRate, FlatRate, Rank) Values ('"+
							this.getId() + "','" + this.getCarrier().getId() +"','"+this.getStartLocation()
							+ this.getEndLocation() +"','"+ this.getType() +"','"+ this.getWeight1() +"','"
							+ this.getRate1() +"','"+ this.getWeight2() +"','"+ this.getRate2() +"','"
							+ this.getWeight3() +"','"+ this.getRate3() +"','"+ this.getMileRate() +"','"
							+ this.getFlatRate() +"','"+ this.getRank() +"')");
					//Grab this ShippingRate from the database
					ArrayList<Map<String,Object>> temp =executeQuery("Select ShippingRateID from shippingrate where CarrierID = '" + 
					this.getCarrier().getId() + "' AND StartLocation = '"+this.getStartLocation() +
					"' AND EndLocation = '"+this.getEndLocation() + "' AND TravelType = '"+this.getType() +
					"' AND Weight1 = '"+this.getWeight1() + "' AND Rate1 = '"+this.getRate1() +
					"' AND Weight2 = '"+this.getWeight2() + "' AND Rate2 = '"+this.getRate2() +
					"' AND Weight3 = '"+this.getWeight3() + "' AND Rate2 = '"+this.getRate3() +
					"' AND MileRate = '"+this.getMileRate() + "' AND FlatRate = '"+this.getFlatRate() +
					"' AND Rank = '"+this.getRank()  +"'");
					//If this ShippingRate exists on the database mark it as old and clean
					if(temp.size()>0)
					{
						this.id = (Integer)temp.get(0).get("ShippingRateID");			//Set this ShippingRate id to the id in the database
						MarkClean();													//Mark this ShippingRate as clean
						MarkOld();														//Mark this ShippingRate as old
					}//End of found something if
				}//End of isNew if
				else
				{
					if(isDirty())
					{
						//If the ShippingRate is not new, but is dirty then it needs to be updated by the following SQL command
						executeCommand("Update ShippingRate Set CarrierID = '" + this.getCarrier().getId() + 
								"' , StartLocation = '"+this.getStartLocation() + "' , EndLocation = '" + this.getEndLocation() +
								"' , TravelType = '"+this.getType() + "' , Weight1 = '" + this.getWeight1() +
								"' , Rate1 = '"+this.getRate1() + "' , Weight2 = '" + this.getWeight2() +
								"' , Rate2 = '"+this.getRate2() + "' , Weight3 = '" + this.getWeight3() +
								"' , Rate3 = '"+this.getRate3() + "' , MileRate = '" + this.getMileRate() +
								"' , FlatRate = '"+this.getFlatRate() + "' , Rank = '" + this.getRank() +
								"' Where ShippingRateID = " +this.id);
						MarkClean();													//Mark the ShippingRate as clean
					}//End of isDirty else
				}//End of isOld else
			}//End of try block
			catch(Exception ex)
			{
				System.out.println("Error " + ex);										//Print out the error
			}//End of catch block
			
		}//End of the overridden Update()

		//This is the overridden Delete function of the parent class and will remove this ShippingRate from the database
		@Override
		public  void Delete() 
		{
			try
			{
				executeCommand("Delete from ShippingRate Where ShippingRateID = " + this.id);			//Delete the ShippingRate
			}//End of try block
			catch(Exception ex)
			{
				System.out.println("Error " + ex);										//Print out the error
			}//End of catch block

		}//End of overridden Delete()

		public static ShippingRate Load(int id)
		{
			try
			{
				ArrayList<Map<String,Object>> temp = executeQuery("Select * from ShippingRate where ShippingRateID = " + id);
				if(temp.size()>0)
				{
					ShippingRate sr = BuildFromDataRow(temp.get(0));
					return sr;
				}
				return null;
			}
			catch(Exception ex)
			{
				System.out.println("Error " + ex);
			}
	 		return null;
		}
		public static ArrayList<ShippingRate> LoadAll(String where)
		{
			ArrayList<ShippingRate> returnList = new ArrayList<ShippingRate>();
			try 
			{
				ArrayList<Map<String,Object>> temp = executeQuery("Select * from ShippingRate " +  where);
				for(int i = 0; i<temp.size();i++)
				{
					ShippingRate sr = BuildFromDataRow(temp.get(i));
					returnList.add(sr);
				}
			}
			catch(Exception ex)
			{
				System.out.println("Error " + ex);
			}
			return returnList;
		}
		//This function builds objects from returned data from SQL queries against our database
			public static ShippingRate BuildFromDataRow(Map<String,Object> data) throws SQLException
			{
				//This code grabs each element that will be found in the database on the ShippingRate table and set the appropriate values for a new ShippingRate
				ShippingRate sr = new ShippingRate((Integer)data.get("ShippingRateID"));
				sr.setCarrier(Carrier.Load((Integer)data.get("CarrierID")));
				sr.setStartLocation(Location.Load((Integer)data.get("StartLocation")));
				sr.setEndLocation(Location.Load((Integer)data.get("EndLocation")));
				sr.setType(TravelType.Load((Integer)data.get("TravelType")));
				sr.setWeight1((Double)data.get("Weight1"));
				sr.setRate1((Double)data.get("Rate1"));
				sr.setWeight2((Double)data.get("Weight2"));
				sr.setRate2((Double)data.get("Rate2"));
				sr.setWeight3((Double)data.get("Weight3"));
				sr.setRate3((Double)data.get("Rate3"));
				sr.setMileRate((Double)data.get("MileRate"));
				sr.setFlatRate((Double)data.get("FlatRate"));
				sr.setRank((Integer)data.get("Rank"));
				sr.MarkClean();															//Mark the ShippingRate as clean
				return sr;
				
			}//End of BuildFromDataRow(Map<String,Object> data)

	
}//End of ShippingRate class