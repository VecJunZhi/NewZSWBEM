package com.zs.busi.dao;

import java.util.List;
import java.util.Map;

import com.zs.busi.entity.ZsCustomFollowRecordTableVo;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.entity.ZsIntentionTableVo;

public interface ZsInfoDao {
	/**
	 * 新增招商系统客户基本信息
	 * @param bean
	 * @return
	 */
	public int insertZsBasicInfoDao(ZsInfoVo bean);
	/**
	 * 新增招商系统客户意向
	 * @param bean
	 */
	public int insertIntentionCondition(ZsInfoVo bean);
	/**
	 * 更新招商系统客户基本信息
	 * @param bean
	 * @return
	 */
	public int updateZsBasicInfoDao(ZsInfoVo bean);
	/**
	 * 新增招商系统客户跟进信息
	 * @param bean
	 * @return
	 */
	public int insertZsFollowInfoDao(ZsInfoVo bean);
	/**
	 * 获得招商系统客户基本信息
	 * @param bean
	 * @return
	 */
	public List<ZsCustomTableVo> getZsBasicInfoDao(ZsInfoVo bean);
	/**
	 * 获得招商系统跟进信息
	 * @param bean
	 * @return
	 */
	public List<ZsCustomFollowRecordTableVo> getZsFollowInfoDao(ZsInfoVo bean);
	/**
	 * 获得招商系统客户资料完善都度
	 * @param bean
	 * @return
	 */
	public String getCusInfoCompleteDegree(ZsInfoVo bean);
	/**
	 * 获得招商系统客户跟进状态
	 * @param bean
	 * @return
	 */
	public String getCusFollowStatus(ZsInfoVo bean);
	/**
	 * 获得招商系统置业顾问列表
	 * @param bean
	 * @return
	 */
	public List<ZsEmployeeTableVo> getZsEmployeeList(ZsInfoVo bean);
	/**
	 * 新增招商系统置业顾问
	 * @param bean
	 * @return
	 */
	public String insertZsEmployee(ZsInfoVo bean);
	/**
	 * 更改招商系统置业顾问信息
	 * @param bean
	 * @return
	 */
	public String updateZsEmployee(ZsInfoVo bean);
	/**
	 * 获得招商系统所有下拉列表
	 * @param selectName
	 * @return
	 */
	public List<ZsDictionaryTableVo> getDroplist(String selectName);
	/**
	 * 核查客户是公共客户还是无效客户
	 * @return
	 */
	public ZsEmployeeTableVo checkCusPublicOrInvalid(ZsInfoVo bean);

	/**
	 * 根据类别名获得相应的值
	 * @param bean
	 * @return
	 */
	public ZsDictionaryTableVo getDictionaryValue(ZsDictionaryTableVo bean);
	/**
	 * 判断公共客户是否可以被置业顾问直接跟进--招商
	 * @return
	 */
	public ZsDictionaryTableVo checkCusBeFollowedByEmployee(ZsInfoVo bean);
	/**
	 * 核查客户是公共客户还是无效客户--销售
	 * @return
	 */
	public ZsDictionaryTableVo checkxs_CusBeFollowedByEmployee(ZsInfoVo bean);
	/**
	 * 根据客户id获得跟进该客户的置业顾问信息
	 * @return
	 */
	public List<ZsEmployeeTableVo> getEmployIdAndNameByCusId(ZsInfoVo bean);
	/**
	 * <p>获得招商基本信息和最新的跟进信息
	 * @param bean
	 * @return
	 */
	public ZsInfoVo getZsBasicInfoAndLastFollowingByCusId(ZsInfoVo bean);
	/**
	 * 获得自动生成的默认的下次跟进日期--ZS
	 * @return
	 */
	public ZsCustomFollowRecordTableVo getNextFollowDate();	
	/**
	 * 获得自动生成的默认的下次跟进日期--XS
	 * @return
	 */
	public ZsCustomFollowRecordTableVo getXS_NextFollowDate();	
	/**
	 * 根据客户id 获得客户创建日期和下次跟进日期 
	 * @param bean
	 * @return
	 */
	public ZsCustomFollowRecordTableVo getFirstAndNextFollowDateByCusId(ZsInfoVo bean);
	
	public List<ZsIntentionTableVo> getIntentionLevel();
	/**
	 * 将客户置为公共客户
	 * @param bean
	 * @return
	 */
	public int setCustomToInvalid(ZsInfoVo bean);
	/**
	 * 分页获取客户列表
	 * @param bean
	 * @return
	 */
	public List<ZsInfoVo> getZsCustomListInfoDao(ZsInfoVo bean);
	/**
	 * 根据条件获取客户的数量
	 * @param bean
	 * @return
	 */
	public int getZsCustomCountDao(ZsInfoVo bean);
	/**
	 * 根据客户类型获取客户数量
	 * @param type
	 * @return
	 */
	public int getZsCustomCountByTypeDao(ZsInfoVo bean);
	
	public List<ZsInfoVo> getZsCustomListInfoByTypeDao(ZsInfoVo bean);
	/**
	 * 获得所有客户的电话号码
	 * @return
	 */
	public   List<Map<String, Object>> getTel_allCustomer();
}
