package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.Sex;
import com.cn.hnust.service.ISexService;

@Controller
@RequestMapping("/sex")
public class SexController extends BaseController {

	@Resource
	private ISexService<Sex> sexService;
	
	@ResponseBody
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public Map<String, Object> query() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Sex> sexs = sexService.queryAll();
		map.put(TOTAL, sexs.size());
		map.put(ROWS, sexs);
		return map;
	}
}
