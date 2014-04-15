package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import core.BaseClass;
import core.Bike;

public class BaseUnitTest {
	
	@Test
	public void testExecuteQuery() {
		ArrayList<Bike> bList = Bike.LoadAll("where Status = 'RUNNING'");
		Assert.assertFalse(bList.isEmpty());
		ArrayList<Bike> bList2 = Bike.LoadAll("Bull Crapped Here");
		Assert.assertNull(null);
	}
	
	@Test
	public void testExecuteCommand() {
		try {
			boolean flag = BaseClass.executeCommand("CREATE TABLE Test{TestID INT}");
			System.out.println(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
