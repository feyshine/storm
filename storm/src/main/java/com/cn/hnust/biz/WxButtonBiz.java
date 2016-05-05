package com.cn.hnust.biz;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.controller.CreateMenuFactory;
import com.cn.hnust.pojo.Button;
import com.cn.hnust.resp.ResponseResult;
import com.cn.hnust.service.IButtonService;
import com.cn.hnust.util.Config;
import com.cn.hnust.wx.api.req.entity.CommonButton;
import com.cn.hnust.wx.api.resp.entity.ParseMenu;

public class WxButtonBiz {
	private static final long PARENTID = 0;
	
	protected static final int WX_RESPONSE_SUCCESS = 1;
	protected static final int WX_RESPONSE_FAIL = 2;
	protected static final int WX_RESPONSE_NULL = 3;
	private IButtonService<Button> buttonService;
	
	
	public WxButtonBiz(IButtonService<Button> buttonService){
		this.buttonService = buttonService;
	}
	
	
	/**
	 * 获取微信服务器已经创建菜单列表
	 */
	private List<com.cn.hnust.wx.api.req.entity.ComplexButton> getServerButtons() {
		NetWorkHelper menuHelper = new NetWorkHelper();
		String url = String.format(Config.find_menu_url,TokenThread.accessToken.getAccessToken());
		String result = menuHelper.getHttpsResponse(url, "GET");
		List<com.cn.hnust.wx.api.req.entity.ComplexButton> list = ParseMenu.parseComplexButton(result);
		return list;
	}
	
	
	/**
	 * 清除菜单所有数据
	 */
	private void clearUpMenus() {
		List<Button> buttons =this.buttonService.queryAll();
		for(Button btn:buttons){
			this.buttonService.delete(btn.getId());
		}
	}
	
	/**
	 * 保存一级菜单
	 * @param list
	 */
	private void saveParentButtons(List<com.cn.hnust.wx.api.req.entity.ComplexButton> list) {
		if(list==null||list.size() ==0) return;
		for(com.cn.hnust.wx.api.req.entity.ComplexButton wxParentBtn:list){
			Button parentBtn = new Button();
			parentBtn.setId(new Date().getTime());
			parentBtn.setName(wxParentBtn.getName());
			parentBtn.setNkey(wxParentBtn.getKey());
			parentBtn.setType(wxParentBtn.getType());
			parentBtn.setParentid(PARENTID);
			this.buttonService.save(parentBtn);
			saveChildrenButton(wxParentBtn, parentBtn);
		}
	}
	
	
	/**
	 * 保存二级菜单
	 * @param wxParentBtn
	 * @param parentBtn
	 */
	private void saveChildrenButton(com.cn.hnust.wx.api.req.entity.ComplexButton wxParentBtn,Button parentBtn) {
		for(CommonButton wxChildBtn:wxParentBtn.getSub_button()){
			Button childBtn = new Button();
			childBtn.setId(new Date().getTime());
			childBtn.setName(wxChildBtn.getName());
			childBtn.setNkey(wxChildBtn.getKey());
			childBtn.setType(wxChildBtn.getType());
			childBtn.setParentid(parentBtn.getId());
			childBtn.setUrl(wxChildBtn.getUrl());
			childBtn.setMediaId(wxChildBtn.getMedia_id());
			this.buttonService.save(childBtn);
		}
	}

	
	/**
	 * @param success:1 ;error:2; null:3;
	 * @param button
	 */
	private int syncWxCreatTopMenu(List<Button> buttons) {
		NetWorkHelper menuHelper = new NetWorkHelper();
		String url = String.format(Config.creat_menu_url,TokenThread.accessToken.getAccessToken());
		String jsonMenu = CreateMenuFactory.creatTopMenu(buttons);
		String result = menuHelper.postHttpsResponse(url, jsonMenu);
		ResponseResult menuresult = JSON.parseObject(result, ResponseResult.class);
		if(menuresult == null){
			return WX_RESPONSE_NULL;
		}
		if(menuresult.getErrcode()!= null && menuresult.getErrcode().equals("0")){
			return WX_RESPONSE_SUCCESS;
		}else {
			return WX_RESPONSE_FAIL;
		}
	}
	
	/**
	 * @param success:1 ;error:2; null:3;
	 * @param child
	 * @param tempMenu
	 */
		NetWorkHelper menuHelper = new NetWorkHelper();
		private int syncComplexButton(Button child, Button parent) {
		String url = String.format(Config.creat_menu_url,TokenThread.accessToken.getAccessToken());
		String jsonMenu = CreateMenuFactory.creatChildrenMenu(parent,child);
		String result = menuHelper.postHttpsResponse(url, jsonMenu);
		ResponseResult menuresult = JSON.parseObject(result, ResponseResult.class);
		if(menuresult == null){
			return WX_RESPONSE_NULL;
		}
		if(menuresult.getErrcode()!= null && menuresult.getErrcode().equals("0")){
			return WX_RESPONSE_SUCCESS;
		}else {
			return WX_RESPONSE_FAIL;
		}
	}
		
		
		/**
		 * 删除自定义菜单
		 */
		private int syncDeleteButtons() {
			NetWorkHelper menuHelper = new NetWorkHelper();
			String url = String.format(Config.delete_menu_url,TokenThread.accessToken.getAccessToken());
			String result = menuHelper.getHttpsResponse(url, "GET");
			ResponseResult menuresult = JSON.parseObject(result, ResponseResult.class);
			if(menuresult == null){
				return WX_RESPONSE_NULL;
			}
			if(menuresult.getErrcode()!= null && menuresult.getErrcode().equals("0")){
				return WX_RESPONSE_SUCCESS;
			}else {
				return WX_RESPONSE_FAIL;
			}
		}
}
