package com.zs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	private static final String WUZHI_URL_COMMON="https://wuzhi.me/u/";
	
	public static String getUrl(long id){
		return WUZHI_URL_COMMON+id;
	}

	/**
	 * eg:将【2011年06月04日   星期六】转为Date类型
	 * @param date
	 * @return
	 */
	public static Date getDate(String date){
		Date reuslt=null;
		int index=date.lastIndexOf("日");
		String date_str=date.substring(0, index);
		date_str=date_str.replaceAll("[年月]", "-");
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			reuslt=format.parse(date_str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static int getStarCount(String value){
		String count_str=value.replaceAll("[× ]", "");
		return Integer.valueOf(count_str);
	}
	
}
