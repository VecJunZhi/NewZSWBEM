package com.zs.oa.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Repository;

import com.zs.busi.utils.LogUtil;
import com.zs.common.util.DateUtil;
import com.zs.oa.dao.IAttendSynchDao;
import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.entity.AttendRecordDto;
import com.zs.oa.entity.DutyIntervalDto;
import com.zs.oa.entity.DutyTimeDto;
import com.zs.oa.entity.NewPerson;
import com.zs.oa.entity.OtherOweAttendDto;
import com.zs.oa.util.DBpool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class AttendSynchDaoImpl implements IAttendSynchDao {
	
	Log log=LogUtil.getLog();
	
	@Override
	public void insertAttendRecord(String id, String type, String time,
			String ip, String remark, int dtype, int mduty, int mid)
			throws Exception {
		// TODO Auto-generated method stub
		 String  sql ="INSERT INTO `attend_duty`(`USER_ID`,`REGISTER_TYPE`,`REGISTER_TIME`, `REGISTER_IP`, `REMARK`, `DUTY_TYPE`, `IS_MOBILE_DUTY`, `ATTEND_MOBILE_ID`) VALUES (\'"+id+"\',\'"+type+"\',\'"+time+"\',\'"+ip+"\',\'"+remark+"\',"+dtype+","+mduty+","+mid+")";
		 Connection con = null;
		 Statement stm = null;
		try {
			
			 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 stm.executeUpdate(sql);
			
		} catch (Exception e) {
			log.info(e);
		}finally{
			try {
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		
	}

	@Override
	public List<AttendRecordDto> queryAttendRecord() throws Exception {
		// TODO Auto-generated method stub
		List<AttendRecordDto> list=new ArrayList<AttendRecordDto>();
		 Connection con = null;
		 Statement stm = null;
		 ResultSet rs=null;
		 String sql="SELECT * FROM `attend_duty` ";
		 try {
			 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 rs= stm.executeQuery(sql);
			 AttendRecordDto dto=null;
			 while(rs.next()){
				 dto= new AttendRecordDto();
				 dto.setATTEND_MOBILE_ID(rs.getInt("ATTEND_MOBILE_ID"));
				 dto.setDUTY_TYPE(rs.getInt("DUTY_TYPE"));
				 dto.setIS_MOBILE_DUTY(rs.getInt("IS_MOBILE_DUTY"));
				 dto.setREGISTER_IP(rs.getString("REGISTER_IP"));
				 dto.setREGISTER_TIME(rs.getString("REGISTER_TIME"));
				 dto.setREGISTER_TYPE(rs.getString("REGISTER_TYPE"));
				 dto.setREMARK(rs.getString("REMARK"));
				 dto.setUSER_ID(rs.getString("USER_ID"));
				 list.add(dto);
				  log.info("fdf  "+rs.getObject("REGISTER_TIME")+" "+rs.getObject("USER_ID"));
			  
			 }
		} catch (Exception e) {
			log.info(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return list;
	}

	@Override
	public String queryUser_Id(String work_no) throws Exception {
		// TODO Auto-generated method stub
		 Connection con = null;
		 Statement stm = null;
		 String sql="SELECT * FROM `hr_staff_info` WHERE WORK_NO=\'"+work_no+"\'";
		 String user_id = "";
		 try {
			  con=DBpool.ds.getConnection();
			  stm=con.createStatement();
			   ResultSet rs= stm.executeQuery(sql);
			  
			   while(rs.next()){
				   user_id=rs.getString("USER_ID");
				  // log.info("num4 user_id  "+user_id);
			   }
			  // log.info("num4 user_id Nrs "+user_id);
		} catch (Exception e) {
			// TODO: handle exception
			user_id="false";
			log.info(e);
		}finally{
			try {
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return user_id;
	}

	@Override
	public int queryDuty_Type(String user_id) throws Exception {
		// TODO Auto-generated method stub
	
		Connection con = null;
		 Statement stm = null;
		 String sql="SELECT * FROM `user_ext` WHERE USER_ID=\'"+user_id+"\'";
		 int dtype=0;
		 ResultSet rs=null;
		 try {
			 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			   rs= stm.executeQuery(sql);
			   while(rs.next()){
				   dtype=rs.getInt("DUTY_TYPE");
				}
		} catch (Exception e) {
			dtype=0;
			log.info(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return dtype;
	}

	@Override
	public String register_type(String time) throws Exception {
		// TODO Auto-generated method stub
		 String num=null;
		
		try {
			
			  
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date=sdf.parse(time);
				//log.info(date.getHours());
				@SuppressWarnings("deprecation")
				int  hour=date.getHours();
				@SuppressWarnings("deprecation")
				int minute=date.getMinutes();
				if(hour==7||hour==8 || hour==9 ){
					num="1";
				}else if(hour==11||hour==12 ){
					num="2";
				}else if((hour==13 && minute >=30) ||hour==14){
					num="3";
				}else if(hour==18||hour>=19){
					num="4";
				}else {
					num="5";
				}
				log.info("num6 num "+num);
		} catch (Exception e) {
			// TODO: handle exception
			num="false";
			log.info("num6 bad num "+num);
		}
		return num;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getStart_Time() throws Exception {
		// TODO Auto-generated method stub
		 Connection con = null;
		 Statement stm = null;
		 String sql="SELECT MAX(`REGISTER_TIME`) AS TIME FROM `attend_duty`";
		  String start_time=null;
		  ResultSet rs=null;
		 try {
			 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 rs= stm.executeQuery(sql);
			 if(rs.next()){
				   	start_time=rs.getString("TIME");
				   	log.info("start time "+start_time);
				    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date=sdf.parse(start_time);
					date.setSeconds(date.getSeconds()+1);
					start_time=sdf.format(date);
				   log.info("num2 start_time  "+new Date()+" "+start_time);
			 }else{
				   log.info("helo");
			   }
		} catch (Exception e) {
			// TODO: handle exception
			start_time="false";
			log.info(e);
		}finally{
			try {
				if(rs !=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return start_time;
	}

	@Override
	public String otFry(int hour,String date,String user_id){
		
		 Connection con = null;
		 Statement stm = null;
		 String register_type=null;
		 ResultSet rs=null;
		 String sql="SELECT MAX(`REGISTER_TIME`) AS TIME FROM `attend_duty`";
		 if(hour==13 || hour==14){
			 sql="select duty_type from `attend_duty` where REGISTER_TIME>"+date+" and "+" user_id="+user_id;
		 try {
			 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 rs=stm.executeQuery(sql);
			 if(rs.next()){
				 String type= rs.getString("DUTY_TYPE");
				 if (type.equals("1") || type==null){
					 register_type="2";
					}else if(type.equals("2")){
						register_type="3";
					}
			 }else{
				
			 }
			
			
			
		} catch (SQLException e) {
			log.info(e);
		}finally{
			try {
			if(rs !=null){
				rs.close();
			}
			if(stm !=null){
				stm.close();
			}if(con !=null){
				con.close();
			}
		} catch (Exception e2) {
			log.info(e2);
		}
			
		}
		 } 
		return register_type;
		
	}
	
	@Override
	public Map<String, NewPerson>  insertLastMap(){
		Map<String, NewPerson> map = new HashMap<String, NewPerson>();
		String sql="SELECT USER_ID ,WORK_NO ,STAFF_NAME FROM hr_staff_info WHERE USER_ID NOT IN (SELECT USER_ID FROM attend_duty)  AND WORK_NO !=\'\'" ;
		 Connection con = null;
		 Statement stm = null;
		 ResultSet rs=null;
		 try {
			 
			 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			  rs=stm.executeQuery(sql);
			 String user_id="";
			 String work_no="";
			 String name="";
			 NewPerson p =null;
			 while(rs.next()){
				
				 user_id= rs.getString("USER_ID");
				 work_no= rs.getString("WORK_NO");
				 name=rs.getString("STAFF_NAME");
				 p=new NewPerson(user_id, work_no, name);
				 map.put(work_no, p);
				 
			 }
			
		} catch (Exception e) {
			log.info(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return map;
		
	}

	@Override
    public String JudgeIfInsert(String user_id){
    	 Connection con = null;
		 Statement stm = null;
		 ResultSet rs=null;
    	String sql="SELECT COUNT(USER_ID) AS TCOUNT FROM `attend_duty` WHERE USER_ID=\'"+user_id+"\'";
    	String count="";
    	try {
    		 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			  rs= stm.executeQuery(sql);
			
			 if(rs.next()){
				count= (String) rs.getObject("TCOUNT").toString();
			 }else{
				 log.info(new Date()+" judge if insert bad");
			 }
			 
		} catch (Exception e) {
			log.info(e);
			
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}if(stm!=null){
					stm.close();
				}if(con!=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
    	
    	
		return count;
    	
    }
   
	@Override
	public String SynchNeed(){
		 Connection con = null;
		 Statement stm = null;
		 ResultSet rs=null;
		 String sql="SELECT COUNT(user_id) AS TCOUNT  FROM hr_staff_info WHERE USER_ID NOT IN (SELECT USER_ID FROM attend_duty)  AND WORK_NO !=\'\'";
		 String count="";
		try {
   		 	 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 rs= stm.executeQuery(sql);
			
			 if(rs.next()){
				count= (String) rs.getObject("TCOUNT").toString();
				log.info(" synchneed count "+count);
			 }else{
				 log.info(new Date()+" if syncheNeed else bad");
			 }
			 
		} catch (Exception e) {
			log.info(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}if(stm!=null){
					stm.close();
				}if(con!=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return count;
		
	}
	/**
	 * 
	 */
	@Override
	public String selectMinValueForPM(String user_id ,String register_type){
		String sql="select COUNT(user_id) AS TCOUNT from `attend_duty` where user_id=\'"+user_id+"\' and register_type=\'"+register_type+"\' and (register_time BETWEEN CURDATE() and NOW()) order by register_time ASC";
		 Connection con = null;
		 Statement stm = null;
		 ResultSet rs=null;
		 String count="";
		 try {
   		 	 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 rs= stm.executeQuery(sql);
			 if(rs.next()){
				count= (String) rs.getObject("TCOUNT").toString();
			}else{
				 count="false";
				 log.info("  selectMinValueForPM else bad");
			 }
			 
		} catch (Exception e) {
			// TODO: handle exception
			count="false";
			 log.info("  selectMinValueForPM catch bad"+e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}if(stm!=null){
					stm.close();
				}if(con!=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return count;
		
	}
	public List<String> selectMinDataForPM(String user_id ,String register_type){
		String sql="select register_time AS TIMER from `attend_duty` where user_id=\'"+user_id+"\' and register_type=\'"+register_type+"\' and (register_time BETWEEN CURDATE() and NOW()) order by register_time ASC";
		 Connection con = null;
		 Statement stm = null;
		 ResultSet rs=null;
		 String count="";
		List<String> list = new ArrayList<String>();
		 try {
   		 	 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 rs= stm.executeQuery(sql);
			
			 while(rs.next()){
				count= (String) rs.getObject("TIMER").toString();
				list.add(count);
			 }
			 
		} catch (Exception e) {
			// TODO: handle exception
			 count="false";
			 log.info(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}if(stm!=null){
					stm.close();
				}if(con!=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return list;
		
	}
	
	
	/**
	 * 
	 */
	@Override
	public String deleteMinValueForPM(String user_id ,String register_type,String register_time){
		log.info("idid "+user_id);
		String sql="DELETE from `attend_duty` where user_id=\'"+user_id+"\' and register_type=\'"+register_type+"\' and register_time= \'"+register_time+"\'";
		 Connection con = null;
		 Statement stm = null;
		 ResultSet rs=null;
		 String result="";
		 try {
  		 	 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 stm.executeUpdate(sql);
			 result="true";
			 
		} catch (Exception e) {
			// TODO: handle exception
			result="false";
			log.info(e);
		}finally{
			try {
				if(stm!=null){
					stm.close();
				}if(con!=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return result;
		
	}
	
	@Override
	public   Map<String,OtherOweAttendDto>  selectExistEmployData(){
		 String sql="SELECT a.`USER_ID`, MAX(a.`REGISTER_TIME`)AS MaxTime,b.`STAFF_NAME`,b.`WORK_NO`  from attend_duty a ,`hr_staff_info` b where a.`USER_ID`=b.`USER_ID` AND b.work_no!=\'\' GROUP BY a.`USER_ID`;";
		 Connection con = null;
		 Statement stm = null;
		 ResultSet rs=null;
		 String user_id=null;
		 String maxTime=null;
		 String staff_name=null;
		 String work_no=null;
		 OtherOweAttendDto dto=null;
		 Map<String,OtherOweAttendDto> map = new HashMap<String,OtherOweAttendDto>();
		 JSONObject data = new JSONObject();
		 try {
			 con=DBpool.ds.getConnection();
			 stm=con.createStatement();
			 rs= stm.executeQuery(sql);
			 
			 while(rs.next()){
				 user_id=rs.getString("USER_ID");
				 maxTime= rs.getString("MaxTime");
				 staff_name= rs.getString("STAFF_NAME");
				 work_no=rs.getString("WORK_NO");
				 dto=new OtherOweAttendDto(user_id, maxTime, staff_name, work_no);
				 map.put(work_no, dto);
				 
			 }
			 JSONArray ja = JSONArray.fromObject(map);
		     data.put("data", ja);
		} catch (Exception e) {
			log.info(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		 return map;
	}
	@Override
	@SuppressWarnings("deprecation")
	public String deleteDuplData(){
	
		Date date = new Date();
		int hour=date.getHours();
		Map<String, OtherOweAttendDto> map=selectExistEmployData();
		Iterator<Entry<String, OtherOweAttendDto>> it=map.entrySet().iterator();
		String user_id=null;
		String count=null;
		while(it.hasNext()){
			Entry<String, OtherOweAttendDto> entry= it.next();
			user_id=entry.getValue().getUser_id();
			if(hour==12 ||hour==13){
				count=selectMinValueForPM(user_id, "2");
			if(!count.equals("") && !count.equals("1") && !count.equals("false") && count !=null && !count.equals("0")){
				List<String>list=selectMinDataForPM(user_id, "2");
				if(list.size()>=2){
				for (int i = 0; i < list.size()-1; i++) {
					deleteMinValueForPM(user_id, "2", list.get(i));
					log.info("delete the noon dupl data for user_id: "+user_id);
				}
				}
			}
			}
			if(hour>=18 ){
				count=selectMinValueForPM(user_id, "4");
				if(!count.equals("") && !count.equals("1") && !count.equals("false") && count !=null && !count.equals("0")){
					List<String>list=selectMinDataForPM(user_id, "4");
					if(list.size()>=2){
						for (int i = 0; i < list.size()-1; i++) {
							deleteMinValueForPM(user_id, "4", list.get(i));
							log.info("delete the night dupl data for user_id: "+user_id);
						}
					}
				}
				}
		}
		return "";
	}
/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String getDutyInterval(String para_name) {
		 String sql="SELECT PARA_VALUE FROM sys_para where PARA_NAME=?";
		 Connection con = null;
		 PreparedStatement stm = null;
		 ResultSet rs=null;
		 String para_val=null;
		 
		 try {
			 con=DBpool.ds.getConnection();
			 stm=con.prepareStatement(sql);
			 stm.setString(1, para_name);
			 rs= stm.executeQuery();
			  if(rs.next()){
				 para_val=rs.getString("PARA_VALUE");
			 }
			 
		} catch (Exception e) {
			log.info(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		 return para_val;
	}
	@Override
	public String selectUseridByWorkNo(String work_no){
		 String sql="select user_id from hr_staff_info where work_no=?";
		 Connection con = null;
		 PreparedStatement stm = null;
		 ResultSet rs=null;
		 String user_id=null;
		 try {
			 con=DBpool.ds.getConnection();
			 stm=con.prepareStatement(sql);
			 stm.setString(1, work_no);
			 rs= stm.executeQuery();
			 if(rs.next()){
				 user_id=rs.getString("user_id");
				 
			}
		} catch (Exception e) {
			log.info("make error "+e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		 return user_id;
	}

	@Override
	public DutyTimeDto getDutyTimeByDutyType(int dutyType) {
		String sql="select DUTY_TIME1,DUTY_TIME2, DUTY_TIME3, DUTY_TIME4 from attend_config where DUTY_TYPE=?";
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs=null;
		String duty_time1=null;
		String duty_time2=null;
		String duty_time3=null;
		String duty_time4=null;
		DutyTimeDto dto=null;
		try{
			con=DBpool.ds.getConnection();
			stm=con.prepareStatement(sql);
			stm.setInt(1, dutyType);
			rs= stm.executeQuery();
			if(rs.next()){
				duty_time1=rs.getString("DUTY_TIME1");
				duty_time2=rs.getString("DUTY_TIME2");
				duty_time3=rs.getString("DUTY_TIME3");
				duty_time4=rs.getString("DUTY_TIME4");
				dto=new DutyTimeDto(duty_time1, duty_time2, duty_time3, duty_time4);
			}
		}catch (Exception e) {
			log.info("make error :"+e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		 return dto;
	}
	@Override
	public AttendMachineNodeDto selectAttendMachineNodeDate(String attendMachineCode){
		String sql="select * from zs_attend_machine where attendMachineCode=?";
		 Connection con = null;
		 PreparedStatement stm = null;
		 ResultSet rs=null;
		 String id=null;
		 String lastTime=null;
		 String machineStatus=null;
		 String machineIP=null;
		 String machinePort=null;
		 String machineSecretkey=null;
		 String machineCharset=null;
		 String remark=null;
		 AttendMachineNodeDto dto = null;
		 try {
			 con=DBpool.ds_zs.getConnection();
			 stm=con.prepareStatement(sql);
			 stm.setString(1, attendMachineCode);
			 rs= stm.executeQuery();
			 if(rs.next()){
				 id=rs.getString("id");
				 attendMachineCode= rs.getString("attendMachineCode");
				 lastTime= rs.getString("lastTime");
				 machineStatus=rs.getString("machineStatus");
				 machineIP=rs.getString("machineIP");
				 machinePort= rs.getString("machinePort");
				 machineSecretkey= rs.getString("machineSecretkey");
				 machineCharset=rs.getString("machineCharset");
				 remark=rs.getString("remark");
				 dto=new AttendMachineNodeDto(id, attendMachineCode, lastTime, machineStatus, machineIP, machinePort, machineSecretkey, machineCharset,remark);
			}
		} catch (Exception e) {
			log.info("make error : "+e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stm !=null){
					stm.close();
				}if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info("make error : "+e2);
			}
		}
		 return dto;
	}
	@Override
	public boolean updateMachineLastTime(String time,String lastIP,String machineCode){
			time=DateUtil.addSecond(1, "yyyy-MM-dd HH:mm:ss", time);
			boolean flag=true;
	        Connection con=null;
	        PreparedStatement ps=null;
	        String sql="update zs_attend_machine set lastTime=?,lastIP=? where attendMachineCode=?";
	        try {
				con=DBpool.ds_zs.getConnection();
				ps=con.prepareStatement(sql);
	            ps.setString(1, time);
	            ps.setString(2, lastIP);
	            ps.setString(3, machineCode);
	            int i= ps.executeUpdate();
	            if(i==0){
	                flag=false;
	            }
			} catch (SQLException e) {
				flag=false;
	            log.info(e);
	        }finally{
				try {
					if(ps !=null){
						ps.close();
					}if(con !=null){
						con.close();
					}
				} catch (Exception e2) {
					log.info("make error : "+e2);
				}
			}
	        return flag;
	}

	@Override
	public Map<String, String> getOaSysPara(String[] paraArray) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDataFormAttend(AttendMachineNodeDto machineNode) {
		// TODO Auto-generated method stub
		return null;
	}

}
