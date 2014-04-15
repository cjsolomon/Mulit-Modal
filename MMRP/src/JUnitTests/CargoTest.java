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
		Assert.assertEquals(test_cargo.getId(),test_cargo2.getId());
		//Assert.assertEquals(test_cargo.getCarrier().toString().trim(),test_cargo2.getCarrier().toString().trim());
		Assert.assertEquals(test_cargo.getStatus().toString().trim(), test_cargo2.getStatus().toString().trim());
		Assert.assertEquals(test_cargo.isDirty(), test_cargo2.isDirty());
		Assert.assertEquals(test_cargo.isNew(),test_cargo2.isNew());
		Assert.assertEquals(test_cargo.getTravelMode().toString().trim(),test_cargo2.getTravelMode().toString().trim());
		test_cargo.Delete();
	}
	
	@Test
	public void testDelete() {
		Cargo test_cargo = new Cargo();
		test_cargo.setVehicleName("deleteTest");
		test_cargo.Update();
		test_cargo.Delete();
		Cargo test_cargo2 = Cargo.LoadAll(new String("where ShipName = 'DeleteTest'")).get(0);
		
	}
	
	
}
