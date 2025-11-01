package com.JobFindingPlatform.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.JobFindingPlatform.DTO.SupportRequestDTO;
import com.JobFindingPlatform.Entity.ChatSupport;
import com.JobFindingPlatform.Entity.UnblockRequest;
import com.JobFindingPlatform.Entity.User;
import com.JobFindingPlatform.Repository.ChatSupportRepository;
import com.JobFindingPlatform.Repository.UnblockRequestRepository;
import com.JobFindingPlatform.Repository.UserRepository;

@RestController
@RequestMapping("/api/support") // ✅ Matches frontend POST path
public class ChatSupportRestController {
    @Autowired
    private ChatSupportRepository chatRepo;
    
    @Autowired
    private UnblockRequestRepository unblockRepo;
    @Autowired
    private UserRepository userRepo;




    @GetMapping("/history/{userId}")
    public List<ChatSupport> getChatHistory(@PathVariable String userId) {
        return chatRepo.findBySenderIdOrReceiverId(userId, userId);
    }

    @PostMapping("/request-unblock")
    public ResponseEntity<String> requestUnblock(@RequestBody SupportRequestDTO dto) {
        UnblockRequest request = new UnblockRequest();
        request.setEmail(dto.getEmail());
        request.setReason(dto.getReason());
        request.setTimestamp(LocalDateTime.now());

        unblockRepo.save(request);

        return ResponseEntity.ok("✅ Your request has been sent to the admin.");
    }
    
    @GetMapping("/requests")
    public List<UnblockRequest> getAllRequests() {
        return unblockRepo.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }



}

