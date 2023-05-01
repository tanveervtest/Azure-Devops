package com.efuelsystems.reusemethods;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWebDriver {

	/**
	 * This method is used get the Web Driver(BROWSER).
	 * 
	 * @param driverType is type of the browser
	 * @param ip         is IP of the HUB machine
	 * @param port       is Port No on which Node is registered to carry out the
	 *                   test execution
	 * @return returns webdriver
	 */

	public static synchronized WebDriver getRemoteWebDriver(String driverType, String ip, String port)
			throws MalformedURLException {
		WebDriver driver = null;
		DesiredCapabilities capabilities = null;

		String nodeURL = "http://" + ip + ":" + port + "/wd/hub";
		if (driverType != null) {
			if (driverType.equalsIgnoreCase("IE")) {
				
				System.setProperty("webdriver.ie.driver", "");
				capabilities = new DesiredCapabilities();
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
			
			} else if (driverType.equalsIgnoreCase("FireFox") || driverType.equalsIgnoreCase("Mozilla")) {

				System.setProperty("webdriver.gekco.driver", "./src/main/resources/drivers/geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();  
				options.addPreference("browser.cache.disk.enable", false);
				options.addPreference("browser.cache.memory.enable", false);
				options.addPreference("browser.cache.offline.enable", false);
				options.addPreference("network.http.use-cache", false);
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				
			} else if (driverType.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
				System.setProperty("webdriver.http.factory", "jdk-http-client");
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("test-type");
				capabilities = new DesiredCapabilities();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver();
				driver.manage().window().maximize();

			} else if (driverType.equalsIgnoreCase("safari")) {
				SafariOptions sOptions = new SafariOptions();
				sOptions.setCapability("browserstack.safari.driver", "15.5");
				sOptions.setCapability("browserstack.safari.enablePopups", true);
				sOptions.setUseTechnologyPreview(true);
				SafariOptions.fromCapabilities(sOptions);
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				driver.manage().window().maximize();
			}
		}

		return driver;
	}

	public static synchronized WebDriver getLocalWebDriver(String driverType) {
		WebDriver driver = null;
		// DesiredCapabilities capabilities = null;

		if (driverType != null) {
			if (driverType.equalsIgnoreCase("IE")) {
				driver = new InternetExplorerDriver();
			} else if (driverType.equalsIgnoreCase("FireFox")) {
				driver = new FirefoxDriver();
			}

		}

		return driver;
	}

	/**
	 * DRIVER CLOSING METHOD
	 * 
	 * @param driver is browser instance
	 */
	public static synchronized void closeWebDriver(WebDriver driver) {
		try {
			driver.quit();
		} catch (WebDriverException e) {
			e.printStackTrace();

		}
	}
}
