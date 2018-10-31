package com.zkx.cn.organization.service;

import com.zkx.cn.domain.Organization;
import com.zkx.cn.domain.Response;

public interface OrganizationService {
	
	Response<String> remove(int id);
	
	Response<Organization> get(int id);
	
	Response<String> update(Organization organization);
	
	Response<String> add(Organization organization);
}
