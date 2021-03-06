/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 */
package core;
import java.util.ArrayList;

import GUI.Log;


public abstract class Vehicle extends BaseClass {
	
	/**
	 * This is an enumeration for the different TravelModes that the shipment may use
	 */
	public static enum TravelModes 
	{
			TRUCK ("TRUCK"),
			RAIL("RAIL"),
			CARGO("CARGO"),
			PLANE("PLANE"),
			BIKE("BIKE"),
			ALL("ALL"),
			NONE("NONE");
			private String mode;
			TravelModes(String s)
			{
				mode=s;
			}
			@Override public String toString()
			{
				return mode;
			}

	}//End of TravelModes enumeration
	
	//Modifier Variables For Each Carrier.  Tries to introduce real world differences between carriers
	public static double [] COST_MODIFIER_PLANE = {0.8,0.9,1.0,1.1,1.2};
	
	
	
	/**
	 * This is an enumeration for the different states that a vehicle may find itself in
	 */
	public static enum Status
	{
		Running("RUNNING"),
		Delayed("DELAYED"),
		Disabled("DISABLED");
		private String status;
		Status(String s)
		{
			status=s;
		}
		@Override public String toString()
		{
			return status;
		}
		
	}//End of Status enumeration

	protected Carrier carrier;						//The carrier for the vehicle
	protected String name;							//The name of the vehicle
	protected Status status;						//The current status of the vehicle
	protected TravelModes mode;						//The type of vehicle
	protected int id;								//The unique vehicle ID
	private ArrayList<Segment> schedule;
	private ArrayList<TravelType> typesAvail;
	
	/**
	 * Sets the travel mode of the vehicle 
	 * @param newTravelMode This is the new travel mode for the Vehicle
	 */
	protected void setTravelMode(TravelModes newTravelMode)
	{
		//NEED ERROR CHECKING
		if(mode==null || !mode.equals(newTravelMode))	//Make sure we have a valid type
		{
			mode=newTravelMode;							//Set the type to t
			MarkDirty();								//Mark the vehicle as dirty
		}//End of if statement
	}//End of setTravelMode(TravelType newTravelMode)
	
	/**
	 * Sets the travel mode of the vehicle from a string
	 * @param newTravelMode This is the new travel mode for the Vehicle
	 */
	protected void setTravelMode(String newTravelMode)
	{
		if(!mode.toString().equals(newTravelMode))	//Make sure we have a valid type
		{
			if(FormatChecker.isEnumerated(Vehicle.TravelModes.class, newTravelMode))
			{
				mode = loadMode(newTravelMode);							//Set the type of the vehicle
				MarkDirty();								//Mark the vehicle as dirty
			}
			else
			{
				mode = loadMode("NONE");
				Log.writeLogWarning("Invalid travel mode in Vehicle. Not a valid mode. Setting mode to "
						+ " NONE.");
			}
			
		}
		else 
		{
			mode = loadMode("NONE");
			Log.writeLogWarning("Invalid travel mode in Vehicle. Not a valid mode. Setting mode to "
					+ " NONE.");
		}
	}//End of setTravelMode(String t)
		
	/**
	* Converts string to a TravelMode
	* @param t TravelMode name
	* @return TravelMode 
	*/
	public static TravelModes loadMode(String t)
	{
		if(t.equals(TravelModes.BIKE.toString()))
			return TravelModes.BIKE;
		if(t.equals(TravelModes.CARGO.toString()))
			return TravelModes.CARGO;
		if(t.equals(TravelModes.PLANE.toString()))
			return TravelModes.PLANE;
		if(t.equals(TravelModes.RAIL.toString()))
			return TravelModes.RAIL;
		if(t.equals(TravelModes.TRUCK.toString()))
			return TravelModes.TRUCK;
		if(t.equals(TravelModes.NONE.toString()))
			return TravelModes.NONE;
		if(t.equals(TravelModes.ALL.toString()))
			return TravelModes.ALL;
		return null;
	}//End of loadType(String t)
		
	/**
	* This function returns the TravelType of the vehicle
	* @return The TravelType of the vehicle
	*/
	public String getTravelMode()
	{
		return mode.toString();
	}//End of getTravelType
		
