package com.yourcaryourway.chat.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.yourcaryourway.chat.dto.UserDTO;
import com.yourcaryourway.chat.service.JwtService;
import com.yourcaryourway.chat.service.UserService;

@Component
public class JwtChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor == null) return message;

        System.out.println("JwtChannelInterceptor preSend called with command: " + accessor.getCommand());

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            List<String> authHeaders = accessor.getNativeHeader("Authorization");
            if (authHeaders != null && !authHeaders.isEmpty()) {
                String token = authHeaders.get(0).replace("Bearer ", "");

                if (jwtService.validateToken(token)) {
                    String email = jwtService.extractUsername(token);
                    Optional<UserDTO> userOpt = userService.findByEmail(email);
                    if (userOpt.isPresent()) {
                        Authentication auth = new UsernamePasswordAuthenticationToken(
                            email, null, List.of(new SimpleGrantedAuthority("ROLE_USER"))
                        );
                        accessor.setUser(auth);
                        accessor.setLeaveMutable(true);

                        System.out.println("WebSocket authentication success for user: " + email);
                    } else {
                        System.out.println("WebSocket authentication failed for user: " + email);
                    }
                }
            }
        }

        return message; // OK as long as headers are mutable
    }
}
