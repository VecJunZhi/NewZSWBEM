package com.zs.crm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.dao.ZsInfoDao;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.busi.log.dao.ZsBusiCustomerLogDao;
import com.zs.busi.utils.LogUtil;
import com.zs.common.dao.IOptionListDao;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.crm.dao.XsCstInfoDao;
import com.zs.crm.entity.XsCstSearchOption;
import com.zs.crm.entity.XsCstInfo;
import com.zs.crm.entity.tableStructure.XsCstAttachTableEntity;
import com.zs.crm.entity.tableStructure.XsCstAttrTableEntity;
import com.zs.crm.entity.tableStructure.XsCstTableEntity;
import com.zs.crm.entity.tableStructure.XsEmployeeTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2CstTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2GjjlTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2RoomTableEntity;
import com.zs.crm.entity.tableStructure.XsOppTableEntity;
import com.zs.crm.service.XsCstInfoService;

@Service
public class XsCstInfoServiceImpl implements XsCstInfoService {
	Log log=LogUtil.getLog();
	
	@Autowired
	XsCstInfoDao xsCstInfoDao;
	@Autowired
	IOptionListDao optionListDao;
	@Autowired
	ZsInfoDao zsInfoDao;
	@Autowired
	ZsBusiCustomerLogDao zsLogDao;
	
	public int insertXsCstBasicInfo(XsCstTableEntity bean) {
		int result;
		result = xsCstInfoDao.insertXsCstInfoDao(bean);
		return result;
	}
	
	public int insertXsCstAttrInfo(XsCstAttrTableEntity bean) {
		int result;
		result = xsCstInfoDao.insertXsCstAttrDao(bean);
		return result;
	}
	
	public int insertXsCstAttachInfo(XsCstAttachTableEntity bean) {
		int result;
		result = xsCstInfoDao.insertXsCstAttachDao(bean);
		return result;
	}
	
	public int insertXsOpp2CstInfo(XsOpp2CstTableEntity bean) {
		int result;
		result = xsCstInfoDao.insertXsOpp2CstDao(bean);
		return result;
	}
	
	public int insertXsOpp2RoomInfo(XsOpp2RoomTableEntity bean) {
		int result;
		result = xsCstInfoDao.insertXsOpp2RoomDao(bean);
		return result;
	}
	
	public int insertXsOppInfo(XsOppTableEntity bean) {
		int result;
		result = xsCstInfoDao.insertXsOppDao(bean);
		return result;
	}
	
	public int insertXsFollowInfo(XsOpp2GjjlTableEntity bean) {
		int result;
		result = xsCstInfoDao.insertXsFollowInfoDao(bean);
		return result;
	}
	
	public int insertXsCstInfo(XsCstInfo bean) {
		int result=0;
		//分别插入每个表的信息
		xsCstInfoDao.insertXsCstInfoDao(bean.getXsCst());
		xsCstInfoDao.insertXsCstAttrDao(bean.getXsCstAttr());
		xsCstInfoDao.insertXsCstAttachDao(bean.getXsCstAttach());
		xsCstInfoDao.insertXsOpp2CstDao(bean.getXsOpp2Cst());
		xsCstInfoDao.insertXsOpp2RoomDao(bean.getXsOpp2Room());
		xsCstInfoDao.insertXsOppDao(bean.getXsOpp());
		xsCstInfoDao.insertXsFollowInfoDao(bean.getXsOpp2Gjjl());//需不需要按什么顺序插入，还有guid怎么来
		return result;
	}
	public List<SearchOptionEntity> getCstInfoOptionByModule(String module) {
		log.info("getCstInfoOption");
		SearchOptionEntity option = new SearchOptionEntity();
		option.setModule(module);
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		optionList = optionListDao.getOptionListByModule(option);
		return optionList;
	}
	
