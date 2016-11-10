package com.cn.hnust.service;

import java.util.List;

public interface IImageService<T> {

	public void save(T t);
	public void delete(Long id);
	public T select(Long id);
	public void updateByPrimaryKeySelective(T t);
	public void updateByPrimaryKey(T t);
	public List<T> queryAll();
	public List<T> queryTByPageSize(int row,int page);
}
