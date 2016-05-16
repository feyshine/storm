package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProvinceMapper;
import com.cn.hnust.service.IProvinceService;
@Service("provinceService")
public class ProvinceServiceImp<T> implements IProvinceService<T> {

	@Resource
	private ProvinceMapper<T> provinceDao;
	
	@Override
	public List<T> queryByCountry(Long countryId) {
		return this.provinceDao.queryByCountry(countryId);
	}

	@Override
	public T queryById(Long id) {
		return provinceDao.selectByPrimaryKey(id);
	}

	

}
