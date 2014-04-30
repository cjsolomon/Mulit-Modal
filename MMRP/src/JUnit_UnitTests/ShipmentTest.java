package JUnit_UnitTests;

import java.util.ArrayList;

import core.Location;
import core.Segment;
import core.Shipment;
import core.ShipmentHistory;
import core.Shipper;
import core.Vehicle;

import org.junit.*;

public class ShipmentTest {

	@Test
	public void testDefaultConstructor() {
		Shipment test_shipment = new Shipment();

		Assert.assertFalse(test_shipment.getCongestionByPass());
		Assert.assertEquals(Shipment.getDefaultEarliestArrival(), test_shipment.getEarliestArrivalTime());
		Assert.assertEquals(Shipment.getDefaultEarliestDeparture(), test_shipment.getEarliestDepartureTime());
		Assert.assertEquals(Shipment.getDefaultStartLocationId(), test_shipment.getFromLocationID());
		Assert.assertEquals(Shipment.getDefaultHazmatConstraints(),test_shipment.getHazmat());
		Assert.assertEquals(Shipment.getDefaultLatestArrival(), test_shipment.getLatestArrivalTime());
		Assert.assertEquals(Shipment.getDefaultLatestDeparture(), test_shipment.getLatestDepartureTime());
		Assert.assertEquals(Shipment.getDefaultLoadingType(),test_shipment.getLoadingType());
		Assert.assertEquals(Shipment.getDefaultLoadingRate(),test_shipment.getLoadingRate());
		Assert.assertEquals(Shipment.getDefaultMaxStops(), test_shipment.getMaxStops());
		Assert.assertEquals(Shipment.getDefaultPreferredCarriers(),test_shipment.getPrefCarriers());
		Assert.assertEquals(Shipment.getDefaultPriority(),test_shipment.getPriority());
		Assert.assertEquals(Shipment.getDefaultShipperId(), test_shipment.getShipperID());
		Assert.assertEquals(new Double(Shipment.getDefaultSize()), new Double(test_shipment.getSize()));
		Assert.assertEquals(Shipment.getDefaultTimeToLoad(), test_shipment.getTimeToLoad());
		Assert.assertEquals(Shipment.getDefaultTimeToUnload(), test_shipment.getTimeToUnload());
		Assert.assertFalse(test_shipment.getTollRoads());
		Assert.assertEquals(Shipment.getDefaultEndLocationId(), test_shipment.getToLocationID());
		Assert.assertEquals(Shipment.getDefaultTrailerType(), test_shipment.getTrailerType());
		Assert.assertEquals(Shipment.getDefaultUnloadingType(), test_shipment.getUnloadType());
		Assert.assertEquals(new Double(Shipment.getDefaultWeight()),new Double( test_shipment.getWeight()));
		Assert.assertTrue(test_shipment.isNew());
		Assert.assertFalse(test_shipment.isDirty());
		Assert.assertFalse(test_shipment.isDeleted());
	}

	@Test
	public void testArgumentConstructor() {
		Shipment test_shipment = new Shipment();
		int test_id[] = {23,17,456,88,2};

		for (Integer id : test_id) {
			test_shipment = new Shipment(id);
			Assert.assertEquals(id, new Integer(test_shipment.getId()));
		}
	}

