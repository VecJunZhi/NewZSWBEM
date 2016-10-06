package com.zs.crm.service;

import java.util.List;
import java.util.Map;

import com.zs.busi.entity.ZsBusiCustomAllocateEntity;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.web.vo.ZsBusiCustomAllocateVo;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.web.vo.XsCustomersManagerVo;
import com.zs.oa.entity.OaUserEntity;

public interface XsCustomAllocateService {
	//�����пͻ�
	
		/**
		 * ������ڿͻ� ������Ч�ͻ����һ��������¼��ʱ��ڵ�+��������С�ڵ�ǰ����
		 * @param startIndex
		 * @param len
		 * @return
		 * �߼�����
		 */
		public List<XsCustomersManagerEntity> getZsOverdueCustInfoDao(XsCustomersManagerVo vo);
		/**
		 * �����Ч�ͻ����ͻ���Ϣ������һ���ֶ�Ϊ�Ƿ���Ч��ȡ����Ч�Ŀͻ�
		 * @param startIndex
		 * @param len
		 * @return
		 * �߼�����
		 */
		public List<XsCustomersManagerEntity> getZsCusInvalidDao(XsCustomersManagerVo vo);
		/**
		 * ��ø����ͻ���
		 * @param startIndex
		 * @param len
		 * @return
		 * �߼�����
		 */
		public List<XsCustomersManagerEntity> getZsCustFollowingDao(XsCustomersManagerVo vo);
		/**
		 * ��ù����ͻ����Զ����� ���ڿͻ����������Զ�����     �����ֵ������ù����ͻ�����
		 * 				     ��Ч�ͻ��������������Զ�����
		 *          �ֶ����գ����Խ������еĿͻ��ֶ�����
		 * @param startIndex
		 * @param len
		 * @return
		 * �߼�����
		 */
		public List<XsCustomersManagerEntity> getZsCustPublicDao(XsCustomersManagerVo vo);
		/**
		 * ���������ͻ����ӹ����ͻ����Ƴ��Ŀͻ����Ե������� ,���ֵ����������������
		 * @param startIndex
		 * @param len
		 * @return
		 */
		public List<XsCustomersManagerEntity> getZsCustDusBinDao(XsCustomersManagerVo vo);
		
		/**
		 * ��ÿͻ�����--���ڿͻ�/��Ч�ͻ�/�����пͻ�
		 * @return
		 */
		public int getZsCustCountDao(String desc,XsCustomersManagerVo vo);
		/**
		 * ��ÿͻ��Ļ��մ�����
		 * @param vo
		 * @return
		 */
		public int getCusReBackCountByCusId(String cusId,String logType);
		/**
		 * ��ÿͻ���������ԭ��������ʱ��
		 * @param vo
		 * @return
		 */
		public ZsBusiCustomLogTable getCusReBackInfoByCusId(String cusId,String logType);
		/**
		 * ��ù�����������ͻ�����
		 * @return
		 */
		public int getZsCustPubDupCountDao(String desc,XsCustomersManagerVo vo);
		/**
		 * ���ݿͻ�id���ҵ��ͻ��Ļ�����Ϣ
		 */
		public XsCustomersManagerEntity getCustomInfoByCusId(String cusId);
		/**
		 * ���ݿͻ�id���ҵ��ͻ��ı����¼,������־��������־
		 */
		public List<ZsBusiCustomLogTable> getCusRecordLogByCusId(ZsBusiCustomLogTable vo);
		/**
		 * ����ҵ��Ա����ģ������ҵ��Ա�б�
		 * @param employeeName
		 * @return
		 */
		public List<XsCustomersManagerEntity> getEmployeeList(String employeeName);
		/**
		 * ���ݻ���id��ȡ��ҵ���ʵ������Ϣ
		 * @param vo
		 * @return
		 */
		public XsCustomersManagerEntity getZygwByoppGUID(String  oppGUID);
		/**
		 * ��������(����)
		 * String ΪcusId
		 * @param list
		 * @return
		 */
		public int allocateCustomers(String userGuid2,String employeeId,String cusId,String proId,String oppGUID);
		/**
		 * ��������(����)--��Ϊ�����ͻ�(������)
		 * @param list
		 * @return
		 */
		public int reBackCustomers(String cusId,String cstName,String mobileTel,String reason,String fromwhere,String proId,String oppguid,Map<String, OaUserEntity> map);
		/**
		 * �Ƶ�������(������)
		 * @param list
		 * @return
		 */
		public int dusbinCustomers(String employeeId,String cusId);
		/**
		 * ������־��¼
		 * @param vo
		 * @return
		 */
		public int insertZsCusChangeLog(ZsBusiCustomLogTable vo);
		/**
		 * �ͻ��Ƶ�������
		 * @param vo
		 * @return
		 */
		public int dusbinCustomers(String cusId,String proId,String reason,String oppguid);
		/**
		 * �Զ��������ڿͻ�
		 * @return
		 */
		public List<XsCustomersManagerEntity> autoRebackOverCustomer(XsCustomersManagerVo option);
		/**
		 * �Զ�������Ч�ͻ�
		 * @return
		 */
		public List<XsCustomersManagerEntity> autoRebackInvalidCustomer(XsCustomersManagerVo option);
		/**
		 * ���ÿ����ҵ���ʵ����ڿͻ���ͳ����
		 * @return
		 */
		public List<XsCustomersManagerEntity> getZsOverdueCustCountByYwy();
		/**
		 * ���ÿ����ҵ���ʵ���Ч�ͻ���ͳ����
		 * @return
		 */
		public List<XsCustomersManagerEntity> getZsCusInvalidCustCountByYwy();
		/**
		 * ���ÿ����ҵ���ʵĸ����пͻ���ͳ����
		 * @return
		 */
		public List<XsCustomersManagerEntity> getZsCustFollowingCustCountByYwy();
		/**
		 * ���Ŀͻ�
		 * @param vo
		 * @return
		 */
		public int updateXsBasicInfo(XsCustomersManagerVo vo);
		
		public int autoUpdateOppStatus(XsCustomersManagerVo vo);
		//public List<XsCustomersManagerEntity>selectAutoUpdateOppStatus();
}
