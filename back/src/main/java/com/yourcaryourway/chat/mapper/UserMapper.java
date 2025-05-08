package com.yourcaryourway.chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yourcaryourway.chat.dto.UserDTO;
import com.yourcaryourway.chat.model.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);
    
    User toEntity(UserDTO userDTO);
	
}
