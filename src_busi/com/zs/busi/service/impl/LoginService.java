package com.zs.busi.service.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zs.busi.utils.DBPool;

public class LoginService {/*
	 private static Log log=LogFactory.getLog(LoginService.class);
	 
	  public Map<String, Object>  CheckEntPasswords() throws Exception{
		  log.info("efe");
		 Connection conn = DBPool.ds.getConnection();
		 CallableStatement proc = null;
		 ResultSet rs=null;
		 ResultSet rs2=null;
		 Map<String, Object> rsMap = new HashMap<String, Object>();
		 String returnStr =null;
		 log.info("get the connect"+conn);
		try {
			
			
			String wCall = "{call zsp_CountTelAccess(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, "2014-01-01");
			proc.setString(2, "2015-09-09");
		
			proc.registerOutParameter(3, java.sql.Types.VARCHAR);
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.execute();
		    rs = proc.getResultSet();
		    while(rs.next()){
				System.out.println(rs.getString("group"));
			}
			retStr=proc.getString(3);
			returnStr=proc.getString(4);
			if("".equals(retStr)){
				retStr="true";
			}
			log.info("Pro_CheckEntPasswords--"+retStr);
			
			if(proc.getMoreResults()){
				rs2=proc.getResultSet();
				while(rs.next()){
					System.out.println(rs2.getString("id"));
				}
				
			}
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
            	if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("", e);
            }
        }
		    rsMap.put("one", retStr);
	        rsMap.put("two", returnStr);
	        return rsMap;
		
	}

*/}
