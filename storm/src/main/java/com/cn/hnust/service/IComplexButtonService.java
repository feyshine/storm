package com.cn.hnust.service;

import java.util.List;

public interface IComplexButtonService<T> {
	
	public int save(T t);
	public int update(T t);
	public int delte(Long id);
	public T query(Long id);
	public List<T> queryAll();
	public List<T> queryByPage(int row,int page);

}
