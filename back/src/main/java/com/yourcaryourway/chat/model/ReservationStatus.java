package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum ReservationStatus {

	PENDING("Pending"),
	CONFIRMED("Confirmed"),
	CANCELLED("Cancelled"),
	IN_PROGRESS("In progress"),
	COMPLETED("Completed");
	
	private String value;
	
	ReservationStatus(String value) {
		this.value = value;
	}
	
}
