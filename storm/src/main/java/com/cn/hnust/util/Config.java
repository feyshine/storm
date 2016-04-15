package com.cn.hnust.util;

public final class  Config {

	//access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token,
	//access_token的存储至少要保留512个字符空间。access_token的有效期目前为2个小时，
	//需定时刷新，重复获取将导致上次获取的access_token失效
	public static final String access_token = "";
	//http get method
	public static final String access_token_url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	//自定义菜单创建接口 Post
	public final static String creat_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	//自定义菜单查询接口 GET
	public final static String find_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	//自定义菜单删除接口 GET
	public final static String delete_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
}
