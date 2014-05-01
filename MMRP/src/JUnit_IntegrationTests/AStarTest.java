package JUnit_IntegrationTests;

import java.util.ArrayList;

import Routing.AStarAlg;
import Routing.WeightedMetric;
import org.junit.*;
import core.Segment;
import core.Shipment;

public class AStarTest {

	@Test
	public void testAStar() {
		ArrayList<Shipment> shipments = Shipment.LoadAll("where ShipmentID > 7999 AND ShipmentID < 9015");
		for (int i = 0; i < shipments.size();i++) {
			AStarAlg a_star = new AStarAlg(shipments.get(i), new WeightedMetric(1,0,0));
			ArrayList<Segment> route2 = null;
			Assert.assertNull(route2);
			route2 =  a_star.getPath();
			Assert.assertNotNull(route2);
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route2);
		}
	}
}

