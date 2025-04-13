package com.nodalx.service1.repository;

import com.nodalx.service1.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Add custom query methods if needed
} 