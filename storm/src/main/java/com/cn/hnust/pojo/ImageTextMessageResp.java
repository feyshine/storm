package com.cn.hnust.pojo;
/**
 * 回复图文消息
 * @author admin
 *
 */
public class ImageTextMessageResp {
	
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String ArticleCount;
	private String Articles;
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;

	public ImageTextMessageResp() {
		// TODO Auto-generated constructor stub
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public String getArticles() {
		return Articles;
	}

	public void setArticles(String articles) {
		Articles = articles;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
	
	

}
