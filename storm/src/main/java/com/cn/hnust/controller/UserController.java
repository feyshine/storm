package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.L;

@Controller
@RequestMapping("/user")
public class UserController {
	private final static String TAG = UserController.class.getSimpleName();

	@Resource
	private IUserService userService;
	
//	@Value("#{configProperties['userPageSize']}")
	private String userPageSize = "5";
	
	@RequestMapping(value = "/login",method = {RequestMethod.POST})
	public String login(@ModelAttribute User user,Model model){
		String page = null;
		L.i(TAG, "login is donging");
		User queryResult = this.userService.getUserByParemeter(user.getUsername(), user.getPassword());
		model.addAttribute("user", queryResult);
		if(queryResult == null){
			page = "loginFail";
		}else {
			page = "home";
			L.i(TAG, "login successfully");
		}
		return page;
		
	}
	
	@RequestMapping(value = "/regist",method = {RequestMethod.POST})
	public String toRegist(){
		return "regist";
	}
	
	@RequestMapping(value = "/tologin",method = {RequestMethod.GET})
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping(value = "/addUser",method = {RequestMethod.POST})
	public String save(@ModelAttribute User user){
		this.userService.save(user);
		L.i(TAG, "user has saved successfully");
		return "redirect:/user/tologin";//new ModelAndView("redirect:/user/tologin");
	}
	
	

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	
	@RequestMapping(value = "/listUser",method = {RequestMethod.GET})
	public String listUser(String page,Model model){
		L.i(TAG, "开始查询列表");
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
