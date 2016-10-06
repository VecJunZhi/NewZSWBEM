package com.zs.oa.web.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;





import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zs.oa.dao.impl.AttendSynchDaoImpl;
import com.zs.oa.entity.AttendMachineDto;
import com.zs.oa.entity.AttendMachineNodeDto;
import com.zs.oa.entity.OtherResponseDto;
import com.zs.oa.service.impl.OtherAttendSynchServiceImpl;
import com.zs.oa.util.DBpool;

import net.sf.json.JSONObject;
	
public class OtherAttendSynchAction {
		
	Log log =LogFactory.getLog(OtherAttendSynchAction.class);
	OtherAttendSynchServiceImpl service=new OtherAttendSynchServiceImpl();
	AttendSynchDaoImpl dao=new AttendSynchDaoImpl();

	public String getClientIpAxis() {
		MessageContext mc = null;
		HttpServletRequest request = null;
		try{
			mc=MessageContext.getCurrentMessageContext();
			if(mc==null){
				log.info("mc is null");
			}
			request = (HttpServletRequest) mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			log.info("remote ip "+request.getRemoteAddr());
		} catch (Exception e) {
			log.info(e);
		}
		return request.getRemoteAddr();
	}

	public String selectUseridByWorkNo(String work_no){
		String sql="select user_id from hr_staff_info where work_no=?";
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs=null;
		String user_id=null;
		try{
			con=DBpool.ds.getConnection();
			stm=con.prepareStatement(sql);
			stm.setString(1, work_no);
			rs= stm.executeQuery();
			if(rs.next()){
				 user_id=rs.getString("user_id");
			}
		}catch (Exception e) {
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

	public String insertDataToMysql(List<OtherResponseDto> list,String remark) throws Exception{
		String lastTime=service.smpInsertDataToSql(list,remark);
		return lastTime;
	}
	
	public List<OtherResponseDto> DealSmpDataToBean(String json){
		JSONObject b=JSONObject.fromObject(json);
		List<OtherResponseDto> list= new ArrayList<OtherResponseDto>();
		List o=(List) b.get("data");
		String user_id=null;
		for (int i = 0; i < o.size(); i++) {
			JSONObject obj=(JSONObject) o.get(i);
			AttendMachineDto dto=(AttendMachineDto) JSONObject.toBean(obj, AttendMachineDto.class);
			user_id=selectUseridByWorkNo(dto.getId());
			if(user_id!=null && !"".equals(user_id)){
				OtherResponseDto response = new OtherResponseDto(dto.getTime(), user_id, dto.getName(), dto.getId(), "attendIp");
				list.add(response);
			 }
		}
		log.info("step3: encapsulate the smp data into List, and the size is "+list.size());
		return list;
	}
	
	public String smpTongXin(String json,String flg,String machineCode){
		if(flg.equals("receive")){
			log.info("step1: receive connection withe smp is ok ");
			AttendMachineNodeDto dto=dao.selectAttendMachineNodeDate(json);//获取考勤节点数据
			JSONObject object=JSONObject.fromObject(dto);
			return object.toString();
		}else if(flg.equals("response")){
			log.info("step 2: send the machine node data to smp,machine code : "+machineCode);
			List<OtherResponseDto> list=DealSmpDataToBean(json);
			if(list.size()>0){
				try {
					AttendMachineNodeDto dto=dao.selectAttendMachineNodeDate(machineCode);
					String lastTime=insertDataToMysql(list,dto.getRemark());
					if(lastTime!=null && !"".equals(lastTime) && !"error".equals(lastTime)){
						log.info(" step4: the attendData synch Completed,the last time is "+lastTime);
						String lastIp=getClientIpAxis();
						//需要更新时间
						boolean _res=dao.updateMachineLastTime(lastTime,lastIp, machineCode);
						if(_res){
							//删除重复数据
							log.info("step 5: attend machine update time ok");
							//dao.deleteDuplData();
						}
					}
				} catch (Exception e) {
					log.info("insert to mysql catch bad "+e);
				}
			}else{
				log.info("step3: encapsulate the smp data into List, and the size is "+list.size());
			}
			
		}
		return "";
		
	}

	
}
