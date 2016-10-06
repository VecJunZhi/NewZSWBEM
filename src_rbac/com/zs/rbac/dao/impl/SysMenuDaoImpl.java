package com.zs.rbac.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zs.rbac.dao.ISysMenuDao;
import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.SubSystem;
import com.zs.rbac.utils.JdbcTemplateUtils;
@Repository
public class SysMenuDaoImpl  implements ISysMenuDao {
	
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	
/*	private JdbcTemplate jdbcTemplate;
	
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}*/
	
	@Override
	public List<Menu> getMenuByUserID(int userID) {
		String sql = "select DISTINCT p.* from zs_rbac_permission p LEFT JOIN zs_rbac_role_permission rp ON p.PermissionID=rp.PermissionID " 
		+ "LEFT JOIN zs_rbac_user_role ur ON rp.RoleID=ur.RoleID LEFT JOIN zs_rbac_user u on ur.UserID=u.UserID  WHERE u.UStatus=1 and u.userid=? and "
		+ "u.Locked=0 and p.PermissionType='menu' and p.Available=1 ORDER BY Priority";
		return jdbcTemplate.query(sql,new Object[]{userID},new RowMapper<Menu>(){
			@Override
			public Menu mapRow(ResultSet rs,int rowNum)throws SQLException {  
                Menu menu = new Menu();
                menu.setMenuId(rs.getInt("PermissionID"));
                menu.setMenuName(rs.getString("PermissionName"));
                menu.setMenuURL(rs.getString("URL"));
                menu.setPerentMenuId(rs.getInt("ParentID"));
                menu.setAvailable(rs.getBoolean("Available"));
                menu.setMenuDesc(rs.getString("Description"));
                menu.setMenuMark(rs.getString("PermissionMark"));
                return menu;  
            }
		});
	}
	
	public List<SubSystem> getSubSystemByUserID(int userID){
		String sql = "select p.* from zs_rbac_permission p LEFT JOIN zs_rbac_role_permission rp ON p.PermissionID=rp.PermissionID " 
		+ "LEFT JOIN zs_rbac_user_role ur ON rp.RoleID=ur.RoleID LEFT JOIN zs_rbac_user u on ur.UserID=u.UserID  WHERE u.UserID=? and "
		+ "u.Locked=0 and p.PermissionType='subSystem' and p.Available=1 and p.permissionID in ( "
		+ "select p.parentID from zs_rbac_permission p LEFT JOIN zs_rbac_role_permission rp ON p.PermissionID=rp.PermissionID LEFT JOIN zs_rbac_user_role ur ON rp.RoleID=ur.RoleID LEFT JOIN zs_rbac_user u on ur.UserID=u.UserID  WHERE u.UStatus=1 and u.userid=? and u.Locked=0 and p.PermissionType='menu' and p.Available=1"
		+ ") GROUP BY p.permissionID,p.Available,p.CreateTime,p.Description,p.LastTime,p.ParentID,p.PermissionMark,p.PermissionName,p.PermissionType,p.Priority,p.URL ORDER BY Priority";
		return jdbcTemplate.query(sql,new Object[]{userID,userID},new RowMapper<SubSystem>(){
			@Override
			public SubSystem mapRow(ResultSet rs,int rowNum)throws SQLException {  
				SubSystem sub = new SubSystem();
				sub.setSubSystemId(rs.getInt("PermissionID"));
				sub.setSubSystemName(rs.getString("PermissionName"));
				sub.setSubSystemURL(rs.getString("URL"));
				sub.setAvailable(rs.getBoolean("Available"));
				sub.setSubSystemDesc(rs.getString("Description"));
                return sub;  
            }
		});
	}
	


}
