package com.coolfish.gmserver.mvc.model;

public class PlayerInfo {
	private long playerUid;
	private String userName;
	public long getPlayerUid() {
		return playerUid;
	}
	public void setPlayerUid(long playerUid) {
		this.playerUid = playerUid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "PlayerInfo [playerUid=" + playerUid + ", userName=" + userName + "]";
	}
	
	
}
