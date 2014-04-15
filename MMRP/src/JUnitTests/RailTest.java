package JUnitTests;

import org.junit.Test;
import org.junit.Assert;
import core.Rail;
import core.Vehicle;


public class RailTest {

	@Test
	public void testTravelMode() {
		Rail test_Rail = new Rail();
		Assert.assertEquals(Vehicle.TravelModes.RAIL.toString(), test_Rail.getTravelMode());
		
	}
	
	@Test
	public void testIsNew() {
		Rail test_Rail = new Rail();
		Assert.assertEquals(true,test_Rail.isNew());
		test_Rail.Update();
		Assert.assertEquals(false,test_Rail.isNew());
		test_Rail.Delete();
	}
	
	@Test
	public void testIsDirty() {
		Rail test_Rail = new Rail();
		Assert.assertEquals(false, test_Rail.isDirty());
		test_Rail.setStatus(Vehicle.Status.Delayed);
		Assert.assertEquals(true, test_Rail.isDirty());
		test_Rail.Update();
		Assert.assertEquals(false, test_Rail.isDirty());
		test_Rail.Delete();		
	}
	
	@Test
	public void testSetStatus() {
		Rail test_Rail = new Rail();
		//Default should set status to running
		Assert.assertEquals(Vehicle.Status.Running.toString().trim(),test_Rail.getStatus().toString().trim());
		//Set status to something else and then check that it worked
		test_Rail.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString().trim(),test_Rail.getStatus().toString().trim());
	}
	
	@Test
	public void testUpdateLoad() {
		Rail test_Rail = new Rail();
		test_Rail.setVehicleName("InsertLoadTest");
		test_Rail.Update();
		Rail test_Rail2 = Rail.LoadAll(new String("where RailName = 'InsertLoadTest'")).get(0);
		Assert.assertEquals(test_Rail.getVehicleName().toString().trim(), test_Rail2.getVehicleName().toString().trim());
		Assert.assertEquals(test_Rail.getId(),test_Rail2.getId());
		//Assert.assertEquals(test_Rail.getCarrier().toString().trim(),test_Rail2.getCarrier().toString().trim());
		Assert.assertEquals(test_Rail.getStatus().toString().trim(), test_Rail2.getStatus().toString().trim());
		Assert.assertEquals(test_Rail.isDirty(), test_Rail2.isDirty());
		Assert.assertEquals(test_Rail.isNew(),test_Rail2.isNew());
		Assert.assertEquals(test_Rail.getTravelMode().toString().trim(),test_Rail2.getTravelMode().toString().trim());
		test_Rail.Delete();
	}
	
	@Test
	public void testDelete() {
		Rail test_Rail = new Rail();
		test_Rail.setVehicleName("deleteTest");
		test_Rail.Update();
		test_Rail.Delete();
		Rail test_Rail2 = Rail.LoadAll(new String("where RailName = 'DeleteTest'")).get(0);
		Assert.assertNotEquals(test_Rail.getId(),test_Rail2.getId());
		
	}
	
	@Test
	public void testArgumentedConstructor() {
		int testValues[] = {-1,18,100,3,7};
		
		for (int i = 0; i < testValues.length; i++) {
			Rail test_Rail = new Rail(testValues[i]);
			Assert.assertEquals(testValues[i],test_Rail.getId());
		}
	}
	
	
}
