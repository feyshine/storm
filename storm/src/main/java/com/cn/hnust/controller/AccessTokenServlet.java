package com.cn.hnust.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cn.hnust.controller.TokenThread.AccessTokenCallBack;
import com.cn.hnust.resp.AccessToken;
import com.cn.hnust.util.FileUtls;
import com.cn.hnust.util.L;
@WebServlet(name = "AccessTokenServlet")
public class AccessTokenServlet extends HttpServlet implements AccessTokenCallBack{
	private static final long serialVersionUID = -7811255688733771828L;
	public AccessTokenServlet() {
	
	}
	
	@Override
	public void init() throws ServletException {
		//获取servlet初始参数appid和appsecret
		TokenThread.appId = getInitParameter("appid");  
        TokenThread.appSecret = getInitParameter("appsecret");
        new Thread(new TokenThread(this)).start(); //启动进程
//        new Thread(new MenuThread()).start();
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		L.i("doGet", "is running");
		super.doGet(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		L.i("doPost", "is running");
		super.doPost(req, resp);
	}

	@Override
	public void callBack(AccessToken accessToken) {
			FileUtls.writerLog(FileUtls.PATH,accessToken.getAccessToken());
	}

}
