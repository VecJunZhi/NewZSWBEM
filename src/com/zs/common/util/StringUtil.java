package com.zs.common.util;



/**
 * <p>Title: VeryE����������</p>
 * <p>Description: VeryE����������</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * @author JiaRui
 * @version 1.0
 */
public class StringUtil
{
    /**
     * ��һ���ַ�������ָ�����ַ���ת��
     * @param st Ҫת���ַ������ַ���
     * @param original �ַ���ԭʼ���ַ���,null��ʾ��ָ��ԭ���ַ���
     * @param toCharset Ҫת�����ַ���
     * @return ת��ʧ�ܷ���NULL
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
     * ��ISO8859_1ת����GBK
     * @param str ��Ҫת���ַ������ַ���
     * @return ת������ַ���
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
     * ��GBKת����ISO8859_1
     * @param str ��Ҫת���ַ������ַ���
     * @return ת������ַ���
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
     * ����ΪNULL���ַ���������""
     * @param str �ַ���
     * @return �ַ���
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
     * ���ַ���ת����16���Ƶ��ֽ�����
     * @param inHex �ַ���
     * @return �ֽ�����
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
     * ��16���Ƶ��ֽ�����ת�����ַ���
     * @param buffer �ֽ�����
     * @return �ַ���
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
     * ���ַ����еõ������͵�ֵ
     * @param str �ַ���
     * @param defaultValue ȱʡֵ
     * @return ����ֵ
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
     * ���ַ����еõ�Long�͵�ֵ
     * @param str �ַ���
     * @param defaultValue ȱʡֵ
     * @return ������
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
