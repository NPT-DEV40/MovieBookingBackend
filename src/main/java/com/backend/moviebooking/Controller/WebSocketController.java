package com.backend.moviebooking.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/hello")
    @SendTo("/topic/news")
    public String test(String message) {
        System.out.println("Received message from Angular: " + message);
        return "Hello from Spring Boot: " + message;
    }
}
