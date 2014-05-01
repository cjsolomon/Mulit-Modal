package JUnit_UnitTests;

import java.util.ArrayList;

import Routing.WeightedMetric;

import org.junit.*;

import core.FormatChecker;
import core.Segment;
import core.ShippingRate;

public class WeightedMetricTest {

	@Test
	public void testDefaultConstructor() {
		WeightedMetric test_metric = new WeightedMetric();

		Assert.assertEquals(WeightedMetric.DEFAULT_COST, test_metric.getCost());
		Assert.assertEquals(WeightedMetric.DEFAULT_DISTANCE, test_metric.getDistance());
		Assert.assertEquals(WeightedMetric.DEFAULT_TIME,test_metric.getTime());	
		
		//Test the normalization:
		Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
		Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
	}

	@Test 
	public void testArgumentedConstructor() {
		int good_values[] = {1,2,17,23,65,89,99,100};
		int good_single_value = 10;
		int bad_values_low[] = {-4,-2,-1};
		int bad_values_high[] = {101,102,9999};

		//Test good values
		for (int test : good_values) {
			WeightedMetric test_metric = new WeightedMetric(test,test,test);
			Assert.assertEquals(test, test_metric.getCost());
			Assert.assertEquals(test, test_metric.getDistance());
			Assert.assertEquals(test,test_metric.getTime());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}

		//Test bad cost
		for (int test : bad_values_low) {
			WeightedMetric test_metric = new WeightedMetric(good_single_value,good_single_value,test);
			Assert.assertEquals(WeightedMetric.getMinCost(), test_metric.getCost());
			Assert.assertEquals(good_single_value, test_metric.getDistance());
			Assert.assertEquals(good_single_value,test_metric.getTime());

			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}

		for (int test : bad_values_high) {
			WeightedMetric test_metric = new WeightedMetric(good_single_value,good_single_value,test);
			Assert.assertEquals(WeightedMetric.getMaxCost(), test_metric.getCost());
			Assert.assertEquals(good_single_value, test_metric.getDistance());
			Assert.assertEquals(good_single_value,test_metric.getTime());

			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}

		//Test bad distance

		for (int test : bad_values_low) {
			WeightedMetric test_metric = new WeightedMetric(test, good_single_value,good_single_value);
			Assert.assertEquals(WeightedMetric.getMinDistance(), test_metric.getDistance());
			Assert.assertEquals(good_single_value, test_metric.getCost());
			Assert.assertEquals(good_single_value,test_metric.getTime());

			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}

		for (int test : bad_values_high) {
			WeightedMetric test_metric = new WeightedMetric(test, good_single_value,good_single_value);
			Assert.assertEquals(WeightedMetric.getMaxDistance(), test_metric.getDistance());
			Assert.assertEquals(good_single_value, test_metric.getCost());
			Assert.assertEquals(good_single_value,test_metric.getTime());

			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}

		//Test bad time
		for (int test : bad_values_low) {
			WeightedMetric test_metric = new WeightedMetric(good_single_value,test,good_single_value);
			Assert.assertEquals(good_single_value, test_metric.getDistance());
			Assert.assertEquals(good_single_value, test_metric.getCost());
			Assert.assertEquals(WeightedMetric.getMinTime(),test_metric.getTime());

			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}

		for (int test : bad_values_high) {
			WeightedMetric test_metric = new WeightedMetric(good_single_value,test,good_single_value);
			Assert.assertEquals(good_single_value, test_metric.getDistance());
			Assert.assertEquals(good_single_value, test_metric.getCost());
			Assert.assertEquals(WeightedMetric.getMaxTime(),test_metric.getTime());

			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
	}
	
	@Test 
	public void testSetDistance() {
		WeightedMetric test_metric = new WeightedMetric();
		int distance_values[] = {11,33,17,26,52,18};
		int distance_values_high[] = {101,102,103,9999};
		int distance_values_low[] = {-1,-2,-3,-4};
		
		for (int test : distance_values) {
			test_metric = new WeightedMetric();
			test_metric.setDistance(test);
			Assert.assertEquals(test,test_metric.getDistance());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
		for (int test : distance_values_high) {
			test_metric = new WeightedMetric();
			test_metric.setDistance(test);
			Assert.assertEquals(WeightedMetric.getMaxDistance(), test_metric.getDistance());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
		for (int test : distance_values_low) {
			test_metric = new WeightedMetric();
			test_metric.setDistance(test);
			Assert.assertEquals(WeightedMetric.getMinDistance(), test_metric.getDistance());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
	}
	
	@Test
	public void testSetCost() {
		WeightedMetric test_metric = new WeightedMetric();
		int Cost_values[] = {11,33,17,26,52,18};
		int Cost_values_high[] = {101,102,103,9999};
		int Cost_values_low[] = {-1,-2,-3,-4};
		
		for (int test : Cost_values) {
			test_metric = new WeightedMetric();
			test_metric.setCost(test);
			Assert.assertEquals(test,test_metric.getCost());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
		for (int test : Cost_values_high) {
			test_metric = new WeightedMetric();
			test_metric.setCost(test);
			Assert.assertEquals(WeightedMetric.getMaxCost(), test_metric.getCost());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
		for (int test : Cost_values_low) {
			test_metric = new WeightedMetric();
			test_metric.setCost(test);
			Assert.assertEquals(WeightedMetric.getMinCost(), test_metric.getCost());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
	}
	
	@Test
	public void testSetTime() {
		WeightedMetric test_metric = new WeightedMetric();
		int Time_values[] = {11,33,17,26,52,18};
		int Time_values_high[] = {101,102,103,9999};
		int Time_values_low[] = {-1,-2,-3,-4};
		
		for (int test : Time_values) {
			test_metric = new WeightedMetric();
			test_metric.setTime(test);
			Assert.assertEquals(test,test_metric.getTime());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
		for (int test : Time_values_high) {
			test_metric = new WeightedMetric();
			test_metric.setTime(test);
			Assert.assertEquals(WeightedMetric.getMaxTime(), test_metric.getTime());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
		for (int test : Time_values_low) {
			test_metric = new WeightedMetric();
			test_metric.setTime(test);
			Assert.assertEquals(WeightedMetric.getMinTime(), test_metric.getTime());
			
			//Test the normalization:
			Double normal_length = Math.sqrt(Math.pow(test_metric.getWeightedCost(), 2.0) + Math.pow(test_metric.getWeightedTime(), 2.0) + Math.pow(test_metric.getWeightedDistance(),2.0));
			Assert.assertTrue(FormatChecker.inRange(normal_length, .98, 1.02));
		}
		
	}
	
	@Test
	public void testGetWeightedCost() {
		Double cost = -1.0;
		Assert.assertTrue(FormatChecker.checkUpperBound(cost, 0.0));
		
		WeightedMetric test_metric = new WeightedMetric();		
		Segment test_segment = new Segment();
		
		cost = test_metric.getWeightedCost(test_segment);
		
		Assert.assertTrue(FormatChecker.checkLowerBound(cost, 0.0));
	}
	
	@Test
	public void testGetLowestWeightedCost() {
		ShippingRate test_rate1 = new ShippingRate();
		ShippingRate test_rate2 = new ShippingRate();
		
		test_rate1.setFlatRate(32.7);
		test_rate2.setFlatRate(18.6);
		
		Segment test_segment1 = new Segment();
		Segment test_segment2 = new Segment();
		Segment test_segment3;
		
		test_segment1.setLane("This is the expensive one");
		test_segment2.setLane("This is the cheap one");
		
		test_segment1.setShippingRate(test_rate1);
		test_segment2.setShippingRate(test_rate2);
		
		ArrayList<Segment> seg_list = new ArrayList<Segment>();
		seg_list.add(test_segment1);
		seg_list.add(test_segment2);
		
		WeightedMetric test_metric = new WeightedMetric();
		test_segment3 = test_metric.getLowestWeightedCostSegment(seg_list);
		Assert.assertEquals(test_segment2.getLane(), test_segment3.getLane());
	}

}
