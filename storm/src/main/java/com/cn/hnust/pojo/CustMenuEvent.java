package com.cn.hnust.pojo;
/**
 * 自定义菜单事件
 * @author admin
 *
 */
public class CustMenuEvent {
	
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Event;
	private String EventKey;

	public CustMenuEvent() {
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

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	

}
