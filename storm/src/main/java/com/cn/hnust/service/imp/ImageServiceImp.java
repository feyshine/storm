package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ImageMapper;
import com.cn.hnust.service.IImageService;
@Service("imageService")
public class ImageServiceImp<T> implements IImageService<T> {

	@Resource
	private ImageMapper<T> imageDao;
	
	@Override
	public void save(T t) {
		this.imageDao.insert(t);
		
	}

	@Override
	public void delete(Long id) {
		this.imageDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public T select(Long id) {
		return this.imageDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(T t) {
		this.imageDao.updateByPrimaryKeySelective(t);
		
	}

	@Override
	public void updateByPrimaryKey(T t) {
		this.imageDao.updateByPrimaryKey(t);
		
	}

	@Override
	public List<T> queryAll() {
		return this.imageDao.selectAll();
	}

	@Override
	public List<T> queryTByPageSize(int row, int page) {
		return this.imageDao.selectByPage(row, page);
	}

}
