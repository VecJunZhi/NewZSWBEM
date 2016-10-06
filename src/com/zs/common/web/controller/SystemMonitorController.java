package com.zs.common.web.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zs.common.util.SystemInfo;
import com.zs.common.web.vo.FormMap;
import com.zs.common.web.vo.ServerInfoFormMap;

/**
 * 
 * @author jiarui
 * @version 1.0v
 */
@Controller
@RequestMapping("/wbem/system/monitor")
public class SystemMonitorController {
	
	@RequestMapping("/info")
	public String info(Model model) throws Exception {
		model.addAttribute("cpu", "100");
		model.addAttribute("jvm", "100");
		model.addAttribute("ram", "100");
		model.addAttribute("toEmail", "jiarui@zgzsdc.com");
		model.addAttribute("systemInfo", SystemInfo.SystemProperty());
		return "/wbem/system/monitor/info";
	}
	
	@RequestMapping("/monitor")
	public String monitor() throws Exception {
		return  "/system/monitor/monitor";
	}
	
	
	@ResponseBody
	@RequestMapping("/usage")
	public ServerInfoFormMap usage(Model model) throws Exception {
		return SystemInfo.usage(new Sigar());
	}
	
	/**
	 * 获取传递的所有参数,
	 * 反射实例化对象，再设置属性值
	 * 通过泛型回传对象.
	 * @return Class<T>
	 * @throws Exception
	 */
	public <T> T getFormMap(Class<T> clazz){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		Enumeration<String> en = request.getParameterNames();
		T t = null;
		try {
			t = clazz.newInstance();
			@SuppressWarnings("unchecked")
			FormMap<String, Object> map = (FormMap<String, Object>) t;
			while (en.hasMoreElements()) {
				String nms = en.nextElement().toString();
				if(nms.endsWith("[]")){
					String[] as = request.getParameterValues(nms);
					if(as!=null&&as.length!=0&&as.toString()!="[]"){
						String mname = t.getClass().getSimpleName().toUpperCase();
						if(nms.toUpperCase().startsWith(mname)){
							nms=nms.substring(nms.toUpperCase().indexOf(mname)+1);
							map.put( nms,as);
						}
					}
				}else{
					String as = request.getParameter(nms);
					if(isEmpty(as)){
						String mname = t.getClass().getSimpleName().toUpperCase();
						if(nms.toUpperCase().startsWith(mname)){
							nms=nms.substring(mname.length()+1);
							map.put( nms, as);
						}
						
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return  t;
	}
	
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
}
