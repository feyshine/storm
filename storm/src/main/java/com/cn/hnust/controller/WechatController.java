package com.cn.hnust.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.biz.MessageHandler;
import com.cn.hnust.biz.ProcessReqest;
import com.cn.hnust.pojo.WxMessage;
import com.cn.hnust.service.IWechatService;
import com.cn.hnust.service.IWxService;
import com.cn.hnust.util.L;
import com.cn.hnust.util.SignUtil;

@Controller
@RequestMapping("/wx")
public class WechatController extends BaseController{
	private static final String TIMESTAMP = "timestamp";

	private static final String ECHOSTR = "echostr";

	private static final String NONCE = "nonce";

	private static final String SIGNATURE = "signature";

	
	@Resource
	private IWechatService wechatService;
	@Resource
	private IWxService<WxMessage> messageService;
	
	
	
	@RequestMapping("/req")
	public void registerWxAPI(HttpServletRequest request,HttpServletResponse response) {
		String reqMethod = request.getMethod();
		if (reqMethod.equals("GET")) {
			// 微信加密签名
			String signature = request.getParameter(SIGNATURE);
			// 时间戳
			
			String timestamp = request.getParameter(TIMESTAMP);
			// 随机数
			String nonce = request.getParameter(NONCE);
			// 随机字符串
			String echostr = request.getParameter(ECHOSTR);

			logger.info("===============微信公众号对接开始===============！");
			boolean valid = wechatService.checkcalidata(signature, timestamp,
					nonce);
			if (valid) {
				PrintWriter out = null;
				try {
					out = response.getWriter();
					// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
					if (SignUtil.checkSignature(signature, timestamp, nonce)) {
						out.print(echostr);
						out.flush();
						logger.info("微信公众号对接成功！");
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.info("微信公众号对接出现异常！");
				} finally {
					out.close();
					out = null;
				}
			}
		} else if (reqMethod.equals("POST")) {
			response.setContentType("text/html;charset=utf-8"); //设置输出编码格式
			String result = "";
			Map map;
			try {
				map = MessageHandler.parseXml(request);
				System.out.println("开始构造消息");
				result = MessageHandler.buildXml(map,messageService);
				if (result.equals(""))
					result = "未正确响应";
			} catch (Exception e) {
				e.printStackTrace();
				L.i("发生异常：", e.getMessage());
			}
			try {
				response.getWriter().println(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	@RequestMapping("chat")
	public void chat(HttpServletRequest request, HttpServletResponse response) {
		// 微信加密签名
		String signature = request.getParameter(SIGNATURE);
		// 时间戳
		String timestamp = request.getParameter(TIMESTAMP);
		// 随机数
		String nonce = request.getParameter(NONCE);
		
		logger.info("===============微信公众号应答开始===============！");
		boolean valid = wechatService.checkcalidata(signature, timestamp,nonce);
		if (valid) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
				if (SignUtil.checkSignature(signature, timestamp, nonce)) {
					// 调用核心业务类接收消息、处理消息
					String respMessage = null;
					try {
						respMessage = ProcessReqest.process(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					out.print(respMessage);
					out.flush();
					logger.info("微信公众号对接成功！");
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("微信公众号对接出现异常！");
			} finally {
				out.close();
				out = null;
			}
		}
	}


}
