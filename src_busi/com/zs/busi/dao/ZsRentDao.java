package com.zs.busi.dao;

import java.util.List;

import com.zs.busi.entity.ZsRentSearchInfoVo;
import com.zs.busi.entity.ZsRentTableEntity;

public interface ZsRentDao {
	/**
	 * 查询租赁信息
	 * @param searchInfo
	 * @return
	 */
	public List<ZsRentTableEntity> getZsRentInfoListDao(ZsRentSearchInfoVo searchInfo);
	/**
	 * 更新单条租赁信息
	 * @param bean
	 * @return
	 */
	public int updateZsRentInfoDao(ZsRentTableEntity bean);
}
