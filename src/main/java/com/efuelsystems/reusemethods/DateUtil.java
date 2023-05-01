package com.efuelsystems.reusemethods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * Stream Expiration Date used in Stream Create Method
	 * 
	 * @return returns time
	 */

	public static String date() {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // use today date.
		c.add(Calendar.HOUR, 6); // Adding 6 hours
		String time = sdf.format(c.getTime());
		System.out.println(time);
		return time;

	}

	/**
	 * Stream Renew Expiration Date
	 * 
	 * @return returns renewTime
	 */

	public static String renewdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // use today date.
		c.add(Calendar.HOUR, 8); // Adding 8 hours
		String renewTime = sdf.format(c.getTime());
		System.out.println(renewTime);
		return renewTime;

	}

}
