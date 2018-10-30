package com.zkx.cn.personel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkx.cn.domain.LeaveBill;
import com.zkx.cn.domain.Response;
import com.zkx.cn.personel.mapper.LeaveBillMapper;
import com.zkx.cn.personel.service.LeaveBillService;
import com.zkx.cn.util.ListUtil;

@Service
public class LeaveBillServiceImpl implements LeaveBillService{

	@Autowired
	private LeaveBillMapper mapper;
	
	@Override
	public Response<List<LeaveBill>> LeaveBillList() {
		
		Response<List<LeaveBill>> response = new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		List<LeaveBill> list = mapper.list();
		
		if (ListUtil.isNotEmpty(list)) {
			response.setCode(Response.SUCCESS);
			response.setMsg(Response.SUCCESS_MESSAGE);
			response.setData(list);
		}
		
		return response;
	}

	@Override
	public Response<String> remove(String processID) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.remove(processID);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("删除失败,未找到对应数据");
		}
		
		return response;
	}

	@Override
	public Response<LeaveBill> get(String processID) {
		Response<LeaveBill> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		
		LeaveBill leaveBill = mapper.get(processID);
		response.setData(leaveBill);
		
		return response;
	}

	@Override
	public Response<String> update(LeaveBill leaveBill) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.update(leaveBill);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("修改失败,未找到对应数据");
		}
		return response;
	}

	@Override
	public Response<String> add(LeaveBill leaveBill) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		
		int result = mapper.add(leaveBill);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("添加失败");
		}
		return response;
	}

}
