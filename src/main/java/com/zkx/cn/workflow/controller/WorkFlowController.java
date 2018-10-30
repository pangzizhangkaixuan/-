package com.zkx.cn.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkx.cn.domain.BPMNParam;
import com.zkx.cn.domain.LeaveBill;
import com.zkx.cn.personel.service.LeaveBillService;
import com.zkx.cn.workflow.service.WorkFlowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 张凯旋
 *
 */
@RestController
@RequestMapping("/workFlow")
@Api(value = "流程控制")
public class WorkFlowController {
	
	@Autowired
	private WorkFlowService workFlow;
	
	@Autowired
	private LeaveBillService leaveBillService;
	
	private BPMNParam param = BPMNParam.getBPMNParam();
	
	@ApiOperation(value = "activiti集成测试", notes = "activiti集成测试")
	@GetMapping("/test")
	public void workFlowTest() {
		workFlow.workFlowTest();
	}
	
	@ApiOperation(value = "申请提交", notes = "申请提交")
	@PostMapping("/apply")
	public void apply(LeaveBill leaveBill) {
		String userId = leaveBill.getUserId();
		param.setLink("jump");
		param.setRank(userId);
		String processID = workFlow.startProcess();
		param.setProcessID(processID);
		leaveBill.setProcessID(processID);
		workFlow.performTask(userId);
		
		leaveBillService.add(leaveBill);
	}
	
	@ApiOperation(value = "流程跳转", notes = "流程跳转")
	@PostMapping("/jump")
	public void jumpFlow() {
		
	}
	
	@ApiOperation(value = "默认流程执行", notes = "默认流程执行")
	@PostMapping("/default")
	public void defaultFlow() {
		
	}
}
