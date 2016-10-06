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
	 * 根据    类别classes 查找zs_词典中的  selectName值（天数值）
	 * @param zsDictionaryEntity
	 * @return
	 */
	@RequestMapping(value="/configPara")
	public String getZsDictionaryByClassesDaoController(Model model){	
		List<String> classesList = new ArrayList<String>();
		classesList.add("逾期回收天数");
		classesList.add("逾期天数");	
		classesList.add("无效回收天数");		
		classesList.add("公共客户是否允许销售员直接跟进");
		classesList.add("下次跟进间隔天数");
		classesList.add("逾期客户自动回收");
		classesList.add("无效客户自动回收");
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
	 * 批量更新zs_词典中的 selectName值 
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
		selectNameMap.put("逾期回收天数",overdueCustomerRecoveryDay);
		selectNameMap.put("逾期天数",overdueDay);
		selectNameMap.put("无效回收天数",invalidClientRecoveryDay);
		selectNameMap.put("公共客户是否允许销售员直接跟进",publicCustomerFollow);
		selectNameMap.put("下次跟进间隔天数",publicCustomerNextFollowDay);
		selectNameMap.put("逾期客户自动回收",overdueCustomerRecovery);
		selectNameMap.put("无效客户自动回收",invalidClientRecovery);
		Map<String,Object> selectNameMap1 = new HashMap<String,Object>();
		selectNameMap1.put("selectNameMap1",selectNameMap);
		zsDictionaryService.updateZsDictionaryByClassesDao(selectNameMap1);
		return "redirect:/wbem/houses/sysSet/configPara";		
	}
}
