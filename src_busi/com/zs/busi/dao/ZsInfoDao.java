package com.zs.busi.dao;

import java.util.List;
import java.util.Map;

import com.zs.busi.entity.ZsCustomFollowRecordTableVo;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.entity.ZsIntentionTableVo;

public interface ZsInfoDao {
	/**
	 * ��������ϵͳ�ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertZsBasicInfoDao(ZsInfoVo bean);
	/**
	 * ��������ϵͳ�ͻ�����
	 * @param bean
	 */
	public int insertIntentionCondition(ZsInfoVo bean);
	/**
	 * ��������ϵͳ�ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int updateZsBasicInfoDao(ZsInfoVo bean);
	/**
	 * ��������ϵͳ�ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertZsFollowInfoDao(ZsInfoVo bean);
	/**
	 * �������ϵͳ�ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public List<ZsCustomTableVo> getZsBasicInfoDao(ZsInfoVo bean);
	/**
	 * �������ϵͳ������Ϣ
	 * @param bean
	 * @return
	 */
	public List<ZsCustomFollowRecordTableVo> getZsFollowInfoDao(ZsInfoVo bean);
	/**
	 * �������ϵͳ�ͻ��������ƶ���
	 * @param bean
	 * @return
	 */
	public String getCusInfoCompleteDegree(ZsInfoVo bean);
	/**
	 * �������ϵͳ�ͻ�����״̬
	 * @param bean
	 * @return
	 */
	public String getCusFollowStatus(ZsInfoVo bean);
	/**
	 * �������ϵͳ��ҵ�����б�
	 * @param bean
	 * @return
	 */
	public List<ZsEmployeeTableVo> getZsEmployeeList(ZsInfoVo bean);
	/**
	 * ��������ϵͳ��ҵ����
	 * @param bean
	 * @return
	 */
	public String insertZsEmployee(ZsInfoVo bean);
	/**
	 * ��������ϵͳ��ҵ������Ϣ
	 * @param bean
	 * @return
	 */
	public String updateZsEmployee(ZsInfoVo bean);
	/**
	 * �������ϵͳ���������б�
	 * @param selectName
	 * @return
	 */
	public List<ZsDictionaryTableVo> getDroplist(String selectName);
	/**
	 * �˲�ͻ��ǹ����ͻ�������Ч�ͻ�
	 * @return
	 */
	public ZsEmployeeTableVo checkCusPublicOrInvalid(ZsInfoVo bean);

	/**
	 * ��������������Ӧ��ֵ
	 * @param bean
	 * @return
	 */
	public ZsDictionaryTableVo getDictionaryValue(ZsDictionaryTableVo bean);
	/**
	 * �жϹ����ͻ��Ƿ���Ա���ҵ����ֱ�Ӹ���--����
	 * @return
	 */
	public ZsDictionaryTableVo checkCusBeFollowedByEmployee(ZsInfoVo bean);
	/**
	 * �˲�ͻ��ǹ����ͻ�������Ч�ͻ�--����
	 * @return
	 */
	public ZsDictionaryTableVo checkxs_CusBeFollowedByEmployee(ZsInfoVo bean);
	/**
	 * ���ݿͻ�id��ø����ÿͻ�����ҵ������Ϣ
	 * @return
	 */
	public List<ZsEmployeeTableVo> getEmployIdAndNameByCusId(ZsInfoVo bean);
	/**
	 * <p>������̻�����Ϣ�����µĸ�����Ϣ
	 * @param bean
	 * @return
	 */
	public ZsInfoVo getZsBasicInfoAndLastFollowingByCusId(ZsInfoVo bean);
	/**
	 * ����Զ����ɵ�Ĭ�ϵ��´θ�������--ZS
	 * @return
	 */
	public ZsCustomFollowRecordTableVo getNextFollowDate();	
	/**
	 * ����Զ����ɵ�Ĭ�ϵ��´θ�������--XS
	 * @return
	 */
	public ZsCustomFollowRecordTableVo getXS_NextFollowDate();	
	/**
	 * ���ݿͻ�id ��ÿͻ��������ں��´θ������� 
	 * @param bean
	 * @return
	 */
	public ZsCustomFollowRecordTableVo getFirstAndNextFollowDateByCusId(ZsInfoVo bean);
	
	public List<ZsIntentionTableVo> getIntentionLevel();
	/**
	 * ���ͻ���Ϊ�����ͻ�
	 * @param bean
	 * @return
	 */
	public int setCustomToInvalid(ZsInfoVo bean);
	/**
	 * ��ҳ��ȡ�ͻ��б�
	 * @param bean
	 * @return
	 */
	public List<ZsInfoVo> getZsCustomListInfoDao(ZsInfoVo bean);
	/**
	 * ����������ȡ�ͻ�������
	 * @param bean
	 * @return
	 */
	public int getZsCustomCountDao(ZsInfoVo bean);
	/**
	 * ���ݿͻ����ͻ�ȡ�ͻ�����
	 * @param type
	 * @return
	 */
	public int getZsCustomCountByTypeDao(ZsInfoVo bean);
	
	public List<ZsInfoVo> getZsCustomListInfoByTypeDao(ZsInfoVo bean);
	/**
	 * ������пͻ��ĵ绰����
	 * @return
	 */
	public   List<Map<String, Object>> getTel_allCustomer();
}
