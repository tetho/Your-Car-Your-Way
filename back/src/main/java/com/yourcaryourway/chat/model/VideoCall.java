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
@Table(name = "video_call")
public class VideoCall {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="video_call_id")
	private Integer videoCallId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "support_request_id", referencedColumnName = "support_request_id", nullable = false)
	private SupportRequest supportRequest;
		
	@Column(name = "started_at", updatable = false)
	private LocalDateTime startedAt;

	@Column(name = "ended_at", updatable = false)
	private LocalDateTime endedAt;
	
	@PrePersist
	protected void onCreate() {
		this.startedAt = LocalDateTime.now();
	}
}
