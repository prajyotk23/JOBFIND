package com.JobFindingPlatform.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobFindingPlatform.Entity.ChatSupport;

@Repository
public interface ChatSupportRepository extends JpaRepository<ChatSupport, Long> {
    List<ChatSupport> findBySenderIdOrReceiverId(String senderId, String receiverId);
}
