package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;



import core.Bike;
import core.Carrier;


public class CarrierTest {

	@Test
	public void testDefaultConstructor() {
		Carrier test_carrier = new Carrier();
		Assert.assertEquals(test_carrier.getAreaCode(), Carrier.getDEFAULT_AREA_CODE());
		Assert.assertEquals(test_carrier.getAuthorize(), Carrier.getDEFAULT_AUTHORIZE());
		Assert.assertEquals(test_carrier.getCarrierCode(),Carrier.getDEFAULT_CARRIER_CODE());
		Assert.assertEquals(test_carrier.getCarrierName(), Carrier.getDEFAULT_CARRIER_NAME());
		Assert.assertEquals(test_carrier.getContractDate(), Carrier.getDEFAULT_CONTRACT_DATE());
		Assert.assertEquals(test_carrier.getCostModifierBike(), Carrier.getDEFAULT_COST_MOD_BIKE());
		Assert.assertEquals(test_carrier.getCostModifierCargoShip(), Carrier.getDEFAULT_COST_MOD_CARGO());
		Assert.assertEquals(test_carrier.getCostModifierPlane(), Carrier.getDEFAULT_COST_MOD_PLANE());
		Assert.assertEquals(test_carrier.getCostModifierRail(), Carrier.getDEFAULT_COST_MOD_RAIL());
		Assert.assertEquals(test_carrier.getCostModifierTruck(), Carrier.getDEFAULT_COST_MOD_TRUCK());
		Assert.assertEquals(test_carrier.getEmailAddress(), Carrier.getDEFAULT_EMAIL_ADDRESS());
		Assert.assertEquals(test_carrier.getFaxNumber(), Carrier.getDEFAULT_FAX_NUMBER());
		Assert.assertEquals(test_carrier.getInsEndDate(), Carrier.getDEFAULT_INS_END_DATE());
		Assert.assertEquals(test_carrier.getSafetyRateDate(), Carrier.getDEFAULT_SAFETY_RATE_DATE());
		Assert.assertEquals(test_carrier.getSafetyRating(), Carrier.getDEFAULT_SAFETY_RATING());
		Assert.assertFalse(test_carrier.isSendByEmail());
		Assert.assertFalse(test_carrier.isSendByFax());
		Assert.assertTrue(test_carrier.isNew());
		Assert.assertFalse(test_carrier.isDirty());
		Assert.assertFalse(test_carrier.isDeleted());
	}

	@Test
	public void testArgumentedConstructor() {
		int testValues[] = {-1,18,100,3,7};

		for (int i = 0; i < testValues.length; i++) {
			Carrier test_carrier = new Carrier(testValues[i]);
			Assert.assertEquals(testValues[i],test_carrier.getId());
		}
	}
	
	@Test
	public void testSetAreaCode() {
		Carrier test_carrier = new Carrier();
		String test_string = new String("546");
		test_carrier.setAreaCode(test_string);
		Assert.assertEquals(test_string, test_carrier.getAreaCode());
	}
	
	@Test
	public void testSetAuthorize() {
		Carrier test_carrier = new Carrier();
		Integer test_int = new Integer(4); 
		test_carrier.setAuthorize(test_int);
		Assert.assertEquals(test_int,new Integer(test_carrier.getAuthorize()));
	}
	
	@Test public void testSetCarrierCode() {
		Carrier test_carrier = new Carrier();
		String test_string = new String("IHTC");
		test_carrier.setCarrierCode(test_string);
		Assert.assertEquals(test_string, test_carrier.getCarrierCode());
	}
	
	@Test
	public void testSetCarrierName() {
		Carrier test_carrier = new Carrier();
		String test_string = new String("I want to graduate");
		test_carrier.setCarrierName(test_string);
		Assert.assertEquals(test_string, test_carrier.getCarrierName());
	}

