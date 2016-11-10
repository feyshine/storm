package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.SexMapper;
import com.cn.hnust.service.ISexService;
@Service("sexService")
public class SexServiceImp<T> implements ISexService<T> {

	@Resource
	private SexMapper<T> sexDao;
	
	@Override
	public List<T> queryAll() {
		return this.sexDao.queryAll();
	}

	@Override
	public T queryById(Long id) {
		return sexDao.selectByPrimaryKey(id);
	}

}
