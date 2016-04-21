package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.WxMenuMapper;
import com.cn.hnust.pojo.WxMenu;
import com.cn.hnust.service.IMenuMangerService;
@Service("menuMangerService")
public class MenuMangerServiceImp implements IMenuMangerService {
	
	@Resource
	private WxMenuMapper menuDao;

	@Override
	public List<WxMenu> queryAll() {
		// TODO Auto-generated method stub
		return this.menuDao.queryAll();
	}
	
	@Override
	public List<WxMenu> queryWxMenuByPageSize(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.menuDao.queryWxMenuByPage(startRow, pageSize);
	}

	@Override
	public WxMenu queryById(Long id) {
		// TODO Auto-generated method stub
		return this.menuDao.selectByPrimaryKey(id);
	}

	@Override
	public void save(WxMenu menu) {
		// TODO Auto-generated method stub
		this.menuDao.insert(menu);
	}

	@Override
	public void update(WxMenu menu) {
		// TODO Auto-generated method stub
		this.menuDao.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		this.menuDao.deleteByPrimaryKey(id);
	}

	

}
