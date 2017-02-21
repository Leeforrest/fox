package com.coolfish.gmserver.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

public class UserMananger {
	private static final Long LOGIN_TIME_OUT = 10*TimeUnit.MINUTES.toMillis(1);
	private static UserMananger userMgr = new UserMananger();
	
	private Map<Integer, Long> userLogin = new ConcurrentHashMap<>();
	
	private UserMananger () {
		
	}
	
	public static UserMananger getInstance() {
		return userMgr;
	}
	
	
	/**
	 * 用户登录记录登录状态
	 * @param userId
	 */
	public void onLogin(Integer userId) {
		userLogin.put(userId, System.currentTimeMillis());
	}
	
	/**
	 * 检查登录状态
	 * @return
	 */
	public boolean check(Integer userId) {
		long now = System.currentTimeMillis();
		Long lastConnectTime = userLogin.get(userId);
		if(lastConnectTime != null && lastConnectTime+LOGIN_TIME_OUT > now) {
			userLogin.put(userId, now);
			return true;
		}
		userLogin.remove(userId);
		return false;
	}
	
	/**
	 * 退出登录 
	 */
	public void onLogout(Integer userId) {
		this.userLogin.remove(userId);
	}
	
	public int getUserId(HttpSession session) {
		Object obj = session.getAttribute("id");
		if(obj == null && !(obj instanceof Integer)) {
			return -1;
		}
		return Integer.parseInt(obj.toString());
	}
}
