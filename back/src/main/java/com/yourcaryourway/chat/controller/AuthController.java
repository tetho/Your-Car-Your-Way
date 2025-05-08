package com.yourcaryourway.chat.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcaryourway.chat.dto.AuthDTO;
import com.yourcaryourway.chat.dto.JwtDTO;
import com.yourcaryourway.chat.dto.UserDTO;
import com.yourcaryourway.chat.service.AuthService;
import com.yourcaryourway.chat.service.JwtService;
import com.yourcaryourway.chat.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	private final JwtService jwtService;
	
	private final UserService userService;

	@Autowired
	public AuthController(AuthService authService, JwtService jwtService, UserService userService) {
		this.authService = authService;
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO user, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessages = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        } else {
            try {
                authService.register(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
                String token = jwtService.generateToken(authentication);
                JwtDTO jwtDTO = new JwtDTO();
                jwtDTO.setToken(token);
                return ResponseEntity.ok(jwtDTO);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO user) {
        try {
            boolean isAuthenticated = authService.authenticate(user.getUsername(), user.getPassword());
            if (isAuthenticated) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                String token = jwtService.generateToken(authentication);
                JwtDTO jwtDTO = new JwtDTO();
                jwtDTO.setToken(token);
                return ResponseEntity.ok(jwtDTO);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        try {
            String email = authentication.getName();
            Optional<UserDTO> optionalUserDTO = userService.findByEmail(email);
            return optionalUserDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
}
