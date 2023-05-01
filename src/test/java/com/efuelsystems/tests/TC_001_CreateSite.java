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
import com.efuelsystems.constants.pageobjects.DashboardPage;
import com.efuelsystems.constants.pageobjects.LoginPage;
import com.efuelsystems.constants.pageobjects.SitesPage;
import com.efuelsystems.reusemethods.ScreenShot;
import com.efuelsystems.reusemethods.SeleniumWebDriver;

public class TC_001_CreateSite extends BaseDriverUtil {

	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private SitesPage sitesPage;
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
			@Optional("chrome") String driverType, @Optional("https://uat-portal.efuelsystems.com") String environment)
			throws Exception {

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
		sitesPage = PageFactory.initElements(driver, SitesPage.class);
	}

	@BeforeMethod()
	public void beforeMethod(ITestResult testResult) {
		extentTest = extentReports.createTest(testResult.getMethod().getMethodName())
				.assignAuthor("Efuel Test Systems");
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
			extentTest
					.fail(testResult.getMethod().getMethodName(),
							MediaEntityBuilder
									.createScreenCaptureFromPath(
											screenShot.takeScreenShot(driver, testResult.getMethod().getMethodName()))
									.build());
			extentTest.log(Status.FAIL,
					MarkupHelper.createLabel(testResult.getName() + " Test Case FAILED", ExtentColor.RED));
			extentTest.fail(testResult.getThrowable());
//			loginPage.logout();
		}
	}

	@Test(priority = 1, enabled = true, description = "Verify that the user is able to create site")
	public void TC_001_CreateSite() throws IOException, InterruptedException {

		String userName = LoginDetails.getJSONObject("TC_CreateSite_001").getString("userNameInput");
		String password = LoginDetails.getJSONObject("TC_CreateSite_001").getString("passwordInput");
		String companyCodeValue = LoginDetails.getJSONObject("TC_CreateSite_001").getString("companyCode");
		String siteNumberValue = LoginDetails.getJSONObject("TC_CreateSite_001").getString("siteNumber");
		String siteNameValue = LoginDetails.getJSONObject("TC_CreateSite_001").getString("siteName");
		String countryValue = LoginDetails.getJSONObject("TC_CreateSite_001").getString("countryValue");
		String timeZoneValue = LoginDetails.getJSONObject("TC_CreateSite_001").getString("timeZoneValue");
		String brandValue = LoginDetails.getJSONObject("TC_CreateSite_001").getString("brandValue");
		String groupValue = LoginDetails.getJSONObject("TC_CreateSite_001").getString("groupValue");
		String regionValue = LoginDetails.getJSONObject("TC_CreateSite_001").getString("regionValue");

		extentTest.info("Load the URL in Browser -" + url);
		driver.get(url);

		// Login with valid data
		loginPage.login(userName, companyCodeValue, password);
		extentTest.info("User logged in with valid credentials");
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User logged in with valid credentials", ExtentColor.GREEN));
		
		// open sites tab
		dashboardPage.openSitesTab();
		extentTest.info("User successfully opened sites tab");
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User successfully opened sites tab", ExtentColor.GREEN));
		
        // Enter site number and site name		
		sitesPage.enterSiteNumber_SiteName(siteNumberValue, siteNameValue);
		extentTest.info("User entered site number as  "+ siteNumberValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User entered site number as  "+ siteNumberValue, ExtentColor.GREEN));
		extentTest.info("User entered site name as "+ siteNameValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User entered site name as "+ siteNameValue, ExtentColor.GREEN));

		// Select country and time zone
		sitesPage.selectCountryTimeZone(countryValue, timeZoneValue);
		extentTest.info("User selected country as "+ countryValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User selected country as "+ countryValue, ExtentColor.GREEN));
		extentTest.info("User entered time zone as "+ timeZoneValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User entered time zone as "+ timeZoneValue, ExtentColor.GREEN));
		
		// Select Brand
		sitesPage.selectBrand(brandValue);
		extentTest.info("User selected brand as "+ brandValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User selected brand as "+ brandValue, ExtentColor.GREEN));
		
		// Enter group and region
		sitesPage.enterGroup_Region(groupValue, regionValue);
		extentTest.info("User entered group as "+groupValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User entered group as "+groupValue, ExtentColor.GREEN));
		extentTest.info("User entered region as "+regionValue);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("User entered region as "+regionValue, ExtentColor.GREEN));

		// Verify created site
		String actualSiteName = sitesPage.getVerifySiteLocator().getText();
		
		Assert.assertEquals(actualSiteName, siteNameValue);
		extentTest.info("Created site name is " + actualSiteName);
		extentTest.log(Status.PASS, MarkupHelper.createLabel("Created site name is " + actualSiteName, ExtentColor.GREEN));
	}

	/**
	 * This method is used to close the browser after class
	 *
	 */

	@AfterClass
	public void afterClass() {
//		driver.close();
	}

}
