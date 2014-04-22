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
	
	//Default Variables
	private static final double DEFAULT_WEIGHT1 = 10;
	private static final double DEFAULT_WEIGHT2 = 20;
	private static final double DEFAULT_WEIGHT3 = 30;
	private static final double DEFAULT_RATE1 = 10;
	private static final double DEFAULT_RATE2 = 20;
	private static final double DEFAULT_RATE3 = 30;
	private static final double DEFAULT_MILE_RATE = 10;
	private static final double DEFAULT_FLAT_RATE = 1;
	private static final int DEFAULT_RANK = 1;
	
	/**
	 * This is the default ShippingRate constructor
	 */
		public ShippingRate()
		{
			this.carrier = new Carrier();
			this.endLocation = new Location();
			this.flatRate = DEFAULT_FLAT_RATE;
			this.mileRate = DEFAULT_MILE_RATE;
			this.rank = DEFAULT_RANK;
			this.rate1 = DEFAULT_RATE1;
			this.rate2 = DEFAULT_RATE2;
			this.rate3 = DEFAULT_RATE3;
			this.startLocation = new Location();
			this.type = new TravelType();
			this.weight1 = DEFAULT_WEIGHT1;
			this.weight2 = DEFAULT_WEIGHT2;
			this.weight3 = DEFAULT_WEIGHT3;
			MarkNew();													//Mark this ShippingRate as new
			MarkClean();
			MarkUndeleted();
		}//End of default ShippingRate constructor
		
		/**
		 * This is the argumented ShippingRate constructor
		 * @param id The new id of the ShippingRate
		 */
		public ShippingRate(int id)
		{
			this.id=id;													//Set the id
			this.carrier = new Carrier();
			this.endLocation = new Location();
			this.flatRate = DEFAULT_FLAT_RATE;
			this.mileRate = DEFAULT_MILE_RATE;
			this.rank = DEFAULT_RANK;
			this.rate1 = DEFAULT_RATE1;
			this.rate2 = DEFAULT_RATE2;
			this.rate3 = DEFAULT_RATE3;
			this.startLocation = new Location();
			this.type = new TravelType();
			this.weight1 = DEFAULT_WEIGHT1;
			this.weight2 = DEFAULT_WEIGHT2;
			this.weight3 = DEFAULT_WEIGHT3;												//Mark this ShippingRate as new
			MarkClean();												
		}//End of the argumented constructor ShippingRate(int id)
		
		/**
		 * This function returns the id of the ShippingRate
		 * @return Returns the id the of ShippingRate
		 */
		public int getId(){
			return id;
		}
		
		/**
		 * This function returns the start location of the ShippingRate
		 * @return Returns the start location of the ShippingRate
		 */
		public Location getStartLocation() {
			return startLocation;
		}

		/**
		 * This function sets the start location of the ShippingRate
		 * @param startLocation This is the new start location
		 */
		public void setStartLocation(Location startLocation) {
			this.startLocation = startLocation;
		}

		/**
		 * This function returns the end location of the ShippingRate
		 * @return Returns the end location of the ShippingRate
		 */
		public Location getEndLocation() {
			return endLocation;
		}

		/**
		 * This function sets the end location of the ShippingRate
		 * @param endLocation This is the new end location
		 */
		public void setEndLocation(Location endLocation) {
			this.endLocation = endLocation;
		}

		/**
		 * This function returns the TravelType of the ShippingRate
		 * @return Returns the TravelType of the ShippingRate
		 */
		public TravelType getType() {
			return type;
		}

		/**
		 * This function sets the TravelType for the ShippingRate
		 * @param type This is the new TravelType
		 */
		public void setType(TravelType type) {
			this.type = type;
		}

		/**
		 * This function returns weight1 for the ShippingRate
		 * @return Returns the weight1 for the ShippingRate
		 */
		public double getWeight1() {
			return weight1;
		}

		/**
		 * This function sets the weight1 for the ShippingRate
		 * @param weight1 This is the new weight1 for the ShippingRate
		 */
		public void setWeight1(double weight1) {
			this.weight1 = weight1;
		}

		/**
		 * This function sets the weight2 for the ShippingRate
		 * @return Returns the weight2 for the ShippingRate
		 */
		public double getWeight2() {
			return weight2;
		}

		/**
		 * This function sets the weight2 for the ShippingRate
		 * @param weight1 This is the new weight2 for the ShippingRate
		 */
		public void setWeight2(double weight2) {
			this.weight2 = weight2;
		}
		
		/**
		 * This function sets the weight3 for the ShippingRate
		 * @return Returns the weight3 for the ShippingRate
		 */
		public double getWeight3() {
			return weight3;
		}

		/**
		 * This function sets the weight3 for the ShippingRate
		 * @param weight1 This is the new weight3 for the ShippingRate
		 */
		public void setWeight3(double weight3) {
			this.weight3 = weight3;
		}

		/**
		 * This function returns the rate1 for the ShippingRate
		 * @return Returns the rate1 for the ShippingRate
		 */
		public double getRate1() {
			return rate1;
		}

		/**
		 * This function sets the rate1 for the ShippingRate
		 * @param rate1 This is the new rate1 for the ShippingRate
		 */
		public void setRate1(double rate1) {
			this.rate1 = rate1;
		}

		/**
		 * This function returns the rate2 for the ShippingRate
		 * @return Returns the rate2 for the ShippingRate
		 */
		public double getRate2() {
			return rate2;
		}

		/**
		 * This function sets the rate2 for the ShippingRate
		 * @param rate1 This is the new rate2 for the ShippingRate
		 */
		public void setRate2(double rate2) {
			this.rate2 = rate2;
		}

		/**
		 * This function returns the rate3 for the ShippingRate
		 * @return Returns the rate3 for the ShippingRate
		 */
		public double getRate3() {
			return rate3;
		}

		/**
		 * This function sets the rate3 for the ShippingRate
		 * @param rate1 This is the new rate3 for the ShippingRate
		 */
		public void setRate3(double rate3) {
			this.rate3 = rate3;
		}

		/**
		 * This function returns the MileRate for the ShippingRate
		 * @return Returns the MileRate for the ShippingRate
		 */
		public double getMileRate() {
			return mileRate;
		}

		/**
		 * This function sets the MileRate for the ShippingRate
		 * @param rate1 This is the new Milerate for the ShippingRate
		 */
		public void setMileRate(double mileRate) {
			this.mileRate = mileRate;
		}

		/**
		 * This function returns the FlatRate for the ShippingRate
		 * @return Returns the FlatRate for the ShippingRate
		 */
		public double getFlatRate() {
			return flatRate;
		}

		/**
		 * This function sets the FlatRate for the ShippingRate
		 * @param rate1 This is the new FlatRate for the ShippingRate
		 */
		public void setFlatRate(double flatRate) {
			this.flatRate = flatRate;
		}

		/**
		 * This function returns the rank for the ShippingRate
		 * @return Returns the rank for the ShippingRate
		 */
		public int getRank() {
			return rank;
		}

		/**
		 * This function sets the rank for the ShippingRate
		 * @param rate1 This is the new rank for the ShippingRate
		 */
		public void setRank(int rank) {
			this.rank = rank;
		}

		/**
		 * This function sets the Carrier for the ShippingRate
		 * @param rate1 This is the new carrier for the ShippingRate
		 */
		public void setCarrier(Carrier carrier) {
			this.carrier = carrier;
		}
		
		/**
		 * This function returns the Carrier for the ShippingRate
		 * @return Returns the Carrier for the ShippingRate
		 */
		public Carrier getCarrier() {
			return carrier;
		}
		
		/**
		 * This function overrides the parent's Update function and will handle changes made to the ShippingRate object in the database
		 * @return 
		 */
		@Override
		public boolean Update() 
		{
			try
			{
				//toDo: set id on insert set update statement
				if(isNew())
				{
					//If the ShippingRate is new insert it into the database by executing the following
					executeCommand("Insert into shippingrates (ShippingRateID, CarrierID, StartLocation, EndLocation, TravelType, Weight1, Rate1, Weight2, Rate2, Weight3, Rate3, MileRate, FlatRate, Rank) Values ('"+
							this.getId() + "','" + this.getCarrier().getId() +"','"+this.getStartLocation().getID() + "','"
							+ this.getEndLocation().getID() +"','"+ this.getType().getVehicleTypeID() +"','"+ this.getWeight1() +"','"
							+ this.getRate1() +"','"+ this.getWeight2() +"','"+ this.getRate2() +"','"
							+ this.getWeight3() +"','"+ this.getRate3() +"','"+ this.getMileRate() +"','"
							+ this.getFlatRate() +"','"+ this.getRank() +"')");
					//Grab this ShippingRate from the database
					ArrayList<Map<String,Object>> temp =executeQuery("Select * from shippingrates where CarrierID = '" + this.getCarrier().getId() + 
					"' AND StartLocation = '"+this.getStartLocation().getID() +
					"' AND EndLocation = '"+this.getEndLocation().getID() + 
					"' AND TravelType = '"+this.getType().getVehicleTypeID() +
					"' AND Weight1 = '"+this.getWeight1() + 
					"' AND Rate1 = '"+this.getRate1() +
					"' AND Weight2 = '"+this.getWeight2() + 
					"' AND Rate2 = '"+this.getRate2() +
					"' AND Weight3 = '"+this.getWeight3() + 
					"' AND Rate3 = '"+this.getRate3() +
					"' AND MileRate = '"+this.getMileRate() + 
					"' AND FlatRate = '"+this.getFlatRate() +
					"' AND Rank = '"+this.getRank()  +"'");
					//If this ShippingRate exists on the database mark it as old and clean
					if(temp.size()>0)
					{
						this.id = (Integer)temp.get(temp.size()-1).get("ShippingRateID");			//Set this ShippingRate id to the id in the database
						MarkClean();													//Mark this ShippingRate as clean
						MarkOld();														//Mark this ShippingRate as old
					}//End of found something if
				}//End of isNew if
				else
				{
					if(isDirty())
					{
						//If the ShippingRate is not new, but is dirty then it needs to be updated by the following SQL command
						executeCommand("Update shippingrates Set CarrierID = '" + this.getCarrier().getId() + 
								"' , StartLocation = '"+this.getStartLocation() + "' , EndLocation = '" + this.getEndLocation() +
								"' , TravelType = '"+this.getType() + "' , Weight1 = '" + this.getWeight1() +
								"' , Rate1 = '"+this.getRate1() + "' , Weight2 = '" + this.getWeight2() +
								"' , Rate2 = '"+this.getRate2() + "' , Weight3 = '" + this.getWeight3() +
								"' , Rate3 = '"+this.getRate3() + "' , MileRate = '" + this.getMileRate() +
								"' , FlatRate = '"+this.getFlatRate() + "' , Rank = '" + this.getRank() +
								"' , Deleted = " + this.isDeleted() +
								" Where ShippingRateID = " +this.id);
						MarkClean();													//Mark the ShippingRate as clean
					}//End of isDirty else
				}//End of isOld else
				return true;
			}//End of try block
			catch(Exception ex)
			{
				System.out.println("Error " + ex);										//Print out the error
				ex.printStackTrace();
				return false;
			}//End of catch block
			
		}//End of the overridden Update()

		/**
		 * This is the overridden Delete function of the parent class and will mark the ShippingRate as deleted in the database
		 * @return Returns a boolean indicating is the ShippingRate was successfully marked as deleted
		 */
		@Override
		public  boolean Delete() 
		{
			try
			{
				executeCommand("Update ShippingRates Set Deleted = true Where ShippingRateID = " + this.id);			//Delete the ShippingRate
				//Now we need mark all the segments that use this bike as deleted
				executeQuery("Update Segment set Deleted = true Where ShippingRateID = " + this.id);
		
				this.MarkDeleted();
				return true;
			}//End of try block
			catch(Exception ex)
			{
				System.out.println("Error " + ex);										//Print out the error
				ex.printStackTrace();
				return false;
			}//End of catch block

		}//End of overridden Delete()

		/**
		 * This function loads a ShippingRate from the database based on its ID
		 * @param id This is the ID of the ShippingRate to load
		 * @return Returns the loaded ShippingRate object
		 */
		public static ShippingRate Load(int id)
		{
			try
			{
				ArrayList<Map<String,Object>> temp = executeQuery("Select * from ShippingRates where ShippingRateID = " + id  + " AND Deleted = false");
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
				ex.printStackTrace();
			}
	 		return null;
		}
		
		/**
		 * This function loads an ArrayList of ShippingRate objects from the database based on a passed in Where clause of an SQL statement
		 * @param where This is the where clause specifying which ShippingRates to load from the database
		 * @return Returns an ArrayList of ShippingRate objects based on the passed in Where clause
		 */
		public static ArrayList<ShippingRate> LoadAll(String where)
		{
			ArrayList<ShippingRate> returnList = new ArrayList<ShippingRate>();
			try 
			{
				ArrayList<Map<String,Object>> temp = executeQuery("Select * from ShippingRates " +  where  + " AND Deleted = false");
				for(int i = 0; i<temp.size();i++)
				{
					ShippingRate sr = BuildFromDataRow(temp.get(i));
					returnList.add(sr);
				}
			}
			catch(Exception ex)
			{
				System.out.println("Error " + ex);
				ex.printStackTrace();
			}
			return returnList;
		}
		
		/**
		 * This function builds objects from returned data from SQL queries against our database
		 * @param data This is the data from which the object will be build
		 * @return Returns a ShippingRate object based on the passed in data
		 * @throws SQLException
		 */
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
				if(Boolean.getBoolean(data.get("Deleted").toString()))
					sr.MarkDeleted();
				else
					sr.MarkUndeleted();
				sr.MarkClean();															//Mark the ShippingRate as clean
				return sr;
				
			}//End of BuildFromDataRow(Map<String,Object> data)

	
}//End of ShippingRate class