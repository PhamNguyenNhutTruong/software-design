package com.courses.utils.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Random;



public class RandomUtils {
	public static String randomId() {
		Random rand = new Random(); // instance of random class
		// generate random values from 0-999999999
		int int_random = rand.nextInt(999999999);
		return Integer.toString(int_random);
	}
	
	public static Date convertStringToDate(String str) throws ParseException {
		Date date = null;
		if(str.contains("-")) {
//		Chỗ này
			System.out.println("Dau - : " +str);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(str);
//		Với cả chỗ này 
//			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");
//			String temp = formatDate(date);
//			date = formatter2.parse(temp);
		} else if (str.contains("/")) {
//		Với chỗ này đang chế cháo. Không ổn
			System.out.println("Dau / : " +str);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			date = formatter.parse(str);
		}
		return date;
		
//		Date date = DateUtils.parseDate(str, 
//				  new String[] { "dd-MM-yyyy HH:mm:ss", "dd/MM/yyyy hh:mm:ss"});
//		return date;
	}
	
	public static String formatDate(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(date);
		return strDate;
	}
}
