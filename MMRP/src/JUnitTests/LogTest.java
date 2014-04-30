package JUnitTests;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;
import org.junit.Assert;
import GUI.Log;

/**
 * 
 * @author dvm1002
 * A test case to check if the Logg class is working correctly.
 *
 */

public class LogTest {

	@Test
	public void testInitialization() {
		Log.createLogg();

		//At this point file should exist.  Verify that it exists
		File log_file = new File("MMRP.log");
		Assert.assertTrue(log_file.exists());
	}

	@Test
	public void testSevereWrite() {
		Log.createLogg();
		try {
			File log_file2 = new File("MMRP.log");
			Scanner in2 = new Scanner(log_file2);
			String testString2 = new String("Severe Test");
			Log.writeLogSevere(testString2);
			String test2 = null;

			while (in2.hasNextLine()) {
				test2 = new String(in2.nextLine().trim());
			}

			Assert.assertEquals(test2, "SEVERE: Severe Test");
			in2.close();
		}
		catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testWarningWrite() {
		Log.createLogg();
		try {
			File log_file3 = new File("MMRP.log");
			Scanner in3 = new Scanner(log_file3);
			String testString3 = new String("Warning Test");
			Log.writeLogWarning(testString3);
			String test3 = null;
			
			while (in3.hasNextLine()) {
				test3 = new String(in3.nextLine().trim());
			}
			
			Assert.assertEquals(test3, "WARNING: Warning Test");
			in3.close();
		}
		catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	

	
	@Test
	public void testInfoWrite() {
		Log.createLogg();
		try {
			File log_file = new File("MMRP.log");
			Scanner in = new Scanner(log_file);
			String testString = new String("Info Test");
			Log.writeLogInfo(testString);
			String test = null;
			//This may be time consuming but I need to get the last line to verify that it is written correctly
			while (in.hasNextLine()) {
				test = new String(in.nextLine().trim());
			}

			Assert.assertEquals(test,new String("INFO: Info Test"));
			in.close();
		}
		catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	


	
}


