package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.busi.entity.ZsInfoVo;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.web.vo.XsCustomersManagerVo;
@Repository
public interface XsCustomAllocateDao {
//跟进中客户
	
	/**
	 * 获得逾期客户 ：非无效客户最后一个跟进记录的时间节点+逾期天数小于当前日期
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<XsCustomersManagerEntity> getZsOverdueCustInfoDao(XsCustomersManagerVo vo);
	/**
	 * 获得无效客户：客户信息表中有一个字段为是否有效，取出无效的客户
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<XsCustomersManagerEntity> getZsCusInvalidDao(XsCustomersManagerVo vo);
	/**
	 * 获得跟进客户：
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<XsCustomersManagerEntity> getZsCustFollowingDao(XsCustomersManagerVo vo);
	/**
	 * 获得公共客户：自动回收 逾期客户超过天数自动回收     ，在字典中设置公共客户编码
	 * 				     无效客户超过天数设置自动回收
	 *          手动回收：可以将跟进中的客户手动回收
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<XsCustomersManagerEntity> getZsCustPublicDao(XsCustomersManagerVo vo);
	/**
	 * 获得垃圾箱客户：从公共客户中移除的客户可以到垃圾箱 ,在字典中设置垃圾箱编码
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<XsCustomersManagerEntity> getZsCustDusBinDao(XsCustomersManagerVo vo);
	
	/**
	 * 获得跟进中客户总数
	 * @return
	 */
	public int getZsCustCountDao(XsCustomersManagerVo vo);
	/**
	 * 获得公共和垃圾箱客户总数
	 * @return
	 */
	public int getZsCustPubDupCountDao(XsCustomersManagerVo vo);
	/**
	 * 根据客户id查找到客户的基本信息
	 */
	public XsCustomersManagerEntity getCustomInfoByCusId(XsCustomersManagerVo vo);

	/**
	 * 根据业务员名称模糊检索业务员列表
	 * @param employeeName
	 * @return
	 */
	public List<XsCustomersManagerEntity> getZsEmployeeList(XsCustomersManagerVo vo);
	/**
	 * 根据机会id获取置业顾问的相关信息
	 * @param vo
	 * @return
	 */
	public XsCustomersManagerEntity getZygwByoppGUID(XsCustomersManagerVo vo);
	
	/**
	 * 分配(分配)
	 * String 为cusId
	 * @param list
	 * @return
	 */
	public int allocateCustomers(XsCustomersManagerVo vo);
	/**
	 * 回收(回收)--成为公共客户(含回收)
	 * @param list
	 * @return
	 */
	public int reBackCustomers(XsCustomersManagerVo vo);
	/**
	 * 垃圾箱(垃圾箱)
	 * @param list
	 * @return
	 */
	public int dusbinCustomers(XsCustomersManagerVo vo);
	/**
	 * 自动回收逾期客户
	 * @return
	 */
	public List<XsCustomersManagerEntity> autoRebackOverCustomer(XsCustomersManagerVo option);
	/**
	 * 自动回收无效客户
	 * @return
	 */
	public List<XsCustomersManagerEntity> autoRebackInvalidCustomer(XsCustomersManagerVo option);
	/**
	 * 获得每个置业顾问的逾期客户的统计数
	 * @return
	 */
	public List<XsCustomersManagerEntity> getZsOverdueCustCountByYwy();
	/**
	 * 获得每个置业顾问的无效客户的统计数
	 * @return
	 */
	public List<XsCustomersManagerEntity> getZsCusInvalidCustCountByYwy();
	/**
	 * 获得每个置业顾问的跟进中客户的统计数
	 * @return
	 */
	public List<XsCustomersManagerEntity> getZsCustFollowingCustCountByYwy();
	/**
	 * 更改客户
	 * @param vo
	 * @return
	 */
	public int updateXsBasicInfo(XsCustomersManagerVo vo);
	/**
	 * 自动更新机会状态 第一步
	 * @return
	 */
	public int autoUpdateOppStatus(XsCustomersManagerVo vo);
	public List<XsCustomersManagerEntity>selectAutoUpdateOppStatus(XsCustomersManagerVo vo);
}
