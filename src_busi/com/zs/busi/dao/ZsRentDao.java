package com.zs.busi.dao;

import java.util.List;

import com.zs.busi.entity.ZsRentSearchInfoVo;
import com.zs.busi.entity.ZsRentTableEntity;

public interface ZsRentDao {
	/**
	 * ��ѯ������Ϣ
	 * @param searchInfo
	 * @return
	 */
	public List<ZsRentTableEntity> getZsRentInfoListDao(ZsRentSearchInfoVo searchInfo);
	/**
	 * ���µ���������Ϣ
	 * @param bean
	 * @return
	 */
	public int updateZsRentInfoDao(ZsRentTableEntity bean);
}
