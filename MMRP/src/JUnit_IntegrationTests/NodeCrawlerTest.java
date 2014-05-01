package JUnit_IntegrationTests;

import java.util.ArrayList;

import org.junit.*;

import Routing.NodeCrawler;
import core.Segment;
import core.Shipment;

public class NodeCrawlerTest {

	@Test
	public void testNodeCrawler() {
		ArrayList<Shipment> shipments = Shipment.LoadAll("where ShipmentID > 7999 AND ShipmentID < 9015");
		for (int i = 0; i < shipments.size();i++) {
			NodeCrawler nodeCrawler = new NodeCrawler(shipments.get(i), 50, 10);
			ArrayList<Segment> route2 = null;
			Assert.assertNull(route2);
			route2 =  nodeCrawler.getPath();
			Assert.assertNotNull(route2);
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route2);
		}
	}

}
