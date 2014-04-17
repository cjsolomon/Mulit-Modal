package JUnitTests;

import core.FormatChecker;
import org.junit.Test;
import org.junit.Assert;

public class FormatCheckerTest {

	@Test
	public void isValidPhoneTest() {
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
	public void isValidEmailTest() {
		String check_good[] = {"me@the.com","sam.thangiah@sru.edu","a@a.com","theMan@wooHoo.org"};
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
}
