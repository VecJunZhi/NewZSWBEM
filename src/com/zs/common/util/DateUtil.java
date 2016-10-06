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
	 *            ���Ƶ�����
	 * @return dateʱ�������������
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
	 * @param date ��ǰ����ֵ 
	 * @param year ���Ƶ�����
	 * @param formatStr ���صĸ�ʽ
	 * @return dateʱ�������������
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
	 * ����ת�����ַ��� �ѳ����� yyyy-MM-dd HH:mm ת���ɶ����ڸ�ʽ yyyy-MM-dd
	 */
	public static String dateToString(String StrDate) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		String dd = d.format(isToDate(StrDate));
		return dd;
	}
	/**
	 * modify by jixiaohang
	 * ����ת�����ַ��� �ѳ����� yyyy-MM-dd HH:mm ת���ɶ����ڸ�ʽ yyyy-MM-dd HH:mm:ss
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
	 *            ����ʱ��
	 * @return ����ʱ�� ����Ӧ��"yyyy-MM-dd HH:mm"��ʽ������
	 */

	public static String formatTime(long msel) {
		Date date = new Date(msel);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		formatter.setTimeZone(timeZone);
		return formatter.format(date);
	}

	/**
	 * �õ�ϵͳ��ǰ������
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}



	/**
	 * �õ�ϵͳ��ǰ����
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
	 * ȡmonth�º������
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
	 * ���� yyymmdd����yyyy-mm-dd
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
	 * ȡ������֮����������
	 * 
	 * @param strDate1
	 *            ��ʽ:yyyymmdd
	 * @param strDate2
	 *            ��ʽ:yyyymmdd
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
	 * ���ش��������·ݵĵ�һ��
	 * 
	 * @return
	 */
	public static int getFirstDay(int date) {
		return date / 100 * 100 + 1;
	}

	/**
	 * ȡ����һ�����ڵĵ�һ�� changes1.1-00 ͳ�������������ܵ�ƽ��ֵ
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ��һ�����ڵĵ�һ��(YYYYMMDD)
	 */
	public static synchronized java.util.Date getFirstDayOfPrevWeek() {
		/**
		 * ��ϸ��ƣ� 1.����getNextWeek���õ�ǰʱ�� 2.��1Ϊ����������getFirstDayOfWeek
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
	 * ȡ��ָ�����ڵ��������ڵĵ�һ�� changes1.1-00 ͳ�������������ܵ�ƽ��ֵ
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ��������ڵĵ�һ��
	 */
	public static synchronized int getFirstDayOfWeek(int date) {
		/**
		 * ��ϸ��ƣ� 1.���date�������գ����0�� 2.���date������һ�����1�� 3.���date�����ڶ������2��
		 * 4.���date�������������3�� 5.���date�������ģ����4�� 6.���date�������壬���5��
		 * 7.���date�������������6��
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
	 * ȡ����һ�����ڵ����һ�� changes1.1-00 ͳ�������������ܵ�ƽ��ֵ
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ��һ�����ڵ����һ��(YYYYMMDD)
	 */
	public static synchronized java.util.Date getLastDayOfPrevWeek() {
		/**
		 * ��ϸ��ƣ� 1.����getNextWeek���õ�ǰʱ�� 2.��1Ϊ����������getFirstDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		Date d = getFirstDayOfPrevWeek();
		gc.setTime(d);
		gc.add(Calendar.DATE, 6);
		return gc.getTime();
	}

	// �õ�������ڵ����һ��
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
	 * ���ش������ڵ��·ݵ����һ��
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
	 * �������ʱ��,�����뱾�ܻ������һ�����ַ����ָ������
	 * 
	 * @return
	 */
	public static String getLastPeriod(int benginDate, int endDate1) {
		// �Ȼ��������������
		int distanceDate = getDistance(String.valueOf(benginDate), String
				.valueOf(endDate1));
		int endDate2 = Integer.parseInt(addDate2(String.valueOf(endDate1),
				distanceDate));
		String temp = String.valueOf(endDate1) + ",";
		temp = temp + String.valueOf(endDate2);
		return temp;
	}

	/**
	 * ���ݴ�������ڽ����·ݵļӼ�����,�����·ݴ���������month,�����·ݴ��븺����month
	 * 
	 * @return
	 */
	public static int getMonthHandler(int date, int month) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Calendar c = Calendar.getInstance();// ����Calendar�����ʵ��
			Date d = formatter.parse(date + "", new ParsePosition(0));// ���ܷ���null
			c.setTime(d);
			// c.add(Calendar.DATE, -2);//��Ҫ�Ӽ�������
			c.add(Calendar.MONTH, month);// ��Ҫ�Ӽ�������
			// c.add(Calendar.YEAR, -2);//��Ҫ�Ӽ�������
			Date tempDate = c.getTime();
			String strDate = formatter.format(tempDate);
			return Integer.parseInt(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * ȡ��ָ�����ڵ���һ������ changes1.1-00 ͳ�������������ܵ�ƽ��ֵ
	 * 
	 * @param date
	 *            ָ�����ڡ�
	 * @return ָ�����ڵ���һ������
	 */
	public static synchronized java.util.Date getPrevWeek(java.util.Date date) {
		/**
		 * ��ϸ��ƣ� 1.ָ�����ڼ�7��
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, -7);
		return gc.getTime();
	}

	/**
	 * �õ�ϵͳ�ĵ�ǰʱ�� YYYYMMDD
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
	 * ���������ʱ��,int��,yyyyMMdd
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
	 * ����һ�����ж�����
	 * 
	 * @param year
	 * @return int
	 */
	public static int isMontyToDay(int year, int month) {
		int maxDate;
		// �����0-11��
		month = month - 1;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		maxDate = cal.getActualMaximum(Calendar.DATE);
		return maxDate;
	}

	// ����ת��
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
	 * �õ�����ʱ�� yyyy-MM-dd
	 * YD
	 */
	public static String getNowDate() {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		String dd = d.format(new Date());
		return dd;
	}
	/**
	 * �õ�����ʱ�� yyyy-MM-dd HH:mm:ss
	 * YD
	 */
	public static String getNowDateSS() {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dd = d.format(new Date());
		return dd;
	}
	   /**
     * �õ�����ʱ�� yyyy-MM-dd HH:mm:ss
     * YD
     */
    public static String getDateyyyyMMdd() {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String dd = d.format(new Date());
        return dd;
    }
	/**
	 * �õ�����ʱ�� yyyy-MM-dd
	 * YD
	 */
	public static String getNowDateSS(Date date,String fmt) {
		SimpleDateFormat d = new SimpleDateFormat(fmt);
		String dd = d.format(date);
		return dd;
	}
	/**
	 * �õ�����ʱ�� yyyy-MM-dd
	 * YD
	 */
	public static String getNowDateInt() {
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		String dd = d.format(new Date());
		return dd;
	}
	/**
	 * �õ�����ʱ�� yyyy-MM-dd
	 * YD
	 */
	public static String getDateStr(Date date) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		String dd = d.format(date);
		return dd;
	}

	/**
	 * ��200812121212 תΪ YYYY-MM-DD HH:MM
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
	 * mysql db����dateTime����ת��
	 * @return java.sql.Timestamp
	 */
	public static Timestamp setSQLDateTime(){
		Date date = new Date();
		return new Timestamp(date.getTime());
	}
	
	/**
	 * mysql db����dateTime����ת��
	 * @return java.sql.Timestamp
	 */
	public static Timestamp setSQLDateTime(Date date){
		if(date == null)
			date = new Date();
		return new Timestamp(date.getTime());
	}
	
	/**
	 * �õ�ϵͳ��ǰʱ��
	 * @param format ָ����ʱ����ʾ��ʽ������:��yyyy-MM-dd HH:mm��
	 * @return ��ָ����ʽ��ʾ�ĵ�ǰ����
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
	 * �õ�ϵͳ��ǰ����������ڣ������տ�ʼ��
	 * @param format ָ����ʱ����ʾ��ʽ������:��yyyy-MM-dd HH:mm��
	 * @return ��ָ����ʽ��ʾ�ĵ�ǰ��������ַ�������
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
	 * �õ�ϵͳ��ǰ���ڵĵ�һ��
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
* ȡ����7��ĵ�һ�죨��һ�����ڣ�
*/
	public static String getNowWeekBegin() {
	int mondayPlus;
	Calendar cd = Calendar.getInstance();
	//��ý�����һ�ܵĵڼ��죬�������ǵ�һ�죬���ڶ��ǵڶ���......
	int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // ��Ϊ���й����һ��Ϊ��һ�����������1
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
	 *  �õ�ϵͳ��ǰ��
	 * @return ��ǰ��
	 */
	public static int getCurrentMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		String strMonth = dateFormat.format(cal.getTime());

		return Integer.valueOf(strMonth).intValue();
	}

	/**
	 * ��һ���ַ�������������ת��Ϊjava.util.Date����
	 * @param strDate �ַ�������������
	 * @param format �ַ��������ڸ�ʽ������:��yyyy-MM-dd HH:mm��
	 * @return �ַ���ת�������ڶ���java.util.Date
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
	 * ���ַ������������еõ��·�
	 * @param strDate �ַ�����������
	 * @param format �ַ��������ڸ�ʽ������:��yyyy-MM-dd HH:mm��
	 * @return �ַ����е��·�
	 */
	public static int getMonth(String strDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(getDate(strDate, format));
		String strMonth = dateFormat.format(cal.getTime());

		return Integer.valueOf(strMonth).intValue();
	}

	/**
	 * ���ַ������������еõ����
	 * @param strDate �ַ�����������
	 * @param format �ַ��������ڸ�ʽ������:��yyyy-MM-dd HH:mm��
	 * @return �ַ��������е����
	 */
	public static int getYear(String strDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(getDate(strDate, format));
		String strMonth = dateFormat.format(cal.getTime());

		return Integer.valueOf(strMonth).intValue();
	}

	/**
	 * �ж�ָ��ʱ���Ƿ���ĳһ��ʱ��������
	 * @param strDate Ҫ�жϵ������ַ���
	 * @param strBegin ʱ������ʼ�������ַ���
	 * @param strEnd ʱ����������������ַ���
	 * @param format �ַ��������ڸ�ʽ������:��yyyy-MM-dd HH:mm��
	 * @return ���ָ��ʱ����ʱ�������ڣ�����true
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
	 * �õ���ǰʱ��֮���ָ������������
	 * @param day ָ��������
	 * @param format �ַ��������ڸ�ʽ������:��yyyy-MM-dd HH:mm����
	 * @return String ��ǰʱ��֮���ָ������������
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
	 * �õ���ǰʱ�����ָ���������ĵ�����
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
	 * �õ���ǰʱ�����ָ�������ĵ�����
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
	* �����������ڼ������
	* 
	* @param fromDate
	* ��ʼ����
	* @param toDate
	* ��������
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