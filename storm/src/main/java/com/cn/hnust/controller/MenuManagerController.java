package com.cn.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/menu")
public class MenuManagerController {
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toMenusManger() {
		return "manger_menus";
	}

}
