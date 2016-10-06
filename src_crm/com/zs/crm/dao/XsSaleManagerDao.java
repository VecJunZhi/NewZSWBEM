package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsSaleDetailVo;
import com.zs.crm.entity.XsSaleManagerEntity;
import com.zs.crm.entity.XsSaleManagerShowEntity;
import com.zs.crm.entity.XsSignUpDetailVo;

/*在dao层加入@repository注解是为了自动扫描注入时，那些可以注入，还可以采用marketMapper的方式限制*/
@Repository
public interface XsSaleManagerDao {
	/**
	 * 
	 * @return
	 */
	public XsSaleManagerShowEntity getDayReportInfoDao();
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstByCycleAndFollowWayDao(XsSaleManagerEntity bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstByCycleAndStatusDao(XsSaleManagerEntity bean);
	/**
	 * 获取认购信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstSubscribeInfoByCycleDao(XsSaleManagerEntity bean);
	/**
	 * 获取转签约信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstSignUpInfoByCycleDao(XsSaleManagerEntity bean);
	/**
	 * 获取第一次来访的数量
	 * @param bean
	 * @return
	 */
	public String getFirstVisitCountByCycleDao(XsSaleManagerEntity bean);
	/**
	 * 根据时间段获取认购详情
	 * @param bean
	 * @return
	 */
	public XsSaleDetailVo getSaleDetailByCycleDao(XsSaleManagerEntity bean);
	/**
	 * 根据时间段获取转签约详情
	 * @param bean
	 * @return
	 */
	public XsSignUpDetailVo getSignUpDetailByCycleDao(XsSaleManagerEntity bean);
}
