package com.zs.rbac.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.rbac.dao.OrganizationMapper;
import com.zs.rbac.entity.Organization;
import com.zs.rbac.service.IOrganizationService;
@Service
public class OrganizationServiceImpl implements IOrganizationService {

	@Autowired
	private OrganizationMapper orgdao;

	@Override
	public int deleteByPrimaryKey(Integer orgid) {
		
		Organization org=new Organization();
		org.setOrgid(orgid);
		int _int=orgdao.deleteByPrimaryKey(org);
		return _int;
	}

	@Override
	public int insert(String orgname, Integer orgtype, String description,
			Integer parentid, String priority, Integer available,
			Date createtime, Date lasttime) {
		Organization org=new Organization(orgname, orgtype, description, parentid, priority, available, createtime, lasttime);
		int _int=orgdao.insert(org);
		return _int;
	}

	@Override
	public List<Organization> selectBySelective(Integer orgid, String orgname,
			Integer orgtype, String description, Integer parentid,
			String priority, Integer available, Date createtime, Date lasttime) {
		Organization org=new Organization(orgid, orgname, orgtype, description, parentid, priority, available, createtime, lasttime);
		List<Organization>list=orgdao.selectBySelective(org);
		return list;
	}

/*	@Override
	public int updateByPrimaryKeySelective(Integer orgid, String orgname,
			Integer orgtype, String description, Integer parentid,
			String priority, Integer available, Date createtime, Date lasttime) {
		// TODO Auto-generated method stub
		Organization org=new Organization(orgid, orgname, orgtype, description, parentid, priority, available, createtime, lasttime);
		int _int=orgdao.updateByPrimaryKeySelective(org);
		return _int;
	}*/
	@Override
	public int updateByPrimaryKeySelective(Integer orgid, String orgname,Integer available,Integer parentid) {
		// TODO Auto-generated method stub
		Organization org=new Organization();
		org.setOrgid(orgid);
		org.setOrgname(orgname);
		org.setAvailable(available);
		org.setParentid(parentid);
		int _int=orgdao.updateByPrimaryKeySelective(org);
		return _int;
	}

	@Override
	public List<Organization> selectBySelective(Organization org) {
		// TODO Auto-generated method stub
		List<Organization> list=orgdao.selectBySelective(org);
		return list;
	}



}
