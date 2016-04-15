package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.User;

public interface IUserService {
	
	
	public void save(User user);
	public void delete(int userId);
	public void update(User user);
	public User getUserById(int userId);
	public User getUserByParemeter(String username,String password);
	public List<User> getAllUser();
	public List<User> getUserByPage(int startRow, int pageSize);
	
}
