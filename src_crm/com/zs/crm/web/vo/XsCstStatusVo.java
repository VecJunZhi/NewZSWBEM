package com.zs.crm.web.vo;

public class XsCstStatusVo {
	private XsSaleManagerVo subscribe;
	private XsSaleManagerVo sign;
	public XsSaleManagerVo getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(XsSaleManagerVo subscribe) {
		this.subscribe = subscribe;
	}
	public XsSaleManagerVo getSign() {
		return sign;
	}
	public void setSign(XsSaleManagerVo sign) {
		this.sign = sign;
	}
	public XsCstStatusVo() {
		this.subscribe = new XsSaleManagerVo("�Ϲ�");
		this.sign = new XsSaleManagerVo("תǩԼ");
	}
}
