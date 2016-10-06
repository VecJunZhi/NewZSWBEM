package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.busi.entity.ZsInfoVo;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.web.vo.XsCustomersManagerVo;
@Repository
public interface XsCustomAllocateDao {
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
	 * ��ø����пͻ�����
	 * @return
	 */
	public int getZsCustCountDao(XsCustomersManagerVo vo);
	/**
	 * ��ù�����������ͻ�����
	 * @return
	 */
	public int getZsCustPubDupCountDao(XsCustomersManagerVo vo);
	/**
	 * ���ݿͻ�id���ҵ��ͻ��Ļ�����Ϣ
	 */
	public XsCustomersManagerEntity getCustomInfoByCusId(XsCustomersManagerVo vo);

	/**
	 * ����ҵ��Ա����ģ������ҵ��Ա�б�
	 * @param employeeName
	 * @return
	 */
	public List<XsCustomersManagerEntity> getZsEmployeeList(XsCustomersManagerVo vo);
	/**
	 * ���ݻ���id��ȡ��ҵ���ʵ������Ϣ
	 * @param vo
	 * @return
	 */
	public XsCustomersManagerEntity getZygwByoppGUID(XsCustomersManagerVo vo);
	
	/**
	 * ����(����)
	 * String ΪcusId
	 * @param list
	 * @return
	 */
	public int allocateCustomers(XsCustomersManagerVo vo);
	/**
	 * ����(����)--��Ϊ�����ͻ�(������)
	 * @param list
	 * @return
	 */
	public int reBackCustomers(XsCustomersManagerVo vo);
	/**
	 * ������(������)
	 * @param list
	 * @return
	 */
	public int dusbinCustomers(XsCustomersManagerVo vo);
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
	/**
	 * �Զ����»���״̬ ��һ��
	 * @return
	 */
	public int autoUpdateOppStatus(XsCustomersManagerVo vo);
	public List<XsCustomersManagerEntity>selectAutoUpdateOppStatus(XsCustomersManagerVo vo);
}
