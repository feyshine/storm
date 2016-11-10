package com.cn.hnust.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.UserMapper;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;
@Service("userService")  
public class UserServiceImpl implements IUserService {
	
	@Resource  
    private UserMapper userDao;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		  return this.userDao.selectByPrimaryKey(userId);  
	}

	@Override
	public void save(User user) {
		this.userDao.insert(user);
	}

	@Override
	public void delete(int userId) {
		this.userDao.deleteByPrimaryKey(userId);
		
	}

	@Override
	public void update(User user) {
		this.userDao.updateByPrimaryKey(user);
		
	}

	@Override
	public User getUserByParemeter(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.selectByParameters(username, password);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

	@Override
	public List<User> getUserByPage(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.getUserByPage(startRow,pageSize);
	}

	
	

}
