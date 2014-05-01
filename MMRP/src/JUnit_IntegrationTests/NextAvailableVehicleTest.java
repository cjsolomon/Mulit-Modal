package JUnit_IntegrationTests;

import java.util.ArrayList;

import Routing.NextAvailableVehicle;
import Routing.WeightedMetric;
import org.junit.*;
import core.Segment;
import core.Shipment;
import core.Vehicle;

public class NextAvailableVehicleTest {

	@Test
	public void testBestFirstFind() {
		ArrayList<Shipment> shipments = Shipment.LoadAll("where ShipmentID > 7999 AND ShipmentID < 9015");
		for (int i = 0; i < shipments.size();i++) {
			NextAvailableVehicle my_nav = new NextAvailableVehicle(Vehicle.TravelModes.TRUCK, new WeightedMetric(1,1,1), shipments.get(i));
			ArrayList<Segment> route2 = null;
			Assert.assertNull(route2);
			route2 =  my_nav.getPath();
			Assert.assertNotNull(route2);
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route2);
		}
	}
}