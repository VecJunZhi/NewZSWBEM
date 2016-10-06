package com.zs.crm.web.controller;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.common.util.DateUtil;
import com.zs.crm.service.XsFeedBackService;
import com.zs.rbac.entity.User;
@Controller
@RequestMapping(value="/mbem/mcrm/house/find")
public class XsFeedBackController {
	
	@Autowired
	XsFeedBackService xsFeedBackService;
	/**
	 * 反馈列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/xsFeedBack")
	public String findXsFeedBack(Model model) {
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
		String userName=user.getUsername();
		model.addAttribute("userName",userName);
		
		List<ZsUpdateLogEntity> zsUpdateLogList = new ArrayList<ZsUpdateLogEntity>();
		zsUpdateLogList=xsFeedBackService.findXsFeedBackDao();
		/*for(ZsUpdateLogEntity zsUpdateLog:zsUpdateLogList){
			String logTimes = DateUtil.dateToString(zsUpdateLog.getLogTime());
			zsUpdateLog.setLogTime(logTimes);
		}*/
		for(ZsUpdateLogEntity zsUpdateLog:zsUpdateLogList){
			String logTime=zsUpdateLog.getLogTime();
			ZsReplyFeedEntity zsReplyFeed = new ZsReplyFeedEntity();
			zsReplyFeed.setLogTime(logTime);
			List<ZsReplyFeedEntity> zsReplyFeedEntityList=new ArrayList<ZsReplyFeedEntity>();
			zsReplyFeedEntityList=xsFeedBackService.findXsReplyFeedDao(zsReplyFeed);
			
			zsUpdateLog.setZsReplyFeedEntityList(zsReplyFeedEntityList);
		}
		model.addAttribute("zsUpdateLogList",zsUpdateLogList);
		return "/mbem/mcrm/house/find/xsFeedBack";
	}
	/**
	 * 发布反馈
	 * @param model
	 * @return
	 */
	@RequestMapping("/postXsFeedBack")
	@ResponseBody
	public String postXsFeedBack(String data){
		int k=0;
		try {
			JSONObject object =JSONObject.fromObject(data);
			String logSubject =(String)object.get("logSubject");
			String logContent =(String)object.get("logContent");
			String userName =(String)object.get("userName");
			logSubject=URLDecoder.decode(logSubject,"utf-8");
			logContent=URLDecoder.decode(logContent,"utf-8");
			userName=URLDecoder.decode(userName,"utf-8");
			ZsUpdateLogEntity zsUpdateLog = new ZsUpdateLogEntity();
			zsUpdateLog.setLogSubject(logSubject);
			zsUpdateLog.setLogContent(logContent);
			zsUpdateLog.setUserName(userName);
			k=xsFeedBackService.postXsFeedBackDao(zsUpdateLog);
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}
	/**
	 * 新增回复
	 * @param model
	 * @return
	 */
	@RequestMapping("/addXsReplyContent")
	@ResponseBody
	public String addXsReplyContent(String logTime,String replyContent,String replyName){
		int k=0;
		try {
			logTime=URLDecoder.decode(logTime,"utf-8");
			replyContent=URLDecoder.decode(replyContent,"utf-8");
			replyName=URLDecoder.decode(replyName,"utf-8");
			ZsReplyFeedEntity zsReplyFeed = new ZsReplyFeedEntity();
			zsReplyFeed.setLogTime(logTime);
			zsReplyFeed.setReplyContent(replyContent);
			zsReplyFeed.setReplyName(replyName);
			k=xsFeedBackService.addXsReplyContentDao(zsReplyFeed);
			
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}
}
