package com.zs.busi.service;

import java.util.List;
import java.util.Map;

import com.zs.busi.entity.ZsBusiCustomAllocateEntity;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.web.vo.ZsBusiCustomAllocateVo;
import com.zs.oa.entity.OaUserEntity;
import com.zs.rbac.entity.User;

public interface ZsBusiCustomAllocateService {
//�����пͻ�

	/**
	 * ������ڿͻ� ������Ч�ͻ����һ��������¼��ʱ��ڵ�+��������С�ڵ�ǰ����
	 * @param startIndex
	 * @param len
	 * @return
	 * �߼�����
	 */
	public List<ZsBusiCustomAllocateEntity> getZsOverdueCustInfoDao(ZsBusiCustomAllocateVo vo);
	/**
	 * �����Ч�ͻ����ͻ���Ϣ������һ���ֶ�Ϊ�Ƿ���Ч��ȡ����Ч�Ŀͻ�
	 * @param startIndex
	 * @param len
	 * @return
	 * �߼�����
	 */
	public List<ZsBusiCustomAllocateEntity> getZsCusInvalidDao(ZsBusiCustomAllocateVo vo);
	/**
	 * ��ø����ͻ���
	 * @param startIndex
	 * @param len
	 * @return
	 * �߼�����
	 */
	public List<ZsBusiCustomAllocateEntity> getZsCustFollowingDao(ZsBusiCustomAllocateVo vo);
	/**
	 * ��ø����ͻ�����ҳ��
	 * @param startIndex
	 * @param len
	 * @return
	 * �߼�����
	 */
	public List<ZsBusiCustomAllocateEntity> getZsCustFollowingDao_withoutPage(ZsBusiCustomAllocateVo vo);
	/**
	 * ��ù����ͻ����Զ����� ���ڿͻ����������Զ�����     �����ֵ������ù����ͻ�����
	 * 				     ��Ч�ͻ��������������Զ�����
	 *          �ֶ����գ����Խ������еĿͻ��ֶ�����
	 * @param startIndex
	 * @param len
	 * @return
	 * �߼�����
	 */
	public List<ZsBusiCustomAllocateVo> getZsCustPublicDao(ZsBusiCustomAllocateVo vo);
	/**
	 * ���������ͻ����ӹ����ͻ����Ƴ��Ŀͻ����Ե������� ,���ֵ����������������
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<ZsBusiCustomAllocateVo> getZsCustDusBinDao(ZsBusiCustomAllocateVo vo);
	
	/**
	 * ��ÿͻ�����--���ڿͻ�/��Ч�ͻ�/�����пͻ�
	 * @return
	 */
	public int getZsCustCountDao(String desc,ZsBusiCustomAllocateVo vo);
	/**
	 * ��ù�����������ͻ�����
	 * @return
	 */
	public int getZsCustPubDupCountDao(String desc,ZsBusiCustomAllocateVo vo);
	/**
	 * ���ݿͻ�id���ҵ��ͻ��Ļ�����Ϣ
	 */
	public ZsCustomTableVo getCustomInfoByCusId(String cusId);
	/**
	 * ���ݿͻ�id���ҵ��ͻ��ı����¼,������־��������־
	 */
	public List<ZsBusiCustomLogTable> getCusRecordLogByCusId(ZsBusiCustomLogTable vo);
	/**
	 * ��ÿͻ���������ԭ��������ʱ��
	 * @param vo
	 * @return
	 */
	public ZsBusiCustomLogTable getCusReBackInfoByCusId(String cusId,String logType);
	/**
	 * ��ÿͻ��Ļ��մ�����
	 * @param vo
	 * @return
	 */
	public int getCusReBackCountByCusId(String cusId,String logType);
	/**
	 * ����ҵ��Ա����ģ������ҵ��Ա�б�
	 * @param employeeName
	 * @return
	 */
	public List<User> getEmployeeList(String employeeName);
	public List<User> getEmployeeList(ZsInfoVo vo);
	/**
	 * ��������(����)
	 * String ΪcusId
	 * @param list
	 * @return
	 */
	public int allocateCustomers(String employeeId,String cusId,String proId);
	/**
	 * ��������(����)--��Ϊ�����ͻ�(������)
	 * @param list
	 * @return
	 */
	public int reBackCustomers(String cusId,String cstName,String tel,String reason,String fromwhere,String proId,Map<String, OaUserEntity> map);
	/**
	 * �Ƶ�������(������)
	 * @param list
	 * @return
	 */
	public int dusbinCustomers(String employeeId,String cusId,String fromwhere,String proId);
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
	public int dusbinCustomers(String cusId);
	/**
	 * �Զ��������ڿͻ�
	 * @return
	 */
	public List<ZsBusiCustomAllocateEntity> autoRebackOverCustomer(ZsInfoVo option);
	/**
	 * �Զ�������Ч�ͻ�
	 * @return
	 */
	public List<ZsBusiCustomAllocateEntity> autoRebackInvalidCustomer(ZsInfoVo option);
	/**
	 * ͨ���ͻ�id����û�����--�ӿͻ�����������
	 * @param option
	 * @return
	 */
	public ZsBusiCustomAllocateEntity getUserGuidBycstId(ZsInfoVo option);
	public void transTest();
}
