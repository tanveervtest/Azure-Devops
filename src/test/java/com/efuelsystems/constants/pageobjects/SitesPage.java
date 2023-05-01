package com.efuelsystems.constants.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.efuelsystems.tests.BaseDriverUtil;

public class SitesPage extends BasePO {

	
	@FindBy(xpath = "//input[@name='StoreNumber']")
	private WebElement siteNumber;

	@FindBy(xpath = "//input[@name='SiteName']")
	private WebElement sitesName;
	
	@FindBy(xpath = "//input[@placeholder='Site Name']//following::div[37]")
	private WebElement country;
	
	@FindBy(id = "react-select-7-placeholder")
	private WebElement state;
	
	@FindBy(id = "react-select-4-placeholder")
	private WebElement brand;
	
	@FindBy(id = "react-select-4-placeholder")
	private WebElement siteStatus;
	
	@FindBy(xpath = "//input[@placeholder='Group']")
	private WebElement group;
	
	@FindBy(xpath = "//input[@placeholder='Region']")
	private WebElement region;
	
	@FindBy(xpath = "//input[@placeholder='Site Name']//following::div[74]")
	private WebElement timeZone;
	
	@FindBy(xpath = "//input[@placeholder='Site Name']//following::div[6]")
	private WebElement selectBrand;
	
	@FindBy(xpath = "//button[@class='btn-standard btn btn-success btn-sm']")
	private WebElement saveButton;
	
	@FindBy(xpath = "(//div[@class='companysite-select__single-value css-1dimb5e-singleValue'])[1]")
	private WebElement verifySite;
	
	public WebElement getVerifySiteLocator() {
		return verifySite;
	}

	public void enterSiteNumber_SiteName(String siteNumberData, String siteNameData) throws InterruptedException {
		Thread.sleep(2000);
		highLighterMethod(siteNumber);
		waitForElementToBeClickable(siteNumber);
		siteNumber.sendKeys(siteNumberData);
//		Thread.sleep(2000);
		waitForElementToBeClickable(sitesName);
		highLighterMethod(sitesName);
		sitesName.sendKeys(siteNameData);
	}
	
	public void selectCountryTimeZone(String countryValue, String timeZoneValue) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeClickable(country);
		highLighterMethod(country);
		country.click();
		Thread.sleep(1000);
		BaseDriverUtil.driver.findElement(By.xpath("//div[normalize-space()='"+countryValue+"']")).click();
//		Thread.sleep(2000);
		waitForElementToBeClickable(timeZone);
		highLighterMethod(timeZone);
		timeZone.click();
		BaseDriverUtil.driver.findElement(By.xpath("//div[normalize-space()='"+timeZoneValue+"']")).click();
	}
	
	public void selectBrand(String brandValue) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeClickable(selectBrand);
		highLighterMethod(selectBrand);
		selectBrand.click();
		Thread.sleep(1000);
		WebElement brandValueElement = BaseDriverUtil.driver.findElement(By.xpath("//div[normalize-space()='"+brandValue+"']"));
		brandValueElement.click();
//		Thread.sleep(2000);
	}
	
	public void enterGroup_Region(String groupData, String regionData) throws InterruptedException {
		Thread.sleep(2000);
		highLighterMethod(group);
		waitForElementToBeClickable(group);
		group.sendKeys(groupData);
//		Thread.sleep(2000);
		waitForElementToBeClickable(region);
		highLighterMethod(region);
		region.sendKeys(regionData);
		saveButton.click();
		waitForVisibility(getVerifySiteLocator());
		highLighterMethod(getVerifySiteLocator());
		System.out.println(getVerifySiteLocator());
		System.out.println("created site name "+ getVerifySiteLocator().getText());
	}

}