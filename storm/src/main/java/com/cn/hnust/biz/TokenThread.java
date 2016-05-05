package com.cn.hnust.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.hnust.resp.AccessToken;
import com.cn.hnust.util.Config;
import com.cn.hnust.util.L;

public class TokenThread implements Runnable {
	public static String appId = "";
	public static String appSecret = "";
	public static AccessToken accessToken = null;
	private AccessTokenCallBack callback;
	
	
	
	public TokenThread(AccessTokenCallBack callback) {
		this.callback = callback;
	}

	@Override
	public void run() {
		while (true) {

			accessToken = this.getAccessToken();
			if (null != accessToken) {
				L.i("token", accessToken.getAccessToken());
				L.i("time", accessToken.getExpiresin()+"");
				callback.callBack(accessToken);
					try {
						Thread.sleep(7000 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}// 获取到access_token 休眠7000秒
			}else{
                    try {
						Thread.sleep(1000*3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} //获取的access_token为空 休眠3秒
			}
		}

	}
	
	private AccessToken getAccessToken() {
		NetWorkHelper workHelper = new NetWorkHelper();
		String Url = String.format(Config.access_token_url,this.appId,this.appSecret);
		String result = workHelper.getHttpsResponse(Url, "");
		JSONObject json = JSON.parseObject(result);
		AccessToken token = new AccessToken();
		token.setAccessToken(json.getString("access_token"));
		token.setExpiresin(json.getInteger("expires_in"));
		return token;

	}
	
	public interface AccessTokenCallBack {
		public void callBack(AccessToken accessToken);
	}
	
}
