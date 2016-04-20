package com.cn.hnust.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.KService;
import com.cn.hnust.resp.ResponseResult;
import com.cn.hnust.service.IKService;
import com.cn.hnust.util.Config;
import com.cn.hnust.util.FileUtls;
import com.cn.hnust.util.L;

@Controller
@RequestMapping("kfservice")
public class KServiceController {
	private final static String TAG = KServiceController.class.getSimpleName();
	
	@Resource
	private IKService kService;
	
	@RequestMapping(value = "/add",method = {RequestMethod.POST})
	public String addKService(@ModelAttribute KService kservice){
		NetWorkHelper workHelper = new NetWorkHelper();
		String token = FileUtls.read(FileUtls.PATH);
		L.i(TAG, "token" + token);
		String url = String.format(Config.add_customer_service_url,token);
		kservice.setKfaccount(kservice.getKfaccount()+"@xuyelijia");
		String jsonStr = JSON.toJSONString(kservice);
		L.i("json", jsonStr);
		String result = workHelper.postHttpsResponse(url, jsonStr);
		ResponseResult addresult = JSON.parseObject(result, ResponseResult.class);
		if(addresult == null){
			L.i(TAG, "服务器返回 null");
		}else{
			if(addresult.getErrcode()!= null && addresult.getErrcode().equals("0")){
				L.i(TAG, "添加客服成功！");
				kService.save(kservice);
			}else{
				L.i(TAG, "添加客服失败！err = " + addresult.getErrmsg());
			}
		}
		return "addkfsuccess";
	}
	
	@RequestMapping("/service")
	public String toServcieJsp(){
		return "kservice";
	}
	
	@RequestMapping(value = "/delete",method = {RequestMethod.GET})
	public String deleteKService(){
		return "";
	}
	
	@RequestMapping(value = "/update",method = {RequestMethod.POST})
	public String updateKService(){
		return "";
	}
	
	@RequestMapping(value = "/query",method = {RequestMethod.POST})
	public String queryKService(){
		return "";
	}
}
