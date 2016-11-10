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

import com.cn.hnust.pojo.Province;
import com.cn.hnust.service.IProvinceService;
@Controller
@RequestMapping("/province")
public class ProvinceController extends BaseController {

	@Resource
	private IProvinceService<Province> provinceService;
	
	@ResponseBody
	@RequestMapping(value = "/queryByCountry", method = { RequestMethod.POST })
	public Map<String, Object> queryByCountry(Long cid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Province> provinces = provinceService.queryByCountry(cid);
		map.put(TOTAL, provinces.size());
		map.put(ROWS, provinces);
		return map;
	}
}
