package com.backend.moviebooking.Controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SocketController {

    @MessageMapping("/send/{user}")
    @SendTo("/topic/messages/{user}")
    public String sendMessage(String message, @DestinationVariable String user) {
        return user;
    }


}
