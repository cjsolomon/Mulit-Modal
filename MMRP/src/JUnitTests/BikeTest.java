package JUnitTests;

import org.junit.Test;
import org.junit.Assert;
import core.Bike;
import core.Vehicle;


public class BikeTest {

	@Test
	public void testTravelMode() {
		Bike test_Bike = new Bike();
		Assert.assertEquals(Vehicle.TravelModes.BIKE.toString(), test_Bike.getTravelMode());
		
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
	public void testIsDirty() {
		Bike test_Bike = new Bike();
		Assert.assertEquals(false, test_Bike.isDirty());
		test_Bike.setStatus(Vehicle.Status.Delayed);
		Assert.assertEquals(true, test_Bike.isDirty());
		test_Bike.Update();
		Assert.assertEquals(false, test_Bike.isDirty());
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
	public void testUpdateLoad() {
		Bike test_Bike = new Bike();
		test_Bike.setVehicleName("InsertLoadTest");
		test_Bike.Update();
		Bike test_Bike2 = Bike.LoadAll(new String("where BikeName = 'InsertLoadTest'")).get(0);
		Assert.assertEquals(test_Bike.getVehicleName().toString().trim(), test_Bike2.getVehicleName().toString().trim());
		Assert.assertEquals(test_Bike.getId(),test_Bike2.getId());
		//Assert.assertEquals(test_Bike.getCarrier().toString().trim(),test_Bike2.getCarrier().toString().trim());
		Assert.assertEquals(test_Bike.getStatus().toString().trim(), test_Bike2.getStatus().toString().trim());
		Assert.assertEquals(test_Bike.isDirty(), test_Bike2.isDirty());
		Assert.assertEquals(test_Bike.isNew(),test_Bike2.isNew());
		Assert.assertEquals(test_Bike.getTravelMode().toString().trim(),test_Bike2.getTravelMode().toString().trim());
		test_Bike.Delete();
	}
	
	@Test
	public void testDelete() {
		Bike test_Bike = new Bike();
		test_Bike.setVehicleName("deleteTest");
		test_Bike.Update();
		test_Bike.Delete();
		Bike test_Bike2 = Bike.LoadAll(new String("where BikeName = 'DeleteTest'")).get(0);
		Assert.assertNotEquals(test_Bike.getId(),test_Bike2.getId());
		
	}
	
	@Test
	public void testArgumentedConstructor() {
		int testValues[] = {-1,18,100,3,7};
		
		for (int i = 0; i < testValues.length; i++) {
			Bike test_Bike = new Bike(testValues[i]);
			Assert.assertEquals(testValues[i],test_Bike.getId());
		}
	}
	
	
}
