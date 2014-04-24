package zeusInterface;
import core.FormatChecker;
import core.Vehicle;

public class FormatTest {
	
	public static void main(String[] args)
	{
		boolean accepted = FormatChecker.isEnumerated(Vehicle.TravelModes.class, "TRUCK");
		System.out.println(accepted);
	}

}
