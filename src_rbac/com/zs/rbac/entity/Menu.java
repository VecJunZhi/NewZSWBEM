package com.zs.rbac.entity;

import java.io.Serializable;

/**
 * 菜单基础类
 * @author jiarui
 *
 */
public class Menu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int menuId;				//菜单id
	
	private String menuName;		//菜单名称
	
	private int perentMenuId;		//上级菜单ID
	
	private Menu perentMenu;		//上级菜单信息
	
	private String menuURL;			//菜单URL
	
	private boolean available;		//菜单是否可用
	
	private String menuDesc;		//菜单描述
	
	private String menuMark;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getPerentMenuId() {
		return perentMenuId;
	}

	public void setPerentMenuId(int perentMenuId) {
		this.perentMenuId = perentMenuId;
	}

	public Menu getPerentMenu() {
		return perentMenu;
	}

	public void setPerentMenu(Menu perentMenu) {
		this.perentMenu = perentMenu;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuMark() {
		return menuMark;
	}

	public void setMenuMark(String menuMark) {
		this.menuMark = menuMark;
	}
	
}
