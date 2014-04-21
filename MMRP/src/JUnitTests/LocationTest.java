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
		Location test_location;
		int test_values[] = {17,36,23,82,11,5,99,33,-26};
		for (int check:test_values) {
			test_location = new Location(check);
			Assert.assertEquals(new Integer(check), new Integer(test_location.getID()));
		}
	}
	
	@Test
	public void testSetLatitude () {
		Location test_location = new Location();
		double test_values[] = {23.6, 88.8,-90,0,90.0};
		
		for (double check : test_values) {
			test_location.setLatitude(check);
			Assert.assertEquals(new Double(check), new Double(test_location.getLatitude()));
		}

	}
	
	@Test
	public void testSetLongitude () {
		Location test_location = new Location();
		double test_values[] = {23.6, 88.8,-90,0,90.0};
		
		for (double check : test_values) {
			test_location.setLongitude(check);
			Assert.assertEquals(new Double(check), new Double(test_location.getLongitude()));
		}

	}
	
	@Test
	public void testSetName() {
		Location test_location = new Location();
		String test_string = new String("My Name is Kiiiiiiiiiid Rock");
		
		test_location.setName(test_string);
		Assert.assertEquals(test_string, test_location.getName());
	}
	
	@Test
	public void testSetState() {
		Location test_location = new Location();
		String test_string = new String("AK");
		
		test_location.setState(test_string);
		Assert.assertEquals(test_string, test_location.getState());
	}
}
