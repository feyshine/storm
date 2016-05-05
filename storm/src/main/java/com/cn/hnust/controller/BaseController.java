package com.cn.hnust.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
public class BaseController {
	
	protected static final int RESULT_ERROR = 0;
	protected static final int RESULT_OK = 1;
	protected static final String MSG = "msg";
	protected static final String RESULT = "result";
	protected static final String ROWS = "rows";
	protected static final String TOTAL = "total";
	protected static final int WX_RESPONSE_SUCCESS = 1;
	protected static final int WX_RESPONSE_FAIL = 2;
	protected static final int WX_RESPONSE_NULL = 3;
	
	public static Logger logger = LogManager.getLogger(BaseController.class.getName());
}
