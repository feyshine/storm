package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.FansMapper;
import com.cn.hnust.service.IFansService;
@Service("fansService")
public class FansServiceImp<T> implements IFansService<T> {

	@Resource
	FansMapper<T> fansDao;
	
	@Override
	public void save(T t) {
		this.fansDao.insert(t);
		
	}

	@Override
	public void delete(String id) {
		this.fansDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public T select(String id) {
		return this.fansDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(T t) {
		this.fansDao.updateByPrimaryKeySelective(t);
		
	}

	@Override
	public void updateByPrimaryKey(T t) {
		this.fansDao.updateByPrimaryKey(t);
		
	}

	@Override
	public List<T> queryAll() {
		return this.fansDao.selectAll();
	}

	@Override
	public List<T> queryTByPageSize(int row, int page) {
		return this.fansDao.selectByPage(row, page);
	}

}
