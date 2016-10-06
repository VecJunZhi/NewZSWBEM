package com.zs.crm.web.vo;

import java.util.HashMap;
import java.util.Map;

public class XsSaleManagerVo {
	private String followWayName;
	private int followTimes;
	private String firstFollowInfo;
	private int sets;//Ì×Êý
	private String money;
	private Map<String,Integer> groupInfo;
	public String getFollowWayName() {
		return followWayName;
	}
	public void setFollowWayName(String followWayName) {
		this.followWayName = followWayName;
	}
	public int getFollowTimes() {
		return followTimes;
	}
	public void setFollowTimes(int followTimes) {
		this.followTimes = followTimes;
	}
	public String getFirstFollowInfo() {
		return firstFollowInfo;
	}
	public void setFirstFollowInfo(String firstFollowInfo) {
		this.firstFollowInfo = firstFollowInfo;
	}
	public Map<String, Integer> getGroupInfo() {
		return groupInfo;
	}
	public void setGroupInfo(Map<String, Integer> groupInfo) {
		this.groupInfo = groupInfo;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public XsSaleManagerVo(String followWayName) {
		Map<String,Integer> group = new HashMap<String,Integer>();
		this.followWayName = followWayName;
		this.followTimes = 0;
		this.firstFollowInfo = "";
		group.put("A", 0);
		group.put("B", 0);
		group.put("C", 0);
		this.groupInfo = group;
		this.sets = 0;
		this.money = "";
	}
	
	
}
