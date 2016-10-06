/*
 * Created on 2005-9-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.zs.common.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sufeng
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class StrUtil {
	public static int DateStrToInt(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		return Integer.valueOf(str.replaceAll("-", ""));
	}

	public static String deleteLast(String result, String oldstr) {
		int splitIndex = 0;
		for (int i = result.length() - 1; i > 0; i--) {
			if (result.substring(i, i + 1).equals(oldstr))
				splitIndex = i;
			else
				break;
		}
		if (splitIndex > 0)
			result = result.substring(0, splitIndex);
		return result;
	}

	// 编码JS encode
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	// 将半角字符换成全角字符
	public static String filterWords(String str) {
		String[] oldChar = { "\'", "\"", ",", ";" };
		String[] newChar = { "＇", "＂", "，", "；" };
		for (int i = 0; i < oldChar.length; i++) {
			str = str.replace(oldChar[i], newChar[i]);
		}
		return str;
	}

	/**
	 * 从GBK转换到ISO8859_1
	 * 
	 * @param str
	 *            需要转换字符集的字符串
	 * @return 转换后的字符串
	 */
	public static String gbk2iso(String str) {
		String value = "";
		if (str == null || str.length() == 0) {
			return "";
		}

		try {
			value = new String(str.getBytes("GBK"), "ISO8859_1");
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	/**
	 * 从 GBK 转换到 GBK
	 * 
	 * @param str
	 *            需要转换字符集的字符串
	 * @return 转换后的字符串
	 */
	public static String gbk2utf8(String str) {
		String value = "";
		if (str == null || str.length() == 0) {
			return "";
		}

		try {
			value = new String(str.getBytes("GBK"), "UTF-8");
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	/**
	 * 
	 * @param str
	 * @param length
	 *            要输出的英文的长度 两个英文代表一个中文.
	 * @return 截取后的字符串
	 */
	public static String getFixWidthSub(String str, int length) {
		int j = 0;
		if (str == null || str.equals("")) {
			return "";
		}
		for (int i = 0; i < str.length(); i++) {
			if (((int) (str.charAt(i))) > 255) {
				j += 2;
			} else {
				j++;
			}
			if (j > length) {
				return str.substring(0, i) + "...";
			}
		}
		return str;
	}

	/**
	 * 获得字符串的前ｌｅｎｇｔｈ个字符（ＧＢＫ）
	 * 
	 * @param str
	 *            ＧＢＫ编码
	 * @param length
	 * @return
	 */
	public static String getSubShowStringCN(String str, int length) {
		String ret = "";
		if (str.length() > length) {
			ret = str.substring(0, length) + "...";
		} else {
			ret = str;
		}
		return ret;
	}

	public static boolean isBlankStr(String str) {
		if (str == null || str.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLetter(String str) {
		Pattern pattern = Pattern.compile("[A-Za-z]*");
		Matcher matcher = pattern.matcher(str);
		boolean result = matcher.matches();
		return result;
	}

	/**
	 * 从ISO8859_1转换到GBK
	 * 
	 * @param str
	 *            需要转换字符集的字符串
	 * @return 转换后的字符串
	 */
	public static String iso2gbk(String str) {
		String value = "";
		if (str == null || str.length() == 0) {
			return "";
		}

		try {
			value = new String(str.getBytes("ISO8859_1"), "GBK");
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	/**
	 * 从 ISO8859_1 转换到 GBK
	 * 
	 * @param str
	 *            需要转换字符集的字符串
	 * @return 转换后的字符串
	 */
	public static String iso2utf(String str) {
		String value = "";
		if (str == null || str.length() == 0) {
			return "";
		}

		try {
			value = new String(str.getBytes("ISO8859_1"), "UTF-8");
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	/**
	 * 将其中的大写字母换为小写
	 * 
	 * @param str
	 * @return
	 */
	public static String letterToLowerCase(String str) {
		String result = "";
		String s = str;
		if (s != null) {
			// s = StringUtil.gbk2iso(s);
			int length = s.length();
			for (int i = 0; i < length; i++) {
				if ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
					result = result + String.valueOf(s.charAt(i)).toLowerCase();
				} else {
					result = result + String.valueOf(s.charAt(i));
				}
			}
		}
		// return StringUtil.iso2gbk(result);
		return result;
	}

	/**
	 * 把为空或为NULL的对象转换成“”
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String nullStr(String str) {

		if (str == null || str.length() == 0) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 把为空或为NULL的对象转换成"0"
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String zeroStr(String str) {
		if (str == null || str.length() == 0 || str.equals("null")) {
			return "0";
		} else {
			return str;
		}
	}

	/**
	 * 字符串转换成int型；当str为null或""时，返回0
	 * 
	 * @param str
	 *            待转换的字符串
	 * @return
	 */
	public static int parseInt(String str) {
		int returnValue = 0;
		try {
			returnValue = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return returnValue;
		}
		return returnValue;
	}

	/**
	 * 字符串转换成int型；当str为null或""或者为非数字串时，返回指定的默认值
	 * 
	 * @param str
	 *            待转换的字符串
	 * @param returnValue
	 *            String不符合条件时返回的默认
	 * @return
	 */
	public static int parseInt(String str, int returnValue) {
		try {
			returnValue = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return returnValue;
		}
		return returnValue;
	}

	public static long parseLong(String str, long returnValue) {
		try {
			returnValue = Long.parseLong(str);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return returnValue;
		}
		return returnValue;
	}

	public static String removehtmltag(String htmlstr)
			throws UnsupportedEncodingException {
		htmlstr = htmlstr.replaceAll("\n", "<BR>");
		Pattern pat = Pattern.compile("\\s*<.*?>\\s*", Pattern.DOTALL
				| Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		Matcher m = pat.matcher(htmlstr);
		String rs = m.replaceAll("       ");
		rs = rs.replaceAll("&nbsp;", "       ");
		rs = rs.replaceAll("&lt;", "<");
		rs = rs.replaceAll("&gt;", ">");
		rs = rs.replaceAll("   ", "");
		return rs;
	}

	// 异或操作，也就是通用的对称加密、解密操作
	public static String strXor(String val, String key) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < val.length(); i++)
			result.append((char) (((int) val.charAt(i)) ^ key.charAt(i
					% key.length())));
		return result.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 从 ISO8859_1 转换到 GBK
	 * 
	 * @param str
	 *            需要转换字符集的字符串
	 * @return 转换后的字符串
	 */
	public static String utf2iso(String str) {
		String value = "";
		if (str == null || str.length() == 0) {
			return "";
		}

		try {
			value = new String(str.getBytes("UTF-8"), "ISO8859_1");
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	/**
	 * 从 GBK 转换到 GBK
	 * 
	 * @param str
	 *            需要转换字符集的字符串
	 * @return 转换后的字符串
	 */
	public static String utf82gbk(String str) {
		String value = "";
		if (str == null || str.length() == 0) {
			return "";
		}

		try {
			value = new String(str.getBytes("UTF-8"), "GBK");
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	// 检查是否是整数
	public static boolean isIntNum(String str) {
		String regex = "\\-?\\d{1,}";
		return Pattern.matches(regex, str);
	}

	// 检查字符串是否为空
	public static boolean isNullString(String str) {
		return str == null || "null".equalsIgnoreCase(str)
				|| str.trim().length() == 0;
	}

}
