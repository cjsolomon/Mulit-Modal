package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import core.Rail;
import core.Carrier;
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
	public void testSetStatus() {
		Rail test_Rail = new Rail();
		//Default should set status to running
		Assert.assertEquals(Vehicle.Status.Running.toString().trim(),test_Rail.getStatus().toString().trim());
		//Set status to something else and then check that it worked
		test_Rail.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString().trim(),test_Rail.getStatus().toString().trim());
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
	public void testUpdateLoad() {
		Rail test_Rail = new Rail();
		test_Rail.setVehicleName("InsertLoadTest");
		
		test_Rail.setCarrier(Carrier.Load(3));
		test_Rail.Update();
		ArrayList<Rail> rList = Rail.LoadAll(new String("where RailName = 'InsertLoadTest'"));
		if (!rList.isEmpty()) {
			Assert.assertEquals(test_Rail.getVehicleName().toString().trim(), rList.get(0).getVehicleName().toString().trim());
			Assert.assertEquals(test_Rail.getId(),rList.get(0).getId());
			Assert.assertEquals(test_Rail.getCarrier().getCarrierName().trim(),rList.get(0).getCarrier().getCarrierName().trim());
			Assert.assertEquals(test_Rail.getStatus().toString().trim(), rList.get(0).getStatus().toString().trim());
			Assert.assertEquals(test_Rail.isDirty(), rList.get(0).isDirty());
			Assert.assertEquals(test_Rail.isNew(),rList.get(0).isNew());
			Assert.assertEquals(test_Rail.getTravelMode().toString().trim(),rList.get(0).getTravelMode().toString().trim());
			for (Rail delete : rList)
				delete.Delete();
		}
		else {
			Assert.assertEquals(false, true);
		}
		
	}
	
	@Test
	public void testDelete() {
		Rail test_Rail = new Rail();
		test_Rail.setVehicleName("deleteTest");
		test_Rail.Update();
		test_Rail.Delete();
		ArrayList<Rail> rList = Rail.LoadAll(new String("where RailName = 'deleteTest'"));
		System.out.println(rList.size());
		Assert.assertTrue(rList.isEmpty());
		for (Rail delete : rList)
			delete.Delete();	
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
