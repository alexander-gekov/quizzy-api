package com.quizzy.quizzy;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {

    @MessageMapping("/join")
    @SendTo("/topic/greetings")
    public String join(@Payload String message) {
        return message;
    }
}
