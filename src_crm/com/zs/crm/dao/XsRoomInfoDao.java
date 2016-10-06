package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsRoomCountInfoVo;
import com.zs.crm.entity.XsRoomInfoVo;
import com.zs.crm.entity.XsRoomResourceInfoVo;
import com.zs.crm.entity.XsRoomSearchVo;
import com.zs.crm.entity.XsUnitInfoVo;
@Repository
public interface XsRoomInfoDao {
	/**
	 * ��ȡĳ��Ԫ�����з�Դ��Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsUnitInfoVo> getXsRoomInfoByUnitNoDao(XsRoomSearchVo bean);
	/**
	 * ����¥����ȡ��¥���µ�ͳ����Ϣ�����ۡ�δ�ۡ����صȣ�
	 * @param bean
	 * @return
	 */
	public XsRoomCountInfoVo getXsCountInfoByBulidNoDao(XsRoomSearchVo bean);
	/**
	 * ����¥����ȡ��Ԫ��Ϣ������Ԫ���ƣ�
	 * @param bean
	 * @return
	 */
	public List<String> getXsUnitInfoByBulidNoDao(XsRoomSearchVo bean);
	/**
	 * ���ݷ��Ų�ѯ��������л�����Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsRoomResourceInfoVo> getXsRoomInfoByRoomNoDao(XsRoomSearchVo bean);
	/**
	 * ��ǰ��Ŀ����¥����ʣ������
	 * @param bean
	 * @return
	 */
	public List<XsRoomInfoVo> getXsBldNameAndCountInfoDao(XsRoomSearchVo bean);
	/**
	 * ��ѯ����Ŀ�µ�¥��ͳ����Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsRoomCountInfoVo> getXsBldInfoByProNoDao(XsRoomSearchVo bean);
}
