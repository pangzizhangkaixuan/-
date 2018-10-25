package com.zkx.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkx.cn.entity.Dept;
import com.zkx.cn.entity.Response;
import com.zkx.cn.mapper.DeptMapper;
import com.zkx.cn.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public Response<List<Dept>> deptList() {
		Response<List<Dept>> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		List<Dept> depts = deptMapper.list();
		response.setData(depts);
		return response;
	}
	
	@Override
	public Response<String> remove(Integer deptno) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = deptMapper.remove(deptno);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("删除失败");
		}
		
		response.setData(String.valueOf(result));
		return response;
	}
	
	@Override
	public Response<Dept> get(Integer deptno) {
		Response<Dept> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		Dept dept = deptMapper.get(deptno);
		response.setData(dept);
		return response;
	}
	
	@Override
	public Response<String> update(Dept dept) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = deptMapper.update(dept);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("修改失败");
		}
		
		response.setData(String.valueOf(result));
		return response;
	}
	
	@Override
	public Response<String> add(Dept dept) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = deptMapper.add(dept);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("添加失败");
		}
		
		response.setData(String.valueOf(result));
		return response;
	}

}
