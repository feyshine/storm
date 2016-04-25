package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.WxMessageMapper;
import com.cn.hnust.service.IWxService;
@Service("messageService")
public class WxServiceImp<T> implements IWxService<T> {
	
	@Resource
	private WxMessageMapper<T> messageDao;
	@Override
	public List<T> queryAll() {
		return this.messageDao.queryAll();
	}

	@Override
	public List<T> queryByPageSize(int row,int page) {
		return this.messageDao.queryByPageSize(row, page);
	}

	@Override
	public void save(T t) {
		this.messageDao.insert(t);
	}

	@Override
	public void save(List<T> list) {

	}

	@Override
	public void update(T t) {
		this.messageDao.updateByPrimaryKey(t);
	}


	@Override
	public void delete(Long id) {
		this.messageDao.deleteByPrimaryKey(id);
	}

	@Override
	public T queryById(Long id) {
		return this.messageDao.selectByPrimaryKey(id);
	}

}
