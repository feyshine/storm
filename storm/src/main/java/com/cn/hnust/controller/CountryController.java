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

import com.cn.hnust.pojo.Country;
import com.cn.hnust.service.ICountryService;

@Controller
@RequestMapping("/country")
public class CountryController extends BaseController{
	
	@Resource
	private ICountryService<Country> countryService;
	
	@ResponseBody
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public Map<String, Object> query() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Country> countries = countryService.queryAll();
		map.put(TOTAL, countries.size());
		map.put(ROWS, countries);
		return map;
	}

}
