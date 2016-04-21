package com.cn.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {
	private final static String TAG = HomeController.class.getSimpleName();

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String toHome() {
		return "home";
	}

	@RequestMapping(value = "/regist", method = { RequestMethod.POST })
	public String toRegist() {
		return "redirect:/regist";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "/menu", method = { RequestMethod.GET })
	public String toMenusManger() {
		return "redirect:/menu";
	}

	@RequestMapping(value = "/message", method = { RequestMethod.GET })
	public String toMessageManger() {
		return "redirect:/message";
	}

	@RequestMapping(value = "/kservice", method = { RequestMethod.GET })
	public String toKf() {
		return "redirect:/kservice";
	}

	@RequestMapping(value = "/web", method = { RequestMethod.GET })
	public String toWeb() {
		return "redirect:/web";
	}

	@RequestMapping(value = "/material", method = { RequestMethod.GET })
	public String toMaterial() {
		return "redirect:/material";
	}

	@RequestMapping(value = "/userManager", method = { RequestMethod.GET })
	public String toUser() {
		return "redirect:/userManager";
	}

	@RequestMapping(value = "/dataAnalysis", method = { RequestMethod.GET })
	public String toDataAnalysis() {
		return "redirect:/dataAnalysis";
	}

	//
	// @RequestMapping("/showUser")
	// public String toIndex(HttpServletRequest request, Model model) {
	// int userId = Integer.parseInt(request.getParameter("id"));
	// User user = this.userService.getUserById(userId);
	// model.addAttribute("user", user);
	// return "showUser";
	// }

}
