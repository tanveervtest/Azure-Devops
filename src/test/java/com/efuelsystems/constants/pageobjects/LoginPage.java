package com.efuelsystems.constants.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.efuelsystems.tests.BaseDriverUtil;

public class LoginPage extends BasePO {

	// This method is used for finding webElement of User Name
	@FindBy(xpath = "//input[@name='username']")
	private WebElement userNameLocator;

	// This method is used for finding webElement of Company Code
	@FindBy(xpath = "//input[@name='companyCode']")
	private WebElement companyCodeLocator;

	// This method is used for finding webElement of Password
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordLocator;

	// locator for Login button
	@FindBy(xpath = "//button[@id='LoginButton']")
	private WebElement loginButtonLocator;


	public WebElement getUserNameLocator() {
		highLighterMethod(userNameLocator);
		return userNameLocator;
	}

	public WebElement getpasswordLocator() {
		highLighterMethod(passwordLocator);
		return passwordLocator;
	}
	
	public WebElement getCompanyCodeLocator() {
		highLighterMethod(companyCodeLocator);
		return companyCodeLocator;
	}

	public WebElement getloginButtonLocator() {
		highLighterMethod(loginButtonLocator);
		return loginButtonLocator;
	}

	/**
	 * This method is used for performing scrolling operation
	 * 
	 * @param driver
	 */

	public void scrollup(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
	}

	public void login(String userNameData, String companyCodeData, String passwordData) throws InterruptedException {
		highLighterMethod(getUserNameLocator());
		getUserNameLocator().sendKeys(userNameData);
		Thread.sleep(2000);
		highLighterMethod(getCompanyCodeLocator());
		getCompanyCodeLocator().sendKeys(companyCodeData);
		Thread.sleep(2000);
		highLighterMethod(getpasswordLocator());
		getpasswordLocator().sendKeys(passwordData);
		Thread.sleep(2000);
		waitForElementToBeClickable(getloginButtonLocator());
		highLighterMethod(getloginButtonLocator());
		getloginButtonLocator().click();
		Thread.sleep(12000);
		/*
		 * BaseDriverUtil s = new BaseDriverUtil(); WebDriverWait firstResult = new
		 * WebDriverWait(s.driver, Duration.ofSeconds(80));
		 * firstResult.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//a[contains(@id,'btnLogout')]")));
		 */
	}

}