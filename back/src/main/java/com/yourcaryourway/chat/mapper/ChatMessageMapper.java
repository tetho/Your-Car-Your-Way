package com.yourcaryourway.chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.yourcaryourway.chat.dto.ChatMessageDTO;
import com.yourcaryourway.chat.model.ChatMessage;

@Mapper
public interface ChatMessageMapper {

	ChatMessageMapper INSTANCE = Mappers.getMapper(ChatMessageMapper.class);

	@Mapping(source = "supportRequest.id", target = "supportRequestId")
    @Mapping(source = "sender.id", target = "senderUserId")
    @Mapping(source = "receiver.id", target = "receiverUserId")
	ChatMessageDTO toDTO(ChatMessage chatMessage);
    
	@Mapping(target = "supportRequest", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
	ChatMessage toEntity(ChatMessageDTO chatMessageDTO);
	
}
