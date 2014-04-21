package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import core.BaseClass;
import core.Bike;

public class BaseUnitTest {
	
	@Test()
	public void testExecuteQuery() {
		ArrayList<Bike> bList = Bike.LoadAll("where Status = 'RUNNING'");
		Assert.assertFalse(bList.isEmpty());
		bList = Bike.LoadAll("Bull Crapped Here");
		Assert.assertTrue(bList.isEmpty());
		
	}
	
	@Test
	public void testExecuteCommand() {
		try {
			boolean flag = BaseClass.executeCommand("CREATE TABLE Test(TestID int)");
			Assert.assertTrue(flag);
			flag = BaseClass.executeCommand("DROP TABLE Test");
			Assert.assertTrue(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
