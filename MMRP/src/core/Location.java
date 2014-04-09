package core;
import java.sql.SQLException;
import java.util.*;
public class Location extends BaseClass {

	private ArrayList<Vehicle.TravelModes> travelModes;
	private int id;
	private double latitude;
	private double longitude;
	private String name;
	private String state; 
	private String country; 
	private ArrayList<Vehicle> vehiclesAtLocation;
	
	//Default Variables
	private final double DEFAULT_LATITUDE = 0;
	private final double DEFAULT_LONGITUDE = 0;
	private final String DEFAULT_LOCATION_NAME = "defaultLocationName";
	private final String DEFAULT_STATE = "PA";
	private final String DEFAULT_COUNTRY = "USA";
	
	/**
	 * This is the default constructor for the Location object
	 */
	public Location()
	{
		this.country = DEFAULT_COUNTRY;
		this.latitude = DEFAULT_LATITUDE;
		this.longitude = DEFAULT_LONGITUDE;
		this.name = DEFAULT_LOCATION_NAME;
		this.state = DEFAULT_STATE;
		travelModes=new ArrayList<Vehicle.TravelModes>();
		vehiclesAtLocation=new ArrayList<Vehicle>();
		MarkNew();
		MarkClean();
	}//End of default Location constructor
	
	/**
	 * This is the Location constructor that will take an ID to assign to the new Location object
	 * @param id This is the id that will be assigned to the new Location
	 */
	public Location(int id)
	{
		this.id=id;
		this.country = DEFAULT_COUNTRY;
		this.latitude = DEFAULT_LATITUDE;
		this.longitude = DEFAULT_LONGITUDE;
		this.name = DEFAULT_LOCATION_NAME;
		this.state = DEFAULT_STATE;
		travelModes=new ArrayList<Vehicle.TravelModes>();
		vehiclesAtLocation=new ArrayList<Vehicle>();
		MarkNew();
		MarkClean();

	}//End of Location(int id)
	
	/**
	 * This function will return the ID of the Location
	 * @return Returns the Location's ID
	 */
	public int getID()
	{
		return id;
	}//End of getID()

	/**
	 * This function will set the latitude for the Location
	 * @param lat This is the new latitude for the Location
	 */
	public void setLatitude(double lat)
	{
		//NEED ERROR CHECKING
		if(this.latitude!=lat)
		{
			this.latitude=lat;
			MarkDirty();
		}

	}//End of setLatitude(double lat)
	
	/**
	 * This function returns the latitude of the Location
	 * @return Returns the latitude of the location
	 */
	public double getLatitude()
	{
		return this.latitude;
	}//End of getLatitude()
	
	/**
	 * This function sets the state of the Location
	 * @param st This is the new state of the Location
	 */
	public void setState(String st)
	{
		//NEED ERROR CHECKING
		if(state==null || !this.state.equals(st)) 
		{
			this.state = st; 
			MarkDirty();
		}
	}//End of setState(String st)
	
	/**
	 * This function will return the state of the Location
	 * @return Returns the state of the Location
	 */
	public String getState() 
	{
		return this.state;
	}//End of getState()
	
	/**
	 * This function sets the Country of the Location
	 * @param nation This is the new Country for the Location
	 */
	public void setCountry(String nation)
	{
		//NEED ERROR CHECKING
		if(country==null || !this.country.equals(nation))
		{
			this.country = nation;
			MarkDirty();
		}
	}//End of setCountry(String nation)
	
	/**
	 * This function returns the Country of the Location
	 * @return Returns the country of this Location
	 */
	public String getCountry()
	{
		return this.country;
	}//End of getCountry()
	
	/**
	 * This function will set the longitude of the Location
	 * @param lon This is the new longitude of the location 
	 */
	public void setLongitude(double lon)
	{
		//NEED SOME ERROR CHECKING
		if(this.longitude!=lon)
		{
			this.longitude=lon;
			MarkDirty();
		}
	}//End of setLongitude(double lon)
	
	/**
	 * This function returns the longitude of the Location
	 * @return Returns the longitude of the location
	 */
	public double getLongitude()
	{
		return this.longitude;
	}//End of getLongitude()
	
	/**
	 * This function adds a Travel Mode option to this Location
	 * @param mode This is the new Travel Mode to add to this Location
	 */
	public void addTravelMode(Vehicle.TravelModes mode)
	{
		//DO WE NEED ANY ERROR CHECKING HERE?
		if(!travelModes.contains(mode))
		{
			travelModes.add(mode);
			MarkDirty();
		}
	}//End of addTravelMode(Vehicle.TravelModes mode)
	
	/**
	 * This function will return if the given Travel Mode is available at this Location
	 * @param mode This is Travel Mode that will be checked to see if it is available at this location
	 * @return Returns a boolean indicating if the Travel Mode given is available at this Location
	 */
	public boolean travelTypeAvailable(Vehicle.TravelModes mode)
	{
		if(travelModes.contains(mode))
		{
			return true;
		}
		return false;
	}//End of travelTypeAvailable(Vehicle.TravelModes mode)
	
	/**
	 * This function sets the name of the Location
	 * @param newName This is the new name of the Location
	 */
	public void setName(String newName)
	{
		//NEED ERROR CHECKING
		if(name==null || !name.equals(newName))
		{
			name=newName;
			MarkDirty();
		}
	}//End of setName(String newName)
	
	/**
	 * This function returns the name of the Location
	 * @return Returns the name of the Location
	 */
	public String getName()
	{
		return name;
	}//End of getName()
	
