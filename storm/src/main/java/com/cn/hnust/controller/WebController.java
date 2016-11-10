package com.cn.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web")
public class WebController {
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toWeb() {
		return "web";
	}

}
