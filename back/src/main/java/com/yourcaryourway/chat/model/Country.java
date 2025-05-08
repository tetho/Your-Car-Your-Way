package com.yourcaryourway.chat.model;

import lombok.Getter;

@Getter
public enum Country {

	FRANCE("France"),
	ITALY("Italy"),
	SPAIN("Spain"),
	UNITED_STATES("United States");
	
	private String value;
	
	Country(String value) {
		this.value = value;
	}
	
}
