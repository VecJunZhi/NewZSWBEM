package com.zs.common.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.common.util.StringUtil;
import com.zs.common.web.vo.TreeViewForMap;
import com.zs.rbac.entity.Organization;
import com.zs.rbac.service.IOrganizationService;
import com.zs.rbac.service.impl.OrganizationServiceImpl;

/**
 * 组织架构管理
 * @author jiarui
 * @version 1.0
 */
@Controller
@RequestMapping("/wbem/system/orgManage")
public class SystemOrgController {
	
	public void 组织架构管理(){}
	
	@RequestMapping("/orgTree")
	public String info(Model model) {
		return "/wbem/system/orgManage/orgTree";
	}
	
	@RequestMapping("/orgMemberFream")
	public String orgMemberFream(Model model) {
		return "/wbem/system/orgManage/orgMemberFream";
	}
	
	@RequestMapping("/orgPositionFream")
	public String orgPositionFream(Model model) {
		return "/wbem/system/orgManage/orgPositionFream";
	}
	
/*	@ResponseBody
	@RequestMapping(value="/loadOrgTree")
	public List<Organization> loadOrgTree(String parentID){
		//,method = RequestMethod.POST
		System.out.println("parentID=" + parentID);
		IOrganizationService orgService = new OrganizationServiceImpl();
		List<Organization> list =  orgService.getOrganizationByParentID(StringUtil.getInt(parentID, 0));
		return list;
	}*/
	
//	@ResponseBody
//	@RequestMapping(value="/loadOrgTree")
//	public List<TreeViewForMap> loadOrgTree(String parentID,String pid){
//		System.out.println("parentid=" + parentID);
//		System.out.println("pid=" + pid);
//		List<TreeViewForMap> result = new ArrayList<TreeViewForMap>();
//		IOrganizationService orgService = new OrganizationServiceImpl();
//		List<Organization> list =  orgService.getOrganizationByParentID(StringUtil.getInt(parentID, 0));
//		for(Organization org:list){
//			TreeViewForMap tvf = new TreeViewForMap();
//			tvf.put("name", org.getOrGName());
//			tvf.put("wid", org.getOrGID());
//			tvf.put("pid", org.getParentID());
//			tvf.set("isParent", "true");
//			result.add(tvf);
//		}
//		
//		//List<String> list = new ArrayList<String>();
//		//list.add("[{ id:1, pId:0, name:\"父节点1 - 展开\", open:true}]");
//		//return "[{ id:1, pId:0, name:\"1t1t1t1\", open:true}]";
//		return result;
//	}
}
