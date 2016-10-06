/*package com.zs.rbac.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.zs.rbac.dao.PermissionDao;
import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.Permission;
import com.zs.rbac.entity.Role;
import com.zs.rbac.utils.JdbcTemplateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

*//**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 *//*

public class PermissionDaoImpl implements PermissionDao {

    JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
    @Override
    public Permission createPermission(final Permission permission) {
        final String sql = " ";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql,  new String[] { "permissionID" });
                psst.setString(1, permission.getPermissionName());
                psst.setString(2, permission.getPermissionMark());
                psst.setString(3, permission.getPermissionType());
                psst.setString(4, permission.getParentID());
                psst.setString(5, permission.getUrl());
                psst.setInt(6, permission.getPriority());
                psst.setBoolean(7, permission.getAvailable());
                psst.setString(8, permission.getCreateTime());
                psst.setString(9, permission.getLastTime());
                psst.setString(10, permission.getDescription());
                return psst;
            }
        }, keyHolder);
        permission.setPermissionID(keyHolder.getKey().intValue());

        return permission;
    }
    @Override
    public void deletePermission(int permissionId) {
       
    	String sql = "delete from zs_rbac_role_permission where permissionID=?";
        jdbcTemplate.update(sql, permissionId);
        sql = "delete from zs_rbac_permission where permissionID=?";
        jdbcTemplate.update(sql, permissionId);
        
    }

	@Override
	public Permission updatePermission(Permission permission) {
		
		return null;
	}

	@Override
	public Permission queryPermission(Permission permission) {
		return null;
	}
	@Override
	public List<Permission> getPermission() {
		String sql = "select * from zs_rbac_permission";
		List<Permission> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<Permission>(Permission.class));
		return list;
	}
	@Override
	public List<Role> getRoleByPermission(Permission permission) {
		String sql = "select r.* from zs_rbac_role r, zs_rbac_permission p,zs_rbac_role_permission rp where p.permissionId=? and r.roleID=rp.roleID and p.permissionID=rp.permissionID";
		List<Role> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class), permission.getPermissionID());
		return list;
	}
	
}
*/