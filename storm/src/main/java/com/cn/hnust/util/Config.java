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
	public final static String find_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
	//自定义菜单删除接口 GET
	public final static String delete_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
	
	//创建个性化菜单 post
	public final static String creat_cust_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=%s";
	//删除个性化菜单 post
	public final static String delete_cust_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=%s";
	//测试个性化菜单匹配结果 post
	public final static String test_cust_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=%s";
	//获取自定义菜单配置接口 GET
	public final static String get_cust_menu_config_url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=%s";
	
	//添加客服帐号 POST
	public final static String add_customer_service_url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=%s";
	//修改客服帐号 POST
	public final static String reset_customer_service_url = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=%s";
	//删除客服帐号 GET
	public final static String delete_customer_service_url ="https://api.weixin.qq.com/customservice/kfaccount/del?access_token=%s";
	//设置客服帐号的头像  POST/FORM
	public final static String set_customer_service_avator_url = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=%s&kf_account=KFACCOUNT";
	//获取所有客服账号 GET
	public final static String get_all_customer_service_url = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=%s";
	//客服接口-发消息  POST
	public final static String send_msg_to_customer_service_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
	
}
