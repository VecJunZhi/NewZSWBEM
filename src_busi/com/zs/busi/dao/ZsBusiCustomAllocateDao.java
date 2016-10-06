package com.zs.busi.dao;

import java.util.List;

import com.zs.busi.entity.ZsBusiCustomAllocateEntity;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsCustomFollowRecordTableVo;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsDictionaryTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.entity.ZsIntentionTableVo;
import com.zs.busi.web.vo.ZsBusiCustomAllocateVo;
import com.zs.rbac.entity.User;

public interface ZsBusiCustomAllocateDao {
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
	 * ��ø����пͻ�����
	 * @return
	 */
	public int getZsCustCountDao(ZsBusiCustomAllocateVo vo);
	/**
	 * ��ù�����������ͻ�����
	 * @return
	 */
	public int getZsCustPubDupCountDao(ZsBusiCustomAllocateVo vo);
	/**
	 * ���ݿͻ�id���ҵ��ͻ��Ļ�����Ϣ
	 */
	public ZsCustomTableVo getCustomInfoByCusId(ZsInfoVo vo);

	/**
	 * ����ҵ��Ա����ģ������ҵ��Ա�б�
	 * @param employeeName
	 * @return
	 */
	public List<User> getZsEmployeeList(ZsInfoVo vo);
	/**
	 * ����(����)
	 * String ΪcusId
	 * @param list
	 * @return
	 */
	public int allocateCustomers(ZsInfoVo vo);
	/**
	 * ����(����)--��Ϊ�����ͻ�(������)
	 * @param list
	 * @return
	 */
	public int reBackCustomers(ZsInfoVo vo);
	/**
	 * ������(������)
	 * @param list
	 * @return
	 */
	public int dusbinCustomers(ZsInfoVo vo);
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
	
}
