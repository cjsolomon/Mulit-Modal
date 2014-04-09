package core;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {
	
	/**TODO
	 * Print out to log file 
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
	    	return false;
	    }
	}
	
	static public boolean isValidEmail(String address)
	{
		Pattern pattern = Pattern.compile("[\\d\\D]*@\\D*.[{com}{edu}{org}]");
	    Matcher matcher = pattern.matcher(address);
	    if (matcher.matches()) {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
	
	static public boolean isValidAreaCode(String area)
	{
		Pattern pattern = Pattern.compile("\\d{3}");
	    Matcher matcher = pattern.matcher(area);
	    if (matcher.matches()) {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
	
	static public boolean inRange(double check, double min, double max)
	{
		if(check >= min && check <= max) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

}
