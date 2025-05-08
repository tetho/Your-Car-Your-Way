package com.yourcaryourway.chat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yourcaryourway.chat.model.SupportRequest;
import com.yourcaryourway.chat.model.User;

public interface SupportRequestRepository extends JpaRepository<SupportRequest, Integer> {

	List<SupportRequest> findByUser(User user);
	
}
