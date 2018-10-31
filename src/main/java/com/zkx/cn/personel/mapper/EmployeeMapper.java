package com.zkx.cn.personel.mapper;

import java.util.List;

import com.zkx.cn.domain.Employee;

public interface EmployeeMapper {
	List<Employee> list();
	int remove(String userId);
	Employee get(String userId);
	int update(Employee employee);
	int add(Employee employee);
	String getLeader(String userId);
}
