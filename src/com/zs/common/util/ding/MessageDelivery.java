package com.zs.common.util.ding;
import com.dingtalk.open.client.api.model.corp.MessageBody;
/**
 * 消息类
 * touser:员工id列表
 * toparty:部门id列表
 * agentid:企业应用id，这个值代表以哪个应用的名义发送消息.
 * msgType:消息类型
 * message:消息内容
 */
public class MessageDelivery {
	public String touser;
	public String toparty;
	public String agentid;
	public String msgType;
	public MessageBody message;
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public MessageBody getMessage() {
		return message;
	}
	public void setMessage(MessageBody message) {
		this.message = message;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public  MessageDelivery(String toUsers, String toParties, String agentId) {
		this.touser = toUsers;
		this.toparty = toParties;
		this.agentid = agentId;
	}
	public  MessageDelivery withMessage(String msgType, MessageBody msg) {
		this.msgType = msgType;
		this.message = msg;
		return this;
	}
}
