package com.zs.busi.log.dao;

import java.util.List;

import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsInfoVo;

public interface ZsBusiCustomerLogDao {
	
	/**
	 * 新增日志记录
	 * @param vo
	 * @return
	 */
	public int insertZsCusChangeLog(ZsBusiCustomLogTable vo);
	/**
	 * 根据客户id查找到客户的变更记录,分配日志，回收日志
	 */
	public List<ZsBusiCustomLogTable> getCusRecordLogByCusId(ZsBusiCustomLogTable vo);
	/**
	 * 获得客户的最后回收原因，最后回收时间
	 * @param vo
	 * @return
	 */
	public ZsBusiCustomLogTable getCusReBackInfoByCusId(ZsInfoVo vo);
	/**
	 * 获得客户的回收次数，
	 * @param vo
	 * @return
	 */
	public int getCusReBackCountByCusId(ZsInfoVo vo);

}
