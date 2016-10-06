package com.zs.busi.entity;

public class CountInfoVo {
	//回电回访数据
	private String group;
	private String zygw;
	private String newCusTel;  //'新客户接�?,
	private String oldCusTel;  //'老客户接�?,
	private String newCusAccess;  //'新客户接�?,
	private String oldCusAccess ; //'老客户接�?,
	private String toTel ; //'回电',
	private String toInterView ; //'回访',
	
	public CountInfoVo() {
		super();
	}
	
	public CountInfoVo(String group, String zygw, String newCusTel,
			String oldCusTel, String newCusAccess, String oldCusAccess,
			String toTel, String toInterView) {
		super();
		this.group = group;
		this.zygw = zygw;
		this.newCusTel = newCusTel;
		this.oldCusTel = oldCusTel;
		this.newCusAccess = newCusAccess;
		this.oldCusAccess = oldCusAccess;
		this.toTel = toTel;
		this.toInterView = toInterView;
	}

	//成交与�?�?
	private String name;
	private String roomDeal;  //'住宅成交',
	private String shopDeal; //'商铺成交',
	private String cellarDeal;  //'地下室成�?,
	private String carDeal;  //'车位成交',
	private String roomCheckOut ; //'住宅�?��',
	private String shopCheckOut;  //'商铺�?��',
	private String cellarCheckOut;  //'地下室�?�?,
	private String carCheckOut ; //'车位�?��',
	private String roomChange ; //'住宅换房',
	private String shopChange ; //'商铺换房',
	private String cellarChange ; //'地下室换�?,
	private String carChange;  //'车位换房'

	public CountInfoVo(String name, String roomDeal, String shopDeal,
			String cellarDeal, String carDeal, String roomCheckOut,
			String shopCheckOut, String cellarCheckOut, String carCheckOut,
			String roomChange, String shopChange, String cellarChange,
			String carChange) {
		super();
		this.name = name;
		this.roomDeal = roomDeal;
		this.shopDeal = shopDeal;
		this.cellarDeal = cellarDeal;
		this.carDeal = carDeal;
		this.roomCheckOut = roomCheckOut;
		this.shopCheckOut = shopCheckOut;
		this.cellarCheckOut = cellarCheckOut;
		this.carCheckOut = carCheckOut;
		this.roomChange = roomChange;
		this.shopChange = shopChange;
		this.cellarChange = cellarChange;
		this.carChange = carChange;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getZygw() {
		return zygw;
	}

	public void setZygw(String zygw) {
		this.zygw = zygw;
	}

	public String getNewCusTel() {
		return newCusTel;
	}

	public void setNewCusTel(String newCusTel) {
		this.newCusTel = newCusTel;
	}

	public String getOldCusTel() {
		return oldCusTel;
	}

	public void setOldCusTel(String oldCusTel) {
		this.oldCusTel = oldCusTel;
	}

	public String getNewCusAccess() {
		return newCusAccess;
	}

	public void setNewCusAccess(String newCusAccess) {
		this.newCusAccess = newCusAccess;
	}

	public String getOldCusAccess() {
		return oldCusAccess;
	}

	public void setOldCusAccess(String oldCusAccess) {
		this.oldCusAccess = oldCusAccess;
	}

	public String getToTel() {
		return toTel;
	}

	public void setToTel(String toTel) {
		this.toTel = toTel;
	}

	public String getToInterView() {
		return toInterView;
	}

	public void setToInterView(String toInterView) {
		this.toInterView = toInterView;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomDeal() {
		return roomDeal;
	}

	public void setRoomDeal(String roomDeal) {
		this.roomDeal = roomDeal;
	}

	public String getShopDeal() {
		return shopDeal;
	}

	public void setShopDeal(String shopDeal) {
		this.shopDeal = shopDeal;
	}

	public String getCellarDeal() {
		return cellarDeal;
	}

	public void setCellarDeal(String cellarDeal) {
		this.cellarDeal = cellarDeal;
	}

	public String getCarDeal() {
		return carDeal;
	}

	public void setCarDeal(String carDeal) {
		this.carDeal = carDeal;
	}

	public String getRoomCheckOut() {
		return roomCheckOut;
	}

	public void setRoomCheckOut(String roomCheckOut) {
		this.roomCheckOut = roomCheckOut;
	}

	public String getShopCheckOut() {
		return shopCheckOut;
	}

	public void setShopCheckOut(String shopCheckOut) {
		this.shopCheckOut = shopCheckOut;
	}

	public String getCellarCheckOut() {
		return cellarCheckOut;
	}

	public void setCellarCheckOut(String cellarCheckOut) {
		this.cellarCheckOut = cellarCheckOut;
	}

	public String getCarCheckOut() {
		return carCheckOut;
	}

	public void setCarCheckOut(String carCheckOut) {
		this.carCheckOut = carCheckOut;
	}

	public String getRoomChange() {
		return roomChange;
	}

	public void setRoomChange(String roomChange) {
		this.roomChange = roomChange;
	}

	public String getShopChange() {
		return shopChange;
	}

	public void setShopChange(String shopChange) {
		this.shopChange = shopChange;
	}

	public String getCellarChange() {
		return cellarChange;
	}

	public void setCellarChange(String cellarChange) {
		this.cellarChange = cellarChange;
	}

	public String getCarChange() {
		return carChange;
	}

	public void setCarChange(String carChange) {
		this.carChange = carChange;
	}

	
	

}
