package com.zs.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.zs.common.entity.DataTablesParameters;

/**
 * JSP页面的request请求应用类
 * @author jiarui
 */
public class RequestUtil {
	/**
	 * 获取Request的整型参数
	 */
	public static double getDoubleParameter(HttpServletRequest request,
			String name, double defaultValue) {
		double val = defaultValue;
		try {
			val = Double.parseDouble(request.getParameter(name));
		} catch (Exception e) {
			val = defaultValue;
		}
		return val;
	}

	/**
	 * 获取Fload的整型参数
	 */
	public static float getFloatParameter(HttpServletRequest request,
			String name, float defaultValue) {
		float val = defaultValue;
		try {
			val = Float.parseFloat(request.getParameter(name));
		} catch (Exception e) {
			val = defaultValue;
		}
		return val;
	}

	/**
	 * 获取Request的整型参数
	 */
	public static int getIntParameter(HttpServletRequest request, String name,
			int defaultValue) {
		int intValue = defaultValue;
		try {
			intValue = Integer.parseInt(request.getParameter(name));
		} catch (Exception e) {
			intValue = defaultValue;
		}
		return intValue;
	}

	/**
	 * 获取Request的Long整型参数
	 */
	public static long getLongParameter(HttpServletRequest request,
			String name, long defaultValue) {
		long longValue = defaultValue;
		try {
			Long tmpInteger = new Long(request.getParameter(name));
			longValue = tmpInteger.longValue();
		} catch (Exception e) {
			longValue = defaultValue;
		}
		return longValue;
	} // function

	/**
	 * 获取Request的字符参数
	 */
	public static String getStringParameter(HttpServletRequest request,
			String name, String defaultValue) {
		String strValue = request.getParameter(name);
		if (strValue == null) {
			strValue = "";
		}
		if (strValue.trim().equals(""))
			strValue = defaultValue;
		return strValue;
	}

	/**
	 * 处理Form输入(新闻内容)
	 * 
	 * @param str
	 *            字符串
	 * @return 处理后的字符串
	 */
	public static String parseParameter(String str) {
		if (str == null)
			return "";
		else
			return str.trim();
	}

	/**
	 * 将新闻内容每段前的空格去掉
	 * 
	 * @param strOriginal
	 *            需转换字符串
	 * @return 转换后的字符串
	 */
	public static String replaceSpace(String strOriginal) {
		StringBuffer sbuf = new StringBuffer();
		// char ch = ' '; // 全角空格
		char ch = '\u3000'; // 全角空格

		StringTokenizer st = new StringTokenizer(strOriginal, "\n");
		while (st.hasMoreTokens()) {
			String str = StrUtil.iso2gbk(st.nextToken());
			StringBuffer tempBuf = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				char tempChar = str.charAt(i);
				if (tempChar == ch) {
					tempBuf.append(" ");
				} else {
					tempBuf.append(tempChar);
				}
			}
			str = tempBuf.toString();
			sbuf.append("    ").append(str.trim()).append("\n");
		}

		return StrUtil.gbk2iso(sbuf.toString());
	}
	
	public static Date getDateParameter(HttpServletRequest request, String key, Date defDate) {
        String s = request.getParameter(key);
        if (s != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                defDate = df.parse(s);
            } catch (Exception e) {
            }
        }
        return defDate;        
    }
	
	public static Date getDateParameter(HttpServletRequest request, String key, Date defDate,String format) {
        String s = request.getParameter(key);
        if (s != null) {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                defDate = df.parse(s);
            } catch (Exception e) {
            }
        }
        return defDate;        
    }
	
	/**
	 * 获取DataTables公共参数
	 * @param HttpServletRequest
	 * @return DataTablesParameters
	 */
	public static DataTablesParameters getDTPara(HttpServletRequest request){
		DataTablesParameters dtp = new DataTablesParameters();
		dtp.setDraw(getIntParameter(request, "draw", 0));
		dtp.setStart(getIntParameter(request, "start", 0));
		dtp.setLength(getIntParameter(request, "length", 0));
		dtp.setOrderColumn(getStringParameter(request,"order[0][column]", ""));
		dtp.setOrderDir(getStringParameter(request, "order[0][dir]", ""));
		return dtp;
	}
}