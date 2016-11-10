package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.WxMessage;

public interface IWxService<T> {
	
	public T queryById(Long id);
	public List<T> queryAll();
	public List<T> queryByPageSize(int row,int page);
	public void save(T t);
	public void save(List<T> list);
	public void update(T t);
	public void delete(Long id);

}
