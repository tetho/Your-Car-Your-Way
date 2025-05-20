package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum VehicleCategory {

	M("Mini"),
	N("Mini Elite"),
	E("Economy"),
	H("Economy Lite"),
	C("Compact"),
	D("Compact Lite");
	
	private String value;
	
	VehicleCategory(String value) {
		this.value = value;
	}
	
}
