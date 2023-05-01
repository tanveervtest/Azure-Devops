package com.efuelsystems.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.efuelsystems.constants.pageobjects.DashboardPage;
import com.efuelsystems.constants.pageobjects.LoginPage;
import com.efuelsystems.constants.pageobjects.UserPage;
import com.efuelsystems.reusemethods.ScreenShot;
import com.efuelsystems.reusemethods.SeleniumWebDriver;

public class TC_002_CreateUser extends BaseDriverUtil {

	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private UserPage userPage;
	private ExtentTest extentTest;
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
			dataFileName = "/src/test/java/data_test/Login.json";
		} else if (url.equals("https://pulse-dev.tatsh.cloud/Login2.aspx")) {
			dataFileName = "/src/test/java/data_test/Login.json";
		} else {
			dataFileName = "/src/test/java/data_test/Login.json";
		}

		String path = System.getProperty("user.dir");
		String jsonFolderPath = path + dataFileName;
		File initialFile = new File(jsonFolderPath);
		details = new FileInputStream(initialFile);

		JSONTokener tokener = new JSONTokener(details);
		LoginDetails = new JSONObject(tokener);
		// Created the PageObject to read the Address of HTML Elements
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		userPage = PageFactory.initElements(driver, UserPage.class);
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

	@Test(priority = 1, enabled = true, description = "Verify that the user is able to login to the EfuelSystems successfully")
	public void TC_Login_001_verifyLoginwithValidData() throws IOException, InterruptedException {

		String loginUserName = LoginDetails.getJSONObject("TC_CreateUser_002").getString("loginUserName");
		String loginPassword = LoginDetails.getJSONObject("TC_CreateUser_002").getString("loginPassword");
		String companyCode = LoginDetails.getJSONObject("TC_CreateUser_002").getString("companyCode");
		
		
		String userNameValue = LoginDetails.getJSONObject("TC_CreateUser_002").getString("userNameValue");
		String emailAddressValue = LoginDetails.getJSONObject("TC_CreateUser_002").getString("emailAddressValue");
		String firstNameValue = LoginDetails.getJSONObject("TC_CreateUser_002").getString("firstNameValue");
		String lastNameValue = LoginDetails.getJSONObject("TC_CreateUser_002").getString("lastNameValue");
		String countryValue = LoginDetails.getJSONObject("TC_CreateUser_002").getString("country");
		String timeZoneValue = LoginDetails.getJSONObject("TC_CreateUser_002").getString("timeZone");

		extentTest.info("Load the URL in Browser -" + url);
		driver.get(url);
//		driver.get("https://uat-portal.efuelsystems.com");

		/*
		 * String actualbackgroundColor =
		 * loginPO.getloginButtonLocatorForColorCheck().getCssValue("background-color");
		 * String actualfontColor =
		 * loginPO.getloginButtonLocatorForColorCheck().getCssValue("color"); String
		 * actualfontSize =
		 * loginPO.getloginButtonLocatorForColorCheck().getCssValue("font-size");
		 */

//		extentTest.info("Verified the background colour of Login button as " + actualbackgroundColor);
//		Assert.assertEquals(actualbackgroundColor, backgroundColor);
//		extentTest.log(Status.PASS, MarkupHelper.createLabel(actualbackgroundColor, ExtentColor.GREEN));

//		extentTest.info("Verified the font colour of Login button as " + actualfontColor);
//		Assert.assertEquals(actualfontColor, fontColor);
//		extentTest.log(Status.PASS, MarkupHelper.createLabel(actualfontColor, ExtentColor.GREEN));

//		extentTest.info("Verified the font size of Login button as " + actualfontSize);
//		Assert.assertEquals(actualfontSize, fontSize);
//		extentTest.log(Status.PASS, MarkupHelper.createLabel(actualfontSize, ExtentColor.GREEN));

		// Login with valid data
		loginPage.login(loginUserName, companyCode, loginPassword); 
		
		extentTest.info("Enter Username, Password and click on Login button");
		
		dashboardPage.openUsersTab();
		userPage.enterUserName_EmailAddress(userNameValue, emailAddressValue);
		userPage.enterFirstName_LastName(firstNameValue, lastNameValue);
		userPage.selectCountryTimeZone(countryValue, timeZoneValue);
//		String actualText = loginPO.getclaimDashBoardTextLocator().getText().trim();

		// Verify the application logged
//		Assert.assertEquals(actualText, expectedText);
//		extentTest.info("Actual Text and Expected Text are Matched- " + actualText);
//		extentTest.log(Status.PASS, MarkupHelper.createLabel(actualText, ExtentColor.GREEN));

		// Logout the application
//		loginPO.logout();
//		extentTest.info("Log Out from the application");
	}

	/**
	 * This method is used to close the browser after class
	 *
	 */

	@AfterClass
	public void afterClass() {
//		driver.close();
//		driver.quit();
	}

}
