package com.cn.hnust.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.L;

@Controller
@RequestMapping("/regist")
public class RegisterController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "",method = {RequestMethod.GET})
	public String toRegist(){
		return "regist";
	}

	@RequestMapping(value = "/add",method = {RequestMethod.POST})
	public String save(@ModelAttribute User user){
		this.userService.save(user);
		L.i("UserManagerController", "user has saved successfully");
		return "login";//new ModelAndView("redirect:/user/tologin");
	}
}
