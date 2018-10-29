package com.zkx.cn.mapper;

import java.util.List;

import com.zkx.cn.domain.LeaveBill;

public interface LeaveBillMapper {
	public List<LeaveBill> list();
	public int remove(String id);
	public LeaveBill get(String id);
	public int update(LeaveBill leaveBill);
	public int add(LeaveBill leaveBill);
}
