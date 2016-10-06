package com.zs.rbac.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.zs.rbac.dao.UserDao;
import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.User;
import com.zs.rbac.utils.CommonUtils;
import com.zs.rbac.utils.JdbcTemplateUtils;

/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	
	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	
//	public JdbcTemplate getJdbcTemplate() {  
//        return jdbcTemplate;  
//    } 
//	
//	@Resource
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
    Log log= LogFactory.getLog(UserDaoImpl.class);
    @Override
    public User createUser(final User user) {
        final String sql = "insert into zs_rbac_user(username,RealName,password,Mobile,EMail,LoginType,UStatus,Locked,CreateTime,LastTime,Description) values(?,?,?,?,?,?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[] { "userID" });
                psst.setString(1, user.getUsername());
                psst.setString(2, user.getRealName());
                psst.setString(3, user.getPassword());
                psst.setString(4, user.getMobile());
                psst.setString(5, user.getEmail());
                psst.setString(6, user.getLoginType());
                psst.setInt(7, user.getuStatus());
                psst.setBoolean(8, user.getLocked());
                psst.setString(9, user.getCreateTime());
                psst.setString(10, user.getLastTime());
                psst.setString(11, user.getDescription());
                return psst;
            }
        }, keyHolder);

        user.setUserID(keyHolder.getKey().intValue());
        return user;
    }
    @Override
    public void updateUser(User user) {
        String sql = "update zs_rbac_user set username=?,RealName=?,password=?,Mobile=?,EMail=?,LoginType=?,UStatus=?,Locked=?,CreateTime=?,LastTime=?,Description=? where userID=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getRealName(), user.getPassword(), user.getMobile(), user.getEmail(),user.getLoginType(), user.getuStatus(),user.getLocked(),user.getCreateTime(),user.getLastTime(), user.getDescription(),user.getUserID());
    }
    @Override
    public void updateUser(int userid,String password){
    	String sql = "update zs_rbac_user set password=? where userID=?";
        jdbcTemplate.update(sql, password,userid);
    
    }
    @Override
    public void deleteUser(int userId) {
        String sql = "delete from zs_rbac_user where userID=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void correlationUser_Roles(int userId, int... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "insert into zs_rbac_user_role(userID, roleID) values(?,?)";
        for(int roleId : roleIds) {
            if(!CommonUtils.exists("zs_rbac_user_role","userID","roleID",userId, roleId)) {
            	jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    @Override
    public void uncorrelationUser_Roles(int userId, int... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "delete from zs_rbac_user_role where userID=? and roleID=?";
        for(int roleId : roleIds) {
            if(CommonUtils.exists("zs_rbac_user_role","userID","roleID",userId, roleId)) {
            	jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    


    @Override
    public User findOne(int userId) {
        String sql = "select * from zs_rbac_user where userID=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User findByUsername(String username) {
    	log.info("username "+username);
    	String sql = "select * from zs_rbac_user where Mobile=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username);
        if(userList.size() == 0) {
            return null;
        }
        
        log.info(userList.get(0).getRealName());
        try {
        	//HttpSession session=SessionUtils.getInstance().getSession();
        	//log.info("sef "+httpSession);
           // httpSession.setAttribute("username", userList.get(0).getRealName());
           // log.info(httpSession.getAttribute("username"));
		} catch (Exception e) {
			log.info(e);
		}
        
        return userList.get(0);
    }

    @Override
    public Set<String> findRoles(String username) {
        String sql = "select r.roleName from zs_rbac_user u LEFT JOIN zs_rbac_user_role ur ON u.UserID=ur.UserID LEFT JOIN zs_rbac_role r ON ur.RoleID=r.RoleID WHERE r.Available=1 and (u.UserName=? or u.Mobile=? or u.EMail=?)";
        return new HashSet<String>(jdbcTemplate.queryForList(sql, String.class, username,username,username));
    }

    @Override
    public Set<String> findPermissions(String username) {
    	log.info("findPermissions");
    	//String sql = "select p.permissionMark from rbac_user u, rbac_role r, rbac_permission p, rbac_user_role ur, rbac_role_permission rp where u.Mobile=? and u.userID=ur.userID and r.roleID=ur.roleID and r.roleID=rp.roleID and p.permissionID=rp.permissionID";
        String sql = "select p.permissionMark from zs_rbac_user u LEFT JOIN zs_rbac_user_role ur ON u.UserID=ur.UserID LEFT JOIN zs_rbac_role r ON ur.RoleID=r.RoleID LEFT JOIN zs_rbac_role_permission rp ON rp.RoleID=r.RoleID LEFT JOIN zs_rbac_permission p ON p.PermissionID=rp.PermissionID WHERE p.Available=1 and (u.Mobile=? or u.EMail=? or u.UserName=?)";
    	return new HashSet<String>(jdbcTemplate.queryForList(sql, String.class, username,username,username));
    }
	
	@Override
	public List<Role> getRolesByUser(User user) {
		String sql = "select r.* from zs_rbac_user u, zs_rbac_role r,zs_rbac_user_role ur where u.username=? and u.userID=ur.userID and r.roleID=ur.roleID";
		List<Role> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class), user.getUsername());
		return list;
		
	}
/*	@Override
	public List<UserGroup> getUserGroupByUser(User user) {
		String sql = "select g.* from zs_rbac_user u, zs_rbac_usergroup g,zs_rbac_user_usergroup ug where u.username=? and u.userID=ug.userID and g.groupID=ug.groupID";
		List<UserGroup> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserGroup>(UserGroup.class), user.getUsername());
		return list;
	}*/
	@Override
	public void correlationUser_UserGroup(int userId, int... usergroupID) {
		 if(usergroupID == null || usergroupID.length == 0) {
            return;
        }
        String sql = "insert into zs_rbac_user_usergroup(userID, groupID) values(?,?)";
        for(int groupId : usergroupID) {
            if(!CommonUtils.exists("zs_rbac_user_usergroup","userId","groupId",userId, groupId)) {
            	jdbcTemplate.update(sql, userId, groupId);
            }
        }
    
		
	}
	@Override
	public void uncorrelationUser_UserGroup(int userId, int... usergroupID) {
        if(usergroupID == null || usergroupID.length == 0) {
            return;
        }
        String sql = "delete from zs_rbac_user_usergroup where userID=? and roleID=?";
        for(int groupID : usergroupID) {
            if(CommonUtils.exists("rbac_user_usergroup","userId","groupID",userId, groupID)) {
            	jdbcTemplate.update(sql, userId, groupID);
            }
        }
    }
//	@Override
//	public List<Organization> getOrganizationByUser(User user) {
//		String sql = "select o.* from zs_rbac_user u, zs_rbac_organization o,zs_rbac_user_organization uo where u.username=? and u.userID=uo.userID and o.orgID=uo.orgID";
//		List<Organization> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<Organization>(Organization.class), user.getUsername());
//		return list;
//		
//	}
	@Override
	public void correlationUser_Organization(int userId, int... orgID) {
		 if(orgID == null || orgID.length == 0) {
	            return;
	        }
	        String sql = "insert into zs_rbac_user_organization(userID, orgID) values(?,?)";
	        for(int orgid : orgID) {
	            if(!CommonUtils.exists("zs_rbac_user_organization","userId","orgid",userId, orgid)) {
	            	jdbcTemplate.update(sql, userId, orgid);
	            }
	        }
	    
			
		}
	@Override
	public void uncorrelationUser_Organization(int userId, int... orgID) {
        if(orgID == null || orgID.length == 0) {
            return;
        }
        String sql = "delete from zs_rbac_user_organization where userID=? and roleID=?";
        for(int orgid : orgID) {
            if(CommonUtils.exists("zs_rbac_user_organization","userId","orgid",userId, orgid)) {
            	jdbcTemplate.update(sql, userId, orgid);
            }
        }
    }
	
	@Override
	public List<User> getUsers() {
		String sql = "select * from zs_rbac_user";
		List<User> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
		return list;
	}
	
	@Override
	public User findUserByLoginName(String loginName) {
    	log.info(jdbcTemplate+"====username "+loginName);
		
    	String sql = "select * from zs_rbac_user where (Mobile=? or userName=? or email=?) and ustatus=1";
    	log.info(sql);
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), loginName,loginName,loginName);
        if(userList.size() == 0) {
        	log.info("在云客系统中根据电话号码找不到此人");
            return null;
        }else{
            return userList.get(0);
        }
	}
}
