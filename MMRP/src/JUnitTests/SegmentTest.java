package JUnitTests;

import core.Location;
import core.Segment;
import core.TravelType;
import core.Vehicle;
import core.ShippingRate;

import org.junit.*;

public class SegmentTest {
	
	@Test
	public void testDefaultConstructor() {
		Segment test_segment = new Segment();
		Assert.assertEquals(new Integer(Segment.getDefaultEndLocationId()), new Integer(test_segment.getEndLocationID()));
		Assert.assertEquals(new Integer(Segment.getDefaultStartLocationId()), new Integer(test_segment.getStartLocationID()));
		Assert.assertEquals(Vehicle.TravelModes.TRUCK.toString(), test_segment.getVehicle().getTravelMode());
		Assert.assertEquals(new Double(Segment.getDefaultDistance()), new Double(test_segment.getDistance()));
		Assert.assertEquals(new Integer(Segment.getDefaultDepartureTime()), new Integer(test_segment.getEstimatedDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultArrivalTime()), new Integer(test_segment.getEstimatedArrivalTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultEarliestDepartureTime()), new Integer(test_segment.getEarliestDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultEarliestArrivalTime()), new Integer(test_segment.getEarliestArrivalTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultLatestDepartureTime()), new Integer(test_segment.getLatestDepartureTime()));
		Assert.assertEquals(new Integer(Segment.getDefaultLatestArrivalTime()), new Integer(test_segment.getLatestArrivalTime()));
		Assert.assertEquals(new ShippingRate().getType().toString(), test_segment.getShippingRate().getType().toString());
		Assert.assertEquals(Segment.getDefaultMode(), test_segment.getMode());
		Assert.assertEquals(new TravelType().getTravelTypeName(),test_segment.getTravelType().getTravelTypeName());
		Assert.assertEquals(Segment.getDefaultLanes(), test_segment.getLane());
		Assert.assertTrue(test_segment.getOnBoard().isEmpty());
	}
	
	@Test
	public void testArgumentedConstructor() {
		Segment test_segment = new Segment();
		int values[] = {36,17,22,15,3};
		
		for (int check : values) {
			test_segment = new Segment(check);
			Assert.assertEquals(new Integer(check), new Integer(test_segment.getID()));
		}
	}
	
