package JUnitTests;

import org.junit.Test;
import org.junit.Assert;
import core.Cargo;
import core.Vehicle;
import core.BaseClass;

public class CargoTest {

	@Test
	public void testTravelMode() {
		Cargo test_cargo = new Cargo();
		Assert.assertEquals(Vehicle.TravelModes.CARGO.toString(), test_cargo.getTravelMode());
		
	}
	
	@Test
	public void testIsNew() {
		Cargo test_cargo = new Cargo();
		Assert.assertEquals(true,test_cargo.isNew());
		test_cargo.Update();
		Assert.assertEquals(false,test_cargo.isNew());
		test_cargo.Delete();
	}
	
	@Test
	public void testIsDirty() {
		Cargo test_cargo = new Cargo();
		Assert.assertEquals(false, test_cargo.isDirty());
		test_cargo.setStatus(Vehicle.Status.Delayed);
		Assert.assertEquals(true, test_cargo.isDirty());
		test_cargo.Update();
		Assert.assertEquals(false, test_cargo.isDirty());
		test_cargo.Delete();		
	}
	
	@Test
	public void testSetStatus() {
		Cargo test_cargo = new Cargo();
		//Default should set status to running
		Assert.assertEquals(Vehicle.Status.Running.toString().trim(),test_cargo.getStatus().toString().trim());
		//Set status to something else and then check that it worked
		test_cargo.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString().trim(),test_cargo.getStatus().toString().trim());
	}
	
	@Test
	public void testUpdateLoad() {
		Cargo test_cargo = new Cargo();
		test_cargo.setVehicleName("InsertLoadTest");
		test_cargo.Update();
		Cargo test_cargo2 = Cargo.LoadAll(new String("where ShipName = 'InsertLoadTest'")).get(0);
		Assert.assertEquals(test_cargo.getVehicleName().toString().trim(), test_cargo2.getVehicleName().toString().trim());
	}
	
	
}
