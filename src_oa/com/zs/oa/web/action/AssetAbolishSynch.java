package com.zs.oa.web.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.opensymphony.xwork2.inject.Inject;
import com.zs.oa.util.DBpool;

@Controller
@RequestMapping(value="oa/assetAbolish")
public class AssetAbolishSynch {
	
	Log log= LogFactory.getLog(AssetAbolishSynch.class);
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String sql=null;
	@Autowired
	HttpServletRequest request;
	
	public String selectBasicInforData(String run_id){
		String cptl_no = "";
		try {
			sql="select data_68 from flow_data_163 where run_id=?";
			con=DBpool.ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, run_id);
			rs=ps.executeQuery();
			if(rs.next()){
					cptl_no=rs.getString("data_68").trim();
			}
		}catch (Exception e) {
			log.info(" "+e);
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
	
		return cptl_no;
	}
	
	public String deleteAbolishAsset(String cptl_no){
		try {
			sql="DELETE FROM `cp_cptl_info` WHERE CPTL_NO=?";
			con=DBpool.ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, cptl_no);
			ps.executeUpdate();
			return "success";
		} catch (Exception e) {
			log.info("delete abolish asset wrong :"+e);
			return "bad";
		}
	}
	
	@RequestMapping(value="/assetAbolishSynch")
	public String assetAbolishSynch(@RequestParam(value="run_id",required=true)String run_id){
		log.info("run id "+run_id);
		String cptl_no=selectBasicInforData(run_id);
		String result="";
		if(!"".equals(cptl_no)){
			result=deleteAbolishAsset(cptl_no);
			if("success".equals(result)){
				log.info("delete is ok");
			}	
		}
		return result;
	}
	

}
