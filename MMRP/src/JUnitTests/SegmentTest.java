package JUnitTests;

import java.util.ArrayList;

import core.Bike;
import core.Cargo;
import core.Location;
import core.Plane;
import core.Rail;
import core.Segment;
import core.TravelType;
import core.Truck;
import core.Vehicle;
import core.ShippingRate;

import org.junit.*;

public class SegmentTest {

	@Test
	public void testDefaultConstructor() {
		Segment test_segment = new Segment();
		Assert.assertEquals(new Integer(Segment.getDefaultEndLocationId()), new Integer(test_segment.getEndLocationID()));
		Assert.assertEquals(new Integer(Segment.getDefaultStartLocationId()), new Integer(test_segment.getStartLocationID()));
		Assert.assertEquals(Vehicle.TravelModes.TRUCK.toString(), test_segment.getVehicle().getTravelMode());
		Assert.assertEquals(new Double(Segment.getDefaultDistance()), new Double(test_segment.getDistance()));
		Assert.assertEquals(new Integer(Segment.getDefaultDepartureTime()), new Integer(test_segment.getEstimatedDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultArrivalTime()), new Integer(test_segment.getEstimatedArrivalTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultEarliestDepartureTime()), new Integer(test_segment.getEarliestDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultEarliestArrivalTime()), new Integer(test_segment.getEarliestArrivalTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultLatestDepartureTime()), new Integer(test_segment.getLatestDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultLatestArrivalTime()), new Integer(test_segment.getLatestArrivalTime()));
		Assert.assertEquals(new ShippingRate().getType().toString(), test_segment.getShippingRate().getType().toString());
		Assert.assertEquals(Segment.getDefaultMode(), test_segment.getMode());
		Assert.assertEquals(new TravelType().getTravelTypeName(),test_segment.getTravelType().getTravelTypeName());
		Assert.assertEquals(Segment.getDefaultLanes(), test_segment.getLane());
		Assert.assertTrue(test_segment.getOnBoard().isEmpty());
	}

	@Test
	public void testArgumentedConstructor() {
		Segment test_segment = new Segment();
		int values[] = {36,17,22,15,3};

		for (int check : values) {
			test_segment = new Segment(check);
			Assert.assertEquals(new Integer(check), new Integer(test_segment.getID()));
		}
	}

