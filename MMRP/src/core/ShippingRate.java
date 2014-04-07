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
		
		//This function overrides the parent's Update function and will handle changes made to the Rail object in the database
		@Override
		public void Update() 
		{
			try
			{
				//toDo: set id on insert set update statement
				if(isNew())
				{
					//If the Rail is new insert it into the database by executing the following
					executeCommand("Insert into rail (RailName,Carrier,Status) Values ('"+
							getRailName() + "','" + this.getCarrier().getId() +"','"+this.getStatus()+"')");
					//Grab this Rail from the database
					ArrayList<Map<String,Object>> temp =executeQuery("Select RailID from rail where RailName = '" + this.getRailName() + "' AND Carrier = '"+this.getCarrier().getId()+
							 "' AND Status = '" + this.getStatus()+"'");
					//If this rail exists on the database mark it as old and clean
					if(temp.size()>0)
					{
						this.id = (Integer)temp.get(0).get("RailID");					//Set this Rail id to the id in the database
						MarkClean();													//Mark this Rail as clean
						MarkOld();														//Mark this Rail as old
					}//End of found something if
				}//End of isNew if
				else
				{
					if(isDirty())
					{
						//If the Rail is not new, but is dirty then it needs to be updated by the following SQL command
						executeCommand("Update Rail Set RailName = '" + this.getRailName() + "' , Carrier = '"+this.getCarrier().getId()+
							 "' , Status = '" + this.getStatus() + "' Where RailID = " +this.id);
						MarkClean();													//Mark the Rail as clean
					}//End of isDirty else
				}//End of isOld else
			}//End of try block
			catch(Exception ex)
			{
				System.out.println("Error " + ex);										//Print out the error
			}//End of catch block
			
		}//End of the overridden Update()

		//This is the overridden Delete function of the parent class and will remove this Rail from the database
		@Override
		public  void Delete() 
		{
			try
			{
				executeCommand("Delete from Rail Where RailID = " + this.id);			//Delete the Rail
			}//End of try block
			catch(Exception ex)
			{
				System.out.println("Error " + ex);										//Print out the error
			}//End of catch block

		}//End of overridden Delete()

		public static Rail Load(int id)
		{
			try
			{
				ArrayList<Map<String,Object>> temp = executeQuery("Select * from Rail where RailID = " + id);
				if(temp.size()>0)
				{
					Rail r = BuildFromDataRow(temp.get(0));
					r.getSchedule();
					return r;
				}
				return null;
			}
			catch(Exception ex)
			{
				System.out.println("Error " + ex);
			}
	 		return null;
		}
		public static ArrayList<Rail> LoadAll(String where)
		{
			ArrayList<Rail> returnList = new ArrayList<Rail>();
			try 
			{
				ArrayList<Map<String,Object>> temp = executeQuery("Select * from Rail " +  where);
				for(int i = 0; i<temp.size();i++)
				{
					Rail r = BuildFromDataRow(temp.get(i));
					r.getSchedule();
					returnList.add(r);
				}
			}
			catch(Exception ex)
			{
				System.out.println("Error " + ex);
			}
			return returnList;
		}
		//This function builds objects from returned data from SQL queries against our database
			public static Rail BuildFromDataRow(Map<String,Object> data) throws SQLException
			{
				//This code grabs each element that will be found in the database on the Rail table and set the appropriate values for a new Rail
				Rail r = new Rail((Integer)data.get("RailID"));
				//b.setId();
				r.setRailName((String)data.get("RailName"));
				r.setCarrier(Carrier.Load((Integer)data.get("Carrier")));
				r.setStatus((String)data.get("Status"));		
				r.MarkClean();															//Mark the Rail as clean
				return r;
				
			}//End of BuildFromDataRow(Map<String,Object> data)
		@Override
		public String toString()
		{
			return getRailName();
	
}//End of ShippingRate class