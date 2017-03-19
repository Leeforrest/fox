package com.coolfish.gmserver.config;

/**
 * 网页对应的html路径
 * 默认根路径是resources/templates/***.html
 * @author Forrest
 *
 */
public enum Page {
	/** 登录页面*/
	Login("login"),
	/** 主页*/
	Main_Page("main"),
	/** 管理员*/
	User("user"),
	/** 玩家*/
	Player("player"),
	/** 玩家基本信息*/
	PlayerBaseInfo("player_base"),
	;
	
	String htmlPath;//与html名字相同

	Page(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public String getHtmlPath() {
		return htmlPath;
	}

	
}
