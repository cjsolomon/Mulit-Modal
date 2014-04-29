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
		Double min = new Double(7);
		Double max = new Double(15);
		
		boolean flag = FormatChecker.inRange(min-1, min, max);
		Assert.assertFalse(flag);
		
		for (double i = min; i <=max; i+=.5) {
			flag = FormatChecker.inRange(i, min, max);
			Assert.assertTrue("This should report a number in range", flag);
		}
		
		flag = FormatChecker.inRange(max+1, min, max);
		Assert.assertFalse(flag);
	}
	
	@Test
	public void testIsValidDate() {
		String good_date1[] = {"02/4/1999","02/04/1999","2/4/1999","2/04/1999"};
		String good_date2[] = {"JUN 6 1983", "JUN 06 1983"};
		String bad_date[] = {"123 h 1983","MA 6 1943","AB/12/3456","I am THE hero"};
		
		boolean flag;
		for(String check:good_date1) {
			flag = FormatChecker.isValidDate(check);
			if (!flag) {
				System.out.println("The following date is returning as invalid " + check);
			}
			Assert.assertTrue(flag);
		}
		for(String check:good_date2) {
			flag = FormatChecker.isValidDate(check);
			if (!flag) {
				System.out.println("The following date is returning as invalid " + check);
			}
			Assert.assertTrue(flag);
		}
		for(String check:bad_date) {
			flag = FormatChecker.isValidDate(check);
			if (flag) {
				System.out.println("The following date is returning as valid " + check);
			}
			Assert.assertFalse(flag);
		}
	}
	
	@Test
	public void testIsNumeric() {
		String good_numerics[] = {"23","0.076","-123","55"};
		String bad_numerics[] = {"happy", "a"," ","Hello Nurse","Sacrifice"};
		
		boolean flag;
		for (String check : good_numerics) {
			flag = FormatChecker.isNumeric(check);
			if (!flag) 
				System.out.println("The string " + check + " is returning non-numeric");
			Assert.assertTrue(flag);
		}
		for (String check : bad_numerics) {
			flag = FormatChecker.isNumeric(check);
			if (flag)
				System.out.println("The string " + check + " is returning as a numeric");
			Assert.assertFalse(flag);
		}
	}
	
	@Test
	public void testLowerBound()
	{
		Double min = new Double(7);
		
		boolean flag = FormatChecker.checkLowerBound(min-1, min);
		Assert.assertFalse(flag);
		
		for (double i = min; i <= min+100; i+=.5) {
			flag = FormatChecker.checkLowerBound(i, min);
			Assert.assertTrue("This should report a number in range", flag);
		}
	}
	
	@Test
	public void testUpperBound()
	{
		Double max = new Double(50);
		
		boolean flag = FormatChecker.checkUpperBound(max+1, max);
		Assert.assertFalse(flag);
		
		for (double i = 0; i <= max; i+=.5) {
			flag = FormatChecker.checkUpperBound(i, max);
			Assert.assertTrue("This should report a number in range", flag);
		}
	}
	public enum Day {
	    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
	    THURSDAY, FRIDAY, SATURDAY 
	}
	public enum Month {
	    JANUARY, FEBURARY, MARCH, APRIL, 
	    MAY, JUNE, JULY, AUGUST, SEPTEMBER, 
	    OCTOBER, NOVEMBER, DECEMBER
	}
	@Test 
	public void testIsEnumerated() 
	{	
		boolean flag; 
		for(Day test : Day.values())
		{
			flag = FormatChecker.isEnumerated(Day.class, test);
			Assert.assertTrue(flag);
			flag = FormatChecker.isEnumerated(Day.class, test.toString());
			Assert.assertTrue(flag);
		}
		
		for(Month test : Month.values())
		{
			flag = FormatChecker.isEnumerated(Day.class, test);
			Assert.assertFalse(flag);
			flag = FormatChecker.isEnumerated(Day.class, test.toString()); 
			Assert.assertFalse(flag);
		}
	}
	
}
