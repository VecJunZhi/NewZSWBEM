package com.zs.oa.entity;

public class Employee {
	public String time;
	public String id;
	public String name;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Employee(String time, String id, String name) {
		super();
		this.time = time;
		this.id = id;
		this.name = name;
	}
	public Employee() {
		super();
	}
	

}
