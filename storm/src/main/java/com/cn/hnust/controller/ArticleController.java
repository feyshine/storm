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

import com.cn.hnust.pojo.Article;
import com.cn.hnust.service.IArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

	@Resource
	private IArticleService<Article> articleService;
	
	@ResponseBody
	@RequestMapping(value = "/queryall", method = { RequestMethod.POST })
	public Map<String, Object> getAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Article> articles = articleService.queryAll();
		if(articles!=null && articles.size() > 0){
			map.put(TOTAL, articles.size());
			map.put(ROWS, articles);
		}else{
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "暂无数据");
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/queryArticleByPage", method = { RequestMethod.POST })
	public Map<String, Object> queryImgByPage(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;
		List<Article> imglist = articleService.queryAll();
		map.put(TOTAL, imglist.size());
		List<Article> imgpage = articleService.queryTByPageSize(start, number);
		map.put(ROWS, imgpage);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Map<String, Object> Save(Article article){
		Map<String, Object> map = new HashMap<String, Object>();
		if (articleService.select(article.getId()) != null) {
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "ID重复");
		}else{
			articleService.save(article);
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "添加成功！");
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public Map<String, Object> Update(Article article){
		Map<String, Object> map = new HashMap<String, Object>();
		if (articleService.select(article.getId()) != null) {
			articleService.updateByPrimaryKey(article);
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "更新成功！");
		}else {
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "操作对象不存在！");
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public Map<String, Object> deleteImg(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Article query = this.articleService.select(id);
		if (query!=null) {
			this.articleService.delete(id);
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "删除成功！");
		}else {
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "文件不存在！");
		}
		
		return map;
	}
}
