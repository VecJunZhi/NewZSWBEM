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
 * �����֤�Ĺ���ģ��
 * @author LuLu
 *
 */
public class VertifyID {

	private URL url;
	private String appkey;
	private String output;
	
	/**���֤�ų���*/
	private static final int CARDNOLEN = 18; 
	/**�����ļ�·��*/
	private static final String FILEPATH = "/conf_sms/idCfg.properties";
	
	/**
	 * ���캯������ȡ�����ļ�����ʼ����Ա����
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
	 * ����Ŀ��URL
	 * @param nameval        	����
	 * @param cardnoval      	���֤��
	 * @return					isok   code
	 * 							  1     1              ��ѯ�ɹ���һ�� 
	 * 							        2                                       ��һ��
	 *  						        3                                      �޴����֤��
	 *  						  0     11             ��ѯʧ�ܣ���������ȷ
	 * 							        12                                      �̻�����
	 *  							    13              appkey������
	 *  								14              IP���ܾ�
	 *  							    20                                      ���֤����ά����
	 *  							    21                                      ����ϵͳ��������ϵ����                 
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
					//�������͵Ĵ���
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
	 * �����֤�Ķ���ӿ�
	 * @param nameval			����
	 * @param cardnoval			���֤��
	 * @return					isok   code
	 * 							  1     1              ��ѯ�ɹ���һ�� 
	 * 							        2                                       ��һ��
	 *  						        3                                      �޴����֤��
	 *  						  0     11             ��ѯʧ�ܣ���������ȷ
	 * 							        12                                      �̻�����
	 *  							    13              appkey������
	 *  								14              IP���ܾ�
	 *  							    20                                      ���֤����ά����
	 *  							    21                                      ����ϵͳ��������ϵ����
	 *    								-1				���������֤��Ϊ��
	 *           						-2				���֤�ų��ȴ���         
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
	 * ��������֤�Ķ���ӿ�
	 * @param nameListVal		�����б�
	 * @param cardnoListVal		���֤���б�
	 * @return					isok   code
	 * 							  1     1              ��ѯ�ɹ���һ�� 
	 * 							        2                                       ��һ��
	 *  						        3                                      �޴����֤��
	 *  						  0     11             ��ѯʧ�ܣ���������ȷ
	 * 							        12                                      �̻�����
	 *  							    13              appkey������
	 *  								14              IP���ܾ�
	 *  							    20                                      ���֤����ά����
	 *  							    21                                      ����ϵͳ��������ϵ����
	 *  								-1				���������֤��Ϊ��
	 *           						-2				���֤�ų��ȴ���
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
