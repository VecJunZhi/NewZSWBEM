package com.zs.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.net.*;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 身份验证的功能模块
 * @author LuLu
 *
 */
public class VertifyID {

	private URL url;
	private String appkey;
	private String output;
	
	/**身份证号长度*/
	private static final int CARDNOLEN = 18; 
	/**配置文件路径*/
	private static final String FILEPATH = "/conf_sms/idCfg.properties";
	
	/**
	 * 构造函数，读取配置文件并初始化成员变量
	 */
	public VertifyID()
	{
		Properties p = new Properties();				
		InputStream inStream = ProcMsg.class.getResourceAsStream(FILEPATH);
		try{
		p.load(inStream);}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		appkey = p.getProperty("appkey");		
		output = p.getProperty("output");
		String urlstr = p.getProperty("url");
		try{url = new URL(urlstr);}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 访问目标URL
	 * @param nameval        	姓名
	 * @param cardnoval      	身份证号
	 * @return					isok   code
	 * 							  1     1              查询成功：一致 
	 * 							        2                                       不一致
	 *  						        3                                      无此身份证号
	 *  						  0     11             查询失败：参数不正确
	 * 							        12                                      商户余额不足
	 *  							    13              appkey不存在
	 *  								14              IP被拒绝
	 *  							    20                                      身份证中心维护中
	 *  							    21                                      其它系统错误，请联系我们                 
	 */	
	public List<String> visitUrl(String nameval,String cardnoval)
	{
		String urlparam=null;
		String line;
		List<String> returnVal = new ArrayList<String>();
		String nameCode = null;
		
		try{
		nameCode = URLEncoder.encode(nameval,"utf-8");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		urlparam = "appkey="+appkey+"&"+"name="+nameCode+"&"+"cardno="+cardnoval+"&"+"output="+output;
		try{
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET"); 
			OutputStream os = con.getOutputStream();
			os.write(urlparam.getBytes());
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			
			while ((line = reader.readLine()) != null) 
			{
				if(output.equals("json"))
				{
					try{
						JSONObject jo = JSONObject.fromObject(line);
						String isokval = jo.getString("isok").toString();
						String codeval = jo.getString("code").toString();
						returnVal.add(isokval);
						returnVal.add(codeval);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else if(output.equals("xml"))
				{		
					try {
						Document document = DocumentHelper.parseText(line);
						Element root = document.getRootElement();
						String isokval = root.elementText("isok");
						String codeval = root.elementText("code");
						String errval = root.elementText("err");
						String addressval = root.elementText("address");
						String sexval = root.elementText("sex");
						String birthdayval = root.elementText("birthday");
						returnVal.add(isokval);
						returnVal.add(codeval);					
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				else
				{
					//其它类型的处理
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnVal;
	}
	
	/**
	 * 身份验证的对外接口
	 * @param nameval			姓名
	 * @param cardnoval			身份证号
	 * @return					isok   code
	 * 							  1     1              查询成功：一致 
	 * 							        2                                       不一致
	 *  						        3                                      无此身份证号
	 *  						  0     11             查询失败：参数不正确
	 * 							        12                                      商户余额不足
	 *  							    13              appkey不存在
	 *  								14              IP被拒绝
	 *  							    20                                      身份证中心维护中
	 *  							    21                                      其它系统错误，请联系我们
	 *    								-1				姓名或身份证号为空
	 *           						-2				身份证号长度错误         
	 */
	public List<String> vertifyIdentity(String nameval,String cardnoval)
	{
		List<String> list = new ArrayList<String>();
		if(nameval.isEmpty()||cardnoval.isEmpty())
		{
			list.add("0");
			list.add("-1");
			return list;
		}
		if(cardnoval.length() != CARDNOLEN)
		{
			list.add("0");
			list.add("-2");
			return list;
		}
		list = visitUrl(nameval,cardnoval);
		return list;
	}
	
	/**
	 * 多个身份验证的对外接口
	 * @param nameListVal		姓名列表
	 * @param cardnoListVal		身份证号列表
	 * @return					isok   code
	 * 							  1     1              查询成功：一致 
	 * 							        2                                       不一致
	 *  						        3                                      无此身份证号
	 *  						  0     11             查询失败：参数不正确
	 * 							        12                                      商户余额不足
	 *  							    13              appkey不存在
	 *  								14              IP被拒绝
	 *  							    20                                      身份证中心维护中
	 *  							    21                                      其它系统错误，请联系我们
	 *  								-1				姓名或身份证号为空
	 *           						-2				身份证号长度错误
	 */
	public List<String> vertifyIdentity(List<String> nameListVal,List<String> cardnoListVal)
	{
		Iterator<String> i,j;
		String nameVal;
		String cardnoVal;
		List<String> list = new ArrayList<String>();
		
		for(i = nameListVal.iterator(),j = cardnoListVal.iterator(); i.hasNext();)
		{
			nameVal = (String)i.next();
			cardnoVal = (String)j.next();
			list = vertifyIdentity(nameVal,cardnoVal);
			return list;
		}
		return list;
	}
}
