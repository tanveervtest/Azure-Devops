package com.efuelsystems.reusemethods;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class DriverUtils {

	public static void waitForElement(WebDriver driver) {
		
		 Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
			        .withTimeout(Duration.ofSeconds(60))
			        .pollingEvery(Duration.ofSeconds(5))
			        .ignoring(NoSuchElementException.class);
		 
	}
}