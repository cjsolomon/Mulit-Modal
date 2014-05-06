package core;
import java.text.*;
import java.util.regex.*;
import GUI.Log;

public class FormatChecker {
	/**
	 * @param String telephone number to be tested
	 * @return Returns true if telephone string fits the required telephone/fax format: xxx-xxx-xxxx
	 */
	static public boolean isValidPhone(String tel)
	{
		if(tel != null)
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
		else
		{
			Log.writeLogWarning("Invalid format on phone/fax number.");
			return false;
		}

	}
	/**
	 * 
	 * @param address, email address to be format tested
	 * @return Returns true if the email address fits the required email address format: emailAddress@domain.com
	 */
	static public boolean isValidEmail(String address)
	{
		if(address != null)
		{
			Pattern pattern = Pattern.compile("\\S+@\\S+\\.\\S{2,4}");
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
		else
		{
			Log.writeLogWarning("Invalid format on email address.");
			return false;
		}
	}

	/**
	 * 
	 * @param areacode to be format tested
	 * @return Returns true if the area code fits the proper format: xxx
	 */
	static public boolean isValidAreaCode(String area)
	{
		if(area != null) 
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
	 * @return Returns true if min <= check <= max
	 */
	static public boolean inRange(double check, double min, double max)
	{
		if((check > min && check < max) || Double.compare(min, check) == 0 || Double.compare(max, check) == 0) 
		{
			return true;
		}
		else 
		{
		//	Log.writeLogWarning("Invalid numerical entry given; not in range.");
			return false;
		}
	}

	/**
	 * 
	 * @param check, the value to check if in range given
	 * @param min, the minimum value
	 * @return Returns true if min <= check
	 */
	static public boolean checkLowerBound(double check, double lowerBound)
	{
		if(check > lowerBound || Double.compare(check, lowerBound) == 0)
		{
			return true;
		}
		else 
		{
			//Log.writeLogWarning("Numerical entry given; below lower bound value.");
			return false;
		}
	}

	/**
	 * 
	 * @param check, the value to check if in range given
	 * @param max, the maximum value
	 * @return Returns true if check <= max
	 */
	static public boolean checkUpperBound(double check, double upperBound)
	{
		if(check < upperBound || Double.compare(check, upperBound) == 0)
		{
			return true;
		}
		else 
		{
			//Log.writeLogWarning("Numerical entry given; above upper bound value.");
			return false;
		}
	}

	/**
	 * 
	 * @param String to be tested as a date
	 * @return Returns true if day is of the proper format: MonthName DayNumber Year or month#/day/Year(4 digit)
	 * ex) January 7th 2014
	 * ex) 01/7/2014 
	 * (The leading 0 in the above example is optional)
	 */
	static public boolean isValidDate(String date)
	{
		boolean ret = true;
		if(date.length() >= 7) //Shortest possible date is 8 characters (M/D/YYYY) - reject if shorter
		{
			SimpleDateFormat format = (date.charAt(2) == '/' || date.charAt(1) == '/') 
					? new SimpleDateFormat("MM/D/YYYY")
			:new SimpleDateFormat("MMM D YYYY");
					try {
						format.parse(date);
					}
					catch(ParseException e){
						//Log.writeLogWarning("Invalid format on date entry - improper format.");
						ret = false;
					}

		}
		else
		{
			Log.writeLogWarning("Invalid format on date entry - too short.");
			ret = false;
		}

		return ret;
	}
	/**
	 * 
	 * @param number. String to be tested to see if it is numeric. 
	 * @return True if the string passed is a numeric, false if it can not be converted to a number
	 */
	static public boolean isNumeric(String number)
	{
		boolean ret = true;
		try {

			Double.parseDouble(number);

		}catch (NumberFormatException e) {
			Log.writeLogWarning("String failed numeric test, NAN");
			ret = false;
		}
		return ret;
	}

	/**
	 * 
	 * @param enumClass the entirety of an Enumerated Type class. Passed by calling enumClass.class 
	 * @param enumValue A string that will be tested against the enum constants of the enumerated class
	 * @return True if the enumValue belongs to the enumClass. 
	 */
	static public boolean isEnumerated(Class <?> enumClass, String enumValue)
	{
		boolean ret = false; 
		Object [] all = enumClass.getEnumConstants();
		for(Object i : all)
		{
			if(i.toString().equals(enumValue))
				ret = true;
		}
		if(!ret) 
		{
			Log.writeLogWarning("Enumerated value is not of Enumerated type.");
		}
		return ret;
	}

	/**
	 * 
	 * @param enumClass the entirety of an Enumerated Type class. Passed by calling enumClass.class 
	 * @param enumValue An object that will be tested against the enum constants of the enumerated class
	 * @return True if the enumValue belongs to the enumClass. 
	 * This is an overloaded method from isEnumerated (Class, String). This version will take any 
	 * object as the second parameter and test if it is a member of the enumerated type class passed.
	 */
	static public boolean isEnumerated(Class <?> enumClass, Object value)
	{
		boolean ret = false; 
		Object [] all = enumClass.getEnumConstants();
		for(Object i : all)
		{
			if(i.toString().equals(value.toString()))
				ret = true;
		}
		if(!ret) 
		{
			Log.writeLogWarning("Enumerated value is not of Enumerated type.");
		}
		return ret;
	}
}
