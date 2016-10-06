package com.zs.busi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateFormat {
	/**
	 * 获得当前日期，格�?yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	static Log log= LogFactory.getLog(DateFormat.class);
	public static String getCurrentDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String followDate=df.format(new Date());//必须带有时分�?，保�?
		log.info(followDate);
		return followDate;
	}
	public static String getCurrentDateYmd(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String followDate=df.format(new Date());//必须带有时分�?，保�?
		log.info(followDate);
		return followDate;
	} 
	
	public static String getCurrentDateYmdHms(){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		String followDate=df.format(new Date());//必须带有时分�?，保�?
		log.info(followDate);
		return followDate;
	}
public static void main(String[] args) {
	getCurrentDateYmd();
}
}
