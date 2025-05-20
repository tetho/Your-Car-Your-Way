package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum VehicleFuelAirCond {

	R("Unspecified Fuel/Power With Air"),
	N("Unspecified Fuel/Power Without Air"),
	D("Diesel Air"),
	Q("Diesel No Air"),
	H("Hybrid"),
	I("Hybrid Plug in");
	
	private String value;
	
	VehicleFuelAirCond(String value) {
		this.value = value;
	}
	
}
