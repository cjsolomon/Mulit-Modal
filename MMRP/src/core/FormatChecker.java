package core;
import java.text.*;
import java.util.regex.*;
import GUI.Log;

public class FormatChecker {
	/**
	 * 
	 * @param String telephone number
	 * @return True/False
	 * Returns true if telephone string fits the required telephone/fax format: xxx-xxx-xxxx
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
		if((check > min && check < max) || Double.compare(min, check) == 0 || Double.compare(max, check) == 0) 
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
		boolean ret = true;
		if(date.length() > 7) //Shortest possible date is 8 characters (M/D/YYYY) - reject if shorter
		{
			SimpleDateFormat format = (date.charAt(2) == '/' || date.charAt(1) == '/') 
																? new SimpleDateFormat("MM/D/YYYY")
															   	:new SimpleDateFormat("MMM D YYYY");
			try {
				format.parse(date);
			}
			catch(ParseException e){
				//Log.writeLogWarning("Invalid format on date entry.");
				ret = false;
			}
		
		}
		else
			ret = false;
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
