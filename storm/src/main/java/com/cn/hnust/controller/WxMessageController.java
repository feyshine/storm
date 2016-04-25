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

import com.cn.hnust.pojo.WxMessage;
import com.cn.hnust.service.IWxService;

@Controller
@RequestMapping("/message")
public class WxMessageController extends BaseController{
	
	@Resource
	private IWxService<WxMessage> messageService;
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/add",method = {RequestMethod.POST})
	public Map<String,Object> addMessage(WxMessage message){
		Map<String,Object> map = new HashMap<String,Object>();
		WxMessage tempMessage = messageService.queryById(message.getId());
		if(tempMessage==null){
			messageService.save(message);
			map.put("result", 1);
			map.put("msg", "保存成功");
			logger.info("消息保存成功");
		}else{
			map.put("result", 0);
			map.put("msg", "消息已经存在！");
			logger.info("消息保存失败");
		}

		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/edit",method = {RequestMethod.POST})
	public Map<String,Object> updateMessage(WxMessage message){
		Map<String,Object> map = new HashMap<String,Object>();
		WxMessage tempMessage = messageService.queryById(message.getId());
		if(tempMessage==null){
			map.put("result", 0);
			map.put("msg", "消息不存在！");
			logger.info("消息更新失败");
		}else{
			messageService.update(message);
			map.put("result", 1);
			map.put("msg", "更新成功");
			logger.info("消息更新成功");
		}
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping()
	public Map<String,Object> deleteMessage(WxMessage message){
		Map<String,Object> map = new HashMap<String,Object>();
		WxMessage tempMessage = messageService.queryById(message.getId());
		if(tempMessage==null){
			map.put("result", 0);
			map.put("msg", "消息不存在！");
			logger.info("消息删除失败");
		}else{
			messageService.update(message);
			map.put("result", 1);
			map.put("msg", "删除成功");
			logger.info("消息删除成功");
		}
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST })
	public Map<String,Object> queryAllMessage(WxMessage message){
		Map<String,Object> map = new HashMap<String,Object>();
		List<WxMessage> resultList = messageService.queryAll();
		int total = resultList.size();
		map.put("total", total);
		map.put("rows", resultList);
		logger.info("query all message return");
		return map;
	}
	
	@ResponseBody//加了这行返回json数据
	@RequestMapping(value = "/queryByPageSize", method = { RequestMethod.POST })
	public Map<String,Object> queryMessageByPageSize(@RequestParam(value = "page", required = false) String page,@RequestParam(value = "rows", required = false) String rows){
		Map<String,Object> map = new HashMap<String,Object>();
		//当前页 
		int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); 
		//每页显示条数  
		int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);
		//每页的开始记录  第一页为1  第二页为number +1   
		int start = (intPage-1)*number; 
		int total = messageService.queryAll().size();
		map.put("total", total);
		List<WxMessage> resultList = messageService.queryByPageSize(start, number);
		map.put("rows", resultList);
		logger.info("query  message  return");
		return map;
	}
}
