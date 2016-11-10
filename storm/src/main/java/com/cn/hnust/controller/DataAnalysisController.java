package com.cn.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dataAnalysis")
public class DataAnalysisController {

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toDataAnalysis() {
		return "data_analysis";
	}
}
