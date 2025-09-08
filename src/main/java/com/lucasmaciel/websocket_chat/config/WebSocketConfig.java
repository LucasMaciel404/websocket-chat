package com.lucasmaciel.websocket_chat.config;

import com.lucasmaciel.websocket_chat.midlewere.CustomHandshakeHandler;
import com.lucasmaciel.websocket_chat.midlewere.JwtHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtHandshakeInterceptor jwtInterceptor;
    private final CustomHandshakeHandler handshakeHandler;

    public WebSocketConfig(JwtHandshakeInterceptor jwtInterceptor,
                           CustomHandshakeHandler handshakeHandler) {
        this.jwtInterceptor = jwtInterceptor;
        this.handshakeHandler = handshakeHandler;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .addInterceptors(jwtInterceptor)
                .setHandshakeHandler(handshakeHandler)
                .withSockJS();
    }
}