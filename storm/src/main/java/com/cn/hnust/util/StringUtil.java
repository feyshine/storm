package com.cn.hnust.util;

public class StringUtil {

	public static boolean isEmpty(String value){
		boolean flag ;
		if (value == null|| value.equals("null")||value.length() == 0) {
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}

}
