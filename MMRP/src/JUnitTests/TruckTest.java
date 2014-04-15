package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import core.Truck;
import core.Carrier;
import core.Vehicle;

public class TruckTest {

	@Test
	public void testTravelMode() {
		Truck test_Truck = new Truck();
		Assert.assertEquals(Vehicle.TravelModes.TRUCK.toString(), test_Truck.getTravelMode());
		
	}
	
	@Test
	public void testIsNew() {
		Truck test_Truck = new Truck();
		Assert.assertEquals(true,test_Truck.isNew());
		test_Truck.Update();
		Assert.assertEquals(false,test_Truck.isNew());
		test_Truck.Delete();
	}
	
	@Test
	public void testSetStatus() {
		Truck test_Truck = new Truck();
		//Default should set status to running
		Assert.assertEquals(Vehicle.Status.Running.toString().trim(),test_Truck.getStatus().toString().trim());
		//Set status to something else and then check that it worked
		test_Truck.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString().trim(),test_Truck.getStatus().toString().trim());
	}
	
	@Test
	public void testIsDirty() {
		Truck test_Truck = new Truck();
		Assert.assertEquals(false, test_Truck.isDirty());
		test_Truck.setStatus(Vehicle.Status.Delayed);
		Assert.assertEquals(true, test_Truck.isDirty());
		test_Truck.Update();
		Assert.assertEquals(false, test_Truck.isDirty());
		test_Truck.Delete();		
	}
	
	@Test
	public void testUpdateLoad() {
		Truck test_Truck = new Truck();
		test_Truck.setVehicleName("InsertLoadTest");
		
		test_Truck.setCarrier(Carrier.Load(3));
		test_Truck.Update();
		ArrayList<Truck> tList = Truck.LoadAll(new String("where TruckName = 'InsertLoadTest'"));
		if (!tList.isEmpty()) {
			Assert.assertEquals(test_Truck.getVehicleName().toString().trim(), tList.get(0).getVehicleName().toString().trim());
			Assert.assertEquals(test_Truck.getId(),tList.get(0).getId());
			Assert.assertEquals(test_Truck.getCarrier().getCarrierName().trim(),tList.get(0).getCarrier().getCarrierName().trim());
			Assert.assertEquals(test_Truck.getStatus().toString().trim(), tList.get(0).getStatus().toString().trim());
			Assert.assertEquals(test_Truck.isDirty(), tList.get(0).isDirty());
			Assert.assertEquals(test_Truck.isNew(),tList.get(0).isNew());
			Assert.assertEquals(test_Truck.getTravelMode().toString().trim(),tList.get(0).getTravelMode().toString().trim());
			for (Truck delete : tList)
				delete.Delete();
		}
		else {
			Assert.assertEquals(false, true);
		}
		
	}
	
	@Test
	public void testDelete() {
		Truck test_Truck = new Truck();
		test_Truck.setVehicleName("deleteTest");
		test_Truck.Update();
		test_Truck.Delete();
		ArrayList<Truck> tList = Truck.LoadAll(new String("where TruckName = 'deleteTest'"));
		Assert.assertTrue(tList.isEmpty());
		for (Truck delete : tList)
			delete.Delete();	
	}
	
	@Test
	public void testArgumentedConstructor() {
		int testValues[] = {-1,18,100,3,7};
		
		for (int i = 0; i < testValues.length; i++) {
			Truck test_Truck = new Truck(testValues[i]);
			Assert.assertEquals(testValues[i],test_Truck.getId());
		}
	}
	
	
}
