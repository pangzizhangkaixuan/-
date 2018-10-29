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
	private String processID;//流程ID
	private static BPMNParam param;

	private BPMNParam() {
	}

	public static BPMNParam getBPMNParam() {
		if (param == null) {

			synchronized (BPMNParam.class) {

				if (param == null) {
					param = new BPMNParam();
				}
			}
		}
		return param;
	}

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

	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}
}
