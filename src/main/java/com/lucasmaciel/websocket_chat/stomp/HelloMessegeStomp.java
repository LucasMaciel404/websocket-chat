package com.lucasmaciel.websocket_chat.stomp;

public class HelloMessegeStomp {

    private String name;

    public HelloMessegeStomp() {
    }

    public HelloMessegeStomp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}