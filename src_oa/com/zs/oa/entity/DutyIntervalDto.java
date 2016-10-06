package com.zs.oa.entity;

public class DutyIntervalDto {
	
	private String duty_interval_after1;
	private String duty_interval_after2;
	private String duty_interval_before1;
	private String duty_interval_before2;
	public String getDuty_interval_after1() {
		return duty_interval_after1;
	}
	public void setDuty_interval_after1(String duty_interval_after1) {
		this.duty_interval_after1 = duty_interval_after1;
	}
	public String getDuty_interval_after2() {
		return duty_interval_after2;
	}
	public void setDuty_interval_after2(String duty_interval_after2) {
		this.duty_interval_after2 = duty_interval_after2;
	}
	public String getDuty_interval_before1() {
		return duty_interval_before1;
	}
	public void setDuty_interval_before1(String duty_interval_before1) {
		this.duty_interval_before1 = duty_interval_before1;
	}
	public String getDuty_interval_before2() {
		return duty_interval_before2;
	}
	public void setDuty_interval_before2(String duty_interval_before2) {
		this.duty_interval_before2 = duty_interval_before2;
	}
	public DutyIntervalDto(String duty_interval_after1,
			String duty_interval_after2, String duty_interval_before1,
			String duty_interval_before2) {
		super();
		this.duty_interval_after1 = duty_interval_after1;
		this.duty_interval_after2 = duty_interval_after2;
		this.duty_interval_before1 = duty_interval_before1;
		this.duty_interval_before2 = duty_interval_before2;
	}
	public DutyIntervalDto() {
		super();
	}
	
	

}
