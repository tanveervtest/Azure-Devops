package com.efuelsystems.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
import com.efuelsystems.constants.pageobjects.ContactsPage;
import com.efuelsystems.constants.pageobjects.DashboardPage;
import com.efuelsystems.constants.pageobjects.LoginPage;
import com.efuelsystems.reusemethods.ScreenShot;
import com.efuelsystems.reusemethods.SeleniumWebDriver;

public class TC_003_CreateContacts extends BaseDriverUtil {

	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private ContactsPage contactsPage;
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
		// Created the PageObject to read the Address of HTML Elements
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		contactsPage = PageFactory.initElements(driver, ContactsPage.class);
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

	@Test(priority = 1, enabled = true, description = "Verify that the user is able to create contact")
	public void TC_003_CreateContacts() throws IOException, InterruptedException {

		String loginUserName = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("loginUserName");
		String loginPassword = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("loginPassword");
		String companyCode = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("companyCode");
		String firstNameValue = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("firstNameValue");
		String lastNameValue = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("lastNameValue");
		String emailAddressValue = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("emailAddressValue");
		String countryValue = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("countryValue");
		String timeZoneValue = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("timeZoneValue");
		String contactTypeValue = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("contactTypeValue");
		String mobileValue = LoginDetails.getJSONObject("TC_003_CreateContacts").getString("mobileValue");
		
		extentTest.info("Load the URL in Browser -" + "https://dev-portal.efuelsystems.com");
//		driver.get(url);
		driver.get("https://dev-portal.efuelsystems.com");

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
		
		extentTest.info("User logged in with valid credentials");
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User logged in with valid credentials", ExtentColor.GREEN));
		
		dashboardPage.openContactsTab();
		extentTest.info("User successfully opened contacts tab");
//		Assert.assertEquals(actualbackgroundColor, backgroundColor);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User successfully opened contacts tab", ExtentColor.GREEN));
		
	    // Enter first name and last name
		contactsPage.enter_FirstName_LastName_EMailAddress(firstNameValue, lastNameValue, "");
		extentTest.info("User entered first name as "+firstNameValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User entered first name as "+ firstNameValue, ExtentColor.GREEN));
		extentTest.info("User entered last name as "+lastNameValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User entered last name as "+ lastNameValue, ExtentColor.GREEN));
		
		// Select country and time zone
		contactsPage.selectCountryTimeZone(countryValue, timeZoneValue);
		extentTest.info("User selected country as "+ countryValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User selected country as "+ countryValue, ExtentColor.GREEN));
		extentTest.info("User entered time zone as "+ timeZoneValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User entered time zone as "+ timeZoneValue, ExtentColor.GREEN));

		// Select contact type and mobile value
		contactsPage.selectContactType_MobileNumber(contactTypeValue, mobileValue);
		extentTest.info("User selected contact type as "+ contactTypeValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User selected contact type as "+ contactTypeValue, ExtentColor.GREEN));
		extentTest.info("User selected mobile as "+ mobileValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User selected mobile as "+ mobileValue, ExtentColor.GREEN));

		// Verify created contact
		String actualContactName = contactsPage.getVerifyContactLocator().getText();
		String expectedContactName =firstNameValue +" "+ lastNameValue +" (Contact)";
		
		Assert.assertEquals(actualContactName, expectedContactName);
		extentTest.info("Created contact successfully as " + actualContactName);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("Created contact successfully as " + actualContactName, ExtentColor.GREEN));

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
