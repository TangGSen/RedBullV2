package com.sen.redbull.tools;

import android.annotation.SuppressLint;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DataTool {
	public static String timeShow(String time) {
		String str = "";
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str1 = formatter.format(curDate);

		try {
			Date d1 = formatter.parse(time);
			Date d2 = formatter.parse(str1);
			long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别

			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
					* (1000 * 60 * 60))
					/ (1000 * 60);

			String day = days + "";
			String hour = hours + "";
			String minute = minutes + "";
			if ("0".equals(day) && "0".equals(hour) && "0".equals(minute)) {
				str = "刚刚";
			} else if (!"0".equals(day)) {
				str = day + "天前";
			} else if ("0".equals(day) && !"0".equals(hour)) {
				str = hour + "小时前";
			} else if ("0".equals(day) && "0".equals(hour)
					&& !"0".equals(minute)) {
				str = minute + "分钟前";
			}

			// System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
	
	
	public static Date stringToDate(String dateString) {
		ParsePosition position = new ParsePosition(0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		Date dateValue = simpleDateFormat.parse(dateString, position);
		return dateValue;
	}
	
	
	 public static String secToTime(int time) {  
	        String timeStr = null;  
	        int hour = 0;  
	        int minute = 0;  
	        int second = 0;  
	        if (time <= 0)  
	            return "00:00";  
	        else {  
	            minute = time / 60;  
	            if (minute < 60) {  
	                second = time % 60;  
	                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
	            } else {  
	                hour = minute / 60;  
	                if (hour > 99)  
	                    return "99:59:59";  
	                minute = minute % 60;  
	                second = time - hour * 3600 - minute * 60;  
	                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
	            }  
	        }  
	        return timeStr;  
	    }
	 
	 public static String unitFormat(int i) {  
	        String retStr = null;  
	        if (i >= 0 && i < 10)  
	            retStr = "0" + Integer.toString(i);  
	        else  
	            retStr = "" + i;  
	        return retStr;  
	    }  
	 
	 public static String longToTime(long time) {  
		 
		 Date d = new Date(time);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 sdf.format(d);
		 return  sdf.format(d);  
	 }  
	 
	 
	
}