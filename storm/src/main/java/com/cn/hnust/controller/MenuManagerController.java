package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.biz.WxButtonBiz;
import com.cn.hnust.pojo.Button;
import com.cn.hnust.resp.ResponseResult;
import com.cn.hnust.service.IButtonService;
import com.cn.hnust.util.Config;

@Controller
@RequestMapping("/menu")
public class MenuManagerController extends BaseController{


	private static final long PARENTID = 0;
	@Resource
	private IButtonService<Button> buttonService;

	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toMenusManger() {
		return "manger_menus";
	}
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/queryByPage", method = { RequestMethod.POST })
	public Map<String, Object> queryWxMenuByPageSize(@RequestParam(value = "page", required = false) String page,@RequestParam(value = ROWS, required = false) String rows){
		Map<String, Object> map = new HashMap<String, Object>();
        List<Button> parentList = this.buttonService.queryByParent(PARENTID);
		int total = parentList.size();
		map.put(TOTAL, total);
		map.put(ROWS, parentList);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryChildrenByParent", method = { RequestMethod.POST })
	public Map<String,Object> queryChildren(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Button parentBtn = this.buttonService.query(id);
		List<Button> children = new ArrayList<Button>();
		if(parentBtn == null){
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "父菜单不存在！");
			map.put(ROWS, children);
			logger.info(id + "父菜单不存在！");
		}else{
			children = this.buttonService.queryByParent(id);
			if(children.size()>0){
				map.put(RESULT, RESULT_OK);
				map.put(MSG, "子菜单数据加载成功！");
				map.put(ROWS, children);
				logger.info("主菜单"+id + "子菜单数据加载成功！");
			}else{
				map.put(RESULT, RESULT_ERROR);
				map.put(MSG, "暂无子菜单数据！");
				map.put(ROWS, children);
				logger.info("主菜单编码："+id + "，暂无子菜单数据！");
			}
		}
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Map<String, Object> addMenus(Button button){
		Map<String, Object> map = new HashMap<String, Object>();
		Button btn = this.buttonService.query(button.getId());
		if(btn == null){
			button.setParentid(PARENTID);
			this.buttonService.save(button);
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "菜单添加成功！");
			logger.info(button.getName() + " 保存数据库中");
		}else{
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "菜单已存在！");
			logger.info(button.getName() + " 已存在数据库中");
		}
		return map;
	}


	
	@ResponseBody
	@RequestMapping(value = "/addChild", method = { RequestMethod.POST })
	public Map<String,Object> addChildMenu(Button child){
		Map<String, Object> map = new HashMap<String, Object>();
		Button parentBtn = this.buttonService.query(child.getParentid());
		if(parentBtn==null){
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "父菜单菜单不存在！");
			logger.info(child.getName() + "的父菜单不存在！");
		}else{
			Button temChild = this.buttonService.queryByParams(child.getId(), child.getParentid());
			Button IdDuplicate = this.buttonService.query(child.getId());//数据库id主键不能重复，且自增
			if(temChild == null){
				if(IdDuplicate!=null){
					map.put(RESULT, RESULT_ERROR);
					map.put(MSG, "子菜单ID不能重复，重新填写！");
					logger.info(child.getName() + "子菜单ID不能重复，重新填写！");
				}else{
					this.buttonService.save(child);
					map.put(RESULT, RESULT_OK);
					map.put(MSG, "子菜单菜单添加成功！");
					logger.info(child.getName() + "子菜单菜单添加成功！");
				}
			}else{
				map.put(RESULT, RESULT_ERROR);
				map.put(MSG, "子菜单菜单已存在！");
				logger.info(child.getName() + "子菜单菜单已存在！");
			}
			
		}
		return map;
	}

	
	@ResponseBody
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public Map<String, Object> updateMenus(Button button){
		Map<String, Object> map = new HashMap<String, Object>();
		Button parent = this.buttonService.query(button.getId());
		if(parent==null){
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "菜单不存在！");
			logger.info(button.getName() + " has updated  faltrue");
		}else{
			this.buttonService.update(button);
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "菜单编辑成功！");
			logger.info(button.getName() + " has updated  successfully");
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editChild", method = { RequestMethod.POST })
	public Map<String,Object> updateChlidrenMenu(Button button){
		Map<String, Object> map = new HashMap<String, Object>();
		Button parent = this.buttonService.query(Long.valueOf(button.getParentid()));
		if(parent==null){
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "菜单不存在！");
			logger.info(button.getName() + "菜单不存在！");
		}else{
			Button children = this.buttonService.queryByParams(button.getId(),button.getParentid());
			if(children == null){
				map.put(RESULT, RESULT_ERROR);
				map.put(MSG, "子菜单不存在！");
				logger.info(button.getName() + "子菜单不存在！");
			}else{
				this.buttonService.update(button);
				map.put(RESULT, RESULT_OK);
				map.put(MSG, "子菜单编辑成功！");
				logger.info(button.getName() + "子菜单编辑成功！");
			}
		}
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Map<String, Object> deleteMenus(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Button parent = this.buttonService.query(id);
		if(parent==null){
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "菜单不存在！");
			logger.info(id + " has not in database");
		}else{
			List<Button> chlidren = buttonService.queryByParent(id);
			if(chlidren.size()>0){
				for(Button child:chlidren){
					buttonService.delete(child.getId());
				}
			}
			this.buttonService.delete(id);
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "菜单删除成功！");
			logger.info(id + " has deleted  database");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteChild", method = { RequestMethod.POST })
	public Map<String,Object> deletechildrenMenu(Long parentId,Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Button parent = this.buttonService.query(parentId);
		if(parent==null){
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "菜单不存在！");
			logger.info(parentId + " has not in database");
		}else{
			Button child = this.buttonService.queryByParams(id, parentId);
			if(child == null){
				map.put(RESULT, RESULT_ERROR);
				map.put(MSG, "子菜单不存在！");
				logger.info(id + "子菜单不存在！");
			}else{
				this.buttonService.delete(id);
				map.put(RESULT, RESULT_OK);
				map.put(MSG, "子菜单删除成功！");
				logger.info(id + "子菜单删除成功！");
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/creatWxButton", method = { RequestMethod.POST })
	public Map<String, Object> creat() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Button> parentList = this.buttonService.queryByParent(PARENTID);
		for (Button parentbutton : parentList) {
			List<Button> childrenButtons = this.buttonService
					.queryByParent(parentbutton.getId());
			Button[] btnButtons = new Button[childrenButtons.size()];
			for (int i = 0; i < childrenButtons.size(); i++) {
				btnButtons[i] = childrenButtons.get(i);
				parentbutton.setSub_button(btnButtons);
			}
		}
		int result = WxButtonBiz.creatWxButtons(parentList);
		if (result == WX_RESPONSE_SUCCESS) {
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "微信菜单创建成功！");
			logger.info("微信菜单创建成功");
		} else if (result == WX_RESPONSE_FAIL) {
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "微信菜单创建失败！");
			logger.info("微信菜单创建失败");
		} else if (result == WX_RESPONSE_NULL) {
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "微信菜单创建异常！");
			logger.info("微信菜单创建异常");
		}

		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteWxButton", method = { RequestMethod.POST })
	public Map<String, Object> delete(){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = WxButtonBiz.deleteWxButtons();
		if (result == WX_RESPONSE_SUCCESS) {
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "微信菜单删除成功！");
			logger.info("微信菜单删除成功");
		} else if (result == WX_RESPONSE_FAIL) {
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "微信菜单删除失败！");
			logger.info("微信菜单删除失败");
		} else if (result == WX_RESPONSE_NULL) {
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "微信菜单删除异常！");
			logger.info("微信菜单删除异常");
		}
		return map;
	}

}
