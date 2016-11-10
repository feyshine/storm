package com.cn.hnust.service;

import com.cn.hnust.pojo.WxAccessToken;

public interface IAccessTokenService {
	
	public void insert(WxAccessToken token);
	public WxAccessToken query(Long id);
	public void updateByPrimaryKeySelective(WxAccessToken record);
	public void updateByPrimaryKey(WxAccessToken record);

}
