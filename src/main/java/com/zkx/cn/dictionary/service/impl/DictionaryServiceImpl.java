package com.zkx.cn.dictionary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkx.cn.dictionary.mapping.DictionaryMapper;
import com.zkx.cn.dictionary.service.DictionaryService;
import com.zkx.cn.domain.Dictionary;
import com.zkx.cn.domain.Response;

@Service
public class DictionaryServiceImpl implements DictionaryService{

	@Autowired
	private DictionaryMapper mapper;
	
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
	public Response<Dictionary> get(int id) {
		Response<Dictionary> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		
		Dictionary dictionary = mapper.selectByPrimaryKey(id);
		response.setData(dictionary);
		
		return response;
	}

	@Override
	public Response<String> update(Dictionary dictionary) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.updateByPrimaryKeySelective(dictionary);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("修改失败,未找到对应数据");
		}
		
		return response;
	}

	@Override
	public Response<String> add(Dictionary dictionary) {
		Response<String> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		int result = mapper.insert(dictionary);
		
		if (result == 0) {
			response.setCode(Response.ERROR);
			response.setMsg("添加失败");
		}
		
		return response;
	}
}
