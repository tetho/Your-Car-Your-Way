package com.yourcaryourway.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcaryourway.chat.dto.SupportRequestDTO;
import com.yourcaryourway.chat.service.SupportRequestService;

@RestController
@RequestMapping("/api/support-requests")
public class SupportRequestController {

    @Autowired
    private SupportRequestService supportRequestService;

    @PostMapping
    public SupportRequestDTO create(@RequestBody SupportRequestDTO supportRequestDTO) {
        return supportRequestService.create(supportRequestDTO);
    }

    @GetMapping("/user/{userId}")
    public List<SupportRequestDTO> getByUser(@PathVariable Integer userId) {
        return supportRequestService.findByUserId(userId);
    }
    
    @GetMapping("/latest")
    public SupportRequestDTO getLatestSupportRequest() {
        return supportRequestService.findLast();
    }
    
}
