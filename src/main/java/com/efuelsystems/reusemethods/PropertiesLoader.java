package com.efuelsystems.reusemethods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

	private static Properties confProp = null;
	private static Properties appProp = null;
	private static Properties mailProp = null;
	private static Properties assertLabel = null;

	private PropertiesLoader() {
	}

	/**
	 * This method is singleton factory method
	 * 
	 * @return properties
	 */
	public static Properties getConfigPropertiesLoader() {

		if (confProp == null) {

			confProp = new Properties();
			try {
				confProp.load(new FileInputStream(Constants.CONFIG_PROPERTIES));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return confProp;
	}

	/**
	 * This method is singleton factory method
	 * 
	 * @return properties
	 */
	public static Properties getApplicationPropertiesLoader() {

		if (appProp == null) {

			appProp = new Properties();
			try {
				appProp.load(new FileInputStream(Constants.APP_PROPERTIES));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return appProp;
	}

	/**
	 * This method is singleton factory method
	 * 
	 * @return properties
	 */
	public static Properties getMailProperties() {

		if (mailProp == null) {

			mailProp = new Properties();
			try {
				mailProp.load(new FileInputStream(Constants.EMAIL_PROPERTIES));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mailProp;
	}
	
	public static Properties getAssertLabelProperties() {

		if (assertLabel == null) {
			System.out.println("My Test ******");
			assertLabel = new Properties();
			try {
				assertLabel.load(new FileInputStream(Constants.ASSERT_PROPERTIES));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return assertLabel;
	}
	
	
	
	
	/**
	 * This method is used to test stand alone
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Load the Properties
		PropertiesLoader.getMailProperties();

		// get the property value and print it out
		System.out.println(mailProp.getProperty("mail.smtp.host"));
		System.out.println(mailProp.getProperty("mail.smtp.socketFactory.port"));
		// System.out.println(confProp.getProperty("node.one.port"));
	}
}
