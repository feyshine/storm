package com.cn.hnust.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.Button;
import com.cn.hnust.pojo.ComplexButton;
import com.cn.hnust.resp.ResponseResult;
import com.cn.hnust.service.IButtonService;
import com.cn.hnust.service.IComplexButtonService;
import com.cn.hnust.util.Config;
import com.cn.hnust.util.L;
import com.cn.hnust.wx.api.req.entity.CommonButton;
import com.cn.hnust.wx.api.resp.entity.ParseMenu;

@Controller
@RequestMapping("/menu")
public class MenuManagerController {
	
	private static Logger logger = LogManager.getLogger(MenuManagerController.class.getName());
	

	@Resource
	private IButtonService<Button> buttonService;
	@Resource
	private IComplexButtonService<ComplexButton> complexButtonService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toMenusManger() {
		return "manger_menus";
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST })
	public Map<String, Object> queryAllMenu(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<ComplexButton> menuList = complexButtonService.queryAll();
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
		List<com.cn.hnust.wx.api.req.entity.ComplexButton> list = getServerButtons();
		clearUpMenus();
		saveTopButtons(list);
		//当前页 
		int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); 
		 //每页显示条数  
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);
        //每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number; 
		List<ComplexButton> totalMenuList = complexButtonService.queryAll();
		int total = totalMenuList.size();
		map.put("total", total);
		List<ComplexButton> menuList = complexButtonService.queryByPage(start, number);
		map.put("rows", menuList);
		logger.info("query  menu return");
		return map;
	}

	/**
	 * 保存一级菜单
	 * @param list
	 */
	private void saveTopButtons(
			List<com.cn.hnust.wx.api.req.entity.ComplexButton> list) {
		for(com.cn.hnust.wx.api.req.entity.ComplexButton button:list){
			ComplexButton tableButton = new ComplexButton();
			tableButton.setId(new Date().getTime());
			tableButton.setName(button.getName());
			tableButton.setMkey("null");
			tableButton.setType("null");
			this.complexButtonService.save(tableButton);
			logger.info("save tableButton.name:" + tableButton.getName());
			saveSubButton(button, tableButton);
			
		}
	}

	/**
	 * 清除菜单所有数据
	 */
	private void clearUpMenus() {
		List<ComplexButton> queryset = this.complexButtonService.queryAll();
		for(ComplexButton button:queryset){
			List<Button> buttons =this.buttonService.queryByParent(String.valueOf(button.getId()));
			for(Button suButton:buttons){
				this.buttonService.delete(suButton.getId());
			}
			this.complexButtonService.delte(button.getId());
		}
	}

	/**
	 * 保存二级菜单
	 * @param button
	 * @param tableButton
	 */
	private void saveSubButton(
			com.cn.hnust.wx.api.req.entity.ComplexButton button,
			ComplexButton tableButton) {
		for(CommonButton child:button.getSub_button()){
			Button childButton = new Button();
			childButton.setId(new Date().getTime());
			childButton.setName(child.getName());
			childButton.setBkey(child.getKey());
			childButton.setType(child.getType());
			childButton.setParentid(String.valueOf(tableButton.getId()));
			childButton.setUrl(child.getUrl());
			childButton.setMediaId(child.getMedia_id());
			logger.info("save childButton.name:" + childButton.getName());
			this.buttonService.save(childButton);
		}
	}

	/**
	 * 获取微信服务器已经创建菜单列表
	 */
	private List<com.cn.hnust.wx.api.req.entity.ComplexButton> getServerButtons() {
		NetWorkHelper menuHelper = new NetWorkHelper();
		String url = String.format(Config.find_menu_url,TokenThread.accessToken.getAccessToken());
		String result = menuHelper.getHttpsResponse(url, "GET");
		logger.info("weixin server response menulist :" + result);
		List<com.cn.hnust.wx.api.req.entity.ComplexButton> list = ParseMenu.parseComplexButton(result);
		logger.info("weixin ComplexButton list.size :" +list.size());
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryChildrenByParent", method = { RequestMethod.POST })
	public Map<String,Object> queryChildren(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		ComplexButton tempMenu = complexButtonService.query(id);
		if(tempMenu == null){
			map.put("result", 0);
			map.put("msg", "父菜单不存在！");
			map.put("row", null);
			logger.info(id + "父菜单不存在！");
		}else{
			List<Button> children = this.buttonService.queryByParent(id+"");
			if(children.size()>0){
				map.put("result", 1);
				map.put("msg", "子菜单数据加载成功！");
				map.put("rows", children);
				logger.info("主菜单"+id + "子菜单数据加载成功！");
			}else{
				map.put("result", 2);
				map.put("msg", "暂无子菜单数据！");
				map.put("row", null);
				logger.info("主菜单"+id + "暂无子菜单数据！");
			}
		}
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Map<String, Object> addMenus(ComplexButton button){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = syncWxCreatTopMenu(button);
		switch (result) {
		case 1:
			ComplexButton tempMenu = complexButtonService.query(button.getId());
			if(tempMenu == null){
				complexButtonService.save(button);
				map.put("result", 1);
				map.put("msg", "菜单添加成功！");
				logger.info(button.getName() + " 保存数据库中");
			}else{
				map.put("result", 0);
				map.put("msg", "菜单已存在！");
				logger.info(button.getName() + " 已存在数据库中");
			}
			break;
		case 2:
			map.put("result", 0);
			map.put("msg", "微信菜单创建失败");
			break;
		case 3:
			map.put("result", 0);
			map.put("msg", "微信服务器返回空，菜单创建失败");
			break;
		default:
			break;
		}
		return map;
	}

	/**
	 * @param success:1 ;error:2; null:3;
	 * @param button
	 */
	private int syncWxCreatTopMenu(ComplexButton button) {
		NetWorkHelper menuHelper = new NetWorkHelper();
		String url = String.format(Config.creat_menu_url,TokenThread.accessToken.getAccessToken());
		String jsonMenu = CreateMenuFactory.creatTopMenu(button);
		String result = menuHelper.postHttpsResponse(url, jsonMenu);
		ResponseResult menuresult = JSON.parseObject(result, ResponseResult.class);
		if(menuresult == null){
			return 3;
		}
		if(menuresult.getErrcode()!= null && menuresult.getErrcode().equals("0")){
			return 1;
		}else {
			return 2;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/addChild", method = { RequestMethod.POST })
	public Map<String,Object> addChildMenu(Button child){
		Map<String, Object> map = new HashMap<String, Object>();
		ComplexButton tempMenu = complexButtonService.query(Long.valueOf(child.getParentid()));
		if(tempMenu==null){
			map.put("result", 0);
			map.put("msg", "父菜单菜单不存在！");
			logger.info(child.getName() + "的父菜单不存在！");
		}else{
			int result = syncComplexButton(child, tempMenu);
			switch (result) {
			case 1:
				Button temChild = this.buttonService.queryByParams(child.getId(), child.getParentid());
				Button IdDuplicate = this.buttonService.query(child.getId());//数据库id主键不能重复，且自增
				if(temChild == null){
					if(IdDuplicate!=null){
						map.put("result", 0);
						map.put("msg", "子菜单ID不能重复，重新填写！");
						logger.info(child.getName() + "子菜单ID不能重复，重新填写！");
					}else{
						this.buttonService.save(child);
						map.put("result", 1);
						map.put("msg", "子菜单菜单添加成功！");
						logger.info(child.getName() + "子菜单菜单添加成功！");
					}
				}else{
					map.put("result", 0);
					map.put("msg", "子菜单菜单已存在！");
					logger.info(child.getName() + "子菜单菜单已存在！");
				}
				break;
			case 2:
				map.put("result", 0);
				map.put("msg", "微信菜单创建失败");
				break;
			case 3:
				map.put("result", 0);
				map.put("msg", "微信服务器返回空，菜单创建失败");
				break;
			default:
				break;
			}
			
		}
		return map;
	}

	/**
	 * @param success:1 ;error:2; null:3;
	 * @param child
	 * @param tempMenu
	 */
	private int syncComplexButton(Button child, ComplexButton tempMenu) {
		NetWorkHelper menuHelper = new NetWorkHelper();
		String url = String.format(Config.creat_menu_url,TokenThread.accessToken.getAccessToken());
		String jsonMenu = CreateMenuFactory.creatChildrenMenu(tempMenu,child);
		String result = menuHelper.postHttpsResponse(url, jsonMenu);
		ResponseResult menuresult = JSON.parseObject(result, ResponseResult.class);
		if(menuresult == null){
			return 3;
		}
		if(menuresult.getErrcode()!= null && menuresult.getErrcode().equals("0")){
			return 1;
		}else {
			return 2;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public Map<String, Object> updateMenus(ComplexButton button){
		Map<String, Object> map = new HashMap<String, Object>();
		ComplexButton tempMenu = complexButtonService.query(button.getId());
		if(tempMenu==null){
			map.put("result", 0);
			map.put("msg", "菜单不存在！");
			logger.info(button.getName() + " has updated  faltrue");
		}else{
			complexButtonService.update(button);
			map.put("result", 1);
			map.put("msg", "菜单编辑成功！");
			logger.info(button.getName() + " has updated  successfully");
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editChild", method = { RequestMethod.POST })
	public Map<String,Object> updateChlidrenMenu(Button button){
		Map<String, Object> map = new HashMap<String, Object>();
		ComplexButton tempMenu = complexButtonService.query(Long.valueOf(button.getParentid()));
		if(tempMenu==null){
			map.put("result", 0);
			map.put("msg", "菜单不存在！");
			logger.info(button.getName() + "菜单不存在！");
		}else{
			Button children = this.buttonService.queryByParams(button.getId(),button.getParentid());
			if(children == null){
				map.put("result", 0);
				map.put("msg", "子菜单不存在！");
				logger.info(button.getName() + "子菜单不存在！");
			}else{
				this.buttonService.update(button);
				map.put("result", 1);
				map.put("msg", "子菜单编辑成功！");
				logger.info(button.getName() + "子菜单编辑成功！");
			}
		}
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Map<String, Object> deleteMenus(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		ComplexButton tempMenu = complexButtonService.query(id);
		if(tempMenu==null){
			map.put("result", 0);
			map.put("msg", "菜单不存在！");
			logger.info(id + " has not in database");
		}else{
			List<Button> chlidren = buttonService.queryByParent(id+"");
			if(chlidren.size()>0){
				for(Button child:chlidren){
					buttonService.delete(child.getId());
				}
			}
			complexButtonService.delte(id);
			map.put("result", 1);
			map.put("msg", "菜单删除成功！");
			logger.info(id + " has deleted  database");
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteChild", method = { RequestMethod.POST })
	public Map<String,Object> deletechildrenMenu(String parentId,Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		ComplexButton tempMenu = complexButtonService.query(Long.valueOf(parentId));
		if(tempMenu==null){
			map.put("result", 0);
			map.put("msg", "菜单不存在！");
			logger.info(parentId + " has not in database");
		}else{
			Button tempChild = this.buttonService.queryByParams(id, parentId);
			if(tempChild == null){
				map.put("result", 0);
				map.put("msg", "子菜单不存在！");
				logger.info(id + "子菜单不存在！");
			}else{
				this.buttonService.delete(id);
				map.put("result", 1);
				map.put("msg", "子菜单删除成功！");
				logger.info(id + "子菜单删除成功！");
			}
		}
		return map;
	}

}
