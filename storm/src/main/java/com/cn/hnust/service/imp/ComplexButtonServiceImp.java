package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ComplexButtonMapper;
import com.cn.hnust.pojo.ComplexButton;
import com.cn.hnust.service.IComplexButtonService;
@Service("complexButtonService")
public class ComplexButtonServiceImp implements IComplexButtonService<ComplexButton> {
	
	@Resource
	private ComplexButtonMapper<ComplexButton> complexButtonDao;

	@Override
	public int save(ComplexButton t) {
		this.complexButtonDao.insert(t);
		return 0;
	}

	@Override
	public int update(ComplexButton t) {
		this.complexButtonDao.updateByPrimaryKey(t);
		return 0;
	}

	@Override
	public int delte(Long id) {
		this.complexButtonDao.deleteByPrimaryKey(id);
		return 0;
	}

	@Override
	public ComplexButton query(Long id) {
		return this.complexButtonDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ComplexButton> queryAll() {
		return this.complexButtonDao.queryAll();
	}

	@Override
	public List<ComplexButton> queryByPage(int row, int page) {
		return this.complexButtonDao.queryByPage(row,page);
	}

}
