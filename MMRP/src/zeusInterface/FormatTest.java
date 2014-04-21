package zeusInterface;
import core.FormatChecker;

public class FormatTest {
	
	public static void main(String[] args)
	{
		String number = "01/17/2014";
		boolean accepted = FormatChecker.isValidDate(number);
		System.out.println(accepted);
	}

}
