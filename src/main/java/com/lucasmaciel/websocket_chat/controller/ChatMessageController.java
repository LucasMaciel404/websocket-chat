package com.lucasmaciel.websocket_chat.controller;

import com.lucasmaciel.websocket_chat.dto.request.ChatMessageRequest;
import com.lucasmaciel.websocket_chat.dto.response.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/private")
    public void sendPrivateMessage(ChatMessageRequest request) {
        ChatMessageResponse response = new ChatMessageResponse(
                request.getSender(),
                request.getContent(),
                System.currentTimeMillis()
        );

        // envia para o destino do receiver
        messagingTemplate.convertAndSendToUser(
                request.getReceiver(),
                "/queue/messages",
                response
        );
    }

}
