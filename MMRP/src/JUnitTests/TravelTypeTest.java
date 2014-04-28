package JUnitTests;

import java.util.ArrayList;

import core.Cargo;
import core.TravelType;
import core.Truck;

import org.junit.*;


public class TravelTypeTest {
	TravelType test_type;

	@Before 
	public void setup() {
		test_type = new TravelType();
	}

	@Test
	public void testDefaultconstructor() {
		Assert.assertFalse(test_type.getExplosives());
		Assert.assertFalse(test_type.getHazmat());
		Assert.assertEquals(new Double(TravelType.getDefaultMaximumCapacity()), new Double(test_type.getMaxCap()));
		Assert.assertEquals(new Double(TravelType.getDefaultMaximumWeight()), new Double(test_type.getMaxWeight()));
		Assert.assertEquals(new Double(TravelType.getDefaultMinimumCapacity()), new Double(test_type.getMinCap()));
		Assert.assertFalse(test_type.getRadiation());
		Assert.assertFalse(test_type.getRefridgeration());
		Assert.assertEquals(TravelType.getDefaultServiceType(), test_type.getServiceType());
		Assert.assertFalse(test_type.getTracking());
		Assert.assertEquals(TravelType.getDefaultTrailer1(), test_type.getTrailer1());
		Assert.assertEquals(TravelType.getDefaultTrailer2(), test_type.getTrailer2());
		Assert.assertEquals(TravelType.getDefaultVehicleMode(), test_type.getTravelTypeMode());
		Assert.assertEquals(TravelType.getDefaultVehicleTypeName(),test_type.getTravelTypeName());
		Assert.assertEquals(TravelType.getDefaultVehicleTypeId(), test_type.getVehicleTypeID());
		Assert.assertTrue(test_type.isNew());
		Assert.assertFalse(test_type.isDirty());
	}

	@Test 
	public void testArgumentedConstructor() {
		int test_id[] = {23,17,32,36,-1,1000,10000,100000,1000000};

		for (int id : test_id) {
			test_type = new TravelType(id);
			Assert.assertEquals(id, test_type.getVehicleTypeID());
			Assert.assertFalse(test_type.getExplosives());
			Assert.assertFalse(test_type.getHazmat());
			Assert.assertEquals(new Double(TravelType.getDefaultMaximumCapacity()), new Double(test_type.getMaxCap()));
			Assert.assertEquals(new Double(TravelType.getDefaultMaximumWeight()), new Double(test_type.getMaxWeight()));
			Assert.assertEquals(new Double(TravelType.getDefaultMinimumCapacity()), new Double(test_type.getMinCap()));
			Assert.assertFalse(test_type.getRadiation());
			Assert.assertFalse(test_type.getRefridgeration());
			Assert.assertEquals(TravelType.getDefaultServiceType(), test_type.getServiceType());
			Assert.assertFalse(test_type.getTracking());
			Assert.assertEquals(TravelType.getDefaultTrailer1(), test_type.getTrailer1());
			Assert.assertEquals(TravelType.getDefaultTrailer2(), test_type.getTrailer2());
			Assert.assertEquals(TravelType.getDefaultVehicleMode(), test_type.getTravelTypeMode());
			Assert.assertEquals(TravelType.getDefaultVehicleTypeName(),test_type.getTravelTypeName());
		}
	}

	@Test
	public void testSetTravelTypeName() {
		String test_strings[] = {"BIKE","CARGO", "PLANE", "RAIL","TRUCK", "ALL", "NONE"};

		for (String test : test_strings) {
			test_type.MarkClean();
			test_type.setTravelTypeName(test);
			Assert.assertEquals(test,test_type.getTravelTypeName());
			Assert.assertTrue(test_type.isDirty());
		}
	}

	@Test
	public void testSetTravelMode() {
		String test_strings_bad[] = {"Jordan","Dan", "Chris", "Zach","Macho Man Randy Savage", "Hulk Hogan"};
		String test_strings_good[] = {"BIKE","CARGO", "PLANE", "RAIL","TRUCK", "ALL", "NONE"};

		for (String test : test_strings_bad) {
			test_type.MarkClean();
			test_type.setTravelTypeMode(test);
			Assert.assertEquals(new String("NONE"),test_type.getTravelTypeMode());
		}

		for (String test : test_strings_good) {
			test_type.MarkClean();
			test_type.setTravelTypeMode(test);
			Assert.assertEquals(test,test_type.getTravelTypeMode());
			Assert.assertTrue(test_type.isDirty());
		}
	}

