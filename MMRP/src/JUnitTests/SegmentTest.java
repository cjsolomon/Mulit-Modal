package JUnitTests;

import core.Segment;
import org.junit.*;
import org.junit.Assert;

public class SegmentTest {
	
	@Test
	public void testDefaultconstructor() {
		Segment test_segment = new Segment();
		Assert.assertEquals(Segment.getDefault, actual);
	}
}