	/**
	 * This is the overridden Update function for the Location
	 * <p>This function will update the Location in the database
	 */
	@Override
	public boolean Update() 
	{
		try
		{
			if(isNew())
			{
				String sql ="Insert into Location (Name,Latitude,Longitude,TravelType1";

				for(int i =1;i<travelModes.size();i++)
					sql+=",TravelType"+(i+1);

				sql+=") Values ('" + this.getName() +"','"+this.latitude+"','"+this.longitude + "','"+travelModes.get(0).toString()+"'";

				for(int i =1;i<travelModes.size();i++)
					sql+=",'"+travelModes.get(i).toString()+"'";

				sql+=")";

				executeCommand(sql);

				sql="Select LocationID from Location where Name = '"+ this.name +"' AND Latitude ='"+this.latitude+"' AND Longitude = '"+ this.longitude + "' TravelType1 ='"+ travelModes.get(0).toString()+"'";
				for(int i =1;i<travelModes.size();i++)
					sql+=" AND TravelType"+(i+1)+"='"+travelModes.get(i).toString()+"'";

				ArrayList<Map<String,Object>> temp =executeQuery(sql);
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("ShipID");

				}
				MarkClean();
				MarkOld();
			}
			else
			{
				if(isDirty())
				{
					String sql ="Update Location Set Name = '" + this.getName() + "' , Latitude = '"+this.getLatitude()+
							"' , Longitude = '" + this.getLongitude() + "' , TravelType1 = '" + this.travelModes.get(0).toString()+ "'";
					for(int i = 1; i< this.travelModes.size();i++)
					{
						sql+= " , TravelType"+(i+1)+" = '" + travelModes.get(i).toString() + "'";
					}

					sql += " Where LocationID = "+this.id;
					executeCommand(sql);
					MarkClean();
				}
			}
			return true;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			return false;
		}
		
	}//End of Update()

	/**
	 * This is the overridden Delete function, it will remove this Location from the database
	 */
	@Override
	public  boolean Delete() 
	{
		try
		{
			executeCommand("Delete From location where locationID = " + id);
			return true;
		}
		catch(Exception ex)
		{
			System.out.println("Error "+ ex);
			return false;
		}

	}//End of Delete()
	
	/**
	 * This function will load the Location from the database using the given id
	 * @param id This is the id of the Location to load from the database
	 * @return This is the Location loaded from the database based on the id
	 */
	public static Location Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Location where LocationId = " + id);
			if(temp.size()>0)
				return BuildFromDataRow(temp.get(0));
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}//End of Load(int id)
	
	/**
	 * This function will return an ArrayList of Locations from the database based on the given where clause
	 * @param where This is the where clause that determines which Locations to load from the database
	 * @return Returns an ArrayList of Locations loaded from the database determined by the where clause
	 */
	public static ArrayList<Location> LoadAll(String where)
	{
		ArrayList<Location> temp = new ArrayList<Location>();
		try
		{
			ArrayList<Map<String,Object>> data = executeQuery("Select * from Location " + where);
			for(int i = 0 ;i< temp.size();i++)
				temp.add(BuildFromDataRow(data.get(i)));
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return temp;
	}//End of LoadAll (String where)
	
	/**
	 * This function will build a new Location object from the passed in data
	 * @param data This is the data that will be used to build the Location object
	 * @return Returns a new Location from the passed in data
	 * @throws SQLException
	 */
	private static Location BuildFromDataRow(Map<String,Object> data)throws SQLException
	{
		Location temp = new Location((Integer)data.get("LocationID"));
		temp.setLatitude(Double.parseDouble(data.get("Latitude").toString()));
		temp.setLongitude(Double.parseDouble(data.get("Longitude").toString()));
		temp.setName((String)data.get("Name"));//rs.getString("Name"));
		temp.setCountry((String)data.get("Country"));
		temp.setState((String)data.get("State"));
		temp.addTravelMode(Vehicle.loadMode((String)data.get("TravelType1")));//rs.getString("TravelType1")));
		if((String)data.get("TravelType2")!=null)
		{
			temp.addTravelMode(Vehicle.loadMode((String)data.get("TravelType1")));
			if((String)data.get("TravelType3")!=null)
			{
				temp.addTravelMode(Vehicle.loadMode((String)data.get("TravelType3")));
				if((String)data.get("TravelType4")!=null)
				{
					temp.addTravelMode(Vehicle.loadMode((String)data.get("TravelType4")));
				}
				if((String)data.get("TravelType5")!=null)
				{
					temp.addTravelMode(Vehicle.loadMode((String)data.get("TravelType5")));
					if((String)data.get("TravelType6")!=null)
					{
						temp.addTravelMode(Vehicle.loadMode((String)data.get("TravelType6")));
					}
				}
			}
		}
		temp.MarkClean();

		return temp;
	}//End of BuildFromDataRow(Map<String,Object> data)
	
	/**
	 * This function adds an incoming vehicle to the Location
	 * @param arrivingVehicle This is the vehicle arriving at the Location
	 */
	public void VehicleArriving(Vehicle arrivingVehicle)
	{
		this.vehiclesAtLocation.add(arrivingVehicle);
	}//End of VehicleArriving(Vehicle arrivingVehicle)
	
	/**
	 * This function removes a departing vehicle from the Location
	 * @param departingVehicle This is the vehicle leaving from the Location
	 */
	public void VehicleDeparting(Vehicle departingVehicle)
	{
		this.vehiclesAtLocation.remove(departingVehicle);
	}//End of VehicleDeparting(Vehicle departingVehicle)

}//End of Location Class
