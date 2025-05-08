package com.yourcaryourway.chat.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatMessageDTO {

	@JsonProperty("chat_message_id")
	private Integer chatMessageId;
	
	@JsonProperty("support_request_id")
	private Integer supportRequestId;
	
	@JsonProperty("sender_user_id")
	private Integer senderUserId;
	
	@JsonProperty("receiver_user_id")
	private Integer receiverUserId;
	
	private String text;
	
	private LocalDateTime createdAt;
	
}
