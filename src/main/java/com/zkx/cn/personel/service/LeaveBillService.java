package com.zkx.cn.personel.service;

import java.util.List;

import com.zkx.cn.domain.LeaveBill;
import com.zkx.cn.domain.Response;

public interface LeaveBillService {
	Response<List<LeaveBill>> LeaveBillList();
	
	Response<String> remove(String processID);
	
	Response<LeaveBill> get(String processID);
	
	Response<String> update(LeaveBill leaveBill);
	
	Response<String> add(LeaveBill leaveBill);
}
