package com.zkx.cn.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkx.cn.domain.Organization;
import com.zkx.cn.domain.Response;
import com.zkx.cn.organization.mapping.OrganizationMapper;
import com.zkx.cn.organization.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	private OrganizationMapper mapper;
	
	@Override
	public Response<String> remove(int id) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.deleteByPrimaryKey(id);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("删除失败,未找到对应数据");
		}
		
		return response;
	}

	@Override
	public Response<Organization> get(int id) {
		Response<Organization> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		
		Organization organization = mapper.selectByPrimaryKey(id);
		response.setData(organization);
		
		return response;
	}

	@Override
	public Response<String> update(Organization organization) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.updateByPrimaryKeySelective(organization);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("修改失败,未找到对应数据");
		}
		
		return response;
	}

	@Override
	public Response<String> add(Organization organization) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.insert(organization);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("添加失败");
		}
		
		return response;
	}
	
	
}
