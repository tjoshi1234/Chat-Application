package com.chat.backend.controllers;

import com.chat.backend.entities.Message;
import com.chat.backend.entities.Room;
import com.chat.backend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("*")
public class RoomController {

    private RoomRepository roomRepository;


    // constructor injection
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // create room

    @PostMapping("/{roomId}")
    public ResponseEntity<?> createRoom(@PathVariable String roomId){  // ResponseEntity<?> ? --> multiple types of object can be returned

        if(roomRepository.findByRoomId(roomId) != null){
            return ResponseEntity.badRequest().body("Room already exists");
        }

        Room room = new Room();
        room.setRoomId(roomId);

        Room newroom = roomRepository.save(room);

        return new ResponseEntity<Room>(newroom, HttpStatus.CREATED);
    }


    // get room : join room

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId){
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().body("Room not found");
        }
        return ResponseEntity.ok(room);
    }


    // get messages of room

    @GetMapping("/getmessages/{roomId}")
    public ResponseEntity<?> getMessages(@PathVariable String roomId,
                                         @RequestParam(value = "page",defaultValue = "0",required = false) int page,
                                         @RequestParam(value = "size",defaultValue = "20",required = false) int size){

        Room room = roomRepository.findByRoomId(roomId);

        if(room == null){
            return ResponseEntity.badRequest().build();
        }

        // get messages
        // pagination
        List<Message> messages = room.getMessages();

        int start = Math.max(0,messages.size()-(page+1)*size);
        int end = Math.min(messages.size(),start+size);

        List<Message> paginatedMessages = messages.subList(start,end);


        return ResponseEntity.ok().body(paginatedMessages);
    }

}