	@Test
	public void testSetCurrentLocation() {
		Shipment test_shipment = new Shipment();
		int test_location[] = {23,17,456,88,2};

		for (Integer loc : test_location) {
			test_shipment.setCurrentLocation(loc);
			Assert.assertEquals(loc, new Integer(test_shipment.getCurrentLocationID()));
		}
		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetFromLocationId() {
		Shipment test_shipment = new Shipment();
		int test_id[] = {23,17,456,88,2};

		for (Integer id : test_id) {
			test_shipment.setFromLocationID(id);
			Assert.assertEquals(id, new Integer(test_shipment.getFromLocationID()));
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetToLocationId() {
		Shipment test_shipment = new Shipment();
		int test_id[] = {23,17,456,88,2};

		for (Integer id : test_id) {
			test_shipment.setToLocationID(id);
			Assert.assertEquals(id, new Integer(test_shipment.getToLocationID()));
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetPriority() {
		Shipment test_shipment = new Shipment();
		int good_priority[] = {0,1,2,3,4,5,10};
		int bad_priority[] = {-1,11,12};

		for (Integer priority : good_priority) {
			test_shipment.setPriority(priority);
			Assert.assertEquals(priority, new Integer(test_shipment.getPriority()));
		}

		for (Integer priority : bad_priority) {
			test_shipment.setPriority(priority);
			Assert.assertEquals(Shipment.getDefaultPriority(), test_shipment.getPriority());
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetSize() {
		Shipment test_shipment = new Shipment();
		Double good_size[] = {0.0,100.0,23.0,35.6};
		Double bad_size[] = {-.0000001,100.00000001,123.6,-12.99999};

		for (Double size : good_size) {
			test_shipment.setSize(size);
			Assert.assertEquals(size, new Double(test_shipment.getSize()));
		}

		for (Double size : bad_size) {
			test_shipment.setSize(size);
			Assert.assertEquals(new Double(Shipment.getDefaultSize()), new Double(test_shipment.getSize()));
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetEarliestArrivalTime() {
		Shipment test_shipment = new Shipment();
		Integer test_number = 17;

		test_shipment.setEarliestArrivalTime(test_number);
		Assert.assertEquals(test_number, new Integer(test_shipment.getEarliestArrivalTime()));

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetLatestArrivalTime() {
		Shipment test_shipment = new Shipment();
		Integer test_number = 17;

		test_shipment.setLatestArrivalTime(test_number);
		Assert.assertEquals(test_number, new Integer(test_shipment.getLatestArrivalTime()));

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetEarliestDepartureTime() {
		Shipment test_shipment = new Shipment();
		Integer test_number = 17;

		test_shipment.setEarliestDepartureTime(test_number);
		Assert.assertEquals(test_number, new Integer(test_shipment.getEarliestDepartureTime()));

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetLatestDepartureTime() {
		Shipment test_shipment = new Shipment();
		Integer test_number = 17;

		test_shipment.setLatestDepartureTime(test_number);
		Assert.assertEquals(test_number, new Integer(test_shipment.getLatestDepartureTime()));

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testGetId() {
		Shipment test_shipment = new Shipment();
		Integer testId = new Integer(test_shipment.getId());

		Assert.assertEquals(new Integer(test_shipment.getId()), testId);
	}

	@Test
	public void testSetTimeToLoad() {
		Shipment test_shipment = new Shipment();
		int good_load_time[] = {0,1,2,75,23,99,100};
		int bad_load_time[] = {-1,-2,-3,101,102,103};

		for (Integer time : good_load_time) {
			test_shipment.setTimeToLoad(time);
			Assert.assertEquals(time, new Integer(test_shipment.getTimeToLoad()));
		}

		for (Integer time : bad_load_time) {
			test_shipment.setTimeToLoad(time);
			Assert.assertEquals(Shipment.getDefaultTimeToLoad(), test_shipment.getTimeToLoad());
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetTimeToUnload() {
		Shipment test_shipment = new Shipment();
		int good_load_time[] = {0,1,2,75,23,99,100};
		int bad_load_time[] = {-1,-2,-3,101,102,103};

		for (Integer time : good_load_time) {
			test_shipment.setTimeToUnload(time);
			Assert.assertEquals(time, new Integer(test_shipment.getTimeToUnload()));
		}

		for (Integer time : bad_load_time) {
			test_shipment.setTimeToUnload(time);
			Assert.assertEquals(Shipment.getDefaultTimeToLoad(), test_shipment.getTimeToUnload());
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetShipperId() {
		Shipment test_shipment = new Shipment();
		Integer test_id = new Integer(17);

		test_shipment.setShipperID(test_id);
		Assert.assertEquals(test_id, new Integer(test_shipment.getShipperID()));

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testGetShipper() {
		Shipment test_shipment = new Shipment();
		Shipper test_shipper = new Shipper();
		test_shipper.setContactName("Shipment JUnit testGetShipper");
		test_shipment.setHazmat("Shipment JUnit testGetShipper");
		test_shipper.Update();
		test_shipment.setShipperID(test_shipper.getID());
		test_shipment.Update();

		Assert.assertEquals(test_shipment.getShipperID(), test_shipper.getID());

		test_shipment.Delete();
		test_shipper.Delete();		
	}

	@Test
	public void testSetTollRoads() {
		Shipment test_shipment = new Shipment();

		test_shipment.setTollRoadsTrue();
		Assert.assertTrue(test_shipment.getTollRoads());
		Assert.assertTrue(test_shipment.isDirty());

		test_shipment.MarkClean();

		test_shipment.setTollRoadsFalse();
		Assert.assertFalse(test_shipment.getTollRoads());
		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetCongestionByPass() {
		Shipment test_shipment = new Shipment();

		test_shipment.setCongestionByPassTrue();
		Assert.assertTrue(test_shipment.getCongestionByPass());
		Assert.assertTrue(test_shipment.isDirty());

		test_shipment.MarkClean();

		test_shipment.setCongestionByPassFalse();
		Assert.assertFalse(test_shipment.getCongestionByPass());
		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetHazmat() {
		Shipment test_shipment = new Shipment();
		String test_string = new String("This is the Hazmat test string");

		test_shipment.setHazmat(test_string);
		Assert.assertEquals(test_string,test_shipment.getHazmat());
		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetMaxStops() {
		Shipment test_shipment = new Shipment();
		int good_stops[] = {1,2,3,75,23,99,100,488,489,998,999,1000};
		int bad_stops_low[] = {-3,-2,-1};
		int bad_stops_high[] = {1001,1002,1003};

		for (Integer stops : good_stops) {
			test_shipment.setMaxStops(stops);
			Assert.assertEquals(stops, new Integer(test_shipment.getMaxStops()));
		}

		for (Integer stops : bad_stops_low) {
			test_shipment.setMaxStops(stops);
			Assert.assertEquals(Shipment.getDefaultMaxStops(), test_shipment.getMaxStops());
		}

		for (Integer stops : bad_stops_high) {
			test_shipment.setMaxStops(stops);
			Assert.assertEquals(Shipment.getDefaultMaxStops(), test_shipment.getMaxStops());
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetLoadRate() {
		Shipment test_shipment = new Shipment();
		int good_load_time[] = {1,2,75,23,99,100};
		int bad_load_time[] = {-1,-2,-3,101,102,103,0};

		for (Integer time : good_load_time) {
			test_shipment.setLoadingRate(time);
			Assert.assertEquals(time, new Integer(test_shipment.getLoadingRate()));
		}

		for (Integer time : bad_load_time) {
			test_shipment.setLoadingRate(time);
			Assert.assertEquals(Shipment.getDefaultLoadingRate(), test_shipment.getLoadingRate());
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetUnloadType () {
		Shipment test_shipment = new Shipment();
		String test_string = ("Keep Calm and Test On");

		test_shipment.setUnloadType(test_string);
		Assert.assertEquals(test_string, test_shipment.getUnloadType());

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetLoadType () {
		Shipment test_shipment = new Shipment();
		String test_string = ("Keep Calm and Test On");

		test_shipment.setLoadingType(test_string);
		Assert.assertEquals(test_string, test_shipment.getLoadingType());

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetTrailerType () {
		Shipment test_shipment = new Shipment();
		String test_string = ("I am a Trailer");

		test_shipment.setTrailerType(test_string);
		Assert.assertEquals(test_string, test_shipment.getTrailerType());

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetPreferredCarrierType () {
		Shipment test_shipment = new Shipment();
		String test_string = ("I am a Preferred Carrier");

		test_shipment.setPrefCarrier(test_string);
		Assert.assertEquals(test_string, test_shipment.getPrefCarriers());

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetHistory() {
		ArrayList<ShipmentHistory> history_list = new ArrayList<ShipmentHistory>();
		history_list.add(new ShipmentHistory());
		history_list.add(new ShipmentHistory());

		Shipment test_shipment = new Shipment();
		test_shipment.setHistory(history_list);

		ArrayList<ShipmentHistory> history_list2 = test_shipment.getHistory();
		Assert.assertEquals(history_list, history_list2);
		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testSetHistoryFromSegments() {
		//Setup the test segments
		Segment test_segment = new Segment();
		test_segment.setLane("JUnit testSetHistoryFromSegments");
		Segment test_segment2 = new Segment();
		test_segment2.setLane("JUnit testSetHistoryFromSegments");
		ArrayList<Segment> segList = new ArrayList<Segment>();
		segList.add(test_segment2);
		segList.add(test_segment);

		Shipment test_shipment = new Shipment();
		test_shipment.setHistoryFromSegments(segList);

		//test to ensure both test items are in the database
		Assert.assertEquals(2, test_shipment.getHistory().size());

		test_shipment.DeleteAllHistory();		
	}

	@Test
	public void testSetWeight() {
		Shipment test_shipment = new Shipment();
		Double good_weight[] = {1.0,100.0,23.0,35.6,499.99999999,500.0};
		Double bad_weight[] = {.009,5000.00000001, 9999999.0};

		for (Double weight : good_weight) {
			test_shipment.setWeight(weight);
			Assert.assertEquals(weight, new Double(test_shipment.getWeight()));
		}

		for (Double weight : bad_weight) {
			test_shipment.setWeight(weight);
			Assert.assertEquals(new Double(Shipment.getDefaultWeight()), new Double(test_shipment.getWeight()));
		}

		Assert.assertTrue(test_shipment.isDirty());
	}

	@Test
	public void testLoadStartLocation() {
		//Setup the location in the database
		Location test_location = new Location();
		String test_string = new String("JUnit Shipment.testLoadStartLocation");
		test_location.addTravelMode(Vehicle.TravelModes.RAIL);


		test_location.setName(test_string);
		test_location.Update();

		Shipment test_shipment = new Shipment();
		test_shipment.setFromLocationID(test_location.getID());
		Location test_location2 = test_shipment.loadStartLocation();
		Assert.assertEquals(new Integer(test_location.getID()), new Integer(test_location2.getID()));
		Assert.assertEquals(test_location.getCountry(), test_location2.getCountry());
		Assert.assertEquals(test_location.getName(), test_location2.getName());
		Assert.assertEquals(new Double(test_location.getLatitude()), new Double(test_location2.getLatitude()));
		Assert.assertEquals(new Double(test_location.getLongitude()), new Double(test_location2.getLongitude()));
		Assert.assertEquals(test_location.getTravelModes(), test_location2.getTravelModes());
		Assert.assertEquals(test_location.getVehiclesAtLocation(), test_location2.getVehiclesAtLocation());
		Assert.assertEquals(test_location.getState(), test_location2.getState());
		test_location.Delete();
	}

	@Test
	public void testLoadEndLocation() {
		//Setup the location in the database
		Location test_location = new Location();
		String test_string = new String("JUnit Shipment.testLoadStartLocation");
		test_location.addTravelMode(Vehicle.TravelModes.RAIL);


		test_location.setName(test_string);
		test_location.Update();

		Shipment test_shipment = new Shipment();
		test_shipment.setToLocationID(test_location.getID());
		Location test_location2 = test_shipment.loadEndLocation();
		Assert.assertEquals(new Integer(test_location.getID()), new Integer(test_location2.getID()));
		Assert.assertEquals(test_location.getCountry(), test_location2.getCountry());
		Assert.assertEquals(test_location.getName(), test_location2.getName());
		Assert.assertEquals(new Double(test_location.getLatitude()), new Double(test_location2.getLatitude()));
		Assert.assertEquals(new Double(test_location.getLongitude()), new Double(test_location2.getLongitude()));
		Assert.assertEquals(test_location.getTravelModes(), test_location2.getTravelModes());
		Assert.assertEquals(test_location.getVehiclesAtLocation(), test_location2.getVehiclesAtLocation());
		Assert.assertEquals(test_location.getState(), test_location2.getState());
		test_location.Delete();
	}

	@Test 
	public void testLoad() {
		Shipment test_shipment = new Shipment();
		String test_string = new String("JUnit ShipmetTest.testLoad()");

		test_shipment.setHazmat(test_string);
		test_shipment.Update();

		Shipment test_shipment2 = Shipment.Load(test_shipment.getId());

		Assert.assertEquals(test_shipment.getEarliestArrivalTime(),test_shipment2.getEarliestArrivalTime());
		Assert.assertEquals(test_shipment.getEarliestDepartureTime(),test_shipment2.getEarliestDepartureTime());
		Assert.assertEquals(test_shipment.getFromLocationID(),test_shipment2.getFromLocationID());
		Assert.assertEquals(test_shipment.getHazmat(),test_shipment2.getHazmat());
		Assert.assertEquals(test_shipment.getLatestArrivalTime(),test_shipment2.getLatestArrivalTime());
		Assert.assertEquals(test_shipment.getLatestDepartureTime(),test_shipment2.getLatestDepartureTime());
		Assert.assertEquals(test_shipment.getLoadingType(),test_shipment2.getLoadingType());
		Assert.assertEquals(test_shipment.getLoadingRate(),test_shipment2.getLoadingRate());
		Assert.assertEquals(test_shipment.getMaxStops(),test_shipment2.getMaxStops());
		Assert.assertEquals(test_shipment.getPrefCarriers(),test_shipment2.getPrefCarriers());
		Assert.assertEquals(test_shipment.getPriority(),test_shipment2.getPriority());
		Assert.assertEquals(test_shipment.getShipperID(), test_shipment2.getShipperID());
		Assert.assertEquals(new Double(test_shipment.getSize()), new Double(test_shipment2.getSize()));
		Assert.assertEquals(test_shipment.getTimeToLoad(), test_shipment2.getTimeToLoad());
		Assert.assertEquals(test_shipment.getTimeToUnload(), test_shipment2.getTimeToUnload());
		Assert.assertEquals(test_shipment.getToLocationID(), test_shipment2.getToLocationID());
		Assert.assertEquals(test_shipment.getTrailerType(), test_shipment2.getTrailerType());
		Assert.assertEquals(test_shipment.getUnloadType(), test_shipment2.getUnloadType());
		Assert.assertEquals(new Double( test_shipment.getWeight()),new Double(test_shipment2.getWeight()));

		test_shipment.Delete();
	}

	@Test
	public void testUpdateLoad() {
		Shipment test_shipment = new Shipment();
		test_shipment.setHazmat("JUnit Shipment.testUpdateLoad()");

		test_shipment.Update();
		ArrayList<Shipment> lList = Shipment.LoadAll(new String("where hazmatConstraints = 'JUnit Shipment.testUpdateLoad()'"));
		if (!lList.isEmpty()) {
			Shipment test_shipment2 = lList.get(0);
			Assert.assertEquals(test_shipment.getId(),test_shipment2.getId());
			Assert.assertEquals(test_shipment.getEarliestArrivalTime(),test_shipment2.getEarliestArrivalTime());
			Assert.assertEquals(test_shipment.getEarliestDepartureTime(),test_shipment2.getEarliestDepartureTime());
			Assert.assertEquals(test_shipment.getFromLocationID(),test_shipment2.getFromLocationID());
			Assert.assertEquals(test_shipment.getHazmat(),test_shipment2.getHazmat());
			Assert.assertEquals(test_shipment.getLatestArrivalTime(),test_shipment2.getLatestArrivalTime());
			Assert.assertEquals(test_shipment.getLatestDepartureTime(),test_shipment2.getLatestDepartureTime());
			Assert.assertEquals(test_shipment.getLoadingType(),test_shipment2.getLoadingType());
			Assert.assertEquals(test_shipment.getLoadingRate(),test_shipment2.getLoadingRate());
			Assert.assertEquals(test_shipment.getMaxStops(),test_shipment2.getMaxStops());
			Assert.assertEquals(test_shipment.getPrefCarriers(),test_shipment2.getPrefCarriers());
			Assert.assertEquals(test_shipment.getPriority(),test_shipment2.getPriority());
			Assert.assertEquals(test_shipment.getShipperID(), test_shipment2.getShipperID());
			Assert.assertEquals(new Double(test_shipment.getSize()), new Double(test_shipment2.getSize()));
			Assert.assertEquals(test_shipment.getTimeToLoad(), test_shipment2.getTimeToLoad());
			Assert.assertEquals(test_shipment.getTimeToUnload(), test_shipment2.getTimeToUnload());
			Assert.assertEquals(test_shipment.getToLocationID(), test_shipment2.getToLocationID());
			Assert.assertEquals(test_shipment.getTrailerType(), test_shipment2.getTrailerType());
			Assert.assertEquals(test_shipment.getUnloadType(), test_shipment2.getUnloadType());
			Assert.assertEquals(new Double( test_shipment.getWeight()),new Double(test_shipment2.getWeight()));
			for (Shipment delete : lList)
				delete.Delete();
		}
		else {
			Assert.assertEquals(false, true);
		}
		
		test_shipment.setHazmat("JUnit Shipment.testUpdateLoad(Dirty)");
		Assert.assertTrue(test_shipment.isDirty());
		Assert.assertFalse(test_shipment.isNew());
		test_shipment.Update();
		lList = Shipment.LoadAll(new String("where hazmatConstraints = 'JUnit Shipment.testUpdateLoad(Dirty)'"));
		if (!lList.isEmpty()) {
			Shipment test_shipment2 = lList.get(0);
			Assert.assertEquals(test_shipment.getId(),test_shipment2.getId());
			Assert.assertEquals(test_shipment.getEarliestArrivalTime(),test_shipment2.getEarliestArrivalTime());
			Assert.assertEquals(test_shipment.getEarliestDepartureTime(),test_shipment2.getEarliestDepartureTime());
			Assert.assertEquals(test_shipment.getFromLocationID(),test_shipment2.getFromLocationID());
			Assert.assertEquals(test_shipment.getHazmat(),test_shipment2.getHazmat());
			Assert.assertEquals(test_shipment.getLatestArrivalTime(),test_shipment2.getLatestArrivalTime());
			Assert.assertEquals(test_shipment.getLatestDepartureTime(),test_shipment2.getLatestDepartureTime());
			Assert.assertEquals(test_shipment.getLoadingType(),test_shipment2.getLoadingType());
			Assert.assertEquals(test_shipment.getLoadingRate(),test_shipment2.getLoadingRate());
			Assert.assertEquals(test_shipment.getMaxStops(),test_shipment2.getMaxStops());
			Assert.assertEquals(test_shipment.getPrefCarriers(),test_shipment2.getPrefCarriers());
			Assert.assertEquals(test_shipment.getPriority(),test_shipment2.getPriority());
			Assert.assertEquals(test_shipment.getShipperID(), test_shipment2.getShipperID());
			Assert.assertEquals(new Double(test_shipment.getSize()), new Double(test_shipment2.getSize()));
			Assert.assertEquals(test_shipment.getTimeToLoad(), test_shipment2.getTimeToLoad());
			Assert.assertEquals(test_shipment.getTimeToUnload(), test_shipment2.getTimeToUnload());
			Assert.assertEquals(test_shipment.getToLocationID(), test_shipment2.getToLocationID());
			Assert.assertEquals(test_shipment.getTrailerType(), test_shipment2.getTrailerType());
			Assert.assertEquals(test_shipment.getUnloadType(), test_shipment2.getUnloadType());
			Assert.assertEquals(new Double( test_shipment.getWeight()),new Double(test_shipment2.getWeight()));
			for (Shipment delete : lList)
				delete.Delete();
		}
		else {
			Assert.assertEquals(false, true);
		}

	}

	@Test
	public void testDelete() {
		Shipment test_shipment = new Shipment();
		test_shipment.setHazmat("JUnit ShipmentTest.testDelete()");
		test_shipment.Update();
		test_shipment.Delete();
		ArrayList<Shipment> lList = Shipment.LoadAll(new String("where hazmatConstraints = 'JUnit ShipmentTest.testDelete()'"));
		Assert.assertTrue(lList.isEmpty());
		for (Shipment delete : lList)
			delete.Delete();	
	}

	@Test
	public void testDeleted() {
		Shipment test_shipment = new Shipment();
		Assert.assertFalse(test_shipment.isDeleted());
		Assert.assertFalse(test_shipment.isDirty());

		test_shipment.setHazmat("JUnit ShipmentTest.testDeleted()");
		test_shipment.Update();
		test_shipment.Delete();
		Assert.assertTrue(test_shipment.isDeleted());
		Assert.assertTrue(test_shipment.isDirty());
	}

	@SuppressWarnings("static-access")
	@Test
	public void testLoadAllForSegment() {
		ArrayList<Shipment> shipment_list;

		Segment test_segment = new Segment();
		ShipmentHistory test_history = new ShipmentHistory();
		Shipment test_shipment = new Shipment();

		test_segment.Update();
		test_shipment.Update();

		test_history.setShipmentID(test_shipment.getId());
		test_history.setSegmentID(test_segment.getID());

		test_history.Update();

		shipment_list = test_shipment.LoadAllForSegment(test_segment.getID());
		if (!shipment_list.isEmpty()) {
			Shipment test_shipment2 = shipment_list.get(0);
			Assert.assertEquals(test_shipment.getId(),test_shipment2.getId());
			Assert.assertEquals(test_shipment.getEarliestArrivalTime(),test_shipment2.getEarliestArrivalTime());
			Assert.assertEquals(test_shipment.getEarliestDepartureTime(),test_shipment2.getEarliestDepartureTime());
			Assert.assertEquals(test_shipment.getFromLocationID(),test_shipment2.getFromLocationID());
			Assert.assertEquals(test_shipment.getHazmat(),test_shipment2.getHazmat());
			Assert.assertEquals(test_shipment.getLatestArrivalTime(),test_shipment2.getLatestArrivalTime());
			Assert.assertEquals(test_shipment.getLatestDepartureTime(),test_shipment2.getLatestDepartureTime());
			Assert.assertEquals(test_shipment.getLoadingType(),test_shipment2.getLoadingType());
			Assert.assertEquals(test_shipment.getLoadingRate(),test_shipment2.getLoadingRate());
			Assert.assertEquals(test_shipment.getMaxStops(),test_shipment2.getMaxStops());
			Assert.assertEquals(test_shipment.getPrefCarriers(),test_shipment2.getPrefCarriers());
			Assert.assertEquals(test_shipment.getPriority(),test_shipment2.getPriority());
			Assert.assertEquals(test_shipment.getShipperID(), test_shipment2.getShipperID());
			Assert.assertEquals(new Double(test_shipment.getSize()), new Double(test_shipment2.getSize()));
			Assert.assertEquals(test_shipment.getTimeToLoad(), test_shipment2.getTimeToLoad());
			Assert.assertEquals(test_shipment.getTimeToUnload(), test_shipment2.getTimeToUnload());
			Assert.assertEquals(test_shipment.getToLocationID(), test_shipment2.getToLocationID());
			Assert.assertEquals(test_shipment.getTrailerType(), test_shipment2.getTrailerType());
			Assert.assertEquals(test_shipment.getUnloadType(), test_shipment2.getUnloadType());
			Assert.assertEquals(new Double( test_shipment.getWeight()),new Double(test_shipment2.getWeight()));
		}
		else {
			Assert.assertFalse("Returned emptey shipmentList", true);
		}
		test_segment.Delete();
		test_shipment.Delete();
		test_history.Delete();


	}
	
	@Test
	public void testDeleteAllHistory() {
		ShipmentHistory test_history1 = new ShipmentHistory();
		ShipmentHistory test_history2 = new ShipmentHistory();
		
		ArrayList<ShipmentHistory> history_list = new ArrayList<ShipmentHistory>();
		history_list.add(test_history1);
		history_list.add(test_history2);
		
		Shipment test_shipment = new Shipment();
		test_shipment.setHazmat("JUnit Shipment.testDeleteAllHistory()");
		test_shipment.setHistory(history_list);
		
		test_shipment.Update();
		test_shipment.DeleteAllHistory();
		
		Assert.assertEquals(0,test_shipment.getHistory().size());
		
		ArrayList<ShipmentHistory> history_list2 = ShipmentHistory.LoadAllForShipment(test_shipment.getId());
		
		Assert.assertEquals(0,history_list2.size());
		
		test_shipment.Delete();
		
	}

}