	@Test
	public void testSetTrailer1() {
		String test_strings[] = {"Jordan","Dan", "Chris", "Zach","Macho Man Randy Savage", "Hulk Hogan"};

		for (String test : test_strings) {
			test_type.MarkClean();
			test_type.setTrailer1(test);
			Assert.assertEquals(test,test_type.getTrailer1());
			Assert.assertTrue(test_type.isDirty());
		}
	}

	@Test
	public void testSetTrailer2() {
		String test_strings[] = {"Jordan","Dan", "Chris", "Zach","Macho Man Randy Savage", "Hulk Hogan"};

		for (String test : test_strings) {
			test_type.MarkClean();
			test_type.setTrailer2(test);
			Assert.assertEquals(test,test_type.getTrailer2());
			Assert.assertTrue(test_type.isDirty());
		}
	}

	@Test
	public void testSetMinCap() {
		Double test_doubles_good[] = {23.6,17.0,0.0000000001, 100.0,123456.789,0.0};
		Double test_doubles_bad[] = {-.000000000001, -100.2, -23.6};

		for (Double bad : test_doubles_bad) {
			test_type.MarkClean();
			test_type.setMinCap(bad);
			Assert.assertEquals(new Double(0), new Double(test_type.getMinCap()));
			Assert.assertTrue(test_type.isDirty());
		}

		for (Double good : test_doubles_good) {
			test_type.MarkClean();
			test_type.setMinCap(good);
			Assert.assertEquals(good, new Double(test_type.getMinCap()));
			Assert.assertTrue(test_type.isDirty());
		}
	}
	
	@Test
	public void testSetMaxCap() {
		Double test_doubles_good[] = {23.6,17.0,0.0000000001, 100.0,123456.789,0.0,999999.9999999999};
		Double test_doubles_bad[] = {-.000000000001, -100.2, -23.6, 1000000.0000000001};

		for (Double bad : test_doubles_bad) {
			test_type.MarkClean();
			test_type.setMaxCap(bad);
			Assert.assertEquals(new Double(TravelType.getDefaultMaximumCapacity()), new Double(test_type.getMaxCap()));
			Assert.assertTrue(test_type.isDirty());
		}

		for (Double good : test_doubles_good) {
			test_type.MarkClean();
			test_type.setMaxCap(good);
			Assert.assertEquals(good, new Double(test_type.getMaxCap()));
			Assert.assertTrue(test_type.isDirty());
		}
	}
	
	@Test
	public void testSetMaxWeight() {
		Double test_doubles_good[] = {23.6,17.0,0.0000000001, 100.0,123456.789,0.0,999999.9999999999};
		Double test_doubles_bad[] = {-.000000000001, -100.2, -23.6, 1000000.0000000001};

		for (Double bad : test_doubles_bad) {
			test_type.MarkClean();
			test_type.setMaxWeight(bad);
			Assert.assertEquals(new Double(TravelType.getDefaultMaximumWeight()), new Double(test_type.getMaxWeight()));
			Assert.assertTrue(test_type.isDirty());
		}

		for (Double good : test_doubles_good) {
			test_type.MarkClean();
			test_type.setMaxWeight(good);
			Assert.assertEquals(good, new Double(test_type.getMaxWeight()));
			Assert.assertTrue(test_type.isDirty());
		}
	}
	
	@Test
	public void testServiceType() {
		String test_strings[] = {"One","little","monkey","jumping","on","the","bed",""};
		
		for (String test : test_strings) {
			test_type.MarkClean();
			test_type.setServiceType(test);
			Assert.assertEquals(test,test_type.getServiceType());
			Assert.assertTrue(test_type.isDirty());
		}
	}
	
	@Test
	public void testSetRadiation() {
		Assert.assertFalse(test_type.getRadiation());
		
		test_type.setRadTrue();
		Assert.assertTrue(test_type.getRadiation());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setRadFalse();
		Assert.assertFalse(test_type.getRadiation());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setRad(true);
		Assert.assertTrue(test_type.getRadiation());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setRad(false);
		Assert.assertFalse(test_type.getRadiation());
		Assert.assertTrue(test_type.isDirty());
		
		
	}
	
	@Test
	public void testSetRefrigeration() {
		Assert.assertFalse(test_type.getRefridgeration());
		
		test_type.setRefTrue();
		Assert.assertTrue(test_type.getRefridgeration());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setRefFalse();
		Assert.assertFalse(test_type.getRefridgeration());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setRef(true);
		Assert.assertTrue(test_type.getRefridgeration());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setRef(false);
		Assert.assertFalse(test_type.getRefridgeration());
		Assert.assertTrue(test_type.isDirty());
		
		
	}
	
