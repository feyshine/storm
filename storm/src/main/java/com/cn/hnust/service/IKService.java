package com.cn.hnust.service;

import java.util.List;

public interface IKService<T> {
	
	public void save(T service);
	public void delete(String account);
	public T select(String account);
	public void updateByPrimaryKeySelective(T service);
	public void updateByPrimaryKey(T service);
	public List<T> queryAll();
	public List<T> queryTByPageSize(int row,int page);

}
