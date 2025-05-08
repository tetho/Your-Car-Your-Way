package com.yourcaryourway.chat.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "chat_message")
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="chat_message_id")
	private Integer  chatMessageId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "support_request_id", referencedColumnName = "support_request_id", nullable = false)
	private SupportRequest supportRequest;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_user_id", referencedColumnName = "user_id", nullable = false)
	private User sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_user_id", referencedColumnName = "user_id", nullable = false)
	private User receiver;
	
	private String text;
	
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
		
}
