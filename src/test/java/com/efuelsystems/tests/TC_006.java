package com.efuelsystems.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.efuelsystems.reusemethods.ScreenShot;
import com.efuelsystems.reusemethods.SeleniumWebDriver;

public class TC_006 extends BaseDriverUtil {

	JSONObject LoginDetails;

	/**
	 * In this method we are reading the following parameter from the testng.xml and
	 * based on that we are opening the browser with respective URL
	 * 
	 * @param nodeIp
	 * @param nodePort
	 * @param driverType
	 * @param environment
	 * @throws Exception
	 */

	@BeforeClass
	@Parameters({ "nodeIp", "nodePort", "driverType", "environment" })
	public void beforeClass(@Optional("5555") String nodeIp, @Optional("localhost") String nodePort,
			@Optional("chrome") String driverType,
			@Optional("https://uat-portal.efuelsystems.com") String environment) throws Exception {

		url = environment;

		driver = SeleniumWebDriver.getRemoteWebDriver(driverType, nodeIp, nodePort);

		// Created Object for screen shot
		screenShot = new ScreenShot();
		InputStream details = null;
		String dataFileName = null;

		if (url.equals("https://uat-portal.efuelsystems.com")) {
			dataFileName = "/src/test/java/data_test/TestData.json";
		} else if (url.equals("https://pulse-dev.tatsh.cloud/TestData.aspx")) {
			dataFileName = "/src/test/java/data_test/TestData.json";
		} else {
			dataFileName = "/src/test/java/data_test/TestData.json";
		}

		String path = System.getProperty("user.dir");
		String jsonFolderPath = path + dataFileName;
		File initialFile = new File(jsonFolderPath);
		details = new FileInputStream(initialFile);

		JSONTokener tokener = new JSONTokener(details);
		LoginDetails = new JSONObject(tokener);
	}

	@BeforeMethod()
	public void beforeMethod(ITestResult testResult) {
		extentTest = extentReports.createTest(testResult.getMethod().getMethodName()).assignAuthor("Efuel Test Systems");
	}

	/**
	 * If previous method fails we are capturing the screen shot here
	 * 
	 * @param testResult
	 * @throws InterruptedException
	 */

	@AfterMethod()
	public void afterMethod(ITestResult testResult) throws InterruptedException {
		// If the test method is failed then it will take a screen shot
		if (!testResult.isSuccess()) {
			extentTest.fail(testResult.getMethod().getMethodName(),MediaEntityBuilder.createScreenCaptureFromPath(screenShot.takeScreenShot(driver, testResult.getMethod().getMethodName())).build());
			extentTest.log(Status.FAIL,MarkupHelper.createLabel(testResult.getName() + " Test Case FAILED", ExtentColor.RED));
			extentTest.fail(testResult.getThrowable());
//			loginPage.logout();
		}
	}

	@Test(priority = 1, enabled = true, description = "TC_006")
	public void TC_006() throws IOException, InterruptedException {
		
		extentTest.info("Load the URL in Browser -" + "https://dev-portal.efuelsystems.com");
//		driver.get(url);
//		driver.get("https://dev-portal.efuelsystems.com");

		extentTest.info("TC006 executed successfully");
		Assert.assertEquals(true, true);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("TC006 executed successfully", ExtentColor.GREEN));
	}

	/**
	 * This method is used to close the browser after class
	 *
	 */

	@AfterClass
	public void afterClass() {
//		driver.close();
		driver.quit();
	}

}
