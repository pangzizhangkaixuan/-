package com.zkx.cn.service.personel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zkx.cn.domain.Employee;
import com.zkx.cn.domain.Response;
import com.zkx.cn.mapper.EmployeeMapper;
import com.zkx.cn.service.personel.EmployeeService;
import com.zkx.cn.util.ListUtil;

public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeMapper mapper;
	
	@Override
	public Response<List<Employee>> employeeList() {
		Response<List<Employee>> response = new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		List<Employee> list = mapper.list();
		
		if (ListUtil.isNotEmpty(list)) {
			response.setCode(Response.SUCCESS);
			response.setMsg(Response.SUCCESS_MESSAGE);
			response.setData(list);
		}
		
		return response;
	}

	@Override
	public Response<String> remove(String userId) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.remove(userId);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("删除失败,未找到对应数据");
		}
		
		return response;
	}

	@Override
	public Response<Employee> get(String userId) {
		Response<Employee> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		
		Employee employee = mapper.get(userId);
		response.setData(employee);
		
		return response;
	}

	@Override
	public Response<String> update(Employee employee) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.update(employee);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("修改失败,未找到对应数据");
		}
		
		return response;
	}

	@Override
	public Response<String> add(Employee employee) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.add(employee);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("添加失败");
		}
		
		return response;
	}

}
