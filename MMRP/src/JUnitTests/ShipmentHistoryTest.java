package JUnitTests;

import core.Segment;
import core.ShipmentHistory;

import org.junit.*;

public class ShipmentHistoryTest {

	@Test 
	public void testDefaultConstrut() {
		
		ShipmentHistory test_history = new ShipmentHistory();
		
		Assert.assertEquals(ShipmentHistory.getDEFAULT_NODE_NUMBER(),test_history.getNodeNumber());
		Assert.assertEquals(ShipmentHistory.getDEFAULT_SEGMENT_ID(), test_history.getSegmentID());
		Assert.assertEquals(ShipmentHistory.getDEFAULT_SHIPMENT_ID(),test_history.getShipmentID());
		
		Assert.assertTrue(test_history.isNew());
		Assert.assertFalse(test_history.isDeleted());
		Assert.assertFalse(test_history.isDirty());
	}
	
	@Test
	public void testArgumentConstruct() {
		ShipmentHistory test_history = new ShipmentHistory();
		Assert.assertTrue(test_history.isNew());
		int test_intteger[] = {23,17,49,5,32};
		
		for (int test : test_intteger) {
			test_history = new ShipmentHistory(test);
			Assert.assertEquals(new Integer(test), new Integer(test_history.getShipmentHistoryID()));
		}
		
		Assert.assertFalse(test_history.isDeleted());
		Assert.assertFalse(test_history.isDirty());
	}
	
	@Test
	public void testSetSegmentId() {
		ShipmentHistory test_history = new ShipmentHistory();
		int test_intteger[] = {23,17,49,5,32};
		for (int test : test_intteger) {
			test_history.setSegmentID(test);;
			Assert.assertEquals(new Integer(test), new Integer(test_history.getSegmentID()));
		}
		Assert.assertTrue(test_history.isDirty());
	}
	
	@Test
	public void testGetSegment() {
		ShipmentHistory test_history = new ShipmentHistory();
		Integer test_num = new Integer(104);
		test_history.setSegmentID(test_num);
		
		Segment result = test_history.getSegment();
		Assert.assertEquals(test_num, new Integer(result.getID()));
	}
	
	@Test
	public void testSetShipmentId() {
		ShipmentHistory test_history = new ShipmentHistory();
		int test_intteger[] = {23,17,49,5,32};
		for (int test : test_intteger) {
			test_history.setShipmentID(test);;
			Assert.assertEquals(new Integer(test), new Integer(test_history.getShipmentID()));
		}
		Assert.assertTrue(test_history.isDirty());
	}
	
	@Test
	public void testGetShipment() {
		ShipmentHistory test_history = new ShipmentHistory();
		
	}
	
}
