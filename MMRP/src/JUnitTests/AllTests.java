package JUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({LogTest.class,BikeTest.class,CargoTest.class,PlaneTest.class, RailTest.class, TruckTest.class})
public class AllTests {

}
