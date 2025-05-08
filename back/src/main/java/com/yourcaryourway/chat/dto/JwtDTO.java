package com.yourcaryourway.chat.dto;

import lombok.Data;

@Data
public class JwtDTO {
	
	private String token;
	
	private UserDTO user;
}

