package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum VehicleCategory {

	SMALL_CAR("Small car"),
	MEDIUM_CAR("Medium car"),
	LARGE_CAR("Large car");
	
	private String value;
	
	VehicleCategory(String value) {
		this.value = value;
	}
	
}
