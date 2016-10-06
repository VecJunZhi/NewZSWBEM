package com.zs.crm.service;
import java.util.List;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.crm.entity.XsFindLogMbemEntity;
public interface XsFindLogMbemService {
	public List<XsFindLogMbemEntity> getXsLogDao();
	public int insertUpdateLogMbemDao(XsFindLogMbemEntity xsFindLogMbem);
}
