//======================================================================================//
//							Software Engineering MMRP Project							//
//									Dr. Sam Thangiah									//
//					Dan Miller, Zach Petrusch, Chris Solomon, and Jordan Schiller		//
//======================================================================================//
//									Zeus Interface										//
//======================================================================================//

package zeusInterface;

public class ZeusInterface {
	/**
	 * 
	 * @param mmrpTruck, currentSegment 
	 * @return zeusTruck
	 * This function returns a Zeus Truck. Must be passed an MMRP Truck and the current Segment 
	 */
	public static zeus.Truck generateZeusTruck(core.Truck mmrpTruck, core.Segment currSeg)
	{
		zeus.VRPTruckType tt = generateTruckType(currSeg);  //Construct a Zeus-Truck Type for this truck
		//float x = mmrpTruck.getCarrier().getLatitude();
		//float y = mmrpTruck.getCarrier().getLongitude();
		zeus.Truck zeusTruck = new zeus.Truck(tt, 0, 0);   //Initialize the Truck, with the proper TruckType
		//Depot X and Y???
		return zeusTruck;  //Return the Zeus Truck
	}
	/**
	 * 
	 * @param segment
	 * @return Truck Type
	 */
	public static zeus.VRPTruckType generateTruckType(core.Segment seg)
	{
		zeus.VRPTruckType type = new zeus.VRPTruckType();
		float capacity = (float)seg.getTravelType().getMaxCap();
		type.setMaxCapacity(capacity);
		//setMaxServiceType(str customer type)
		//setTruckNo(int)
		//setMaxDuration(double)
		/**TODO
		 * Type Number? (Truck#)
		 * Max duration?
		 * Type of customers? 
		 */
		return type;
	}
	
	/**
	 * 
	 * @param mmrpShipment
	 * @return zeusShipment
	 * This function converts a MMRP Shipment into a Zeus Shipment 
	 */
	public static zeus.Shipment translateToZeus(core.Shipment mmrpShipment)
	{
		zeus.Shipment zeusShipment = new zeus.Shipment();
		/**TODO
		 * setSelected, setAssigned?
		 * setDemand?
		 * Setters don't exist for most fields in Zeus shipment (lat, long, toX, toY, fromX, fromY)
		 */
		return zeusShipment;
	}

}
