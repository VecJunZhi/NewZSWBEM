package com.zs.oa.web.action;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.opensymphony.xwork2.inject.Inject;
import com.zs.oa.entity.AssetInStorageDto;
import com.zs.oa.util.DBpool;


@Controller
@RequestMapping(value="oa/assetInstorage")
public class AssetInStorageSynch {
	
	Log log= LogFactory.getLog(AssetInStorageSynch.class);
	@Autowired
	HttpServletRequest request;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String sql=null;		
	public String getRequestData()
		{   
			String run_id=(String) request.getParameter("run_id");
			return run_id;
		}

	public List<AssetInStorageDto> selectInStorageList(String run_id){
		List<AssetInStorageDto> list = new ArrayList<AssetInStorageDto>();
		AssetInStorageDto dto;
		String sql="";
		String cptl_id="";
		String cptl_name="";
		String type_id="";
		String dept_id="";
		String create_date="";
		String from_yymm="";
		String keeper="";
		String remark="";
		String money="";
		try {
			sql="SELECT A.item_id,A.item_name,A.item_type,A.item_deptid,A.item_createdate,A.item_fromdate,A.item_keeper,A.item_remark,A.item_money,B.TYPE_ID,C.DEPT_ID, D.USER_ID FROM `flow_data_167_list_58` A,cp_asset_type B,department C,hr_staff_info D WHERE B.TYPE_NAME=A.item_type AND C.DEPT_NAME=A.item_deptid AND D.STAFF_NAME=A.item_keeper AND A.run_id=?";
			con=DBpool.ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, run_id);
			rs=ps.executeQuery();
			while(rs.next()){
				cptl_id=rs.getString("item_id");
				cptl_name=rs.getString("item_name");
				type_id=rs.getString("TYPE_ID");
				dept_id=rs.getString("DEPT_ID");
				money=rs.getString("item_money");
				create_date=rs.getString("item_createdate");
				from_yymm=rs.getString("item_fromdate");
				keeper=rs.getString("USER_ID");
				remark=rs.getString("item_remark");
				dto=new AssetInStorageDto(cptl_id, cptl_name, type_id, dept_id, money, create_date, from_yymm, keeper, remark);
				list.add(dto);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info(" wrong "+e);
			e.printStackTrace();
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
	
	public String insertAssetInStorge(String cptl_id,String cptl_name,String type_id,String dept_id,
										String create_date,String from_yymm,String keeper,String remark,String money){
		try {
				sql="INSERT INTO cp_cptl_info(CPTL_NO,CPTL_NAME,TYPE_ID,DEPT_ID,CREATE_DATE,FROM_YYMM,KEEPER,REMARK,ATTACHMENT_NAME,CPTL_VAL) VALUES(?,?,?,?,?,?,?,?,?,?)";
				con=DBpool.ds.getConnection();
				ps=con.prepareStatement(sql);
				ps.setString(1, cptl_id);
				ps.setString(2, cptl_name);
				ps.setString(3, type_id);
				ps.setString(4, dept_id);
				ps.setString(5, create_date);
				ps.setString(6, from_yymm);
				ps.setString(7, keeper);
				ps.setString(8, remark);
				ps.setString(9, "");
				ps.setString(10, money);
			    ps.executeUpdate();
			    return "success";
		} catch (Exception e) {
			// TODO: handle exception
			log.info("inser asset instorge data wrong :"+e);
			return "bad";
		}finally{
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
		
	}
	@RequestMapping(value="assetInStorageSynch")
	public void assetInStorageSynch (@RequestParam(value="run_id",required=true)String run_id){
		log.info(run_id);
		String cptl_id="";
		String cptl_name="";
		String type_id="";
		String dept_id="";
		String create_date="";
		String from_yymm="";
		String keeper="";
		String remark="";
		String money="";
		AssetInStorageDto dto;
		//String run_id=getRequestData();
		List<AssetInStorageDto> list=selectInStorageList(run_id);
		
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				dto=list.get(i);
				cptl_id=dto.getCptl_id();
				cptl_name=dto.getCptl_name();
				type_id=dto.getType_id();
				dept_id=dto.getDept_id();
				create_date=dto.getCreate_date();
				from_yymm=dto.getFrom_yymm();
				keeper=dto.getKeeper();
				remark=dto.getRemark();
				money=dto.getMoney();
				insertAssetInStorge(cptl_id, cptl_name, type_id, dept_id, create_date, from_yymm, keeper, remark,money);
			}
			log.info("asset in storage synch completed");
		}
		
	}
	
}
