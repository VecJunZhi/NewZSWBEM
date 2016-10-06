package com.zs.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

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
@Repository
public interface XsCstInfoDao {
	/**
	 * 插入销售客户的基本信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstInfoDao(XsCstTableEntity bean);
	/**
	 * 插入销售客户的特征信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstAttrDao(XsCstAttrTableEntity bean);
	/**
	 * 插入销售客户的所属信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstAttachDao(XsCstAttachTableEntity bean);
	/**
	 * 插入销售机会客户对应信息
	 * @param bean
	 * @return
	 */
	public int insertXsOpp2CstDao(XsOpp2CstTableEntity bean);
	/**
	 * 插入机会房间对应信息
	 * @param bean
	 * @return
	 */
	public int insertXsOpp2RoomDao(XsOpp2RoomTableEntity bean);
	/**
	 * 插入机会信息
	 * @param bean
	 * @return
	 */
	public int insertXsOppDao(XsOppTableEntity bean);
	/**
	 * 插入客户跟进信息
	 * @param bean
	 * @return
	 */
	public int insertXsFollowInfoDao(XsOpp2GjjlTableEntity bean);
	/**
	 * 根据置业顾问查找该置业顾问的客户数量
	 * @param bean
	 * @return
	 */
	public int getXsCstCountByEmployeeDao(XsCstSearchOption bean);
	/**
	 * 根据置业顾问查找该置业顾问的客户列表
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstListByEmployeeDao(XsCstSearchOption bean);
	/**
	 * 根据客户id获取客户的详细信息
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstInfoByCstGuidDao(XsCstSearchOption bean);
	/**
	 * 根据客户id获取客户跟进信息
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstFollowInfoByCstGuidDao(XsCstSearchOption bean);
	/**
	 * 查看客户的来访次数
	 * @param bean
	 * @return
	 */
	public int getVisitTimesDao(XsCstSearchOption bean);
	/**
	 * 查询客户的首次来访时间
	 * @param bean
	 * @return
	 */
	public String getFirstVisitTimeDao(XsCstSearchOption bean);
	/**
	 * 查询客户的归属情况
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getCstBelongByTelDao(XsCstSearchOption bean);
	/**
	 * 更新客户基本信息
	 * @param bean
	 * @return
	 */
	public int updateXsCstInfoDao(XsCstTableEntity bean);
	/**
	 * 更新客户特征信息
	 * @param bean
	 * @return
	 */
	public int updateXsCstAttrInfoDao(XsCstAttrTableEntity bean);
	/**
	 * 更新客户机会信息
	 * @param bean
	 * @return
	 */
	public int updateXsOppInfoDao(XsOppTableEntity bean);
	/**
	 * 更新客户机会对应房间信息
	 * @param bean
	 * @return
	 */
	public int updateXsOpp2RoomInfoDao(XsOpp2RoomTableEntity bean);
	/**
	 * 新增客户跟进信息，新增跟进记录
	 * @param bean
	 * @return
	 */
	public int insertXsCstFollowInfoDao(XsOpp2GjjlTableEntity bean);
	/**
	 * 查询员工信息
	 * @param bean
	 * @return
	 */
	public XsEmployeeTableEntity getXsEmployeeInfoDao(XsEmployeeTableEntity bean);
	/**
	 * 查询客户基本信息
	 * @param bean
	 * @return
	 */
	public XsCstTableEntity getXsCstBasicInfoDao(XsCstTableEntity bean);
	/**
	 * 查询机会信息
	 * @param bean
	 * @return
	 */
	public XsOppTableEntity getXsOppInfoDao(XsOppTableEntity bean);
	public XsOppTableEntity getStatusFlgFromOppOrtunity(XsOppTableEntity bean);
	/**
	 * 新增客户所有信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstAllInfoDao(XsCstInfo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int updateXsCstAndInsertOtherInfoDao(XsCstInfo bean);
	/**
	 * 
	 * @return
	 */
	public String checkCusBeFollowedByEmployeeDao();
	/**
	 * 获得所有客户的电话号码
	 * @return
	 */
	public   List<Map<String, Object>> getTel_allCustomer();
	/**
	 * 根据客户guid和项目guid查询客户信息
	 * @param bean
	 * @return
	 */
	public XsCstInfo getXsCstInfoByCstGuidAndProjGuidDao(XsCstSearchOption bean);
}
