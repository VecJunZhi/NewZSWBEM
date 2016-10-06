package com.zs.rbac.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.zs.rbac.dao.RoleDao;
import com.zs.rbac.entity.Permission;
import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.User;
import com.zs.rbac.utils.CommonUtils;
import com.zs.rbac.utils.JdbcTemplateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */

public class RoleDaoImpl {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	/*private JdbcTemplate jdbcTemplate;
	
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}*/

    public Role createRole(final Role Role) {
        final String sql = "insert into zs_rbac_role(RoleName, Available, Priority,CreateTime,LastTime,Description) values(?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[] { "roleID" });
                psst.setString(1, Role.getRoleName());
                psst.setBoolean(2, Role.getAvailable());
                psst.setString(3, Role.getPriority());
                psst.setString(4, Role.getCreateTime());
                psst.setString(5, Role.getLastTime());
                psst.setString(6, Role.getDescription());
                return psst;
            }
        }, keyHolder);
        Role.setRoleID(keyHolder.getKey().intValue());

        return Role;
    }

    public void deleteRole(int roleId) {
        //首先把和role关联的相关表数据删掉
        String sql = "delete from zs_rbac_User_Role where roleID=?";
        jdbcTemplate.update(sql, roleId);

        sql = "delete from zs_rbac_Role where roleID=?";
        jdbcTemplate.update(sql, roleId);
    }

   

	
	public void updateRole(Role role) {
		
	}
	
	public List<Role> getRoles(Role role) {
		String sql = "select * from zs_rbac_role";
		List<Role> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
		return list;
	}

	/*@Override
	public void correlationRole_Permissions(int roleId, int... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "insert into zs_rbac_role_permission(roleID, permissionID) values(?,?)";
        //这里需要加入事务支持，避免中途出错。
        for(int permissionId : permissionIds) {
            if(!CommonUtils.exists("zs_rbac_role_permission","roleId","permissionId",roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
    }

	@Override
	public void uncorrelationRole_Permissions(int roleId, int... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        
        String sql = "delete from zs_rbac_role_permission where roleID=? and permissionID=?";
        //这里需要加入事务支持，避免中途出错。
        for(int permissionId : permissionIds) {
            if(!CommonUtils.exists("zs_rbac_role_permission","roleId","permissionId",roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
    }

	@Override
	public List<Permission> getPermissionByRole(Role role) {
		String sql = "select p.* from zs_rbac_role r, zs_rbac_permission p,zs_rbac_role_permission rp where r.roleName=? and r.roleID=rp.roleID and p.permissionID=rp.permissionID";
		List<Permission> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<Permission>(Permission.class), role.getRoleName());
		return list;
	}

	

	@Override
	public List<User> getUsersByRole(Role role) {
		String sql = "select u.* from zs_rbac_user u, zs_rbac_role r,zs_rbac_user_role ur where r.roleName=? and u.userID=ur.userID and r.roleID=ur.roleID";
		List<User> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), role.getRoleName());
		return list;
	}*/

/*	@Override
	public List<UserGroup> getUserGroupByRole(Role role) {
		String sql = "select g.* from zs_rbac_usergroup g, zs_rbac_role r,zs_rbac_role_usergroup rg where r.roleName=? and r.roleID=rg.roleID and g.groupID=rg.groupID";
		List<UserGroup> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserGroup>(UserGroup.class), role.getRoleName());
		return list;
	}*/

}
