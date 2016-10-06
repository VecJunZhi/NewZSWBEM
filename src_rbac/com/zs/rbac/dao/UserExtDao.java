package com.zs.rbac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.entity.UserExt;
@Repository
public interface UserExtDao {
	/**
	 * �����Ҫ�󶨵���Ա����
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getPreConnectUsers(XsTeamGroupVo vo);
	/**
	 * ����Ѿ��󶨵���Ա����
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getConnectedUsers(XsTeamGroupVo vo);
	/**
	 * ��rbac �û��� crm �����û����а� 
	 * @param vo
	 * @return
	 */
	public int insertUserConnectExt(XsTeamGroupVo vo);
	/**
	 *  �����
	 * @param vo
	 * @return
	 */
	public int deleteUserConnect(XsTeamGroupVo vo);
	
	
	/**
	 * �����û�Id��ѯ�û���չ��Ϣ
	 * @param userID
	 * @return
	 */
	public List<UserExt> getUsetExtById(int userId);
	/**
	 * �ж�ϵͳ�Ƿ�������ۻ�����ϵͳ
	 * @param userExt
	 * @return
	 */
	public List<UserExt> judgeIfCorSystem(UserExt userExt);
	/**
	 * ��ѯ��չ��Ϣ
	 * @param userExt
	 * @return
	 */
	public List<UserExt> getUserExtInfoDao(UserExt userExt);
	/**
	 * ������չ��Ϣ
	 * @param userExt
	 * @return
	 */
	public int insertUserExtInfoDao(UserExt userExt);
	/**
	 * ������չ��Ϣ
	 * @param userExt
	 * @return
	 */
	public int updateUserExtInfoDao(UserExt userExt);

}
