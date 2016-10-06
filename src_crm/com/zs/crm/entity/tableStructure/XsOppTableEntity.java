package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsOppTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oppGuid;//���ۻ���guid
    private String buGuid;//��λguid
    private String projGuid;//��Ŀguid
    private String leadGuid;//����guid
    private String topic;//����
    private String oppSource;//������Դ--������:���硢���á�����
    private String process;//�����׶�
    private String estRevenue;//���Ƽ�ֵ
    private String probability;//�ɽ�������
    private String estCloseDate;//���ƹر�ԭ��
    private String rating;//��������
    private String status;//״̬--������:Ӯ�á���ʧ
    private String statusReason;//״̬ԭ��
    private String createdOn;//����ʱ��
    private String createdBy;//������
    private String closedOn;//�ر�����
    private String competitor;//��������
    private String cjTotal;//ʵ�ʼ�ֵ
    private String description;//����
    private String roomGuid;//����guid
    private String modifyBy;//�޸���
    private String modifyOn;//�޸�ʱ��
    private String userGuid;//ҵ��Աguid
    private String jzfx;//��������
    private String createdByGuid;//������guid
    private Byte isCreatorUse;//Ĭ��1
    private String cstGuid;//�ͻ�guid
    private String mainMediaName;//��֪;��������
    private String subMediaName;//��֪;��������
    private String gfys;//����Ԥ��
    private String gfyt;//������;
    private String xqts;//��������
    private String gzqs;//��ע����
    private String gzjg;//��ע����
    private String gzfm1;//��ע����1
    private String gzfm2;//��ע����2
    private String gzfm3;//��ע����3
    private String dsYype;
    private String dsReason;
    private String dsRemark;
    private String zygw;//��ҵ����
    private String zygwGuid;//��ҵ����guid
    private String lastDate;//�ϴθ�������
    private String nextDate;//�´θ�������
    private String nextContent;//�´θ�������
    private String gfyx;//��������
    private Integer fpNum;
    private String fpr;
    private String fprGuid;
    private String fpTime;
    private String oldZygw;//ԭ��ҵ����
    private Integer dfNum;//���ô���
    private String firstGfyx;//��һ�ι�������
    private String oldUserGuid;//ԭҵ��Աguid
    private String lastGjjlGuid;//���һ�θ�����¼��guid
    private String scrmTimestampData;//ʱ���
    private int statusFlg;//0 ��ʧ      1 ��ѯ      2 ����       3 ԤԼ         4 �Ϲ�           5 ǩԼ 
    
	public int getStatusFlg() {
		return statusFlg;
	}
	public void setStatusFlg(int statusFlg) {
		this.statusFlg = statusFlg;
	}
	public String getOppGuid() {
		return oppGuid;
	}
	public void setOppGuid(String oppGuid) {
		this.oppGuid = oppGuid;
	}
	public String getBuGuid() {
		return buGuid;
	}
	public void setBuGuid(String buGuid) {
		this.buGuid = buGuid;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public String getLeadGuid() {
		return leadGuid;
	}
	public void setLeadGuid(String leadGuid) {
		this.leadGuid = leadGuid;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getOppSource() {
		return oppSource;
	}
	public void setOppSource(String oppSource) {
		this.oppSource = oppSource;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getEstRevenue() {
		return estRevenue;
	}
	public void setEstRevenue(String estRevenue) {
		this.estRevenue = estRevenue;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getEstCloseDate() {
		return estCloseDate;
	}
	public void setEstCloseDate(String estCloseDate) {
		this.estCloseDate = estCloseDate;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getClosedOn() {
		return closedOn;
	}
	public void setClosedOn(String closedOn) {
		this.closedOn = closedOn;
	}
	public String getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	public String getCjTotal() {
		return cjTotal;
	}
	public void setCjTotal(String cjTotal) {
		this.cjTotal = cjTotal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoomGuid() {
		return roomGuid;
	}
	public void setRoomGuid(String roomGuid) {
		this.roomGuid = roomGuid;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getUserGuid() {
		return userGuid;
	}
	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}
	public String getJzfx() {
		return jzfx;
	}
	public void setJzfx(String jzfx) {
		this.jzfx = jzfx;
	}
	public String getCreatedByGuid() {
		return createdByGuid;
	}
	public void setCreatedByGuid(String createdByGuid) {
		this.createdByGuid = createdByGuid;
	}
	public Byte getIsCreatorUse() {
		return isCreatorUse;
	}
	public void setIsCreatorUse(Byte isCreatorUse) {
		this.isCreatorUse = isCreatorUse;
	}
	public String getCstGuid() {
		return cstGuid;
	}
	public void setCstGuid(String cstGuid) {
		this.cstGuid = cstGuid;
	}
	public String getMainMediaName() {
		return mainMediaName;
	}
	public void setMainMediaName(String mainMediaName) {
		this.mainMediaName = mainMediaName;
	}
	public String getSubMediaName() {
		return subMediaName;
	}
	public void setSubMediaName(String subMediaName) {
		this.subMediaName = subMediaName;
	}
	public String getGfys() {
		return gfys;
	}
	public void setGfys(String gfys) {
		this.gfys = gfys;
	}
	public String getGfyt() {
		return gfyt;
	}
	public void setGfyt(String gfyt) {
		this.gfyt = gfyt;
	}
	public String getXqts() {
		return xqts;
	}
	public void setXqts(String xqts) {
		this.xqts = xqts;
	}
	public String getGzqs() {
		return gzqs;
	}
	public void setGzqs(String gzqs) {
		this.gzqs = gzqs;
	}
	public String getGzjg() {
		return gzjg;
	}
	public void setGzjg(String gzjg) {
		this.gzjg = gzjg;
	}
	public String getGzfm1() {
		return gzfm1;
	}
	public void setGzfm1(String gzfm1) {
		this.gzfm1 = gzfm1;
	}
	public String getGzfm2() {
		return gzfm2;
	}
	public void setGzfm2(String gzfm2) {
		this.gzfm2 = gzfm2;
	}
	public String getGzfm3() {
		return gzfm3;
	}
	public void setGzfm3(String gzfm3) {
		this.gzfm3 = gzfm3;
	}
	public String getDsYype() {
		return dsYype;
	}
	public void setDsYype(String dsYype) {
		this.dsYype = dsYype;
	}
	public String getDsReason() {
		return dsReason;
	}
	public void setDsReason(String dsReason) {
		this.dsReason = dsReason;
	}
	public String getDsRemark() {
		return dsRemark;
	}
	public void setDsRemark(String dsRemark) {
		this.dsRemark = dsRemark;
	}
	public String getZygw() {
		return zygw;
	}
	public void setZygw(String zygw) {
		this.zygw = zygw;
	}
	public String getZygwGuid() {
		return zygwGuid;
	}
	public void setZygwGuid(String zygwGuid) {
		this.zygwGuid = zygwGuid;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getNextDate() {
		return nextDate;
	}
	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}
	public String getNextContent() {
		return nextContent;
	}
	public void setNextContent(String nextContent) {
		this.nextContent = nextContent;
	}
	public String getGfyx() {
		return gfyx;
	}
	public void setGfyx(String gfyx) {
		this.gfyx = gfyx;
	}
	public Integer getFpNum() {
		return fpNum;
	}
	public void setFpNum(Integer fpNum) {
		this.fpNum = fpNum;
	}
	public String getFpr() {
		return fpr;
	}
	public void setFpr(String fpr) {
		this.fpr = fpr;
	}
	public String getFprGuid() {
		return fprGuid;
	}
	public void setFprGuid(String fprGuid) {
		this.fprGuid = fprGuid;
	}
	public String getFpTime() {
		return fpTime;
	}
	public void setFpTime(String fpTime) {
		this.fpTime = fpTime;
	}
	public String getOldZygw() {
		return oldZygw;
	}
	public void setOldZygw(String oldZygw) {
		this.oldZygw = oldZygw;
	}
	public Integer getDfNum() {
		return dfNum;
	}
	public void setDfNum(Integer dfNum) {
		this.dfNum = dfNum;
	}
	public String getFirstGfyx() {
		return firstGfyx;
	}
	public void setFirstGfyx(String firstGfyx) {
		this.firstGfyx = firstGfyx;
	}
	public String getOldUserGuid() {
		return oldUserGuid;
	}
	public void setOldUserGuid(String oldUserGuid) {
		this.oldUserGuid = oldUserGuid;
	}
	public String getLastGjjlGuid() {
		return lastGjjlGuid;
	}
	public void setLastGjjlGuid(String lastGjjlGuid) {
		this.lastGjjlGuid = lastGjjlGuid;
	}
	public String getScrmTimestampData() {
		return scrmTimestampData;
	}
	public void setScrmTimestampData(String scrmTimestampData) {
		this.scrmTimestampData = scrmTimestampData;
	}
	
}