	@Test
	public void testSetHazmat() {
		Assert.assertFalse(test_type.getHazmat());
		
		test_type.setHazTrue();
		Assert.assertTrue(test_type.getHazmat());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setHazFalse();
		Assert.assertFalse(test_type.getHazmat());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setHaz(true);
		Assert.assertTrue(test_type.getHazmat());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setHaz(false);
		Assert.assertFalse(test_type.getHazmat());
		Assert.assertTrue(test_type.isDirty());
		
		
	}
	
	@Test
	public void testSetExplosives() {
		Assert.assertFalse(test_type.getExplosives());
		
		test_type.setExpTrue();
		Assert.assertTrue(test_type.getExplosives());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setExpFalse();
		Assert.assertFalse(test_type.getExplosives());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setExp(true);
		Assert.assertTrue(test_type.getExplosives());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setExp(false);
		Assert.assertFalse(test_type.getExplosives());
		Assert.assertTrue(test_type.isDirty());
		
		
	}
	
	@Test
	public void testSetTracking() {
		Assert.assertFalse(test_type.getTracking());
		
		test_type.setTrackingTrue();
		Assert.assertTrue(test_type.getTracking());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setTrackingFalse();
		Assert.assertFalse(test_type.getTracking());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setTracking(true);
		Assert.assertTrue(test_type.getTracking());
		Assert.assertTrue(test_type.isDirty());
		
		test_type.MarkClean();
		test_type.setTracking(false);
		Assert.assertFalse(test_type.getTracking());
		Assert.assertTrue(test_type.isDirty());
		
		
	}
	
	@Test
	public void testLoad() {
		test_type.setServiceType("JUnit TravelTypeTest.testLoad()");
		test_type.setTravelTypeMode("BIKE");
		test_type.Update();
		TravelType test_type2 = TravelType.Load(test_type.getVehicleTypeID());
		
		
		Assert.assertEquals(test_type.getVehicleTypeID(), test_type2.getVehicleTypeID());
		Assert.assertEquals(test_type.getExplosives(),test_type2.getExplosives());
		Assert.assertEquals(test_type.getHazmat(),test_type2.getHazmat());
		Assert.assertEquals(new Double(test_type.getMaxCap()), new Double(test_type2.getMaxCap()));
		Assert.assertEquals(new Double(test_type.getMaxWeight()), new Double(test_type2.getMaxWeight()));
		Assert.assertEquals(new Double(test_type.getMinCap()), new Double(test_type2.getMinCap()));
		Assert.assertEquals(test_type.getRadiation(),test_type2.getRadiation());
		Assert.assertEquals(test_type.getRefridgeration(),test_type2.getRefridgeration());
		Assert.assertEquals(test_type.getServiceType(), test_type2.getServiceType());
		Assert.assertEquals(test_type.getTracking(),test_type2.getTracking());
		Assert.assertEquals(test_type.getTrailer1(), test_type2.getTrailer1());
		Assert.assertEquals(test_type.getTrailer2(), test_type2.getTrailer2());
		Assert.assertEquals(test_type.getTravelTypeMode(), test_type2.getTravelTypeMode());
		Assert.assertEquals(test_type.getTravelTypeName(),test_type2.getTravelTypeName());
		
		test_type.Delete();
		
	}
	
	@Test
	public void testLoadAllForVehicle() {
		Truck test_truck = new Truck();
		Truck test_truck2 = new Truck();
		test_truck.setVehicleName("JUnit TravelTypeTest.testLoadAllForVehicle()");
		test_truck2.setVehicleName("JUnit TravelTypeTest.testLoadAllForVehicle()");
		
		
		TravelType test_type2 = new TravelType();
		test_type2.setExpTrue();
		test_type2.setRefTrue();
		
		test_truck.addToTypes(test_type);
		test_truck.addToTypes(test_type2);
		
		test_truck.Update();
		test_truck2.Update();
		
		ArrayList<TravelType> travel_list = new ArrayList<TravelType>();
		
		travel_list = TravelType.LoadForVehicle(test_truck);
		
		Assert.assertEquals(2,travel_list.size());
		
		travel_list = TravelType.LoadForVehicle(test_truck2);
		Assert.assertEquals(0, travel_list.size());
			
		test_truck.Delete();
		test_truck2.Delete();
	}
	
	
}
