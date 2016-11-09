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
	
	private static final String TOP_TREE = "";
	private static final String TREE_DATA = "data";
	@Resource
	private INodeService<Node> nodeService;
	
	@ResponseBody
	@RequestMapping(value="/accordion",method={RequestMethod.POST})
	public Map<String,Object> queryTopNodes(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Node> topNodesSet = this.nodeService.queryChildNodes(TOP_TREE);
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
	public Map<String,Object> queryChildNodes(String pname){
		Map<String,Object> map = new HashMap<String,Object>();
//		String queryParam = null;
//		try {
//			queryParam = new String(pname.getBytes("ISO-8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		logger.info("pname = " + pname);
		List<Tree> trees = new ArrayList<Tree>();
		List<Node> childNodesSet = this.nodeService.queryChildNodes(pname);
		if(childNodesSet.size()>0){
			for (Node node : childNodesSet) {
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
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "暂无菜单节点数据");
			map.put(TREE_DATA, childNodesSet);
		}
		return map;
	}

}
