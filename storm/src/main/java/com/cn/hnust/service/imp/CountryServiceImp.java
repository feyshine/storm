package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.CountryMapper;
import com.cn.hnust.service.ICountryService;
@Service("countryService")
public class CountryServiceImp<T> implements ICountryService<T> {

	@Resource
	private CountryMapper<T> countryDao;
	
	@Override
	public List<T> queryAll() {
		return this.countryDao.queryAll();
	}

}
