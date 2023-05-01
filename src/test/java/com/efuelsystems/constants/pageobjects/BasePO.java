package com.efuelsystems.constants.pageobjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.efuelsystems.tests.BaseDriverUtil;

public class BasePO {
	
	public static void highLighterMethod(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) BaseDriverUtil.driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	
	public static void scrollMethod(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) BaseDriverUtil.driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
		
	public static void waitFor(WebDriver driver, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.elementToBeClickable(element));	
	}
	
	public static void waitForVisibility(WebDriver driver, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.visibilityOf(element));	
	}
	
	public static void waitForElementToBeClickable(WebElement element) {
		new WebDriverWait(BaseDriverUtil.driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForVisibility(WebElement element) {
		new WebDriverWait(BaseDriverUtil.driver, Duration.ofSeconds(60))
				.until(ExpectedConditions.visibilityOf(element));
	}
}