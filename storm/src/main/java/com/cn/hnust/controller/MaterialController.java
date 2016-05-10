package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.util.FileUtls;

@Controller
@RequestMapping("/material")
public class MaterialController {
	

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toMaterial() {
		return "material";
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadimg", method = { RequestMethod.POST })
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if(FileUtls.saveFile(file,FileUtls.getFilePath(request))){
			
		}else {
			
		}
		return map;
	}

}
