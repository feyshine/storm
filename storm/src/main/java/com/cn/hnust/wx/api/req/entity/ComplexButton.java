package com.cn.hnust.wx.api.req.entity;
//二级菜单数组,父菜单
public class ComplexButton extends Button{
	
	 private CommonButton[] sub_button;

	public CommonButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(CommonButton[] sub_button) {
		this.sub_button = sub_button;
	}
	 
	 

}
