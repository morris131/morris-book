package com.morris.ch3;

import java.text.SimpleDateFormat;

public class DateUtil {
	
	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
	
	public static SimpleDateFormat getDateFormat(String pattern) {
		SimpleDateFormat sdf = threadLocal.get();
		if(null == sdf) {
			sdf = new SimpleDateFormat(pattern);
			threadLocal.set(sdf);
		}
		return sdf;
	}

}
