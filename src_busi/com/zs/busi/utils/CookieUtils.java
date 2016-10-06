package com.zs.busi.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieUtils {
	static Log log = LogFactory.getLog(CookieUtils.class);

	/**
	 * 获取cookie
	 * 
	 * @param request
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,String cookieName){
		String val = null;
		Cookie[] cookies = request.getCookies();
		if(cookies == null)
			 return val;
		
		for (Cookie c : cookies) {
			log.info(c.getName() + "--->" + c.getValue());
			if (c.getName().equals(cookieName)) {
				log.info("abc....");
				// 判读是否有效
				c.getMaxAge();
				log.info(c.getMaxAge());
				val = c.getValue();
			}
		}
		 
		 return val;
	}

	/**
	 * 新增cookie
	 * 
	 * @param username
	 * @param sessionId
	 * @param days
	 */
	public static void createCookie(HttpServletResponse response,
			String cookieName, String cookieValue, int days) {
		try {
			Cookie cookie = new Cookie(cookieName, cookieValue);
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * days);
			response.addCookie(cookie);
		} catch (Exception e) {
			log.info(e);
		}

	}

	/**
	 * 删除指定名称的cookie
	 * 
	 * @param response
	 * @param cookieName
	 */
	public static void deleteCookie(HttpServletResponse response,
			String cookieName) {
		Cookie cookie = new Cookie(cookieName, "");// 这边得用"",不能用null
		cookie.setPath("/");// 设置成跟写入cookies一样的
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		log.info(cookie);
	}

}
