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

public class DashboardPage extends BasePO {

	// This method is used for finding webElement of Admin tab
	@FindBy(xpath = "//p[normalize-space()='Admin']")
	private WebElement adminLocator;

	// This method is used for finding webElement of Sites Tab
	@FindBy(xpath = "//span[normalize-space()='Sites']")
	private WebElement sitesLocator;
	
	@FindBy(xpath = "//span[normalize-space()='Users']")
	private WebElement usersLocator;
	
	@FindBy(xpath = "//span[normalize-space()='Contacts']")
	private WebElement contactsLocator;

	// This method is used for finding webElement of Add button
	@FindBy(xpath = "//button[@title='Add']")
	private WebElement addButton;

	public WebElement getAdminLocator() {
		highLighterMethod(adminLocator);
		return adminLocator;
	}

	public WebElement getSitesLocator() {
		highLighterMethod(sitesLocator);
		return sitesLocator;
	}
	
	public WebElement getUsersLocator() {
		highLighterMethod(usersLocator);
		return usersLocator;
	}
	
	public WebElement getContactsLocator() {
		highLighterMethod(contactsLocator);
		return contactsLocator;
	}
	
	public WebElement getAddButtonLocator() {
		highLighterMethod(addButton);
		return addButton;
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

	public void openSitesTab() throws InterruptedException {
		Thread.sleep(12000);
		waitForElementToBeClickable(getAdminLocator());
		highLighterMethod(getAdminLocator());
		getAdminLocator().click();
//		Thread.sleep(2000);
		waitForElementToBeClickable(getSitesLocator());
		highLighterMethod(getSitesLocator());
		getSitesLocator().click();
		Thread.sleep(12000);
		waitForElementToBeClickable(getAddButtonLocator());
		highLighterMethod(getAddButtonLocator());
		getAddButtonLocator().click();
//		Thread.sleep(2000);
		Thread.sleep(12000);
		/*
		 * BaseDriverUtil s = new BaseDriverUtil(); WebDriverWait firstResult = new
		 * WebDriverWait(s.driver, Duration.ofSeconds(80));
		 * firstResult.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//a[contains(@id,'btnLogout')]")));
		 */
	}
	
	public void openUsersTab() throws InterruptedException {
		Thread.sleep(12000);
		waitForElementToBeClickable(getAdminLocator());
		highLighterMethod(getAdminLocator());
		getAdminLocator().click();
//		Thread.sleep(2000);
		waitForElementToBeClickable(getUsersLocator());
		highLighterMethod(getUsersLocator());
		getUsersLocator().click();
		Thread.sleep(12000);
		waitForElementToBeClickable(getAddButtonLocator());
		highLighterMethod(getAddButtonLocator());
		getAddButtonLocator().click();
//		Thread.sleep(2000);
		Thread.sleep(12000);
	}
	
	public void openContactsTab() throws InterruptedException {
		Thread.sleep(12000);
		waitForElementToBeClickable(getAdminLocator());
		highLighterMethod(getAdminLocator());
		getAdminLocator().click();
//		Thread.sleep(2000);
		waitForElementToBeClickable(getContactsLocator());
		highLighterMethod(getContactsLocator());
		getContactsLocator().click();
		Thread.sleep(12000);
		waitForElementToBeClickable(getAddButtonLocator());
		highLighterMethod(getAddButtonLocator());
		getAddButtonLocator().click();
//		Thread.sleep(2000);
		Thread.sleep(12000);
	}

}