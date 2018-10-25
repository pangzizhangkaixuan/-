package com.zkx.cn.service;

import java.util.List;

import com.zkx.cn.entity.Dept;
import com.zkx.cn.entity.Response;

public interface DeptService {
	/**
	 * 查询所有员工信息
	 * @return
	 */
	public Response<List<Dept>> deptList();
	
	/**
	 * 删除某个员工信息
	 * @param deptno 员工ID
	 * @return
	 */
	public Response<String> remove(Integer deptno);
	
	/**
	 * 获取某个员工信息
	 * @param deptno 员工ID
	 * @return
	 */
	public Response<Dept> get(Integer deptno);
	
	/**
	 * 更改员工信息
	 * @param dept
	 * @return
	 */
	public Response<String> update(Dept dept);
	
	/**
	 * 添加员工信息
	 * @param dept
	 * @return
	 */
	public Response<String> add(Dept dept);
}
