package com.cn.hnust.service;

import java.util.List;

public interface IProvinceService<T> {
	public List<T> queryByCountry(Long countryId);
	public T queryById(Long id);
}
