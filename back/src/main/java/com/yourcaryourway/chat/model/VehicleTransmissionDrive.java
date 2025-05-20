package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum VehicleTransmissionDrive {

	M("Manual Unspecified Drive"),
	N("Manual 4WD"),
	C("Manual AWD"),
	A("Auto Unspecified Drive"),
	B("Auto 4WD"),
	D("Auto AWD");
	
	private String value;
	
	VehicleTransmissionDrive(String value) {
		this.value = value;
	}
	
}
