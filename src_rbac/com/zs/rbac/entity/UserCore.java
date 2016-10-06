package com.zs.rbac.entity;

/**
 * 用户相关类
 * @author JiaRui
 *
 */
public class UserCore {
	
	private User user;
	
	private Resource resource;
	
	//private Organization organization;

	/**
	 * 得到用户相关信息
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 设置用户相关信息
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 得到用户相关资源信息<br>
	 * 包括用户拥有子系统、目录、权限等<br>
	 * @return Resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * 设备用户相关资源信息
	 * @param resource
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * 得到用户组织机构信息
	 * @return
	 */
//	public Organization getOrganization() {
//		return organization;
//	}

	/**
	 * 设置用户组织机构信息
	 * @param organization
	 */
//	public void setOrganization(Organization organization) {
//		this.organization = organization;
//	}
	
	
}
