package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.WxMenu;
import com.cn.hnust.service.imp.MenuMangerServiceImp;
import com.cn.hnust.util.L;

@Controller
@RequestMapping("/menu")
public class MenuManagerController {
	
	@Resource
	private MenuMangerServiceImp menuMangerService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toMenusManger() {
		return "manger_menus";
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST })
	public Map<String, Object> queryAllMenu(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<WxMenu> menuList = menuMangerService.queryAll();
		int total = menuList.size();
		map.put("total", total);
		map.put("rows", menuList);
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/queryByPageSize", method = { RequestMethod.POST })
	public Map<String, Object> queryWxMenuByPageSize(@RequestParam(value = "page", required = false) String page,@RequestParam(value = "rows", required = false) String rows){
		Map<String, Object> map = new HashMap<String, Object>();
		//当前页 
		int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); 
		 //每页显示条数  
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);
        //每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number; 
		List<WxMenu> totalMenuList = menuMangerService.queryAll();
		int total = totalMenuList.size();
		map.put("total", total);
		List<WxMenu> menuList = menuMangerService.queryWxMenuByPageSize(start, number);
		map.put("rows", menuList);
		L.i("菜单", "返回了");
		return map;
	}

}
