package com.cn.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/message")
public class MessageManagerController {

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toMessageManger() {
		return "message";
	}
}
