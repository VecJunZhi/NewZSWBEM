package com.zs.rbac.dao;

import com.zs.rbac.entity.UserUserGroup;

public interface UserUserGroupMapper {
    int deleteByPrimaryKey(Integer uugid);

    int insert(UserUserGroup record);

    int insertSelective(UserUserGroup record);

    UserUserGroup selectByPrimaryKey(Integer uugid);

    int updateByPrimaryKeySelective(UserUserGroup record);

    int updateByPrimaryKey(UserUserGroup record);
}