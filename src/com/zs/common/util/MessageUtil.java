package com.zs.common.util;

import org.springframework.ui.Model;

/**
 * 消息提醒类
 * @author jiarui
 *
 */
public class MessageUtil {
	
	/**
	 * WEBM发送消息提醒
	 * @param model
	 * @param msg
	 * @return message jsp路径
	 */
	public static String WBEMMessage(Model model,String msg){
		if(msg == null)
			msg = "";
		model.addAttribute("message",msg);
		return "/wbem/pub/message";
	}
	
	/**
	 * WEBM发送消息提醒
	 * @param model
	 * @param msg
	 * @return message jsp路径
	 */
	public static String MBEMMessage(Model model,String msg){
		if(msg == null)
			msg = "";
		model.addAttribute("message",msg);
		return "/mbem/pub/message";
	}
	
}
