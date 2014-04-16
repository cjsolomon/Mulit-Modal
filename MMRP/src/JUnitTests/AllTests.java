package JUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import core.Carrier;

@RunWith(Suite.class)
@SuiteClasses({
	LogTest.class,BikeTest.class,CargoTest.class,PlaneTest.class, RailTest.class, 
	TruckTest.class, BaseUnitTest.class, CarrierTest.class
	})
public class AllTests {

}
