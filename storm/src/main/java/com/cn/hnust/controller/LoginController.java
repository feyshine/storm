package com.cn.hnust.controller;

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
@RequestMapping("/login")
public class LoginController {
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "",method = {RequestMethod.POST})
	public String login(@ModelAttribute User user,Model model){
		String page = null;
		L.i("LoginController", "login is donging");
		User queryResult = this.userService.getUserByParemeter(user.getUsername(), user.getPassword());
		model.addAttribute("user", queryResult);
		if(queryResult == null){
			page = "loginFail";
		}else {
			page = "redirect:/home";
			L.i("LoginController", "login successfully");
		}
		return page;
	}
	
	
}
