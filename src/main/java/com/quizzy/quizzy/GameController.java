package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Message;
import com.quizzy.quizzy.model.Room;
import com.quizzy.quizzy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {
    @Autowired
    SimpUserRegistry userRegistry;


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private List<Room> rooms = new ArrayList<>();

    @MessageMapping("/game/sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        return chatMessage;
    }


//    @MessageMapping("/test/send")
//    public void findPlayers(@Payload Message message) {
//        message.setType(Message.MessageType.GET_PLAYERS);
//        message.setContent(this.players.toString());
//        simpMessagingTemplate.convertAndSend("/topic/public",message);
//    }

    @MessageMapping("/game/{gameId}")
    public void createGame(@DestinationVariable String gameId, @Payload Message message) {
        boolean exists = false;
        for (Room room : rooms) {
            if (room.getGameId().equals(gameId)) {
                exists = true;
                Message newMessage = new Message();
                newMessage.setSender("server");
                newMessage.setType(Message.MessageType.EXISTS);
                simpMessagingTemplate.convertAndSend("/topic/" + gameId, newMessage);
            }
        }
        if (!exists) {
            Room room = new Room();
            room.setGameId(gameId);
            List<String> users = new ArrayList<>();
            room.setPlayers(users);
            room.setOwner(message.getSender());
            rooms.add(room);
            simpMessagingTemplate.convertAndSend("/topic/" + gameId, message);
        }
    }

    @MessageMapping("/game/{gameId}/answer")
    public void answer(@DestinationVariable String gameId, @Payload Message message) {
        simpMessagingTemplate.convertAndSend("/topic/" + gameId, message);
    }

    @MessageMapping("/game/{gameId}/endGame")
    public void endGame(@DestinationVariable String gameId, @Payload Message message) {
        message.setType(Message.MessageType.END);

        simpMessagingTemplate.convertAndSend("/topic/" + gameId, message);
    }

    @MessageMapping("/game/{gameId}/removeUser")
    public void removeUser(@DestinationVariable String gameId, @Payload Message message) {
        for (Room room : rooms) {
            if (room.getGameId().equals(gameId)) {
                room.getPlayers().remove(message.getSender());
                message.setContent(room.getPlayers().toString());
            }
        }
        simpMessagingTemplate.convertAndSend("/topic/" + gameId, message);
    }

    @MessageMapping("/game/{gameId}/countdown")
    public void countdown(@DestinationVariable String gameId, @Payload Message message) {
        if(message.getType() == Message.MessageType.COUNTDOWN){
            simpMessagingTemplate.convertAndSend("/topic/" + gameId, message);
        }
    }

    @MessageMapping("/game/{gameId}/startGame")
    public void startGame(@DestinationVariable String gameId, @Payload Message message) {
        message.setType(Message.MessageType.START);
        simpMessagingTemplate.convertAndSend("/topic/" + gameId, message);
    }

    @MessageMapping("/game/{gameId}/addUser")
    public void addUser(@DestinationVariable String gameId, @Payload Message chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session

        boolean exists = false;
        for (Room room1 : rooms) {
            if (room1.getGameId().equals(gameId)) {
                room1.getPlayers().add(chatMessage.getSender());
                chatMessage.setContent(room1.getPlayers().toString());
                chatMessage.setOther(room1.getOwner());
                exists = true;
            }
        }
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        simpMessagingTemplate.convertAndSend("/topic/" + gameId, chatMessage);
    }
}
