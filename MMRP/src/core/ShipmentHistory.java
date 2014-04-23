package core;
import java.util.ArrayList;
import java.util.Map;


public class ShipmentHistory extends BaseClass {

	private int id;
	private int segmentID;
	private int shipmentID;
	private int nodeNumber;
	
	
	//Default Variables
	private static int DEFAULT_NODE_NUMBER = 0;
	private static int DEFAULT_SEGMENT_ID = 1;
	private static int DEFAULT_SHIPMENT_ID = 1;
	
	/**
	 * This is the default constructor for the ShipmentHistory
	 */
	public ShipmentHistory()
	{
		this.nodeNumber = DEFAULT_NODE_NUMBER;
		this.segmentID = DEFAULT_SEGMENT_ID;
		this.shipmentID = DEFAULT_SHIPMENT_ID;
		MarkClean();
		MarkNew();
		MarkUndeleted();
	}//End of ShipmentHistory()
	
	/**
	 * This is the argumented constructor for the ShipmentHistory
	 * @param id This is the new id for the ShipmentHistory
	 */
	public ShipmentHistory(int id)
	{
		this.id=id;
		this.nodeNumber = DEFAULT_NODE_NUMBER;
		this.segmentID = DEFAULT_SEGMENT_ID;
		this.shipmentID = DEFAULT_SHIPMENT_ID;
		MarkClean();
	}//End of ShipmentHistory(int id)
	
	/**
	 * This function will return the unique id of the ShipmentHistory assigned by the database
	 * @return Returns the unique id assigned by the database
	 */
	public int getShipmentHistoryID(){
		return this.id;
	}//End of getShipmentHistoryID()
	
	/**
	 * This function will set the Segment's id for this ShipmentHistory
	 * @param id This is the new Segment id for the ShipmentHistory
	 */
	public void setSegmentID(int id)
	{
		//NEED SOME ERROR CHECKING
		if(this.segmentID!=id)
		{
			segmentID=id;
			MarkDirty();
		}
	}//End of setSegmentID(int id)
	
	/**
	 * This function will return the id of the Segment for this ShipmentHistory
	 * @return Returns the Segment id for the ShipmentHistory
	 */
	public int getSegmentID()
	{
		return segmentID;
	}//End of getSegmentID()
	
	/**
	 * This function will return the Segment for this ShipmentHistory
	 * @return Returns the Segment for this ShipmentHistory
	 */
	public Segment getSegment()
	{
		return Segment.LoadAll("where SegmentID = " + this.segmentID).get(0);
	}//End of getSegment()
	
	/**
	 * This function sets the Shipment id
	 * @param id This is the new Shipment id
	 */
	public void setShipmentID(int id)
	{
		//NEED SOME ERROR CHECKING
		if(this.shipmentID!=id)
		{
			shipmentID=id;
			MarkDirty();
		}
	}//End of setShipmentID(int id)
	
	/**
	 * This function returns the Shipment id for the ShipmentHistory
	 * @return Returns the Shipment id 
	 */
	public int getShipmentID()
	{
		return this.shipmentID;
	}//End of getShipmentID()
	
	/**
	 * This function returns the Shipment object for this ShipmentHistory
	 * @return Returns the Shipment
	 */
	public Shipment getShipment()
	{
		return Shipment.Load(this.shipmentID);
	}//End of getShipment()
	
	/**
	 * This function sets the node number for the ShipmentHistory
	 * @param newNodeNumber This is the new node number for the ShipmentHistory
	 */
	public void setNodeNumber(int newNodeNumber)
	{
		//NEED ERROR CHECKING
		if(this.nodeNumber!=newNodeNumber)
		{
			nodeNumber=newNodeNumber;
			MarkDirty();
		}
	}//End of setNodeNumber(int newNodeNumber)
	
	/**
	 * This function returns the node number for the ShipmentHistory
	 * @return Returns the node number 
	 */
	public int getNodeNumber()
	{
		return nodeNumber;
	}//End of getNodeNumber()
	
	/**
	 * This function will return an ArrayList of ShipmentHistory for a given Shipment id
	 * @param id This is the id of the Shipment that will be used to retreive all the ShipmentHistory
	 * @return Returns an ArrayList of ShipmentHistory for the given Shipment
	 */
	public static ArrayList<ShipmentHistory> LoadAllForShipment(int id)
	{
		ArrayList<ShipmentHistory> returnList = new ArrayList<ShipmentHistory>();
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from ShipmentHistory where ShipmentID = " + id  + " AND Deleted = false" + " order by NodeNumber");
			for(int i = 0;i<temp.size();i++)
			{
				returnList.add(BuildFromDataRow(temp.get(i)));
			}
			return returnList;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			ex.printStackTrace();
		}
		return null;
	}//End of LoadAllForShipment(int id)
	
	/**
	 * This function will build a new ShipmentHistory object from the passed in data
	 * @param data This is the data that will be used to build the ShipmentHistory
	 * @return Returns a new ShipmentHistory build from the passed in data
	 */
	public static ShipmentHistory BuildFromDataRow(Map<String,Object> data)
	{
		ShipmentHistory sh = new ShipmentHistory(Integer.parseInt(data.get("ShipmentHistoryID").toString()));
		sh.setSegmentID(Integer.parseInt(data.get("SegmentID").toString()));
		sh.setShipmentID((Integer)data.get("ShipmentID"));
		sh.setNodeNumber((Integer)data.get("NodeNumber"));
		if(Boolean.getBoolean(data.get("Deleted").toString()))
			sh.MarkDeleted();
		else
			sh.MarkUndeleted();
		sh.MarkClean();
		return sh;
	}//End of BuildFromDataRow(Map<String,Object> data)
	
	/**
	 * This function will update the ShipmentHistory in the database
	 */
	@Override
	boolean Update() {
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the plane is new insert it into the database by executing the following
				executeCommand("Insert into ShipmentHistory (SegmentID,ShipmentID,NodeNumber) Values ('"+
						this.segmentID+"','"+this.shipmentID+"','"+this.nodeNumber+"')");
				//Grab this plane from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select ShipmentHistoryID from ShipmentHistory where SegmentID = '" + this.segmentID + "' AND ShipmentID = '"+this.shipmentID+
						"' AND NodeNumber = '" + this.nodeNumber+ "'");
				//If this plane exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(temp.size()-1).get("ShipmentHistoryID");								//Set this Plane's id to the database id
					MarkClean();																//Mark the Plane as clean
					MarkOld();																	//Mark the Plane as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Plane is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update ShipmentHistory Set SegmentID = '" + this.segmentID + "' , ShipmentID = '"+this.shipmentID+
						"' , NodeNumber = '" + this.nodeNumber + "', deleted = " + this.isDeleted() + " where ShipmentHistoryID ='"+this.id+"'");
					MarkClean();
				}//End of isDirty if
			}//End of isOld else
			return true;
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);													//Print out the error
			ex.printStackTrace();
			return false;
		}//End of catch block
	}//End of Update()

	/**
	 * This function will mark the ShipmentHistory as deleted
	 */
	@Override
	boolean Delete() {
		try
		{
			executeCommand("Update ShipmentHistory Set Deleted = true where ShipmentHistoryID = '"+this.id+"'");
			this.MarkDeleted();
			return true;
		}
		catch(Exception ex)
		{
			System.out.println("Error "+ex);
			ex.printStackTrace();
			return false;
		}

	}//End of Delete()

}//End of ShipmentHistory Class
