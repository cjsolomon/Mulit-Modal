package JUnit_UnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import core.Carrier;

@RunWith(Suite.class)
@SuiteClasses({
	LogTest.class,BikeTest.class,CargoTest.class,PlaneTest.class, RailTest.class, 
	TruckTest.class, BaseUnitTest.class, CarrierTest.class, FormatCheckerTest.class,
	LocationTest.class, SegmentTest.class, ShipmentHistoryTest.class, ShipmentTest.class,
	ShippingRateTest.class, TravelTypeTest.class, VehicleTest.class, WeightedMetricTest.class,
	ShipperTest.class, AStarNodeTest.class
	})
public class AllTests {

}
