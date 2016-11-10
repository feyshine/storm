package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.NodeMapper;
import com.cn.hnust.service.INodeService;
@Service("nodeService")
public class NodeServiceImp<T> implements INodeService<T>{

	@Resource
	private NodeMapper<T> nodeDao;
	@Override
	public int save(T t) {
		return nodeDao.insert(t);
	}

	@Override
	public T query(String id) {
		return this.nodeDao.selectByPrimaryKey(id);
	}

	@Override
	public int update(T t) {
		return nodeDao.updateByPrimaryKey(t);
	}

	@Override
	public int delete(String id) {
		return nodeDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<T> queryTopNodes() {
		return nodeDao.queryTopNodes();
	}

	


}
