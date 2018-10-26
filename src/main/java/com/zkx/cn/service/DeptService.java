package com.zkx.cn.service;

import java.util.List;

import com.zkx.cn.entity.Dept;
import com.zkx.cn.entity.Response;

/**
 * 员工信息操作接口
 * @author 张凯旋
 *
 */
public interface DeptService {
	/**
	 * 查询所有员工信息
	 * @return
	 */
	Response<List<Dept>> deptList();
	
	/**
	 * 删除某个员工信息
	 * @param deptno 员工ID
	 * @return
	 */
	Response<String> remove(Integer deptno);
	
	/**
	 * 获取某个员工信息
	 * @param deptno 员工ID
	 * @return
	 */
	Response<Dept> get(Integer deptno);
	
	/**
	 * 更改员工信息
	 * @param dept
	 * @return
	 */
	Response<String> update(Dept dept);
	
	/**
	 * 添加员工信息
	 * @param dept
	 * @return
	 */
	Response<String> add(Dept dept);
}
