package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsSaleDetailVo;
import com.zs.crm.entity.XsSaleManagerEntity;
import com.zs.crm.entity.XsSaleManagerShowEntity;
import com.zs.crm.entity.XsSignUpDetailVo;

/*��dao�����@repositoryע����Ϊ���Զ�ɨ��ע��ʱ����Щ����ע�룬�����Բ���marketMapper�ķ�ʽ����*/
@Repository
public interface XsSaleManagerDao {
	/**
	 * 
	 * @return
	 */
	public XsSaleManagerShowEntity getDayReportInfoDao();
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstByCycleAndFollowWayDao(XsSaleManagerEntity bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstByCycleAndStatusDao(XsSaleManagerEntity bean);
	/**
	 * ��ȡ�Ϲ���Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstSubscribeInfoByCycleDao(XsSaleManagerEntity bean);
	/**
	 * ��ȡתǩԼ��Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstSignUpInfoByCycleDao(XsSaleManagerEntity bean);
	/**
	 * ��ȡ��һ�����õ�����
	 * @param bean
	 * @return
	 */
	public String getFirstVisitCountByCycleDao(XsSaleManagerEntity bean);
	/**
	 * ����ʱ��λ�ȡ�Ϲ�����
	 * @param bean
	 * @return
	 */
	public XsSaleDetailVo getSaleDetailByCycleDao(XsSaleManagerEntity bean);
	/**
	 * ����ʱ��λ�ȡתǩԼ����
	 * @param bean
	 * @return
	 */
	public XsSignUpDetailVo getSignUpDetailByCycleDao(XsSaleManagerEntity bean);
}
