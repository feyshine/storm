package com.cn.hnust.service.imp;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cn.hnust.dao.KServiceMapper;
import com.cn.hnust.pojo.KService;
import com.cn.hnust.service.IKService;
@Service("kService")
public class KServiceImp implements IKService {
	
	@Resource
	private KServiceMapper serviceDao;

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

	

}
