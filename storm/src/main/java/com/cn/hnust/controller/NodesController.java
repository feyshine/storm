package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.Nodes;
import com.cn.hnust.service.INodesService;

@Controller
@RequestMapping("/nodes")
public class NodesController {
	
	@Resource
	private INodesService<Nodes> nodesService;
	
	@ResponseBody
	@RequestMapping(value="/top",method={RequestMethod.POST})
	public Map<String,Object> queryTopNodes(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Nodes> topNodesSet = this.nodesService.queryTopNodes();
		if(topNodesSet.size()>0){
			map.put("result", 1);
			map.put("msg", "菜单节点获取成功");
			map.put("data", topNodesSet);
		}else{
			map.put("result", 0);
			map.put("msg", "暂无菜单节点数据");
			map.put("data", topNodesSet);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/children",method={RequestMethod.POST})
	public Map<String,Object> queryChildNodes(String parentid){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Nodes> childNodesSet = this.nodesService.queryChildNodes(parentid);
		if(childNodesSet.size()>0){
			map.put("result", 1);
			map.put("msg", "菜单节点获取成功");
			map.put("data", childNodesSet);
		}else{
			map.put("result", 0);
			map.put("msg", "暂无菜单节点数据");
			map.put("data", childNodesSet);
		}
		return map;
	}

}
