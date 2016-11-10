package com.cn.hnust.pojo;
/**
 * 接收视频消息
 * @author admin
 *
 */
public class VideoMessage {
	private String MsgId;
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String MediaId;
	private String ThumbMediaId;


	public VideoMessage() {
		// TODO Auto-generated constructor stub
	}


	public String getMsgId() {
		return MsgId;
	}


	public void setMsgId(String msgId) {
		MsgId = msgId;
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


	public String getMediaId() {
		return MediaId;
	}


	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}


	public String getThumbMediaId() {
		return ThumbMediaId;
	}


	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	

}
