package com.backend.moviebooking.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {
        return message;
    }
}
