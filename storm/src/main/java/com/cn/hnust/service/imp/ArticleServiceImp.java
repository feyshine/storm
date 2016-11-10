package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ArticleMapper;
import com.cn.hnust.service.IArticleService;
@Service("articleService")
public class ArticleServiceImp<T> implements IArticleService<T> {

	@Resource
	private ArticleMapper<T> articleDao;
	
	@Override
	public void save(T t) {
		this.articleDao.insert(t);
		
	}

	@Override
	public void delete(Long id) {
		this.articleDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public T select(Long id) {
		
		return this.articleDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(T t) {
		this.articleDao.updateByPrimaryKeySelective(t);
		
	}

	@Override
	public void updateByPrimaryKey(T t) {
		this.articleDao.updateByPrimaryKey(t);
		
	}

	@Override
	public List<T> queryAll() {
		return this.articleDao.selectAll();
	}

	@Override
	public List<T> queryTByPageSize(int row, int page) {
		return this.articleDao.selectByPage(row, page);
	}

}
