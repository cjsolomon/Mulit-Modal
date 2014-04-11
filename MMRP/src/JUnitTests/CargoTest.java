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
	}
	
	@Test
	public void testIsDirty() {
		Cargo test_cargo = new Cargo();
		Assert.assertEquals(false, test_cargo.isDirty());
		test_cargo.setStatus(Vehicle.Status.Delayed);
		Assert.assertEquals(true, test_cargo.isDirty());
		test_cargo.Update();
		Assert.assertEquals(false, test_cargo.isDirty());
		//test_cargo.Delete();
		test_cargo.Delete();
		
		
	}
	
}
