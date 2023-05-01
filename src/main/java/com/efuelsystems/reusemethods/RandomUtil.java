package com.efuelsystems.reusemethods;

public class RandomUtil {

	/**
	 * This method will return Random String
	 * 
	 * @return returns string of random 15 digit data(Dynamic data)
	 */
	public static String getRandomString() {
		String str = String.valueOf(Math.random());
		str = new String(str.substring(str.indexOf(".") + 1, str.length()));
		return str;
	}

	/**
	 * this method is to test the random string generation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(" String is ....... " + RandomUtil.getRandomString());
	}

}
