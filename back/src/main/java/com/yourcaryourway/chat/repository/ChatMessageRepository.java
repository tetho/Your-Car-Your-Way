package com.yourcaryourway.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourcaryourway.chat.model.ChatMessage;
import com.yourcaryourway.chat.model.SupportRequest;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
	
    List<ChatMessage> findBySupportRequest(SupportRequest supportRequest);
    
}
