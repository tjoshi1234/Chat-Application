package com.chat.backend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


public class MessageRequest {

    private String content;
    private String sender;
    private LocalDateTime timeStamp;
    private String roomId;

    public MessageRequest(){}

    public MessageRequest(String content, String sender, String roomId) {
        this.content = content;
        this.sender = sender;
        this.timeStamp  = LocalDateTime.now();
        this.roomId = roomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", timeStamp=" + timeStamp +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
