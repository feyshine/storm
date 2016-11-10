package com.cn.hnust.controller;
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
import com.cn.hnust.pojo.NodeChildren;
import com.cn.hnust.resp.Tree;
import com.cn.hnust.service.INodeChildrenService;
import com.cn.hnust.service.INodeService;

@Controller
@RequestMapping("/node")
public class NodesController extends BaseController{
	
	private static final String TREE_DATA = "data";
	@Resource
	private INodeService<Node> nodeService;
	@Resource
	private INodeChildrenService<NodeChildren> nodeChildrenService;
	
	@ResponseBody
	@RequestMapping(value="/accordion",method={RequestMethod.POST})
	public Map<String,Object> queryTopNodes(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Node> topNodesSet = this.nodeService.queryTopNodes();
		logger.info("菜单请求收到....");
		if(topNodesSet.size()>0){
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "菜单节点获取成功");
			map.put(TREE_DATA, topNodesSet);
		}else{
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "暂无菜单节点数据");
			map.put(TREE_DATA, topNodesSet);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/tree",method={RequestMethod.POST})
	public Map<String,Object> queryChildNodes(String pid){
		Map<String,Object> map = new HashMap<String,Object>();
		logger.info("pname = " + pid);
		List<Tree> trees = new ArrayList<Tree>();
		List<NodeChildren> childNodesSet = this.nodeChildrenService.queryChildNodes(pid);
		logger.info("childNodesSet = " + childNodesSet.size());
		if(childNodesSet.size()>0){
			for (NodeChildren node : childNodesSet) {
				Tree tree = new Tree();
				tree.id = node.getId();
				tree.text = node.getName();
				tree.checked = node.getChecked();
				tree.attributes = node.getUrl();
				trees.add(tree);
			}
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "菜单节点获取成功");
			map.put(TREE_DATA, trees);
		}else{
			logger.info("pname = " + pid + ",暂无子节点数据");
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "暂无菜单节点数据");
			map.put(TREE_DATA, childNodesSet);
		}
		return map;
	}

}
