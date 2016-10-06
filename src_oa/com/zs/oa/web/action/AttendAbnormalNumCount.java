package com.zs.oa.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.common.util.DateUtil;
import com.zs.oa.dao.IAttendSynchDao;
import com.zs.oa.dao.impl.AttendSynchDaoImpl;
import com.zs.oa.entity.DutyTimeDto;
import com.zs.oa.service.IAttendSynchService;
import com.zs.oa.service.impl.AttendSynchServiceImpl;
import com.zs.oa.util.DBpool;

/**
 * 考勤异常
 * @author jiarui
 *
 */
@Controller
@RequestMapping(value="oa/attendAbnormalTotalNums")
public class AttendAbnormalNumCount {
	/**
	 * 
	 */
	Log log= LogFactory.getLog(AttendAbnormalNumCount.class);
	//@Autowired
	IAttendSynchDao  dao=new AttendSynchDaoImpl();
	//@Autowired
	IAttendSynchService service=new AttendSynchServiceImpl();
	Connection con=null;
	PreparedStatement ps=null;
	String sql=null;
	ResultSet rs=null;
	
	@RequestMapping(value="doGet",method=RequestMethod.GET)
	public  void doGet(@RequestParam(value="run_id",required=true)String run_id,HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//String run_id=req.getParameter("run_id");
		int num=selectAbnormalCountNum(run_id);
		log.info("run_id "+run_id);
		resp.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = resp.getWriter(); 
		out.write(Integer.toString(num));
		log.info("doGet invoked!"+Integer.toString(num));  
	    out.flush();
	    out.close();
		
	}
	@RequestMapping(value="doGet",method=RequestMethod.POST)
	public void doPost(@RequestParam(value="run_id",required=true)String run_id,HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		/*log.info(req.getAuthType());
		log.info(req.getContentType());
		log.info(req.getDateHeader("date"));
		log.info(req.getHeaderNames().toString());
		log.info(req.getLocalName());
		log.info(req.getRemoteAddr());//获取发送请求段的ip地址
		log.info(req.getRemoteHost());//获取发送请求端的主机名
		log.info(req.getRemotePort());
		log.info(req.getRemoteUser());
		log.info(req.getRequestURI());//获取请求的路径
		log.info(req.getRequestURL());//获取请求的地址
		log.info(req.getScheme());//获取请求的协议
		log.info(req.getServerName());//获取请求地址中的域名
		log.info(req.getServerPort());//获取请求地址中的端口
		log.info(req.getServletContext());//
		log.info(req.getServletPath());//获取请求地址中的路径
		log.info(req.getContextPath());*/
		
		int num=selectAbnormalCountNum(run_id);
		log.info("run_id "+run_id);
		/*StringBuffer url = req.getRequestURL();  
		String tempContextUrl = url.delete(url.length() - req.getRequestURI().length(), url.length()).append("/").toString();//获取域名 */
		resp.addHeader("Access-Control-Allow-Origin", "*");//配置地址栏中的地址
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS"); //指明资源可以被请求的方式有哪些(一个或者多个). 这个响应头信息在客户端发出预检请求的时候会被返回   
		PrintWriter out = resp.getWriter(); 
		out.write(Integer.toString(num));
		log.info("doPost invoked"+Integer.toString(num));
	    out.flush();
	    out.close();
	}
	
	/**
	 * 1	根据run_id获得user_id
	 * 2	根据user_id获得该用户的duty_type
	 * 3	根据duty_type获得该用户的上下班时间，从而获得迟到早退的时间
	 */
	public int selectAbnormalCountNum(String run_id){
		log.info(run_id);
		int totalCount=0;
		try {
			String user_id=selectUserIdByRunId(run_id);
			
			int duty_type=dao.queryDuty_Type(user_id);
			DutyTimeDto dto=service.getDutyTimeByDutyType(duty_type);
			log.info(user_id+" "+duty_type+" "+dto.getDuty_time1()+" "+dto.getDuty_time2()+" "+dto.getDuty_time3()+" "+dto.getDuty_time4());
//			String startDate = new StringBuffer().append(DateUtil.getCurrentTime("yyyy-MM")).append("-").append("01 00:00:00").toString();
//			String endDate = new StringBuffer().append(DateUtil.getCurrentTime("yyyy-MM")).append("-").
//					append(DateUtil.isMontyToDay(DateUtil.getCurrentYear(),DateUtil.getCurrentMonth())).
//					append(" 23:59:59").toString();
			sql="SELECT count(distinct REGISTER_TIME) as totalCount  FROM attend_duty where ((time(REGISTER_TIME)> ? and REGISTER_TYPE=1)or  (time(REGISTER_TIME)< ? and REGISTER_TYPE=2) or (time(REGISTER_TIME)> ? and REGISTER_TYPE=3) or (time(REGISTER_TIME)< ? and REGISTER_TYPE=4) or (time(REGISTER_TIME)='00:00:00' and REMARK LIKE '其他原因%') or ( time(REGISTER_TIME)='23:59:59' and REMARK LIKE '其他原因%' )) and USER_ID=? and month(REGISTER_TIME)=month(NOW()) AND YEAR(REGISTER_TIME)=YEAR(NOW())";
			con=DBpool.ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getDuty_time1());
			ps.setString(2, dto.getDuty_time2());
			ps.setString(3, dto.getDuty_time3());
			ps.setString(4, dto.getDuty_time4());
			ps.setString(5, user_id);
			rs=ps.executeQuery();
			if(rs.next())
				{
				totalCount=rs.getInt("totalCount");
				log.info(totalCount);
				}
		} catch (Exception e) {
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
			}
		}
		return totalCount;
		
	}

	public String selectUserIdByRunId(String run_id){
		String user_id="";
		try {
			sql="SELECT begin_user AS user_id FROM flow_data_166 WHERE run_id=?";
			con=DBpool.ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, run_id);
			rs=ps.executeQuery();
			if(rs.next()){
				user_id=rs.getString("user_id");
			}
		} catch (Exception e) {
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
			}
		}
		return user_id;
		
}

}
