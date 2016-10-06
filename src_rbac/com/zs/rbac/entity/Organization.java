package com.zs.rbac.entity;

import java.util.Date;

public class Organization {
    private Integer orgid;

    private String orgname;

    private Integer orgtype;

    private String description;

    private Integer parentid;

    private String priority;

    private Integer available;

    private Date createtime;

    private Date lasttime;

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public Integer getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(Integer orgtype) {
        this.orgtype = orgtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

	public Organization() {
		super();
	}

	public Organization(Integer orgid, String orgname, Integer orgtype,
			String description, Integer parentid, String priority,
			Integer available, Date createtime, Date lasttime) {
		super();
		this.orgid = orgid;
		this.orgname = orgname;
		this.orgtype = orgtype;
		this.description = description;
		this.parentid = parentid;
		this.priority = priority;
		this.available = available;
		this.createtime = createtime;
		this.lasttime = lasttime;
	}

	public Organization(String orgname, Integer orgtype, String description,
			Integer parentid, String priority, Integer available,
			Date createtime, Date lasttime) {
		super();
		this.orgname = orgname;
		this.orgtype = orgtype;
		this.description = description;
		this.parentid = parentid;
		this.priority = priority;
		this.available = available;
		this.createtime = createtime;
		this.lasttime = lasttime;
	}
    
}