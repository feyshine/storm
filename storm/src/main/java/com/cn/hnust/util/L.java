package com.cn.hnust.util;

public class L {
	
	private static boolean isDebug = true;

	public static void i(String tag,String value){
		if (isDebug) {
			System.out.println(tag + "," +value);
		}
	}
}
