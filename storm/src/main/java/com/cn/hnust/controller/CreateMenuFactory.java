package com.cn.hnust.controller;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.wx.api.req.entity.Button;
import com.cn.hnust.wx.api.req.entity.CommonButton;
import com.cn.hnust.wx.api.req.entity.ComplexButton;
import com.cn.hnust.wx.api.req.entity.Menu;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

public class CreateMenuFactory {

	public static String creatMenu(List<com.cn.hnust.pojo.Button> parents) {
		Menu menu = getMenu(parents);
		return JSON.toJSONString(menu);
	}


	private static Menu getMenu(List<com.cn.hnust.pojo.Button> parents) {
		Button[] preParentsButton = new Button[parents.size()];
		for (int j=0;j< parents.size();j++) {
			ComplexButton pcb = new ComplexButton();
			pcb.setName(parents.get(j).getName());
			pcb.setKey(parents.get(j).getNkey());
			pcb.setType(parents.get(j).getType());
			pcb.setMedia_id(parents.get(j).getMediaId());
			pcb.setUrl(parents.get(j).getUrl());
			com.cn.hnust.pojo.Button[] parent_sub_button = parents.get(j).getSub_button();
			if (parent_sub_button!=null&&!parent_sub_button.equals("")) {
				//如果有子菜单，那么父菜单失去配置功能，微信也是这样做的
				pcb.setKey(null);
				pcb.setType(null);
				pcb.setMedia_id(null);
				pcb.setUrl(null);
				
				CommonButton[] sub_button = new CommonButton[parent_sub_button.length];
				for (int i = 0; i < parent_sub_button.length; i++) {
					CommonButton childButton = new CommonButton();
					childButton.setName(parent_sub_button[i].getName());
					childButton.setKey(parent_sub_button[i].getNkey());
					childButton.setType(parent_sub_button[i].getType());
					childButton.setUrl(parent_sub_button[i].getUrl());
					childButton.setMedia_id(parent_sub_button[i].getMediaId());
					sub_button[i] = childButton;
				}
				pcb.setSub_button(sub_button);
			}
			preParentsButton[j] = pcb;
		}
	
		Menu menuHelpMenu = new Menu();
		menuHelpMenu.setButton(preParentsButton);
		return menuHelpMenu;
	}


//	private static Menu getMenu() {
//		CommonButton btn11 = new CommonButton();
//		btn11.setName("天气预报");
//		btn11.setType("click");
//		btn11.setKey("11");
//
//		CommonButton btn21 = new CommonButton();
//		btn21.setName("经典游戏");
//		btn21.setType("click");
//		btn21.setKey("21");
//
//		CommonButton btn31 = new CommonButton();
//		btn31.setName("微社区");
//		btn31.setType("click");
//		btn31.setKey("31");
//
//		ComplexButton mainBtn1 = new ComplexButton();
//		mainBtn1.setName("生活助手");
//		mainBtn1.setSub_button(new CommonButton[] { btn11 });
//
//		ComplexButton mainBtn2 = new ComplexButton();
//		mainBtn2.setName("娱乐休闲");
//		mainBtn2.setSub_button(new CommonButton[] { btn21 });
//
//		ComplexButton mainBtn3 = new ComplexButton();
//		mainBtn3.setName("更多体验");
//		mainBtn3.setSub_button(new CommonButton[] { btn31 });
//
//		/**
//		 * 
//		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
//		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
//		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
//		 */
//		Menu menu = new Menu();
//		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
//
//		return menu;
//	}

}
