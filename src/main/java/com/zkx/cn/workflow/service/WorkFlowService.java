package com.zkx.cn.workflow.service;

import com.zkx.cn.domain.BPMNParam;

/**
 * 流程控制接口
 * @author 张凯旋
 *
 */
public interface WorkFlowService {

	void workFlowTest();
	
	/**
	 * 启动任务
	 * @param param (link,rank可为空)
	 * @return 流程id用于绑定业务，为空则启动失败
	 */
	String startProcess(BPMNParam param);
	
	/**
	 * 执行任务
	 * @param param (属性processKey可为空，当link为default时rank可为空)
	 */
	void performTask (BPMNParam param);
	
	/**
	 * 获取任务id
	 * @param param (属性processId的值不能为空)
	 * @return TaskId
	 */
	String getTaskId(BPMNParam param);
}
