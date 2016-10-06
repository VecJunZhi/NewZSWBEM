package com.zs.oa.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.opensymphony.xwork2.inject.Inject;
import com.zs.oa.entity.AssetAllocateInfoDto;
import com.zs.oa.util.DBpool;



@Controller
@RequestMapping(value="oa/assetAllocate")
public class AssetAllocateSynch {
			Log log= LogFactory.getLog(AssetAllocateSynch.class);
			@Autowired
			HttpServletRequest request;
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String sql=null;
			public String updateAssetInfor(String dept_id,String createData,String fromData,String user_id,String remark,String cptl_no)
			{
				try {
					sql="UPDATE `cp_cptl_info` SET `DEPT_ID`=?,`CREATE_DATE`=?,`FROM_YYMM`=?,`KEEPER`=?,`REMARK`=? WHERE CPTL_NO=?";
					con=DBpool.ds.getConnection();
					ps=con.prepareStatement(sql);
					ps.setString(1, dept_id);
					ps.setString(2, createData);
					ps.setString(3, fromData);
					ps.setString(4, user_id);
					ps.setString(5, remark);
					ps.setString(6, cptl_no);
					ps.executeUpdate();
					log.info("updateAssetInfor synch completed");
				
				} catch (Exception e) {
					// TODO: handle exception
					log.info(e);
				}finally
					{
						try {
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
				return null;
				
			}
			public Map<String, String> getRequestData(String run_id,String data)
				{
					Map<String, String> map = new HashMap<String, String>();
					//String run_id=(String) request.getParameter("run_id");
					//String  data=(String) request.getParameter("data");
					try {
						data=URLDecoder.decode(data,"UTF-8");
						log.info(data);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String[]str=data.split(" ");
					data=str[1];
					//String  remark=(String) request.getParameter("remark");
					System.out.println(run_id+" "+data);
					
					map.put("run_id", run_id);
					map.put("data", data);
					//map.put("remark", remark);
					return map;
				}
			public Map<String, String> selectBasicInforData(String run_id){
				Map<String, String> map = new HashMap<String, String>();
				String to_dept;
				String new_keeper;
				try {
					sql="select data_57,data_59 from flow_data_162 where run_id=?";
					con=DBpool.ds.getConnection();
					ps=con.prepareStatement(sql);
					ps.setString(1, run_id);
					rs=ps.executeQuery();
					while(rs.next())
						{
						to_dept=rs.getString("data_57");
						if(to_dept.endsWith(",")){
							to_dept=to_dept.substring(0, to_dept.length()-1).trim();
						}
						new_keeper=rs.getString("data_59");
						if(new_keeper.endsWith(",")){
							new_keeper=new_keeper.substring(0,new_keeper.length()-1).trim();
						}
						
						map.put("dept",to_dept);
						map.put("name",new_keeper);
						}
				} catch (Exception e) {
					// TODO: handle exception
					log.info(e);
				}finally
					{
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
				
				return map;
				
			}
			public List<AssetAllocateInfoDto> selectAllocateList(String run_id){
				 List<AssetAllocateInfoDto> list = new ArrayList<>();
			 	 String asset_id;
				 String asset_name;
				 String asset_type;
				 String asset_models;
				 String asset_count;
				 String asset_allocate_reason;
				 AssetAllocateInfoDto dto=null;
				try {
					sql="select item_w,item_e,item_fd,item_c,item_tt,item_f from flow_data_162_list_61 where run_id=?";
					con=DBpool.ds.getConnection();
					ps=con.prepareStatement(sql);
					ps.setString(1, run_id);
					rs=ps.executeQuery();
					while(rs.next()){
						asset_id=rs.getString("item_w").trim();
						asset_name=rs.getString("item_e").trim();
						asset_models=rs.getString("item_fd").trim();
						asset_type=rs.getString("item_c").trim();
						asset_count=rs.getString("item_tt").trim();
						asset_allocate_reason=rs.getString("item_f").trim();
						dto=new AssetAllocateInfoDto(asset_id, asset_name, asset_models, asset_type, asset_count, asset_allocate_reason);
						list.add(dto);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					log.info(e);
				}finally
				{
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
			public String selectDept_id(String dept){
				String dept_id="";
				try {
					sql="SELECT DEPT_ID FROM `department` WHERE DEPT_NAME=?";
					con=DBpool.ds.getConnection();
					ps=con.prepareStatement(sql);
					ps.setString(1, dept);
					rs=ps.executeQuery();
					if(rs.next()){
						dept_id=rs.getString("DEPT_ID").trim();
						
					}
					return dept_id;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					log.info("selectDept_id wrong : "+e);
					return dept_id;
				}finally
				{
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
				
			}
			public String selectUser_id(String name){
				String user_id="";
				try {
					sql="SELECT USER_ID FROM `hr_staff_info` WHERE STAFF_NAME=?";
					con=DBpool.ds.getConnection();
					ps=con.prepareStatement(sql);
					ps.setString(1, name);
					rs=ps.executeQuery();
					if(rs.next()){
						user_id=rs.getString("USER_ID").trim();
						
					}
					return user_id;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					log.info("selectUser_id wrong : "+e);
					return user_id;
				}finally
					{
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
				
			}
		@RequestMapping(value="assetAllocateSynch")
		public String assetAllocateSynch(@RequestParam(value="run_id",required=true)String run_id,@RequestParam String data){
			
			String fromDate="";
			String dept="";
			String name="";
			String dept_id="";
			String user_id="";
			String cptl_no="";
			String remark="";
			Map<String, String> map=getRequestData(run_id,data);
			run_id=map.get("run_id");
			fromDate=map.get("data");
			Map<String, String> map2=selectBasicInforData(run_id);
			dept=map2.get("dept");
			name=map2.get("name");
			log.info(dept+" "+name);
			dept_id=selectDept_id(dept);
			user_id=selectUser_id(name);
			log.info(dept_id+" "+user_id);
			List<AssetAllocateInfoDto>list=selectAllocateList(run_id);
			log.info(list.size());
			if(list.size()>0){
				for (AssetAllocateInfoDto dto : list){
					cptl_no=dto.getAsset_id();
					remark=dto.getAsset_allocate_reason();
					updateAssetInfor(dept_id, fromDate, fromDate, user_id, remark, cptl_no);
				}
				log.info("asset allocate synch complete");
			}
			return null;
		}
	
}
