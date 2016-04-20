package com.cn.hnust.service;

import com.cn.hnust.pojo.KService;


public interface IKService {
	
	public void save(KService service);
	public void delete(String account);
	public KService select(String account);
	public void updateByPrimaryKeySelective(KService service);
	public void updateByPrimaryKey(KService service);

}
