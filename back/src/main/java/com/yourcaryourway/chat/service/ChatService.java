package com.yourcaryourway.chat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yourcaryourway.chat.dto.ChatMessageDTO;

@Service
public class ChatService {

    private final List<ChatMessageDTO> messages = Collections.synchronizedList(new ArrayList<>());

    public void saveMessage(ChatMessageDTO message) {
        messages.add(message);
    }

    public List<ChatMessageDTO> getAllMessages() {
        return new ArrayList<>(messages);
    }
    
}
