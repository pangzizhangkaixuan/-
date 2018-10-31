package com.zkx.cn.domain;

/**
 * 流程参数对象
 * @author 张凯旋
 *
 */
public class BPMNParam {

	private String rank;// 回退职级
	private String link;// 流程方向（jump回退，default默认下一级）
	private String role;// 节点执行人
	private String processId;//流程ID
	private String processKey;//流程图key值对应不同流程
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getProcessKey() {
		return processKey;
	}
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
}
