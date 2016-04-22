package com.cn.hnust.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController<T> {
	public static Logger logger = LogManager.getLogger(MenuManagerController.class.getName());
	private T t;
}
