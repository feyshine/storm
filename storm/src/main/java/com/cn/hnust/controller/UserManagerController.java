package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.L;

@Controller
@RequestMapping("/userManager")
public class UserManagerController {

	@Resource
	private IUserService userService;
	private String userPageSize = "5";
	
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toUser() {
		return "listUser";
	}
	
	
	@RequestMapping(value = "/listUser",method = {RequestMethod.GET})
	public String listUser(String page,Model model){
		L.i("UserManagerController", "开始查询列表");
		 //每页显示的条数
		int pageSize = Integer.parseInt(userPageSize);
		List<User> users = new ArrayList<User>();
		users = this.userService.getAllUser();
		// 查到的总用户数
		model.addAttribute("userNum", users.size());
		// 总页数
		int pageTimes;
		if (users.size() % pageSize == 0) {
			pageTimes = users.size() / pageSize;
		} else {
			pageTimes = users.size() / pageSize + 1;
		}
		model.addAttribute("pageTimes", pageTimes);
		//页面初始的时候page没有值
		if (null == page) {
			page = "1";
		}
		//每页开始的第几条记录 
		int startRow = (Integer.parseInt(page)-1) * pageSize;
		users = this.userService.getUserByPage(startRow, pageSize);
		model.addAttribute("currentPage", Integer.parseInt(page));
		model.addAttribute("users", users);
		return "listUser";
	}
}
