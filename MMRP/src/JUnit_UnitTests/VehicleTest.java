package JUnit_UnitTests;

import core.Carrier;
import core.Vehicle;
import core.Cargo;

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
	
	
	
}
