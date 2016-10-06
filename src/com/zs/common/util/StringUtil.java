package com.zs.common.util;



/**
 * <p>Title: VeryE基本工具类</p>
 * <p>Description: VeryE基本工具类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * @author JiaRui
 * @version 1.0
 */
public class StringUtil
{
    /**
     * 将一个字符串进行指定的字符集转换
     * @param st 要转换字符集的字符串
     * @param original 字符串原始的字符集,null表示不指定原有字符集
     * @param toCharset 要转换的字符集
     * @return 转换失败返回NULL
     */
    public static String convertCharSet(
            String st,
            String original,
            String toCharset)
    {
        if (st == null || toCharset == null)
            return "";

        String strResult;
        try
        {
            if (original == null)
            {
                strResult = new String(st.toString().getBytes(), toCharset);
            }
            else
            {
                strResult =
                        new String(st.toString().getBytes(original), toCharset);
            }

        }
        catch (Exception e)
        {
            return null;
        }

        return strResult;
    }

    /**
     * 从ISO8859_1转换到GBK
     * @param str 需要转换字符集的字符串
     * @return 转换后的字符串
     */
    public static String iso2gbk(String str)
    {
        String value = "";
        if (str == null || str.length() == 0)
        {
            return "";
        }

        try
        {
            value = new String(str.getBytes("ISO8859_1"), "GBK");
        }
        catch (Exception e)
        {
            return null;
        }
        return value;
    }

    /**
     * 从GBK转换到ISO8859_1
     * @param str 需要转换字符集的字符串
     * @return 转换后的字符串
     */
    public static String gbk2iso(String str)
    {
        String value = "";
        if (str == null || str.length() == 0)
        {
            return "";
        }

        try
        {
            value = new String(str.getBytes("GBK"), "ISO8859_1");
        }
        catch (Exception e)
        {
            return null;
        }
        return value;
    }

    /**
     * 处理为NULL的字符串，返回""
     * @param str 字符串
     * @return 字符串
     */
    public static String nullStr(String str)
    {

        if (str == null || str.length() == 0)
        {
            return "";
        }
        else
        {
            return str;
        }
    }

    /**
     * 将字符串转换成16进制的字节数组
     * @param inHex 字符串
     * @return 字节数组
     */
    public static byte[] fromString(String inHex)
    {
        int len = inHex.length();
        int pos = 0;
        byte buffer[] = new byte[((len + 1) / 2)];
        if ((len % 2) == 1)
        {
            buffer[0] = (byte) asciiToHex(inHex.charAt(0));
            pos = 1;
            len--;
        }

        for (int ptr = pos; len > 0; len -= 2)
            buffer[pos++] = (byte) ((asciiToHex(inHex.charAt(ptr++)) << 4)
                                    | (asciiToHex(inHex.charAt(ptr++))));
        return buffer;
    }

    /**
     * 将16进制的字节数组转换成字符串
     * @param buffer 字节数组
     * @return 字符串
     */
    public static final String toString(byte buffer[])
    {
        StringBuffer returnBuffer = new StringBuffer();
        for (int pos = 0, len = buffer.length; pos < len; pos++)
            returnBuffer.append(hexToAscii((buffer[pos] >>> 4) & 0x0F)).append(
                    hexToAscii(buffer[pos] & 0x0F));
        return returnBuffer.toString();
    }

    /**
     * 从字符串中得到整数型的值
     * @param str 字符串
     * @param defaultValue 缺省值
     * @return 整数值
     */
    public static int getInt(String str, int defaultValue)
    {
        if (str == null)
        {
            return defaultValue;
        }

        int intValue = defaultValue;
        try
        {
            intValue = Integer.parseInt(str);
        }
        catch (Exception e)
        {
            intValue = defaultValue;
        }
        return intValue;
    }

    /**
     * 从字符串中得到Long型的值
     * @param str 字符串
     * @param defaultValue 缺省值
     * @return 长整数
     */
    public static long getLong(String str, int defaultValue)
    {
        if (str == null)
        {
            return defaultValue;
        }

        long longValue = defaultValue;
        try
        {
            longValue = Long.parseLong(str);
        }
        catch (Exception e)
        {
            longValue = defaultValue;
        }
        return longValue;
    }

    private static final int asciiToHex(char c)
    {
        if ((c >= 'a') && (c <= 'f'))
            return (c - 'a' + 10);
        if ((c >= 'A') && (c <= 'F'))
            return (c - 'A' + 10);
        if ((c >= '0') && (c <= '9'))
            return (c - '0');
        throw new Error("ascii to hex failed");
    }

    private static char hexToAscii(int h)
    {
        if ((h >= 10) && (h <= 15))
            return (char) ('A' + (h - 10));
        if ((h >= 0) && (h <= 9))
            return (char) ('0' + h);
        throw new Error("hex to ascii failed");
    }
  
}
