package com.yourcaryourway.chat.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import com.yourcaryourway.chat.dto.ChatMessageDTO;
import com.yourcaryourway.chat.dto.UserDTO;
import com.yourcaryourway.chat.service.ChatService;
import com.yourcaryourway.chat.service.UserService;

@Controller
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public ChatWebSocketController(SimpMessagingTemplate messagingTemplate, UserService userService, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.userService = userService;
        this.chatService = chatService;
    }

    @MessageMapping("/chat")
    public void receiveMessage(ChatMessageDTO message, Principal principal) {
        System.out.println("Received message from: " + (principal != null ? principal.getName() : "null"));

        if (principal == null) {
            throw new AccessDeniedException("WebSocket message rejected: no authenticated user");
        }

        Optional<UserDTO> userOpt = userService.findByEmail(principal.getName());
        if (userOpt.isPresent()) {
            UserDTO user = userOpt.get();
            message.setUserId(user.getId());
            message.setName(user.getName());
            message.setFirstname(user.getFirstname());
            message.setCreatedAt(LocalDateTime.now());

            chatService.saveMessage(message);
            messagingTemplate.convertAndSend("/topic/messages", message);
        }
    }

}