	@Test
	public void testSetContractDate() {
		Carrier test_carrier = new Carrier();
		String test_string = ("8/14/2001");
		test_carrier.setContractDate(test_string);
		Assert.assertEquals(test_string, test_carrier.getContractDate());
	}
	
	@Test
	public void testSetCostModifyBike() {
		Carrier test_carrier = new Carrier();
		Integer test_int = new Integer(100);
		test_carrier.setCostModifierBike(test_int);
		Assert.assertEquals(test_int, new Integer(test_carrier.getCostModifierBike()));
	}
	
	@Test
	public void testSetCostModifyCargoShip() {
		Carrier test_carrier = new Carrier();
		Integer test_int = new Integer(75);
		test_carrier.setCostModifierCargoShip(test_int);
		Assert.assertEquals(test_int, new Integer(test_carrier.getCostModifierCargoShip()));
	}
	
	@Test
	public void testSetCostModifyPlane() {
		Carrier test_carrier = new Carrier();
		Integer test_int = new Integer(63);
		test_carrier.setCostModifierPlane(test_int);
		Assert.assertEquals(test_int, new Integer(test_carrier.getCostModifierPlane()));
	}
	
	@Test
	public void testSetCostModifyTruck() {
		Carrier test_carrier = new Carrier();
		Integer test_int = new Integer(43);
		test_carrier.setCostModifierTruck(test_int);
		Assert.assertEquals(test_int, new Integer(test_carrier.getCostModifierTruck()));
	}
	
	@Test
	public void testSetCostModifyRail() {
		Carrier test_carrier = new Carrier();
		Integer test_int = new Integer(17);
		test_carrier.setCostModifierRail(test_int);
		Assert.assertEquals(test_int, new Integer(test_carrier.getCostModifierRail()));
	}
	
	public void testSetEmailAddress() {
		Carrier test_carrier = new Carrier();
		String test_string = ("nomotivation@haveajob.com");
		test_carrier.setEmailAddress(test_string);
		Assert.assertEquals(test_string, test_carrier.getEmailAddress());
	}
	
	public void testSetFaxNumber() {
		Carrier test_carrier = new Carrier();
		String test_string = ("867-5309");
		test_carrier.setFaxNumber(test_string);
		Assert.assertEquals(test_string, test_carrier.getFaxNumber());
	}
	
	public void testSetInsEndDate() {
		Carrier test_carrier = new Carrier();
		String test_string = ("Whats going on with this project?");
		test_carrier.setInsEndDate(test_string);
		Assert.assertEquals(test_string, test_carrier.getInsEndDate());
	}
	
	public void testSetSafetyRateDate() {
		Carrier test_carrier = new Carrier();
		String test_string = ("06-28-2014");
		test_carrier.setSafetyRateDate(test_string);
		Assert.assertEquals(test_string, test_carrier.getSafetyRateDate());
	}
	
	@Test
	public void testSetSafetyRating() {
		Carrier test_carrier = new Carrier();
		Integer test_int = new Integer(2);
		test_carrier.setSafetyRating(test_int);
		Assert.assertEquals(test_int, new Integer(test_carrier.getSafetyRating()));
	}
	
	@Test
	public void testSetSendByEmail() {
		Carrier test_carrier = new Carrier();
		test_carrier.setSendByEmailTrue();
		Assert.assertTrue(test_carrier.isSendByEmail());
		test_carrier.setSendByEmailFalse();
		Assert.assertFalse(test_carrier.isSendByEmail());
	}
	
	@Test
	public void testSendByFax() {
		Carrier test_carrier = new Carrier();
		test_carrier.setSendByFaxTrue();
		Assert.assertTrue(test_carrier.isSendByFax());
		test_carrier.setSendByFaxFalse();
		Assert.assertFalse(test_carrier.isSendByFax());
	}
	
	@Test
	public void testIsNew() {
		Carrier test_carrier = new Carrier();
		Assert.assertTrue(test_carrier.isNew());
		test_carrier.Update();
		Assert.assertFalse(test_carrier.isNew());
		test_carrier.Delete();
	}
	
