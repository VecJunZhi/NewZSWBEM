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
 * 实现发送短信的功能
 * @author LuLu
 *
 */
public class ProcMsg {
	
	private URL url;
	private String appkey;
	private int templateid;
	private String output;
	
	/**单条短信的最大长度*/
	private static final int SINGLEMSGLEN = 65;
	/**手机号长度*/
	private static final int PHONELEN = 11;
	/**配置文件地址*/
	private static final String FILEPATH = "/conf_sms/smsCfg.properties";
	
	/**
	 * 构造函数，读取配置文件并初始化成员变量
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
	 * 访问目标URL的接口
	 * @param phoneVal   	手机号
	 * @param msgVal     	消息内容
	 * @return           	0	发送成功
	 *						1	手机号码格式错误
	 *						2	IP被拒绝
	 *						3	短信模版ID不存在或审核未通过
	 *						4	appkey不存在
	 *						5	param内容数据格式错误（与短信模版的变量数量不相符）
	 *						6	必填参数不正确
	 *						7	用户余额不足
	 *						8	param内容不合规或含违禁词
	 *						9	param内容长度超限
	 *						10	对同一手机号连续发送多条相同信息
	 *						-1	其他原因发送失败，请联系我们
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
					//其它类型处理
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
	 * 发送短信的接口
	 * @param phoneVal   	手机号
	 * @param msgVal     	消息内容
	 * @return           	0	发送成功
	 *						1	手机号码格式错误
	 *						2	IP被拒绝
	 *						3	短信模版ID不存在或审核未通过
	 *						4	appkey不存在
	 *						5	param内容数据格式错误（与短信模版的变量数量不相符）
	 *						6	必填参数不正确
	 *						7	用户余额不足
	 *						8	param内容不合规或含违禁词
	 *						9	param内容长度超限
	 *						10	对同一手机号连续发送多条相同信息
	 *						-1	其他原因发送失败，请联系我们
	 *						-2     手机号为空
	 *						-3     手机号长度错误
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
			System.out.println("手机号为空");
			return -2;
		}
		if(phoneVal.length() != PHONELEN)
		{
			System.out.println("手机号长度错误");
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
	 * 发送单条短信验证的对外接口
	 * @param phone			手机号
	 * @param msg   		消息字符串，可以是一个或多个参数
	 * @return           	0	发送成功
	 *						1	手机号码格式错误
	 *						2	IP被拒绝
	 *						3	短信模版ID不存在或审核未通过
	 *						4	appkey不存在
	 *						5	param内容数据格式错误（与短信模版的变量数量不相符）
	 *						6	必填参数不正确
	 *						7	用户余额不足
	 *						8	param内容不合规或含违禁词
	 *						9	param内容长度超限
	 *						10	对同一手机号连续发送多条相同信息
	 *						-1	其他原因发送失败，请联系我们
	 *						-2     手机号为空
	 *						-3     手机号长度错误
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
	 * 群发短信验证的对外接口
	 * @param phoneListVal  手机号列表
	 * @param msg			消息字符串，可以是一个或多个
	 * @return           	0	发送成功
	 *						1	手机号码格式错误
	 *						2	IP被拒绝
	 *						3	短信模版ID不存在或审核未通过
	 *						4	appkey不存在
	 *						5	param内容数据格式错误（与短信模版的变量数量不相符）
	 *						6	必填参数不正确
	 *						7	用户余额不足
	 *						8	param内容不合规或含违禁词
	 *						9	param内容长度超限
	 *						10	对同一手机号连续发送多条相同信息
	 *						-1	其他原因发送失败，请联系我们
	 *						-2     手机号为空
	 *						-3     手机号长度错误
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
	 * 测试程序
	 */
	public static void main(String args[])
	{
		String str;
		ProcMsg procmsg = new ProcMsg();
		//procmsg.sendSMS("13800000000");
		//procmsg.sendSMS("13363455873","欢迎","8.15","即将到来");
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
				//System.out.println(telArray[i]+"     "+"尊敬的"+cusName+"客户，您好，感谢您选购兆盛地产的住宅产品，现通知您本人携带身份证原件及所以购房资料到御泽嘉园售楼部领取银行借款合同。请尽快领取。");
				procmsg.sendSMS(telArray[i],"尊敬的"+cusName+":感谢您选购兆盛住宅产品，请本人携带身份证原件及所有购房资料到御泽嘉园售楼部领取银行借款合同，请尽快领取");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("size"+telArray.length);
		
		//procmsg.sendSMS(phoneList,"御泽嘉园恭祝大家新年快乐-测试");
		//15835596709
		//procmsg.sendSMS("13363455873","尊敬的","XXX",":感谢您选购兆盛住宅产品，请本人携带身份证原件及所有购房资料到御泽嘉园售楼部领取银行借款合同，请尽快领取");
		/*VertifyID vid = new VertifyID();
		vid.vertifyIdentity("邓永望","610922197401232578");*/
	}
}


