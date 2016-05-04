package com.cn.hnust.wx.api.req.entity;
//二级菜单数组,父菜单
public class ComplexButton extends Button{
	
	 private String type;
	 private String key;
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
	 
	 

}