	@Test
	public void testSetDistance() {
		Segment test_segment = new Segment();
		Double good_values[] = {3.33,6.66,9.99,8.12,12.123, .0000000001, 0.0};
		Double bad_values[] = {-6.21,-2.31,-5.86,-99.99, -.00000001};
		
		for (double check : good_values) {
			test_segment.setDistance(check);
			Assert.assertEquals(new Double(check), new Double(test_segment.getDistance()));
		}
		
		for (double check : bad_values) {
			test_segment.setDistance(check);
			Assert.assertEquals(new Double(0), new Double(test_segment.getDistance()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
	}
	
	@Test
	public void testSetShippingRate() {
		Segment test_segment = new Segment();
		ShippingRate test_rate = new ShippingRate();
		Integer test_int = new Integer(123456789);
		
		test_rate.setRank(test_int);
		test_segment.setShippingRate(test_rate);
		Assert.assertEquals(test_int, new Integer(test_segment.getShippingRate().getRank()));
		
		Assert.assertTrue(test_segment.isDirty());
		
	}
	
	@Test public void testSetEstimatedArrivalTime() {
		Segment test_segment = new Segment();
		Integer early_time = new Integer(2);
		Integer late_time = new Integer(10);
		Integer good_time[] = {2,3,4,5,6,7,8,9,10};
		Integer bad_time_early[] = {-1,0,1};
		Integer bad_time_late[] = {11,12,13};
		
		test_segment.setEarliestArrivalTime(early_time);
		test_segment.setLatestArrivalTime(late_time);
		
		for (Integer check : good_time) {
			test_segment.setEstimatedArrivalTime(check);
			Assert.assertEquals(check, new Integer(test_segment.getEstimatedArrivalTime()));
		}
		
		for (Integer check : bad_time_early) {
			test_segment.setEstimatedArrivalTime(check);
			Assert.assertEquals(early_time, new Integer(test_segment.getEstimatedArrivalTime()));
		}
		
		for (Integer check : bad_time_late) {
			test_segment.setEstimatedArrivalTime(check);
			Assert.assertEquals(late_time, new Integer(test_segment.getEstimatedArrivalTime()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
		
	}
	
	@Test 
	public void testSetEstimatedDepartureTime() {
		Segment test_segment = new Segment();
		Integer early_time = new Integer(2);
		Integer late_time = new Integer(10);
		Integer good_time[] = {2,3,4,5,6,7,8,9,10};
		Integer bad_time_early[] = {-1,0,1};
		Integer bad_time_late[] = {11,12,13};
		
		test_segment.setEarliestDepartureTime(early_time);
		test_segment.setLatestDepartureTime(late_time);
		
		for (Integer check : good_time) {
			test_segment.setEstimatedDepartureTime(check);
			Assert.assertEquals(check, new Integer(test_segment.getEstimatedDepartureTime()));
		}
		
		for (Integer check : bad_time_early) {
			test_segment.setEstimatedDepartureTime(check);
			Assert.assertEquals(early_time, new Integer(test_segment.getEstimatedDepartureTime()));
		}
		
		for (Integer check : bad_time_late) {
			test_segment.setEstimatedDepartureTime(check);
			Assert.assertEquals(late_time, new Integer(test_segment.getEstimatedDepartureTime()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
		
	}
	
	@Test
	public void testSetEarliestArrivalTime() {
		Segment test_segment = new Segment();
		Integer really_big_number = new Integer(10000);
		Integer big_number = new Integer(500);
		Integer good_values[] = {501,853,22,145,9850,10000};
		Integer bad_values[] = {10001,50000};
		
		test_segment.setLatestArrivalTime(really_big_number);
		test_segment.setEarliestArrivalTime(big_number);
		
		for (Integer check : good_values) {
			test_segment.setEarliestArrivalTime(check);
			Assert.assertEquals(check,new Integer(test_segment.getEarliestArrivalTime()));
		}
		
		for (Integer check : bad_values) {
			test_segment.setEarliestArrivalTime(check);
			Assert.assertEquals(really_big_number, new Integer(test_segment.getEarliestArrivalTime()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
		
	}
	
	@Test
	public void testSetEarliestDepartureTime() {
		Segment test_segment = new Segment();
		Integer really_big_number = new Integer(10000);
		Integer big_number = new Integer(500);
		Integer good_values[] = {501,853,22,145,9850,10000};
		Integer bad_values[] = {10001,50000};
		
		test_segment.setLatestDepartureTime(really_big_number);
		test_segment.setEarliestDepartureTime(big_number);
		
		for (Integer check : good_values) {
			test_segment.setEarliestDepartureTime(check);
			Assert.assertEquals(check,new Integer(test_segment.getEarliestDepartureTime()));
		}
		
		for (Integer check : bad_values) {
			test_segment.setEarliestDepartureTime(check);
			Assert.assertEquals(really_big_number, new Integer(test_segment.getEarliestDepartureTime()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
		
	}
	
	@Test
	public void testSetLatestArrivalTime() {
		Segment test_segment = new Segment();
		Integer big_number = new Integer(5000);
		Integer bigger_number = new Integer(10000);
		Integer good_values[] = {5000,6000,100000,5623};
		Integer bad_values[] = {4999,0,123,12};
		
		test_segment.setEarliestArrivalTime(big_number);
		test_segment.setLatestArrivalTime(bigger_number);
		
		for (Integer check : good_values) {
			test_segment.setLatestArrivalTime(check);
			Assert.assertEquals(check, new Integer(test_segment.getLatestArrivalTime()));
		}
		
		for (Integer check : bad_values) {
			test_segment.setLatestArrivalTime(check);
			Assert.assertEquals(big_number, new Integer(test_segment.getLatestArrivalTime()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
	}
	
	@Test
	public void testSetLatestDepartureTime() {
		Segment test_segment = new Segment();
		Integer big_number = new Integer(5000);
		Integer bigger_number = new Integer(10000);
		Integer good_values[] = {5000,6000,100000,5623};
		Integer bad_values[] = {4999,0,123,12};
		
		test_segment.setEarliestDepartureTime(big_number);
		test_segment.setLatestDepartureTime(bigger_number);
		
		for (Integer check : good_values) {
			test_segment.setLatestDepartureTime(check);
			Assert.assertEquals(check, new Integer(test_segment.getLatestDepartureTime()));
		}
		
		for (Integer check : bad_values) {
			test_segment.setLatestDepartureTime(check);
			Assert.assertEquals(big_number, new Integer(test_segment.getLatestDepartureTime()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
	}
	
	@Test
	public void testSetStartLocation() {
		Segment test_segment = new Segment();
		Location test_locations[] = {new Location(555), new Location(666), new Location(777)};
		Integer test_integers[] = {1,2,3,4,5};
		
		for (Location test : test_locations) {
			test_segment.setStartLocation(test);
			Assert.assertEquals(new Integer(test.getID()), new Integer(test_segment.getStartLocation().getID()));
			Assert.assertEquals(new Integer(test_segment.getStartLocation().getID()), new Integer(test_segment.getStartLocationID()));
		}
		
		for (Integer test : test_integers) {
			test_segment = new Segment();
			test_segment.setStartLocation(test);
			Assert.assertEquals(test, new Integer(test_segment.getStartLocation().getID()));
			Assert.assertEquals(new Integer(test_segment.getStartLocation().getID()), new Integer(test_segment.getStartLocationID()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
	}
	
	@Test
	public void testSetEndLocation() {
		Segment test_segment = new Segment();
		Location test_locations[] = {new Location(555), new Location(666), new Location(777)};
		Integer test_integers[] = {1,2,3,4,5};
		
		for (Location test : test_locations) {
			test_segment.setEndLocation(test);
			Assert.assertEquals(new Integer(test.getID()), new Integer(test_segment.getEndLocation().getID()));
			Assert.assertEquals(new Integer(test_segment.getEndLocation().getID()), new Integer(test_segment.getEndLocationID()));
		}
		
		for (Integer test : test_integers) {
			test_segment = new Segment();
			test_segment.setEndLocation(test);
			Assert.assertEquals(test, new Integer(test_segment.getEndLocation().getID()));
			Assert.assertEquals(new Integer(test_segment.getEndLocation().getID()), new Integer(test_segment.getEndLocationID()));
		}
		
		Assert.assertTrue(test_segment.isDirty());
	}
}
