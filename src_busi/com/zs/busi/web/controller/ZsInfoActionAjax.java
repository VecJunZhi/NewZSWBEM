package com.zs.busi.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.ajax.ZsCustomBelongingAjaxVo;
import com.zs.busi.service.ZsInfoService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/mbem/mcrm/business")
public class ZsInfoActionAjax {
	
	private String data;
	private JSONObject result;
	@Autowired
	ZsInfoService service;
	@Autowired
	HttpSession httpSession;
	Log log=LogFactory.getLog(ZsCustomerController.class);

	@RequestMapping(value="/getZsCustomBelongingAjax")
	@ResponseBody
	public ZsCustomBelongingAjaxVo getZsCustomBelonging(@RequestParam(value="telVal",required=true) String telVal){
	    List<ZsCustomBelongingAjaxVo> list=service.getZsCustomBelonging(telVal);
	    String cst_id=list.get(0).getCst_id();
	    log.info("yaodjfiegdf "+cst_id);
	    httpSession.setAttribute("cst_id", list.get(0).getCst_id());
		return list.get(0);
	}	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	

}
