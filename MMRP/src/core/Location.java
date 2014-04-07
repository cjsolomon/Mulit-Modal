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
	public Location()
	{
		travelModes=new ArrayList<Vehicle.TravelModes>();
		vehiclesAtLocation=new ArrayList<Vehicle>();
		MarkNew();
	}
	public Location(int id)
	{
		this.id=id;
		travelModes=new ArrayList<Vehicle.TravelModes>();
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setLatitude(double lat)
	{
		if(this.latitude!=lat)
		{
			this.latitude=lat;
			MarkDirty();
		}
	}
	
	public double getLatitude()
	{
		return this.latitude;
	}
	
	public void setState(String st)
	{
		if(state==null || !this.state.equals(st)) 
		{
			this.state = st; 
			MarkDirty();
		}
	}
	
	public String getState() 
	{
		return this.state;
	}
	
	public void setCountry(String nation)
	{
		if(country==null || !this.country.equals(nation))
		{
			this.country = nation;
			MarkDirty();
		}
	}
	
	public String getCountry()
	{
		return this.country;
	}
	
	public void setLongitude(double lon)
	{
		if(this.longitude!=lon)
		{
			this.longitude=lon;
			MarkDirty();
		}
	}
	public double getLongitude()
	{
		return this.longitude;
	}
	
	public void addTravelMode(Vehicle.TravelModes mode)
	{
		if(!travelModes.contains(mode))
		{
			travelModes.add(mode);
			MarkDirty();
		}
	}
	
	public boolean travelTypeAvailable(Vehicle.TravelModes mode)
	{
		if(travelModes.contains(mode))
		{
			return true;
		}
		return false;
	}
	
	public void setName(String s)
	{
		if(name==null || !name.equals(s))
		{
			name=s;
			MarkDirty();
		}
	}
	
	public String getName()
	{
		return name;
	}
	

	@Override
	public void Update() 
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
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		
	}

	@Override
	public  void Delete() 
	{
		try
		{
			executeCommand("Delete From location where locationID = " + id);
		}
		catch(Exception ex)
		{
			System.out.println("Error "+ ex);
		}

	}
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
	}
	
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
	}
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
	}
	public void VehcileArriving(Vehicle v)
	{
		this.vehiclesAtLocation.add(v);
	}
	public void VehicleDeparting(Vehicle v)
	{
		this.vehiclesAtLocation.remove(v);
	}

}
