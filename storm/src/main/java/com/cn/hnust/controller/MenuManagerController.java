package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.hnust.pojo.WxMenu;
import com.cn.hnust.service.imp.MenuMangerServiceImp;

@Controller
@RequestMapping("/menu")
public class MenuManagerController {
	
	private static Logger logger = LogManager.getLogger(MenuManagerController.class.getName());
	
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
		logger.info("query all menu return");
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
		logger.info("query  menu return");
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Map<String, Object> addMenus(WxMenu menu){
		Map<String, Object> map = new HashMap<String, Object>();
		WxMenu tempMenu = menuMangerService.queryById(menu.getId());
		if(tempMenu==null){
			menuMangerService.save(menu);
			map.put("result", 1);
			map.put("msg", "菜单添加成功！");
			logger.info(menu.getName() + " 保存数据库中");
		}else{
			map.put("result", 0);
			map.put("msg", "菜单已存在！");
			logger.info(menu.getName() + " 已存在数据库中");
		}
		
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public Map<String, Object> updateMenus(WxMenu menu){
		Map<String, Object> map = new HashMap<String, Object>();
		WxMenu tempMenu = menuMangerService.queryById(menu.getId());
		if(tempMenu==null){
			map.put("result", 0);
			map.put("msg", "菜单不存在！");
			logger.info(menu.getName() + " has updated  faltrue");
		}else{
			menuMangerService.update(menu);
			map.put("result", 1);
			map.put("msg", "菜单编辑成功！");
			logger.info(menu.getName() + " has updated  successfully");
		}
		
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Map<String, Object> deleteMenus(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		WxMenu tempMenu = menuMangerService.queryById(id);
		if(tempMenu==null){
			map.put("result", 0);
			map.put("msg", "菜单不存在！");
			logger.info(id + " has not in database");
		}else{
			menuMangerService.delete(id);;
			map.put("result", 1);
			map.put("msg", "菜单删除成功！");
			logger.info(id + " has deleted  database");
		}
		
		return map;
	}

}
