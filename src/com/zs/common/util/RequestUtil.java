package com.zs.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.zs.common.entity.DataTablesParameters;

/**
 * JSPҳ���request����Ӧ����
 * @author jiarui
 */
public class RequestUtil {
	/**
	 * ��ȡRequest�����Ͳ���
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
	 * ��ȡFload�����Ͳ���
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
	 * ��ȡRequest�����Ͳ���
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
	 * ��ȡRequest��Long���Ͳ���
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
	 * ��ȡRequest���ַ�����
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
	 * ����Form����(��������)
	 * 
	 * @param str
	 *            �ַ���
	 * @return �������ַ���
	 */
	public static String parseParameter(String str) {
		if (str == null)
			return "";
		else
			return str.trim();
	}

	/**
	 * ����������ÿ��ǰ�Ŀո�ȥ��
	 * 
	 * @param strOriginal
	 *            ��ת���ַ���
	 * @return ת������ַ���
	 */
	public static String replaceSpace(String strOriginal) {
		StringBuffer sbuf = new StringBuffer();
		// char ch = ' '; // ȫ�ǿո�
		char ch = '\u3000'; // ȫ�ǿո�

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
	 * ��ȡDataTables��������
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