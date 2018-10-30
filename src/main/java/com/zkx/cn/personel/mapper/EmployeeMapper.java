package com.zkx.cn.personel.mapper;

import java.util.List;

import com.zkx.cn.domain.Employee;

public interface EmployeeMapper {
	public List<Employee> list();
	public int remove(String userId);
	public Employee get(String userId);
	public int update(Employee employee);
	public int add(Employee employee);
}
