package JUnitTests;

/**
 * 
 * @author Dan Miller, Jordan Schiller, Zach Petrusch, Christopher Solomon
 * A unit test case to ensure that the Bike class is working correctly.
 *
 */

import org.junit.Test;
import org.junit.Assert;
import core.BaseClass;
import core.Vehicle;
import core.Bike;

public class BikeTest {
	
	@Test
	/**
	 * The first test tests the default constructor as well as some of the methods from Bike.java
	 * 		o Bike.Update();
	 * 		o Bike.setStatus();
	 * 		o Bike.newObject
	 * 		o Bike.dirty
	 * 		o Bike.Delete();
	 */
	public void testDefaultConstructor() {
		Bike testBike = new Bike();
		boolean status;
		
		//Travel Mode Should be set to Bike
		Assert.assertEquals(testBike.getTravelMode(),Vehicle.TravelModes.Bike.toString() );

		//isNew Should Return True as the item still needs pushed to the db
		Assert.assertEquals(true, testBike.isNew());
		
		
		
		//status should be true indicating successful db push and isNew() and isDirty() should be false Must provide status prior to update()
		testBike.setStatus(Vehicle.Status.Running); 
		status = testBike.Update();
		Assert.assertEquals(true, status);
		Assert.assertEquals(false, testBike.isNew());
		Assert.assertEquals(false, testBike.isDirty());
		Assert.assertEquals(Vehicle.Status.Running.toString(), testBike.getStatus());
		
		//set the status of the vehicle to running, this should mark the dirty bit as true
		testBike.setStatus(Vehicle.Status.Disabled);
		Assert.assertEquals(Vehicle.Status.Disabled.toString(), testBike.getStatus());
		Assert.assertEquals(true, testBike.isDirty());
		status = testBike.Update();
		Assert.assertEquals(true, status);
		Assert.assertEquals(false, testBike.isDirty());
		
		//Test Deletion of Bike
		status = testBike.Delete();
		Assert.assertEquals(true, status);
	}
}
