package com.yourcaryourway.chat.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SupportRequestDTO {

	@JsonProperty("support_request_id")
	private Integer id;
	
	@JsonProperty("user_id")
	private Integer userId;
	
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	
}
