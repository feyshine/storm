package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.LanguageMapper;
import com.cn.hnust.service.ILanguageService;
@Service("languageService")
public class LanguageServiceImp<T> implements ILanguageService<T> {
	
	@Resource
	private LanguageMapper<T> LanguageDao;
	@Override
	public List<T> queryAll() {
		return this.LanguageDao.queryAll();
	}
	@Override
	public T queryById(Long id) {
		return this.LanguageDao.selectByPrimaryKey(id);
	}

}
