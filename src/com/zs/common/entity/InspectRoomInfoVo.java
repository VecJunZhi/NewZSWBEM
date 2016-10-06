package com.zs.common.entity;

import java.io.Serializable;

import com.zs.rbac.entity.User;

public class InspectRoomInfoVo implements Serializable{

	private static final long serialVersionUID = 1L;
	BuildingExtTableEntity buildExt;
	InspectRoomTableEntity inspectRoom;
	IssueFeedbackTableEntity issueFeedback;
	IssuesTypeTableEntity issuesType;
	IssuesRoomTableEntity issuesRoom;
	IssuesDetailTableEntity issuesDetail;
	BuildingTableEntity build;
	RoomTableEntity room;
	User user;
	
	public BuildingExtTableEntity getBuildExt() {
		return buildExt;
	}
	public void setBuildExt(BuildingExtTableEntity buildExt) {
		this.buildExt = buildExt;
	}
	public InspectRoomTableEntity getInspectRoom() {
		return inspectRoom;
	}
	public void setInspectRoom(InspectRoomTableEntity inspectRoom) {
		this.inspectRoom = inspectRoom;
	}
	public IssueFeedbackTableEntity getIssueFeedback() {
		return issueFeedback;
	}
	public void setIssueFeedback(IssueFeedbackTableEntity issueFeedback) {
		this.issueFeedback = issueFeedback;
	}
	public IssuesTypeTableEntity getIssuesType() {
		return issuesType;
	}
	public void setIssuesType(IssuesTypeTableEntity issuesType) {
		this.issuesType = issuesType;
	}
	public IssuesRoomTableEntity getIssuesRoom() {
		return issuesRoom;
	}
	public void setIssuesRoom(IssuesRoomTableEntity issuesRoom) {
		this.issuesRoom = issuesRoom;
	}
	public IssuesDetailTableEntity getIssuesDetail() {
		return issuesDetail;
	}
	public void setIssuesDetail(IssuesDetailTableEntity issuesDetail) {
		this.issuesDetail = issuesDetail;
	}
	public BuildingTableEntity getBuild() {
		return build;
	}
	public void setBuild(BuildingTableEntity build) {
		this.build = build;
	}
	public RoomTableEntity getRoom() {
		return room;
	}
	public void setRoom(RoomTableEntity room) {
		this.room = room;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public InspectRoomInfoVo(){
		buildExt = new BuildingExtTableEntity();
		inspectRoom = new InspectRoomTableEntity();
		issueFeedback = new IssueFeedbackTableEntity();
		issuesType = new IssuesTypeTableEntity();
		issuesRoom = new IssuesRoomTableEntity();
		issuesDetail = new IssuesDetailTableEntity();
		build = new BuildingTableEntity();
		room = new RoomTableEntity();
		user = new User();
	}
}
