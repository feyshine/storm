package com.cn.hnust.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.Node;
import com.cn.hnust.resp.Tree;
import com.cn.hnust.service.INodeService;

@Controller
@RequestMapping("/node")
public class NodesController extends BaseController{
	
	@Resource
	private INodeService<Node> nodeService;
	
	@ResponseBody
	@RequestMapping(value="/accordion",method={RequestMethod.POST})
	public Map<String,Object> queryTopNodes(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Node> topNodesSet = this.nodeService.queryChildNodes("");
		logger.info("菜单请求收到....");
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
	@RequestMapping(value="/tree",method={RequestMethod.POST})
	public Map<String,Object> queryChildNodes(String pname){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String queryParam = new String(pname.getBytes("ISO-8859-1"),"UTF-8");
			logger.info("pname = " + queryParam);
			List<Tree> trees = new ArrayList<Tree>();
			List<Node> childNodesSet = this.nodeService.queryChildNodes(queryParam);
			if(childNodesSet.size()>0){
				for (Node node : childNodesSet) {
					Tree tree = new Tree();
					tree.id = node.getId();
					tree.text = node.getName();
					tree.checked = node.getChecked();
					tree.attributes = node.getUrl();
					trees.add(tree);
				}
				map.put("result", 1);
				map.put("msg", "菜单节点获取成功");
				map.put("data", trees);
			}else{
				map.put("result", 0);
				map.put("msg", "暂无菜单节点数据");
				map.put("data", childNodesSet);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;
	}

}
