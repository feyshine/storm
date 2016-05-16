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
import com.cn.hnust.pojo.Fans;
import com.cn.hnust.pojo.Language;
import com.cn.hnust.pojo.Province;
import com.cn.hnust.pojo.Sex;
import com.cn.hnust.service.ICountryService;
import com.cn.hnust.service.IFansService;
import com.cn.hnust.service.ILanguageService;
import com.cn.hnust.service.IProvinceService;
import com.cn.hnust.service.ISexService;

@Controller
@RequestMapping("/fans")
public class FansController extends BaseController {
	
	@Resource
	private IFansService<Fans> fansService;
	@Resource
	private ICountryService<Country> countryService;
	@Resource
	private ISexService<Sex> sexService;
	@Resource
	private IProvinceService<Province> provinceService;
	@Resource
	private ILanguageService<Language> languageService;
	
	@ResponseBody
	@RequestMapping(value = "/queryFansByPage", method = { RequestMethod.POST })
	public Map<String, Object> queryFansByPage(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;
		List<Fans> imglist = fansService.queryAll();
		map.put(TOTAL, imglist.size());
		List<Fans> imgpage = fansService.queryTByPageSize(start, number);
		map.put(ROWS, imgpage);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Map<String, Object> add(Fans fan){
		Map<String, Object> map = new HashMap<String, Object>();
		if (this.fansService.select(fan.getOpenid()) != null) {
			map.put(MSG, "已经存在！");
			map.put(RESULT, RESULT_ERROR);
		}else {
			convertColumn(fan);
			this.fansService.save(fan);
			map.put(MSG, "添加成功！");
			map.put(RESULT, RESULT_OK);
		};
		return map;
	}

	/**
	 * @param fan
	 */
	private void convertColumn(Fans fan) {
		Long countryId = Long.valueOf(fan.getCountry());
		Country country = countryService.queryById(countryId);
		fan.setCountry(country.getName());
		Long provinceId = Long.valueOf(fan.getProvince());
		Province province = provinceService.queryById(provinceId);
		fan.setProvince(province.getName());
		Long sexId = Long.valueOf(fan.getSex());
		Sex sex = sexService.queryById(sexId);
		fan.setSex(sex.getName());
		Long languageId = Long.valueOf(fan.getLanguage());
		Language language = languageService.queryById(languageId);
		fan.setLanguage(language.getName());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public Map<String, Object> edit(Fans fan){
		logger.info("openid:" +fan.getOpenid());
		Map<String, Object> map = new HashMap<String, Object>();
		if (this.fansService.select(fan.getOpenid()) != null) {
			this.fansService.updateByPrimaryKey(fan);;
			map.put(MSG, "编辑成功！");
			map.put(RESULT, RESULT_OK);
		}else {
			map.put(MSG, "用户不存在！");
			map.put(RESULT, RESULT_ERROR);
		};
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Map<String, Object> delete(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		if (this.fansService.select(id) != null) {
			this.fansService.delete(id);
			map.put(MSG, "删除成功！");
			map.put(RESULT, RESULT_OK);
		}else {
			map.put(MSG, "用户不存在！");
			map.put(RESULT, RESULT_ERROR);
		};
		return map;
	}

}
