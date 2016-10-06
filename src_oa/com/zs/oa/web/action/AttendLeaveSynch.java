package com.zs.oa.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.inject.Inject;
import com.zs.oa.util.DBpool;

@Controller
public class AttendLeaveSynch {
	Log log= LogFactory.getLog(AttendLeaveSynch.class);
	@Inject
	HttpServletRequest request;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String sql=null;
	public String insertAttendLeaveData(String USER_ID,String LEAVE_TYPE,String LEAVE_DATE1,String LEAVE_DATE2,String RECORD_TIME,String LEAVE_TYPE2,String HANDLE_TIME){
		try {
			sql="INSERT INTO `attend_leave`( `USER_ID`, `LEADER_ID`, `LEAVE_TYPE`, `LEAVE_DATE1`, `LEAVE_DATE2`, `ANNUAL_LEAVE`, `STATUS`, `ALLOW`, `REASON`, `DESTROY_TIME`, `REGISTER_IP`, `RECORD_TIME`, `LEAVE_TYPE2`, `HANDLE_TIME`, `AGENT`, `ORDER_NO`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			con=DBpool.ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, USER_ID);
			ps.setString(2, "ÕÔ·É");
			ps.setString(3, LEAVE_TYPE);
			ps.setString(4, LEAVE_DATE1);
			ps.setString(5, LEAVE_DATE2);
			ps.setString(6, "0");
			ps.setString(7, "1");
			ps.setString(8, "1");
			ps.setString(9, "");
			ps.setString(10, "0000-00-00");
			ps.setString(11, "192.168.1.1");
			ps.setString(12, RECORD_TIME);
			ps.setString(13, LEAVE_TYPE2);
			ps.setString(14, HANDLE_TIME);
			ps.setString(15, "ÕÔ·É");
			ps.setInt(16, 0);
			ps.executeUpdate();
			log.info("attend_leave synch completed");
			return "success";
		} catch (Exception e) {
			log.info("attend_leave synch exception :"+e);
			return "bad";
		}
	
	}
		public Map<String, String> getRequestData() 
			{
				Map<String, String> map = new HashMap<String, String>();
				String run_id=(String) request.getParameter("run_id");
				String  nameTime=(String) request.getParameter("data");
				try {
					nameTime=URLDecoder.decode(nameTime , "utf-8");
					String[]str=nameTime.split(" ");
					String name=str[0];
					String time=str[1]+" "+str[2];
					map.put("run_id", run_id);
					map.put("name", name);
					map.put("time", time);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return map;
			}
		public Map<String, String> selectBasicInforData(String run_id){
			Map<String, String> map = new HashMap<String, String>();
			String user_id="";
			String leave_Time="";
			String leave_startDate="";
			String leave_endData="";
			String leave_type2="";
			String leave_type="";
			try {
				sql="select begin_user,begin_time,data_22,data_23,data_24,data_26 from flow_data_164 where run_id=?";
				con=DBpool.ds.getConnection();
				ps=con.prepareStatement(sql);
				ps.setString(1, run_id);
				rs=ps.executeQuery();
				while(rs.next()){
					user_id=rs.getString("begin_user");
					leave_Time=rs.getString("begin_time");
					leave_startDate=rs.getString("data_23");
					leave_endData=rs.getString("data_24");
					leave_type=rs.getString("data_26");
					leave_type2=rs.getString("data_22").trim();
					if(leave_type2.equals("ÊÂ¼Ù")){
						leave_type2="1";
					}else if(leave_type2.equals("²¡¼Ù")){
						leave_type2="2";
					}else if(leave_type2.equals("¹«ÐÝ")){
						leave_type2="3";
					}else{
						leave_type2="9";
					}
					map.put("user_id",user_id);
					map.put("leave_Time",leave_Time);
					map.put("leave_type",leave_type);
					map.put("leave_startDate",leave_startDate);
					map.put("leave_endData", leave_endData);
					map.put("leave_type2",leave_type2);
				}
			} catch (Exception e) {
			}
			return map;
			
		}
		
		public String attendLeaveSynch(){
			System.out.println("hojo");
			String result="";
			String run_id="";
			String user_id="";
			String leave_Time="";
			String leave_startDate="";
			String leave_endData="";
			String leave_type2="";
			String leave_type="";
			String handle_time="";
			Map<String, String>map2=getRequestData();
			if(map2.size()>=1){
				run_id=map2.get("run_id");
				handle_time=map2.get("time");
			}
			log.info("run id "+run_id);
			Map<String, String>map=selectBasicInforData(run_id);
			if(map.size()>=1){
				 user_id=map.get("user_id");
				leave_Time=map.get("leave_Time");
				leave_startDate=map.get("leave_startDate");
				leave_endData=map.get("leave_endData");
				leave_type=map.get("leave_type");
				leave_type2=map.get("leave_type2");
			}
			result=insertAttendLeaveData(user_id, leave_type, leave_startDate, leave_endData, leave_Time, leave_type2, handle_time);
			
			return result;
			
		}
		

}
