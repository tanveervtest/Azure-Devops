package com.efuelsystems.constants.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.efuelsystems.tests.BaseDriverUtil;

public class UserPage extends BasePO {

	
	@FindBy(name = "Username")
	private WebElement userName;

	@FindBy(name = "Email")
	private WebElement emailAddress;
	
	@FindBy(xpath = "//div[contains(text(),'Standard Password')]")
	private WebElement passwordType;
	
	@FindBy(name = "Firstname")
	private WebElement firstName;
	
	@FindBy(name = "Lastname")
	private WebElement lastName;
	
	@FindBy(id = "//div[contains(text(),'Select Country')]")
	private WebElement country;
	
	@FindBy(id = "react-select-33-placeholder")
	private WebElement timeZone;
	
	@FindBy(id = "react-select-17-placeholder")
	private WebElement securityRole;
	
	@FindBy(xpath = "//button[@class='btn-standard btn btn-success btn-sm']")
	private WebElement saveButton;

	public void enterUserName_EmailAddress(String userNameValue, String emailAddressValue) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeClickable(userName);
		highLighterMethod(userName);
		userName.sendKeys(userNameValue);
//		Thread.sleep(2000);
		waitForElementToBeClickable(emailAddress);
		highLighterMethod(emailAddress);
		emailAddress.sendKeys(emailAddressValue);
	}
		
	public void enterFirstName_LastName(String firstNameValue, String lastNameValue) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeClickable(firstName);
		highLighterMethod(firstName);
		firstName.sendKeys(firstNameValue);
//		Thread.sleep(2000);
		waitForElementToBeClickable(lastName);
		highLighterMethod(lastName);
		lastName.sendKeys(lastNameValue);
	}
	
	public void selectCountryTimeZone(String countryValue, String timeZoneValue) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeClickable(country);
		highLighterMethod(country);
		country.click();
		BaseDriverUtil.driver.findElement(By.xpath("//div[normalize-space()= '"+countryValue+"']"));
//		Thread.sleep(2000);
		waitForElementToBeClickable(timeZone);
		highLighterMethod(timeZone);
		BaseDriverUtil.driver.findElement(By.xpath("//div[normalize-space()= '"+timeZoneValue+"']"));
		timeZone.click();
	}

}