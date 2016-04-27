package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.NodesMapper;
import com.cn.hnust.pojo.Nodes;
import com.cn.hnust.service.INodesService;
@Service("nodesService")
public class NodesServiceImp<T> implements INodesService<T>{

	@Resource
	private NodesMapper<T> nodesDao;
	@Override
	public int save(T t) {
		this.nodesDao.insert(t);
		return 0;
	}

	@Override
	public T query(Long id) {
		return this.nodesDao.selectByPrimaryKey(id);
	}

	@Override
	public int update(T t) {
		this.nodesDao.updateByPrimaryKey(t);
		return 0;
	}

	@Override
	public int delete(Long id) {
		this.nodesDao.deleteByPrimaryKey(id);
		return 0;
	}

	@Override
	public List<T> queryTopNodes() {
		return this.nodesDao.queryTopNodes();
	}

	@Override
	public List<T> queryChildNodes(String parentId) {
		return this.nodesDao.queryChildNodes(parentId);
	}


}
