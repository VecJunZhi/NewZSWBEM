package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.crm.entity.XsFindLogMbemEntity;

@Repository
public interface XsFindLogMbemDao {
	public List<XsFindLogMbemEntity> getXsLog();
	public int insertUpdateLogMbem(XsFindLogMbemEntity xsFindLogMbem);
}
