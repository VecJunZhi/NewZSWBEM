package com.zs.busi.utils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionUtils {
	
	@Inject
	private  HttpServletRequest httpServletRequest;
	@Inject
	private HttpSession httpSession;
	
	@RequestMapping("/getSession")
	public HttpSession getSession(){
		httpSession.setAttribute("a", "dfd");
		LogUtil.getLog().info(httpSession.getAttribute("a"));
		return httpSession;
	}
	public static SessionUtils getInstance(){
		return new SessionUtils();
	}

}

/*2.最麻烦的方法

a. 在web.xml中配置一个监听

<listener>  
     <listener-class>  
         org.springframework.web.context.request.RequestContextListener  
     </listener-class>  
</listener>  


b.之后在程序里可以用

HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();*/  