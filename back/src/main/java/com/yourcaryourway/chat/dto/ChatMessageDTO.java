package com.yourcaryourway.chat.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatMessageDTO {

	@JsonProperty("user_id")
	private Integer userId;
	
	private String text;
	
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	
	private String name;
	
	private String firstname;
	
}
