package com.zs.common.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.common.service.AllFeedBackService;
import com.zs.common.util.DateUtil;
import com.zs.common.util.RequestUtil;
import com.zs.rbac.entity.User;
import com.zs.common.entity.ZsReplyFeedEntity;
@Controller
@RequestMapping(value="/wbem/index")
public class AllFeedBackController {
	@Autowired
	AllFeedBackService allFeedBackService;
	Log log=LogUtil.getLog();
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/allFeedBack")
	public String allFeedBack(Model model) {
		
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
		String userName=user.getUsername();
		model.addAttribute("userName",userName);
		log.info("dddfeeee");
		return "/wbem/index/allFeedBack";
	}
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/allFeedBackList")
	@ResponseBody
	public Map<String,Object> allFeedBackList(HttpServletRequest request) throws UnsupportedEncodingException {
				log.info("ddddfdfdsfd");
				//获得总数
				ZsUpdateLogEntity zsUpdateLogEntityS = new ZsUpdateLogEntity();
				int recordsTotal = allFeedBackService.findAllFeedBackDao(zsUpdateLogEntityS).size();
				int recordsFiltered=recordsTotal;
				//分页
				DataTablesParameters para= RequestUtil.getDTPara(request);
				int i=para.getStart()+1;
				int startIndex = para.getStart();
				int length = para.getLength();
				para.setColumnArray(new String[]{"","","","","",""});
				List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>();
				List<ZsUpdateLogEntity> zsUpdateLogList = new ArrayList<ZsUpdateLogEntity>();
				ZsUpdateLogEntity zsUpdateLogEntity = new ZsUpdateLogEntity();
				zsUpdateLogEntity.setStartIndex(startIndex);
				zsUpdateLogEntity.setLength(length);
				zsUpdateLogList=allFeedBackService.findAllFeedBackDao(zsUpdateLogEntity);
				for(ZsUpdateLogEntity zsUpdateLog:zsUpdateLogList) {
					Map<String,Object> zsUpdateMap = new HashMap<String,Object>();
					zsUpdateMap.put("logId", "<label class='checkbox inline index'> <input type='checkbox' name='radios'/>"+i+"</label>");
					zsUpdateMap.put("logSubject", zsUpdateLog.getLogSubject());
					zsUpdateMap.put("logContent", zsUpdateLog.getLogContent());
					zsUpdateMap.put("userName", zsUpdateLog.getUserName());
					zsUpdateMap.put("logTime", zsUpdateLog.getLogTime());
					
					String logTime = zsUpdateLog.getLogTime();
					ZsReplyFeedEntity zsReplyFeedEntity = new ZsReplyFeedEntity();
					zsReplyFeedEntity.setLogTime(logTime);
					
					List<ZsReplyFeedEntity> zsReplyFeedEntityList = new ArrayList<ZsReplyFeedEntity>();
					zsReplyFeedEntityList=allFeedBackService.getUnReadReplyDao(zsReplyFeedEntity);
					int size=zsReplyFeedEntityList.size();
					if(size>0){
						zsUpdateMap.put("warn", "<a style='color:red'>"+"有新回复"+"</a>");
					}else{
						zsUpdateMap.put("warn", "");
					}
					
					i++;
					lst.add(zsUpdateMap);
				}
				Map<String,Object> jsonResult = new HashMap<String,Object>();
				jsonResult.put("draw", para.getDraw());
				jsonResult.put("recordsTotal", recordsTotal);
				jsonResult.put("recordsFiltered", recordsFiltered);
				jsonResult.put("data",lst);
				return jsonResult;		
	}
	/**
	 * 发布反馈
	 * @param model
	 * @return
	 */
	@RequestMapping("/postAllFeedBack")
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
			k=allFeedBackService.postAllFeedBackDao(zsUpdateLog);
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}
	/**
	 * 删除反馈
	 * @param model
	 * @return
	 */
	@RequestMapping("/delAllFeedBack")
	@ResponseBody
	public String delAllFeedBack(String data){
		int k=0;
		try {
			JSONObject object =JSONObject.fromObject(data);
			String logTime =(String)object.get("logTime");
			logTime=URLDecoder.decode(logTime,"utf-8");
			
			ZsUpdateLogEntity zsUpdateLog = new ZsUpdateLogEntity();
			zsUpdateLog.setLogTime(logTime);
			k=allFeedBackService.delAllFeedBackDao(zsUpdateLog);
			
			ZsReplyFeedEntity zsReplyFeedEntity = new ZsReplyFeedEntity();
			zsReplyFeedEntity.setLogTime(logTime);
			allFeedBackService.delReplyFeedDao(zsReplyFeedEntity);
			
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
	@RequestMapping("/addReplyContent")
	@ResponseBody
	public String addReplyContent(String logTime,String userName,String replyContent){
		int k=0;
		try {
			replyContent=URLDecoder.decode(replyContent,"utf-8");
			userName=URLDecoder.decode(userName,"utf-8");
			ZsReplyFeedEntity zsReplyFeed = new ZsReplyFeedEntity();
			zsReplyFeed.setLogTime(logTime);
			zsReplyFeed.setReplyName(userName);
			zsReplyFeed.setReplyContent(replyContent);
			k=allFeedBackService.addReplyContentDao(zsReplyFeed);
			
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}
	/**
	 * 查询回复
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryReplyContents")
	@ResponseBody
	public Map<String,Object> queryReplyContents(String logTime) {
		ZsReplyFeedEntity zsReplyFeed = new ZsReplyFeedEntity();
		zsReplyFeed.setLogTime(logTime);
		List<ZsReplyFeedEntity> zsReplyFeedList = new ArrayList<ZsReplyFeedEntity>();
		zsReplyFeedList=allFeedBackService.queryReplyContentsDao(zsReplyFeed);
		Map<String,Object> zsReplyFeedMap = new HashMap<String,Object>();
		zsReplyFeedMap.put("zsReplyFeedEntityList", zsReplyFeedList);
		return zsReplyFeedMap;
	}
	
	
	/**
	 * 更改阅读状态
	 * @param model
	 * @return
	 */
	@RequestMapping("/changeUnReadType")
	@ResponseBody
	public String changeUnReadType(String logTime){
		int k=0;
		try {
			ZsReplyFeedEntity zsReplyFeed = new ZsReplyFeedEntity();
			zsReplyFeed.setLogTime(logTime);
			k=allFeedBackService.changeUnReadTypeDao(zsReplyFeed);
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}
}



