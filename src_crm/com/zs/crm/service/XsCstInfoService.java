package com.zs.crm.service;

import java.util.List;
import java.util.Map;

import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.crm.entity.XsCstBelongToAjax;
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


public interface XsCstInfoService {
	/**
	 * �������ۿͻ��Ļ�����Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstBasicInfo(XsCstTableEntity bean);
	/**
	 * �������ۿͻ���������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstAttrInfo(XsCstAttrTableEntity bean);
	/**
	 * �������ۿͻ���������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstAttachInfo(XsCstAttachTableEntity bean);
	/**
	 * �������ۻ���ͻ���Ӧ��Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsOpp2CstInfo(XsOpp2CstTableEntity bean);
	/**
	 * ������᷿���Ӧ��Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsOpp2RoomInfo(XsOpp2RoomTableEntity bean);
	/**
	 * ���������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsOppInfo(XsOppTableEntity bean);
	/**
	 * ����ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsFollowInfo(XsOpp2GjjlTableEntity bean);
	/**
	 * ����ͻ���Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstInfo(XsCstInfo bean);
	/**
	 * ����ģ������ѯ��ǰҳҪ��ʾ��ѡ����Ϣ
	 * @param module
	 * @return
	 */
	public List<SearchOptionEntity> getCstInfoOptionByModule(String module);
	/**
	 * ����ѡ������ѯѡ����б���
	 * @param selectName
	 * @return
	 */
	public Map<String,Object> getDroplist(String name,int i,String tagname);
	/**
	 * 
	 * @param addOrUpdate
	 * @return
	 */
	public List<Map<String,Object>> addOrUpdateCstInfo(String addOrUpdate);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int getXsCstCountByEmployee(XsCstSearchOption bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstListByEmployee(XsCstSearchOption bean);
	/**
	 * ���ݿͻ�id��ȡ�ͻ�����Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstInfoByCstGuid(XsCstSearchOption bean);
	/**
	 * ���ݿͻ�id��ȡ�ͻ�������Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsCstInfo> getXsCstFollowInfoByCstGuid(XsCstSearchOption bean);
	/**
	 * ��ȡ�ͻ������ô���
	 * @param bean
	 * @return
	 */
	public int getVisitTimes(XsCstSearchOption bean);
	/**
	 * ��ȡ�ͻ��״���������
	 * @param bean
	 * @return
	 */
	public String getFirstVisitTime(XsCstSearchOption bean);
	/**
	 * �����ֻ��Ų�ѯ�ͻ�����
	 * @param tel
	 * @return
	 */
	public List<XsCstInfo> getXsCstBelongTo(String tel);
	/**
	 * ���¿ͻ���Ϣ
	 * @param bean
	 * @return
	 */
	public String updateXsCstInfo(XsCstInfo bean);
	/**
	 * ���¿ͻ��Ļ�����Ϣ
	 * @param bean
	 * @return
	 */
	public int updateXsOppInfo(XsOppTableEntity bean);
	/**
	 * �����ͻ��ĸ�����Ϣ
	 * @param bean
	 * @return
	 */
	
	public int insertXsCstFollowInfo(XsOpp2GjjlTableEntity bean);
	/**
	 * ��ѯԱ����Ϣ
	 * @param bean
	 * @return
	 */
	public XsEmployeeTableEntity getXsEmployeeInfo(XsEmployeeTableEntity bean);
	/**
	 * ��ѯ�ͻ���Ϣ
	 * @param bean
	 * @return
	 */
	public XsCstTableEntity getXsCstBasicInfo(XsCstTableEntity bean);
	/**
	 * ��ѯ������Ϣ
	 * @param bean
	 * @return
	 */
	public XsOppTableEntity getXsOppInfo(XsOppTableEntity bean);
	/**
	 * �����ͻ�ʱ����ͻ���������Ϣ
	 * @param bean
	 * @return
	 */
	public int insertXsCstAllInfo(XsCstInfo bean);
	/**
	 * ����ͻ���Ϣ�޸���־(�޸��ֻ���ʱ����)
	 * @param vo
	 * @return
	 */
	public int insertXsCstModifyLog(ZsBusiCustomLogTable vo);
	/**
	 * ��ȡ�´θ���������
	 * @return
	 */
	public String getNextFollowDate();
	/**
	 * 
	 * @return
	 */
	public int updateXsCstAndInsertOtherInfo(XsCstInfo bean);
	/**
	 * 
	 * @return
	 */
	public String checkCusBeFollowedByEmployee();
	/**
	 * ������пͻ��ĵ绰����
	 * @return
	 */
	public   List<Map<String, Object>> getTel_allCustomer();
	/**
	 * ��ÿͻ�������״̬
	 * @param bean
	 * @return
	 */
	public XsOppTableEntity getStatusFlgFromOppOrtunity(String oppguid);
	/**
	 * 
	 * @param projGuid
	 * @param cstGuid
	 */
	public XsCstInfo getXsCstInfoByCstGuidAndProjGuid(String projGuid,String cstGuid);
}
