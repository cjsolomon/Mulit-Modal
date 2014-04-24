package zeusInterface;

import java.util.*;
import core.*;

public class FormatTest {
	
	public static void main(String[] args)
	{
		//Vehicle.TravelModes mine = Vehicle.TravelModes.BIKE;
		//boolean accepted = FormatChecker.isEnumerated(Vehicle.TravelModes.class, mine);
		ArrayList<Shipper> myShip = new ArrayList<Shipper>();
		myShip = Shipper.LoadAll("Where ShipperID = '8023'");
		//boolean accepted = FormatChecker.isValidPhone("123-456-7890");
		//System.out.println(accepted);
	}

}
