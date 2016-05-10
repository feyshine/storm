package com.cn.hnust.wx.api.req.entity;
//二级菜单数组,父菜单
public class ComplexButton extends Button{
	
	private String type;
	private String key;
	private String url;
	private String value;
	private String media_id;
	private String matchrule;
	private String tag_id;
	private String sex;
	private String client_platform_type;
	private String country;
	private String province;
	private String city;
	private String language;
	 private CommonButton[] sub_button;
	 
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CommonButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(CommonButton[] sub_button) {
		this.sub_button = sub_button;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getMatchrule() {
		return matchrule;
	}

	public void setMatchrule(String matchrule) {
		this.matchrule = matchrule;
	}

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getClient_platform_type() {
		return client_platform_type;
	}

	public void setClient_platform_type(String client_platform_type) {
		this.client_platform_type = client_platform_type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	 
	 

}