	/**
	* This function sets the id of the vehicle
	* @param ID of the vehicle
	*/
	public void setId(int id)
	{
		this.id=id;											//Set the id
		MarkDirty();										//Mark the vehicle as dirty
	}//End of setId(int id)
		
	/**
	* This function returns the id of the vehicle
	* @return ID of the vehicle
	*/
	public int  getId()
	{
		return id;				
	}//End of getId()
		
	/**
	* This function sets the status of the vehicle
	* @param s Status of the vehicle
	*/
	public void setStatus(Status s)
	{
		if(status == null || status!=(s))
		{
			status=s;										//Set the status
			MarkDirty();									//Mark the vehicle as dirty
		}//End of the if statement
	}//End of setStatus(Status s)
		
	/**
	* This function sets the status of the vehicle based on a string
	* @param s string Status of the vehicle
	*/
	public void setStatus(String s)
	{
		if(status==null || !status.toString().equals(s))
		{
			status=loadStatus(s);								//Set the status of the vehicle
			MarkDirty();										//Mark the vehicle as dirty
		}
	}//End of the setStatus(String s) function
		
	//This function will take a string and return the appropriate status
	/**
	* This function returns a status based on the string passed
	* @param val String value for status
	* @return Status type 
	*/
	public static Status loadStatus(String val)
	{
		if(val.equals(Status.Delayed.toString()))
		{
			return Status.Delayed;
		}//End of if value == delayed
		else
		{
			if(val.equals(Status.Disabled.toString()))
			{
				return Status.Disabled;
			}//End of if value ==  disabled
			else
			{
				return Status.Running;
			}//End of value == running else
		}//End of value != delayed else
	}//End of loadStatus(String val)
		
	/**
	* This function returns the status of the vehicle
	* @return Status of the vehicle
	*/
	public Status getStatus()
	{
		return status;
	}//End of getStatus
		
	/**
	* This function sets the carrier of the vehicle
	* @param c Carrier of the vehicle
	*/
	public void setCarrier(Carrier c)
	{
			carrier=c;											//Set the carrier
			MarkDirty();											//Mark the vehicle as dirty
	}//End of setCarrier(Carrier c)
		
	/**
	* This function returns the Carrier of the vehicle
	* @return Carrier of the vehicle
	*/
	public Carrier getCarrier()
	{
		return carrier;
	}//End of getCarrier()
		
	/**
	* This function sets the vehicle's name 
	* @param name String name of the vehicle
	*/
	public void setVehicleName(String name)
	{
		if(this.name==null || !this.name.equals(name) )
		{
			this.name=name;								//Set the vehicle name
			MarkDirty();								//Mark the vehicle as dirty
		}//End of valid name if
	}//End of setVehicleName(String name)
		
	/**
	* This function returns the name of the vehicle
	* @return name of the vehicle
	*/
	public String getVehicleName()
	{
		return name;									//Return the name
	}//End of getVehicleName()
		
	//Abtract Functions
	abstract boolean Update();
	abstract boolean Delete();
	
	/**
	 * This function will return an ArrayList of Segments of all the that the Vehicle will visit
	 * @return Returns an Arraylist of the Segments 
	 */
	public ArrayList<Segment> getSchedule()
	{
		if(schedule==null)
			schedule=Segment.LoadAll("where ModeType = '"+mode.toString()+"' and VehicleID= " + id);
		return schedule;
	}

	/**
	* This function adds a segment to the trucks schedule
	* @param s Segment to be added to schedule
	*/
	public void addToSchedule(Segment s)
	{
		getSchedule().add(s);
		s.Update();
	}
	
	public void removeFromTypes(TravelType t)
	{
		this.typesAvail.remove(t);
		t.removeFromVehicle(this.id,this.mode.toString());
	}
	public void addToTypes(TravelType t)
	{
		if(typesAvail == null){
			typesAvail = new ArrayList<TravelType>();
		}
		this.typesAvail.add(t);
		t.addToVehicle(this.id,this.mode.toString());
		this.MarkDirty();
	}
	
	public ArrayList<TravelType> getAvailableTypes()
	{
		//if(typesAvail == null)
			typesAvail=TravelType.LoadForVehicle(this.getId(), this.getTravelMode());
		return typesAvail;
		
	}

	/**
	 * This function returns the name of the vehicle
	 */
	@Override
	public String toString(){
		return this.id+"-"+this.name;
	}
	

}//End of the Vehicle Class
