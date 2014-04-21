package JUnitTests;

import core.Location;

import org.junit.*;

public class LocationTest {

	@Test
	public void testDefaultConstructor() {
		Location test_location = new Location();
		Assert.assertEquals(Location.getDEFAULT_COUNTRY(), test_location.getCountry());
		Assert.assertEquals(new Double(Location.getDEFAULT_LATITUDE()), new Double(test_location.getLatitude()));
		Assert.assertEquals(new Double(Location.getDEFAULT_LONGITUDE()), new Double(test_location.getLongitude()));
		Assert.assertEquals(Location.getDEFAULT_LOCATION_NAME(), test_location.getName());
		Assert.assertEquals(Location.getDEFAULT_STATE(), test_location.getState());
		Assert.assertEquals(new Integer(0), new Integer(test_location.getTravelModes().size()));
		Assert.assertEquals(new Integer(0), new Integer(test_location.getVehiclesAtLocation().size()));
		Assert.assertTrue(test_location.isNew());
		Assert.assertFalse(test_location.isDirty());
		
	}
	
	@Test
	public void testArgumentedConstructor() {
		int test_values[] = {17,36,23,82,11,5,99,33,-26};
		for (int check:test_values) {
			Location test_location = new Location(check);
			Assert.assertEquals(new Integer(check), new Integer(test_location.getID()));
		}
	}
	
	@Test
	public void testSetLatitude () {
		double test_values[] = {23.6, 88.8,9876};
	}
}
