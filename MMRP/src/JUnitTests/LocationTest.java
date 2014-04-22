package JUnitTests;

import java.util.ArrayList;

import core.Bike;
import core.Cargo;
import core.Location;
import core.Plane;
import core.Rail;
import core.Truck;
import core.Vehicle;

import org.junit.*;

public class LocationTest {

	@Test
	public void testDefaultConstructor() {
		Location test_location = new Location();
		Integer num_travel_modes = new Integer(6);			//By default a new location has 6 null travel modes added
		Assert.assertEquals(Location.getDEFAULT_COUNTRY(), test_location.getCountry());
		Assert.assertEquals(new Double(Location.getDEFAULT_LATITUDE()), new Double(test_location.getLatitude()));
		Assert.assertEquals(new Double(Location.getDEFAULT_LONGITUDE()), new Double(test_location.getLongitude()));
		Assert.assertEquals(Location.getDEFAULT_LOCATION_NAME(), test_location.getName());
		Assert.assertEquals(Location.getDEFAULT_STATE(), test_location.getState());
		Assert.assertEquals(new Integer(num_travel_modes), new Integer(test_location.getTravelModes().size()));
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
	
	@Test
	public void testSetCountry() {
		Location test_location = new Location();
		String test_string = new String("Canada, Eh?");
		
		test_location.setState(test_string);
		Assert.assertEquals(test_string, test_location.getState());
	}
	
	@Test
	public void testVehiclesAtLocation() {
		Location test_location = new Location();
		ArrayList<Vehicle> test_vehicles = new ArrayList<Vehicle>();
		test_vehicles.add(new Bike());
		test_vehicles.add(new Plane());
		test_vehicles.add(new Rail());
		test_vehicles.add(new Cargo());
		test_vehicles.add(new Truck());
		
		Integer counter = new Integer(0);
		while (!test_vehicles.isEmpty()) {
			Assert.assertEquals(new Integer(counter), new Integer(test_location.getVehiclesAtLocation().size()));
			test_location.VehicleArriving(test_vehicles.remove(0));
			counter++;
		}
		
		while(!test_location.getVehiclesAtLocation().isEmpty()) {
			Assert.assertEquals(new Integer(counter), new Integer(test_location.getVehiclesAtLocation().size()));
			test_location.VehicleDeparting(test_location.getVehiclesAtLocation().get(0));
			counter--;
		}
	}
	
	@Test
	public void testAddRemoveTravelModes() {
		Location test_location = new Location();
		Integer num_travel_modes = new Integer(6);			//By default a new location has 6 null travel modes added
		Assert.assertEquals(num_travel_modes,new Integer(test_location.getTravelModes().size()));
		
		test_location.addTravelMode(Vehicle.TravelModes.CARGO);
		Assert.assertTrue(test_location.getTravelModes().contains(Vehicle.TravelModes.CARGO));
		
		test_location.removeTravelMode(Vehicle.TravelModes.CARGO);
		Assert.assertFalse(test_location.getTravelModes().contains(Vehicle.TravelModes.CARGO));
	}
	
	@Test
	public void testLoad() {
		Location test_location = new Location();
		String test_string = new String("Load Test String For The Homies");
		test_location.addTravelMode(Vehicle.TravelModes.RAIL);
		
		
		test_location.setName(test_string);
		test_location.Update();
		System.out.println(test_location.getID());
		//System.out.println("The id of the Location is " + test_location.getID());
		Location test_location2 = Location.Load(test_location.getID());
		//Assert.assertEquals(test_location.getCountry(), test_location2.getCountry());
		//Assert.assertEquals(test_location.getName(), test_location2.getName());
		//test_location.Delete();
		
	}
	
	@Test
	public void testUpdateLoad() {
		
	}
}
