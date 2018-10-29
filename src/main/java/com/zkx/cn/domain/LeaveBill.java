package com.zkx.cn.domain;

import java.util.Date;

/**
 * 请假单
 * @author 张凯旋
 *
 */
public class LeaveBill {
	private String processID;//主键ID（为activiti启动时获取的流程ID用于绑定流程）
	private String days;// 请假天数
	private String userId;//请假人ID
	private String content;// 请假内容
	private Date leaveDate = new Date();// 请假时间
	private String remark;// 备注
	private Integer state=0;// 请假单状态 0初始录入,1.开始审批,2为审批完成

	public String getId() {
		return processID;
	}
	public void setId(String processID) {
		this.processID = processID;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
