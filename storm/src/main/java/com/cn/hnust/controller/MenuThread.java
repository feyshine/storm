package com.cn.hnust.controller;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.resp.ResponseResult;
import com.cn.hnust.util.Config;
import com.cn.hnust.util.L;

public class MenuThread implements Runnable{
	private static final String TAG = MenuThread.class.getSimpleName();

	@Override
	public void run() {
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		initMenu();
	}
	
	
	private void initMenu(){
		NetWorkHelper workHelper = new NetWorkHelper();
		String url = String.format(Config.creat_menu_url,TokenThread.accessToken.getAccessToken());
		String jsonMenu = CreateMenuFactory.create();
		String result = workHelper.postHttpsResponse(url, jsonMenu);
		ResponseResult menuresult = JSON.parseObject(result, ResponseResult.class);
		if(menuresult == null){
			L.i(TAG, "服务器返回 null");
			return ;
		}
		if(menuresult.getErrcode()!= null && menuresult.getErrcode().equals("0")){
			L.i(TAG, "菜单初始化成功！");
		}else {
			L.i(TAG, "菜单初始化失败！err = " + menuresult.getErrmsg());
		}
	}
	
	

}
