package JUnit_UnitTests;

import java.util.ArrayList;

import core.Shipment;
import core.Shipper;

import org.junit.*;

public class ShipperTest {
	Shipper test_shipper;
	
	@Before
	public void initialize() {
		test_shipper = new Shipper();
	}
	
	@Test
	public void testDefaultconstructor() {
		Assert.assertEquals(Shipper.getDefaultCompanyName(),test_shipper.getCompanyName());
		Assert.assertEquals(Shipper.getDefaultContactName(),test_shipper.getContactName());
		Assert.assertEquals(Shipper.getDefaultEmailAddress(),test_shipper.getEmailAddress());
		Assert.assertEquals(Shipper.getDefaultPhoneNumber(),test_shipper.getPhoneNumber());
		Assert.assertEquals(Shipper.getDefaultPreferredCarriers(), test_shipper.getPrefferedCarriers());
		Assert.assertEquals(Shipper.getDefaultLocationId(), test_shipper.getLocationID());
		Assert.assertTrue(test_shipper.isNew());
		Assert.assertFalse(test_shipper.isDeleted());
		Assert.assertFalse(test_shipper.isDirty());
	}
	
	@Test
	public void testArgumentedConstructor() {
		int test_values[] = {23,17,32,26,2011,4};
		
		for (int test : test_values) {
			test_shipper = new Shipper(test);
			Assert.assertEquals(test, test_shipper.getID());
			Assert.assertEquals(Shipper.getDefaultCompanyName(),test_shipper.getCompanyName());
			Assert.assertEquals(Shipper.getDefaultContactName(),test_shipper.getContactName());
			Assert.assertEquals(Shipper.getDefaultEmailAddress(),test_shipper.getEmailAddress());
			Assert.assertEquals(Shipper.getDefaultPhoneNumber(),test_shipper.getPhoneNumber());
			Assert.assertEquals(Shipper.getDefaultPreferredCarriers(), test_shipper.getPrefferedCarriers());
			Assert.assertEquals(Shipper.getDefaultLocationId(), test_shipper.getLocationID());
			Assert.assertFalse(test_shipper.isDirty());
		}
	}
	
	@Test
	public void testSetLocation() {
		int test_values[] = {23,17,32,26,2011,4};
	
		for (int test : test_values) {
			test_shipper.MarkClean(); 				//Marking clean as the function should mark it dirty
			test_shipper.setLocationID(test);
			Assert.assertEquals(test, test_shipper.getLocationID());
			Assert.assertTrue(test_shipper.isDirty());
		}
	}
	
	@Test
	public void testSetPreferredCarriers() {
		String test_values[] = {"Jordan", "Dan", "Zach", "Chris", "Macho Man Randy Savage", "Hulk Hogan"};
		
		for (String test : test_values) {
			test_shipper.MarkClean();
			test_shipper.setPrefferedCarriers(test);
			Assert.assertEquals(test,test_shipper.getPrefferedCarriers());
			Assert.assertTrue(test_shipper.isDirty());
		}
	}
	
	@Test
	public void testSetCompanyName() {
		String test_values[] = {"Jordan", "Dan", "Zach", "Chris", "Macho Man Randy Savage", "Hulk Hogan"};
		
		for (String test : test_values) {
			test_shipper.MarkClean();
			test_shipper.setCompanyName(test);
			Assert.assertEquals(test,test_shipper.getCompanyName());
			Assert.assertTrue(test_shipper.isDirty());
		}
	}
	
	@Test
	public void testSetConatctName() {
		String test_values[] = {"Jordan", "Dan", "Zach", "Chris", "Macho Man Randy Savage", "Hulk Hogan"};
		
		for (String test : test_values) {
			test_shipper.MarkClean();
			test_shipper.setContactName(test);
			Assert.assertEquals(test,test_shipper.getContactName());
			Assert.assertTrue(test_shipper.isDirty());
		}
	}
	
	@Test
	public void testSetPhoneNumber() {
		String test_values_bad[] = {"Jordan", "Dan", "Zach", "Chris", "Macho Man Randy Savage", "Hulk Hogan"};
		String test_values_good[] = {"814-316-1570", "800-956-2323"};
		
		for (String test : test_values_bad) {
			test_shipper.MarkClean();
			test_shipper.setPhoneNumber(test);
			Assert.assertEquals(Shipper.getDefaultPhoneNumber(),test_shipper.getPhoneNumber());
			Assert.assertFalse(test_shipper.isDirty());
		}
		
		for (String test:test_values_good) {
			test_shipper.MarkClean();
			test_shipper.setPhoneNumber(test);
			Assert.assertEquals(test,test_shipper.getPhoneNumber());
			Assert.assertTrue(test_shipper.isDirty());
			
		}
	}
	
