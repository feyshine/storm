package com.cn.hnust.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.pojo.Image;
import com.cn.hnust.service.IImageService;
import com.cn.hnust.util.FileUtls;


@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController{
	
	@Resource
	private IImageService<Image> imageService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toMaterial() {
		return "material";
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryallimg", method = { RequestMethod.POST })
	public Map<String, Object> getAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Image> imglist = imageService.queryAll();
		if(imglist!=null && imglist.size() > 0){
			map.put(TOTAL, imglist.size());
			map.put(ROWS, imglist);
		}else{
			map.put(RESULT, 0);
			map.put(MSG, "暂无数据");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryImgByPage", method = { RequestMethod.POST })
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
		List<Image> imglist = imageService.queryAll();
		map.put(TOTAL, imglist.size());
		List<Image> imgpage = imageService.queryTByPageSize(start, number);
		map.put(ROWS, imgpage);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadimg", method = { RequestMethod.POST })
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if(FileUtls.saveFile(file,FileUtls.getFilePath(request))){
			Image img = new Image();
			img.setImgId(new Date().getTime());
			img.setImgName(file.getOriginalFilename());
			img.setImgType(file.getContentType());
			img.setImgSize(file.getSize()+"");
			img.setImgPath(FileUtls.getFilePath(request));
			imageService.save(img);
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "上传成功！");
			logger.info(file.getOriginalFilename()+"上传成功！");
		}else {
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "上传失败！");
			logger.info(file.getOriginalFilename()+"上传失败！");
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteimg", method = { RequestMethod.POST })
	public Map<String, Object> deleteImg(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Image query = this.imageService.select(id);
		if (query!=null) {
			this.imageService.delete(id);
			map.put(RESULT, RESULT_OK);
			map.put(MSG, "删除成功！");
		}else {
			map.put(RESULT, RESULT_ERROR);
			map.put(MSG, "文件不存在！");
		}
		
		return map;
	}

}
