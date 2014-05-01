package JUnit_IntegrationTests;

import java.util.ArrayList;

import Routing.BestFirstFind;
import Routing.WeightedMetric;
import org.junit.*;
import core.Segment;
import core.Shipment;

public class BestFirstFindTest {

	@Test
	public void testBestFirstFind() {
		ArrayList<Shipment> shipments = Shipment.LoadAll("where ShipmentID > 7999 AND ShipmentID < 9015");
		for (int i = 0; i < shipments.size();i++) {
			BestFirstFind my_bff = new BestFirstFind(new WeightedMetric(1,1,1), shipments.get(i));
			ArrayList<Segment> route2 = null;
			Assert.assertNull(route2);
			route2 =  my_bff.getPath();
			Assert.assertNotNull(route2);
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route2);
		}
	}
}