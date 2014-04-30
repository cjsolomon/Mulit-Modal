package JUnit_UnitTests;

import java.util.ArrayList;

import core.Carrier;
import core.ShippingRate;
import core.TravelType;

import org.junit.*;

public class ShippingRateTest {
	ShippingRate test_rate;

	@Before
	public void setup() {
		test_rate = new ShippingRate();
	}

	@Test
	public void testDefaultConstructor() {
		//quick test to ensure correct carrier -- Carrier is tested in CarierTest.class
		Assert.assertEquals(new Carrier().getEmailAddress(), test_rate.getCarrier().getEmailAddress());		
		Assert.assertEquals(new Double(ShippingRate.getDefaultFlatRate()), new Double(test_rate.getFlatRate()));
		Assert.assertEquals(new Double(ShippingRate.getDefaultMileRate()), new Double(test_rate.getMileRate()));
		Assert.assertEquals(ShippingRate.getDefaultRank(), test_rate.getRank());
		Assert.assertEquals(new Double(ShippingRate.getDefaultRate1()), new Double(test_rate.getRate1()));
		Assert.assertEquals(new Double(ShippingRate.getDefaultRate2()), new Double(test_rate.getRate2()));
		Assert.assertEquals(new Double(ShippingRate.getDefaultRate3()), new Double(test_rate.getRate3()));
		//quick test to ensure correct travel type -- TravelType tested in TravelTypeTest.class
		Assert.assertEquals(new TravelType().getHazmat(), test_rate.getType().getHazmat());
		Assert.assertEquals(new Double(ShippingRate.getDefaultWeight1()), new Double(test_rate.getWeight1()));
		Assert.assertEquals(new Double(ShippingRate.getDefaultWeight2()), new Double(test_rate.getWeight2()));
		Assert.assertEquals(new Double(ShippingRate.getDefaultWeight3()), new Double(test_rate.getWeight3()));

		Assert.assertTrue(test_rate.isNew());
		Assert.assertFalse(test_rate.isDirty());
		Assert.assertFalse(test_rate.isDeleted());


	}

	@Test
	public void testArgumentedConstructor() {
		int test_values[] = {23,17,999,0,-11,145,3};

		for (int test : test_values) {
			test_rate = new ShippingRate(test);
			Assert.assertEquals(test, test_rate.getId());
			//quick test to ensure correct carrier -- Carrier is tested in CarierTest.class
			Assert.assertEquals(new Carrier().getEmailAddress(), test_rate.getCarrier().getEmailAddress());		
			Assert.assertEquals(new Double(ShippingRate.getDefaultFlatRate()), new Double(test_rate.getFlatRate()));
			Assert.assertEquals(new Double(ShippingRate.getDefaultMileRate()), new Double(test_rate.getMileRate()));
			Assert.assertEquals(ShippingRate.getDefaultRank(), test_rate.getRank());
			Assert.assertEquals(new Double(ShippingRate.getDefaultRate1()), new Double(test_rate.getRate1()));
			Assert.assertEquals(new Double(ShippingRate.getDefaultRate2()), new Double(test_rate.getRate2()));
			Assert.assertEquals(new Double(ShippingRate.getDefaultRate3()), new Double(test_rate.getRate3()));
			//quick test to ensure correct travel type -- TravelType tested in TravelTypeTest.class
			Assert.assertEquals(new TravelType().getHazmat(), test_rate.getType().getHazmat());
			Assert.assertEquals(new Double(ShippingRate.getDefaultWeight1()), new Double(test_rate.getWeight1()));
			Assert.assertEquals(new Double(ShippingRate.getDefaultWeight2()), new Double(test_rate.getWeight2()));
			Assert.assertEquals(new Double(ShippingRate.getDefaultWeight3()), new Double(test_rate.getWeight3()));

			Assert.assertFalse(test_rate.isDirty());
		}
	}

	@Test
	public void testSetTravelType() {
		TravelType test_type = new TravelType();
		test_type.setExp(true);
		test_type.setMaxWeight(133.6542);

		test_rate.setType(test_type);

		Assert.assertEquals(test_type.getExplosives(), test_rate.getType().getExplosives());
		Assert.assertEquals(new Double(test_type.getMaxWeight()), new Double(test_rate.getType().getMaxWeight()));
		Assert.assertTrue(test_rate.isDirty());
	}

