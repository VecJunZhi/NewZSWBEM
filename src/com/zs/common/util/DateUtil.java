package com.zs.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 
 * @author jiarui
 *
 */
public class DateUtil {
	private static final TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");

	/**
	 * 
	 * @param day
	 *            推移的天数
	 * @return date时间加天数的日期
	 */
	public static String addDate(String date, int day) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate(date, "yyyy-MM-dd"));
		calendar.add(GregorianCalendar.DAY_OF_MONTH, day);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
	
	/**
	 * @author Mengfw
	 * @param date 当前日期值 
	 * @param year 推移的年数
	 * @param formatStr 返回的格式
	 * @return date时间加天数的日期
	 */
	public static String addYear(String date, int year,String formatStr) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate(date, "yyyy-MM-dd"));
		calendar.add(GregorianCalendar.YEAR, year);
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(calendar.getTime());
	}

	public static String addDate2(String date, int day) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate(date, "yyyyMMdd"));
		calendar.add(GregorianCalendar.DAY_OF_MONTH, day);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(calendar.getTime());
	}
	
	public static String addWeek(String date, int week) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate(date, "yyyy-MM-dd"));
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, week);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
	
	public static String addMonth(String date, int month) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate(date, "yyyy-MM-dd"));
		calendar.add(GregorianCalendar.MONTH, month);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
	

	/**
	 * 日期转换成字符串 把长日期 yyyy-MM-dd HH:mm 转换成短日期格式 yyyy-MM-dd
	 */
	public static String dateToString(String StrDate) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		String dd = d.format(isToDate(StrDate));
		return dd;
	}
	/**
	 * modify by jixiaohang
	 * 日期转换成字符串 把长日期 yyyy-MM-dd HH:mm 转换成短日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToStringSecond(String strDate) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		String dd;
		try {
			date = d.parse(strDate);
			dd = d.format(date);
			return dd;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * @param dateNum(yyyymmdd)
	 * @return (yyyy-mm-dd)
	 */
	public static synchronized String formatDate(int dateNum) {
		String result = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = formatter.parse(String.valueOf(dateNum));
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			result = formatter1.format(date);
		} catch (Exception e) {
		}
		return result;
	}
	
	/**
	 * @return (yyyymmdd)
	 */
	public static synchronized int formatDate(Date date) {
		String result = "0";
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
			result = formatter1.format(date);
		} catch (Exception e) {
		}
		return Integer.parseInt(result);
	}

	/**
	 * @param msel
	 *            毫秒时间
	 * @return 毫秒时间 所对应的"yyyy-MM-dd HH:mm"格式的日期
	 */

	public static String formatTime(long msel) {
		Date date = new Date(msel);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		formatter.setTimeZone(timeZone);
		return formatter.format(date);
	}

	/**
	 * 得到系统当前的日期
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}



	/**
	 * 得到系统当前的年
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR);
	}

	/*
	 * public static void main(String[] args){
	 *  }
	 */


	/**
	 * 取month月后的日期
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static String getDateFromMonth(String date, int month) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate(date, "yyyyMMdd"));
		calendar.add(GregorianCalendar.MONTH, month);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(calendar.getTime());
	}

	/**
	 * 传入 yyymmdd返回yyyy-mm-dd
	 */
	public static String getDateStr(String date) {
		if (date.trim().length() < 8) {
			return "";
		} else {
			String dateStr = "";
			dateStr = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
					+ date.substring(6, 8);
			return dateStr;
		}

	}

	/**
	 * 取两日期之间的天数间隔
	 * 
	 * @param strDate1
	 *            格式:yyyymmdd
	 * @param strDate2
	 *            格式:yyyymmdd
	 * @return
	 */
	public static int getDistance(String strDate1, String strDate2) {
		int distance = 0;
		Date date1 = getDate(strDate1, "yyyyMMdd");
		Date date2 = getDate(strDate2, "yyyyMMdd");
		distance = (int) ((date2.getTime() - date1.getTime()) / 1000 / 60 / 60 / 24);
		return distance;
	}

	/**
	 * 返回传入日期月份的第一天
	 * 
	 * @return
	 */
	public static int getFirstDay(int date) {
		return date / 100 * 100 + 1;
	}

	/**
	 * 取得上一个星期的第一天 changes1.1-00 统计内容增加上周的平均值
	 * 
	 * @param date
	 *            指定日期。
	 * @return 上一个星期的第一天(YYYYMMDD)
	 */
	public static synchronized java.util.Date getFirstDayOfPrevWeek() {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(new Date());
		gc.setTime(getPrevWeek(gc.getTime()));
		gc.setTime(format.parse(getFirstDayOfWeek(Integer.parseInt(format
				.format(gc.getTime())))
				+ "", new ParsePosition(0)));
		return gc.getTime();
	}

	/**
	 * 取得指定日期的所处星期的第一天 changes1.1-00 统计内容增加上周的平均值
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处星期的第一天
	 */
	public static synchronized int getFirstDayOfWeek(int date) {
		/**
		 * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天
		 * 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天 6.如果date是星期五，则减5天
		 * 7.如果date是星期六，则减6天
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String strDate = date + "";
		Date day = format.parse(strDate, new ParsePosition(0));
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(day);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {

		case (Calendar.SUNDAY  ):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY  ):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY  ):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY  ):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY  ):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY  ):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY  ):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return Integer.parseInt(format.format(gc.getTime()));
	}

	/**
	 * 取得上一个星期的最后一天 changes1.1-00 统计内容增加上周的平均值
	 * 
	 * @param date
	 *            指定日期。
	 * @return 上一个星期的最后一天(YYYYMMDD)
	 */
	public static synchronized java.util.Date getLastDayOfPrevWeek() {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		Date d = getFirstDayOfPrevWeek();
		gc.setTime(d);
		gc.add(Calendar.DATE, 6);
		return gc.getTime();
	}

	// 得到这个星期的最后一天
	public static synchronized int getLastDayOfWeek(int date) {
		int firstDay = getFirstDayOfWeek(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String strDate = firstDay + "";
		Date day = format.parse(strDate, new ParsePosition(0));
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(day);
		gc.add(Calendar.DATE, 6);
		return Integer.parseInt(format.format(gc.getTime()));

	}

	/**
	 * 返回传入日期的月份的最后一天
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getLastlyDay(int day) {
		int lastDay = 0;
		int year = 0;
		int month = 0;

		year = day / 10000;
		month = (day / 100) % 100;
		day = day % 100;

		GregorianCalendar cca = new GregorianCalendar(year, month - 1, day);
		Calendar ca = cca.getInstance();
		lastDay = year * 10000 + month * 100
				+ cca.getActualMaximum(ca.DAY_OF_MONTH);
		return lastDay;
	}

	/**
	 * 获得上期时间,即传入本周获得是上一周以字符串分割的日期
	 * 
	 * @return
	 */
	public static String getLastPeriod(int benginDate, int endDate1) {
		// 先获得两个相差的日子
		int distanceDate = getDistance(String.valueOf(benginDate), String
				.valueOf(endDate1));
		int endDate2 = Integer.parseInt(addDate2(String.valueOf(endDate1),
				distanceDate));
		String temp = String.valueOf(endDate1) + ",";
		temp = temp + String.valueOf(endDate2);
		return temp;
	}

	/**
	 * 根据传入的日期进行月份的加减操作,增加月份传入正数的month,减少月份传入负数的month
	 * 
	 * @return
	 */
	public static int getMonthHandler(int date, int month) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Calendar c = Calendar.getInstance();// 返回Calendar对象的实例
			Date d = formatter.parse(date + "", new ParsePosition(0));// 可能返回null
			c.setTime(d);
			// c.add(Calendar.DATE, -2);//你要加减的天数
			c.add(Calendar.MONTH, month);// 你要加减的月数
			// c.add(Calendar.YEAR, -2);//你要加减的年数
			Date tempDate = c.getTime();
			String strDate = formatter.format(tempDate);
			return Integer.parseInt(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 取得指定日期的上一个星期 changes1.1-00 统计内容增加上周的平均值
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的上一个星期
	 */
	public static synchronized java.util.Date getPrevWeek(java.util.Date date) {
		/**
		 * 详细设计： 1.指定日期加7天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -7);
		return gc.getTime();
	}

	/**
	 * 得到系统的当前时间 YYYYMMDD
	 * 
	 * @return
	 */
	public static int getThisday() {
		StringBuffer sb = new StringBuffer(8);
		sb.append(getCurrentYear());
		int iMonth = getCurrentMonth();
		if (String.valueOf(iMonth).length() == 1)
			sb.append("0" + iMonth);
		else
			sb.append(iMonth);
		int iDay = getCurrentDay();
		if (String.valueOf(iDay).length() == 1)
			sb.append("0" + iDay);
		else
			sb.append(iDay);
		return new Integer(sb.toString()).intValue();
	}

	/**
	 * 返回昨天的时间,int型,yyyyMMdd
	 * 
	 * @return
	 */
	public static int getYesterday() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date tempDate = new Date();
		long tempLong = tempDate.getTime();
		tempLong = tempLong - (24 * 60 * 60 * 1000);
		tempDate.setTime(tempLong);
		String strDate = formatter.format(tempDate);
		return Integer.parseInt(strDate);
	}

	/**
	 * 计算一个月有多少天
	 * 
	 * @param year
	 * @return int
	 */
	public static int isMontyToDay(int year, int month) {
		int maxDate;
		// 计算机0-11月
		month = month - 1;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		maxDate = cal.getActualMaximum(Calendar.DATE);
		return maxDate;
	}

	// 日期转换
	public static Date isToDate(String StrDate) {
		Date date = new Date();
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = d.parse(StrDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block

		}
		return date;
	}
	/**
	 * 得到现在时间 yyyy-MM-dd
	 * YD
	 */
	public static String getNowDate() {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		String dd = d.format(new Date());
		return dd;
	}
	/**
	 * 得到现在时间 yyyy-MM-dd HH:mm:ss
	 * YD
	 */
	public static String getNowDateSS() {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dd = d.format(new Date());
		return dd;
	}
	   /**
     * 得到现在时间 yyyy-MM-dd HH:mm:ss
     * YD
     */
    public static String getDateyyyyMMdd() {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String dd = d.format(new Date());
        return dd;
    }
	/**
	 * 得到现在时间 yyyy-MM-dd
	 * YD
	 */
	public static String getNowDateSS(Date date,String fmt) {
		SimpleDateFormat d = new SimpleDateFormat(fmt);
		String dd = d.format(date);
		return dd;
	}
	/**
	 * 得到现在时间 yyyy-MM-dd
	 * YD
	 */
	public static String getNowDateInt() {
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		String dd = d.format(new Date());
		return dd;
	}
	/**
	 * 得到现在时间 yyyy-MM-dd
	 * YD
	 */
	public static String getDateStr(Date date) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String dd = d.format(date);
		return dd;
	}

	/**
	 * 将200812121212 转为 YYYY-MM-DD HH:MM
	 * @return
	 */
	public static String changeTime(String str){
		if(str.length()>= 12){
			String changeStr = str.substring(0, 4) + "-" + str.substring(4,6) + "-" + str.substring(6,8)+ " " + 
			str.substring(8,10) + ":" + str.substring(10,12);
			return changeStr;
		}else{
			return str;
		}
	}
	
	/**
	 * mysql db插入dateTime类型转换
	 * @return java.sql.Timestamp
	 */
	public static Timestamp setSQLDateTime(){
		Date date = new Date();
		return new Timestamp(date.getTime());
	}
	
	/**
	 * mysql db插入dateTime类型转换
	 * @return java.sql.Timestamp
	 */
	public static Timestamp setSQLDateTime(Date date){
		if(date == null)
			date = new Date();
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 得到系统当前时间
	 * @param format 指定的时间显示格式，比如:“yyyy-MM-dd HH:mm”
	 * @return 按指定格式显示的当前日期
	 */
	public static String getCurrentTime(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date());
	}

	public static String format(String format, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * 得到系统当前周七天的日期（以周日开始）
	 * @param format 指定的时间显示格式，比如:“yyyy-MM-dd HH:mm”
	 * @return 按指定格式显示的当前周七天的字符串数组
	 */
	public static String[] getCurrentWeek(String format) {
		String[] result = { "", "", "", "", "", "", "", "" };

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(new Date());
		for (int i = 1; i < 8; i++) {
			cal.set(GregorianCalendar.DAY_OF_WEEK, i);
			result[i - 1] = dateFormat.format(cal.getTime());
		}

		return result;
	}
	/**
	 * 得到系统当前日期的第一天
	 */
	public static String getFirstDayOfWeek(String formate) {
		  SimpleDateFormat df = new SimpleDateFormat(formate);
		  Calendar c = Calendar.getInstance(Locale.CHINA);
		  c.setFirstDayOfWeek(Calendar.MONDAY);
		  c.setTimeInMillis(System.currentTimeMillis());
		  df.format(c.getTime());
		  c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		 String firstDay= df.format(c.getTime());
		 System.out.println(firstDay);
		 return firstDay;
	}
    /*
* 取本周7天的第一天（周一的日期）
*/
	public static String getNowWeekBegin() {
	int mondayPlus;
	Calendar cd = Calendar.getInstance();
	//获得今天是一周的第几天，星期日是第一天，星期二是第二天......
	int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
	if (dayOfWeek == 1) {
	mondayPlus = 0;
	} else {
	mondayPlus = 1 - dayOfWeek;
	}
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus);
	Date monday = currentDate.getTime();
	
	
	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);
	
	return preMonday + " 00:00:00";
	
	
	}

	/**
	 *  得到系统当前月
	 * @return 当前月
	 */
	public static int getCurrentMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		String strMonth = dateFormat.format(cal.getTime());

		return Integer.valueOf(strMonth).intValue();
	}

	/**
	 * 将一个字符串的日期描述转换为java.util.Date对象
	 * @param strDate 字符串的日期描述
	 * @param format 字符串的日期格式，比如:“yyyy-MM-dd HH:mm”
	 * @return 字符串转换的日期对象java.util.Date
	 */
	public static Date getDate(String strDate, String format) {
		if (strDate == null || strDate.trim().equals("")) {
			strDate = getCurrentTime(format);
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		Date date;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			date = null;
		}

		return date;
	}

	/**
	 * 从字符串日期描述中得到月份
	 * @param strDate 字符串日期描述
	 * @param format 字符串的日期格式，比如:“yyyy-MM-dd HH:mm”
	 * @return 字符串中的月份
	 */
	public static int getMonth(String strDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(getDate(strDate, format));
		String strMonth = dateFormat.format(cal.getTime());

		return Integer.valueOf(strMonth).intValue();
	}

	/**
	 * 从字符串日期描述中得到年份
	 * @param strDate 字符串日期描述
	 * @param format 字符串的日期格式，比如:“yyyy-MM-dd HH:mm”
	 * @return 字符串日期中的年份
	 */
	public static int getYear(String strDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(getDate(strDate, format));
		String strMonth = dateFormat.format(cal.getTime());

		return Integer.valueOf(strMonth).intValue();
	}

	/**
	 * 判断指定时间是否在某一个时间区域内
	 * @param strDate 要判断的日期字符串
	 * @param strBegin 时间区域开始的日期字符串
	 * @param strEnd 时间区域截至的日期字符串
	 * @param format 字符串的日期格式，比如:“yyyy-MM-dd HH:mm”
	 * @return 如果指定时间在时间区域内，返回true
	 */
	public static boolean isBetween(
		String strDate,
		String strBegin,
		String strEnd,
		String format) {
		try {
			Date date = getDate(strDate, format);
			Date dBegin = getDate(strBegin, format);
			Date dEnd = getDate(strEnd, format);

			long lDate = date.getTime();
			long lBegin = dBegin.getTime();
			long lEnd = dEnd.getTime();
			if (lBegin <= lDate && lDate <= lEnd) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 得到当前时间之后的指定天数的日期
	 * @param day 指定的天数
	 * @param format 字符串的日期格式，比如:“yyyy-MM-dd HH:mm”　
	 * @return String 当前时间之后的指定天数的日期
	 */
	public static String addDay(int day, String format) {
		String dateBack = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, day);
		dateBack = dateFormat.format(date.getTime());
		return dateBack;
	}
	/**
	 * 得到当前时间加上指定分钟数的的日期
	 * @param second
	 * @param format
	 * @param sdate
	 * @return string
	 */
	public static String addMinute(int minute, String format,String sdate) {
		String dateBack = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date;
		try {
			date = dateFormat.parse(sdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MINUTE, minute);
			dateBack = dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateBack;
	}
	/**
	 * 得到当前时间加上指定秒数的的日期
	 * @param second
	 * @param format
	 * @param sdate
	 * @return string
	 */
	public static String addSecond(int second, String format,String sdate) {
		String dateBack = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date;
		try {
			date = dateFormat.parse(sdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.SECOND, second);
			dateBack = dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateBack;
	}
	/**
	* 计算两个日期间的天数
	* 
	* @param fromDate
	* 起始日期
	* @param toDate
	* 结束日期
	* @return
	* @throws ParseException
	*/
	public static int dateDiff(String fromDate, String toDate)
	throws ParseException {
	int days = 0;
	 
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date from = df.parse(fromDate);
	Date to = df.parse(toDate);
	days = (int) Math.abs((to.getTime() - from.getTime())
	 / (24 * 60 * 60 * 1000)) + 1;
	 System.out.println(days);
	 return days;
	}
	public static void main(String[] args) {
	   System.out.println( addDate(getDateyyyyMMdd(),-7));
	}
}