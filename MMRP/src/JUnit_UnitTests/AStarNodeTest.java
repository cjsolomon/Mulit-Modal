package JUnit_UnitTests;

import Routing.AStarNode;
import org.junit.*;

public class AStarNodeTest {
	private int test_location_id = 32;
	private int segment_id = 17;
	private double cost =233.6;
	AStarNode test_node;
	AStarNode previous_node;
	
	@Before
	public void setup() {
		previous_node = new AStarNode(test_location_id - 1, null, segment_id - 1, cost);
		test_node = new AStarNode(test_location_id, previous_node, segment_id, cost);
	}
	
	@Test
	public void testDefaultConstructor() {
		Assert.assertEquals(segment_id, test_node.getSegmentID());
		Assert.assertEquals(test_location_id,test_node.getLocationID());
		Assert.assertEquals(new Double(cost + previous_node.getCost()), new Double(test_node.getCost()));
		Assert.assertEquals(previous_node, test_node.getPrevious());
		Assert.assertEquals(new Double(cost), new Double(previous_node.getCost()));
	}
}
