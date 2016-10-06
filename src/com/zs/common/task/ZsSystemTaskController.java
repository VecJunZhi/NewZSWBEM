package com.zs.common.task;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.zs.busi.entity.ZsBusiCustomAllocateEntity;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.service.ZsBusiCustomAllocateService;
import com.zs.busi.utils.LogUtil;
import com.zs.common.service.ZsDictionaryService;
import com.zs.common.util.ding.DingDingVo;
import com.zs.common.util.ding.DingUtil;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.oa.entity.OaUserEntity;
import com.zs.oa.service.OaUserService;
import com.zs.oa.service.impl.OaUserServiceImpl;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.test.common.Ajax;

public class ZsSystemTaskController {
	
	Log log=LogUtil.getLog();
	@Autowired 
	ZsBusiCustomAllocateService zsBusiCustomAllocateService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	ZsDictionaryService zsDictionaryService;
	public static Map<String, OaUserEntity> map ;
	static{
		OaUserService oaUserService = new OaUserServiceImpl();
		map = oaUserService.getAllUserService();
	}
	// 逾期超过天数的回收
	public void autoAllocateToPublicCustomer(){
		
		List<String> classesList = new ArrayList<String>();
		
		classesList.add("招商逾期客户自动回收");
		
		//classesList.add("招商无效客户自动回收");
		List<Map<String,String>> zsDictionaryEntityList =zsDictionaryService.getZsDictionaryByClassesDao(classesList);
		String _overDueFlg=zsDictionaryEntityList.get(0).get("selectName");
		log.info(_overDueFlg);
		if("1".equals(_overDueFlg)){
			ZsInfoVo option = new ZsInfoVo();
			List<XsTeamGroupEntity> proList = xsTeamGroupService.getProjectItem();
			for(XsTeamGroupEntity proInfo:proList) {
				option.setProjGUID(proInfo.getId());
				log.info(proInfo.getProjectId()+" sfdsagegaesd");
				List<ZsBusiCustomAllocateEntity> list=zsBusiCustomAllocateService.autoRebackOverCustomer(option);
				String cusId;
				String cusName;
				String tel;
				log.info(list.size());
				if(list.size()>0){
					//sendDDMessage(list,"yu期");
				}
				if(list.size()>0){
					for (ZsBusiCustomAllocateEntity zsBusiCustomAllocateEntity : list) {
						log.info(zsBusiCustomAllocateEntity.getCusId());
						cusId=zsBusiCustomAllocateEntity.getCusId();
						cusName=zsBusiCustomAllocateEntity.getCusName();
						tel=zsBusiCustomAllocateEntity.getTel();
						zsBusiCustomAllocateService.reBackCustomers(cusId,cusName,tel, "招商逾期客户自动回收","招商逾期客户自动回收",proInfo.getId(),map);
					}
				}
			}
		}else{
			log.info("招商逾期客户自动回收功能关闭");
		}
		
	}
	
	//无效客户多少天回收为公共客户
	public void autoRebackInvalidCustomer(){
		List<String> classesList = new ArrayList<String>();
		classesList.add("招商无效客户自动回收");
		List<Map<String,String>> zsDictionaryEntityList =zsDictionaryService.getZsDictionaryByClassesDao(classesList);
		String _invalidFlg=zsDictionaryEntityList.get(0).get("selectName");
		log.info(_invalidFlg);
		if("1".equals(_invalidFlg)){
			ZsInfoVo option = new ZsInfoVo();
			List<XsTeamGroupEntity> proList = xsTeamGroupService.getProjectItem();
			for(XsTeamGroupEntity proInfo:proList) {
				option.setProjGUID(proInfo.getId());
				List<ZsBusiCustomAllocateEntity> list=zsBusiCustomAllocateService.autoRebackInvalidCustomer(option);
				String cusId;
				String cusName;
				String tel;
				if(list.size()>0){
					sendDDMessage(list,"无效");
				}
				if(list.size()>0){
					for (ZsBusiCustomAllocateEntity zsBusiCustomAllocateEntity : list) {
						log.info(zsBusiCustomAllocateEntity.getCusId());
						cusId=zsBusiCustomAllocateEntity.getCusId();
						cusName=zsBusiCustomAllocateEntity.getCusName();
						tel=zsBusiCustomAllocateEntity.getTel();
						zsBusiCustomAllocateService.reBackCustomers(cusId,cusName,tel, "招商无效客户自动回收","招商无效客户自动回收",proInfo.getId(),map);
					}
				}
			}
		}else{
			log.info("招商无效客户自动回收功能关闭");
		}
		
	}
	public void sendDDMessage(List<ZsBusiCustomAllocateEntity> list,String type){
		List<String> userList=new ArrayList<String>();
		List<String> oppList=new ArrayList<String>();
		for (ZsBusiCustomAllocateEntity entity : list) {
			userList.add(entity.getUserName());
		}
		JSONArray opparray =JSONArray.fromObject(oppList);
		JSONArray userguidArray =JSONArray.fromObject(userList);
		//回收后钉钉提醒
		Map<String, Object>ddMap=DingUtil.getDDmap(userguidArray, opparray);
		Set<Entry<String, Object>>sets=ddMap.entrySet();
		log.info(sets.size());
		for (Entry<String, Object> entry : sets) {
			int count=(int)entry.getValue();
			String zygw=entry.getKey();
			String str0 = "系统自动将"+zygw+"的"+Integer.toString(count)+"个"+type+"客户回收成公共客户";
			if(zygw.length()%2==1){//奇数
				str0 = "系统自动将"+zygw+"的共"+Integer.toString(count)+"个"+type+"客户回收成公共客户";
			}else{//偶数
				str0 = "系统自动将"+zygw+"的"+Integer.toString(count)+"个"+type+"客户回收成公共客户";
			}
			try {
				int uid=map.get(zygw).uid;
				String duid=Integer.toString(uid);
				byte[] bytes0 = str0.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
	            str0 = new String(bytes0, "GBK");//to GBK string  
	            DingUtil.sendTextMessage(str0, duid, "", "37907377");//正式
			} catch (Exception e) {
				log.info(e);
			}
		}
	}
}
