package com.zs.busi.utils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;


public class ZsFilter implements Filter {
	Log log= LogFactory.getLog(ZsFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		String userName=CookieUtils.getCookie(req, "username");
			if(userName!=null && !"false".equals(userName)){
				session.setAttribute("telphone", userName);
				res.sendRedirect("/ZsInfoAction_getFollowRemindWithoutLogin");
				chain.doFilter(request,response);
			}else{
				log.info("cookie is empty");
				chain.doFilter(request,response);
			}
	}
	public void destroy() {
	}

	public String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	 
}
