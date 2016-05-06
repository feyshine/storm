package com.cn.hnust.wx.api.req.entity;
//二级菜单数组,父菜单
public class ComplexButton extends Button{
	
	 private String type;
	 private String key;
	 private String url;
	 private String media_id;
	 private CommonButton[] sub_button;
	 
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public CommonButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(CommonButton[] sub_button) {
		this.sub_button = sub_button;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	 
	 

}
