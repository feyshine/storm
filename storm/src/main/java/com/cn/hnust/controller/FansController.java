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

import com.cn.hnust.pojo.Fans;
import com.cn.hnust.service.IFansService;

@Controller
@RequestMapping("/fans")
public class FansController extends BaseController {
	
	@Resource
	private IFansService<Fans> fansService;
	
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
			this.fansService.save(fan);
			map.put(MSG, "添加成功！");
			map.put(RESULT, RESULT_OK);
		};
		return map;
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
