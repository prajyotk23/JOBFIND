package com.JobFindingPlatform.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.JobFindingPlatform.Entity.ChatSupport;
import com.JobFindingPlatform.Repository.ChatSupportRepository;

@Controller
public class WebSocketChatController {

    @Autowired
    private ChatSupportRepository chatRepo;

    @MessageMapping("/SendMessage")
    @SendTo("/topic/message")
    public ChatSupport sendMessage(ChatSupport message) {
        message.setTimeStamp(LocalDateTime.now());
        chatRepo.save(message);
        return message;
    }
}

