package com.zst.test.rbac;

/**
 * 1 什么是上下文对象？如何获得上下文对象？
 * 2 如何在配置监听器？
 * 		答：编写监听接口，实现监听类，配置监听
 * 3 过滤器作用？
 * 		答：过滤器就是对web应用中通用的处理和控制，进行统一管理。
 * 		<despatcher>作用：用来指定过滤的请求方式。		
 * 4 jsp的内置对象？
 * 5 el表达式
 * 6 el的内置对象
 * 7 如何自定义el表达式，简述tld文件的作用，以及如何开发tld文件。
 * 8 jstl是什么，如何使用jstl。
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
		//①创建Http Request(内部使用HttpURLConnection)
		String url = "http://localhost:8090/response/ContentType.action";
		ClientHttpRequest request;
		try {
			request = new SimpleClientHttpRequestFactory().
			createRequest(new URI(url), HttpMethod.POST);
			//②设置客户端可接受的媒体类型（即需要什么类型的响应体数据）
			request.getHeaders().set("Accept", "application/json,application/html");
			//③发送请求并得到响应
			
			ClientHttpResponse response = request.execute();
			//response.setContentType("application/json;charset=utf-8");
			//④得到响应体的编码方式
			Charset charset = response.getHeaders().getContentType().getCharSet(); 
			String type=response.getHeaders().getContentType().getType()+" "+response.getHeaders().getContentType().getSubtype()+" ";
			MediaType list=response.getHeaders().getContentType();
			List<MediaType> req_list=request.getHeaders().getAccept();
			//String charset="utf-8";
			//⑤得到响应体的内容 
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
	//①表示响应的内容区数据的媒体类型为json格式，且编码为utf-8(客户端应该以utf-8解码)
		System.out.println("dd");
	response.setContentType("application/json;charset=utf-8");
	//②写出响应体内容
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
			//什么是URL重写？能解决什么问题？
			//响应接口中调用encodeURL方法对path进行编码，
			response.encodeURL("");
			//什么是上下文对象？如何获得上下文对象？
			//上下文就是一个全局的概念，每一个应用对应一个上下文对象，容器初始化时候给每一个应用上下文创建一个上下文对象。
			HttpServlet servert = null;
			servert.getServletContext();
			
			//如何配置上下文参数？在程序中如何获得上下文参数？
			//在web.xml文件中配置上线文，
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
