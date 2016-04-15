package com.cn.hnust.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public final class XmlUtil {

	public static Map parseXml(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 从request中取得输入流
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String xml = sb.toString();
		// 读取输入流
		Document document = null;
		try {
			document = DocumentHelper.parseText(xml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			// 对于CDATA区域内的内容，XML解析程序不会处理，而是直接原封不动的输出。
			map.put(e.getName(), e.getText());
		}
		return map;
	}

}
