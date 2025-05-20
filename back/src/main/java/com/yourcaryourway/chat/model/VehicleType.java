package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum VehicleType {

	B("2-3 Door"),
	C("2/4 Door"),
	D("4-5 Door"),
	W("Wagon/Estate"),
	V("Passenger Van"),
	L("Limousine/Sedan");
	
	private String value;
	
	VehicleType(String value) {
		this.value = value;
	}
	
}
