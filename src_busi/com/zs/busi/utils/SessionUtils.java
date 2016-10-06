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

/*2.���鷳�ķ���

a. ��web.xml������һ������

<listener>  
     <listener-class>  
         org.springframework.web.context.request.RequestContextListener  
     </listener-class>  
</listener>  


b.֮���ڳ����������

HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();*/  