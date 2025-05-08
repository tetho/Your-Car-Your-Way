package com.yourcaryourway.chat.dto;

import com.yourcaryourway.chat.model.Address;
import com.yourcaryourway.chat.model.UserRole;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDTO {

	@JsonProperty("user_id")
	private Integer id;

	private String email;
	
	private String password;
	
	private String name;
	
	private String firstname;
	
	private Date birthdate;
	
	private String phone;
	
	private Address address;
	
	private UserRole role;
	
}
