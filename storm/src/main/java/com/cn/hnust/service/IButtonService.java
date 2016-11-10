package com.cn.hnust.service;

import java.util.List;

public interface IButtonService<T> {
	
	public int save(T t);
	public int update(T t);
	public T query(Long id);
	public int delete(Long id);
	public T queryByParams(Long id,Long parentid);
	public List<T> queryByParent(Long parentid);
	public List<T> queryByPage(int row,int page);
	public List<T> queryAll();


}
