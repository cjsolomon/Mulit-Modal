package zeusInterface;
import core.FormatChecker;

public class FormatTest {
	
	public static void main(String[] args)
	{
		String number = "01/17/2014";
		//boolean accepted = FormatChecker.inRange(2.0,2.0,2.0);
		boolean accepted = FormatChecker.isValidDate(number);
		System.out.println(accepted);
	}

}
