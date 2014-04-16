package zeusInterface;
import core.FormatChecker;

public class FormatTest {
	
	public static void main(String[] args)
	{
		String number = "me@the.com";
		boolean accepted = FormatChecker.isValidEmail(number);
		System.out.println(accepted);
	}

}
