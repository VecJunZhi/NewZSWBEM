package com.zs.common.web.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.busi.utils.LogUtil;
import com.zs.common.service.ZsDictionaryService;

@Controller
@RequestMapping("/wbem/houses/sysSet")
public class ZsDictionaryController {
	@Autowired
	ZsDictionaryService zsDictionaryService ;
	Log log=LogUtil.getLog();
	/**
	 * ����    ���classes ����zs_�ʵ��е�  selectNameֵ������ֵ��
	 * @param zsDictionaryEntity
	 * @return
	 */
	@RequestMapping(value="/configPara")
	public String getZsDictionaryByClassesDaoController(Model model){	
		List<String> classesList = new ArrayList<String>();
		classesList.add("���ڻ�������");
		classesList.add("��������");	
		classesList.add("��Ч��������");		
		classesList.add("�����ͻ��Ƿ���������Աֱ�Ӹ���");
		classesList.add("�´θ����������");
		classesList.add("���ڿͻ��Զ�����");
		classesList.add("��Ч�ͻ��Զ�����");
		List<Map<String,String>> zsDictionaryEntityList =zsDictionaryService.getZsDictionaryByClassesDao(classesList);
		Map<String,String> zsDictionaryEntityMap = new HashMap<String,String>();
		for(Map<String,String> map:zsDictionaryEntityList){
			String classes = null;
			String selectName = null;
		    for (Map.Entry<String, String> entry : map.entrySet()) {
		        if ("classes".equals(entry.getKey())) {
		           classes = (String) entry.getValue();
		        }else if("selectName".equals(entry.getKey())) {
		           selectName =  entry.getValue();
		        }
		     }
		    zsDictionaryEntityMap.put(classes,selectName);
		}
		model.addAttribute("zsDictionaryEntityMap",zsDictionaryEntityMap);
	    return "/wbem/houses/sysSet/configPara";
	}
	/**
	 * ��������zs_�ʵ��е� selectNameֵ 
	 * @param overdueCustomerRecoveryDay
	 * @param overdueDay
	 * @param invalidClientRecoveryDay
	 * @param publicCustomerFollow
	 * @param publicCustomerNextFollowDay
	 * @param overdueCustomerRecovery
	 * @param invalidClientRecovery
	 * @return
	 */
	@RequestMapping(value="/updateConfigPara")
	public String updateZsDictionaryByClassesDaoController( String overdueCustomerRecoveryDay,
														    String overdueDay,
														    String invalidClientRecoveryDay,
														    String publicCustomerFollow,
														    String publicCustomerNextFollowDay,
														    String overdueCustomerRecovery,
														    String invalidClientRecovery,
														  	Model model){
		Map<String,Object> selectNameMap = new HashMap<String,Object>();
		selectNameMap.put("���ڻ�������",overdueCustomerRecoveryDay);
		selectNameMap.put("��������",overdueDay);
		selectNameMap.put("��Ч��������",invalidClientRecoveryDay);
		selectNameMap.put("�����ͻ��Ƿ���������Աֱ�Ӹ���",publicCustomerFollow);
		selectNameMap.put("�´θ����������",publicCustomerNextFollowDay);
		selectNameMap.put("���ڿͻ��Զ�����",overdueCustomerRecovery);
		selectNameMap.put("��Ч�ͻ��Զ�����",invalidClientRecovery);
		Map<String,Object> selectNameMap1 = new HashMap<String,Object>();
		selectNameMap1.put("selectNameMap1",selectNameMap);
		zsDictionaryService.updateZsDictionaryByClassesDao(selectNameMap1);
		return "redirect:/wbem/houses/sysSet/configPara";		
	}
}
