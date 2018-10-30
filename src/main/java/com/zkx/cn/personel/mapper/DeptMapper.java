package com.zkx.cn.personel.mapper;

import java.util.List;

import com.zkx.cn.domain.Dept;
public interface DeptMapper {
	public List<Dept> list();
	public int remove(Integer deptno);
	public Dept get(Integer deptno);
	public int update(Dept dept);
	public int add(Dept dept);
}
