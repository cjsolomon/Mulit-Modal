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
	 * @param mmrpTruck
	 * @return zeusTruck
	 * This function converts a MMRP Truck into a Zeus Truck 
	 */
	static zeus.Truck translateToZeus(core.Truck mmrpTruck)
	{
		zeus.VRPTruckType tt = generateTruckType(mmrpTruck);  //Construct a Zeus-Truck Type for this truck
		float x = (float)mmrpTruck.getLatitude();
		float y = (float)mmrpTruck.getLongitude();
		zeus.Truck zeusTruck = new zeus.Truck(tt, x, y);   //Initialize the Truck, with the proper TruckType
		//Depot X and Y???
		return zeusTruck;  //Return the Zeus Truck
	}
	
	static zeus.VRPTruckType generateTruckType(core.Truck mmrpTruck)
	{
		zeus.VRPTruckType type = new zeus.VRPTruckType();
		type.setMaxCapacity(mmrpTruck.getCapacity());
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
	static zeus.Shipment translateToZeus(core.Shipment mmrpShipment)
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
