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
	
	//上传图文消息内的图片获取URL POST
	public final static String upload_ImgMsg_return_url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=%s";
	//上传图文消息素材 POST
	public final static String upload_image_message_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=%s";
	//根据分组进行群发 POST
	public final static String send_msg_by_group_url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";
	//根据OpenID列表群发 POST
	public final static String seng_msg_by_openIds_url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%s";
	//删除群发 post
	public final static String delete_group_msg_url = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=%s";
	//预览接口  POST
	public final static String pre_view_url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=%s";
	//查询群发消息发送状态  POST
	public final static String group_message_state_url = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=%s";
	
	//创建分组 POST
	public static final String creat_group_url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=%s";
	//查询所有分组  GET
	public static final String query_group_all_url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=%s";
	//查询用户所在分组 POST
	public static final String query_group_by_user = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=%s";
	//修改分组名  POST
	public static final String reset_group_name_url = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=%s";
	//移动用户分组 POST
	public final static String move_user_group_url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=%s";
	//批量移动用户分组 POST
	public final static String move_users_group_url = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=%s";
	//删除分组 POST
	public final static String delete_group_url = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=%s";
	
	//设置用户备注名  POST
	public final static String rename_user_url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%s";
	//获取用户基本信息（包括UnionID机制） GET 
	public final static String get_userInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	//批量获取用户基本信息  POST
	public final static String get_userInfos_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=%s";
	//获取用户列表 GET
	public final static String get_users_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
	
	//新增永久图文素材POST
	public final static String add_newsinfo_url ="https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";
	//新增其他类型永久素材 POST
	public final static String add_other_material_url ="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";
	//获取永久素材 POST
	public final static String get_material_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=%s";
	//删除永久素材 post
	public final static String delete_material_url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=%s";
	//修改永久图文素材 post
	public final static String update_material_url = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=%s";
	//获取素材总数 post
	public final static String get_material_totalNum = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=%s";
	//获取素材列表 post
	public final static String get_material_list = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";
	
}
