package com.javaweb.utils;



public class NumberUtils {
	public static boolean isNumber(String data) {
		try {
			Long number = Long.parseLong(data); 
		
		}
		catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
}
