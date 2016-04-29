package com.cn.hnust.service;

import java.util.List;

public interface INodeService<T> {
	
	public int save(T t);
	public T query(Long id);
	public int update(T t);
	public int delete(Long id);
	public List<T> queryTopNodes();
	public List<T> queryChildNodes(String pname);

}
