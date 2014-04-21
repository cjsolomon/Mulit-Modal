package JUnitTests;

import core.FormatChecker;
import org.junit.Test;
import org.junit.Assert;

public class FormatCheckerTest {

	@Test
	public void testIsValidPhoneTest() {
		String check_good[] = {"814-316-1530","999-999-9999","412-345-6789","724-856-4567"};
		String check_bad[] = {"814-316-153","9999-999-9999","345-679","5856-4567","", "814 316 1444"};
		
		for (String check : check_good) {
			boolean flag = FormatChecker.isValidPhone(check);
			Assert.assertTrue(flag);
		}
		
		for (String check : check_bad) {
			boolean flag = FormatChecker.isValidPhone(check);
			Assert.assertFalse(flag);
		}
	}
	
	@Test
	public void testIsValidEmailTest() {
		String check_good[] = {"me@the.com","sam.thangiah@sru.edu","a@a.com","theMan@wooHoo.org", "isThisWorking@checkme.ca", "pleaseDontEmail@me.info"};
		String check_bad[] = {"814-316-153","@jon.edu","theDanMiller@nothin","Marky mark@google.com",""};
		
		for (String check : check_good) {
			boolean flag = FormatChecker.isValidEmail(check);
			if (!flag) {
				System.out.println("The following email address is returnig as invalid " + check);
			}
			Assert.assertTrue(flag);
		}
		
		for (String check : check_bad) {
			boolean flag = FormatChecker.isValidEmail(check);
			if (flag) {
				System.out.println("The following email address is returning valid: " + check);
			}
			Assert.assertFalse(flag);
		}
	}
	
	@Test
	public void testIsValidAreaCode() {
		String check_good[] = {"000","123","456"};
		String check_bad[] = {"814-316-153","@jon.edu","11 1","111.12",""};
		
		for (String check : check_good) {
			boolean flag = FormatChecker.isValidAreaCode(check);
			if (!flag) {
				System.out.println("The following area code is returnig as invalid " + check);
			}
			Assert.assertTrue(flag);
		}
		for (String check : check_bad) {
			boolean flag = FormatChecker.isValidAreaCode(check);
			if (flag) {
				System.out.println("The following area code is returning valid " + check);
			}
			Assert.assertFalse(flag);
		}
	}
	
	@Test
	public void testInRange() {
		Integer min = new Integer(7);
		Integer max = new Integer(15);
		
		boolean flag = FormatChecker.inRange(min-1, min, max);
		Assert.assertFalse(flag);
		
		for (int i = min; i <=max; i++) {
			flag = FormatChecker.inRange(i, min, max);
			Assert.assertTrue("This should report a number in range", flag);
		}
		
		flag = FormatChecker.inRange(max+1, min, max);
		Assert.assertFalse(flag);
	}
	
}
