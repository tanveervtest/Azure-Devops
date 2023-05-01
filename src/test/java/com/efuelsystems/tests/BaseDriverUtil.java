package com.efuelsystems.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.efuelsystems.reusemethods.ScreenShot;
import com.efuelsystems.reusemethods.VideoRecording;

public class BaseDriverUtil {

	public VideoRecording videoRecording;
	static ExtentReports extentReports;
	static ExtentSparkReporter extentSparkReporter;
	public static WebDriver driver;
	public ScreenShot screenShot;
	public String url = "";
	public static ExtentTest extentTest;
	public static Properties prop;
	public String env;

	/**
	 * In This method we are deleting previous run screen shots and also we are
	 * starting video recording and also we are Initializing the extent reports
	 * 
	 * @throws Exception
	 */
	@BeforeSuite
	public void beforeSuite() throws Exception {
		videoRecording = new VideoRecording();
		videoRecording.startRecording();
		ExtentReports();
	}

	public synchronized static void ExtentReports() throws IOException {
		prop = new Properties();
		File files = new File(System.getProperty("user.dir") + "/src/test/java/com/efuelsystems/constants/config.properties");
		FileInputStream fis = new FileInputStream(files);
		prop.load(fis);
		try {
			if (extentTest == null) {
				String filepath = "./TestReport";
				File file = new File(filepath);
				deleteScreenshotsFolder(file);
				file.delete();

				extentReports = new ExtentReports();
				extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "./TestReport/Efuel_Systems_ExecutionReport.html");
				extentSparkReporter.config().setTheme(Theme.DARK);
				extentReports.setSystemInfo("Operating System: ", System.getProperty("os.name"));
				extentReports.setSystemInfo("Java Version: ", System.getProperty("java.version"));
				extentReports.attachReporter(extentSparkReporter);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * In This method we are stopping the video recording. Recorded video will be
	 * stored in your local computer under Videos section
	 */
	@AfterSuite()
	public void afterSuite() {
		if (url.equals("https://uat-portal.efuelsystems.com")) {
			extentReports.setSystemInfo("UAT Test Environment: ", prop.getProperty("UAT_Test_Environment_Url"));
			env = "UAT TEST";
			} 
			/*
			 * else if (url.equals("https://pulse-dev.tatsh.cloud/Login2.aspx")) {
			 * extentReports.setSystemInfo("Dev Environment",
			 * prop.getProperty("Dev_Environment_Url")); env = "dev"; } else if
			 * (url.equals("https://test-pulse-it.tatsh.cloud/")) {
			 * extentReports.setSystemInfo("Italy Test Environment",
			 * prop.getProperty("Italy_Test_Environment_Url")); env = "Italy-test"; }
			 */
		
		extentSparkReporter.config().setReportName("EFUEL SYSTEMS Automation Report = " + env);
		String browserName = "Chrome";
		extentReports.setSystemInfo("Browser", browserName);

		try {
			videoRecording.stopRecording();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReports.flush();
	}

	/**
	 * This method is used to delete the folder in our project
	 * 
	 * @param file
	 */
	public static void deleteScreenshotsFolder(File file) {
		try {
			for (File subfile : file.listFiles()) {

				if (subfile.isDirectory()) {
					deleteScreenshotsFolder(subfile);
				}
				subfile.delete();
			}
		} catch (Exception e) {

		}
	}

	public static String convertStringArrayToString(String[] strArr, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (String str : strArr)
			sb.append(str).append(delimiter);
		return sb.substring(0, sb.length() - 1);
	}
}
