package com.javaweb.utils;

public class StringUtils {
	public static boolean checkString(String data) {
		if(data != null && !data.isEmpty()) {
			return true;
		}
		else return false;
	}
}
