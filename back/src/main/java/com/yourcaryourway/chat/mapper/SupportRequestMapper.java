package com.yourcaryourway.chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.yourcaryourway.chat.dto.SupportRequestDTO;
import com.yourcaryourway.chat.model.SupportRequest;

@Mapper
public interface SupportRequestMapper {

	SupportRequestMapper INSTANCE = Mappers.getMapper(SupportRequestMapper.class);

	@Mapping(source = "user.id", target = "userId")
    SupportRequestDTO toDTO(SupportRequest supportRequest);
    
	@Mapping(target = "user", ignore = true)
    SupportRequest toEntity(SupportRequestDTO supportRequestDTO);
	
}