	@Test
	public void testIsDirty() {
		Carrier test_carrier = new Carrier();
		String areaCode = new String("717");
		Assert.assertFalse(test_carrier.isDirty());
		test_carrier.setAreaCode(areaCode);
		Assert.assertTrue(test_carrier.isDirty());
		test_carrier.Update();
		Assert.assertFalse(test_carrier.isDirty());
		test_carrier.Delete();
	}
	
	@Test
	public void testUpdateLoad() {
		Carrier test_carrier = new Carrier();
		test_carrier.setCarrierName("InsertLoadTest");
		
		
		test_carrier.Update();
		ArrayList<Carrier> cList = Carrier.LoadAll(new String("where CarrierName = 'InsertLoadTest'"));
		if (!cList.isEmpty()) {
			Assert.assertEquals(test_carrier.getAreaCode(), cList.get(0).getAreaCode());
			Assert.assertEquals(test_carrier.getAuthorize(), cList.get(0).getAuthorize());
			Assert.assertEquals(test_carrier.getCarrierCode(),cList.get(0).getCarrierCode());
			//System.out.println("Original Carrier Code is: " + test_carrier.getCarrierCode() + " but is returned from database as: " +cList.get(0).getCarrierCode());
			Assert.assertEquals(test_carrier.getCarrierName(), cList.get(0).getCarrierName());
			//System.out.println("Original Carrier Name is: " + test_carrier.getCarrierName() + " but is returned from database as: " +cList.get(0).getCarrierName());
			Assert.assertEquals(test_carrier.getContractDate(), cList.get(0).getContractDate());
			Assert.assertEquals(test_carrier.getCostModifierBike(), cList.get(0).getCostModifierBike());
			Assert.assertEquals(test_carrier.getCostModifierCargoShip(), cList.get(0).getCostModifierCargoShip());
			Assert.assertEquals(test_carrier.getCostModifierPlane(), cList.get(0).getCostModifierPlane());
			Assert.assertEquals(test_carrier.getCostModifierRail(), cList.get(0).getCostModifierRail());
			Assert.assertEquals(test_carrier.getCostModifierTruck(), cList.get(0).getCostModifierTruck());
			Assert.assertEquals(test_carrier.getEmailAddress(), cList.get(0).getEmailAddress());
			Assert.assertEquals(test_carrier.getFaxNumber(), cList.get(0).getFaxNumber());
			Assert.assertEquals(test_carrier.getInsEndDate(), cList.get(0).getInsEndDate());
			Assert.assertEquals(test_carrier.getSafetyRateDate(), cList.get(0).getSafetyRateDate());
			Assert.assertEquals(test_carrier.getSafetyRating(), cList.get(0).getSafetyRating());
			Assert.assertEquals(test_carrier.isDirty(), cList.get(0).isDirty());
			for (Carrier delete : cList)
				delete.Delete();
		}
		else {
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void testDelete() {
		Carrier test_carrier = new Carrier();
		test_carrier.setEmailAddress("a@a.com");
		test_carrier.Update();
		test_carrier.Delete();
		ArrayList<Carrier> cList = Carrier.LoadAll(new String("where EmailAddress = 'a@a.com'"));
		Assert.assertTrue(cList.isEmpty());
		for (Carrier delete : cList)
			delete.Delete();	
	}
	
	@Test
	public void testDeleted() {
		Carrier test_carrier = new Carrier();
		Assert.assertFalse(test_carrier.isDeleted());
		Assert.assertFalse(test_carrier.isDirty());
		
		test_carrier.setCarrierName("This is the test that never ends");
		test_carrier.Update();
		test_carrier.Delete();
		Assert.assertTrue(test_carrier.isDeleted());
		Assert.assertTrue(test_carrier.isDirty());
	}
	
}
