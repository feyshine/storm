package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.KService;
import com.cn.hnust.resp.ResponseResult;
import com.cn.hnust.service.IKService;
import com.cn.hnust.util.Config;
import com.cn.hnust.util.FileUtls;
import com.cn.hnust.util.L;

@Controller
@RequestMapping("kservice")
public class KServiceController extends BaseController{
	private final static String TAG = KServiceController.class.getSimpleName();
	
	@Resource
	private IKService<KService> kService;
	
	@ResponseBody
	@RequestMapping(value = "/add",method = {RequestMethod.POST})
	public Map<String,Object> addKService(@ModelAttribute KService kservice){
		Map<String,Object> map = new HashMap<String,Object>();
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
				map.put("result", 1);
				map.put("msg", "添加成功");
			}else{
				map.put("result", 0);
				map.put("msg", "添加失败");
				L.i(TAG, "添加客服失败！err = " + addresult.getErrmsg());
			}
		}
		return map;
	}
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toKf() {
		return "kservice";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete",method = {RequestMethod.POST})
	public Map<String,Object> deleteKService(KService service){
		Map<String,Object> map = new HashMap<String,Object>();
		KService temService = this.kService.select(service.getKfaccount());
		if(temService==null){
			map.put("result", 0);
			map.put("msg", "客服账号不存在");
		}else{
			this.kService.delete(service.getKfaccount());
			map.put("result", 1);
			map.put("msg", "删除成功");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit",method = {RequestMethod.POST})
	public Map<String,Object> updateKService(KService service){
		Map<String,Object> map = new HashMap<String,Object>();
		KService temService = this.kService.select(service.getKfaccount());
		if(temService==null){
			map.put("result", 0);
			map.put("msg", "客服账号不存在");
		}else{
			this.kService.updateByPrimaryKey(service);
			map.put("result", 1);
			map.put("msg", "编辑成功");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryAll",method = {RequestMethod.POST})
	public Map<String,Object> queryKService(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<KService> queryResultSet = this.kService.queryAll();
		map.put("total", queryResultSet.size());
		map.put("rows", queryResultSet);
		logger.info("query all kservice return");
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/queryByPageSize",method = {RequestMethod.POST})
	public Map<String,Object> queryByPageSize(@RequestParam(value = "page", required = false) String page,@RequestParam(value = "rows", required = false) String rows){
		Map<String,Object> map = new HashMap<String,Object>();
		//当前页 
		int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); 
		//每页显示条数  
		int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);
		//每页的开始记录  第一页为1  第二页为number +1   
		int start = (intPage-1)*number; 
		List<KService> queryResultSet = this.kService.queryAll();
		map.put("total", queryResultSet.size());
		List<KService> queryResult = this.kService.queryTByPageSize(start, number);
		map.put("rows", queryResult);
		logger.info("query all kservice return");
		return map;
	}
	
}
