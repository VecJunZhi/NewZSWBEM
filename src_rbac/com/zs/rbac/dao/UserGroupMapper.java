package com.zs.rbac.dao;

import com.zs.rbac.entity.UserGroup;

public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer groupid);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer groupid);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}