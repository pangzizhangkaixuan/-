package com.zkx.cn.personel.service;

import java.util.List;

import com.zkx.cn.domain.Dept;
import com.zkx.cn.domain.Response;

/**
 * 员工信息操作接口
 * @author 张凯旋
 *
 */
public interface DeptService {
	Response<List<Dept>> deptList();
	
	Response<String> remove(Integer deptno);
	
	Response<Dept> get(Integer deptno);
	
	Response<String> update(Dept dept);
	
	Response<String> add(Dept dept);
}
