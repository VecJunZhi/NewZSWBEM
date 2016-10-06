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

import com.zs.busi.service.ZsInfoService;
import com.zs.busi.utils.LogUtil;
import com.zs.common.util.ding.DingDingVo;
import com.zs.common.util.ding.DingUtil;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.service.XsCustomAllocateService;
import com.zs.oa.entity.OaUserEntity;
import com.zs.oa.service.OaUserService;
import com.zs.oa.service.impl.OaUserServiceImpl;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.rbac.utils.RbacUtils;
import com.zs.test.common.Ajax;
import com.zs.crm.web.vo.XsCustomersManagerVo;

public class XsSystemTaskController {
	@Autowired 
	XsCustomAllocateService xsBusiCustomAllocateService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	ZsInfoService zsInfoService;
	Log log=LogUtil.getLog();
	
	public static Map<String, OaUserEntity> map ;
	static{
		OaUserService oaUserService = new OaUserServiceImpl();
		map = oaUserService.getAllUserService();
	}
	// 逾期超过天数的回收
	public void   autoAllocateToPublicCustomer(){
		String selectName=zsInfoService.getDictionaryValue("逾期客户自动回收");
		if("".equals(selectName)){
			log.info("词典中没有设置逾期客户自动回收类别");
		}else if("0".equals(selectName)){
			log.info("词典中逾期客户自动回收功能关闭");
		}else {
			List<XsTeamGroupEntity> proList = xsTeamGroupService.getProjectItem();
			XsCustomersManagerVo option = new XsCustomersManagerVo();
			for(XsTeamGroupEntity proInfo:proList) {
				option.setProjGUID(proInfo.getId());
				List<XsCustomersManagerEntity> list=xsBusiCustomAllocateService.autoRebackOverCustomer(option);
				log.info(list.size());
				String cusId;
				String oppGUID;
				String cstName;
				String mobileTel;
				if(list.size()>0){
					//sendDDMessage(list,"yu期");
				}
				if(list.size()>0){
					for (XsCustomersManagerEntity zsBusiCustomAllocateEntity : list) {
						log.info(zsBusiCustomAllocateEntity.getCstGuid());
						cusId=zsBusiCustomAllocateEntity.getCstGuid();
						cstName=zsBusiCustomAllocateEntity.getCstName();
						mobileTel=zsBusiCustomAllocateEntity.getMobileTel();
						oppGUID=zsBusiCustomAllocateEntity.getOppGUID();
						xsBusiCustomAllocateService.reBackCustomers(cusId, cstName,mobileTel,"逾期客户自动回收","逾期客户自动回收",proInfo.getId(),oppGUID,map);
					}
				}
			}
		}
	}
	//无效客户多少天回收为公共客户
	public void autoRebackInvalidCustomer(){
		String selectName=zsInfoService.getDictionaryValue("无效客户自动回收");
		if("".equals(selectName)){
			log.info("词典中没有设置无效客户自动回收类别");
		}else if("0".equals(selectName)){
			log.info("词典中无效客户自动回收功能关闭");
		}else{
			List<XsTeamGroupEntity> proList	 = xsTeamGroupService.getProjectItem();
			XsCustomersManagerVo option = new XsCustomersManagerVo();
			for(XsTeamGroupEntity proInfo:proList) {
				option.setProjGUID(proInfo.getId());
				List<XsCustomersManagerEntity> list=xsBusiCustomAllocateService.autoRebackInvalidCustomer(option);
				String cusId;
				String cstName;
				String mobileTel;
				String oppGUID;
				if(list.size()>0){
					sendDDMessage(list,"无效");
				}
				if(list.size()>0){
					for (XsCustomersManagerEntity zsBusiCustomAllocateEntity : list) {
						log.info(zsBusiCustomAllocateEntity.getCstGuid());
						cusId=zsBusiCustomAllocateEntity.getCstGuid();
						cstName=zsBusiCustomAllocateEntity.getCstName();
						mobileTel=zsBusiCustomAllocateEntity.getMobileTel();
						oppGUID=zsBusiCustomAllocateEntity.getOppGUID();
						xsBusiCustomAllocateService.reBackCustomers(cusId,cstName,mobileTel, "无效客户自动回收","无效客户自动回收",proInfo.getId(),oppGUID,map);
					}
				}
			}
		}
	}
	public void sendDDMessage(List<XsCustomersManagerEntity> list,String type){
		List<String> userList=new ArrayList<String>();
		List<String> oppList=new ArrayList<String>();
		for (XsCustomersManagerEntity entity : list) {
			userList.add(entity.getUserGuid());
			oppList.add(entity.getOppGUID());
		}
		JSONArray opparray =JSONArray.fromObject(oppList);
		JSONArray userguidArray =JSONArray.fromObject(userList);
		Map<String, String> bmap=new HashMap<String,String>();
		String beforeName="";
		for (int i = 0; i < opparray.size(); i++) {
			XsCustomersManagerEntity beflist=xsBusiCustomAllocateService.getZygwByoppGUID(opparray.getString(i));
			beforeName=beflist.getZygw();
			log.info(beforeName);
			bmap.put(opparray.getString(i), beforeName);
		}
		//回收后钉钉提醒
		Map<String, Object>ddMap=DingUtil.getDDmap(userguidArray, opparray);
		Set<Entry<String, Object>>sets=ddMap.entrySet();
		log.info(sets.size());
		for (Entry<String, Object> entry : sets) {
			DingDingVo d=(DingDingVo)entry.getValue();
			String bname=bmap.get(d.getOppguid());
			log.info(entry.getKey()+" "+d.getCount()+" "+d.getOppguid()+" "+bmap.get(d.getOppguid())+" ");
			String str0 = "";//"系统自动将"+bname+"的"+Integer.toString(d.getCount())+"个"+type+"客户回收成公共客户";
			if(bname.length()%2==1){//奇数
				str0 = "系统自动将"+bname+"的共"+Integer.toString(d.getCount())+"个"+type+"客户回收成公共客户";
			}else{//偶数
				str0 = "系统自动将"+bname+"的"+Integer.toString(d.getCount())+"个"+type+"客户回收成公共客户";
			}
			try {
				int uid=map.get(bname).uid;
				log.info(uid);
				String duid=Integer.toString(uid);
				byte[] bytes0 = str0.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
	            str0 = new String(bytes0, "GBK");//to GBK string  
	            DingUtil.sendTextMessage(str0, duid, "", "37907377");//正式
			} catch (Exception e) {
				log.info(e);
			}
		}
	}
	public void autoUpdateOppStatus(){
		List<XsTeamGroupEntity> proList = xsTeamGroupService.getProjectItem();
		XsCustomersManagerVo option = new XsCustomersManagerVo();
		for (XsTeamGroupEntity xsTeamGroupEntity : proList) {
			option.setOppGUID(xsTeamGroupEntity.getId());
			int i=xsBusiCustomAllocateService.autoUpdateOppStatus(option);
			log.info("auto update oppStatus success! ,total num is "+i);
		}
		
	}
	
}
