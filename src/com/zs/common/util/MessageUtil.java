package com.zs.common.util;

import org.springframework.ui.Model;

/**
 * ��Ϣ������
 * @author jiarui
 *
 */
public class MessageUtil {
	
	/**
	 * WEBM������Ϣ����
	 * @param model
	 * @param msg
	 * @return message jsp·��
	 */
	public static String WBEMMessage(Model model,String msg){
		if(msg == null)
			msg = "";
		model.addAttribute("message",msg);
		return "/wbem/pub/message";
	}
	
	/**
	 * WEBM������Ϣ����
	 * @param model
	 * @param msg
	 * @return message jsp·��
	 */
	public static String MBEMMessage(Model model,String msg){
		if(msg == null)
			msg = "";
		model.addAttribute("message",msg);
		return "/mbem/pub/message";
	}
	
}
