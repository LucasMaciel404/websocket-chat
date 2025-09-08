package com.lucasmaciel.websocket_chat.controller;

import com.lucasmaciel.websocket_chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send")
    public void sendPrivateMessage(@Payload ChatMessage message, Principal principal) {
        message.setFrom(principal.getName());
        messagingTemplate.convertAndSendToUser(
                message.getTo(),
                "/queue/messages",
                message
        );
    }
}
