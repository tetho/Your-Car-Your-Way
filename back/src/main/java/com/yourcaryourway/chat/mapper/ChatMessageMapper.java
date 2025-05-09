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
    @Mapping(source = "user.id", target = "userId")
	ChatMessageDTO toDTO(ChatMessage chatMessage);
    
	@Mapping(target = "supportRequest", ignore = true)
    @Mapping(target = "user", ignore = true)
	ChatMessage toEntity(ChatMessageDTO chatMessageDTO);
	
}
