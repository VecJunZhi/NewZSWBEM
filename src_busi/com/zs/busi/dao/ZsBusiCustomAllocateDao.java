package com.zs.busi.dao;

import java.util.List;

import com.zs.busi.entity.ZsBusiCustomAllocateEntity;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsCustomFollowRecordTableVo;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.entity.ZsIntentionTableVo;
import com.zs.busi.web.vo.ZsBusiCustomAllocateVo;
import com.zs.rbac.entity.User;

public interface ZsBusiCustomAllocateDao {
//跟进中客户
	
	/**
	 * 获得逾期客户 ：非无效客户最后一个跟进记录的时间节点+逾期天数小于当前日期
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<ZsBusiCustomAllocateEntity> getZsOverdueCustInfoDao(ZsBusiCustomAllocateVo vo);
	/**
	 * 获得无效客户：客户信息表中有一个字段为是否有效，取出无效的客户
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<ZsBusiCustomAllocateEntity> getZsCusInvalidDao(ZsBusiCustomAllocateVo vo);
	/**
	 * 获得跟进客户：
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<ZsBusiCustomAllocateEntity> getZsCustFollowingDao(ZsBusiCustomAllocateVo vo);
	/**
	 * 获得跟进客户不分页：
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<ZsBusiCustomAllocateEntity> getZsCustFollowingDao_withoutPage(ZsBusiCustomAllocateVo vo);
	/**
	 * 获得公共客户：自动回收 逾期客户超过天数自动回收     ，在字典中设置公共客户编码
	 * 				     无效客户超过天数设置自动回收
	 *          手动回收：可以将跟进中的客户手动回收
	 * @param startIndex
	 * @param len
	 * @return
	 * 高级搜索
	 */
	public List<ZsBusiCustomAllocateVo> getZsCustPublicDao(ZsBusiCustomAllocateVo vo);
	/**
	 * 获得垃圾箱客户：从公共客户中移除的客户可以到垃圾箱 ,在字典中设置垃圾箱编码
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<ZsBusiCustomAllocateVo> getZsCustDusBinDao(ZsBusiCustomAllocateVo vo);
	
	/**
	 * 获得跟进中客户总数
	 * @return
	 */
	public int getZsCustCountDao(ZsBusiCustomAllocateVo vo);
	/**
	 * 获得公共和垃圾箱客户总数
	 * @return
	 */
	public int getZsCustPubDupCountDao(ZsBusiCustomAllocateVo vo);
	/**
	 * 根据客户id查找到客户的基本信息
	 */
	public ZsCustomTableVo getCustomInfoByCusId(ZsInfoVo vo);

	/**
	 * 根据业务员名称模糊检索业务员列表
	 * @param employeeName
	 * @return
	 */
	public List<User> getZsEmployeeList(ZsInfoVo vo);
	/**
	 * 分配(分配)
	 * String 为cusId
	 * @param list
	 * @return
	 */
	public int allocateCustomers(ZsInfoVo vo);
	/**
	 * 回收(回收)--成为公共客户(含回收)
	 * @param list
	 * @return
	 */
	public int reBackCustomers(ZsInfoVo vo);
	/**
	 * 垃圾箱(垃圾箱)
	 * @param list
	 * @return
	 */
	public int dusbinCustomers(ZsInfoVo vo);
	/**
	 * 自动回收逾期客户
	 * @return
	 */
	public List<ZsBusiCustomAllocateEntity> autoRebackOverCustomer(ZsInfoVo option);
	/**
	 * 自动回收无效客户
	 * @return
	 */
	public List<ZsBusiCustomAllocateEntity> autoRebackInvalidCustomer(ZsInfoVo option);
	/**
	 * 通过客户id获得用户编码--从客户意向条件中
	 * @param option
	 * @return
	 */
	public ZsBusiCustomAllocateEntity getUserGuidBycstId(ZsInfoVo option);
	
}
