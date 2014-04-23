package JUnitTests;

import core.Segment;
import core.Vehicle;
import core.ShippingRate;

import org.junit.*;

public class SegmentTest {
	
	@Test
	public void testDefaultconstructor() {
		Segment test_segment = new Segment();
		Assert.assertEquals(new Integer(Segment.getDefaultEndLocationId()), new Integer(test_segment.getEndLocationID()));
		Assert.assertEquals(new Integer(Segment.getDefaultStartLocationId()), new Integer(test_segment.getStartLocationID()));
		Assert.assertEquals(Vehicle.TravelModes.TRUCK.toString(), test_segment.getVehicle().getTravelMode());
		Assert.assertEquals(new Double(Segment.getDefaultDistance()), new Double(test_segment.getDistance()));
		Assert.assertEquals(new Integer(Segment.getDefaultDepartureTime()), new Integer(test_segment.getEstimatedDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultArrivalTime()), new Integer(test_segment.getEstimatedArrivalTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultEarliestDepartureTime()), new Integer(test_segment.getEarliestDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultEarliestArrivalTime()), new Integer(test_segment.getEarliestArrivalTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultLatestDepartureTime()), new Integer(test_segment.getLatestDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultLatestArrivalTime()), new Integer(test_segment.getLatestArrivalTime()));
		Assert.assertEquals(new ShippingRate().getType().toString(), test_segment.getShippingRate().getType().toString());
		
		
	}
}