	@Test
	public void testSetDistance() {
		Segment test_segment = new Segment();
		Double good_values[] = {3.33,6.66,9.99,8.12,12.123, .0000000001, 0.0};
		Double bad_values[] = {-6.21,-2.31,-5.86,-99.99, -.00000001};

		for (double check : good_values) {
			test_segment.setDistance(check);
			Assert.assertEquals(new Double(check), new Double(test_segment.getDistance()));
		}

		for (double check : bad_values) {
			test_segment.setDistance(check);
			Assert.assertEquals(new Double(0), new Double(test_segment.getDistance()));
		}

		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testSetShippingRate() {
		Segment test_segment = new Segment();
		ShippingRate test_rate = new ShippingRate();
		Integer test_int = new Integer(123456789);

		test_rate.setRank(test_int);
		test_segment.setShippingRate(test_rate);
		Assert.assertEquals(test_int, new Integer(test_segment.getShippingRate().getRank()));

		Assert.assertTrue(test_segment.isDirty());

	}

	@Test public void testSetEstimatedArrivalTime() {
		Segment test_segment = new Segment();
		Integer early_time = new Integer(2);
		Integer late_time = new Integer(10);
		Integer good_time[] = {2,3,4,5,6,7,8,9,10};
		Integer bad_time_early[] = {-1,0,1};
		Integer bad_time_late[] = {11,12,13};

		test_segment.setEarliestArrivalTime(early_time);
		test_segment.setLatestArrivalTime(late_time);

		for (Integer check : good_time) {
			test_segment.setEstimatedArrivalTime(check);
			Assert.assertEquals(check, new Integer(test_segment.getEstimatedArrivalTime()));
		}

		for (Integer check : bad_time_early) {
			test_segment.setEstimatedArrivalTime(check);
			Assert.assertEquals(early_time, new Integer(test_segment.getEstimatedArrivalTime()));
		}

		for (Integer check : bad_time_late) {
			test_segment.setEstimatedArrivalTime(check);
			Assert.assertEquals(late_time, new Integer(test_segment.getEstimatedArrivalTime()));
		}

		Assert.assertTrue(test_segment.isDirty());

	}

	@Test 
	public void testSetEstimatedDepartureTime() {
		Segment test_segment = new Segment();
		Integer early_time = new Integer(2);
		Integer late_time = new Integer(10);
		Integer good_time[] = {2,3,4,5,6,7,8,9,10};
		Integer bad_time_early[] = {-1,0,1};
		Integer bad_time_late[] = {11,12,13};

		test_segment.setLatestDepartureTime(late_time);
		test_segment.setEarliestDepartureTime(early_time);

		for (Integer check : good_time) {
			test_segment.setEstimatedDepartureTime(check);
			Assert.assertEquals(check, new Integer(test_segment.getEstimatedDepartureTime()));
		}

		for (Integer check : bad_time_early) {
			test_segment.setEstimatedDepartureTime(check);
			Assert.assertEquals(early_time, new Integer(test_segment.getEstimatedDepartureTime()));
		}

		for (Integer check : bad_time_late) {
			test_segment.setEstimatedDepartureTime(check);
			Assert.assertEquals(late_time, new Integer(test_segment.getEstimatedDepartureTime()));
		}

		Assert.assertTrue(test_segment.isDirty());

	}

	@Test
	public void testSetEarliestArrivalTime() {
		Segment test_segment = new Segment();
		Integer really_big_number = new Integer(10000);
		Integer big_number = new Integer(500);
		Integer good_values[] = {501,853,22,145,9850,10000};
		Integer bad_values[] = {10001,50000};

		test_segment.setLatestArrivalTime(really_big_number);
		test_segment.setEarliestArrivalTime(big_number);

		for (Integer check : good_values) {
			test_segment.setEarliestArrivalTime(check);
			Assert.assertEquals(check,new Integer(test_segment.getEarliestArrivalTime()));
		}

		for (Integer check : bad_values) {
			test_segment.setEarliestArrivalTime(check);
			Assert.assertEquals(really_big_number, new Integer(test_segment.getEarliestArrivalTime()));
		}

		Assert.assertTrue(test_segment.isDirty());

	}

	@Test
	public void testSetEarliestDepartureTime() {
		Segment test_segment = new Segment();
		Integer really_big_number = new Integer(10000);
		Integer big_number = new Integer(500);
		Integer good_values[] = {501,853,22,145,9850,10000};
		Integer bad_values[] = {10001,50000};

		test_segment.setLatestDepartureTime(really_big_number);
		test_segment.setEarliestDepartureTime(big_number);

		for (Integer check : good_values) {
			test_segment.setEarliestDepartureTime(check);
			Assert.assertEquals(check,new Integer(test_segment.getEarliestDepartureTime()));
		}

		for (Integer check : bad_values) {
			test_segment.setEarliestDepartureTime(check);
			Assert.assertEquals(really_big_number, new Integer(test_segment.getEarliestDepartureTime()));
		}

		Assert.assertTrue(test_segment.isDirty());

	}

	@Test
	public void testSetLatestArrivalTime() {
		Segment test_segment = new Segment();
		Integer big_number = new Integer(5000);
		Integer bigger_number = new Integer(10000);
		Integer good_values[] = {5000,6000,100000,5623};
		Integer bad_values[] = {4999,0,123,12};

		test_segment.setLatestArrivalTime(bigger_number);
		test_segment.setEarliestArrivalTime(big_number);

		for (Integer check : good_values) {
			test_segment.setLatestArrivalTime(check);
			Assert.assertEquals(check, new Integer(test_segment.getLatestArrivalTime()));
		}

		for (Integer check : bad_values) {
			test_segment.setLatestArrivalTime(check);
			Assert.assertEquals(big_number, new Integer(test_segment.getLatestArrivalTime()));
		}

		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testSetLatestDepartureTime() {
		Segment test_segment = new Segment();
		Integer big_number = new Integer(5000);
		Integer bigger_number = new Integer(10000);
		Integer good_values[] = {5000,6000,100000,5623};
		Integer bad_values[] = {4999,0,123,12};

		test_segment.setLatestDepartureTime(bigger_number);
		test_segment.setEarliestDepartureTime(big_number);

		for (Integer check : good_values) {
			test_segment.setLatestDepartureTime(check);
			Assert.assertEquals(check, new Integer(test_segment.getLatestDepartureTime()));
		}

		for (Integer check : bad_values) {
			test_segment.setLatestDepartureTime(check);
			Assert.assertEquals(big_number, new Integer(test_segment.getLatestDepartureTime()));
		}

		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testSetStartLocation() {
		Segment test_segment = new Segment();
		Location test_locations[] = {new Location(555), new Location(666), new Location(777)};
		Integer test_integers[] = {1,2,3,4,5};

		for (Location test : test_locations) {
			test_segment.setStartLocation(test);
			Assert.assertEquals(new Integer(test.getID()), new Integer(test_segment.getStartLocation().getID()));
			Assert.assertEquals(new Integer(test_segment.getStartLocation().getID()), new Integer(test_segment.getStartLocationID()));
		}

		for (Integer test : test_integers) {
			test_segment = new Segment();
			test_segment.setStartLocation(test);
			Assert.assertEquals(test, new Integer(test_segment.getStartLocation().getID()));
			Assert.assertEquals(new Integer(test_segment.getStartLocation().getID()), new Integer(test_segment.getStartLocationID()));
		}

		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testSetEndLocation() {
		Segment test_segment = new Segment();
		Location test_locations[] = {new Location(555), new Location(666), new Location(777)};
		Integer test_integers[] = {1,2,3,4,5};

		for (Location test : test_locations) {
			test_segment.setEndLocation(test);
			Assert.assertEquals(new Integer(test.getID()), new Integer(test_segment.getEndLocation().getID()));
			Assert.assertEquals(new Integer(test_segment.getEndLocation().getID()), new Integer(test_segment.getEndLocationID()));
		}

		for (Integer test : test_integers) {
			test_segment = new Segment();
			test_segment.setEndLocation(test);
			Assert.assertEquals(test, new Integer(test_segment.getEndLocation().getID()));
			Assert.assertEquals(new Integer(test_segment.getEndLocation().getID()), new Integer(test_segment.getEndLocationID()));
		}

		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testSetVehicle() {
		Segment test_segment = new Segment();
		Vehicle test_bike = new Bike();
		Vehicle test_cargo = new Cargo();
		Vehicle test_plane = new Plane();
		Vehicle test_truck = new Truck();
		Vehicle test_train = new Rail();

		test_segment.setVehicle(test_bike);
		Assert.assertEquals(test_bike, test_segment.getVehicle());
		test_segment.setVehicle(test_cargo);
		Assert.assertEquals(test_cargo, test_segment.getVehicle());
		test_segment.setVehicle(test_plane);
		Assert.assertEquals(test_plane, test_segment.getVehicle());
		test_segment.setVehicle(test_truck);
		Assert.assertEquals(test_truck, test_segment.getVehicle());
		test_segment.setVehicle(test_train);
		Assert.assertEquals(test_train, test_segment.getVehicle());

		for (int i = 1; i < 5; i++) {
			test_segment.setVehicle(i, Vehicle.TravelModes.BIKE);
			Assert.assertEquals(new Integer(i),new Integer(test_segment.getVehicle().getId()));
			test_segment.setVehicle(i, Vehicle.TravelModes.PLANE);
			Assert.assertEquals(new Integer(i),new Integer(test_segment.getVehicle().getId()));
			test_segment.setVehicle(i, Vehicle.TravelModes.CARGO);
			Assert.assertEquals(new Integer(i),new Integer(test_segment.getVehicle().getId()));
			test_segment.setVehicle(i, Vehicle.TravelModes.RAIL);
			Assert.assertEquals(new Integer(i),new Integer(test_segment.getVehicle().getId()));
			test_segment.setVehicle(i, Vehicle.TravelModes.TRUCK);
			Assert.assertEquals(new Integer(i),new Integer(test_segment.getVehicle().getId()));
		}

		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testSetLane() {
		Segment test_segment = new Segment();
		String test_string = new String("This is the lane where the testing happens.");

		test_segment.setLane(test_string);
		Assert.assertEquals(test_string, test_segment.getLane());
		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testSetTravelType() {
		Segment test_segment = new Segment();
		TravelType test_traveltype = new TravelType();

		test_segment.setTravelType(test_traveltype);
		Assert.assertEquals(test_traveltype,test_segment.getTravelType());
		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testSetMode() {
		Segment test_segment = new Segment();
		String test_string_good[] = {"RAIL","PLANE","CARGO","BIKE","TRUCK"};
		String test_string_bad[] = {"this","is","a","bad","string"};

		for (String check : test_string_good) {
			test_segment.setMode(check);
			Assert.assertEquals(check,test_segment.getMode());
		}

		for (String check : test_string_bad) {
			test_segment.setMode(check);
			Assert.assertNotEquals(check,test_segment.getMode());
		}

		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testActualCapacity() {
		Segment test_segment = new Segment();
		Double test_capacity = new Double(37.3256);
		Double test_capacity2 = new Double(-16.2345);

		test_segment.setActualCapacity(test_capacity);
		Assert.assertEquals(test_capacity, new Double(test_segment.getActualCapacity()));
		test_segment.setActualCapacity(test_capacity2);
		Assert.assertEquals(new Double(0), new Double(test_segment.getActualCapacity()));
		Assert.assertTrue(test_segment.isDirty());
	}

	@Test
	public void testUpdateLoad() {
		Segment test_segment = new Segment();
		test_segment.setTravelType(new TravelType());

		String test_string = new String("JUnit test");
		String test_string2 = new String ("JUnit test 2");
		test_segment.setLane(test_string);
		Assert.assertTrue(test_segment.isNew());
		test_segment.Update();
		Assert.assertFalse(test_segment.isDirty());
		ArrayList<Segment> sList = Segment.LoadAll(new String("where Lane = '" + test_string + "'")); 
		if (!sList.isEmpty()) {
			Assert.assertEquals(new Integer(test_segment.getEndLocationID()), new Integer(sList.get(0).getEndLocationID()));
			Assert.assertEquals(new Integer(test_segment.getStartLocationID()), new Integer(sList.get(0).getStartLocationID()));
			Assert.assertEquals(new Double(test_segment.getDistance()), new Double(sList.get(0).getDistance()));
			Assert.assertEquals(new Integer(test_segment.getEstimatedDepartureTime()), new Integer(sList.get(0).getEstimatedDepartureTime()));
			Assert.assertEquals(new Integer(test_segment.getEstimatedArrivalTime()), new Integer(sList.get(0).getEstimatedArrivalTime()));
			Assert.assertEquals(new Integer(test_segment.getEarliestDepartureTime()), new Integer(sList.get(0).getEarliestDepartureTime()));
			Assert.assertEquals(new Integer(test_segment.getEarliestArrivalTime()), new Integer(sList.get(0).getEarliestArrivalTime()));
			Assert.assertEquals(new Integer(test_segment.getLatestDepartureTime()), new Integer(sList.get(0).getLatestDepartureTime()));
			Assert.assertEquals(new Integer(test_segment.getLatestArrivalTime()), new Integer(sList.get(0).getLatestArrivalTime()));
			Assert.assertEquals(test_segment.getShippingRate().getType().toString(), sList.get(0).getShippingRate().getType().toString());
			Assert.assertEquals(test_segment.getMode(), sList.get(0).getMode());
			Assert.assertEquals(test_segment.getTravelType().getTravelTypeName(),sList.get(0).getTravelType().getTravelTypeName());
			Assert.assertEquals(new Integer(test_segment.getOnBoard().size()),new Integer(sList.get(0).getOnBoard().size()));
			for (Segment delete : sList)
				delete.Delete();
		}
		else {
			Assert.assertEquals(false, true);
		}

		
		test_segment.setLane(test_string2);
		Assert.assertTrue(test_segment.isDirty());
		Assert.assertFalse(test_segment.isNew());
		test_segment.Update();
		ArrayList<Segment> sList2 = Segment.LoadAll(new String("where Lane = '" + test_string2 + "'"));
		if (!sList2.isEmpty()) {
			Assert.assertEquals(new Integer(test_segment.getEndLocationID()), new Integer(sList2.get(0).getEndLocationID()));
			Assert.assertEquals(new Integer(test_segment.getStartLocationID()), new Integer(sList2.get(0).getStartLocationID()));
			Assert.assertEquals(new Double(test_segment.getDistance()), new Double(sList2.get(0).getDistance()));
			Assert.assertEquals(new Integer(test_segment.getEstimatedDepartureTime()), new Integer(sList2.get(0).getEstimatedDepartureTime()));
			Assert.assertEquals(new Integer(test_segment.getEstimatedArrivalTime()), new Integer(sList2.get(0).getEstimatedArrivalTime()));
			Assert.assertEquals(new Integer(test_segment.getEarliestDepartureTime()), new Integer(sList2.get(0).getEarliestDepartureTime()));
			Assert.assertEquals(new Integer(test_segment.getEarliestArrivalTime()), new Integer(sList2.get(0).getEarliestArrivalTime()));
			Assert.assertEquals(new Integer(test_segment.getLatestDepartureTime()), new Integer(sList2.get(0).getLatestDepartureTime()));
			Assert.assertEquals(new Integer(test_segment.getLatestArrivalTime()), new Integer(sList2.get(0).getLatestArrivalTime()));
			Assert.assertEquals(test_segment.getShippingRate().getType().toString(), sList2.get(0).getShippingRate().getType().toString());
			Assert.assertEquals(test_segment.getMode(), sList2.get(0).getMode());
			Assert.assertEquals(test_segment.getTravelType().getTravelTypeName(),sList2.get(0).getTravelType().getTravelTypeName());
			Assert.assertEquals(new Integer(test_segment.getOnBoard().size()),new Integer(sList2.get(0).getOnBoard().size()));
			for (Segment delete : sList)
				delete.Delete();
		}
		test_segment.Delete();
	}

	@Test
	public void testToString() {
		Segment test_segment = new Segment();
		String output = test_segment.toString();
		String outputs[] = output.split(" ");

		Assert.assertEquals(new Integer(test_segment.getStartLocationID()).toString(), outputs[0]);
		Assert.assertEquals(new Integer(test_segment.getEndLocationID()).toString(),outputs[outputs.length-1]);
	}

	@Test
	public void testDeleted() {
		Segment test_segment = new Segment();
		Assert.assertFalse(test_segment.isDeleted());
		Assert.assertFalse(test_segment.isDirty());
		String test_string = new String("JUnit Delete test");

		test_segment.setLane(test_string);

		test_segment.Update();
		test_segment.Delete();

		Assert.assertTrue(test_segment.isDeleted());

		ArrayList<Segment> sList = Segment.LoadAll(new String("where Lane = '" + test_string + "'"));
		Assert.assertTrue(sList.isEmpty());
		for (Segment delete : sList)
			delete.Delete();	
	}
	
	@Test
	public void testLoadAllAtLocation1() {
		Segment test_segment = new Segment();
		Location test_location_start = new Location();
		Location test_location_end = new Location();
		Location test_location_never = new Location();
		
		test_location_start.setName("Segment Load All Test 1 - Start");
		test_location_end.setName("Segment Load All Test 1 - End");
		test_location_never.setName("Segment Load All Test 1 - Never");
		test_segment.setLane("JUnit test Load All Test 1");
		
		test_location_start.Update();
		test_location_end.Update();
		test_location_never.Update();
		
		test_segment.setStartLocation(test_location_start);
		test_segment.setEndLocation(test_location_end);
		test_segment.Update();
		
		ArrayList<Segment> sList1 = Segment.LoadAllAtLocation(test_location_start);
		ArrayList<Segment> sList2 = Segment.LoadAllAtLocation(test_location_never);
		
		Assert.assertFalse(sList1.isEmpty());
		Assert.assertTrue(sList2.isEmpty());
		
		test_location_end.Delete();
		test_location_start.Delete();
		test_location_never.Delete();
		test_segment.Delete();
				
	}
	
	@Test
	public void testLoadAllAtLocation2() {
		Segment test_segment = new Segment();
		Segment test_segment2 = new Segment();
		Location test_location_start = new Location();
		Location test_location_end = new Location();
		Integer parameter_time = new Integer(700);
		Integer start_time = new Integer(750);
		
		test_location_start.setName("Segment Load All Test 1 - Start");
		test_location_end.setName("Segment Load All Test 1 - End");
		test_segment.setLane("JUnit test Load All Test 2");
		test_segment2.setLane("JUnit test Load All Test 2");
		
		test_location_start.Update();
		test_location_end.Update();
		
		test_segment.setStartLocation(test_location_start);
		test_segment.setEndLocation(test_location_end);
		test_segment.setEstimatedDepartureTime(start_time);
		
		test_segment2.setStartLocation(test_location_start);
		test_segment2.setEndLocation(test_location_end);
		test_segment2.setEstimatedDepartureTime(parameter_time);
		
		test_segment.Update();
		test_segment2.Update();
		
		ArrayList<Segment> sList1 = Segment.LoadAllAtLocation(test_location_start,parameter_time);
		ArrayList<Segment> sList2 = Segment.LoadAllAtLocation(test_location_start,start_time);
		
		Assert.assertFalse(sList1.isEmpty());
		Assert.assertTrue(sList2.isEmpty());
		
		test_location_end.Delete();
		test_location_start.Delete();
		test_segment.Delete();
		test_segment2.Delete();
				
	}
	
	@Test
	public void testLoadAllAtLocation3() {
		Segment test_segment = new Segment();
		Location test_location_start = new Location();
		Location test_location_end = new Location();
		Location test_location_never = new Location();
		
		test_location_start.setName("Segment Load All Test 3 - Start");
		test_location_end.setName("Segment Load All Test 3 - End");
		test_location_never.setName("Segment Load All Test 3 - Never");
		test_segment.setLane("JUnit test Load All Test 3");
		
		test_location_start.Update();
		test_location_end.Update();
		test_location_never.Update();
		
		test_segment.setStartLocation(test_location_start);
		test_segment.setEndLocation(test_location_end);
		test_segment.Update();
		
		ArrayList<Segment> sList1 = Segment.LoadAllAtLocation(test_location_start.getID());
		ArrayList<Segment> sList2 = Segment.LoadAllAtLocation(test_location_never.getID());
		
		Assert.assertFalse(sList1.isEmpty());
		Assert.assertTrue(sList2.isEmpty());
		
		test_location_end.Delete();
		test_location_start.Delete();
		test_location_never.Delete();
		test_segment.Delete();
				
	}
	
	@Test
	public void testLoadAllAtLocation4() {
		Segment test_segment = new Segment();
		Segment test_segment2 = new Segment();
		Location test_location_start = new Location();
		Location test_location_end = new Location();
		Integer parameter_time = new Integer(700);
		Integer start_time = new Integer(750);
		
		test_location_start.setName("Segment Load All Test 1 - Start");
		test_location_end.setName("Segment Load All Test 1 - End");
		test_segment.setLane("JUnit test Load All Test 4");
		test_segment2.setLane("JUnit test Load All Test 4");
		
		test_location_start.Update();
		test_location_end.Update();
		
		test_segment.setStartLocation(test_location_start);
		test_segment.setEndLocation(test_location_end);
		test_segment.setEstimatedDepartureTime(start_time);
		
		test_segment2.setStartLocation(test_location_start);
		test_segment2.setEndLocation(test_location_end);
		test_segment2.setEstimatedDepartureTime(parameter_time);
		
		test_segment.Update();
		test_segment2.Update();
		
		ArrayList<Segment> sList1 = Segment.LoadAllAtLocation(test_location_start.getID(),parameter_time);
		ArrayList<Segment> sList2 = Segment.LoadAllAtLocation(test_location_start.getID(),start_time);
		
		Assert.assertFalse(sList1.isEmpty());
		Assert.assertTrue(sList2.isEmpty());
		
		test_location_end.Delete();
		test_location_start.Delete();
		test_segment.Delete();
		test_segment2.Delete();
				
	}
	
	@Test
	public void testLoad() {
		Segment test_segment = new Segment();
		String test_string = new String("Load Test String For Segment");
		test_segment.setLane(test_string);
		

		test_segment.Update();
		Segment test_segment2 = Segment.Load(test_segment.getID());
		/*Assert.assertEquals(new Double(test_segment.getDistance()), new Double(test_segment2.getDistance()));
		Assert.assertEquals(new Integer(test_segment.getEstimatedDepartureTime()), new Integer(test_segment2.getEstimatedDepartureTime()));
		Assert.assertEquals(new Integer(test_segment.getEstimatedArrivalTime()), new Integer(test_segment2.getEstimatedArrivalTime()));
		Assert.assertEquals(new Integer(test_segment.getEarliestDepartureTime()), new Integer(test_segment2.getEarliestDepartureTime()));
		Assert.assertEquals(new Integer(test_segment.getEarliestArrivalTime()), new Integer(test_segment2.getEarliestArrivalTime()));
		Assert.assertEquals(new Integer(test_segment.getLatestDepartureTime()), new Integer(test_segment2.getLatestDepartureTime()));
		Assert.assertEquals(new Integer(test_segment.getLatestArrivalTime()), new Integer(test_segment2.getLatestArrivalTime()));
		Assert.assertEquals(test_segment.getShippingRate().getType().toString(), test_segment2.getShippingRate().getType().toString());
		Assert.assertEquals(test_segment.getMode(), test_segment2.getMode());
		Assert.assertEquals(test_segment.getTravelType().getTravelTypeName(),test_segment2.getTravelType().getTravelTypeName());
		Assert.assertEquals(new Integer(test_segment.getOnBoard().size()),new Integer(test_segment2.getOnBoard().size()));*/
		test_segment.Delete();
	}

}
