package com.zs.test.common;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.busi.service.ZsBusiCustomAllocateService;
import com.zs.busi.utils.LogUtil;

/**
 *  ¬ŒÒ≤‚ ‘
 * @author zsjr
 *
 */
@Controller
public class TransActionTest {
	@Autowired 
	ZsBusiCustomAllocateService zsBusiCustomAllocateService;
	Log log=LogUtil.getLog();
	@RequestMapping(value="/transActionTest.action")
	public void transActionTest(){
		zsBusiCustomAllocateService.transTest();
	}

}
