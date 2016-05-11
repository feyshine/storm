package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.StateMapper;
import com.cn.hnust.service.IStateService;
@Service("stateService")
public class StateServiceImp<T> implements IStateService<T> {

	@Resource
	private StateMapper<T> stateDao;
	
	@Override
	public List<T> queryAll() {
		return stateDao.selectAll();
	}

}
