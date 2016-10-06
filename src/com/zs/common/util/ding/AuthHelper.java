package com.zs.common.util.ding;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
/**
 * 仅供DingUtil使用
 * 获取accessToken
 * 并发送消息
 */
public class AuthHelper{
	//定义参数
	public static  String CORP_ID="";
	public static  String CORP_SECRET="";
	public static  String SSO_Secret="";
	public static String accessToken = "";
	//定义缓存时间
	public static long  timer = 0;
	public static final long cacheTime = 7000*1000;
	//文件中读取参数
	static{
		Properties p = new Properties();
		try {
			p.load(AuthHelper.class.getClassLoader().getResourceAsStream("config.properties"));
			 CORP_ID=p.getProperty("CORP_ID");
			 CORP_SECRET=p.getProperty("CORP_SECRET");
			 SSO_Secret=p.getProperty("SSO_Secret");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	/**
	 * 判断缓存时间
	 */
	private static boolean checkRequestStatus(){ 
		boolean checkStatus = false; 
		long currentTime = System.currentTimeMillis(); 
		if(timer >0 && currentTime-timer <= cacheTime){ 
			checkStatus = true; 
		} 
		return checkStatus; 
	} 
	/**
	 * 获取accessToken 工具
	 */
	public static String getAccessToken() throws Exception {
	   if(checkRequestStatus()){ 
			return accessToken; 
	   }else{
		   try{
			  BufferedReader in = null;
		      String urlNameString="https://oapi.dingtalk.com/gettoken?corpid="+CORP_ID+"&corpsecret="+CORP_SECRET;
			  URL realUrl = new URL(urlNameString);
			  URLConnection connection = realUrl.openConnection();
		      connection.connect();
			  in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			  String line;
			  while ((line = in.readLine()) != null){ 
		         JSONObject jo = JSONObject.parseObject(line);
		         String backStatus = jo.get("errmsg").toString(); 
		         if("ok".equals(backStatus)){ 
		            accessToken = jo.getString("access_token").toString(); 
		            timer=System.currentTimeMillis();
		         } 
		      }
		   }catch(Exception e){
			  e.printStackTrace(); 
		   }
	   }
	  return accessToken; 
	}
	/**
	 * 发送消息工具
	 * @param accessToken accessToken值
	 * @param messageDelivery 消息类
	 * @param messageform 消息类型,文本为："text",富文本为："oa"
	 * @return Receipt 返回的错误信息
	 */
	public static Receipt send(String accessToken, MessageDelivery messageDelivery,String messageform) throws Exception {
		
		String urlNameString = "https://oapi.dingtalk.com/message/send?access_token="+accessToken;
		JSONObject json = new JSONObject();
		json.put("touser", messageDelivery.touser);
		json.put("toparty", messageDelivery.toparty);
		json.put("agentid", messageDelivery.agentid);
		json.put("msgtype", messageDelivery.msgType);
		json.put(messageform, messageDelivery.message);
		
		URL realUrl = new URL(urlNameString);
		URLConnection conn = realUrl.openConnection();
		conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false); 
        conn.setRequestProperty("Content-Type", "application/json;charset=gbk"); 
        
        PrintWriter out = null;
        out = new PrintWriter(conn.getOutputStream());
        out.print(json);
        out.flush();
        BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line;
		String errcode = null;
		String errmsg = null;
		String invaliduser = null;
		String invalidparty = null;
		String messageId = null;
		while ((line = in.readLine()) != null){ 
	           JSONObject jo = JSONObject.parseObject(line);
	           String backStatus = jo.get("errmsg").toString(); 
	           if("ok".equals(backStatus)){ 
	        	   errcode = jo.getString("errcode").toString(); 
	        	   errmsg = jo.getString("errmsg").toString(); 
	        	   invaliduser = jo.getString("invaliduser").toString(); 
	        	   invalidparty = jo.getString("invalidparty").toString(); 
	        	   messageId = jo.getString("messageId").toString(); 
	           } 
	     }
		Receipt receipt = new Receipt();
		receipt.setErrcode(errcode);
		receipt.setErrmsg(errmsg);
		receipt.setInvaliduser(invaliduser);
		receipt.setInvalidparty(invalidparty);
		receipt.setMessageId(messageId);
		return receipt;
	}
	/**
	 * 获取jsapi_ticket
	 */
	public static String getTicket() {
		String ticket="";
		try{
			String accessToken = AuthHelper.getAccessToken();
			
			BufferedReader in = null;
		    String urlNameString="https://oapi.dingtalk.com/get_jsapi_ticket?access_token="+accessToken;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
		    connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null){ 
		       JSONObject jo = JSONObject.parseObject(line);
		       String backStatus = jo.get("errmsg").toString(); 
		       if("ok".equals(backStatus)){ 
		    	   ticket = jo.getString("ticket").toString(); 
		       } 
		    }
			
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return ticket;
	}
	/**
	 * 获取签名
	 */
	public static String sign(String ticket, String nonceStr, long timeStamp, String url) throws Exception {
		String plain = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + String.valueOf(timeStamp)
				+ "&url=" + url;
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			sha1.reset();
			sha1.update(plain.getBytes("UTF-8"));
			return bytesToHex(sha1.digest());
		} catch (Exception e) {
			throw new Exception();
		}
	}
	private static String bytesToHex(byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	/**
	 * 获取所有参数，传入前台
	 * @param request
	 * @return
	 */
	public static String getConfig(HttpServletRequest request) {
		String urlString = request.getRequestURL().toString();
		String queryString = request.getQueryString();

		String queryStringEncode = null;
		String url;
		if (queryString != null) {
			queryStringEncode = URLDecoder.decode(queryString);
			url = urlString + "?" + queryStringEncode;
		} else {
			url = urlString;
		}
		
		String nonceStr = "abcdefg";
		long timeStamp = System.currentTimeMillis() / 1000;
		String signedUrl = url;
		String accessToken = null;
		String ticket = null;
		String signature = null;
		String agentid = null;
		try {
			accessToken = AuthHelper.getAccessToken();
			ticket = AuthHelper.getTicket();
			
			signature = AuthHelper.sign(ticket, nonceStr, timeStamp, signedUrl);
			agentid = "37907377";
			
		} catch (Exception  e) {
			e.printStackTrace();
		}
		String configValue = "{jsticket:'" + ticket + "',signature:'" + signature + "',nonceStr:'" + nonceStr + "',timeStamp:'"
		+ timeStamp + "',corpId:'" + CORP_ID + "',agentid:'" + agentid+ "',accessToken:'" + accessToken+ "'}";
		System.out.println(configValue);
		return configValue;
	}
	
	public static void main(String[] args){
		System.out.println(AuthHelper.getTicket());
	}
}
