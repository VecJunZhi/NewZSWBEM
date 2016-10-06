package com.zs.busi.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zs.busi.ajax.ZsCustomBelongingAjaxVo;
import com.zs.busi.entity.ZsCustomFollowRecordTableVo;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.entity.ZsIntentionTableVo;


public interface ZsInfoService {
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public String insertZsBasicInfoDao(ZsInfoVo bean);
	/**
	 * 手动导入客户
	 * @param bean
	 * @param userName
	 * @return
	 */
	public String insertZsBasicInfoDao(ZsInfoVo bean,String userName);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public String updateZsBasicInfoDao(ZsInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public String insertZsFollowInfoDao(ZsInfoVo bean);
	/**
	 *	 * @param bean
	 * @return
	 */
	public List<ZsCustomTableVo> getZsBasicInfoDao(ZsInfoVo bean);
	
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<ZsCustomFollowRecordTableVo> getZsFollowInfoDao(ZsInfoVo bean);
	
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public String getCusInfoCompleteDegree(ZsInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public String getCusFollowStatus(ZsInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<ZsInfoVo> getFollowRecall(ZsInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<ZsEmployeeTableVo>   getZsEmployeeList(ZsInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public String  insertZsEmployee(ZsInfoVo bean);
	/**
	 * 
	 * @return
	 */
	public String updateZsEmployee(ZsInfoVo bean);
	/**
	 *
	 * @param selectName
	 * @return
	 */
	public List<Object> getAllDroplist(String addOrEdit);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<ZsCustomTableVo> getZsBasicInfoByEmployee(ZsInfoVo vo);
	/**
	 * 
	 * @return
	 */
	public List<ZsCustomTableVo> getZsBasicInfoByCusID();
	/**
	 *
	 * @param bean
	 * @return
	 */
	public List<ZsCustomFollowRecordTableVo> getZsFollowInfoByCustomID();
	/**
	 * 
	 * @return
	 */
	public List<ZsCustomFollowRecordTableVo> insertZsOldCusFollowInfoDao(String employeeName,String followDate,String followWay,String followInfo,String intentionType,String nextFollowDate);
	/**
	 * 
	 * @param cusId
	 * @param employeeId
	 * @param followDate
	 * @param followWay
	 * @param followInfo
	 * @return
	 */
	public String insertZsNewCusFollowInfoDao(String cusId,String employeeId,String followDate,String followWay,String followInfo);
	/**
	 * 
	 * @param cusId
	 * @param employeeId
	 * @param createDate
	 * @param modifyDate
	 * @param progGuid
	 * @return
	 */
	public String insertIntentionCondition(String cusId, String employeeId,String createDate,String modifyDate,String progGuid);
	
	/**
	 * 
	 */
	public Map<String,String> getFollowRemind();
	/**
	 *
	 */
	public List<ZsCustomBelongingAjaxVo> getZsCustomBelonging(String tel);
	/**
	 * 
	 * @param tel
	 * @return
	 */
	public String checkExistCusByTel(String tel);
	/**
	 * 
	 * @return
	 */
	public String getAutoGenNextFollowDate();
	/**
	 * 
	 * @return
	 */
	public List<ZsCustomTableVo> getZsBasicInfoByEmployeeAndCusNameOrTel(String cusNameOrTel);
	/**
	 * 
	 * @param intentionType
	 * @param customStatus
	 * @param age
	 * @param followWay
	 * @param cognitiveWay
	 * @param investmentInfo
	 * @param purposeItem
	 * @return
	 */
	public List<ZsCustomTableVo> getZsBasicInfoByEmployeeAndCusMoreInfo(String intentionType,String customStatus,String age,String followWay,String cognitiveWay,String investmentInfo,String purposeItem);
	/**
	 * 获得客户的分类
	 * @return
	 */
	public List<ZsIntentionTableVo> getIntentionLevel();
	public int setCustomToInvalid();
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<ZsInfoVo> getZsCustomListInfo(ZsInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int getZsCustomCount(ZsInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int getZsCustomCountByType(ZsInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<ZsInfoVo> getZsCustomListInfoByType(ZsInfoVo bean);
	/**
	 * 更改姓名或电话
	 * @param bean
	 * @return
	 */
	public String updateZsBasicInfo(ZsInfoVo bean);
	/**
	 * 获得所有客户的电话号码
	 * @return
	 */
	public   List<Map<String, Object>> getTel_allCustomer();
	/**
	 * 根据类别名获得相应的值
	 * @param bean
	 * @return
	 */
	public String getDictionaryValue(String  classes);
}
