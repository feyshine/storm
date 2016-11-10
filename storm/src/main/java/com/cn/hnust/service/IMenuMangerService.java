package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.WxMenu;

public interface IMenuMangerService {
	
	public List<WxMenu> queryAll();
	public List<WxMenu> queryWxMenuByPageSize(int startRow, int pageSize);
	public WxMenu queryById(Long id);
	public void save(WxMenu menu);
	public void update(WxMenu menu);
	public void delete(Long id);
	
}
