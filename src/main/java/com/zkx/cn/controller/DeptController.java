package com.zkx.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkx.cn.entity.Dept;
import com.zkx.cn.entity.Response;
import com.zkx.cn.service.DeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/demo")
@Api(value = "员工管理")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@GetMapping("/hw")
	public String helloworld() {
		return "hello world";
	}
	
	@ApiOperation(value = "查询所有员工信息", notes = "查询所有员工信息")
	@GetMapping("/getDepts")
	public Response<List<Dept>> getDepts() {
		return deptService.deptList();
	}
	
	@ApiOperation(value = "删除员工信息", notes = "删除员工信息")
	@GetMapping("/remove")
	public Response<String> remove(Integer deptno){
		return deptService.remove(deptno);
	}
	
	@ApiOperation(value = "获取某个员工信息", notes = "获取某个员工信息")
	@GetMapping("/getDept")
	public Response<Dept> getDeptByDeptno(Integer deptno){
		return deptService.get(deptno);
	}
	
	@ApiOperation(value = "保存修改员工信息", notes = "保存修改员工信息")
	@PostMapping("/updatesave")
	public Response<String> update(@RequestBody Dept dept){
		return deptService.update(dept);
	}
	
	@ApiOperation(value = "添加员工信息", notes = "添加员工信息")
	@PostMapping("/addsave")
	public Response<String> addsave(@RequestBody Dept dept){
		return deptService.add(dept);
	}
}
