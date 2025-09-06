package com.lucasmaciel.websocket_chat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageResponse {
    private String from;
    private String message;
    private long timestamp;
}