	public Map<String,Object> getDroplist(String name,int i,String tagname) {
		List<ZsDictionaryTableVo> list = zsInfoDao.getDroplist(name);
		Map<String,Object> map = new HashMap<String,Object>();
		if(name.equals("意向产品"))
			name = "意向类型";
		String select = list.get(0).getProperty();
		if(select.equals("0")) {
			select = "radio";
			map.put("name", name);
		}
		else if(select.equals("1")) {
			select = "checkbox";
			map.put("name", name+"(可多选)");
		}	
		map.put("select",select);
		map.put("value", list);
		map.put("href", tagname);
		map.put("id","idselect"+i);
		return map;
	}
	public List<Map<String,Object>> addOrUpdateCstInfo(String addOrUpdate) {
		List<Map<String,Object>> optionList = new ArrayList<Map<String,Object>>();
		if(addOrUpdate.equals("addCst") || addOrUpdate.equals("updateCst")) {
			Map<String,Object> age = getDroplist("年龄段",1,"ageGroup");
			Map<String,Object> cognitive = getDroplist("认知途径",2,"subMediaName");
			Map<String,Object> use = getDroplist("购房用途",3,"gfyt");
			Map<String,Object> area = getDroplist("意向面积",4,"yxArea");
			Map<String,Object> factor = getDroplist("关注因素",5,"gzfm1");
			Map<String,Object> workArea = getDroplist("工作区域",6,"workArea");
			Map<String,Object> liveArea = getDroplist("居住区域",7,"homeArea");
			Map<String,Object> workType = getDroplist("工作行业",8,"work");
			Map<String,Object> competitor = getDroplist("竞争对手",9,"competitor");
			Map<String,Object> price = getDroplist("意向价格",10,"gfys");
			//Map product = getDroplist("意向产品",11);
			Map<String,Object> room = getDroplist("意向房型",12,"yxFangXing");
			Map<String,Object> familyInfo = getDroplist("家庭结构",13,"family");
			//Map invesInfo = getDroplist("招商业态",14);
			optionList.add(age);
			optionList.add(cognitive);
			optionList.add(use);
			optionList.add(area);
			optionList.add(factor);
			optionList.add(workArea);
			optionList.add(liveArea);
			optionList.add(workType);
			optionList.add(competitor);
			optionList.add(price);
			//optionList.add(product);
			optionList.add(room);
			optionList.add(familyInfo);
			//optionList.add(invesInfo);
		}
		if(addOrUpdate.equals("addCst") || addOrUpdate.equals("addFollow")) {
			log.info("add");
			Map<String,Object> followType = getDroplist("跟进方式",15,"gjfs");
			Map<String,Object> intentionLevel = getDroplist("意向级别",16,"gfyx");
			//List<ZsIntentionTableVo> intentionLevelList=zsInfoDao.getIntentionLevel();//客户分类
			//Map intentionLevel  = new HashMap();
			//intentionLevel.put("value", intentionLevelList);
			//intentionLevel.put("select", "radio");
			//intentionLevel.put("href", "gfyx");
			//intentionLevel.put("id", "idselect16");
			//intentionLevel.put("name", "意向级别");
			optionList.add(followType);
			optionList.add(intentionLevel);
		}
		return optionList;
	}
	
	public int getXsCstCountByEmployee(XsCstSearchOption bean){
		int count = xsCstInfoDao.getXsCstCountByEmployeeDao(bean);
		return count;
	}
	
	public List<XsCstInfo> getXsCstListByEmployee(XsCstSearchOption bean) {
		List<XsCstInfo> cstList = new ArrayList<XsCstInfo>();
		cstList = xsCstInfoDao.getXsCstListByEmployeeDao(bean);
		return cstList;
	}
	
	public List<XsCstInfo> getXsCstInfoByCstGuid(XsCstSearchOption bean) {
		List<XsCstInfo> cstInfo = new ArrayList<XsCstInfo>();
		cstInfo = xsCstInfoDao.getXsCstInfoByCstGuidDao(bean);
		return cstInfo;
	}
	
