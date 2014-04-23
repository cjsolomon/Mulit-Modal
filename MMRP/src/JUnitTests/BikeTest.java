package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import core.Bike;
import core.Carrier;
import core.Vehicle;

public class BikeTest {

	@Test
	public void testDefaultConstructor() {
		Bike test_Bike = new Bike();
		Assert.assertEquals(Vehicle.TravelModes.BIKE.toString(), test_Bike.getTravelMode());
		Assert.assertTrue(test_Bike.isNew());
		Assert.assertFalse(test_Bike.isDeleted());
		Assert.assertFalse(test_Bike.isDirty());
		Assert.assertEquals(Vehicle.Status.Running, test_Bike.getStatus());
		Assert.assertEquals(Bike.getDefaultName(),test_Bike.getVehicleName());
		
	}
	
	@Test
	public void testIsNew() {
		Bike test_Bike = new Bike();
		Assert.assertEquals(true,test_Bike.isNew());
		test_Bike.Update();
		Assert.assertEquals(false,test_Bike.isNew());
		test_Bike.Delete();
	}
	
	@Test
	public void testSetStatus() {
		Bike test_Bike = new Bike();
		//Default should set status to running
		Assert.assertEquals(Vehicle.Status.Running.toString().trim(),test_Bike.getStatus().toString().trim());
		//Set status to something else and then check that it worked
		test_Bike.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString().trim(),test_Bike.getStatus().toString().trim());
	}
	
	@Test
	public void testIsDirty() {
		Bike test_Bike = new Bike();
		Assert.assertEquals(new String("New Bikes aren't dirty"),false, test_Bike.isDirty());
		test_Bike.setStatus(Vehicle.Status.Delayed);
		Assert.assertEquals(true, test_Bike.isDirty());
		test_Bike.Update();
		Assert.assertEquals(new String("Updated bikes aren't dirty"),false, test_Bike.isDirty());
		test_Bike.Delete();		
	}
	
	@Test
	public void testUpdateLoad() {
		Bike test_Bike = new Bike();
		test_Bike.setVehicleName("InsertLoadTest");
		
		test_Bike.setCarrier(Carrier.Load(3));
		test_Bike.Update();
		ArrayList<Bike> bList = Bike.LoadAll(new String("where BikeName = 'InsertLoadTest'"));
		if (!bList.isEmpty()) {
			Assert.assertEquals(test_Bike.getVehicleName().toString().trim(), bList.get(0).getVehicleName().toString().trim());

			Assert.assertEquals(test_Bike.getId(),bList.get(0).getId());
			Assert.assertEquals(test_Bike.getCarrier().getCarrierName().trim(),bList.get(0).getCarrier().getCarrierName().trim());
			Assert.assertEquals(test_Bike.getStatus().toString().trim(), bList.get(0).getStatus().toString().trim());
			Assert.assertEquals(test_Bike.isDirty(), bList.get(0).isDirty());
			Assert.assertEquals(test_Bike.isNew(),bList.get(0).isNew());
			Assert.assertEquals(test_Bike.getTravelMode().toString().trim(),bList.get(0).getTravelMode().toString().trim());
			for (Bike delete : bList)
				delete.Delete();
		}
		else {
			Assert.assertEquals(false, true);
		}
		
	}
	
	@Test
	public void testDelete() {
		Bike test_Bike = new Bike();
		test_Bike.setVehicleName("deleteTest");
		test_Bike.Update();
		test_Bike.Delete();
		ArrayList<Bike> bList = Bike.LoadAll(new String("where BikeName = 'deleteTest'"));
		Assert.assertTrue(bList.isEmpty());
		for (Bike delete : bList)
			delete.Delete();	
	}
	
	@Test
	public void testArgumentedConstructor() {
		int testValues[] = {-1,18,100,3,7};
		
		for (int i = 0; i < testValues.length; i++) {
			Bike test_Bike = new Bike(testValues[i]);
			Assert.assertEquals(testValues[i],test_Bike.getId());
		}
	}
	
	@Test
	public void testDeleted() {
		Bike test_bike = new Bike();
		Assert.assertFalse(test_bike.isDeleted());
		Assert.assertFalse(test_bike.isDirty());
		
		test_bike.setVehicleName("This is the test that never ends");
		test_bike.Update();
		test_bike.Delete();
		Assert.assertTrue(test_bike.isDeleted());
		Assert.assertTrue(test_bike.isDirty());
	}
	
	
}
