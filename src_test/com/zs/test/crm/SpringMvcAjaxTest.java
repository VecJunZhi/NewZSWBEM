package com.zs.test.crm;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.utils.LogUtil;

@Controller
public class SpringMvcAjaxTest {
	Log log=LogUtil.getLog();
	@RequestMapping(value="/ajaxTest.action",method=RequestMethod.POST,headers="accept",params={"age","sex"})
	@ResponseBody
	public ZsInfoVo ajaxTest(@RequestBody ZsInfoVo vo){
		log.info("adf");
		log.info(vo.getAddr());
		vo.setAge("20");
		vo.setAddr("addr");
		vo.setSex("Ů");
		return vo;
	}
	
	
}
