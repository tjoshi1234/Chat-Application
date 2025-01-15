package com.chat.backend.controllers;

import com.chat.backend.entities.Message;
import com.chat.backend.entities.Room;
import com.chat.backend.payloads.MessageRequest;
import com.chat.backend.repositories.RoomRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin("*")
public class ChatController {

    private RoomRepository roomRepository;

    // constructor injection
    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @MessageMapping("/sendMessage/{roomId}")  // /app/sendMessage/{roomId}
    @SendTo("/topic/room/{roomId}")          // subscribe or messages are published here
    public Message sendMessage(@DestinationVariable String roomId,
                               @RequestBody MessageRequest request){

        Room room = roomRepository.findByRoomId(roomId);

        Message message = new Message();

        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(request.getTimeStamp());

        if(room != null){
            room.getMessages().add(message);
            roomRepository.save(room);
        }
        else{
            throw new RuntimeException("Room not found!!");
        }
return message;
    }
}
