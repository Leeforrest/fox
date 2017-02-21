package com.coolfish.gmserver.config;

public enum GMUserType {
	/**
	 * 超级管理员
	 */
	Admin(1),
	;
	int value;
	private GMUserType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public GMUserType valueOf(int value) {
		for(GMUserType type : GMUserType.values()) {
			if(type.getValue() == this.value) {
				return type;
			}
		}
		return null;
	}
}
