package com.cn.hnust.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.hnust.pojo.ImageMessage;
import com.cn.hnust.pojo.LocationMessage;
import com.cn.hnust.pojo.TextMessage;
import com.cn.hnust.pojo.VideoMessage;
import com.cn.hnust.pojo.VoiceMessage;
import com.cn.hnust.util.MapBeanUtil;
import com.cn.hnust.util.XmlUtil;

public class ProcessReqest {

	public static String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		Map<String, Object> map = XmlUtil.parseXml(request);
		String result = "";
		String msgType = (String) map.get("MsgType");
		String respContent = "";
		// 文本消息
		if (msgType.equals("text")) {
			// respContent = TulingRobot.robot(map.get("Content"));
			 TextMessage message = new TextMessage();
			 MapBeanUtil.transMap2Bean(map, message);
			// result = Bean2ResponseXML.textMessageToXml(textMessage);
		}
		// 图片消息
		else if (msgType.equals("image")) {
			respContent = "";
			ImageMessage message = new ImageMessage();
			MapBeanUtil.transMap2Bean(map, message);
			return null;
		}
		// 声音消息
		else if (msgType.equals("voice")) {
			respContent = "";
			VoiceMessage message = new VoiceMessage();
			MapBeanUtil.transMap2Bean(map, message);
			return null;
		}
		// 视频消息
		else if (msgType.equals("video")) {
			respContent = "";
			VideoMessage message = new VideoMessage();
			MapBeanUtil.transMap2Bean(map, message);
			return null;
		}
		// 地理位置
		else if (msgType.equals("location")) {
			respContent = "";
			LocationMessage message = new LocationMessage();
			MapBeanUtil.transMap2Bean(map, message);
			
			return null;
		}
		// 事件类型
		else if (msgType.equals("event")) {
			String eventType = (String) map.get("Event");
			// 订阅
			if (eventType.equals("subscribe")) {
				respContent = "欢迎订阅我的公众号！";
				// TextMessage textMessage = Map2Bean.parseText(map,
				// respContent);
				// result = Bean2ResponseXML.textMessageToXml(textMessage);
			}
			// 取消订阅
			else if (eventType.equals("unsubscribe")) {
				// TODO
				return null;
			}
			// 点击菜单
			else if (eventType.equals("CLICK")) {
				// TODO
				return null;
			}
		}
		return result;
	}

}
