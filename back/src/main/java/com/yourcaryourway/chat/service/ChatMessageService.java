package com.yourcaryourway.chat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcaryourway.chat.dto.ChatMessageDTO;
import com.yourcaryourway.chat.mapper.ChatMessageMapper;
import com.yourcaryourway.chat.model.ChatMessage;
import com.yourcaryourway.chat.model.SupportRequest;
import com.yourcaryourway.chat.model.User;
import com.yourcaryourway.chat.repository.ChatMessageRepository;
import com.yourcaryourway.chat.repository.SupportRequestRepository;
import com.yourcaryourway.chat.repository.UserRepository;

@Service
public class ChatMessageService {

	@Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupportRequestRepository supportRequestRepository;

    private final ChatMessageMapper chatMessageMapper = ChatMessageMapper.INSTANCE;

    public ChatMessageDTO saveMessage(ChatMessageDTO chatMessageDTO) {
    	SupportRequest supportRequest = supportRequestRepository.findById(chatMessageDTO.getSupportRequestId()).orElseThrow();
    	User user = userRepository.findById(chatMessageDTO.getUserId()).orElseThrow();
        ChatMessage message = chatMessageMapper.toEntity(chatMessageDTO);
        message.setSupportRequest(supportRequest);
        message.setUser(user);
        ChatMessage saved = chatMessageRepository.save(message);
        return chatMessageMapper.toDTO(saved);
    }

    public List<ChatMessageDTO> getMessagesBySupportRequest(int supportRequestId) {
        SupportRequest supportRequest = supportRequestRepository.findById(supportRequestId).orElseThrow();
        return chatMessageRepository.findBySupportRequest(supportRequest)
                .stream()
                .map(chatMessageMapper::toDTO)
                .toList();
    }
	
}
