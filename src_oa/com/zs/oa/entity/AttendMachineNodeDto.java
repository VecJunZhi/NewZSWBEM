package com.zs.oa.entity;

public class AttendMachineNodeDto {
	private  String id;
	private  String attendMachineCode;
	private  String lastTime;
	private  String machineStatus;
	private String machineIP;
	private String machinePort;
	private String machineSecretkey;
	private String machineCharset;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttendMachineCode() {
		return attendMachineCode;
	}
	public void setAttendMachineCode(String attendMachineCode) {
		this.attendMachineCode = attendMachineCode;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getMachineStatus() {
		return machineStatus;
	}
	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}
	
	public String getMachineIP() {
		return machineIP;
	}
	public void setMachineIP(String machineIP) {
		this.machineIP = machineIP;
	}
	public String getMachinePort() {
		return machinePort;
	}
	public void setMachinePort(String machinePort) {
		this.machinePort = machinePort;
	}
	public String getMachineSecretkey() {
		return machineSecretkey;
	}
	public void setMachineSecretkey(String machineSecretkey) {
		this.machineSecretkey = machineSecretkey;
	}
	public String getMachineCharset() {
		return machineCharset;
	}
	public void setMachineCharset(String machineCharset) {
		this.machineCharset = machineCharset;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public AttendMachineNodeDto() {
		super();
	}
	public AttendMachineNodeDto(String id, String attendMachineCode,
			String lastTime, String machineStatus, String machineIP,
			String machinePort, String machineSecretkey, String machineCharset,String remark) {
		super();
		this.id = id;
		this.attendMachineCode = attendMachineCode;
		this.lastTime = lastTime;
		this.machineStatus = machineStatus;
		this.machineIP = machineIP;
		this.machinePort = machinePort;
		this.machineSecretkey = machineSecretkey;
		this.machineCharset = machineCharset;
		this.remark = remark;
	}
	
	

}
