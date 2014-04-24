package zeusInterface;
import core.FormatChecker;
import core.Vehicle;

public class FormatTest {
	
	public static void main(String[] args)
	{
		Vehicle.TravelModes mine = Vehicle.TravelModes.BIKE;
		boolean accepted = FormatChecker.isEnumerated(Vehicle.TravelModes.class, mine);
		System.out.println(accepted);
	}

}
