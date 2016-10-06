package com.zs.rbac.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.rbac.dao.ISysMenuDao;
import com.zs.rbac.dao.factory.DaoImplFactory;
import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.SubSystem;
import com.zs.rbac.service.ISysMenuService;
import com.zs.rbac.service.factory.ServiceImplFactory;

/**
 * 
 * @author JiaRui
 *
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
	
	private ISysMenuDao menuDao = DaoImplFactory.getSysMenuInstance();
	
//	@Autowired
//	private ISysMenuDao menuDao;

	@Override
	public List<Menu> getAllMenuByUserID(int userID) {
		if(userID <= 0)
			return new ArrayList<Menu>();
		return menuDao.getMenuByUserID(userID);
	}
	
	@Override
	public List<SubSystem> getSubSystemID(int userID) {
		if(userID <= 0)
			return new ArrayList<SubSystem>();
		return menuDao.getSubSystemByUserID(userID);
	}
	
	
	public Map<String, Map<String,List<Menu>>> getMenuByUserID(int userID){
		System.out.println("allMenu111");
		if(userID <= 0)
			return new HashMap<String, Map<String,List<Menu>>>();
		System.out.println("menuDao"+menuDao);
		List<Menu> allMenu = menuDao.getMenuByUserID(userID);
		System.out.println("allMenu"+allMenu);
		//临时存放根菜单信息
		Map<Integer,Menu> tmp = new HashMap<Integer,Menu>();
		for(Menu menu:allMenu ){
			String [] pMarks = menu.getMenuMark().split(":");
			if(pMarks != null && pMarks.length == 2){
				tmp.put(menu.getMenuId(), menu);			
			}
		}
		
		//子目录菜单信息
		List<Menu> childMenu = null;    
		//主目录与子目录MAP映射
		Map<String,List<Menu>> menuMap = null;
		//最终封装好的MAP，包括子按系统区分menuMap信息
		Map<String, Map<String,List<Menu>>> result = new HashMap<String, Map<String,List<Menu>>>();
		for(Menu menu:allMenu ){
			if(tmp.get(menu.getPerentMenuId()) == null){
				continue;
			}
			//根据上级ID在临时MAP里查询菜单归属信息
			Menu obj = tmp.get(menu.getPerentMenuId());
			String menuName = tmp.get(menu.getPerentMenuId()).getMenuName();
			
			if(result.get(obj.getPerentMenuId()+"") == null ){		//生成最终返回类型
				childMenu = new ArrayList<Menu>();
				menuMap = new HashMap<String,List<Menu>>();
				childMenu.add(menu);
				menuMap.put(menuName+":"+obj.getMenuId(), childMenu);
				result.put(obj.getPerentMenuId()+"", menuMap);
			}else if(result.get(obj.getPerentMenuId()+"").get(menuName+":"+obj.getMenuId()) == null){	
				//menuMap有多个KEY时分别封装KEY对映的LIST菜单信息
				childMenu = new ArrayList<Menu>();
				childMenu.add(menu);
				result.get(obj.getPerentMenuId()+"").put(menuName+":"+obj.getMenuId(),childMenu);
			}else{
				result.get(obj.getPerentMenuId()+"").get(menuName+":"+obj.getMenuId()).add(menu);
			}
				
			
		}
		Iterator<String> miner = result.keySet().iterator();
		while(miner.hasNext()){
			String  k = miner.next();
			Map<String,List<Menu>> map = result.get(k);
			Iterator<String> iner = map.keySet().iterator();
			while(iner.hasNext()){
				String  key = iner.next();
				List<Menu> list =  map.get(key);
				if(list.size() == 0){
					result.get(k).get(key).remove(key);
				}	
			}
		}		
		return result;
		
		
	}

	public static void main(String[] args) {
		Map<String, Map<String,List<Menu>>> menu = ServiceImplFactory.getSysMenuServiceInstance().getMenuByUserID(1);
		System.out.println(menu.size() + "size");
		Iterator<String> miner = menu.keySet().iterator();
		while(miner.hasNext()){
			String  k = miner.next();
			Map<String,List<Menu>> map = menu.get(k);
			System.out.println(map.size() + "size1");
			Iterator<String> iner = map.keySet().iterator();
			while(iner.hasNext()){
				String  key = iner.next();
				List<Menu> list =  map.get(key);
				System.out.println(list.size() + "size2");
				for(Menu m : list)
					System.out.println(key + "- val=" + m.getMenuName() + "id=" + m.getMenuId() + "perid===" + m.getPerentMenuId());
			}
		}
		
		List<SubSystem> list = ServiceImplFactory.getSysMenuServiceInstance().getSubSystemID(1);
		for(SubSystem sub:list){
			//System.out.println(sub.getSubSystemName());
		}
		//System.out.println(ServiceImplFactory.getSysMenuServiceInstance().getMenuByUserID(1).size());;
	}
	
	
	
}
