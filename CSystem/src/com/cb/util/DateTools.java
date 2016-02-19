package com.cb.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author chen
 *
 */
public class DateTools {

	/**
	 * 将日期格式化为yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static Date getDateShort(Date date)
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=simpleDateFormat.format(date);
		ParsePosition pos = new ParsePosition(8);
		Date returnDate=simpleDateFormat.parse(dateString, pos);
		return returnDate;
	}
	
	
//	 public static Date getNowDateShort() {
//		  Date currentTime = new Date();
//		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		  String dateString = formatter.format(currentTime);
//		  ParsePosition pos = new ParsePosition(8);
//		  Date currentTime_2 = formatter.parse(dateString, pos);
//		  return currentTime_2;
//		 }
}
