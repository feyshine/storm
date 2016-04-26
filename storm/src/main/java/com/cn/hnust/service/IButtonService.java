package com.cn.hnust.service;

import java.util.List;

public interface IButtonService<T> {
	
	public int save(T t);
	public int update(T t);
	public T query(Long id);
	public int delete(Long id);
	public T queryByParams(Long id,String parentid);
	public List<T> queryByParent(String parentid);


}
