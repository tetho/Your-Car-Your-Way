package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum UserRole {

	CUSTOMER("Client"),
	SUPPORT("Support");
	
	private String value;
	
	UserRole(String value) {
		this.value = value;
	}
	
}