	@Test
	public void testSetEmail() {
		String test_values_bad[] = {"Jordan", "Dan", "Zach", "Chris", "Macho Man Randy Savage", "Hulk Hogan"};
		String test_values_good[] = {"me@me.com", "dan@dan.info", "jordan@theGreatWhiteNorth.ca"};
		
		for (String test : test_values_bad) {
			test_shipper.MarkClean();
			test_shipper.setEmailAddress(test);
			Assert.assertEquals(Shipper.getDefaultEmailAddress(),test_shipper.getEmailAddress());
			Assert.assertFalse(test_shipper.isDirty());
		}
		
		for (String test:test_values_good) {
			test_shipper.MarkClean();
			test_shipper.setEmailAddress(test);
			Assert.assertEquals(test,test_shipper.getEmailAddress());
			Assert.assertTrue(test_shipper.isDirty());
			
		}
	}
	
	@Test
	public void testLoad() {
		String test_string = new String("JUnit Shipper.testLoad()");
		test_shipper.setContactName(test_string);
		
		test_shipper.Update();
		Shipper test_shipper2 = Shipper.Load(test_shipper.getID());
		
		Assert.assertEquals(test_shipper.getID(),test_shipper2.getID());
		Assert.assertEquals(test_shipper.getCompanyName(),test_shipper2.getCompanyName());
		Assert.assertEquals(test_shipper.getContactName(),test_shipper2.getContactName());
		Assert.assertEquals(test_shipper.getEmailAddress(),test_shipper2.getEmailAddress());
		Assert.assertEquals(test_shipper.getPhoneNumber(),test_shipper2.getPhoneNumber());
		Assert.assertEquals(test_shipper.getPrefferedCarriers(), test_shipper2.getPrefferedCarriers());
		Assert.assertEquals(test_shipper.getLocationID(), test_shipper2.getLocationID());
		Assert.assertEquals(test_shipper.isNew(), test_shipper2.isNew());
		Assert.assertEquals(test_shipper.isDirty(), test_shipper2.isDirty());
		
		test_shipper.Delete();
	}
	
	@Test
	public void testUpdateLoad() {
		String test_string = new String("JUnit Shipper.testUpdateLoad()");
		test_shipper.setCompanyName(test_string);
		
		test_shipper.Update();
		ArrayList<Shipper> shipper_list = Shipper.LoadAll("where CompanyName = '" + test_string + "'");
		if (!shipper_list.isEmpty()) {
			Shipper test_shipper2 = shipper_list.get(0);
			Assert.assertEquals(test_shipper.getID(),test_shipper2.getID());
			Assert.assertEquals(test_shipper.getCompanyName(),test_shipper2.getCompanyName());
			Assert.assertEquals(test_shipper.getContactName(),test_shipper2.getContactName());
			Assert.assertEquals(test_shipper.getEmailAddress(),test_shipper2.getEmailAddress());
			Assert.assertEquals(test_shipper.getPhoneNumber(),test_shipper2.getPhoneNumber());
			Assert.assertEquals(test_shipper.getPrefferedCarriers(), test_shipper2.getPrefferedCarriers());
			Assert.assertEquals(test_shipper.getLocationID(), test_shipper2.getLocationID());
			Assert.assertEquals(test_shipper.isNew(), test_shipper2.isNew());
			Assert.assertEquals(test_shipper.isDirty(), test_shipper2.isDirty());
			
			for (Shipper delete : shipper_list)
				delete.Delete();
		}
		else {
			Assert.assertTrue(false);
		}
		
		test_shipper.setPrefferedCarriers(test_string);
		test_shipper.Update();
		
		shipper_list = Shipper.LoadAll("where prefCarriers = '" + test_string + "'");
		if (!shipper_list.isEmpty()) {
			Shipper test_shipper2 = shipper_list.get(0);
			Assert.assertEquals(test_shipper.getID(),test_shipper2.getID());
			Assert.assertEquals(test_shipper.getCompanyName(),test_shipper2.getCompanyName());
			Assert.assertEquals(test_shipper.getContactName(),test_shipper2.getContactName());
			Assert.assertEquals(test_shipper.getEmailAddress(),test_shipper2.getEmailAddress());
			Assert.assertEquals(test_shipper.getPhoneNumber(),test_shipper2.getPhoneNumber());
			Assert.assertEquals(test_shipper.getPrefferedCarriers(), test_shipper2.getPrefferedCarriers());
			Assert.assertEquals(test_shipper.getLocationID(), test_shipper2.getLocationID());
			Assert.assertEquals(test_shipper.isNew(), test_shipper2.isNew());
			Assert.assertEquals(test_shipper.isDirty(), test_shipper2.isDirty());
			
			for (Shipper delete : shipper_list)
				delete.Delete();
		}
		else {
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void testDelete() {
		test_shipper = new Shipper();
		test_shipper.setCompanyName("JUnit ShipperTest.testDelete()");
		test_shipper.Update();
		test_shipper.Delete();
		ArrayList<Shipper> lList = Shipper.LoadAll(new String("where CompanyName = 'JUnit ShipperTest.testDelete()'"));
		Assert.assertTrue(lList.isEmpty());
		for (Shipper delete : lList)
			delete.Delete();
		Assert.assertTrue(test_shipper.isDeleted());
	}

}
