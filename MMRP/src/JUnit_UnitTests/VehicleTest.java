package JUnit_UnitTests;

import java.util.ArrayList;

import core.Carrier;
import core.Segment;
import core.TravelType;
import core.Truck;
import core.Vehicle;
import core.Cargo;
import core.Location;

import org.junit.*;



public class VehicleTest {
	Cargo test_cargo;
	
	@Before
	public void setup() {
		test_cargo = new Cargo();
	}

	@Test 
	public void testLoadMode() {
		Assert.assertEquals(Vehicle.TravelModes.TRUCK.toString(), Vehicle.loadMode("TRUCK").toString());
		Assert.assertEquals(Vehicle.TravelModes.NONE.toString(), Vehicle.loadMode("NONE").toString());
		Assert.assertEquals(Vehicle.TravelModes.ALL.toString(), Vehicle.loadMode("ALL").toString());
		Assert.assertEquals(Vehicle.TravelModes.CARGO.toString(), Vehicle.loadMode("CARGO").toString());
		Assert.assertEquals(Vehicle.TravelModes.RAIL.toString(), Vehicle.loadMode("RAIL").toString());
		Assert.assertEquals(Vehicle.TravelModes.PLANE.toString(), Vehicle.loadMode("PLANE").toString());
		Assert.assertEquals(Vehicle.TravelModes.BIKE.toString(), Vehicle.loadMode("BIKE").toString());
	}
	
	@Test
	public void testSetId() {
		int test_int[] = {23,17,101,0,45};
		
		for (int test : test_int) {
			test_cargo.setId(test);
			Assert.assertEquals(test, test_cargo.getId());
		}
	}
	
	@Test
	public void testSetStatus1() {
		test_cargo.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString(),test_cargo.getStatus().toString());
		Assert.assertTrue(test_cargo.isDirty());
	}
	
	@Test
	public void testSetStatus2() {
		test_cargo.setStatus("DISABLED");
		Assert.assertEquals(Vehicle.Status.Disabled.toString(),test_cargo.getStatus().toString());
		Assert.assertTrue(test_cargo.isDirty());
	}
	
	@Test
	public void testLoadStatus() {
		Assert.assertEquals(Vehicle.Status.Delayed.toString(), Vehicle.loadStatus("DELAYED").toString());
		Assert.assertEquals(Vehicle.Status.Disabled.toString(), Vehicle.loadStatus("DISABLED").toString());
		Assert.assertEquals(Vehicle.Status.Running.toString(), Vehicle.loadStatus("RUNNING").toString());
	}
	
	@Test
	public void testSetCarrier() {
		Carrier test_carrier = new Carrier();
		String test_string = new String("JUnit Vehicle.testSetCarrier");
		
		test_carrier.setCarrierName(test_string);
		test_cargo.setCarrier(test_carrier);
		
		Assert.assertEquals(test_string, test_cargo.getCarrier().getCarrierName());
		Assert
		
		.assertTrue(test_cargo.isDirty());
	}
	
	@Test
	public void testSetVehicleName() {
		String test_string = new String("JUnit Vehicle.testSetVehicleName");
		test_cargo.setVehicleName(test_string);
		
		Assert.assertEquals(test_string, test_cargo.getVehicleName());
		Assert.assertTrue(test_cargo.isDirty());
	}
	
	@Test
	public void testAddToSchedule() {
		
		Segment test_segment1 = new Segment();
		Segment test_segment2 = new Segment();
		
		Location start_location = new Location();
		start_location.setName("JUnit add to Schedule Test - Start Location");
		Location middle_location = new Location();
		middle_location.setName("JUnit add to Schedule Test - Middle Location");
		Location end_location = new Location();
		end_location.setName("JUnit add to Schedule Test - End Location");
		
		start_location.Update();
		middle_location.Update();
		end_location.Update();
		
		test_segment1.setStartLocation(start_location);
		test_segment1.setEndLocation(middle_location);
		test_segment1.setMode("CARGO");
		
		test_segment2.setStartLocation(middle_location);
		test_segment2.setEndLocation(end_location);
		test_segment2.setMode("CARGO");
		
		test_segment1.Update();
		test_segment2.Update();
		
		test_cargo.Update();
		
		test_cargo.addToSchedule(test_segment1);
		ArrayList<Segment> seg_list = new ArrayList<Segment>();
		seg_list = test_cargo.getSchedule();
		
		if (!seg_list.isEmpty()) {
			Assert.assertEquals(test_segment1.getStartLocation().getName(), seg_list.get(0).getStartLocation().getName());
			Assert.assertEquals(test_segment1.getEndLocation().getName(), seg_list.get(0).getEndLocation().getName());
		}
		else
			Assert.assertTrue("This list should not be empty", false);
		
		test_cargo.addToSchedule(test_segment2);
		seg_list = test_cargo.getSchedule();
		Assert.assertEquals(2, seg_list.size());
		
		test_cargo.Delete();
		test_segment1.Delete();
		test_segment2.Delete();
		start_location.Delete();
		middle_location.Delete();
		end_location.Delete();
		
	}
	
	@Test
	public void testAddToTypes() {
		//Create variables needed for test
		Truck test_truck = new Truck();
		TravelType test_type = new TravelType();
		
		//Put truck into DB
		test_truck.setVehicleName("JUnit Vehicle.testGetAvailableTypes");
		test_truck.Update();
		
		//Set some attributes of the TravelType
		test_type.setTravelTypeMode("TRUCK");	
		test_type.setServiceType("Junit Vehicle.testGetAvailableTypes");
		test_type.setTrailer1("HoneyWagon");
		test_type.setTrailer2("Ice Cream Module");
		test_type.Update();
		
		test_truck.addToTypes(test_type);
		
		Assert.assertEquals(test_truck.getAvailableTypes().get(0).getServiceType(), test_type.getServiceType());
		Assert.assertEquals(test_truck.getAvailableTypes().get(0).getTrailer1(),test_type.getTrailer1());
		Assert.assertEquals(test_truck.getAvailableTypes().get(0).getTrailer2(),test_type.getTrailer2());
		
		test_truck.removeFromTypes(test_type);
		//System.out.println("There are " + test_truck.getAvailableTypes().size() + " types available");
		Assert.assertTrue(test_truck.getAvailableTypes().isEmpty());
		
		test_type.Delete();
		test_truck.Delete();
	}

	
}
