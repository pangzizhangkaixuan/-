package com.zkx.cn.workflow.service;

/**
 * 流程控制接口
 * @author 张凯旋
 *
 */
public interface WorkFlowService {

	void workFlowTest();
	
	/**
	 * 启动任务
	 * @param param
	 * @return
	 */
	String startProcess();
	
	/**
	 * 执行任务
	 * @param param
	 * @param userId
	 */
	void performTask (String userId);
}
