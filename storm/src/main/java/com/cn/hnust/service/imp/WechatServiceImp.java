package com.cn.hnust.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.SignMapper;
import com.cn.hnust.service.IWechatService;
import com.cn.hnust.util.L;
import com.cn.hnust.util.StringUtil;
@Service("wechatService")
public class WechatServiceImp implements IWechatService {
	private static final String TAG = WechatServiceImp.class.getSimpleName();
	
	@Resource
	private SignMapper signDao;
	
	@Override
	public boolean checkcalidata(String signature, String timestamp, String nonce) {
		boolean flag = true;
		if(StringUtil.isEmpty(signature)){
			L.i(TAG, "signature is empty！");
			flag = false;
		}
		if (StringUtil.isEmpty(timestamp)) {
			L.i(TAG, "timestamp is empty！");
			flag = false;
		}
		if (StringUtil.isEmpty(nonce)) {
			L.i(TAG, "nonce is empty！");
			flag = false;
		}
		
		return flag;
	}

	

}
