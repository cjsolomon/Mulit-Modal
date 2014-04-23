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
		zeus.VRPTruckType tt = generateZeusTruckType(currSeg);  //Construct a Zeus-Truck Type for this truck
		tt.setContractorName(mmrpTruck.getCarrier().getCarrierName());
		zeus.Truck zeusTruck = new zeus.Truck();   
		zeusTruck.setTruckType(tt);
		return zeusTruck;  //Return the Zeus Truck
	}
	/**
	 * 
	 * @param segment
	 * @return Truck Type
	 */
	public static zeus.VRPTruckType generateZeusTruckType(core.Segment seg)
	{
		zeus.VRPTruckType type = new zeus.VRPTruckType();
		float capacity = (float)seg.getTravelType().getMaxCap();
		type.setMaxCapacity(capacity);
		//setMaxServiceType(str customer type)
		//setTruckNo(int)
		//setMaxDuration(double)
		return type;
	}
	
	/**
	 * 
	 * @param Location from
	 * @param Location to
	 * @return zeusShipment
	 * This function converts a MMRP Shipment into a Zeus Shipment 
	 */
	public static zeus.Shipment generateZeusShipment(core.Location from, core.Location to, core.Shipment ship)
	{
		zeus.Shipment zeusShipment = new zeus.Shipment();
		zeusShipment.setFromLatCoord(from.getLatitude());
		zeusShipment.setFromLongCoord(from.getLongitude());
		zeusShipment.setToLatCoord(to.getLatitude());
		zeusShipment.setToLongCoord(to.getLongitude());
		String [] temp = new String[1];
		
		temp[0] = ship.getHazmat();
		zeusShipment.setHazmat(temp);
		
		temp[0] = ship.getPrefCarriers();
		zeusShipment.setPrefCarriers(temp);
		
		temp[0] = ship.getTrailerType();
		zeusShipment.setTrailerType(temp);
		
		temp[0] = ship.getUnloadType();
		zeusShipment.setUnloadType(temp);
		zeusShipment.setLoadType(temp);
		
		zeusShipment.setLoadingRate(ship.getLoadingRate());
		zeusShipment.setUnloadingRate(ship.getLoadingRate());
		zeusShipment.setLocalBypass(ship.getCongestionByPass());
		zeusShipment.setTollRoad(ship.getTollRoads());
		zeusShipment.setMaxStops(ship.getMaxStops());
		
		return zeusShipment;
	}

}
