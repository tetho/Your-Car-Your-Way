package com.yourcaryourway.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcaryourway.chat.dto.ChatMessageDTO;
import com.yourcaryourway.chat.service.ChatMessageService;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping
    public ChatMessageDTO sendMessage(@RequestBody ChatMessageDTO chatMessageDTO) {
        return chatMessageService.saveMessage(chatMessageDTO);
    }

    @GetMapping("/support-request/{id}")
    public List<ChatMessageDTO> getMessagesBySupportRequest(@PathVariable int id) {
        return chatMessageService.getMessagesBySupportRequest(id);
    }
    
}
