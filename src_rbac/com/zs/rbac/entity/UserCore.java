package com.zs.rbac.entity;

/**
 * �û������
 * @author JiaRui
 *
 */
public class UserCore {
	
	private User user;
	
	private Resource resource;
	
	//private Organization organization;

	/**
	 * �õ��û������Ϣ
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * �����û������Ϣ
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * �õ��û������Դ��Ϣ<br>
	 * �����û�ӵ����ϵͳ��Ŀ¼��Ȩ�޵�<br>
	 * @return Resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * �豸�û������Դ��Ϣ
	 * @param resource
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * �õ��û���֯������Ϣ
	 * @return
	 */
//	public Organization getOrganization() {
//		return organization;
//	}

	/**
	 * �����û���֯������Ϣ
	 * @param organization
	 */
//	public void setOrganization(Organization organization) {
//		this.organization = organization;
//	}
	
	
}
