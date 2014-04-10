package core;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GUI.Log;

public class FormatChecker {

	/**
	 * 
	 * @param tel
	 * @return True/False
	 * Returns true if tel fits the required telephone/fax format: xxx-xxx-xxxx
	 */
	static public boolean isValidPhone(String tel)
	{
		Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
	    Matcher matcher = pattern.matcher(tel);
	    if (matcher.matches()) {
	    	return true;
	    }
	    else
	    {
	    	Log.writeLogWarning("Invalid format on phone/fax number.");
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param address
	 * @return True/False
	 * Returns true if address fits the email address format: emailAddress@domain.com
	 */
	static public boolean isValidEmail(String address)
	{
		Pattern pattern = Pattern.compile("[\\d\\D]*@\\D*.[{com}{edu}{org}]");
	    Matcher matcher = pattern.matcher(address);
	    if (matcher.matches()) {
	    	return true;
	    }
	    else
	    {
	    	Log.writeLogWarning("Invalid format on email address.");
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param area
	 * @return True/False
	 * Returns true if the area code fits the proper format: xxx
	 */
	static public boolean isValidAreaCode(String area)
	{
		Pattern pattern = Pattern.compile("\\d{3}");
	    Matcher matcher = pattern.matcher(area);
	    if (matcher.matches()) {
	    	return true;
	    }
	    else
	    {
	    	Log.writeLogWarning("Invalid format on area code.");
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param check, the value to check if in range given
	 * @param min, the minimum value
	 * @param max, the maximum value
	 * @return True/False
	 * Returns true if check is inside of minimum and maximum values 
	 */
	static public boolean inRange(double check, double min, double max)
	{
		if(check >= min && check <= max) 
		{
			return true;
		}
		else 
		{
			Log.writeLogWarning("Invalid numerical entry given; not in range.");
			return false;
		}
	}
	
	/**
	 * 
	 * @param day
	 * @return True/False
	 * Returns true if day is of the proper format: MonthName DayNumber Year 
	 * ex) January 7th 2014
	 */
	static public boolean isValidDate(String day)
	{
		Pattern pattern = Pattern.compile("*");
	    Matcher matcher = pattern.matcher(day);
	    if (matcher.matches()) {
	    	return true;
	    }
	    else
	    {
	    	//Log.writeLogWarning("Invalid format on date.");
	    	return false;
	    }
		
	}

}
