package com.zs.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.*;
import java.net.*;

import javax.ws.rs.client.Client;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * ʵ�ַ��Ͷ��ŵĹ���
 * @author LuLu
 *
 */
public class ProcMsg {
	
	private URL url;
	private String appkey;
	private int templateid;
	private String output;
	
	/**�������ŵ���󳤶�*/
	private static final int SINGLEMSGLEN = 65;
	/**�ֻ��ų���*/
	private static final int PHONELEN = 11;
	/**�����ļ���ַ*/
	private static final String FILEPATH = "/conf_sms/smsCfg.properties";
	
	/**
	 * ���캯������ȡ�����ļ�����ʼ����Ա����
	 */
	private ProcMsg()
	{
		Properties p = new Properties();				
		InputStream inStream=ProcMsg.class.getResourceAsStream(FILEPATH);
		
		try{
			p.load(inStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		appkey = p.getProperty("appkey");		
		String templatestr = p.getProperty("templateid");
		templateid = Integer.parseInt(templatestr);				
		output = p.getProperty("output");
		String urlstr = p.getProperty("url");
		try
		{
			url = new URL(urlstr);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * ����Ŀ��URL�Ľӿ�
	 * @param phoneVal   	�ֻ���
	 * @param msgVal     	��Ϣ����
	 * @return           	0	���ͳɹ�
	 *						1	�ֻ������ʽ����
	 *						2	IP���ܾ�
	 *						3	����ģ��ID�����ڻ����δͨ��
	 *						4	appkey������
	 *						5	param�������ݸ�ʽ���������ģ��ı��������������
	 *						6	�����������ȷ
	 *						7	�û�����
	 *						8	param���ݲ��Ϲ��Υ����
	 *						9	param���ݳ��ȳ���
	 *						10	��ͬһ�ֻ����������Ͷ�����ͬ��Ϣ
	 *						-1	����ԭ����ʧ�ܣ�����ϵ����
	 */
	private int visitUrl(String phoneVal,String msgVal)
	{
		String urlval = null;
		String line = null;
		String smsId = null;
		String errCode = null;
		String msgCode=null;
		int code;
		
		try
		{
			msgCode = URLEncoder.encode(msgVal,"utf-8");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		urlval = "appkey="+appkey+"&"+"phone="+phoneVal+"&"+"templateid="+templateid+"&"+"param="+msgCode+"&"+"output="+output;

		try{
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET"); 
			OutputStream os = con.getOutputStream();
			os.write(urlval.getBytes());
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			
			while ((line = reader.readLine()) != null) 
			{
				if(output.equals("json"))
				{
					JSONObject jo = JSONObject.fromObject(line);
					errCode = jo.get("errcode").toString();
					smsId = jo.get("smsid").toString();
					
				}
				else if(output.equals("xml"))
				{	
					try {
						Document document = DocumentHelper.parseText(line);
						Element root = document.getRootElement();
						
						errCode = root.elementText("errcode");
						smsId = root.elementText("smsid");					
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}					
				}
				else
				{
					//�������ʹ���
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		code = Integer.parseInt(errCode);
		System.out.println("errcode = "+errCode);
		System.out.println("smsid = "+smsId);
		return code;
	}
	
	/**
	 * ���Ͷ��ŵĽӿ�
	 * @param phoneVal   	�ֻ���
	 * @param msgVal     	��Ϣ����
	 * @return           	0	���ͳɹ�
	 *						1	�ֻ������ʽ����
	 *						2	IP���ܾ�
	 *						3	����ģ��ID�����ڻ����δͨ��
	 *						4	appkey������
	 *						5	param�������ݸ�ʽ���������ģ��ı��������������
	 *						6	�����������ȷ
	 *						7	�û�����
	 *						8	param���ݲ��Ϲ��Υ����
	 *						9	param���ݳ��ȳ���
	 *						10	��ͬһ�ֻ����������Ͷ�����ͬ��Ϣ
	 *						-1	����ԭ����ʧ�ܣ�����ϵ����
	 *						-2     �ֻ���Ϊ��
	 *						-3     �ֻ��ų��ȴ���
	 */
	private int sendMsg(String phoneVal,String msgVal)
	{
		int i=0;
		int msglen = msgVal.length();
		String submsg;
		int returnval;
		
		//System.out.println(msgVal);
		if(phoneVal.isEmpty())
		{
			System.out.println("�ֻ���Ϊ��");
			return -2;
		}
		if(phoneVal.length() != PHONELEN)
		{
			System.out.println("�ֻ��ų��ȴ���");
			return -3;
		}
		if(msglen >= SINGLEMSGLEN)
		{
			while(i+SINGLEMSGLEN <= msglen)
			{
				submsg = msgVal.substring(i, i+SINGLEMSGLEN);
				returnval = visitUrl(phoneVal,msgVal);
				if(returnval != 0)
					return returnval;
				i += SINGLEMSGLEN;
			}
			if(i != msglen)
			{
				submsg = msgVal.substring(i);
				returnval = visitUrl(phoneVal,msgVal);
				if(returnval != 0)
					return returnval;
			}
		}	
		else
		{
			returnval = visitUrl(phoneVal,msgVal);
			if(returnval != 0)
				return returnval;
		}
		return 0;
	}
	
	/**
	 * ���͵���������֤�Ķ���ӿ�
	 * @param phone			�ֻ���
	 * @param msg   		��Ϣ�ַ�����������һ����������
	 * @return           	0	���ͳɹ�
	 *						1	�ֻ������ʽ����
	 *						2	IP���ܾ�
	 *						3	����ģ��ID�����ڻ����δͨ��
	 *						4	appkey������
	 *						5	param�������ݸ�ʽ���������ģ��ı��������������
	 *						6	�����������ȷ
	 *						7	�û�����
	 *						8	param���ݲ��Ϲ��Υ����
	 *						9	param���ݳ��ȳ���
	 *						10	��ͬһ�ֻ����������Ͷ�����ͬ��Ϣ
	 *						-1	����ԭ����ʧ�ܣ�����ϵ����
	 *						-2     �ֻ���Ϊ��
	 *						-3     �ֻ��ų��ȴ���
	 */
	public int sendSMS(String phone,String...msg )
	{
		String msgStr = "";
		int result;
		
		for(String str:msg)
		{
			msgStr = msgStr+str;
		}
		result = sendMsg(phone,msgStr);
		return result;
	}
	
	/**
	 * Ⱥ��������֤�Ķ���ӿ�
	 * @param phoneListVal  �ֻ����б�
	 * @param msg			��Ϣ�ַ�����������һ������
	 * @return           	0	���ͳɹ�
	 *						1	�ֻ������ʽ����
	 *						2	IP���ܾ�
	 *						3	����ģ��ID�����ڻ����δͨ��
	 *						4	appkey������
	 *						5	param�������ݸ�ʽ���������ģ��ı��������������
	 *						6	�����������ȷ
	 *						7	�û�����
	 *						8	param���ݲ��Ϲ��Υ����
	 *						9	param���ݳ��ȳ���
	 *						10	��ͬһ�ֻ����������Ͷ�����ͬ��Ϣ
	 *						-1	����ԭ����ʧ�ܣ�����ϵ����
	 *						-2     �ֻ���Ϊ��
	 *						-3     �ֻ��ų��ȴ���
	 */
	public int sendSMS(List<String> phoneListVal,String...msg)
	{
		Iterator<String> i;
		String phoneVal = null;
		String msgStr = "";
		int result;
		
		for(String str:msg)
			msgStr = msgStr+str;
		for(i=phoneListVal.iterator();i.hasNext();)
		{	
			phoneVal = (String)i.next();
			result = sendSMS(phoneVal,msgStr);
			if(result != 0)
				return result;
		}
		return 0;
	}	
	/**
	 * ���Գ���
	 */
	public static void main(String args[])
	{
		String str;
		ProcMsg procmsg = new ProcMsg();
		//procmsg.sendSMS("13800000000");
		//procmsg.sendSMS("13363455873","��ӭ","8.15","��������");
		Properties p = new Properties();				
		InputStream inStream=ProcMsg.class.getResourceAsStream("/conf_sms/telList.properties");
		try{
			p.load(inStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String tel = p.getProperty("tel");
		System.out.println(tel);
		String name = p.getProperty("name");
		String nameArray[] = name.split(",");
		
		/*String roomno = p.getProperty("roomno");
		String roomArray[] = roomno.split(",");*/
		String telArray[] = tel.split(",");
		System.out.println(telArray.length);
		List<String> phoneList = new ArrayList<String>();
		for(int i=0; i< telArray.length; i++)
		{//phoneList.add(telArray[i]);
			try {
				String cusName = new String(nameArray[i].getBytes("ISO-8859-1"),"gbk");
				//System.out.println(cusName+" "+telArray[i]+" "+roomArray[i]);
				//System.out.println(telArray[i]+"     "+"�𾴵�"+cusName+"�ͻ������ã���л��ѡ����ʢ�ز���סլ��Ʒ����֪ͨ������Я�����֤ԭ�������Թ������ϵ������԰��¥����ȡ���н���ͬ���뾡����ȡ��");
				procmsg.sendSMS(telArray[i],"�𾴵�"+cusName+":��л��ѡ����ʢסլ��Ʒ���뱾��Я�����֤ԭ�������й������ϵ������԰��¥����ȡ���н���ͬ���뾡����ȡ");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("size"+telArray.length);
		
		//procmsg.sendSMS(phoneList,"�����԰��ף����������-����");
		//15835596709
		//procmsg.sendSMS("13363455873","�𾴵�","XXX",":��л��ѡ����ʢסլ��Ʒ���뱾��Я�����֤ԭ�������й������ϵ������԰��¥����ȡ���н���ͬ���뾡����ȡ");
		/*VertifyID vid = new VertifyID();
		vid.vertifyIdentity("������","610922197401232578");*/
	}
}


