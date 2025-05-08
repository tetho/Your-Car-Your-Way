package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum SupportRequestStatus {

	PENDING("Pending"),
	CONFIRMED("Confirmed"),
	CANCELLED("Cancelled"),
	IN_PROGRESS("In progress"),
	COMPLETED("Completed");
	
	private String value;
	
	SupportRequestStatus(String value) {
		this.value = value;
	}
	
}
