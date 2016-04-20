package com.cn.hnust.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.WxAccessTokenMapper;
import com.cn.hnust.pojo.WxAccessToken;
import com.cn.hnust.service.IAccessTokenService;
@Service("accessTokenService")
public class AccessTokenServiceImp implements IAccessTokenService {
	
	@Resource
	private WxAccessTokenMapper accessTokenDao;

	@Override
	public void insert(WxAccessToken token) {
		this.accessTokenDao.insert(token);
	}

	@Override
	public WxAccessToken query(Long id) {
		// TODO Auto-generated method stub
		return this.accessTokenDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(WxAccessToken record) {
		// TODO Auto-generated method stub
		this.accessTokenDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByPrimaryKey(WxAccessToken record) {
		// TODO Auto-generated method stub
		this.accessTokenDao.updateByPrimaryKey(record);
	}

}
