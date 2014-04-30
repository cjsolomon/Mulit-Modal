package JUnit_UnitTests;

import java.util.ArrayList;

import core.Cargo;
import core.Segment;
import core.Shipment;
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
		Integer test_num = new Integer(8000);
		test_history.setShipmentID(test_num);
				
		Shipment result = test_history.getShipment();
		Assert.assertEquals(test_num, new Integer(result.getId()));
	}
	
	@Test
	public void testSetNodeNumber() {
		ShipmentHistory test_history = new ShipmentHistory();
		Integer test_numbers[] = {23,17,5,36,3,27};
		
		for (Integer test : test_numbers) {
			test_history.setNodeNumber(test);
			Assert.assertEquals(test, new Integer(test_history.getNodeNumber()));
			Assert.assertTrue(test_history.isDirty());
		}
	}
	
	@Test
	public void testLoadAllForShipment() {
		//This test assumes that shipment 1 is in the database and has history
		ArrayList<ShipmentHistory> shipList = ShipmentHistory.LoadAllForShipment(1);
		Assert.assertFalse(shipList.isEmpty());
		
	}
	
	@Test
	public void testUpdateLoad() {
		//Test assumes that segment 8000 is in the database.
		ShipmentHistory test_history = new ShipmentHistory();
		Integer idNumber = new Integer(8000);
		Integer node = new Integer(1);
		
		test_history.setSegmentID(idNumber);
		test_history.setShipmentID(idNumber);
		test_history.setNodeNumber(node);
		
		test_history.Update();
		
		ArrayList<ShipmentHistory> shipList = ShipmentHistory.LoadAllForShipment(idNumber);
		Assert.assertFalse(shipList.isEmpty());
		test_history.Delete();
	}
	
	@Test
	public void testDelete()
	{
		//Test assumes that segment 8000 is in the database.
				ShipmentHistory test_history = new ShipmentHistory();
				Integer idNumber = new Integer(8000);
				Integer node = new Integer(1);
				Integer size1;
				Integer size2;
				
				test_history.setSegmentID(idNumber);
				test_history.setShipmentID(idNumber);
				test_history.setNodeNumber(node);
				
				test_history.Update();
				
				ArrayList<ShipmentHistory> shipList = ShipmentHistory.LoadAllForShipment(idNumber);
				size1 = new Integer(shipList.size());
				test_history.Delete();
				shipList = ShipmentHistory.LoadAllForShipment(idNumber);
				size2 = new Integer(shipList.size());
				Assert.assertNotEquals(size1, size2);
				
	}
	
}
