package com.zs.rbac.entity;

import java.io.Serializable;

/**
 * �˵�������
 * @author jiarui
 *
 */
public class Menu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int menuId;				//�˵�id
	
	private String menuName;		//�˵�����
	
	private int perentMenuId;		//�ϼ��˵�ID
	
	private Menu perentMenu;		//�ϼ��˵���Ϣ
	
	private String menuURL;			//�˵�URL
	
	private boolean available;		//�˵��Ƿ����
	
	private String menuDesc;		//�˵�����
	
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
