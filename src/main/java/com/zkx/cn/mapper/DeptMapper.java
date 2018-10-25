package com.zkx.cn.mapper;

import java.util.List;

import com.zkx.cn.entity.Dept;
public interface DeptMapper {
	public List<Dept> list();
	public int remove(Integer deptno);
	public Dept get(Integer deptno);
	public int update(Dept dept);
	public int add(Dept dept);
}
