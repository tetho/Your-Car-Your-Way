package com.yourcaryourway.chat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcaryourway.chat.dto.UserDTO;
import com.yourcaryourway.chat.mapper.UserMapper;
import com.yourcaryourway.chat.model.User;
import com.yourcaryourway.chat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private final UserMapper userMapper = UserMapper.INSTANCE;
	
	public Optional<UserDTO> findByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
	    if (user.isEmpty()) {
	    	throw new RuntimeException("User with email " + email + " not found");
	    }
	    return user.map(userMapper::toDTO);
	}
	
	public Optional<UserDTO> findById(Integer id) {
		Optional<User> user = userRepository.findById(id);
	    if (user.isEmpty()) {
	    	throw new RuntimeException("User with ID \" + id + \" not found");
	    }
	    return user.map(userMapper::toDTO);
    }

	public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
	
}
