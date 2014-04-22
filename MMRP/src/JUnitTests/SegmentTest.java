package JUnitTests;

import core.Segment;
import core.Vehicle;

import org.junit.*;

public class SegmentTest {
	
	@Test
	public void testDefaultconstructor() {
		Segment test_segment = new Segment();
		Assert.assertEquals(new Integer(Segment.getDefaultEndLocationId()), new Integer(test_segment.getEndLocationID()));
		Assert.assertEquals(new Integer(Segment.getDefaultStartLocationId()), new Integer(test_segment.getStartLocationID()));
		Assert.assertEquals(Vehicle.TravelModes.TRUCK, test_segment.getVehicle().getTravelMode());
		
	}
}
