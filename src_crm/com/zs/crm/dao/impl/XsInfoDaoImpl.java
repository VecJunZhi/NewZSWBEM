package com.zs.crm.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.zs.busi.entity.CountInfoVo;
import com.zs.busi.entity.CountRoomVo;
import com.zs.busi.entity.CountTelAccessVo;
import com.zs.busi.entity.XsInfoVo;
import com.zs.busi.entity.XsMonthCountInfoVo;
import com.zs.busi.utils.DBPool;
import com.zs.busi.utils.RsToDtoUtil;
import com.zs.crm.dao.XsInfoDao;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.web.vo.XsTeamGroupVo;

public class XsInfoDaoImpl {/*
		private static Log log=LogFactory.getLog(XsInfoDaoImpl.class);

		@Override
		public List<CountTelAccessVo> getCountTelAccess(String startDate,
				String endDate) throws Exception {
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs=null;
			List<CountTelAccessVo> list = new ArrayList<CountTelAccessVo>();
			try {
				String wCall = "{call zsp_CountTelAccess(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, startDate);
				proc.setString(2, endDate);
				proc.execute();
			    rs = proc.getResultSet();
			    if(rs.next()){
			    	CountTelAccessVo dto=RsToDtoUtil.tranRsToDto(rs, CountTelAccessVo.class);
			    	list.add(dto);
				}
			} catch (Exception e) {
				log.info("telAccessCount expection :"+e);
			}finally{
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
			return list;
		}

		@Override
		public List<CountRoomVo> getCountRoom(String startDate, String endDate,
				String proJid) throws Exception {
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs=null;
			
			List<CountRoomVo> list = new ArrayList<CountRoomVo>();
			try {
				String wCall = "{call zsp_CountRoom(?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, startDate);
				proc.setString(2, endDate);
				proc.setString(3, proJid);
				
				proc.execute();
			    rs = proc.getResultSet();
			    if(rs.next()){
			    	CountRoomVo dto=RsToDtoUtil.tranRsToDto(rs, CountRoomVo.class);
			    	list.add(dto);
				}
			} catch (Exception e) {
				log.info("telAccessCount expection :"+e);
			}finally{
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
			return list;
		}

		@Override
		public List<XsMonthCountInfoVo> getMonthCountMoneyTnumber()
				throws Exception {
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs=null;
			
			List<XsMonthCountInfoVo> list = new ArrayList<XsMonthCountInfoVo>();
			try {
				String wCall = "{call zsp_MonthCountMoneyNumber()}";
				proc = conn.prepareCall(wCall);
				proc.execute();
			    rs = proc.getResultSet();
			    if(rs.next()){
			    	XsMonthCountInfoVo dto=RsToDtoUtil.tranRsToDto(rs, XsMonthCountInfoVo.class);
			    	list.add(dto);
				}
			} catch (Exception e) {
				log.info("moneyNumber expection :"+e);
			}finally{
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
			return list;
		}

		@Override
		public List<XsTeamGroupEntity> getUsersFromXsCRM(XsTeamGroupVo vo) {
			// TODO Auto-generated method stub
			return null;
		}


		
		
	      
*/}
