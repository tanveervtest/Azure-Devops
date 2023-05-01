package com.efuelsystems.constants.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.efuelsystems.tests.BaseDriverUtil;

public class ContactsPage extends BasePO {

	@FindBy(name = "Username")
	private WebElement userName;

	@FindBy(xpath = "//div[contains(text(),'Standard Password')]")
	private WebElement passwordType;
	
	@FindBy(name = "Firstname")
	private WebElement firstName;
	
	@FindBy(name = "Lastname")
	private WebElement lastName;
	
	@FindBy(name = "Email")
	private WebElement emailAddress;
	
	@FindBy(id = "react-select-3-input")
	private WebElement country;
	
	@FindBy(id = "react-select-5-input")
	private WebElement timeZone;
	
	@FindBy(id = "react-select-8-input")
	private WebElement contactType;
	
	@FindBy(xpath = "//input[@placeholder='Mobile Number']")
	private WebElement mobileNumber;
	
	@FindBy(xpath = "//button[@class='btn-standard btn btn-success btn-sm']")
	private WebElement saveButton;
	
	@FindBy(xpath = "//div[@class='contact-select__single-value css-1dimb5e-singleValue']")
	private WebElement verifyContact;
	
	
	
	public WebElement getVerifyContactLocator() {
		return verifyContact;
	}
	

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
		
	public void enter_FirstName_LastName_EMailAddress(String firstNameValue, String lastNameValue, String emailAddressValue) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeClickable(firstName);
		highLighterMethod(firstName);
		firstName.sendKeys(firstNameValue);
//		Thread.sleep(2000);
		waitForElementToBeClickable(lastName);
		highLighterMethod(lastName);
		lastName.sendKeys(lastNameValue);
		waitForElementToBeClickable(emailAddress);
		highLighterMethod(emailAddress);
		emailAddress.sendKeys(emailAddressValue);
	}
	
	public void selectCountryTimeZone(String countryValue, String timeZoneValue) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeClickable(country);
		highLighterMethod(country);
		country.click();
		BaseDriverUtil.driver.findElement(By.xpath("//div[normalize-space()='"+countryValue+"']")).click();
//		Thread.sleep(2000);
		waitForElementToBeClickable(timeZone);
		highLighterMethod(timeZone);
		timeZone.click();
		BaseDriverUtil.driver.findElement(By.xpath("//div[normalize-space()='"+timeZoneValue+"']")).click();
	}
	
	public void selectContactType_MobileNumber(String contactTypeValue, String mobileNumberValue) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeClickable(contactType);
		highLighterMethod(contactType);
		contactType.click();
		BaseDriverUtil.driver.findElement(By.xpath("//div[normalize-space()='"+contactTypeValue+"']")).click();
//		Thread.sleep(2000);
		waitForElementToBeClickable(mobileNumber);
		highLighterMethod(mobileNumber);
		mobileNumber.sendKeys(mobileNumberValue);
		saveButton.click();
		waitForVisibility(verifyContact);
		highLighterMethod(verifyContact);
		System.out.println(verifyContact);
		System.out.println("created contact name "+ verifyContact.getText());
	}

}