package JUnit_IntegrationTests;



import org.junit.*;

import core.Segment;
import core.Shipment;
import Routing.NodeCrawler;
import Routing.DansAlgorithm;

import java.util.ArrayList;

public class DanAlgorithmTest {

	@Test
	public void testMyAlgorithm() {

		ArrayList<Shipment> shipments = Shipment.LoadAll("where ShipmentID > 7999 AND ShipmentID < 9015");

		for (int i = 0; i < shipments.size();i++) {
			NodeCrawler NC = new Routing.NodeCrawler(shipments.get(i));
			NodeCrawler NC2 = new Routing.NodeCrawler(shipments.get(i));
			DansAlgorithm DA = new Routing.DansAlgorithm(shipments.get(i), NC, NC2);

			ArrayList<Segment> route3 =  null;
			Assert.assertNull(route3);
			route3 = DA.getPath();
			Assert.assertNotNull(route3);
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route3);
		}
	}

}
