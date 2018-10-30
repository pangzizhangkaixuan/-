package com.zkx.cn.personel.service;

import java.util.List;

import com.zkx.cn.domain.Employee;
import com.zkx.cn.domain.Response;

public interface EmployeeService {
	Response<List<Employee>> employeeList();
	
	Response<String> remove(String userId);
	
	Response<Employee> get(String userId);
	
	Response<String> update(Employee employee);
	
	Response<String> add(Employee employee);
}
