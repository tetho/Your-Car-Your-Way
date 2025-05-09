package com.yourcaryourway.chat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcaryourway.chat.dto.SupportRequestDTO;
import com.yourcaryourway.chat.mapper.SupportRequestMapper;
import com.yourcaryourway.chat.model.SupportRequest;
import com.yourcaryourway.chat.model.User;
import com.yourcaryourway.chat.repository.SupportRequestRepository;
import com.yourcaryourway.chat.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupportRequestService {

	@Autowired
    private SupportRequestRepository supportRequestRepository;

    @Autowired
    private UserRepository userRepository;

    private final SupportRequestMapper supportRequestMapper = SupportRequestMapper.INSTANCE;
    
    public SupportRequestDTO create(SupportRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUserId()));
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setUser(user);
        SupportRequest saved = supportRequestRepository.save(supportRequest);
        return supportRequestMapper.toDTO(saved);
    }

    public List<SupportRequestDTO> findByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        return supportRequestRepository.findByUser(user).stream()
                .map(supportRequestMapper::toDTO)
                .toList();
    }
    
    public SupportRequestDTO findLast() {
        return supportRequestRepository.findTopByOrderByCreatedAtDesc()
                .map(SupportRequestMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("No support request found"));
    }
	
}
