package utils;

import com.aventstack.extentreports.ExtentTest;

//this for Redirection Tracker 
public class ExtentManager {
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static void setTest(ExtentTest test) {
		extentTest.set(test);
	}

	public static ExtentTest getTest() {
		return extentTest.get();
	}
}
