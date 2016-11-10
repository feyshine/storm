package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.State;
import com.cn.hnust.service.IStateService;

@Controller
@RequestMapping("/state")
public class StateController extends BaseController{
	
	@Resource
	private IStateService<State> stateService;
	
	@ResponseBody
	@RequestMapping(value = "/queryall", method = { RequestMethod.POST })
	public Map<String, Object> getAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<State> states = stateService.queryAll();
		if(states!=null && states.size() > 0){
			map.put(TOTAL, states.size());
			map.put(ROWS, states);
		}else{
			map.put(RESULT, 0);
			map.put(MSG, "暂无数据");
		}
		return map;
	}
}
