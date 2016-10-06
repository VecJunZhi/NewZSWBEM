package com.zs.busi.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * sessionµÄÖµµÄÅÐ¶Ï
 * @author zhijun
 *
 */
public class SessionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    Log log= LogFactory.getLog(SessionInterceptor.class);
    @Autowired
    HttpServletRequest request;
    @Override
    public String intercept(ActionInvocation arg0) throws Exception {
        String userName = (String)ActionContext.getContext().getSession().get(ConstantPool.EMPLOYEENAME);
        Log log=LogFactory.getLog(SessionInterceptor.class);
        
        String _res=CookieUtils.getCookie(request,"username");
        if(userName == null){
        	log.info("session is empty and cookie is empty ");
        	if(!"".equals(_res) && _res != null){
        		log.info("session is empty auto login..");
        		return arg0.invoke();
        	}
            return "relogin";
        }else{
        	log.info("get context from session");
            return arg0.invoke();
        }
    }
	}
