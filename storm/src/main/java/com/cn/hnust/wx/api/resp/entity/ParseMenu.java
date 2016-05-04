package com.cn.hnust.wx.api.resp.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.hnust.wx.api.req.entity.ComplexButton;


public class ParseMenu {
	
	public static List<ComplexButton> parseComplexButton(String json){
		JSONObject jb = JSON.parseObject(json);
		JSONObject subjb = jb.getJSONObject("menu");
		JSONArray ja = subjb.getJSONArray("button"); 
		List<ComplexButton> list = JSON.parseArray(ja.toString(), ComplexButton.class);
		return list;
		
	}

}
