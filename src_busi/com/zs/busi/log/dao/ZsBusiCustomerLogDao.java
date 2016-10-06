package com.zs.busi.log.dao;

import java.util.List;

import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsInfoVo;

public interface ZsBusiCustomerLogDao {
	
	/**
	 * ������־��¼
	 * @param vo
	 * @return
	 */
	public int insertZsCusChangeLog(ZsBusiCustomLogTable vo);
	/**
	 * ���ݿͻ�id���ҵ��ͻ��ı����¼,������־��������־
	 */
	public List<ZsBusiCustomLogTable> getCusRecordLogByCusId(ZsBusiCustomLogTable vo);
	/**
	 * ��ÿͻ���������ԭ��������ʱ��
	 * @param vo
	 * @return
	 */
	public ZsBusiCustomLogTable getCusReBackInfoByCusId(ZsInfoVo vo);
	/**
	 * ��ÿͻ��Ļ��մ�����
	 * @param vo
	 * @return
	 */
	public int getCusReBackCountByCusId(ZsInfoVo vo);

}
