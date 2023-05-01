package com.efuelsystems.reusemethods;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;



public class ScreenShot {
	static Properties prop = null;
	String concatenate=".";

	/**
	 * This Method is used to Take the ScreenShot at Driver Level
	 * 
	 * @param driver
	 *            is a Browser Instance
	 * @param className
	 *            is Name of the Class in which takeScreenShot method is being
	 *            called
	 * @param methodName
	 *            is Name of the Method in which takeScreenShot method is being
	 *            called
	 */
	public static String takeScreenShot(WebDriver driver, String methodName) {
		
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		File src = edriver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./TestReport/screenshots/"+methodName + ".png");
		String path = null;
		try
		{
		 path ="."+ dst.getPath();
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}