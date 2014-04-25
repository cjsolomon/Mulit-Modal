package JUnitTests;

import core.Shipment;
import org.junit.*;

public class ShipmentTest {
	
	@Test
	public void testDefaultConstructor() {
		Shipment test_shipment = new Shipment();
		
		Assert.assertFalse(test_shipment.getCongestionByPass());
		Assert.assertEquals(Shipment.getDefaultEarliestArrival(), test_shipment.getEarliestArrivalTime());
		Assert.assertEquals(Shipment.getDefaultEarliestDeparture(), test_shipment.getEarliestDepartureTime());
		Assert.assertEquals(Shipment.getDefaultStartLocationId(), test_shipment.getFromLocationID());
		Assert.assertEquals(Shipment.getDefaultHazmatConstraints(),test_shipment.getHazmat());
		
	}

}
