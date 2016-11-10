package com.cn.hnust.service;

import java.util.List;

public interface INodeService<T> {
	
	public int save(T t);
	public T query(String id);
	public int update(T t);
	public int delete(String id);
	public List<T> queryTopNodes();
	
}
