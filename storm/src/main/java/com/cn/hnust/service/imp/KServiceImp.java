package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.KServiceMapper;
import com.cn.hnust.pojo.KService;
import com.cn.hnust.service.IKService;
@Service("kService")
public class KServiceImp implements IKService<KService> {
	
	@Resource
	private KServiceMapper<KService> serviceDao;

	@Override
	public void save(KService service) {
		// TODO Auto-generated method stub
		this.serviceDao.insert(service);
	}

	@Override
	public void delete(String account) {
		// TODO Auto-generated method stub
		this.serviceDao.deleteByPrimaryKey(account);
	}

	@Override
	public KService select(String account) {
		// TODO Auto-generated method stub
		return this.serviceDao.selectByPrimaryKey(account);
	}

	@Override
	public void updateByPrimaryKeySelective(KService service) {
		// TODO Auto-generated method stub
		this.serviceDao.updateByPrimaryKeySelective(service);
	}

	@Override
	public void updateByPrimaryKey(KService service) {
		// TODO Auto-generated method stub
		this.serviceDao.updateByPrimaryKey(service);
	}

	@Override
	public List<KService> queryAll() {
		// TODO Auto-generated method stub
		return this.serviceDao.queryAll();
	}

	@Override
	public List<KService> queryTByPageSize(int row, int page) {
		// TODO Auto-generated method stub
		return this.serviceDao.queryTByPageSize(row,page);
	}

	

}
