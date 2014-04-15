package JUnitTests;

import org.junit.Test;
import org.junit.Assert;
import core.Plane;
import core.Vehicle;
import core.BaseClass;

public class PlaneTest {

	@Test
	public void testTravelMode() {
		Plane test_Plane = new Plane();
		Assert.assertEquals(Vehicle.TravelModes.PLANE.toString(), test_Plane.getTravelMode());
		
	}
	
	@Test
	public void testIsNew() {
		Plane test_Plane = new Plane();
		Assert.assertEquals(true,test_Plane.isNew());
		test_Plane.Update();
		Assert.assertEquals(false,test_Plane.isNew());
		test_Plane.Delete();
	}
	
	@Test
	public void testIsDirty() {
		Plane test_Plane = new Plane();
		Assert.assertEquals(false, test_Plane.isDirty());
		test_Plane.setStatus(Vehicle.Status.Delayed);
		Assert.assertEquals(true, test_Plane.isDirty());
		test_Plane.Update();
		Assert.assertEquals(false, test_Plane.isDirty());
		test_Plane.Delete();		
	}
	
	@Test
	public void testSetStatus() {
		Plane test_Plane = new Plane();
		//Default should set status to running
		Assert.assertEquals(Vehicle.Status.Running.toString().trim(),test_Plane.getStatus().toString().trim());
		//Set status to something else and then check that it worked
		test_Plane.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString().trim(),test_Plane.getStatus().toString().trim());
	}
	
	@Test
	public void testUpdateLoad() {
		Plane test_Plane = new Plane();
		test_Plane.setVehicleName("InsertLoadTest");
		test_Plane.Update();
		Plane test_Plane2 = Plane.LoadAll(new String("where PlaneName = 'InsertLoadTest'")).get(0);
		Assert.assertEquals(test_Plane.getVehicleName().toString().trim(), test_Plane2.getVehicleName().toString().trim());
		Assert.assertEquals(test_Plane.getId(),test_Plane2.getId());
		//Assert.assertEquals(test_Plane.getCarrier().toString().trim(),test_Plane2.getCarrier().toString().trim());
		Assert.assertEquals(test_Plane.getStatus().toString().trim(), test_Plane2.getStatus().toString().trim());
		Assert.assertEquals(test_Plane.isDirty(), test_Plane2.isDirty());
		Assert.assertEquals(test_Plane.isNew(),test_Plane2.isNew());
		Assert.assertEquals(test_Plane.getTravelMode().toString().trim(),test_Plane2.getTravelMode().toString().trim());
		test_Plane.Delete();
	}
	
	@Test
	public void testDelete() {
		Plane test_Plane = new Plane();
		test_Plane.setVehicleName("deleteTest");
		test_Plane.Update();
		test_Plane.Delete();
		Plane test_Plane2 = Plane.LoadAll(new String("where PlaneName = 'DeleteTest'")).get(0);
		Assert.assertNotEquals(test_Plane.getId(),test_Plane2.getId());
		
	}
	
	@Test
	public void testArgumentedConstructor() {
		int testValues[] = {-1,18,100,3,7};
		
		for (int i = 0; i < testValues.length; i++) {
			Plane test_Plane = new Plane(testValues[i]);
			Assert.assertEquals(testValues[i],test_Plane.getId());
		}
	}
	
	
}
