package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import core.Bike;
import core.Cargo;
import core.Cargo;
import core.Carrier;
import core.Vehicle;

public class CargoTest {

	@Test
	public void testDefaultConstructor() {
		Cargo test_cargo = new Cargo();
		Assert.assertEquals(Vehicle.TravelModes.CARGO.toString(), test_cargo.getTravelMode());
		Assert.assertTrue(test_cargo.isNew());
		Assert.assertFalse(test_cargo.isDeleted());
		Assert.assertFalse(test_cargo.isDirty());
		Assert.assertEquals(Vehicle.Status.Running, test_cargo.getStatus());
		Assert.assertEquals(Cargo.getDefaultName(),test_cargo.getVehicleName());
		
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
	public void testSetStatus() {
		Cargo test_cargo = new Cargo();
		//Default should set status to running
		Assert.assertEquals(Vehicle.Status.Running.toString().trim(),test_cargo.getStatus().toString().trim());
		//Set status to something else and then check that it worked
		test_cargo.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString().trim(),test_cargo.getStatus().toString().trim());
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
	public void testUpdateLoad() {
		Cargo test_cargo = new Cargo();
		test_cargo.setVehicleName("InsertLoadTest");
		test_cargo.setCarrier(Carrier.Load(3));
		test_cargo.Update();
		ArrayList<Cargo> cList = Cargo.LoadAll(new String("where ShipName = 'InsertLoadTest'"));
		if (!cList.isEmpty()) {
			Assert.assertEquals(test_cargo.getVehicleName().toString().trim(), cList.get(0).getVehicleName().toString().trim());
			Assert.assertEquals(test_cargo.getId(),cList.get(0).getId());
			Assert.assertEquals(test_cargo.getCarrier().getCarrierName().trim(),cList.get(0).getCarrier().getCarrierName().trim());
			Assert.assertEquals(test_cargo.getStatus().toString().trim(), cList.get(0).getStatus().toString().trim());
			Assert.assertEquals(test_cargo.isDirty(), cList.get(0).isDirty());
			Assert.assertEquals(test_cargo.isNew(),cList.get(0).isNew());
			Assert.assertEquals(test_cargo.getTravelMode().toString().trim(),cList.get(0).getTravelMode().toString().trim());
			for (Cargo delete : cList)
				delete.Delete();
		}
		else {
			Assert.assertEquals(false, true);
		}
		
	}
	
	@Test
	public void testDelete() {
		Cargo test_cargo = new Cargo();
		test_cargo.setVehicleName("deleteTest");
		test_cargo.Update();
		test_cargo.Delete();
		ArrayList<Cargo> cList = Cargo.LoadAll(new String("where ShipName = 'deleteTest'"));
		Assert.assertTrue(cList.isEmpty());
		for (Cargo delete : cList)
			delete.Delete();	
	}
	
	@Test
	public void testArgumentedConstructor() {
		int testValues[] = {-1,18,100,3,7};
		
		for (int i = 0; i < testValues.length; i++) {
			Cargo test_cargo = new Cargo(testValues[i]);
			Assert.assertEquals(testValues[i],test_cargo.getId());
		}
	}
	
	@Test
	public void testDeleted() {
		Cargo test_cargo = new Cargo();
		Assert.assertFalse(test_cargo.isDeleted());
		Assert.assertFalse(test_cargo.isDirty());
		
		test_cargo.setVehicleName("This is the test that never ends");
		test_cargo.Update();
		test_cargo.Delete();
		Assert.assertTrue(test_cargo.isDeleted());
		Assert.assertTrue(test_cargo.isDirty());
	}
	
	
}
