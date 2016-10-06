package com.zs.rbac.service;

import java.util.List;

import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.entity.UserExt;

public interface XsTeamGroupService {
	/**
	 * ���crm�������û����б���Ϣ 
	 * @param userName :���Ը���username����ģ������
	 * @return
	 */
	public List<XsTeamGroupEntity> getUsersFromXsCRM(String userName);
	/**
	 * �����Ҫ�󶨵���Ա����
	 * @param username 
	 * @param propery : ָ���û���չ���е���������
	 * @return
	 */
	public List<XsTeamGroupEntity> getPreConnectUsers(String userName ,String property);
	public List<XsTeamGroupEntity> getPreConnectUsers(String userName,
			String property,int startLength,int rows,String sidx,String sord) ;
	/**
	 * ����Ѿ��󶨵���Ա����
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getPreConnectUsersByUserId(String userId,String property);
	public List<XsTeamGroupEntity> getConnectedUsers(String userName ,String property);
	public List<XsTeamGroupEntity> getConnectedUsers(String userName,
			String property,int startLength,int rows,String sidx,String sord);
	/**
	 * ��rbac �û��� crm �����û����а� 
	 * @param userId �û�id
	 * @param userName���û�����
	 * @param property��������
	 * @param value��������ֵ
	 * @param description������������
	 * @return
	 */
	public int insertUserConnectExt(List<XsTeamGroupVo> list);
	/**
	 *  �����
	 * @param id:�û���չ���е�������id
	 * @return
	 */
	public int deleteUserConnect(String id);
	/**
	 * ���������Ŀ�б� 
	 * @param �� �����б�����ȡ���е���Ŀ
	 * @return
	 */
	public List<XsTeamGroupEntity> getProjectItem();
	/**
	 * ���������Ŀ��Ӧ���Ŷӷ�����Ϣ
	 * @param �ޣ����б�����ȡÿ����Ŀ��Ӧ����Ŀ����
	 * @return
	 */
	public List<XsTeamGroupEntity> getTeamGroupFromProject(String projectId,String isProjectAdmin,String groupType);
	public List<XsTeamGroupEntity> getTeamGroupFromProject(String projectId);
	public List<XsTeamGroupEntity> getAdminTeamGroupFromProject(String projectId,String isProjecAdmin,String groupType);
	public List<XsTeamGroupEntity> getTeamGroupFromProjectById(String id);
	/**
	 * ��ö�Ӧ�����Ա��Ϣ
	 * @param teamGroupId : ��Ŀ���id��
	 * @param projectId �� ��Ŀ��id��
	 * @return
	 */
	public List<XsTeamGroupEntity> getUserFromTeamGroup(String teamGroupId,String projectId,String userName,String sidx,String sord,String property);
	public List<XsTeamGroupEntity> getUserFromTeamGroup(int page,int rows,String teamGroupId,String projectId,String userName,String sidx,String sord,String property);
	public List<XsTeamGroupEntity> getUserFromTeamGroup(XsTeamGroupVo vo);
	/**
	 * ����������ʱ���жϸ÷����Ƿ��Ѿ�����  
	 * @param groupName : ����ķ�������
	 * @param projectId �� ��Ŀ��id��
	 * @return��0 ˵�������ڣ���0 ˵�������Ѿ�����
	 */
	public int judgeTeamGroupIfExist(String groupName,String projectId);
	/**
	 *  ����Ӧ��Ŀ���ӷ���
	 * @param groupName : ��������Ŀ��������
	 * @param projectId ����Ŀid
	 * @param isProjectAdmin���Ƿ�Ϊ��Ŀ����Ա
	 * @param description������
	 * @return
	 */
	public int insertTeamGroup(String groupName,String projectId,String isProjectAdmin,String description,String groupType,String projectName);
	/**
	 * �޸Ķ�Ӧ��Ŀ�ķ���
	 * @param vo
	 * @return
	 */
	public int updateTeamGoup(String teamGrouId,String groupName);
	/**
	 * ɾ����Ӧ��Ŀ�ķ���
	 * @param vo
	 * @return
	 */
	public int deleteTeamGoup(String teamGrouId,String projectId,String groupType);
	/**
	 * ��������Աʱ���жϸó�Ա�Ƿ��Ѿ������ڸ���Ŀ����
	 * @param userId ��ѡ�е��û���id��
	 * @param teamGroupId ����Ӧ����Ŀ�����id��
	 * @return 0 ˵�������ڣ���0 ˵�������Ѿ�����
	 */
	public List<XsTeamGroupEntity> judgeUserIfExistInTeamGroup(String[] userId,String teamGroupId);
	/**
	 * ���������Ա 
	 * @param userId :�û�id
	 * @param teamGroupId������id
	 * @param userLevelId���û��ȼ� �鳤/��Ա
	 * @param description ������
	 * @return
	 */
	public int insertUserToTeamGroup(List<XsTeamGroupVo> vo);
	/**
	 * �༭�����Ա  ���ó�Ա�Ƶ�������   ���ó�Ա����Ϊ�鳤����Ա 
	 * @param id:Ҫ�޸ĵ��û����û�_�Ŷӷ�����е�������id
	 * @param teamGroupId �� �Ŷӷ����id�ţ��޸Ĵ�ֵ���Խ���Ա�Ƶ���������
	 * @param userLevelId�� �û��ļ����鳤/��Ա
	 * @param description������
	 * @return
	 */
	public int updateUserInTeamGroup(String id,String teamGroupId,String userLevelId,String description);
	/**
	 *  �Ƴ������Ա
	 * @param id �û����û�_�Ŷӷ�����е�������id
	 * @return
	 */
	public int deleteUserFromTeamGroup(String id);
	/**
	 * �ж�ϵͳ�Ƿ�������ۻ�����ϵͳ
	 * @param userExt
	 * @return
	 */
	public List<UserExt> judgeIfCorSystem(String utid,String property);
	
}
