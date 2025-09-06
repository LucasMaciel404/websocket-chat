package com.lucasmaciel.websocket_chat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private String sender;
    private String receiver;
    private String content;
}
