package com.zkx.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkx.cn.service.WorkFlowService;

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
	WorkFlowService workFlow;
	
	@ApiOperation(value = "activiti集成测试", notes = "activiti集成测试")
	@GetMapping("/test")
	public void workFlowTest() {
		workFlow.workFlowTest();
	}
	
}
