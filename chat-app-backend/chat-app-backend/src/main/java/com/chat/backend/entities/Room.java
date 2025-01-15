package com.chat.backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rooms")
public class Room {

    @Id
    private String id;  // MongoDb : unique identifier

    private  String roomId;  // roomId is given by the user

    private List<Message> messages = new ArrayList<>();

    public  Room(){
        super();
    }

    public Room(String roomId, String id, List<Message> messages) {
        this.roomId = roomId;
        this.id = id;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomId='" + roomId + '\'' +
                ", messages=" + messages +
                '}';
    }
}
