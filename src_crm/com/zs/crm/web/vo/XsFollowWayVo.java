package com.zs.crm.web.vo;

public class XsFollowWayVo {
	XsSaleManagerVo call;
	XsSaleManagerVo visit;
	XsSaleManagerVo toCall;
	XsSaleManagerVo other;
	public XsSaleManagerVo getCall() {
		return call;
	}
	public void setCall(XsSaleManagerVo call) {
		this.call = call;
	}
	public XsSaleManagerVo getVisit() {
		return visit;
	}
	public void setVisit(XsSaleManagerVo visit) {
		this.visit = visit;
	}
	public XsSaleManagerVo getToCall() {
		return toCall;
	}
	public void setToCall(XsSaleManagerVo toCall) {
		this.toCall = toCall;
	}
	public XsSaleManagerVo getOther() {
		return other;
	}
	public void setOther(XsSaleManagerVo other) {
		this.other = other;
	}
	public XsFollowWayVo() {
		this.call = new XsSaleManagerVo("�ͻ�����");
		this.visit = new XsSaleManagerVo("�������");
		this.toCall = new XsSaleManagerVo("�ֳ��Ӵ�");
		this.other = new XsSaleManagerVo("����������ʽ");
	}
	
}
