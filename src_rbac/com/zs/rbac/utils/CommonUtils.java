package com.zs.rbac.utils;

import org.springframework.jdbc.core.JdbcTemplate;

public class CommonUtils {
	private static JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	
	public static boolean exists(String tableName,String firstIDName,String secondIdName,  int firstIdVal, int secIdVal) {
        String sql = "select count(1) from "+tableName+" where "+firstIDName+"=? and "+secondIdName+"=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, firstIdVal, secIdVal) != 0;
    }

}
