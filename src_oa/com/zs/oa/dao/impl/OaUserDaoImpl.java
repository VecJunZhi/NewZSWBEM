package com.zs.oa.dao.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.zs.oa.dao.OaUserDao;
import com.zs.oa.entity.OaUserEntity;
public class OaUserDaoImpl implements OaUserDao {
	/**
	 * 通过姓名获得uid
	 * @param  userName oa用户姓名
	 * @return oa用户uid
	 */
	public String getUidByUserName(String userName) {
		String uid = "";
		int uids =0;
		//定义数据库参数
		String oaDriver="";
		String oaUrl="";
		String oaUserName="";
		String oaPassword="";
		//文件中读取数据库参数
		Properties p = new Properties();
		try {
			p.load(OaUserDaoImpl.class.getClassLoader().getResourceAsStream("config.properties"));
			oaDriver=p.getProperty("oa_driver");
			oaUrl=p.getProperty("oa_url");
			oaUserName=p.getProperty("oa_username");
			oaPassword=p.getProperty("oa_password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			 //建立连接
			 Class.forName(oaDriver);
			 String url =oaUrl;
			 con = DriverManager.getConnection(url,oaUserName,oaPassword);
			 //发送sql语句
			 String sql ="select uid from user where user_name=? and not_login=0";
		     pst = con.prepareStatement(sql);
		     pst.setString(1, userName);
		     //获取返回值
		     rs = pst.executeQuery();
		     while(rs.next()){
		    		 uids=rs.getInt("uid"); 
		     }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		uid = String.valueOf(uids);
		return uid;
	}
	/**
	 * 获得oa用户所有信息(oa数据库user表中信息)
	 * @return 
	 * map<String,OaUserEntity> 
	 * String：oa用户姓名
	 * OaUserEntity oa用户所有信息
	 */
	public Map<String, OaUserEntity> getAllUser() {
		Map<String, OaUserEntity> map = new HashMap<String, OaUserEntity>();
		//定义数据库参数
		String oaDriver="";
		String oaUrl="";
		String oaUserName="";
		String oaPassword="";		
		//文件中读取数据库参数
		Properties p = new Properties();
		try {
			p.load(OaUserDaoImpl.class.getClassLoader().getResourceAsStream("config.properties"));
			oaDriver=p.getProperty("oa_driver");
			oaUrl=p.getProperty("oa_url");
			oaUserName=p.getProperty("oa_username");
			oaPassword=p.getProperty("oa_password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			 //建立连接
			 Class.forName(oaDriver);
			 String url =oaUrl;
			 con = DriverManager.getConnection(url,oaUserName,oaPassword);
			 //发送sql语句
			 String sql ="select * from user";
		     pst = con.prepareStatement(sql);
		     //获取返回值
		     rs = pst.executeQuery();
		     while(rs.next()){
		        OaUserEntity oaUserEntity=createOaUserEntity(rs);
		    	map.put(oaUserEntity.getUserName(), oaUserEntity);	 
		     }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 封装方法
	 * 通过ResultSet的 rs返回用户信息
	 * @param rs ResultSet类
	 * @return OaUserEntity 用户的信息
	 * @throws Exception
	 */
	private OaUserEntity createOaUserEntity(ResultSet rs) throws Exception{
		OaUserEntity oaUserEntity = new OaUserEntity();
		
		oaUserEntity.setUid(rs.getInt("UID")); 
    	oaUserEntity.setUserId(rs.getString("USER_ID")); 
    	oaUserEntity.setUserName(rs.getString("USER_NAME"));
    	oaUserEntity.setUserNameIndex(rs.getString("USER_NAME_INDEX"));
    	oaUserEntity.setByName(rs.getString("BYNAME"));
    	oaUserEntity.setUseingKey(rs.getString("USEING_KEY"));
    	oaUserEntity.setUseingFinger(rs.getString("USING_FINGER"));
    	oaUserEntity.setPassword(rs.getString("PASSWORD"));
    	oaUserEntity.setKeySn(rs.getString("KEY_SN"));
    	oaUserEntity.setSecureKeySn(rs.getString("SECURE_KEY_SN"));
    	oaUserEntity.setUserPriv(rs.getInt("USER_PRIV"));
    	oaUserEntity.setUserPrivNo(rs.getInt("USER_PRIV_NO"));
    	oaUserEntity.setUserPrivName(rs.getString("USER_PRIV_NAME"));
    	oaUserEntity.setPostPriv(rs.getString("POST_PRIV"));
    	oaUserEntity.setPostDept(rs.getString("POST_DEPT"));
    	oaUserEntity.setDeptId(rs.getInt("DEPT_ID"));
    	oaUserEntity.setDeptIdOther(rs.getString("DEPT_ID_OTHER"));
    	oaUserEntity.setSex(rs.getString("SEX"));
    	//oaUserEntity.setBirthday(rs.getString("BIRTHDAY"));
    	oaUserEntity.setIsLunar(rs.getString("IS_LUNAR"));
    	oaUserEntity.setTelNoDept(rs.getString("TEL_NO_DEPT"));
    	oaUserEntity.setFaxNoDept(rs.getString("FAX_NO_DEPT"));
    	oaUserEntity.setAddHome(rs.getString("ADD_HOME"));
    	oaUserEntity.setPostNoHome(rs.getString("POST_NO_HOME"));
    	oaUserEntity.setTelNoHome(rs.getString("TEL_NO_HOME"));
    	oaUserEntity.setMobilNo(rs.getString("MOBIL_NO"));
    	oaUserEntity.setBpNo(rs.getString("BP_NO"));
    	oaUserEntity.setEmail(rs.getString("EMAIL"));
    	oaUserEntity.setOicqNo(rs.getString("OICQ_NO"));
    	oaUserEntity.setIcqNo(rs.getString("ICQ_NO"));
    	oaUserEntity.setMsn(rs.getString("MSN"));
    	oaUserEntity.setAvatar(rs.getString("AVATAR"));
    	oaUserEntity.setCallSound(rs.getString("CALL_SOUND"));
    	//oaUserEntity.setLastVisitTime(rs.getString("LAST_VISIT_TIME"));
    	oaUserEntity.setSmsOn(rs.getString("SMS_ON"));
    	oaUserEntity.setMenuType(rs.getString("MENU_TYPE"));
    	//oaUserEntity.setLastPassTime(rs.getString("LAST_PASS_TIME"));
    	oaUserEntity.setTheme(rs.getInt("THEME"));
    	oaUserEntity.setShortCut(rs.getString("SHORTCUT"));
    	oaUserEntity.setPortal(rs.getString("PORTAL"));
    	oaUserEntity.setPanel(rs.getString("PANEL"));
    	oaUserEntity.setOnLine(rs.getInt("ONLINE"));
    	oaUserEntity.setOnStatus(rs.getString("ON_STATUS"));
    	oaUserEntity.setAttendStatus(rs.getString("ATTEND_STATUS"));
    	oaUserEntity.setMobilNoHidden(rs.getString("MOBIL_NO_HIDDEN"));
    	oaUserEntity.setMytableLeft(rs.getString("MYTABLE_LEFT"));
    	oaUserEntity.setMytableRight(rs.getString("MYTABLE_RIGHT"));
    	oaUserEntity.setUserPrivOther(rs.getString("USER_PRIV_OTHER"));
    	oaUserEntity.setUserNo(rs.getInt("USER_NO"));
    	oaUserEntity.setNotLogin(rs.getInt("NOT_LOGIN"));
    	oaUserEntity.setNotViewUser(rs.getString("NOT_VIEW_USER"));
    	oaUserEntity.setNotViewTable(rs.getString("NOT_VIEW_TABLE"));
    	oaUserEntity.setNotSearch(rs.getString("NOT_SEARCH"));
    	oaUserEntity.setBkGround(rs.getString("BKGROUND"));
    	oaUserEntity.setBindIp(rs.getString("BIND_IP"));
    	oaUserEntity.setLastVisitIp(rs.getString("LAST_VISIT_IP"));
    	oaUserEntity.setMenuImage(rs.getString("MENU_IMAGE"));
    	oaUserEntity.setWeatherCity(rs.getString("WEATHER_CITY"));
    	oaUserEntity.setShowRss(rs.getString("SHOW_RSS"));
    	oaUserEntity.setMyRss(rs.getString("MY_RSS"));
    	oaUserEntity.setRemark(rs.getString("REMARK"));
    	oaUserEntity.setMenuExpand(rs.getString("MENU_EXPAND"));
    	oaUserEntity.setMyStatus(rs.getString("MY_STATUS"));
    	oaUserEntity.setLimitLogin(rs.getString("LIMIT_LOGIN"));
    	oaUserEntity.setPhoto(rs.getString("PHOTO"));
    	oaUserEntity.setImRange(rs.getInt("IM_RANGE"));
    	//oaUserEntity.setLeaveTime(rs.getString("LEAVE_TIME"));
    	oaUserEntity.setSecretLevel(rs.getInt("SECRET_LEVEL"));
    	oaUserEntity.setUserPara(rs.getString("USER_PARA"));
    	oaUserEntity.setNotMobileLogin(rs.getInt("NOT_MOBILE_LOGIN"));
    	oaUserEntity.setManageModuleType(rs.getString("MANAGE_MODULE_TYPE"));
    	oaUserEntity.setUserPrivType(rs.getInt("USER_PRIV_TYPE"));
    	oaUserEntity.setUserManageOrgs(rs.getString("USER_MANAGE_ORGS"));
    	return oaUserEntity;
	}
	public static void main(String[] args){
		OaUserDao oaUserDao = new OaUserDaoImpl();
		Map<String, OaUserEntity> map = oaUserDao.getAllUser();
		System.out.println("uid为"+map.get("姬小航").uid+""+map.get("姬小航").mobilNo);
	}
}
