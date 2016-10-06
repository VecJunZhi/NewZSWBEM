package com.zs.common.util.ding;

public class DingDingVo {
	int count;
	String oppguid;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOppguid() {
		return oppguid;
	}
	public void setOppguid(String oppguid) {
		this.oppguid = oppguid;
	}
	public DingDingVo(int count, String oppguid) {
		super();
		this.count = count;
		this.oppguid = oppguid;
	}
	public DingDingVo() {
		super();
	}
}
