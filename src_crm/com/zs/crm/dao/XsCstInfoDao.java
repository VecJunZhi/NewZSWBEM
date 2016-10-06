package com.zs.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsCstSearchOption;
import com.zs.crm.entity.XsCstInfo;
import com.zs.crm.entity.tableStructure.XsCstAttachTableEntity;
import com.zs.crm.entity.tableStructure.XsCstAttrTableEntity;
import com.zs.crm.entity.tableStructure.XsCstTableEntity;
import com.zs.crm.entity.tableStructure.XsEmployeeTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2CstTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2GjjlTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2RoomTableEntity;
import com.zs.crm.entity.tableStructure.XsOppTableEntity;
@Repository
public interface XsCstInfoDao {
	/**
	 * �������ۿͻ��Ļ�����Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstInfoDao(XsCstTableEntity bean);
	/**
	 * �������ۿͻ���������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstAttrDao(XsCstAttrTableEntity bean);
	/**
	 * �������ۿͻ���������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstAttachDao(XsCstAttachTableEntity bean);
	/**
	 * �������ۻ���ͻ���Ӧ��Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsOpp2CstDao(XsOpp2CstTableEntity bean);
	/**
	 * ������᷿���Ӧ��Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsOpp2RoomDao(XsOpp2RoomTableEntity bean);
	/**
	 * ���������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsOppDao(XsOppTableEntity bean);
	/**
	 * ����ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsFollowInfoDao(XsOpp2GjjlTableEntity bean);
	/**
	 * ������ҵ���ʲ��Ҹ���ҵ���ʵĿͻ�����
	 * @param bean
	 * @return
	 */
	public int getXsCstCountByEmployeeDao(XsCstSearchOption bean);
	/**
	 * ������ҵ���ʲ��Ҹ���ҵ���ʵĿͻ��б�
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstListByEmployeeDao(XsCstSearchOption bean);
	/**
	 * ���ݿͻ�id��ȡ�ͻ�����ϸ��Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstInfoByCstGuidDao(XsCstSearchOption bean);
	/**
	 * ���ݿͻ�id��ȡ�ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstFollowInfoByCstGuidDao(XsCstSearchOption bean);
	/**
	 * �鿴�ͻ������ô���
	 * @param bean
	 * @return
	 */
	public int getVisitTimesDao(XsCstSearchOption bean);
	/**
	 * ��ѯ�ͻ����״�����ʱ��
	 * @param bean
	 * @return
	 */
	public String getFirstVisitTimeDao(XsCstSearchOption bean);
	/**
	 * ��ѯ�ͻ��Ĺ������
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getCstBelongByTelDao(XsCstSearchOption bean);
	/**
	 * ���¿ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int updateXsCstInfoDao(XsCstTableEntity bean);
	/**
	 * ���¿ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int updateXsCstAttrInfoDao(XsCstAttrTableEntity bean);
	/**
	 * ���¿ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int updateXsOppInfoDao(XsOppTableEntity bean);
	/**
	 * ���¿ͻ������Ӧ������Ϣ
	 * @param bean
	 * @return
	 */
	public int updateXsOpp2RoomInfoDao(XsOpp2RoomTableEntity bean);
	/**
	 * �����ͻ�������Ϣ������������¼
	 * @param bean
	 * @return
	 */
	public int insertXsCstFollowInfoDao(XsOpp2GjjlTableEntity bean);
	/**
	 * ��ѯԱ����Ϣ
	 * @param bean
	 * @return
	 */
	public XsEmployeeTableEntity getXsEmployeeInfoDao(XsEmployeeTableEntity bean);
	/**
	 * ��ѯ�ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public XsCstTableEntity getXsCstBasicInfoDao(XsCstTableEntity bean);
	/**
	 * ��ѯ������Ϣ
	 * @param bean
	 * @return
	 */
	public XsOppTableEntity getXsOppInfoDao(XsOppTableEntity bean);
	public XsOppTableEntity getStatusFlgFromOppOrtunity(XsOppTableEntity bean);
	/**
	 * �����ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstAllInfoDao(XsCstInfo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int updateXsCstAndInsertOtherInfoDao(XsCstInfo bean);
	/**
	 * 
	 * @return
	 */
	public String checkCusBeFollowedByEmployeeDao();
	/**
	 * ������пͻ��ĵ绰����
	 * @return
	 */
	public   List<Map<String, Object>> getTel_allCustomer();
	/**
	 * ���ݿͻ�guid����Ŀguid��ѯ�ͻ���Ϣ
	 * @param bean
	 * @return
	 */
	public XsCstInfo getXsCstInfoByCstGuidAndProjGuidDao(XsCstSearchOption bean);
}
