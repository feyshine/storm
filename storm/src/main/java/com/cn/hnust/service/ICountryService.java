package com.cn.hnust.service;

import java.util.List;

public interface ICountryService<T> {
	public List<T> queryAll();
	public T queryById(Long id);
}
