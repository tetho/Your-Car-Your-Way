package com.yourcaryourway.chat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yourcaryourway.chat.dto.UserDTO;

@Service
public class AuthService {

	private final UserService userService;

	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public AuthService(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	public boolean authenticate(String email, String password) {
		Optional<UserDTO> optionalUser = userService.findByEmail(email);
		if (optionalUser.isPresent()) {
			UserDTO user = optionalUser.get();
			return passwordEncoder.matches(password, user.getPassword());
		} else {
			throw new RuntimeException("Authentication failed: User not found.");
		}
	}

	public void register(UserDTO user) {
		if (user == null || user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new RuntimeException("Registration failed: Invalid user data or password.");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userService.createUser(user);
		} catch (Exception e) {
			throw new RuntimeException("Error occurred during user registration: " + e.getMessage(), e);
		}
	}
}
