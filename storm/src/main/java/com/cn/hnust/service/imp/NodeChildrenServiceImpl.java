package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.NodeChildrenMapper;
import com.cn.hnust.service.INodeChildrenService;
@Service("nodeChildrenService")
public class NodeChildrenServiceImpl<T> implements INodeChildrenService<T> {
	
	@Resource
	private NodeChildrenMapper<T> nodeChildrenDao;
	

	@Override
	public int deleteByPrimaryKey(String id) {
		return nodeChildrenDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {
		return nodeChildrenDao.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return nodeChildrenDao.insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(String id) {
		return nodeChildrenDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return nodeChildrenDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return nodeChildrenDao.updateByPrimaryKey(record);
	}

	@Override
	public List<T> queryChildNodes(String pid) {
		return nodeChildrenDao.queryChildNodes(pid);
	}

}
