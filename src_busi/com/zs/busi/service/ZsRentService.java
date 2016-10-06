package com.zs.busi.service;

import java.util.List;

import com.zs.busi.entity.ZsRentSearchInfoVo;
import com.zs.busi.entity.ZsRentTableEntity;

public interface ZsRentService {
	/**
	 * 
	 * @param searchInfo
	 * @return
	 */
	public List<ZsRentTableEntity> getZsRentInfoList(ZsRentSearchInfoVo searchInfo);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int updateZsRentInfo(ZsRentTableEntity bean);
}
