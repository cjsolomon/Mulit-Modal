package JUnit_IntegrationTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	AStarTest.class, BestFirstFindTest.class, DanAlgorithmTest.class, NextAvailableVehicleTest.class,
	NodeCrawlerTest.class, TravelByTypeTest.class
})

public class AllIntegration {

}