	@Test
	public void testSetWeight1() {
		double test_weights[] = {23.6,5020.78, 99.99, .5, 0.0, 1000.0000001};

		for (Double test : test_weights) {
			test_rate.MarkClean();
			test_rate.setWeight1(test);
			Assert.assertEquals(test, new Double(test_rate.getWeight1()));
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetWeight2() {
		double test_weights[] = {23.6,5020.78, 99.99, .5, 0.0, 1000.0000001};

		for (Double test : test_weights) {
			test_rate.MarkClean();
			test_rate.setWeight2(test);
			Assert.assertEquals(test, new Double(test_rate.getWeight2()));
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetWeight3() {
		double test_weights[] = {23.6,5020.78, 99.99, .5, 0.0, 1000.0000001};

		for (Double test : test_weights) {
			test_rate.MarkClean();
			test_rate.setWeight3(test);
			Assert.assertEquals(test, new Double(test_rate.getWeight3()));
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetRate1() {
		double test_rates[] = {23.6,5020.78, 99.99, .5, 0.0, 1000.0000001};

		for (Double test : test_rates) {
			test_rate.MarkClean();
			test_rate.setRate1(test);
			Assert.assertEquals(test, new Double(test_rate.getRate1()));
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetRate2() {
		double test_rates[] = {23.6,5020.78, 99.99, .5, 0.0, 1000.0000001};

		for (Double test : test_rates) {
			test_rate.MarkClean();
			test_rate.setRate2(test);
			Assert.assertEquals(test, new Double(test_rate.getRate2()));
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetRate3() {
		double test_rates[] = {23.6,5020.78, 99.99, .5, 0.0, 1000.0000001};

		for (Double test : test_rates) {
			test_rate.MarkClean();
			test_rate.setRate3(test);
			Assert.assertEquals(test, new Double(test_rate.getRate3()));
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetMileRate() {
		double test_rates[] = {23.6,5020.78, 99.99, .5, 0.0, 1000.0000001};

		for (Double test : test_rates) {
			test_rate.MarkClean();
			test_rate.setMileRate(test);
			Assert.assertEquals(test, new Double(test_rate.getMileRate()));
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetFlatRate() {
		double test_rates[] = {23.6,5020.78, 99.99, .5, 0.0, 1000.0000001};

		for (Double test : test_rates) {
			test_rate.MarkClean();
			test_rate.setFlatRate(test);
			Assert.assertEquals(test, new Double(test_rate.getFlatRate()));
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetRank() {
		int test_values[] = {23,17,999,0,-11,145,3};

		for (int test : test_values) {
			test_rate.MarkClean();
			test_rate.setRank(test);
			Assert.assertEquals(test,test_rate.getRank());
			Assert.assertTrue(test_rate.isDirty());
		}
	}

	@Test
	public void testSetCarrier() {
		Carrier test_carrier = new Carrier();
		test_carrier.setCarrierName("This is the one for the testing");

		test_rate.setCarrier(test_carrier);

		Assert.assertEquals(test_carrier.getCarrierName(),test_rate.getCarrier().getCarrierName());
		Assert.assertTrue(test_carrier.isDirty());
	}

	@Test
	public void testLoad() {
		test_rate.setRank(98765432);
		test_rate.setMileRate(23.65421);
		test_rate.setWeight2(123.454545);
		test_rate.Update();

		ShippingRate test_rate2 = ShippingRate.Load(test_rate.getId());
		Assert.assertEquals(test_rate.getId(), test_rate2.getId());

		Assert.assertEquals(new Double(test_rate.getFlatRate()), new Double(test_rate2.getFlatRate()));
		Assert.assertEquals(new Double(test_rate.getMileRate()), new Double(test_rate2.getMileRate()));
		Assert.assertEquals(test_rate.getRank(), test_rate2.getRank());
		Assert.assertEquals(new Double(test_rate.getRate1()), new Double(test_rate2.getRate1()));
		Assert.assertEquals(new Double(test_rate.getRate2()), new Double(test_rate2.getRate2()));
		Assert.assertEquals(new Double(test_rate.getRate3()), new Double(test_rate2.getRate3()));

		Assert.assertEquals(new Double(test_rate.getWeight1()), new Double(test_rate2.getWeight1()));
		Assert.assertEquals(new Double(test_rate.getWeight2()), new Double(test_rate2.getWeight2()));
		Assert.assertEquals(new Double(test_rate.getWeight3()), new Double(test_rate2.getWeight3()));
		test_rate.Delete();
	}

	@Test
	public void testUpdateLoad() {
		test_rate.setRank(1234567890);

		Assert.assertTrue(test_rate.isNew());
		test_rate.Update();
		Assert.assertFalse(test_rate.isNew());

		ArrayList<ShippingRate> rate_list = new ArrayList<ShippingRate>();
		rate_list = ShippingRate.LoadAll("where Rank = 1234567890");
		if (!rate_list.isEmpty()) {
			ShippingRate test_rate2 = rate_list.get(0);

			Assert.assertEquals(test_rate.getId(), test_rate2.getId());

			Assert.assertEquals(new Double(test_rate.getFlatRate()), new Double(test_rate2.getFlatRate()));
			Assert.assertEquals(new Double(test_rate.getMileRate()), new Double(test_rate2.getMileRate()));
			Assert.assertEquals(test_rate.getRank(), test_rate2.getRank());
			Assert.assertEquals(new Double(test_rate.getRate1()), new Double(test_rate2.getRate1()));
			Assert.assertEquals(new Double(test_rate.getRate2()), new Double(test_rate2.getRate2()));
			Assert.assertEquals(new Double(test_rate.getRate3()), new Double(test_rate2.getRate3()));

			Assert.assertEquals(new Double(test_rate.getWeight1()), new Double(test_rate2.getWeight1()));
			Assert.assertEquals(new Double(test_rate.getWeight2()), new Double(test_rate2.getWeight2()));
			Assert.assertEquals(new Double(test_rate.getWeight3()), new Double(test_rate2.getWeight3()));

			for (ShippingRate delete : rate_list) {
				delete.Delete();
			}
		}
		else
			Assert.assertTrue(false);

		Assert.assertFalse(test_rate.isDirty());
		test_rate.setWeight2(1845.67523);
		Assert.assertTrue(test_rate.isDirty());
		test_rate.Update();

		rate_list = ShippingRate.LoadAll("where Weight2 = 1845.67523");
		if (!rate_list.isEmpty()) {
			ShippingRate test_rate2 = rate_list.get(0);
			Assert.assertEquals(test_rate.getId(), test_rate2.getId());

			Assert.assertEquals(new Double(test_rate.getFlatRate()), new Double(test_rate2.getFlatRate()));
			Assert.assertEquals(new Double(test_rate.getMileRate()), new Double(test_rate2.getMileRate()));
			Assert.assertEquals(test_rate.getRank(), test_rate2.getRank());
			Assert.assertEquals(new Double(test_rate.getRate1()), new Double(test_rate2.getRate1()));
			Assert.assertEquals(new Double(test_rate.getRate2()), new Double(test_rate2.getRate2()));
			Assert.assertEquals(new Double(test_rate.getRate3()), new Double(test_rate2.getRate3()));

			Assert.assertEquals(new Double(test_rate.getWeight1()), new Double(test_rate2.getWeight1()));
			Assert.assertEquals(new Double(test_rate.getWeight2()), new Double(test_rate2.getWeight2()));
			Assert.assertEquals(new Double(test_rate.getWeight3()), new Double(test_rate2.getWeight3()));

			for (ShippingRate delete : rate_list) {
				delete.Delete();
			}
		}
		else
			Assert.assertTrue(false);
		
		test_rate.Delete();
	}
	
	@Test
	public void testDelete() {
		test_rate.setRate3(57.57);
		test_rate.Update();
		
		Assert.assertFalse(test_rate.isDeleted());
		test_rate.Delete();
		Assert.assertTrue(test_rate.isDeleted());
		
		ArrayList<ShippingRate> rate_list = new ArrayList<ShippingRate>();
		rate_list = ShippingRate.LoadAll("where Rate3 = 57.57");
		
		Assert.assertTrue(rate_list.isEmpty());
		
		
		
	}
}
