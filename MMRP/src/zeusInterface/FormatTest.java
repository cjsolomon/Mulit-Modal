package zeusInterface;
import core.FormatChecker;

public class FormatTest {
	
	public static void main(String[] args)
	{
		String number = "814 316 1444";
		boolean accepted = FormatChecker.isValidPhone(number);
		System.out.println(accepted);
	}

}
