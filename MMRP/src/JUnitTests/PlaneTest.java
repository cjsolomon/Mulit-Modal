package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import core.Plane;
import core.Carrier;
import core.Vehicle;

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
	public void testSetStatus() {
		Plane test_Plane = new Plane();
		//Default should set status to running
		Assert.assertEquals(Vehicle.Status.Running.toString().trim(),test_Plane.getStatus().toString().trim());
		//Set status to something else and then check that it worked
		test_Plane.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString().trim(),test_Plane.getStatus().toString().trim());
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
	public void testUpdateLoad() {
		Plane test_Plane = new Plane();
		test_Plane.setVehicleName("InsertLoadTest");
		
		test_Plane.setCarrier(Carrier.Load(3));
		test_Plane.Update();
		ArrayList<Plane> pList = Plane.LoadAll(new String("where PlaneName = 'InsertLoadTest'"));
		if (!pList.isEmpty()) {
			Assert.assertEquals(test_Plane.getVehicleName().toString().trim(), pList.get(0).getVehicleName().toString().trim());
			Assert.assertEquals(test_Plane.getId(),pList.get(0).getId());
			Assert.assertEquals(test_Plane.getCarrier().getCarrierName().trim(),pList.get(0).getCarrier().getCarrierName().trim());
			Assert.assertEquals(test_Plane.getStatus().toString().trim(), pList.get(0).getStatus().toString().trim());
			Assert.assertEquals(test_Plane.isDirty(), pList.get(0).isDirty());
			Assert.assertEquals(test_Plane.isNew(),pList.get(0).isNew());
			Assert.assertEquals(test_Plane.getTravelMode().toString().trim(),pList.get(0).getTravelMode().toString().trim());
			for (Plane delete : pList)
				delete.Delete();
		}
		else {
			Assert.assertEquals(false, true);
		}
		
	}
	
	@Test
	public void testDelete() {
		Plane test_Plane = new Plane();
		test_Plane.setVehicleName("deleteTest");
		test_Plane.Update();
		test_Plane.Delete();
		ArrayList<Plane> pList = Plane.LoadAll(new String("where PlaneName = 'deleteTest'"));
		System.out.println(pList.size());
		Assert.assertTrue(pList.isEmpty());
		for (Plane delete : pList)
			delete.Delete();	
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
