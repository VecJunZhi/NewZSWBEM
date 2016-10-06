package com.zs.oa.web.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.oa.dao.IAttendSynchDao;
import com.zs.oa.dao.impl.AttendSynchDaoImpl;
import com.zs.oa.entity.AttendAbnormalDto;
import com.zs.oa.util.DBpool;

@Controller
@RequestMapping(value="oa/attendAbnormal")
public class AttendAbnormalSynch {
	Log log= LogFactory.getLog(AttendAbnormalSynch.class);
	@Autowired
	HttpServletRequest request;
	//@Autowired
	IAttendSynchDao dao=new AttendSynchDaoImpl();
	Connection con=null;
	PreparedStatement ps=null;
	String sql=null;
	ResultSet rs=null;
	public String insertAbnormalRemark(String USER_ID,String REGISTER_TYPE,String REGISTER_TIME,String REGISTER_IP,String REMARK,int DUTY_TYPE,int IS_MOBILE_DUTY,int ATTEND_MOBILE_ID){
		try {
			sql="INSERT INTO `attend_duty`(`USER_ID`, `REGISTER_TYPE`, `REGISTER_TIME`, `REGISTER_IP`, `REMARK`, `DUTY_TYPE`, `IS_MOBILE_DUTY`, `ATTEND_MOBILE_ID`) VALUES (?,?,?,?,?,?,?,?)";
			con=DBpool.ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, USER_ID);
			ps.setString(2, REGISTER_TYPE);
			ps.setString(3, REGISTER_TIME);
			ps.setString(4, REGISTER_IP);
			ps.setString(5, REMARK);
			ps.setInt(6, DUTY_TYPE);
			ps.setInt(7, IS_MOBILE_DUTY);
			ps.setInt(8, ATTEND_MOBILE_ID);
			ps.executeUpdate();
			log.info("attend_duty abnormal synch completed");
		} catch (Exception e) {
			log.info("attend_duty abnormal synch exception :"+e);
		}finally{
			try {
				if(ps !=null){
					ps.close();
				}
				if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				log.info(e2);
			}
		}
		return null;
		
	}
	public String getRequestData(){
			String run_id=(String) request.getParameter("run_id");
			return run_id;
	}
	public List<AttendAbnormalDto> selectBasicInforData(String run_id){
		List<AttendAbnormalDto> list= new ArrayList<AttendAbnormalDto>();
		Map<String, String> map = new HashMap<String, String>();
		String user_id="";
		String register_time;
		
		String remark;
		String data="";
		String data61="";
		AttendAbnormalDto dto;
		try {
			sql="select begin_user,data_161,data_26 from flow_data_166 where run_id=?";
			con=DBpool.ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, run_id);
			rs=ps.executeQuery();
			if(rs.next())
				{
					user_id=rs.getString("begin_user");
					data61=rs.getString("data_161");
					String[] dataArry=data61.split("\n+");
					for (int i = 0; i < dataArry.length; i++) {
						data=dataArry[i];
						register_time=data.substring(0, 10);
						String st=data.substring(11,data.length()-2);
						String[] str=st.split("`");
						String typestr=str[0];
						if(str.length>2){
							remark=str[1]+":"+str[2];
						}else{
							remark=str[1];
						}
						dto=new AttendAbnormalDto(user_id, register_time, typestr, remark);
						list.add(dto);
						
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ps !=null){
					ps.close();
				}
				if(con !=null){
					con.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
		
	}
	@RequestMapping(value="/attendAbnormalSynch")/*,method=RequestMethod.POST*/
	public String attendAbnormalSynch(@RequestParam(value="run_id",required=true)String run_id){
		String user_id="";
		String register_time="";
		String register_type="";
		String typestr="";
		String remark="";
		int duty_type=0;
		String _time="";
		List<AttendAbnormalDto> list=selectBasicInforData(run_id);
		AttendAbnormalDto dto;
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				dto=list.get(i);
				user_id=dto.getUser_id();
				register_time=dto.getRegister_time();
				typestr=dto.getTypestr();
				remark=dto.getRemark();
				try {
					duty_type=dao.queryDuty_Type(user_id);
					String[]type=typestr.split(",");
					for (int j = 0; j < type.length; j++) {
						register_type=type[j].trim();
						if("上午上班".equals(register_type)){
							register_type="1";
							_time="";
							_time=register_time+" "+"00:00:00";
						}else if("上午下班".equals(register_type)){
							register_type="2";
							_time="";
							_time=register_time+" "+"23:59:59";
						}else if("下午上班".equals(register_type)){
							register_type="3";
							_time="";
							_time=register_time+" "+"00:00:00";
						}else if("下午下班".equals(register_type)){
							register_type="4";
							_time="";
							_time=register_time+" "+"23:59:59";
						}
						insertAbnormalRemark(user_id, register_type, _time, "考勤异常", remark, duty_type, 0, 0);
					}
				} catch (Exception e) {
					log.info(e);
				}
					
			}
			
			
		}
		return null;
		
	}
}
