package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ButtonMapper;
import com.cn.hnust.pojo.Button;
import com.cn.hnust.service.IButtonService;
@Service("buttonService")
public class ButtonServiceImp implements IButtonService<Button> {
	
	@Resource
	private ButtonMapper<Button> buttonDao;

	@Override
	public int save(Button t) {
		this.buttonDao.insert(t);
		return 0;
	}

	@Override
	public int update(Button t) {
		this.buttonDao.updateByPrimaryKey(t);
		return 0;
	}

	@Override
	public Button query(Long id) {
		return this.buttonDao.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Long id) {
		this.buttonDao.deleteByPrimaryKey(id);
		return 0;
	}

	@Override
	public List<Button> queryByParent(Long parentid) {
		return this.buttonDao.selectByParent(parentid);
	}

	@Override
	public Button queryByParams(Long id, Long parentid) {
		// TODO Auto-generated method stub
		return this.buttonDao.selectByParams(id, parentid);
	}

	@Override
	public List<Button> queryByPage(int row, int page) {
		// TODO Auto-generated method stub
		return this.buttonDao.selectByPage(row, page);
	}

	@Override
	public List<Button> queryAll() {
		// TODO Auto-generated method stub
		return this.buttonDao.selectAll();
	}





}
