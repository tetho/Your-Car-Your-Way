package com.yourcaryourway.chat.controller;

import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO user) {
        try {
            boolean isAuthenticated = authService.authenticate(user.getEmail(), user.getPassword());
            if (isAuthenticated) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
                String token = jwtService.generateToken(authentication);
                JwtDTO jwtDTO = new JwtDTO();
                jwtDTO.setToken(token);
                userService.findByEmail(user.getEmail()).ifPresent(jwtDTO::setUser);
                return ResponseEntity.ok(jwtDTO);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = principal.getName();
        Optional<UserDTO> userOpt = userService.findByEmail(email);
        return userOpt.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.logout();
        return ResponseEntity.ok().build();
    }
    
}