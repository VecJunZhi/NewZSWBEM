package com.zs.rbac.dao;

import com.zs.rbac.entity.UserOrganization;

public interface UserOrganizationMapper {
    int deleteByPrimaryKey(Integer uugid);

    int insert(UserOrganization record);

    int insertSelective(UserOrganization record);

    UserOrganization selectByPrimaryKey(Integer uugid);

    int updateByPrimaryKeySelective(UserOrganization record);

    int updateByPrimaryKey(UserOrganization record);
}