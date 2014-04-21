package core;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GUI.Log;

public class FormatChecker {

	/**
	 * TODO - isNumeric()
	 */
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
	    	//Log.writeLogWarning("Invalid format on phone/fax number.");
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
		Pattern pattern = Pattern.compile("\\S+@\\S+\\.\\S{2,4}");
	    Matcher matcher = pattern.matcher(address);
	    if (matcher.matches()) {
	    	return true;
	    }
	    else
	    {
	    	//Log.writeLogWarning("Invalid format on email address.");
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
	    	//Log.writeLogWarning("Invalid format on area code.");
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
			//Log.writeLogWarning("Invalid numerical entry given; not in range.");
			return false;
		}
	}

	/**
	 * 
	 * @param String to be tested as a date
	 * @return True/False
	 * Returns true if day is of the proper format: MonthName DayNumber Year or month#/day/Year(4 digit)
	 * ex) January 7th 2014
	 * ex) 01/7/2014 
	 */
	static public boolean isValidDate(String date)
	{
		SimpleDateFormat format = (date.charAt(2) == '/') ? new SimpleDateFormat("MM/D/YYYY")
														   :new SimpleDateFormat("MMM D YYYY");
		boolean ret = true;
		try {
			format.parse(date);
		}
		catch(ParseException e){
			ret = false;
		}
		return ret;
	}
	
	/**
	 * 
	 * @param String to be tested if numeric
	 * @return True if the string passed is a numeric, false if it can not be converted to a number
	 */
	static public boolean isNumeric(String number)
	{
		boolean ret = true;
	    try {

	        Double.parseDouble(number);

	    }catch (NumberFormatException e) {
	    	//Log.writeLogWarning("String failed numeric test, NAN");
	        ret = false;
	    }
	    return ret;
	}

}