	public List<XsCstInfo> getXsCstFollowInfoByCstGuid(XsCstSearchOption bean) {
		List<XsCstInfo> cstFollowInfo = new ArrayList<XsCstInfo>();
		cstFollowInfo = xsCstInfoDao.getXsCstFollowInfoByCstGuidDao(bean);
		return cstFollowInfo;
	}
	public int getVisitTimes(XsCstSearchOption bean) {
		int times;
		times = xsCstInfoDao.getVisitTimesDao(bean);
		return times;
	}
	public String getFirstVisitTime(XsCstSearchOption bean) {
		if(bean.getCstGuid()==null ||bean.getCstGuid().equals(""))
			return "";
		String firstVisitTime = xsCstInfoDao.getFirstVisitTimeDao(bean);
		return firstVisitTime;
	}
	public List<XsCstInfo> getXsCstBelongTo(String tel) {
		XsCstSearchOption cst = new XsCstSearchOption();
		List<XsCstInfo> belongInfo = new ArrayList<XsCstInfo>();
		cst.setMobileTel(tel);
		belongInfo = xsCstInfoDao.getCstBelongByTelDao(cst);
		return belongInfo;
	}
	
	public String updateXsCstInfo(XsCstInfo bean) {
		xsCstInfoDao.updateXsCstInfoDao(bean.getXsCst());
		xsCstInfoDao.updateXsCstAttrInfoDao(bean.getXsCstAttr());
		xsCstInfoDao.updateXsOppInfoDao(bean.getXsOpp());
		xsCstInfoDao.updateXsOpp2RoomInfoDao(bean.getXsOpp2Room());
		return "";
	}
	
	
	public int updateXsOppInfo(XsOppTableEntity bean) {
		int flag = xsCstInfoDao.updateXsOppInfoDao(bean);
		return flag;
	}
	public int insertXsCstFollowInfo(XsOpp2GjjlTableEntity bean) {
		xsCstInfoDao.insertXsCstFollowInfoDao(bean);
		return 0;
	}
	public XsEmployeeTableEntity getXsEmployeeInfo(XsEmployeeTableEntity bean) {
		XsEmployeeTableEntity employeeInfo = xsCstInfoDao.getXsEmployeeInfoDao(bean);
		return employeeInfo;
	}
	
	public XsCstTableEntity getXsCstBasicInfo(XsCstTableEntity bean) {
		XsCstTableEntity cstInfo = xsCstInfoDao.getXsCstBasicInfoDao(bean);
		return cstInfo;
	}
	public XsOppTableEntity getXsOppInfo(XsOppTableEntity bean) {
		XsOppTableEntity oppInfo = xsCstInfoDao.getXsOppInfoDao(bean);
		return oppInfo;
	}
	public int insertXsCstAllInfo(XsCstInfo bean) {
		xsCstInfoDao.insertXsCstAllInfoDao(bean);
		return 0;
	}
	public int insertXsCstModifyLog(ZsBusiCustomLogTable bean) {
		return zsLogDao.insertZsCusChangeLog(bean);
	}
	
	public String getNextFollowDate() {
		return zsInfoDao.getXS_NextFollowDate().getNextFollowDay();
	}
	
	public int updateXsCstAndInsertOtherInfo(XsCstInfo bean) {
		int flag = xsCstInfoDao.updateXsCstAndInsertOtherInfoDao(bean);
		return flag;
	}
	
	public String checkCusBeFollowedByEmployee() {
		String result = xsCstInfoDao.checkCusBeFollowedByEmployeeDao();
		return result;
	}

	@Override
	public List<Map<String, Object>> getTel_allCustomer() {
		List<Map<String, Object>> map=xsCstInfoDao.getTel_allCustomer();
		return map;
	}

	@Override
	public XsOppTableEntity getStatusFlgFromOppOrtunity(String oppGuid) {
		XsOppTableEntity bean =new XsOppTableEntity();
		bean.setOppGuid(oppGuid);
		XsOppTableEntity entity=xsCstInfoDao.getStatusFlgFromOppOrtunity(bean);
		return entity;
	}
	
	public XsCstInfo getXsCstInfoByCstGuidAndProjGuid(String projGuid,String cstGuid) {
		XsCstInfo cstInfo = new XsCstInfo();
		XsCstSearchOption bean = new XsCstSearchOption();
		bean.setProjGUID(projGuid);
		bean.setCstGuid(cstGuid);
		cstInfo = xsCstInfoDao.getXsCstInfoByCstGuidAndProjGuidDao(bean);
		return cstInfo;
	}
}
