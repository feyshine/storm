package com.cn.hnust.wx.api.req.entity;

public class Articles {
	
	private long id;
	private String media_id;//新增的图文消息素材的media_id
	private String title;//标题
	private String thumb_media_id;//图文消息的封面图片素材id（必须是永久mediaID）
	private String author;//作者
	private String digest;//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	private String show_cover_pic;//是否显示封面，0为false，即不显示，1为true，即显示
	private String content;//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	private String content_source_url;//图文消息的原文地址，即点击“阅读原文”后的URL

}
