package com.zs.crm.service;

import java.util.List;
import java.util.Map;

import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.crm.entity.XsCstBelongToAjax;
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


public interface XsCstInfoService {
	/**
	 * 插入销售客户的基本信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstBasicInfo(XsCstTableEntity bean);
	/**
	 * 插入销售客户的特征信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstAttrInfo(XsCstAttrTableEntity bean);
	/**
	 * 插入销售客户的所属信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstAttachInfo(XsCstAttachTableEntity bean);
	/**
	 * 插入销售机会客户对应信息
	 * @param bean
	 * @return
	 */
	public int insertXsOpp2CstInfo(XsOpp2CstTableEntity bean);
	/**
	 * 插入机会房间对应信息
	 * @param bean
	 * @return
	 */
	public int insertXsOpp2RoomInfo(XsOpp2RoomTableEntity bean);
	/**
	 * 插入机会信息
	 * @param bean
	 * @return
	 */
	public int insertXsOppInfo(XsOppTableEntity bean);
	/**
	 * 插入客户跟进信息
	 * @param bean
	 * @return
	 */
	public int insertXsFollowInfo(XsOpp2GjjlTableEntity bean);
	/**
	 * 插入客户信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstInfo(XsCstInfo bean);
	/**
	 * 根据模块来查询当前页要显示的选项信息
	 * @param module
	 * @return
	 */
	public List<SearchOptionEntity> getCstInfoOptionByModule(String module);
	/**
	 * 根据选项名查询选项的列表项
	 * @param selectName
	 * @return
	 */
	public Map<String,Object> getDroplist(String name,int i,String tagname);
	/**
	 * 
	 * @param addOrUpdate
	 * @return
	 */
	public List<Map<String,Object>> addOrUpdateCstInfo(String addOrUpdate);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int getXsCstCountByEmployee(XsCstSearchOption bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstListByEmployee(XsCstSearchOption bean);
	/**
	 * 根据客户id获取客户的信息
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstInfoByCstGuid(XsCstSearchOption bean);
	/**
	 * 根据客户id获取客户跟进信息
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstFollowInfoByCstGuid(XsCstSearchOption bean);
	/**
	 * 获取客户的来访次数
	 * @param bean
	 * @return
	 */
	public int getVisitTimes(XsCstSearchOption bean);
	/**
	 * 获取客户首次来访日期
	 * @param bean
	 * @return
	 */
	public String getFirstVisitTime(XsCstSearchOption bean);
	/**
	 * 根据手机号查询客户归属
	 * @param tel
	 * @return
	 */
	public List<XsCstInfo> getXsCstBelongTo(String tel);
	/**
	 * 更新客户信息
	 * @param bean
	 * @return
	 */
	public String updateXsCstInfo(XsCstInfo bean);
	/**
	 * 更新客户的机会信息
	 * @param bean
	 * @return
	 */
	public int updateXsOppInfo(XsOppTableEntity bean);
	/**
	 * 新增客户的跟进信息
	 * @param bean
	 * @return
	 */
	
	public int insertXsCstFollowInfo(XsOpp2GjjlTableEntity bean);
	/**
	 * 查询员工信息
	 * @param bean
	 * @return
	 */
	public XsEmployeeTableEntity getXsEmployeeInfo(XsEmployeeTableEntity bean);
	/**
	 * 查询客户信息
	 * @param bean
	 * @return
	 */
	public XsCstTableEntity getXsCstBasicInfo(XsCstTableEntity bean);
	/**
	 * 查询机会信息
	 * @param bean
	 * @return
	 */
	public XsOppTableEntity getXsOppInfo(XsOppTableEntity bean);
	/**
	 * 新增客户时插入客户的所有信息
	 * @param bean
	 * @return
	 */
	public int insertXsCstAllInfo(XsCstInfo bean);
	/**
	 * 插入客户信息修改日志(修改手机号时插入)
	 * @param vo
	 * @return
	 */
	public int insertXsCstModifyLog(ZsBusiCustomLogTable vo);
	/**
	 * 获取下次跟进的日期
	 * @return
	 */
	public String getNextFollowDate();
	/**
	 * 
	 * @return
	 */
	public int updateXsCstAndInsertOtherInfo(XsCstInfo bean);
	/**
	 * 
	 * @return
	 */
	public String checkCusBeFollowedByEmployee();
	/**
	 * 获得所有客户的电话号码
	 * @return
	 */
	public   List<Map<String, Object>> getTel_allCustomer();
	/**
	 * 获得客户的最后的状态
	 * @param bean
	 * @return
	 */
	public XsOppTableEntity getStatusFlgFromOppOrtunity(String oppguid);
	/**
	 * 
	 * @param projGuid
	 * @param cstGuid
	 */
	public XsCstInfo getXsCstInfoByCstGuidAndProjGuid(String projGuid,String cstGuid);
}
