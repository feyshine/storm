package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.Language;
import com.cn.hnust.service.ILanguageService;

@Controller
@RequestMapping("/language")
public class LanguageController extends BaseController{

	@Resource
	private ILanguageService<Language> languageService;
	
	@ResponseBody
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public Map<String, Object> query() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Language> laList = languageService.queryAll();
		map.put(TOTAL, laList.size());
		map.put(ROWS, laList);
		return map;
	}

}
