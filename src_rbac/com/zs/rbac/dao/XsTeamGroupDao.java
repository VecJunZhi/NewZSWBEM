package com.zs.rbac.dao;

import java.util.List;

import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.web.vo.XsTeamGroupVo;

public interface XsTeamGroupDao {
	/**
	 * ���������Ŀ�б� 
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getProjectItem(XsTeamGroupVo vo);
	/**
	 * ���������Ŀ��Ӧ���Ŷӷ�����Ϣ
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getTeamGroupFromProject(XsTeamGroupVo vo);
	/**
	 * ��ö�Ӧ�����Ա��Ϣ
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getUserFromTeamGroup(XsTeamGroupVo vo);
	/**
	 * ����������ʱ���жϸ÷����Ƿ��Ѿ�����  
	 * @param vo
	 * @return
	 */
	public int judgeTeamGroupIfExist(XsTeamGroupVo vo);
	/**
	 * ����Ӧ��Ŀ���ӷ���
	 * @param vo
	 * @return
	 */
	public int insertTeamGroup(XsTeamGroupVo vo);
	/**
	 * �޸Ķ�Ӧ��Ŀ�ķ���
	 * @param vo
	 * @return
	 */
	public int updateTeamGoup(XsTeamGroupVo vo);
	/**
	 * ɾ����Ӧ��Ŀ�ķ���
	 * @param vo
	 * @return
	 */
	public int deleteTeamGoup(XsTeamGroupVo vo);
	/**
	 * ��������Աʱ���жϸó�Ա�Ƿ��Ѿ������ڸ���Ŀ����
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> judgeUserIfExistInTeamGroup(XsTeamGroupVo vo);
	/**
	 * ���������Ա 
	 * @param vo
	 * @return
	 */
	public int insertUserToTeamGroup(XsTeamGroupVo vo);
	/**
	 * �༭�����Ա  ���ó�Ա�Ƶ�������   ���ó�Ա����Ϊ�鳤����Ա -
	 * @param vo
	 * @return
	 */
	public int updateUserInTeamGroup(XsTeamGroupVo vo);
	/**
	 *  �Ƴ������Ա
	 * @param vo
	 * @return
	 */
	public int deleteUserFromTeamGroup(XsTeamGroupVo vo);
}
