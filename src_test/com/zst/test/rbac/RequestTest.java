package com.zst.test.rbac;

/**
 * 1 ʲô�������Ķ�����λ�������Ķ���
 * 2 ��������ü�������
 * 		�𣺱�д�����ӿڣ�ʵ�ּ����࣬���ü���
 * 3 ���������ã�
 * 		�𣺹��������Ƕ�webӦ����ͨ�õĴ���Ϳ��ƣ�����ͳһ����
 * 		<despatcher>���ã�����ָ�����˵�����ʽ��		
 * 4 jsp�����ö���
 * 5 el���ʽ
 * 6 el�����ö���
 * 7 ����Զ���el���ʽ������tld�ļ������ã��Լ���ο���tld�ļ���
 * 8 jstl��ʲô�����ʹ��jstl��
 * 
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Cookie;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class RequestTest {
	
	public static void jsonRequest(){
		
		//String url = "http://localhost:8090/loginWBEM.action";
		//String url="http://localhost:8090/wbem/system/sysSet/userManager.action";
		//�ٴ���Http Request(�ڲ�ʹ��HttpURLConnection)
		String url = "http://localhost:8090/response/ContentType.action";
		ClientHttpRequest request;
		try {
			request = new SimpleClientHttpRequestFactory().
			createRequest(new URI(url), HttpMethod.POST);
			//�����ÿͻ��˿ɽ��ܵ�ý�����ͣ�����Ҫʲô���͵���Ӧ�����ݣ�
			request.getHeaders().set("Accept", "application/json,application/html");
			//�۷������󲢵õ���Ӧ
			
			ClientHttpResponse response = request.execute();
			//response.setContentType("application/json;charset=utf-8");
			//�ܵõ���Ӧ��ı��뷽ʽ
			Charset charset = response.getHeaders().getContentType().getCharSet(); 
			String type=response.getHeaders().getContentType().getType()+" "+response.getHeaders().getContentType().getSubtype()+" ";
			MediaType list=response.getHeaders().getContentType();
			List<MediaType> req_list=request.getHeaders().getAccept();
			//String charset="utf-8";
			//�ݵõ���Ӧ������� 
			InputStream is = response.getBody();
			byte bytes[] = new byte[(int)response.getHeaders().getContentLength()];
			is.read(bytes);
			String jsonData = new String(bytes, charset);
			System.out.println("type "+type+" charset : " + charset + ", json data : " + jsonData+" medatype "+list+" req_list "+req_list);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value = "/response/ContentType", headers = "Accept=application/json")
	public void response2(HttpServletResponse response) throws IOException {
	//�ٱ�ʾ��Ӧ�����������ݵ�ý������Ϊjson��ʽ���ұ���Ϊutf-8(�ͻ���Ӧ����utf-8����)
		System.out.println("dd");
	response.setContentType("application/json;charset=utf-8");
	//��д����Ӧ������
	String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
	response.getWriter().write(jsonData);
	}
	public static void main(String[] args) {
		jsonRequest();
		HttpServletResponse response = null;
		HttpServletRequest request = null;
		request.getParameter("");
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			request.setAttribute("page", "abc");
			request.getParameterMap();
			
			request.getSession().setMaxInactiveInterval(50);
			javax.servlet.http.Cookie[] cook=request.getCookies();
			response.setContentType("text/html,charset=utf-8");
			response.addCookie(cook[0]);
			//ʲô��URL��д���ܽ��ʲô���⣿
			//��Ӧ�ӿ��е���encodeURL������path���б��룬
			response.encodeURL("");
			//ʲô�������Ķ�����λ�������Ķ���
			//�����ľ���һ��ȫ�ֵĸ��ÿһ��Ӧ�ö�Ӧһ�������Ķ���������ʼ��ʱ���ÿһ��Ӧ�������Ĵ���һ�������Ķ���
			HttpServlet servert = null;
			servert.getServletContext();
			
			//������������Ĳ������ڳ�������λ�������Ĳ�����
			//��web.xml�ļ������������ģ�
				/*<context-param>
					<context-name>
					<context-value>
				</context-param>*/
			
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
