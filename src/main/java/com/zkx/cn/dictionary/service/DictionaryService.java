package com.zkx.cn.dictionary.service;

import com.zkx.cn.domain.Dictionary;
import com.zkx.cn.domain.Response;

public interface DictionaryService {
	Response<String> remove(int id);
	
	Response<Dictionary> get(int id);
	
	Response<String> update(Dictionary dictionary);
	
	Response<String> add(Dictionary dictionary);
}
