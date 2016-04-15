package com.cn.hnust.pojo;
/**
 * 关注/取消事件
 * @author admin
 *
 */
public class AttentionEvent {
	
	private String MsgType;
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String Event;//事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	private String EventKey;
	private String Ticket;

	public AttentionEvent() {
		// TODO Auto-generated constructor stub
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
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

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	
	

	
}
